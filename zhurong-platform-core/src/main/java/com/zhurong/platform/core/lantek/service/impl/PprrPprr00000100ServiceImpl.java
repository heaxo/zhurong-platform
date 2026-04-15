package com.zhurong.platform.core.lantek.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.base.constant.OwndConstant;
import com.zhurong.platform.core.lantek.constants.MCode;
import com.zhurong.platform.core.lantek.constants.SysOwndDocDsc;
import com.zhurong.platform.core.lantek.convert.PprrPprr00000100Convert;
import com.zhurong.platform.core.lantek.dto.ItemsDocument;
import com.zhurong.platform.core.lantek.dto.PprrPprr00000100DTO;
import com.zhurong.platform.core.lantek.dto.RelationLoadPlan;
import com.zhurong.platform.core.lantek.dto.SheetPartsAuxiliaryData;
import com.zhurong.platform.core.lantek.entity.DisShprPptt00000200;
import com.zhurong.platform.core.lantek.entity.PprrPprr00000100;
import com.zhurong.platform.core.lantek.entity.SystOwnd00000100;
import com.zhurong.platform.core.lantek.mapper.PprrPprr00000100Mapper;
import com.zhurong.platform.core.lantek.service.IDisShprPptt00000200Service;
import com.zhurong.platform.core.lantek.service.IPprrPprr00000100Service;
import com.zhurong.platform.core.lantek.service.ISystOwnd00000100Service;
import com.zhurong.platform.core.lantek.util.LantekFilePathBuilder;
import com.zhurong.platform.core.lantek.vo.PprrPprr00000100VO;
import com.zhurong.platform.core.properties.LantekConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class PprrPprr00000100ServiceImpl
        extends ServiceImpl<PprrPprr00000100Mapper, PprrPprr00000100>
        implements IPprrPprr00000100Service {

    private final PprrPprr00000100Convert convert;
    private final ISystOwnd00000100Service systOwnd00000100Service;
    private final IDisShprPptt00000200Service disShprPptt00000200Service;

    private final LantekConfigProperties lantekConfigProperties;


    @Override
    public PprrPprr00000100VO getVOById(Long id) {
        PprrPprr00000100 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(PprrPprr00000100DTO dto) {
        PprrPprr00000100 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, PprrPprr00000100DTO dto) {
        PprrPprr00000100 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }

    @Override
    public List<PprrPprr00000100VO> fetchPartProfiles(List<String> productRefs, RelationLoadPlan plan) {
        if (CollectionUtils.isEmpty(productRefs)) {
            return Collections.emptyList();
        }

        List<PprrPprr00000100> entities = this.list(
                Wrappers.lambdaQuery(PprrPprr00000100.class)
                        .in(PprrPprr00000100::getPrdRef, productRefs)
        );

        List<PprrPprr00000100VO> views = convert.toVOList(entities);
        if (CollectionUtils.isEmpty(views)) {
            return views;
        }

        List<String> prdRefs = entities.stream()
                .map(PprrPprr00000100::getPrdRef)
                .filter(StringUtils::isNotBlank)
                .distinct()
                .toList();

        List<Integer> recIds = entities.stream()
                .map(PprrPprr00000100::getRecID)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        if (plan != null && plan.isIncludePartExtra()) {
            Map<String, SheetPartsAuxiliaryData> metricMap = assemblePartMetrics(prdRefs).stream()
                    .collect(Collectors.toMap(SheetPartsAuxiliaryData::getPrdRef, Function.identity(), (a, b) -> a));

            views.forEach(vo -> vo.setSheetPartsAuxiliaryData(metricMap.get(vo.getPrdRef())));
        }

        if (plan != null && plan.isIncludePartFiles()) {
            Map<Integer, ItemsDocument> docMap = assemblePartFiles(recIds).stream()
                    .collect(Collectors.toMap(ItemsDocument::getRecID, Function.identity(), (a, b) -> a));

            views.forEach(vo -> vo.setItemsDocument(docMap.get(vo.getRecID())));
        }

        return views;
    }

    /**
     * 单项辅助数据
     */
    public List<SheetPartsAuxiliaryData> assemblePartMetrics(List<String> productRefs) {
        if (CollectionUtils.isEmpty(productRefs)) {
            return Collections.emptyList();
        }

        List<DisShprPptt00000200> rows = disShprPptt00000200Service.list(
                Wrappers.lambdaQuery(DisShprPptt00000200.class)
                        .in(DisShprPptt00000200::getPartRef, productRefs)
        );

        Map<String, List<DisShprPptt00000200>> grouped = rows.stream()
                .filter(it -> StringUtils.isNotBlank(it.getPartRef()))
                .collect(Collectors.groupingBy(DisShprPptt00000200::getPartRef));

        if (grouped.isEmpty()) {
            return Collections.emptyList();
        }

        return grouped.entrySet().stream().map(entry -> {
            String partRef = entry.getKey();
            List<DisShprPptt00000200> metrics = entry.getValue();

            SheetPartsAuxiliaryData vo = new SheetPartsAuxiliaryData();
            vo.setPrdRef(partRef);
            vo.setDisplacementLength(readMetric(metrics, MCode.DisShprPptt00000200.DisplacementLengthCode));
            vo.setNumberOfPunches(readMetric(metrics, MCode.DisShprPptt00000200.NumberOfPunchesCode));
            vo.setPartNumberOfPunches(readMetric(metrics, MCode.DisShprPptt00000200.PartNumberOfPunchesCode));
            vo.setDisplacementDuration(readMetric(metrics, MCode.DisShprPptt00000200.DisplacementDurationCode));
            vo.setPartPunchesDuration(readMetric(metrics, MCode.DisShprPptt00000200.PartPunchesDurationCode));
            vo.setPartPerforationDuration(readMetric(metrics, MCode.DisShprPptt00000200.PartPerforationDurationCode));
            vo.setTotalProcessingTime(readMetric(metrics, MCode.DisShprPptt00000200.TotalProcessingTimeCode));
            vo.setCostOfMaterial(readMetric(metrics, MCode.DisShprPptt00000200.CostOfMaterialCode));
            vo.setCostOfMachineTime(readMetric(metrics, MCode.DisShprPptt00000200.CostOfMachineTimeCode));
            vo.setCostOfConsumable(readMetric(metrics, MCode.DisShprPptt00000200.CostOfConsumableCode));
            vo.setCostOfPlanning(readMetric(metrics, MCode.DisShprPptt00000200.CostOfPlanningCode));
            vo.setTotalCost(readMetric(metrics, MCode.DisShprPptt00000200.TotalCostCode));
            vo.setPartRectangularWeightWithOffset(readMetric(metrics, MCode.DisShprPptt00000200.PartRectangularWeightWithOffsetCode));
            vo.setExternalWeightWithOffset(readMetric(metrics, MCode.DisShprPptt00000200.ExternalWeightWithOffsetCode));
            vo.setRealWeightWithOffset(readMetric(metrics, MCode.DisShprPptt00000200.RealWeightWithOffsetCode));
            vo.setOffset(readMetric(metrics, MCode.DisShprPptt00000200.OffsetCode));
            vo.setMinimumRadiusForHoles(readMetric(metrics, MCode.DisShprPptt00000200.MinimumRadiusForHolesCode));
            vo.setMaximumPartAreaToBeIncreasedPercentage(readMetric(metrics, MCode.DisShprPptt00000200.MaximumPartAreaToBeIncreasedPercentageCode));
            return vo;
        }).toList();
    }

    /**
     * 单项文档
     */
    public List<ItemsDocument> assemblePartFiles(List<Integer> recIds) {
        if (CollectionUtils.isEmpty(recIds)) {
            return Collections.emptyList();
        }

        List<SystOwnd00000100> docs = systOwnd00000100Service.list(
                Wrappers.lambdaQuery(SystOwnd00000100.class)
                        .eq(SystOwnd00000100::getTblRef, OwndConstant.TblRef.PART)
                        .in(SystOwnd00000100::getRecordID, recIds)
        );

        Map<Integer, List<SystOwnd00000100>> grouped = docs.stream()
                .filter(it -> it.getRecordID() != null)
                .collect(Collectors.groupingBy(SystOwnd00000100::getRecordID));

        return grouped.entrySet().stream().map(entry -> {
            Integer recId = entry.getKey();
            List<SystOwnd00000100> fileRows = entry.getValue();

            ItemsDocument doc = new ItemsDocument();
            doc.setRecID(recId);
            doc.setBMP(findFileName(fileRows, SysOwndDocDsc.BMP.name()));
            doc.setIMGB(findFileName(fileRows, SysOwndDocDsc.IMGB.name()));
            doc.setMEC(findFileName(fileRows, SysOwndDocDsc.MEC.name()));

            doc.setFullPathBMP(LantekFilePathBuilder.combine(lantekConfigProperties.getInstall(), doc.getBMP()));
            doc.setFullPathIMGB(LantekFilePathBuilder.combine(lantekConfigProperties.getInstall(), doc.getIMGB()));
            doc.setFullPathMEC(LantekFilePathBuilder.combine(lantekConfigProperties.getInstall(), doc.getMEC()));

            return doc;
        }).toList();
    }

    private Double readMetric(List<DisShprPptt00000200> rows, String code) {
        return rows.stream()
                .filter(it -> Objects.equals(it.getMCode(), code))
                .map(DisShprPptt00000200::getDValue)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    private String findFileName(List<SystOwnd00000100> rows, String docType) {
        return rows.stream()
                .filter(it -> Objects.equals(it.getDocDsc(), docType))
                .map(SystOwnd00000100::getFFName)
                .filter(StringUtils::isNotBlank)
                .findFirst()
                .orElse(null);
    }
}
