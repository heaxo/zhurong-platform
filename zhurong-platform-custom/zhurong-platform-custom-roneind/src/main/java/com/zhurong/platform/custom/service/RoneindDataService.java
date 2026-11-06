package com.zhurong.platform.custom.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.custom.dto.RoneindRequests;
import com.zhurong.platform.custom.entity.*;
import com.zhurong.platform.custom.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@DS("lantek")
@RequiredArgsConstructor
public class RoneindDataService {
    private final RoneindBasePartMapper basePartMapper;
    private final RoneindManufacturingOrderMapper manufacturingOrderMapper;
    private final RoneindSteelPlateMapper steelPlateMapper;
    private final RoneindImportTaskMapper importTaskMapper;
    private final RoneindNestFeedbackStateMapper nestFeedbackStateMapper;
    private final IPprrPprr00000100Service partService;

    public PageResponse<RoneindBasePart> pageBaseParts(RoneindRequests.BasePartPage query) {
        LambdaQueryWrapper<RoneindBasePart> wrapper = Wrappers.lambdaQuery(RoneindBasePart.class)
                .eq(RoneindBasePart::getInvalidState, false)
                .like(StringUtils.hasText(query.getPrdRef()), RoneindBasePart::getPrdRef, query.getPrdRef())
                .like(StringUtils.hasText(query.getPrdName()), RoneindBasePart::getPrdName, query.getPrdName())
                .like(StringUtils.hasText(query.getDrawingCode()), RoneindBasePart::getDrawingCode, query.getDrawingCode())
                .like(StringUtils.hasText(query.getMatRef()), RoneindBasePart::getMatRef, query.getMatRef())
                .eq(query.getThickness() != null, RoneindBasePart::getThickness, query.getThickness())
                .like(StringUtils.hasText(query.getUdata1()), RoneindBasePart::getUdata1, query.getUdata1())
                .like(StringUtils.hasText(query.getUdata2()), RoneindBasePart::getUdata2, query.getUdata2())
                .like(StringUtils.hasText(query.getUdata3()), RoneindBasePart::getUdata3, query.getUdata3())
                .ge(query.getStartDate() != null, RoneindBasePart::getCreatedAt, query.getStartDate())
                .le(query.getEndDate() != null, RoneindBasePart::getCreatedAt, query.getEndDate())
                .orderByDesc(RoneindBasePart::getCreatedAt);
        Page<RoneindBasePart> page = basePartMapper.selectPage(PageFactory.build(query), wrapper);
        Set<String> maintained = findMaintainedPartRefs(page.getRecords().stream().map(RoneindBasePart::getPrdName).toList());
        page.getRecords().forEach(item -> item.setPartMaintenance(maintained.contains(normalize(item.getPrdName()))));
        return response(page);
    }

    @Transactional(rollbackFor = Exception.class)
    public RoneindBasePart createBasePart(RoneindBasePart item) {
        requireText(item.getPrdRef(), "零件编号不能为空");
        requireText(item.getPrdName(), "零件图号不能为空");
        if (item.getThickness() == null || item.getThickness() <= 0) {
            throw new IllegalArgumentException("厚度必须大于0");
        }
        Long count = basePartMapper.selectCount(Wrappers.lambdaQuery(RoneindBasePart.class)
                .eq(RoneindBasePart::getPrdRef, item.getPrdRef()).or()
                .eq(RoneindBasePart::getPrdName, item.getPrdName()));
        if (count > 0) throw new IllegalArgumentException("零件编号或图号已存在");
        item.setInvalidState(false);
        basePartMapper.insert(item);
        return item;
    }

