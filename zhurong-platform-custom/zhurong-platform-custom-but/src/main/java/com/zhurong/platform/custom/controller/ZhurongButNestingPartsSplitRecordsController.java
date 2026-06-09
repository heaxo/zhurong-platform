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

    private static final String AUTO_SPLIT_SUFFIX = "_B";
    private static final String AUTO_SPLIT_REMARK = "程序自动拆单";

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
        Map<String, String> orgMnoRefMap = dto.getOrgMnoRefs().stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(it -> !it.isEmpty())
                .collect(Collectors.toMap(
                        ZhurongButNestingPartsSplitRecordsController::normalizeRef,
                        Function.identity(),
                        (first, second) -> first,
                        LinkedHashMap::new
                ));
        List<String> orgMnoRefs = new ArrayList<>(orgMnoRefMap.values());
        if (CollectionUtils.isEmpty(orgMnoRefs)){
            return ApiResponse.fail("订单号不能为空");
        }
        log.info("开始自动拆单，原始请求订单：{}，有效订单：{}", dto.getOrgMnoRefs(), orgMnoRefs);
        //获取这些订单已拆分的数据
        List<ZhurongButNestingPartsSplitRecords> spliteds = service.list(Wrappers.lambdaQuery(ZhurongButNestingPartsSplitRecords.class)
                .in(ZhurongButNestingPartsSplitRecords::getOrgMnoRef, orgMnoRefs));
        log.info("自动拆单查询已拆记录完成，订单数：{}，已拆记录数：{}", orgMnoRefs.size(), spliteds.size());

        //按订单号分组处理
        Map<String, List<ZhurongButNestingPartsSplitRecords>> groupMap = spliteds.stream()
                .collect(Collectors.groupingBy(e -> normalizeRef(e.getOrgMnoRef())));
        //获取原始订单数量
        List<MmnnMmoo00000300> mmnnMmoo00000300s = mmnnMmoo00000300Service.list(Wrappers.lambdaQuery(MmnnMmoo00000300.class)
                .in(MmnnMmoo00000300::getMnORef, orgMnoRefs)
                .gt(MmnnMmoo00000300::getMinQuan, 0));
        log.info("自动拆单查询订单原始数量完成，请求订单数：{}，可拆订单记录数：{}", orgMnoRefs.size(), mmnnMmoo00000300s.size());

        if (CollectionUtils.isEmpty(mmnnMmoo00000300s)){
            String msg = String.format("订单没有记录原始数量，无法执行拆单：%s", String.join(",", orgMnoRefs));
            log.warn(msg);
            return ApiResponse.fail(msg);
        }

        //取各个订单原始数量
        Map<String, MmnnMmoo00000300> mmnnMmoo00000300Map = mmnnMmoo00000300s.stream().collect(Collectors.toMap(
                e -> normalizeRef(e.getMnORef()), Function.identity(),
                BinaryOperator.maxBy(
                        Comparator.comparing(MmnnMmoo00000300::getCrtDate, Comparator.naturalOrder())
                )));

        List<String> missingMnoRefs = orgMnoRefs.stream()
                .filter(it -> !mmnnMmoo00000300Map.containsKey(normalizeRef(it)))
                .toList();
        if (!missingMnoRefs.isEmpty()){
            String msg = String.format("没有可拆订单，无法执行拆单（订单可能没有记录原始数量），%s", String.join(",", missingMnoRefs));
            log.warn(msg);
            return ApiResponse.fail(msg);
        }

        //获取这些工单的套料零件
        List<DisNestNest00000500> nestParts = disNestNest00000500Service.list(Wrappers.lambdaQuery(DisNestNest00000500.class)
                .in(DisNestNest00000500::getMnORef, orgMnoRefs));
        log.info("自动拆单查询套料零件完成，订单数：{}，套料零件记录数：{}", orgMnoRefs.size(), nestParts.size());

        Map<String, List<DisNestNest00000500>> nestPartMap = nestParts.stream()
                .collect(Collectors.groupingBy(e -> normalizeRef(e.getMnORef())));

        List<ZhurongButNestingPartsSplitRecordsCreateDTO> creates = new ArrayList<>();

        for(Map.Entry<String, MmnnMmoo00000300> entry : mmnnMmoo00000300Map.entrySet()){
            MmnnMmoo00000300 mmnn300 = entry.getValue();
            String mnoRef = mmnn300.getMnORef();
            String mnoRefKey = entry.getKey();
            //可拆数量（用户手动改的最终数量 - 最开始的原始数量，）
            //比如：原始数量：20，修改后的数量：30
            //30 - 20 = 10（10是可拆分的数量，不能超过这个数量）
            int detachableQuantity = calculateDetachableQuantity(mmnn300);
            log.info("自动拆单处理订单，订单：{}，当前数量：{}，原始数量：{}，可拆数量：{}",
                    mnoRef, mmnn300.getRQ(), mmnn300.getMinQuan(), detachableQuantity);
            if (detachableQuantity <= 0){
                String msg = String.format("订单：%s，没有可拆单数量，当前数量：%s，原始数量：%s",
                        mnoRef, mmnn300.getRQ(), mmnn300.getMinQuan());
                log.warn(msg);
                return ApiResponse.fail(msg);
            }
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
            log.info("自动拆单订单套料程序统计，订单：{}，套料零件数：{}，套料程序数：{}",
                    mnoRef, disNestNest00000500S.size(), nstRefs.size());
            log.debug("自动拆单订单套料程序明细，订单：{}，nstRefs：{}", mnoRef, nstRefs);

            List<ZhurongButNestingPartsSplitRecords> splitRecords = groupMap.getOrDefault(mnoRefKey, null);
            if (splitRecords != null){
                //统计已拆分数量
                splitedQuantity = splitRecords.stream()
                        //两个订单号不相等，说明其中一个是带后缀的被拆分订单
                        .filter(it -> !isSameRef(it.getOrgMnoRef(), it.getMnoRef()))
                        .mapToInt(it -> Optional.ofNullable(it.getQuantity()).orElse(0))
                        .sum();
                log.info("自动拆单已有拆分统计，订单：{}，已拆记录数：{}，已拆数量：{}",
                        mnoRef, splitRecords.size(), splitedQuantity);
                if (detachableQuantity <= splitedQuantity){
                    String msg = String.format("订单：%s，没有可拆单的余数，可拆数量：%s，已拆数量：%s",mnoRef,detachableQuantity,splitedQuantity);
                    log.warn(msg);
                    return ApiResponse.fail(msg);
                }
                //排除掉这些已拆分过的程序
                nstRefs.removeAll(splitRecords.stream()
                        .map(ZhurongButNestingPartsSplitRecords::getNstRef)
                        .distinct().toList());
                log.info("自动拆单排除已拆套料程序完成，订单：{}，剩余可拆套料程序数：{}", mnoRef, nstRefs.size());
                log.debug("自动拆单剩余可拆套料程序明细，订单：{}，nstRefs：{}", mnoRef, nstRefs);

                if (CollectionUtils.isEmpty(nstRefs)){
                    String msg = String.format("%s，没有可拆单的程序",mnoRef);
                    log.warn(msg);
                    return ApiResponse.fail(msg);
                }

            }
            //计算本次可拆数量
            int currentDetachableQuantity = detachableQuantity - splitedQuantity;
            log.info("自动拆单本次可拆数量，订单：{}，可拆数量：{}，已拆数量：{}，本次可拆数量：{}",
                    mnoRef, detachableQuantity, splitedQuantity, currentDetachableQuantity);

            //获取可拆程序集
            LambdaQueryWrapper<DisNestNest00000100> nestLambdaQueryWrapper = Wrappers.lambdaQuery(DisNestNest00000100.class);
            nestLambdaQueryWrapper.in(DisNestNest00000100::getNstRef,nstRefs);
            List<DisNestNest00000100> nests = disNestNest00000100Service.list(nestLambdaQueryWrapper);
            log.info("自动拆单查询可拆程序集完成，订单：{}，请求套料程序数：{}，可拆程序集记录数：{}",
                    mnoRef, nstRefs.size(), nests.size());

            if (CollectionUtils.isEmpty(nests)){
                String msg = String.format("%s，没有可拆单程序集",mnoRef);
                log.warn(msg);
                return ApiResponse.fail(msg);
            }

            List<DisNestNest00000500> values = disNestNest00000500Service.list(Wrappers.lambdaQuery(DisNestNest00000500.class)
                    .in(DisNestNest00000500::getNstRef, nests.stream().map(DisNestNest00000100::getNstRef).toList())
                    .eq(DisNestNest00000500::getMnORef, mnoRef));
            int availableQuantity = values.stream().mapToInt(ZhurongButNestingPartsSplitRecordsController::safeQuantity).sum();
            log.info("自动拆单查询可拆套料零件完成，订单：{}，可拆零件记录数：{}，可拆零件数量合计：{}",
                    mnoRef, values.size(), availableQuantity);
            log.debug("自动拆单可拆套料零件明细，订单：{}，values：{}", mnoRef,
                    values.stream().map(ZhurongButNestingPartsSplitRecordsController::describeNestPart).toList());
            int beforeSize = creates.size();
            appendOrderSplitRecords(creates, values, currentDetachableQuantity);
            log.info("自动拆单订单记录生成完成，订单：{}，本订单新增记录数：{}，当前累计新增记录数：{}",
                    mnoRef, creates.size() - beforeSize, creates.size());
            if (creates.size() == beforeSize){
                String msg = String.format("%s，没有可拆单的套料零件数量",mnoRef);
                log.warn(msg);
                return ApiResponse.fail(msg);
            }

        }

        if (CollectionUtils.isEmpty(creates)){
            return ApiResponse.fail("没有生成可保存的拆单记录");
        }
        List<ZhurongButNestingPartsSplitRecords> saves = convert.toEntity(creates);
        log.info("自动拆单开始保存，订单：{}，保存记录数：{}，拆出数量合计：{}",
                orgMnoRefs, saves.size(), creates.stream()
                        .filter(it -> !isSameRef(it.getOrgMnoRef(), it.getMnoRef()))
                        .mapToInt(it -> Optional.ofNullable(it.getQuantity()).orElse(0))
                        .sum());
        log.debug("自动拆单保存记录明细：{}", creates.stream()
                .map(ZhurongButNestingPartsSplitRecordsController::describeCreateRecord)
                .toList());
        boolean batch = service.saveBatch(saves);
        log.info("自动拆单保存完成，订单：{}，保存记录数：{}，保存结果：{}", orgMnoRefs, saves.size(), batch);
        return ApiResponse.success(batch);
    }

    private static String normalizeRef(String ref) {
        return ref == null ? "" : ref.trim().toUpperCase(Locale.ROOT);
    }

    private static boolean isSameRef(String left, String right) {
        return normalizeRef(left).equals(normalizeRef(right));
    }

    private static int calculateDetachableQuantity(MmnnMmoo00000300 order) {
        if (order.getRQ() == null || order.getMinQuan() == null) {
            return 0;
        }
        return (int) Math.floor(order.getRQ() - order.getMinQuan());
    }

    private static int safeQuantity(DisNestNest00000500 nestPart) {
        return Optional.ofNullable(nestPart.getQuantity()).orElse(0);
    }

    private static Integer safeRecId(DisNestNest00000500 nestPart) {
        return Optional.ofNullable(nestPart.getRecID()).orElse(Integer.MAX_VALUE);
    }

    private static void appendOrderSplitRecords(
            List<ZhurongButNestingPartsSplitRecordsCreateDTO> creates,
            List<DisNestNest00000500> values,
            int currentDetachableQuantity
    ) {
        if (CollectionUtils.isEmpty(values) || currentDetachableQuantity <= 0) {
            return;
        }

        Optional<DisNestNest00000500> candidate = values.stream()
                .filter(it -> safeQuantity(it) >= currentDetachableQuantity)
                .min(Comparator.comparingInt(ZhurongButNestingPartsSplitRecordsController::safeQuantity)
                        .thenComparing(ZhurongButNestingPartsSplitRecordsController::safeRecId));

        if (candidate.isPresent()) {
            log.debug("自动拆单命中单条满足策略，本次可拆数量：{}，选中零件：{}",
                    currentDetachableQuantity, describeNestPart(candidate.get()));
            addSplitRecord(creates, candidate.get(), currentDetachableQuantity);
            return;
        }

        int remainingQuantity = currentDetachableQuantity;
        List<DisNestNest00000500> sortedValues = values.stream()
                .filter(it -> safeQuantity(it) > 0)
                .sorted(Comparator.comparingInt(ZhurongButNestingPartsSplitRecordsController::safeQuantity).reversed()
                        .thenComparing(ZhurongButNestingPartsSplitRecordsController::safeRecId))
                .toList();
        log.debug("自动拆单使用多条累加策略，本次可拆数量：{}，候选零件数：{}，候选明细：{}",
                currentDetachableQuantity,
                sortedValues.size(),
                sortedValues.stream().map(ZhurongButNestingPartsSplitRecordsController::describeNestPart).toList());

        for (DisNestNest00000500 nestPart : sortedValues) {
            if (remainingQuantity <= 0) {
                break;
            }
            int splitQuantity = Math.min(safeQuantity(nestPart), remainingQuantity);
            log.debug("自动拆单累加拆分零件，零件：{}，本零件拆出数量：{}，拆分前剩余目标数量：{}",
                    describeNestPart(nestPart), splitQuantity, remainingQuantity);
            addSplitRecord(creates, nestPart, splitQuantity);
            remainingQuantity -= splitQuantity;
        }
        log.debug("自动拆单多条累加策略完成，本次可拆数量：{}，未满足剩余数量：{}",
                currentDetachableQuantity, remainingQuantity);
    }

    private static void addSplitRecord(
            List<ZhurongButNestingPartsSplitRecordsCreateDTO> creates,
            DisNestNest00000500 nestPart,
            int splitQuantity
    ) {
        if (splitQuantity <= 0) {
            return;
        }

        creates.add(buildSplitRecord(nestPart, nestPart.getMnORef() + AUTO_SPLIT_SUFFIX, splitQuantity));
        log.debug("自动拆单生成拆出记录，零件：{}，mnoRef：{}，quantity：{}",
                describeNestPart(nestPart), nestPart.getMnORef() + AUTO_SPLIT_SUFFIX, splitQuantity);

        int remainderQuantity = safeQuantity(nestPart) - splitQuantity;
        if (remainderQuantity > 0) {
            creates.add(buildSplitRecord(nestPart, nestPart.getMnORef(), remainderQuantity));
            log.debug("自动拆单生成原工单余量记录，零件：{}，mnoRef：{}，quantity：{}",
                    describeNestPart(nestPart), nestPart.getMnORef(), remainderQuantity);
        }
    }

    private static ZhurongButNestingPartsSplitRecordsCreateDTO buildSplitRecord(
            DisNestNest00000500 nestPart,
            String mnoRef,
            int quantity
    ) {
        ZhurongButNestingPartsSplitRecordsCreateDTO create = new ZhurongButNestingPartsSplitRecordsCreateDTO();
        create.setNstRef(nestPart.getNstRef());
        create.setMnoRef(mnoRef);
        create.setOrgMnoRef(nestPart.getMnORef());
        create.setOprId(nestPart.getOprID());
        create.setQuantity(quantity);
        create.setRemark(AUTO_SPLIT_REMARK);
        create.setRecId(nestPart.getRecID());
        create.setPrdRef(nestPart.getPrdRefDst());
        return create;
    }

    private static String describeNestPart(DisNestNest00000500 nestPart) {
        return String.format("nstRef=%s,mnoRef=%s,recId=%s,prdRef=%s,oprId=%s,quantity=%s",
                nestPart.getNstRef(),
                nestPart.getMnORef(),
                nestPart.getRecID(),
                nestPart.getPrdRefDst(),
                nestPart.getOprID(),
                nestPart.getQuantity());
    }

    private static String describeCreateRecord(ZhurongButNestingPartsSplitRecordsCreateDTO record) {
        return String.format("nstRef=%s,mnoRef=%s,orgMnoRef=%s,recId=%s,prdRef=%s,oprId=%s,quantity=%s",
                record.getNstRef(),
                record.getMnoRef(),
                record.getOrgMnoRef(),
                record.getRecId(),
                record.getPrdRef(),
                record.getOprId(),
                record.getQuantity());
    }

    @DeleteMapping("remove")
    public ApiResponse
            <Boolean> remove(Long id) {
        boolean remove = service.removeById(id);
        return ApiResponse.success(remove);
    }

    @DeleteMapping("batchRemove")
    public ApiResponse
            <Boolean> batchRemove(@RequestBody List<Long> ids) {
        boolean remove = service.removeByIds(ids);
        return ApiResponse.success(remove);
    }
}
