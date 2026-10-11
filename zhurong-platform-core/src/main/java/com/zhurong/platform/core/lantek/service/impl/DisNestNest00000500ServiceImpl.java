package com.zhurong.platform.core.lantek.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.core.lantek.constants.MCode;
import com.zhurong.platform.core.lantek.convert.DisNestNest00000500Convert;
import com.zhurong.platform.core.lantek.convert.MmnnMmoo00000300Convert;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000500DTO;
import com.zhurong.platform.core.lantek.dto.RelationLoadPlan;
import com.zhurong.platform.core.lantek.dto.SheetPartsAuxiliaryData;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000100;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000500;
import com.zhurong.platform.core.lantek.entity.DisShprPptt00000200;
import com.zhurong.platform.core.lantek.entity.MmnnMmoo00000300;
import com.zhurong.platform.core.lantek.mapper.DisNestNest00000100Mapper;
import com.zhurong.platform.core.lantek.mapper.DisNestNest00000500Mapper;
import com.zhurong.platform.core.lantek.service.IDisNestNest00000500Service;
import com.zhurong.platform.core.lantek.service.IDisShprPptt00000200Service;
import com.zhurong.platform.core.lantek.service.IMmnnMmoo00000300Service;
import com.zhurong.platform.core.lantek.service.IPprrPprr00000100Service;
import com.zhurong.platform.core.util.SqlServerInClauseBatcher;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000500VO;
import com.zhurong.platform.core.lantek.vo.MmnnMmoo00000300VO;
import com.zhurong.platform.core.lantek.vo.PprrPprr00000100VO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class DisNestNest00000500ServiceImpl
        extends ServiceImpl<DisNestNest00000500Mapper, DisNestNest00000500>
        implements IDisNestNest00000500Service {

    private static final List<String> PART_METRIC_CODES = List.of(
            MCode.DisShprPptt00000200.DisplacementLengthCode,
            MCode.DisShprPptt00000200.NumberOfPunchesCode,
            MCode.DisShprPptt00000200.PartNumberOfPunchesCode,
            MCode.DisShprPptt00000200.DisplacementDurationCode,
            MCode.DisShprPptt00000200.PartPunchesDurationCode,
            MCode.DisShprPptt00000200.PartPerforationDurationCode,
            MCode.DisShprPptt00000200.TotalProcessingTimeCode,
            MCode.DisShprPptt00000200.CostOfMaterialCode,
            MCode.DisShprPptt00000200.CostOfMachineTimeCode,
            MCode.DisShprPptt00000200.CostOfConsumableCode,
            MCode.DisShprPptt00000200.CostOfPlanningCode,
            MCode.DisShprPptt00000200.TotalCostCode,
            MCode.DisShprPptt00000200.PartRectangularWeightWithOffsetCode,
            MCode.DisShprPptt00000200.ExternalWeightWithOffsetCode,
            MCode.DisShprPptt00000200.RealWeightWithOffsetCode,
            MCode.DisShprPptt00000200.OffsetCode,
            MCode.DisShprPptt00000200.MinimumRadiusForHolesCode,
            MCode.DisShprPptt00000200.MaximumPartAreaToBeIncreasedPercentageCode,
            MCode.DisShprPptt00000200.MarkingLengthCode,
            MCode.DisShprPptt00000200.NumberOfMarkingTimesCode,
            MCode.DisShprPptt00000200.MarkingDurationCode
    );

    private final DisNestNest00000500Convert convert;
    private final MmnnMmoo00000300Convert mmnnMmoo00000300Convert;
    private final DisNestNest00000100Mapper disNestNest00000100Mapper;
    private final IPprrPprr00000100Service pprrPprr00000100Service;
    private final IDisShprPptt00000200Service disShprPptt00000200Service;
    private final IMmnnMmoo00000300Service mmnnMmoo00000300Service;


    @Override
    public DisNestNest00000500VO getVOById(Long id) {
        DisNestNest00000500 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(DisNestNest00000500DTO dto) {
        DisNestNest00000500 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, DisNestNest00000500DTO dto) {
        DisNestNest00000500 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
    @Override
    public List<DisNestNest00000500VO> findPartRowsByNestRefs(List<String> nestRefs, RelationLoadPlan loadPlan) {
        if (CollectionUtils.isEmpty(nestRefs)) {
            return Collections.emptyList();
        }

        List<DisNestNest00000500> rows = SqlServerInClauseBatcher.listByIn(
                this,
                Wrappers.lambdaQuery(DisNestNest00000500.class),
                DisNestNest00000500::getNstRef,
                nestRefs
        );

        List<DisNestNest00000500VO> views = convert.toVOList(rows);

        if (loadPlan != null && loadPlan.isIncludePartMaster()) {
            List<String> prdRefs = rows.stream()
                    .map(DisNestNest00000500::getPrdRefDst)
                    .filter(Objects::nonNull)
                    .distinct()
                    .toList();

            List<PprrPprr00000100VO> partViews =
                    pprrPprr00000100Service.fetchPartProfiles(prdRefs, loadPlan);

            Map<String, PprrPprr00000100VO> partMap = partViews.stream()
                    .collect(Collectors.toMap(PprrPprr00000100VO::getPrdRef, Function.identity(), (a, b) -> a));

            views.forEach(vo -> vo.setItem(partMap.get(vo.getPrdRefDst())));
        }

        if (loadPlan != null && loadPlan.isIncludeNestPartMetrics()) {
            fillNestPartMetrics(rows, views);
        }

        if (loadPlan != null && loadPlan.isIncludePlanMaster()) {
            List<String> mnoRefs = rows.stream()
                    .map(DisNestNest00000500::getMnORef)
                    .filter(Objects::nonNull)
                    .distinct()
                    .toList();
            List<Integer> oprIds = rows.stream()
                    .map(DisNestNest00000500::getOprID)
                    .filter(Objects::nonNull)
                    .distinct()
                    .toList();

            List<MmnnMmoo00000300> list = SqlServerInClauseBatcher.listByTwoIn(
                    (mnoBatch, oprBatch) -> mmnnMmoo00000300Service.list(Wrappers.lambdaQuery(MmnnMmoo00000300.class)
                            .in(MmnnMmoo00000300::getMnORef, mnoBatch)
                            .in(MmnnMmoo00000300::getOprID, oprBatch)),
                    mnoRefs,
                    oprIds);

            record GroupKey(String mnoRef,Integer oprId){}
            Map<GroupKey, MmnnMmoo00000300VO> workOrderMap = list.stream()
                    .collect(Collectors.toMap(it -> new GroupKey(it.getMnORef(),it.getOprID()), mmnnMmoo00000300Convert::toVO, (a, b) -> a));

            views.forEach(vo -> vo.setWorkOrder(workOrderMap.get(new GroupKey(vo.getMnORef(),vo.getOprID()))));
        }

        return views;
    }

    private void fillNestPartMetrics(List<DisNestNest00000500> rows, List<DisNestNest00000500VO> views) {
        if (CollectionUtils.isEmpty(rows) || CollectionUtils.isEmpty(views)) {
            return;
        }

        List<String> prdRefs = rows.stream()
                .map(DisNestNest00000500::getPrdRefDst)
                .filter(StringUtils::isNotBlank)
                .distinct()
                .toList();
        List<String> nstRefs = rows.stream()
                .map(DisNestNest00000500::getNstRef)
                .filter(StringUtils::isNotBlank)
                .distinct()
                .toList();

        if (CollectionUtils.isEmpty(prdRefs) || CollectionUtils.isEmpty(nstRefs)) {
            return;
        }

        Map<String, DisNestNest00000100> nestMap = listNestMasters(nstRefs).stream()
                .filter(it -> StringUtils.isNotBlank(it.getNstRef()))
                .collect(Collectors.toMap(it -> normalizeKey(it.getNstRef()), Function.identity(), (a, b) -> a));

        List<DisShprPptt00000200> metricRows = SqlServerInClauseBatcher.listByIn(
                disShprPptt00000200Service,
                Wrappers.lambdaQuery(DisShprPptt00000200.class),
                DisShprPptt00000200::getPartRef,
                prdRefs
        );

        if (CollectionUtils.isEmpty(metricRows)) {
            return;
        }

        Map<NestPartMetricKey, SheetPartsAuxiliaryData> metricMap = metricRows.stream()
                .filter(it -> StringUtils.isNotBlank(it.getPartRef()))
                .filter(it -> PART_METRIC_CODES.contains(it.getMCode()))
                .collect(Collectors.groupingBy(it -> metricKey(
                        it.getPartRef(),
                        it.getWrkRef(),
                        it.getMatRef(),
                        it.getThickness()
                )))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> buildNestPartMetrics(entry.getValue())));

        views.forEach(vo -> {
            DisNestNest00000100 nest = nestMap.get(normalizeKey(vo.getNstRef()));
            if (nest == null) {
                return;
            }

            SheetPartsAuxiliaryData metrics = metricMap.get(metricKey(
                    vo.getPrdRefDst(),
                    nest.getWrkRef(),
                    nest.getMatRef(),
                    nest.getSThickness()
            ));
            if (metrics == null) {
                return;
            }

            if (vo.getItem() != null) {
                vo.getItem().setSheetPartsAuxiliaryData(metrics);
            }

            PprrPprr00000100VO meta = vo.getMeta();
            if (meta == null) {
                meta = new PprrPprr00000100VO();
                meta.setPrdRef(vo.getPrdRefDst());
                vo.setMeta(meta);
            }
            meta.setSheetPartsAuxiliaryData(metrics);
        });
    }

    private List<DisNestNest00000100> listNestMasters(List<String> nestRefs) {
        List<DisNestNest00000100> result = new ArrayList<>();
        SqlServerInClauseBatcher.forEachBatch(nestRefs, batch -> result.addAll(
                disNestNest00000100Mapper.selectList(Wrappers.lambdaQuery(DisNestNest00000100.class)
                        .in(DisNestNest00000100::getNstRef, batch))
        ));
        return result;
    }

    private SheetPartsAuxiliaryData buildNestPartMetrics(List<DisShprPptt00000200> rows) {
        DisShprPptt00000200 first = rows.getFirst();

        SheetPartsAuxiliaryData metrics = new SheetPartsAuxiliaryData();
        metrics.setPrdRef(first.getPartRef());
        metrics.setWrkRef(first.getWrkRef());
        metrics.setMatRef(first.getMatRef());
        metrics.setThickness(first.getThickness());
        metrics.setDisplacementLength(readMetric(rows, MCode.DisShprPptt00000200.DisplacementLengthCode));
        metrics.setNumberOfPunches(readMetric(rows, MCode.DisShprPptt00000200.NumberOfPunchesCode));
        metrics.setPartNumberOfPunches(readMetric(rows, MCode.DisShprPptt00000200.PartNumberOfPunchesCode));
        metrics.setDisplacementDuration(readMetric(rows, MCode.DisShprPptt00000200.DisplacementDurationCode));
        metrics.setPartPunchesDuration(readMetric(rows, MCode.DisShprPptt00000200.PartPunchesDurationCode));
        metrics.setPartPerforationDuration(readMetric(rows, MCode.DisShprPptt00000200.PartPerforationDurationCode));
        metrics.setTotalProcessingTime(readMetric(rows, MCode.DisShprPptt00000200.TotalProcessingTimeCode));
        metrics.setCostOfMaterial(readMetric(rows, MCode.DisShprPptt00000200.CostOfMaterialCode));
        metrics.setCostOfMachineTime(readMetric(rows, MCode.DisShprPptt00000200.CostOfMachineTimeCode));
        metrics.setCostOfConsumable(readMetric(rows, MCode.DisShprPptt00000200.CostOfConsumableCode));
        metrics.setCostOfPlanning(readMetric(rows, MCode.DisShprPptt00000200.CostOfPlanningCode));
        metrics.setTotalCost(readMetric(rows, MCode.DisShprPptt00000200.TotalCostCode));
        metrics.setPartRectangularWeightWithOffset(readMetric(rows, MCode.DisShprPptt00000200.PartRectangularWeightWithOffsetCode));
        metrics.setExternalWeightWithOffset(readMetric(rows, MCode.DisShprPptt00000200.ExternalWeightWithOffsetCode));
        metrics.setRealWeightWithOffset(readMetric(rows, MCode.DisShprPptt00000200.RealWeightWithOffsetCode));
        metrics.setOffset(readMetric(rows, MCode.DisShprPptt00000200.OffsetCode));
        metrics.setMinimumRadiusForHoles(readMetric(rows, MCode.DisShprPptt00000200.MinimumRadiusForHolesCode));
        metrics.setMaximumPartAreaToBeIncreasedPercentage(readMetric(rows, MCode.DisShprPptt00000200.MaximumPartAreaToBeIncreasedPercentageCode));
        metrics.setMarkingLength(readMetric(rows, MCode.DisShprPptt00000200.MarkingLengthCode));
        metrics.setNumberOfMarkingTimes(readMetric(rows, MCode.DisShprPptt00000200.NumberOfMarkingTimesCode));
        metrics.setMarkingDuration(readMetric(rows, MCode.DisShprPptt00000200.MarkingDurationCode));
        return metrics;
    }

    private Double readMetric(List<DisShprPptt00000200> rows, String code) {
        return rows.stream()
                .filter(it -> Objects.equals(it.getMCode(), code))
                .map(DisShprPptt00000200::getDValue)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    private NestPartMetricKey metricKey(String prdRef, String wrkRef, String matRef, Double thickness) {
        return new NestPartMetricKey(
                normalizeKey(prdRef),
                normalizeKey(wrkRef),
                normalizeKey(matRef),
                normalizeNumber(thickness)
        );
    }

    private String normalizeKey(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }

    private String normalizeNumber(Double value) {
        return value == null ? "" : BigDecimal.valueOf(value).stripTrailingZeros().toPlainString();
    }

    private record NestPartMetricKey(String prdRef, String wrkRef, String matRef, String thickness) {
    }
}