    public PageResponse<RoneindSteelPlate> pageSteelPlates(RoneindRequests.SteelPlatePage query) {
        LambdaQueryWrapper<RoneindSteelPlate> wrapper = Wrappers.lambdaQuery(RoneindSteelPlate.class)
                .eq(RoneindSteelPlate::getInvalidState, false).ge(RoneindSteelPlate::getQuantity, 1)
                .like(StringUtils.hasText(query.getPrdRef()), RoneindSteelPlate::getPrdRef, query.getPrdRef())
                .like(StringUtils.hasText(query.getPrdName()), RoneindSteelPlate::getPrdName, query.getPrdName())
                .like(StringUtils.hasText(query.getMatRef()), RoneindSteelPlate::getMatRef, query.getMatRef())
                .like(StringUtils.hasText(query.getStockName()), RoneindSteelPlate::getStockName, query.getStockName())
                .like(StringUtils.hasText(query.getLotNumber()), RoneindSteelPlate::getLotNumber, query.getLotNumber())
                .eq(query.getThickness() != null, RoneindSteelPlate::getThickness, query.getThickness())
                .ge(query.getStartDate() != null, RoneindSteelPlate::getCreatedAt, query.getStartDate())
                .le(query.getEndDate() != null, RoneindSteelPlate::getCreatedAt, query.getEndDate())
                .orderByDesc(RoneindSteelPlate::getCreatedAt);
        Page<RoneindSteelPlate> page = steelPlateMapper.selectPage(PageFactory.build(query), wrapper);
        attachTasks(page.getRecords(), RoneindSteelPlate::getLastTaskId, RoneindSteelPlate::setTask);
        return response(page);
    }

    public PageResponse<RoneindManufacturingOrder> pageManufacturingOrders(RoneindRequests.ManufacturingOrderPage query) {
        String matRef = trimToNull(query.getMatRef());
        String cusRef = trimToNull(query.getCusRef());
        Double minThickness = query.getMinThickness();
        Double maxThickness = query.getMaxThickness();
        List<String> prdRefs = null;
        if (StringUtils.hasText(query.getPrdName())) {
            List<RoneindBasePart> baseParts = basePartMapper.selectList(Wrappers.lambdaQuery(RoneindBasePart.class)
                    .eq(RoneindBasePart::getPrdName, query.getPrdName()));
            prdRefs = baseParts.isEmpty() ? null : baseParts.stream().map(RoneindBasePart::getPrdRef).toList();
        }
        LambdaQueryWrapper<RoneindManufacturingOrder> wrapper = Wrappers.lambdaQuery(RoneindManufacturingOrder.class)
                .eq(RoneindManufacturingOrder::getInvalidState, false)
                .like(StringUtils.hasText(query.getProductionOrderNumber()), RoneindManufacturingOrder::getProductionOrderNumber, query.getProductionOrderNumber())
                .like(StringUtils.hasText(query.getProductionOrderErpInternalCode()), RoneindManufacturingOrder::getProductionOrderErpInternalCode, query.getProductionOrderErpInternalCode())
                .like(StringUtils.hasText(query.getProductionWorkshopName()), RoneindManufacturingOrder::getProductionWorkshopName, query.getProductionWorkshopName())
                .in(prdRefs != null, RoneindManufacturingOrder::getPrdRef, prdRefs)
                .like(matRef != null, RoneindManufacturingOrder::getMatRef, matRef)
                .eq(query.getThickness() != null, RoneindManufacturingOrder::getThickness, query.getThickness())
                .ge(minThickness != null, RoneindManufacturingOrder::getThickness, minThickness)
                .le(maxThickness != null, RoneindManufacturingOrder::getThickness, maxThickness)
                .like(StringUtils.hasText(query.getPrdName()), RoneindManufacturingOrder::getPrdName, query.getPrdName())
                .eq(query.getQuantity() != null, RoneindManufacturingOrder::getQuantity, query.getQuantity())
                .like(cusRef != null, RoneindManufacturingOrder::getCusRef, cusRef)
                .like(StringUtils.hasText(query.getWorkCenter()), RoneindManufacturingOrder::getWorkCenter, query.getWorkCenter())
                .eq(query.getSendState() != null, RoneindManufacturingOrder::getSendState, query.getSendState())
                .ge(query.getStartDate() != null, RoneindManufacturingOrder::getCreatedAt, query.getStartDate())
                .le(query.getEndDate() != null, RoneindManufacturingOrder::getCreatedAt, query.getEndDate())
                .orderByAsc(RoneindManufacturingOrder::getReadState)
                .orderByDesc(RoneindManufacturingOrder::getCreatedAt);
        Page<RoneindManufacturingOrder> page = manufacturingOrderMapper.selectPage(PageFactory.build(query), wrapper);
        enrichOrders(page.getRecords());
        return response(page);
    }

