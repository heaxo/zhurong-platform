package com.zhurong.platform.custom.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.custom.convert.ZhurongButNestingPartsSplitRecordsConvert;
import com.zhurong.platform.custom.dto.ZhurongButNestingPartsSplitRecordsCreateDTO;
import com.zhurong.platform.custom.dto.ZhurongButNestingPartsSplitRecordsDTO;
import com.zhurong.platform.custom.dto.ZhurongButNestingPartsSplitRecordsPageQuery;
import com.zhurong.platform.custom.entity.DisNestNest00000100;
import com.zhurong.platform.custom.entity.DisNestNest00000500;
import com.zhurong.platform.custom.entity.MmnnMmoo00000300;
import com.zhurong.platform.custom.entity.ZhurongButNestingPartsSplitRecords;
import com.zhurong.platform.custom.service.IDisNestNest00000100Service;
import com.zhurong.platform.custom.service.IDisNestNest00000500Service;
import com.zhurong.platform.custom.service.IMmnnMmoo00000300Service;
import com.zhurong.platform.custom.service.IZhurongButNestingPartsSplitRecordsService;
import com.zhurong.platform.custom.vo.ZhurongButNestingPartsSplitRecordsVO;
import com.zhurong.platform.custom.web.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/zhurongButNestingPartsSplitRecords")
public class ZhurongButNestingPartsSplitRecordsController extends BaseController {

    private final ZhurongButNestingPartsSplitRecordsConvert convert;
    private final IZhurongButNestingPartsSplitRecordsService service;
    private final IMmnnMmoo00000300Service mmnnMmoo00000300Service;
    private final IDisNestNest00000100Service disNestNest00000100Service;
    private final IDisNestNest00000500Service disNestNest00000500Service;

