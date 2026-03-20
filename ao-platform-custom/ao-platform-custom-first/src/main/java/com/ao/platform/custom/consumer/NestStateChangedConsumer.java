package com.ao.platform.custom.consumer;

import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;
import com.ao.platform.base.constant.NestConstant;
import com.ao.platform.base.exception.BusinessException;
import com.ao.platform.base.util.DateFormats;
import com.ao.platform.core.lantek.constants.NestMqConstants;
import com.ao.platform.core.lantek.dto.DisNestNest00000100DTO;
import com.ao.platform.core.lantek.dto.MmnnMmoo00000300PageQuery;
import com.ao.platform.core.lantek.dto.NestStateChangedEvent;
import com.ao.platform.core.lantek.vo.DisNestNest00000100VO;
import com.ao.platform.core.lantek.vo.DisNestNest00000500VO;
import com.ao.platform.core.lantek.vo.MmnnMmoo00000300VO;
import com.ao.platform.custom.dto.*;
import com.ao.platform.custom.feign.DisNestNest00000100FeignClient;
import com.ao.platform.custom.feign.MmnnMmoo00000300FeignClient;
import com.ao.platform.custom.httpclient.CcsApiClient;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class NestStateChangedConsumer {


    private final DisNestNest00000100FeignClient disNestNest00000100FeignClient;
    private final MmnnMmoo00000300FeignClient mmnnMmoo00000300FeignClient;
    private final CcsApiClient ccsApiClient;

    @RabbitListener(queues = NestMqConstants.Queue.CUSTOM_STATE)
    public void handle(NestStateChangedEvent event) {

        log.info("收到套料状态变更 recID={}, oldState={}, newState={}",
                event.getRecID(),
                event.getOldState(),
                event.getNewState());

        // 业务处理
        process(event);
    }

    private void process(NestStateChangedEvent event) {

        if (event.getNewState() == NestConstant.MState.IN_WORKSHOP) {
            Integer recID = event.getRecID();
            DisNestNest00000100DTO query = new DisNestNest00000100DTO();
            query.setRecID(recID);
            DisNestNest00000100VO nest = disNestNest00000100FeignClient.details(query).unwrap();
            List<DisNestNest00000500VO> nestParts = nest.getNestParts();
            List<MmnnMmoo00000300VO> jobParts = nest.getJobParts();

            List<String> ordRefs = jobParts.stream().map(MmnnMmoo00000300VO::getOrdRef).distinct()
                    .filter(StringUtils::isNotBlank).toList();

            OrderNestingRequest orderNestingRequest = new OrderNestingRequest();
            List<NestingMain> nestingMains = new ArrayList<>();

            MmnnMmoo00000300PageQuery mmoo00000300PageQuery = new MmnnMmoo00000300PageQuery();
            mmoo00000300PageQuery.setOrdRefs(ordRefs);
            mmoo00000300PageQuery.setPageSize(-1L);
            PageResponse<MmnnMmoo00000300VO> ordPartPageResponse = mmnnMmoo00000300FeignClient.page(mmoo00000300PageQuery).unwrap();
            List<MmnnMmoo00000300VO> ordParts = ordPartPageResponse.items();
            Map<String, MmnnMmoo00000300VO> ordCrtDateMap = ordParts.stream().collect(Collectors.toMap(MmnnMmoo00000300VO::getOrdRef,
                    Function.identity(),
                    BinaryOperator.minBy(Comparator.comparing(MmnnMmoo00000300VO::getCrtDate))
            ));
            Map<String, List<MmnnMmoo00000300VO>> ordMap = ordParts.stream().collect(Collectors.groupingBy(MmnnMmoo00000300VO::getOrdRef));
            Map<String, String> ordRefMap = ordParts.stream().collect(Collectors.toMap(MmnnMmoo00000300VO::getMnORef,MmnnMmoo00000300VO::getOrdRef,(cur,old) -> cur));

            List<OrderMain> orderMains = ordMap.entrySet().stream().map(it -> OrderMain.builder()
                    .createTime(ordCrtDateMap.get(it.getKey()).getCrtDate().format(DateFormats.DATETIME))
                    .orderCode(it.getKey())
                    .projectCode("")
                    .details(it.getValue().stream().map(d -> OrderDetail.builder()
                            .partCode(d.getPrdRef())
                            .partName(d.getPrdNameDst())
                            .partNum(d.getRQ().intValue())
                            .craft(NestConstant.OprRef.Punch.equalsIgnoreCase(d.getOprRef()) ? 2 : 1)
                            .build()
                    ).toList())
                    .isBack(1)
                    .build()
            ).toList();

            NestingMain nestMain = NestingMain.builder()
                    .nestingReference(nest.getNstRef())
                    .orderCode(ordRefs.stream().collect(Collectors.joining("/")))
                    .materialCode(nest.getShtRef())
                    .program(Path.of(nest.getCNCPath())
                            .getFileName()
                            .toString())
                    .length(nest.getSLength())
                    .width(nest.getSWidth())
                    .height(nest.getSThickness())
                    .machine(nest.getWrkRef())
                    .material(nest.getMatRef())
                    .craft(NestConstant.OprRef.Punch.equalsIgnoreCase(nest.getOprRef()) ? 2 : 1)
                    .runCount(nest.getQuantity())
                    .parameter("")
                    .programPath(nest.getCNCPath())
                    .pickPath(nest.getFullWMFPath())
                    .nestingDetails(nestParts.stream().map(it -> NestingDetail.builder()
                            .orderCode(ordRefMap.get(it.getMnORef()))
                            .partCode(it.getPrdRefDst())
                            .partName(it.getMeta().getPrdName())
                            .partLength(it.getMeta().getDIS_Length())
                            .partWidth(it.getMeta().getDIS_Width())
                            .partHeight(nest.getSThickness())
                            .partMaterial(nest.getMatRef())
                            .partNum(it.getQuantity())
                            .partPath(it.getFullWMFPath())
                            .build()
                    ).toList())
                    .build();

            nestingMains.add(nestMain);

            orderNestingRequest.setNestingMains(nestingMains);
            orderNestingRequest.setOrderMains(orderMains);

            ApiResponse apiResponse = ccsApiClient.requestAcceptOrder(orderNestingRequest);
            if (apiResponse == null) {
                String msg = "中控接口请求失败";
                log.error(msg);
                log.info(nest.getNstRef());
                throw new BusinessException(msg);
            }
            if (apiResponse.code() != 0) {
                log.error(apiResponse.message());
                throw new BusinessException(apiResponse.message());
            }
        }
    }

}