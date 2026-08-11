package com.zhurong.platform.custom.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.custom.dto.XyRequests;
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
public class XyDataService {
    private final XyBasePartMapper basePartMapper;
    private final XyManufacturingOrderMapper manufacturingOrderMapper;
    private final XySteelPlateMapper steelPlateMapper;
    private final XyImportTaskMapper importTaskMapper;
    private final XyNestFeedbackStateMapper nestFeedbackStateMapper;
    private final IPprrPprr00000100Service partService;

    public PageResponse<XyBasePart> pageBaseParts(XyRequests.BasePartPage query) {
        LambdaQueryWrapper<XyBasePart> wrapper = Wrappers.lambdaQuery(XyBasePart.class)
                .eq(XyBasePart::getInvalidState, false)
                .like(StringUtils.hasText(query.getPrdRef()), XyBasePart::getPrdRef, query.getPrdRef())
                .like(StringUtils.hasText(query.getPrdName()), XyBasePart::getPrdName, query.getPrdName())
                .like(StringUtils.hasText(query.getDrawingCode()), XyBasePart::getDrawingCode, query.getDrawingCode())
                .like(StringUtils.hasText(query.getMatRef()), XyBasePart::getMatRef, query.getMatRef())
                .eq(query.getThickness() != null, XyBasePart::getThickness, query.getThickness())
                .like(StringUtils.hasText(query.getUdata1()), XyBasePart::getUdata1, query.getUdata1())
                .like(StringUtils.hasText(query.getUdata2()), XyBasePart::getUdata2, query.getUdata2())
                .like(StringUtils.hasText(query.getUdata3()), XyBasePart::getUdata3, query.getUdata3())
                .ge(query.getStartDate() != null, XyBasePart::getCreatedAt, query.getStartDate())
                .le(query.getEndDate() != null, XyBasePart::getCreatedAt, query.getEndDate())
                .orderByDesc(XyBasePart::getCreatedAt);
        Page<XyBasePart> page = basePartMapper.selectPage(PageFactory.build(query), wrapper);
        Set<String> maintained = findMaintainedPartRefs(page.getRecords().stream().map(XyBasePart::getDrawingCode).toList());
        page.getRecords().forEach(item -> item.setPartMaintenance(maintained.contains(normalize(item.getDrawingCode()))));
        return response(page);
    }

    @Transactional(rollbackFor = Exception.class)
    public XyBasePart createBasePart(XyBasePart item) {
        requireText(item.getPrdRef(), "零件编号不能为空");
        requireText(item.getDrawingCode(), "零件图号不能为空");
        if (item.getThickness() == null || item.getThickness() <= 0) {
            throw new IllegalArgumentException("厚度必须大于0");
        }
        Long count = basePartMapper.selectCount(Wrappers.lambdaQuery(XyBasePart.class)
                .eq(XyBasePart::getPrdRef, item.getPrdRef()).or()
                .eq(XyBasePart::getDrawingCode, item.getDrawingCode()));
        if (count > 0) throw new IllegalArgumentException("零件编号或图号已存在");
        item.setInvalidState(false);
        basePartMapper.insert(item);
        return item;
    }

    public PageResponse<XySteelPlate> pageSteelPlates(XyRequests.SteelPlatePage query) {
        LambdaQueryWrapper<XySteelPlate> wrapper = Wrappers.lambdaQuery(XySteelPlate.class)
                .eq(XySteelPlate::getInvalidState, false).ge(XySteelPlate::getQuantity, 1)
                .like(StringUtils.hasText(query.getPrdRef()), XySteelPlate::getPrdRef, query.getPrdRef())
                .like(StringUtils.hasText(query.getPrdName()), XySteelPlate::getPrdName, query.getPrdName())
                .like(StringUtils.hasText(query.getMatRef()), XySteelPlate::getMatRef, query.getMatRef())
                .like(StringUtils.hasText(query.getStockName()), XySteelPlate::getStockName, query.getStockName())
                .like(StringUtils.hasText(query.getLotNumber()), XySteelPlate::getLotNumber, query.getLotNumber())
                .eq(query.getThickness() != null, XySteelPlate::getThickness, query.getThickness())
                .ge(query.getStartDate() != null, XySteelPlate::getCreatedAt, query.getStartDate())
                .le(query.getEndDate() != null, XySteelPlate::getCreatedAt, query.getEndDate())
                .orderByDesc(XySteelPlate::getCreatedAt);
        Page<XySteelPlate> page = steelPlateMapper.selectPage(PageFactory.build(query), wrapper);
        attachTasks(page.getRecords(), XySteelPlate::getLastTaskId, XySteelPlate::setTask);
        return response(page);
    }