    @GetMapping("page")
    public ApiResponse
            <PageResponse
                    <ZhurongButNestingPartsSplitRecordsVO>> page(ZhurongButNestingPartsSplitRecordsPageQuery pageQuery) {

        LambdaQueryWrapper<ZhurongButNestingPartsSplitRecords> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(ZhurongButNestingPartsSplitRecords::getCreatedAt);

        Page<ZhurongButNestingPartsSplitRecords> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <ZhurongButNestingPartsSplitRecordsVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <ZhurongButNestingPartsSplitRecordsVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @PostMapping("splitRecordsOverwrite")
    public ApiResponse<Boolean> splitRecordsOverwrite(@RequestBody @Valid ZhurongButNestingPartsSplitRecordsDTO dto) {

        if (CollectionUtils.isEmpty(dto.getRecords())){
            try {
                boolean result = service.removeSplitRecords(dto.getNstRef());
                return ApiResponse.success(result);
            } catch (Exception e) {
                return ApiResponse.fail(e.getMessage());
            }
        }

        try {
            boolean result = service.splitRecordsOverwrite(dto.getRecords());
            return ApiResponse.success(result);
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }
    @PostMapping("orderSplitting")
    public ApiResponse<Boolean> orderSplitting(@RequestBody @Valid ZhurongButNestingPartsSplitRecordsDTO dto) {

        if (dto.getOrgMnoRefs() == null){
            return ApiResponse.fail("订单号不能为空");
        }
        List<String> orgMnoRefs = new java.util.ArrayList<>(dto.getOrgMnoRefs().stream().distinct().toList());
        if (CollectionUtils.isEmpty(orgMnoRefs)){
            return ApiResponse.fail("订单号不能为空");
        }
        //获取这些订单已拆分的数据
        List<ZhurongButNestingPartsSplitRecords> spliteds = service.list(Wrappers.lambdaQuery(ZhurongButNestingPartsSplitRecords.class)
                .in(ZhurongButNestingPartsSplitRecords::getOrgMnoRef, orgMnoRefs));

        //按订单号分组处理
        Map<String, List<ZhurongButNestingPartsSplitRecords>> groupMap = spliteds.stream()
                .collect(Collectors.groupingBy(e -> e.getOrgMnoRef().toUpperCase(Locale.ROOT)));
        //获取原始订单数量
        List<MmnnMmoo00000300> mmnnMmoo00000300s = mmnnMmoo00000300Service.list(Wrappers.lambdaQuery(MmnnMmoo00000300.class)
                .in(MmnnMmoo00000300::getMnORef, orgMnoRefs)
                .gt(MmnnMmoo00000300::getMinQuan, 0));

        if (CollectionUtils.isEmpty(mmnnMmoo00000300s)){
            String msg = String.format("订单没有记录原始数量，无法执行拆单：%s", String.join(",", orgMnoRefs));
            log.warn(msg);
            return ApiResponse.fail(msg);
        }

        //取各个订单原始数量
        Map<String, MmnnMmoo00000300> mmnnMmoo00000300Map = mmnnMmoo00000300s.stream().collect(Collectors.toMap(
                e -> e.getMnORef().toUpperCase(Locale.ROOT), Function.identity(),
                BinaryOperator.maxBy(
                        Comparator.comparing(MmnnMmoo00000300::getCrtDate, Comparator.naturalOrder())
                )));

        if (mmnnMmoo00000300Map.size() < orgMnoRefs.size()){
            List<String> keys = mmnnMmoo00000300Map.values().stream().map(MmnnMmoo00000300::getMnORef).distinct().toList();
            orgMnoRefs.removeAll(keys);
            String msg = String.format("没有可拆订单，无法执行拆单（订单可能没有记录原始数量），%s", String.join(",", orgMnoRefs));
            log.warn(msg);
            return ApiResponse.fail(msg);
        }

        //获取这些工单的套料零件
        List<DisNestNest00000500> nestParts = disNestNest00000500Service.list(Wrappers.lambdaQuery(DisNestNest00000500.class)
                .in(DisNestNest00000500::getMnORef, orgMnoRefs));

        Map<String, List<DisNestNest00000500>> nestPartMap = nestParts.stream()
                .collect(Collectors.groupingBy(e -> e.getMnORef().toUpperCase(Locale.ROOT)));

        List<ZhurongButNestingPartsSplitRecordsCreateDTO> creates = new ArrayList<>();

        for(String mnoRef : mmnnMmoo00000300Map.keySet()){
            MmnnMmoo00000300 mmnn300 = mmnnMmoo00000300Map.get(mnoRef);
            String mnoRefKey = mnoRef.toUpperCase(Locale.ROOT);
            //可拆数量（用户手动改的最终数量 - 最开始的原始数量，）
            //比如：原始数量：20，修改后的数量：30
            //30 - 20 = 10（10是可拆分的数量，不能超过这个数量）
            double detachableQuantity = mmnn300.getRQ() - mmnn300.getMinQuan();
            //已拆分数量
            Integer splitedQuantity = 0;

            if (!nestPartMap.containsKey(mnoRefKey)){
                String msg = String.format("工单没有套料程序，无法执行拆单，%s", mmnn300.getMnORef());
                log.warn(msg);
                return ApiResponse.fail(msg);
            }
            List<DisNestNest00000500> disNestNest00000500S = nestPartMap.get(mnoRefKey);

            List<String> nstRefs = new java.util.ArrayList<>(disNestNest00000500S.stream()
                    .map(DisNestNest00000500::getNstRef).distinct().toList());

            List<ZhurongButNestingPartsSplitRecords> splitRecords = groupMap.getOrDefault(mnoRefKey, null);
            if (splitRecords != null){
                //统计已拆分数量
                splitedQuantity = splitRecords.stream()
                        //两个订单号不相等，说明其中一个是带后缀的被拆分订单
                        .filter(it -> !it.getOrgMnoRef().equals(it.getMnoRef()))
                        .mapToInt(ZhurongButNestingPartsSplitRecords::getQuantity)
                        .sum();
                if (detachableQuantity <= splitedQuantity){
                    String msg = String.format("订单：%s，没有可拆单的余数，可拆数量：%s，已拆数量：%s",mnoRef,detachableQuantity,splitedQuantity);
                    log.warn(msg);
                    return ApiResponse.fail(msg);
                }
                //排除掉这些已拆分过的程序
                nstRefs.removeAll(splitRecords.stream()
                        .map(ZhurongButNestingPartsSplitRecords::getNstRef)
                        .distinct().toList());

                if (CollectionUtils.isEmpty(nstRefs)){
                    String msg = String.format("%s，没有可拆单的程序",mnoRef);
                    log.warn(msg);
                    return ApiResponse.fail(msg);
                }

            }
            //计算本次可拆数量
            double currentDetachableQuantity = detachableQuantity - splitedQuantity.doubleValue();

            //获取可拆程序集
            LambdaQueryWrapper<DisNestNest00000100> nestLambdaQueryWrapper = Wrappers.lambdaQuery(DisNestNest00000100.class);
            nestLambdaQueryWrapper.in(DisNestNest00000100::getNstRef,nstRefs);
            List<DisNestNest00000100> nests = disNestNest00000100Service.list(nestLambdaQueryWrapper);

            if (CollectionUtils.isEmpty(nests)){
                String msg = String.format("%s，没有可拆单程序集",mnoRef);
                log.warn(msg);
                return ApiResponse.fail(msg);
            }

            List<DisNestNest00000500> values = disNestNest00000500Service.list(Wrappers.lambdaQuery(DisNestNest00000500.class)
                    .in(DisNestNest00000500::getNstRef, nests.stream().map(DisNestNest00000100::getNstRef).toList())
                    .eq(DisNestNest00000500::getMnORef, mnoRef));
            /**
             * 假设
             * 本次可拆数量 = 1，即 currentDetachableQuantity = 1
             * 找到values中满足数量的一条进行拆分（可能values数据本身也只有一条）,
             * 当values只有一条数据，且数量正好是当前可拆分的数量时，那么直接将这条数据创建为拆单数据（new ZhurongButNestingPartsSplitRecordsCreateDTO()）
             */

            if (values.size() == 1 && values.get(0).getQuantity() >= currentDetachableQuantity){
                DisNestNest00000500 nestPart = values.get(0);
                Integer quantity = nestPart.getQuantity();
                boolean isRemainder = quantity.doubleValue() > currentDetachableQuantity;
                ZhurongButNestingPartsSplitRecordsCreateDTO create1 = new ZhurongButNestingPartsSplitRecordsCreateDTO();
                create1.setNstRef(nestPart.getNstRef());
                //拆单数据需要带上后缀
                create1.setMnoRef(String.format("%s_A", nestPart.getMnORef()));
                create1.setOrgMnoRef(nestPart.getMnORef());
                create1.setOprId(nestPart.getOprID());
                create1.setQuantity(Integer.getInteger(String.format("%s", currentDetachableQuantity)));
                create1.setRemark("程序自动拆单");
                create1.setRecId(nestPart.getRecID());
                create1.setPrdRef(nestPart.getPrdRefDst());
                creates.add(create1);

                //有余数，需要将剩余数量的数据也创建一份拆单记录（不同的是此次不带后缀）
                if (isRemainder){
                    Double qty = quantity.doubleValue() - currentDetachableQuantity;
                    ZhurongButNestingPartsSplitRecordsCreateDTO create2 = new ZhurongButNestingPartsSplitRecordsCreateDTO();
                    create2.setNstRef(nestPart.getNstRef());
                    create2.setMnoRef(nestPart.getMnORef());
                    create2.setOrgMnoRef(nestPart.getMnORef());
                    create2.setOprId(nestPart.getOprID());
                    create2.setQuantity(qty.intValue());
                    create2.setRemark("程序自动拆单");
                    create2.setRecId(nestPart.getRecID());
                    create2.setPrdRef(nestPart.getPrdRefDst());
                    creates.add(create2);
                }
            }

        }

        return ApiResponse.success(true);
    }
}