    public void enrichOrders(List<RoneindManufacturingOrder> orders) {
        List<String> refs = orders.stream().map(RoneindManufacturingOrder::getPrdRef).filter(Objects::nonNull).distinct().toList();
        List<RoneindBasePart> selectedParts = refs.isEmpty() ? List.of() : basePartMapper.selectList(
                Wrappers.lambdaQuery(RoneindBasePart.class).in(RoneindBasePart::getPrdRef, refs));
        Map<String, RoneindBasePart> parts = selectedParts.stream().collect(Collectors.toMap(
                item -> normalize(item.getPrdRef()), Function.identity(), (left, right) -> left));
        Set<String> maintained = findMaintainedPartRefs(selectedParts.stream().map(RoneindBasePart::getPrdName).toList());
        orders.forEach(order -> {
            RoneindBasePart part = parts.get(normalize(order.getPrdRef()));
            if (part == null) {
                order.setPartMaintenance(null);
                return;
            }
            order.setDrawingCode(part.getDrawingCode());
            order.setPartMaintenance(maintained.contains(normalize(part.getPrdName())));
        });
        attachTasks(orders, RoneindManufacturingOrder::getLastTaskId, RoneindManufacturingOrder::setTask);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchUpdateOrders(List<RoneindRequests.OrderUpdate> updates) {
        if (updates == null || updates.isEmpty()) throw new IllegalArgumentException("更新数据不能为空");
        for (RoneindRequests.OrderUpdate update : updates) {
            if (update.getId() == null) throw new IllegalArgumentException("生产订单ID不能为空");
            RoneindManufacturingOrder entity = new RoneindManufacturingOrder();
            entity.setId(update.getId());
            entity.setWrkRef(update.getWrkRef());
            entity.setJobRef(update.getJobRef());
            entity.setJobName(update.getJobName());
            entity.setInvalidState(update.getInvalidState());
            if (manufacturingOrderMapper.updateById(entity) != 1) {
                throw new IllegalStateException("生产订单更新失败: " + update.getId());
            }
        }
    }

    public List<RoneindBasePart> basePartsByIds(List<Long> ids) { return selectByIds(basePartMapper, ids); }
    public List<RoneindBasePart> findBasePartsByRefs(Collection<String> refs) {
        if (refs == null || refs.isEmpty()) return List.of();
        return basePartMapper.selectList(Wrappers.lambdaQuery(RoneindBasePart.class).in(RoneindBasePart::getPrdRef, refs));
    }
    public List<RoneindSteelPlate> steelPlatesByIds(List<Long> ids) { return selectByIds(steelPlateMapper, ids); }
    public List<RoneindManufacturingOrder> ordersByIds(List<Long> ids) { return selectByIds(manufacturingOrderMapper, ids); }
    public RoneindImportTask taskById(Long id) { return id == null ? null : importTaskMapper.selectById(id); }

    public List<RoneindImportTask> pendingTasks() {
        return importTaskMapper.selectPage(new Page<>(1, 1), Wrappers.lambdaQuery(RoneindImportTask.class)
                .in(RoneindImportTask::getStatus, "PENDING", "RETRY")
                .lt(RoneindImportTask::getAttempts, 3)
                .orderByAsc(RoneindImportTask::getCreatedAt)).getRecords();
    }

    public int insertTask(RoneindImportTask task) { return importTaskMapper.insert(task); }
    public int updateTask(RoneindImportTask task) { return importTaskMapper.updateById(task); }
    public int updateOrder(RoneindManufacturingOrder order) { return manufacturingOrderMapper.updateById(order); }
    public int deleteOrderByProductionOrderERPInternalCode(String productionOrderERPInternalCode) {
        return manufacturingOrderMapper.delete(Wrappers.lambdaQuery(RoneindManufacturingOrder.class)
            .eq(RoneindManufacturingOrder::getProductionOrderErpInternalCode, productionOrderERPInternalCode));
    }
    public int deleteOrderByProductionOrderERPInternalCodes(List<String> productionOrderERPInternalCodes) {
        return manufacturingOrderMapper.delete(Wrappers.lambdaQuery(RoneindManufacturingOrder.class)
            .in(RoneindManufacturingOrder::getProductionOrderErpInternalCode, productionOrderERPInternalCodes));
    }
    public int updateSteelPlate(RoneindSteelPlate plate) { return steelPlateMapper.updateById(plate); }
    public int insertSteelPlate(RoneindSteelPlate plate) { return steelPlateMapper.insert(plate); }
    public int updateSteelPlateById(RoneindSteelPlate plate) { return steelPlateMapper.updateById(plate); }
    public List<RoneindSteelPlate> findSteelPlatesByRefs(Collection<String> refs) {
        if (refs == null || refs.isEmpty()) return List.of();
        return steelPlateMapper.selectList(Wrappers.lambdaQuery(RoneindSteelPlate.class).in(RoneindSteelPlate::getPrdRef, refs));
    }
    public RoneindSteelPlate steelPlateByPrdRef(String ref) {
        return steelPlateMapper.selectOne(Wrappers.lambdaQuery(RoneindSteelPlate.class).eq(RoneindSteelPlate::getPrdRef, ref));
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteBaseParts(List<Long> ids) { deleteByIds(basePartMapper, ids); }

    @Transactional(rollbackFor = Exception.class)
    public void deleteSteelPlates(List<Long> ids) { deleteByIds(steelPlateMapper, ids); }

    public RoneindNestFeedbackState feedbackState(Integer recId) {
        return nestFeedbackStateMapper.selectOne(Wrappers.lambdaQuery(RoneindNestFeedbackState.class)
                .eq(RoneindNestFeedbackState::getNestRecId, recId));
    }

    public List<RoneindNestFeedbackState> feedbackStates(Collection<Integer> recIds) {
        if (recIds == null || recIds.isEmpty()) return List.of();
        return nestFeedbackStateMapper.selectList(Wrappers.lambdaQuery(RoneindNestFeedbackState.class)
                .in(RoneindNestFeedbackState::getNestRecId, recIds));
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveFeedbackState(Integer recId, String nstRef, boolean sent, String remark) {
        RoneindNestFeedbackState state = feedbackState(recId);
        if (state == null) {
            state = new RoneindNestFeedbackState();
            state.setNestRecId(recId);
            state.setNstRef(nstRef);
            state.setSent(sent);
            state.setSentAt(sent ? LocalDateTime.now() : null);
            state.setRemark(remark);
            nestFeedbackStateMapper.insert(state);
            return;
        }
        state.setSent(sent);
        state.setSentAt(sent ? LocalDateTime.now() : state.getSentAt());
        state.setRemark(remark);
        nestFeedbackStateMapper.updateById(state);
    }

    private Set<String> findMaintainedPartRefs(Collection<String> refs) {
        if (refs == null || refs.isEmpty()) return Set.of();
        return partService.list(Wrappers.lambdaQuery(PprrPprr00000100.class)
                        .in(PprrPprr00000100::getPrdRef, refs)).stream()
                .map(PprrPprr00000100::getPrdRef).filter(Objects::nonNull)
                .map(RoneindDataService::normalize).collect(Collectors.toSet());
    }

    private <T> void attachTasks(List<T> records, Function<T, Long> idGetter,
                                 java.util.function.BiConsumer<T, RoneindImportTask> setter) {
        List<Long> ids = records.stream().map(idGetter).filter(Objects::nonNull).distinct().toList();
        if (ids.isEmpty()) return;
        Map<Long, RoneindImportTask> tasks = importTaskMapper.selectBatchIds(ids).stream()
                .collect(Collectors.toMap(RoneindImportTask::getId, Function.identity()));
        records.forEach(item -> setter.accept(item, tasks.get(idGetter.apply(item))));
    }

    private static <T> PageResponse<T> response(Page<T> page) {
        return new PageResponse<>(page.getRecords(), page.getTotal(), page.getCurrent(), page.getSize());
    }

    private static <T> List<T> selectByIds(BaseMapper<T> mapper, List<Long> ids) {
        return ids == null || ids.isEmpty() ? List.of() : mapper.selectBatchIds(ids);
    }

    private static <T> void deleteByIds(BaseMapper<T> mapper, List<Long> ids) {
        if (ids == null || ids.isEmpty()) throw new IllegalArgumentException("ID不能为空");
        mapper.deleteByIds(ids);
    }

    private static void requireText(String value, String message) {
        if (!StringUtils.hasText(value)) throw new IllegalArgumentException(message);
    }

    private static String trimToNull(String value) {
        return StringUtils.hasText(value) ? value.trim() : null;
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toUpperCase(Locale.ROOT);
    }
}
