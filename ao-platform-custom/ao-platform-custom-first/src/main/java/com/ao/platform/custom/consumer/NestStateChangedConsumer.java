package com.ao.platform.custom.consumer;

import com.ao.platform.base.constant.NestConstant;
import com.ao.platform.core.lantek.constants.NestMqConstants;
import com.ao.platform.core.lantek.dto.DisNestNest00000100DTO;
import com.ao.platform.core.lantek.dto.NestStateChangedEvent;
import com.ao.platform.core.lantek.vo.DisNestNest00000100VO;
import com.ao.platform.core.lantek.vo.DisNestNest00000500VO;
import com.ao.platform.core.lantek.vo.PprrPprr00000100VO;
import com.ao.platform.custom.dto.AcceptOrderRequestDTO;
import com.ao.platform.custom.feign.DisNestNest00000100FeignClient;
import com.ao.platform.custom.feign.MmnnMmoo00000300FeignClient;
import com.ao.platform.custom.httpclient.CcsApiClient;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class NestStateChangedConsumer {


    DisNestNest00000100FeignClient disNestNest00000100FeignClient;
    MmnnMmoo00000300FeignClient mmnnMmoo00000300FeignClient;

    CcsApiClient ccsApiClient;

    @RabbitListener(queues = NestMqConstants.Queue.CUSTOM_STATE)
    public void handle(NestStateChangedEvent event,
                       Channel channel,
                       Message message) throws IOException {

        long tag = message.getMessageProperties().getDeliveryTag();

        try {

            log.info("收到套料状态变更 recID={}, oldState={}, newState={}",
                    event.getRecID(),
                    event.getOldState(),
                    event.getNewState());

            // 业务处理
            process(event);

            // ACK
            channel.basicAck(tag, false);

        } catch (Exception e) {

            log.error("消费失败 recID={}", event.getRecID(), e);

            // 重新入队
            channel.basicNack(tag, false, true);
        }
    }

    private void process(NestStateChangedEvent event) {

        if (event.getNewState() == NestConstant.MState.IN_WORKSHOP) {
            Integer recID = event.getRecID();
            DisNestNest00000100DTO query = new DisNestNest00000100DTO();
            query.setRecID(recID);
            DisNestNest00000100VO nest = disNestNest00000100FeignClient.details(query).unwrap();
            List<DisNestNest00000500VO> nestParts = nest.getNestParts();

            AcceptOrderRequestDTO dto = new AcceptOrderRequestDTO();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dto.setCreateTime(nest.getCrtDate().format(dateTimeFormatter));
            dto.setOrderCode("");
            dto.setProjectCode("");
            dto.setIsBack(0);
            List<AcceptOrderRequestDTO.NestingMains> nestingMains = new ArrayList<>();
            AcceptOrderRequestDTO.NestingMains nestingMain = new AcceptOrderRequestDTO.NestingMains();
            nestingMain.setMaterialCode(nest.getShtRef());
            nestingMain.setProgram(nest.getCNC());
            nestingMain.setLength(nest.getSLength());
            nestingMain.setWidth(nest.getSWidth());
            nestingMain.setHeight(nest.getSThickness());
            nestingMain.setMachine(nest.getWrkRef());
            nestingMain.setMaterial(nest.getMatRef());
            nestingMain.setCraft(NestConstant.OprRef.Punch.equalsIgnoreCase(nest.getOprRef()) ? 2 : 1);
            nestingMain.setRunCount(nest.getQuantity());
            nestingMain.setParameter("");
            nestingMain.setProgramPath(nest.getCNCPath());
            nestingMain.setPickPath(nest.getWMFPath());
            List<AcceptOrderRequestDTO.NestingDetails> nestingDetails = nestParts.stream()
                    .map(it -> {
                        PprrPprr00000100VO meta = it.getMeta();
                        AcceptOrderRequestDTO.NestingDetails nestingDetail = new AcceptOrderRequestDTO.NestingDetails();
                        nestingDetail.setPartCode(it.getPrdRefDst());
                        nestingDetail.setPartName(meta.getPrdName());
                        nestingDetail.setPartLength(meta.getDIS_Length());
                        nestingDetail.setPartWidth(meta.getDIS_Width());
                        nestingDetail.setPartHeight(nest.getSThickness());
                        nestingDetail.setPartMaterial(nest.getMatRef());
                        nestingDetail.setPartNum(it.getQuantity());
                        nestingDetail.setPartPath(it.getWMFPath());
                        return nestingDetail;
                    }).toList();
            nestingMain.setNestingDetails(nestingDetails);

            nestingMains.add(nestingMain);


            List<AcceptOrderRequestDTO.Details> jobPartDetails = new ArrayList<>();

            ccsApiClient.requestAcceptOrder(dto);
        }
    }

}