    public PageResponse<XyManufacturingOrder> pageManufacturingOrders(XyRequests.ManufacturingOrderPage query) {
        String prdRef = null;
        if (StringUtils.hasText(query.getDrawingCode())) {
            XyBasePart part = basePartMapper.selectOne(Wrappers.lambdaQuery(XyBasePart.class)
                    .eq(XyBasePart::getDrawingCode, query.getDrawingCode()));
            prdRef = part == null ? "__NOT_FOUND__" : part.getPrdRef();
        }
        LambdaQueryWrapper<XyManufacturingOrder> wrapper = Wrappers.lambdaQuery(XyManufacturingOrder.class)
                .eq(XyManufacturingOrder::getInvalidState, false)
                .like(StringUtils.hasText(query.getProductionOrderNumber()), XyManufacturingOrder::getProductionOrderNumber, query.getProductionOrderNumber())
                .like(StringUtils.hasText(query.getProductionOrderErpInternalCode()), XyManufacturingOrder::getProductionOrderErpInternalCode, query.getProductionOrderErpInternalCode())
                .like(StringUtils.hasText(query.getProductionWorkshopName()), XyManufacturingOrder::getProductionWorkshopName, query.getProductionWorkshopName())
                .eq(prdRef != null, XyManufacturingOrder::getPrdRef, prdRef)
                .like(StringUtils.hasText(query.getMatRef()), XyManufacturingOrder::getMatRef, query.getMatRef())
                .eq(query.getThickness() != null, XyManufacturingOrder::getThickness, query.getThickness())
                .like(StringUtils.hasText(query.getPrdName()), XyManufacturingOrder::getPrdName, query.getPrdName())
                .eq(query.getQuantity() != null, XyManufacturingOrder::getQuantity, query.getQuantity())
                .like(StringUtils.hasText(query.getCusRef()), XyManufacturingOrder::getCusRef, query.getCusRef())
                .like(StringUtils.hasText(query.getWorkCenter()), XyManufacturingOrder::getWorkCenter, query.getWorkCenter())
                .eq(query.getSendState() != null, XyManufacturingOrder::getSendState, query.getSendState())
                .ge(query.getStartDate() != null, XyManufacturingOrder::getCreatedAt, query.getStartDate())
                .le(query.getEndDate() != null, XyManufacturingOrder::getCreatedAt, query.getEndDate())
                .orderByAsc(XyManufacturingOrder::getReadState)
                .orderByDesc(XyManufacturingOrder::getCreatedAt);
        Page<XyManufacturingOrder> page = manufacturingOrderMapper.selectPage(PageFactory.build(query), wrapper);
        enrichOrders(page.getRecords());
        return response(page);
    }

    private void enrichOrders(List<XyManufacturingOrder> orders) {
        List<String> refs = orders.stream().map(XyManufacturingOrder::getPrdRef).filter(Objects::nonNull).distinct().toList();
        List<XyBasePart> selectedParts = refs.isEmpty() ? List.of() : basePartMapper.selectList(
                Wrappers.lambdaQuery(XyBasePart.class).in(XyBasePart::getPrdRef, refs));
        Map<String, XyBasePart> parts = selectedParts.stream().collect(Collectors.toMap(
                item -> normalize(item.getPrdRef()), Function.identity(), (left, right) -> left));
        Set<String> maintained = findMaintainedPartRefs(selectedParts.stream().map(XyBasePart::getDrawingCode).toList());
        orders.forEach(order -> {
            XyBasePart part = parts.get(normalize(order.getPrdRef()));
            if (part == null) {
                order.setPartMaintenance(false);
                return;
            }
            order.setDrawingCode(part.getDrawingCode());
            order.setPartMaintenance(maintained.contains(normalize(part.getDrawingCode())));
        });
        attachTasks(orders, XyManufacturingOrder::getLastTaskId, XyManufacturingOrder::setTask);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchUpdateOrders(List<XyRequests.OrderUpdate> updates) {
        if (updates == null || updates.isEmpty()) throw new IllegalArgumentException("更新数据不能为空");
        for (XyRequests.OrderUpdate update : updates) {
            if (update.getId() == null) throw new IllegalArgumentException("生产订单ID不能为空");
            XyManufacturingOrder entity = new XyManufacturingOrder();
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

    public List<XyBasePart> basePartsByIds(List<Long> ids) { return selectByIds(basePartMapper, ids); }
    public List<XyBasePart> findBasePartsByRefs(Collection<String> refs) {
        if (refs == null || refs.isEmpty()) return List.of();
        return basePartMapper.selectList(Wrappers.lambdaQuery(XyBasePart.class).in(XyBasePart::getPrdRef, refs));
    }
    public List<XySteelPlate> steelPlatesByIds(List<Long> ids) { return selectByIds(steelPlateMapper, ids); }
    public List<XyManufacturingOrder> ordersByIds(List<Long> ids) { return selectByIds(manufacturingOrderMapper, ids); }
    public XyImportTask taskById(Long id) { return id == null ? null : importTaskMapper.selectById(id); }

    public List<XyImportTask> pendingTasks() {
        return importTaskMapper.selectPage(new Page<>(1, 1), Wrappers.lambdaQuery(XyImportTask.class)
                .in(XyImportTask::getStatus, "PENDING", "RETRY")
                .lt(XyImportTask::getAttempts, 3)
                .orderByAsc(XyImportTask::getCreatedAt)).getRecords();
    }

    public int insertTask(XyImportTask task) { return importTaskMapper.insert(task); }
    public int updateTask(XyImportTask task) { return importTaskMapper.updateById(task); }
    public int updateOrder(XyManufacturingOrder order) { return manufacturingOrderMapper.updateById(order); }
    public int updateSteelPlate(XySteelPlate plate) { return steelPlateMapper.updateById(plate); }
    public int insertSteelPlate(XySteelPlate plate) { return steelPlateMapper.insert(plate); }
    public int updateSteelPlateById(XySteelPlate plate) { return steelPlateMapper.updateById(plate); }
    public List<XySteelPlate> findSteelPlatesByRefs(Collection<String> refs) {
        if (refs == null || refs.isEmpty()) return List.of();
        return steelPlateMapper.selectList(Wrappers.lambdaQuery(XySteelPlate.class).in(XySteelPlate::getPrdRef, refs));
    }
    public XySteelPlate steelPlateByPrdRef(String ref) {
        return steelPlateMapper.selectOne(Wrappers.lambdaQuery(XySteelPlate.class).eq(XySteelPlate::getPrdRef, ref));
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteBaseParts(List<Long> ids) { deleteByIds(basePartMapper, ids); }

    @Transactional(rollbackFor = Exception.class)
    public void deleteSteelPlates(List<Long> ids) { deleteByIds(steelPlateMapper, ids); }

    public XyNestFeedbackState feedbackState(Integer recId) {
        return nestFeedbackStateMapper.selectOne(Wrappers.lambdaQuery(XyNestFeedbackState.class)
                .eq(XyNestFeedbackState::getNestRecId, recId));
    }

    public List<XyNestFeedbackState> feedbackStates(Collection<Integer> recIds) {
        if (recIds == null || recIds.isEmpty()) return List.of();
        return nestFeedbackStateMapper.selectList(Wrappers.lambdaQuery(XyNestFeedbackState.class)
                .in(XyNestFeedbackState::getNestRecId, recIds));
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveFeedbackState(Integer recId, String nstRef, boolean sent, String remark) {
        XyNestFeedbackState state = feedbackState(recId);
        if (state == null) {
            state = new XyNestFeedbackState();
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
                .map(XyDataService::normalize).collect(Collectors.toSet());
    }

    private <T> void attachTasks(List<T> records, Function<T, Long> idGetter,
                                 java.util.function.BiConsumer<T, XyImportTask> setter) {
        List<Long> ids = records.stream().map(idGetter).filter(Objects::nonNull).distinct().toList();
        if (ids.isEmpty()) return;
        Map<Long, XyImportTask> tasks = importTaskMapper.selectBatchIds(ids).stream()
                .collect(Collectors.toMap(XyImportTask::getId, Function.identity()));
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

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toUpperCase(Locale.ROOT);
    }
}
