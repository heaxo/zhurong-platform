package com.zhurong.platform.core.lantek.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.constant.OwndConstant;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.base.util.PathResolver;
import com.zhurong.platform.core.lantek.constants.MCode;
import com.zhurong.platform.core.lantek.constants.SysOwndDocDsc;
import com.zhurong.platform.core.lantek.convert.DisNestNest00000100Convert;
import com.zhurong.platform.core.lantek.convert.DisNestNest00000500Convert;
import com.zhurong.platform.core.lantek.convert.PprrPprr00000100Convert;
import com.zhurong.platform.core.lantek.dto.*;
import com.zhurong.platform.core.lantek.entity.*;
import com.zhurong.platform.core.lantek.mapper.DisNestNest00000100Mapper;
import com.zhurong.platform.core.lantek.service.*;
import com.zhurong.platform.core.lantek.util.LantekFilePathBuilder;
import com.zhurong.platform.core.lantek.vo.*;
import com.zhurong.platform.core.properties.LantekConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DisNestNest00000100ServiceImpl
        extends ServiceImpl<DisNestNest00000100Mapper, DisNestNest00000100>
        implements IDisNestNest00000100Service {

    private final DisNestNest00000100Convert convert;
    private final DisNestNest00000500Convert disNestNest00000500Convert;
    private final PprrPprr00000100Convert pprrPprr00000100Convert;

    private final IDisNestNest00000500Service disNestNest00000500Service;
    private final IDisNestNest00000200Service disNestNest00000200Service;
    private final IDisNestNest00000300Service disNestNest00000300Service;
    private final IDisMmnnMmoo00000200Service disMmnnMmoo00000200Service;
    private final ISystOwnd00000100Service systOwnd00000100Service;
    private final IPprrPprr00000100Service pprrPprr00000100Service;
    private final IMmnnMmoo00000300Service mmnnMmoo00000300Service;
    private final ISystFfll00000200Service systFfll00000200Service;

    private final LantekConfigProperties lantekConfigProperties;

    @Override
    public DisNestNest00000100VO getVOById(Long id) {
        DisNestNest00000100 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(DisNestNest00000100DTO dto) {
        DisNestNest00000100 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, DisNestNest00000100DTO dto) {
        DisNestNest00000100 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }

    @Override
    public DisNestNest00000100VO details(Integer recID) {
        DisNestNest00000100 entity = getById(recID);

        if (entity == null) {
            throw new BusinessException("套料程序为空异常");
        }

        DisNestNest00000100VO vo = convert.toVO(entity);

        List<DisNestNest00000500> nest500S = disNestNest00000500Service.list(Wrappers.lambdaUpdate(DisNestNest00000500.class)
                .eq(DisNestNest00000500::getNstRef, entity.getNstRef()));
        List<DisNestNest00000500VO> nestParts = disNestNest00000500Convert.toVOList(nest500S);
        vo.setNestParts(nestParts);

        if (CollectionUtils.isEmpty(nest500S)) {
            throw new BusinessException("套料程序零件为空异常");
        }

        String systemVaultPath = systFfll00000200Service.getSystemVaultPath();

        List<String> mnoRefs = nest500S.stream().map(DisNestNest00000500::getMnORef).distinct().toList();
        List<String> prdRefs = nest500S.stream().map(DisNestNest00000500::getPrdRefDst).distinct().toList();


        List<SystOwnd00000100> nestDocs = systOwnd00000100Service.list(Wrappers.lambdaUpdate(SystOwnd00000100.class)
                .eq(SystOwnd00000100::getTblRef, OwndConstant.TblRef.NEST)
                .eq(SystOwnd00000100::getRecordID, entity.getRecID()));

        //获取CNC路径
        Optional<SystOwnd00000100> cncOption = nestDocs.stream().filter(it -> Objects.equals(
                it.getFFType(), OwndConstant.FFtype.CNC)
        ).findFirst();
        SystOwnd00000100 cncOwnd = cncOption.orElseGet(SystOwnd00000100::new);
        log.info("程序CNC路径：{}，{}", entity.getNstRef(), cncOwnd.getFFName());
        vo.setCNCPath(cncOwnd.getFFName());

        //获取WMFPath路径
        Optional<SystOwnd00000100> wmfOption = nestDocs.stream().filter(it -> Objects.equals(
                it.getFFType(), OwndConstant.FFtype.WMF)
        ).findFirst();
        SystOwnd00000100 nestWmfOwnd = wmfOption.orElseGet(SystOwnd00000100::new);
        log.info("程序WMFPath路径：{}，{}", entity.getNstRef(), nestWmfOwnd.getFFName());

        String nestWmfFFName = nestWmfOwnd.getFFName();

        String fullNestWMFPath = PathResolver.resolve(
                systemVaultPath,
                nestWmfFFName
        );

        vo.setWMFPath(nestWmfFFName);
        vo.setFullWMFPath(fullNestWMFPath);


        List<PprrPprr00000100> parts = pprrPprr00000100Service.listByIn(
                Wrappers.lambdaQuery(PprrPprr00000100.class),
                PprrPprr00000100::getPrdRef,
                prdRefs);

        Map<String, PprrPprr00000100> partMap = parts.stream()
                .collect(Collectors.toMap(PprrPprr00000100::getPrdRef, it -> it));

        List<Integer> partRecIds = parts.stream().map(PprrPprr00000100::getRecID).toList();

        List<SystOwnd00000100> partDocs = systOwnd00000100Service.list(Wrappers.lambdaQuery(SystOwnd00000100.class)
                .eq(SystOwnd00000100::getTblRef, OwndConstant.TblRef.PART)
                .in(SystOwnd00000100::getRecordID, partRecIds));

        Map<Integer, List<SystOwnd00000100>> partDocMap = partDocs.stream()
                .collect(Collectors.groupingBy(SystOwnd00000100::getRecordID));

        nestParts.forEach(it -> {

            PprrPprr00000100 pprr = partMap.get(it.getPrdRefDst());
            if (pprr == null) {
                log.warn("零件档案空值警告，{}", it.getPrdRefDst());
                return;
            }
            it.setMeta(pprrPprr00000100Convert.toVO(pprr));
            List<SystOwnd00000100> docs = partDocMap.get(pprr.getRecID());

            if (docs != null) {
                Optional<SystOwnd00000100> partWmfOwnd = docs.stream()
                        .filter(f -> OwndConstant.FFtype.WMF.equals(f.getFFType()))
                        .findFirst();

                log.info("程序零件WMFPath路径：{}，{}", it.getPrdRefDst(), partWmfOwnd.orElseGet(SystOwnd00000100::new).getFFName());
                partWmfOwnd.ifPresent(systOwnd00000100 -> {
                    String partWmfFFName = systOwnd00000100.getFFName();

                    String fullPartWMFPath = PathResolver.resolve(
                            systemVaultPath,
                            partWmfFFName
                    );
                    it.setWMFPath(partWmfFFName);
                    it.setFullWMFPath(fullPartWMFPath);
                });
            }
        });

        //获取生产计划零件信息
        List<MmnnMmoo00000300> mmnnMmoo00000300S = mmnnMmoo00000300Service.listByIn(Wrappers.lambdaQuery(MmnnMmoo00000300.class),
                MmnnMmoo00000300::getMnORef, mnoRefs);
        List<MmnnMmoo00000300VO> jobParts = mmnnMmoo00000300Service.getConvert()
                .toVOList(mmnnMmoo00000300S);
        vo.setJobParts(jobParts);

        return vo;
    }

    @Override
    public PageResponse<DisNestNest00000100VO> pageNestOverview(DisNestNest00000100PageQuery req){
        LambdaQueryWrapper<DisNestNest00000100> wrapper = Wrappers.lambdaQuery();

        if (req.getRecID() != null) {
            wrapper.eq(DisNestNest00000100::getRecID, req.getRecID());
        }
        if (StringUtils.isNotBlank(req.getNstRef())) {
            wrapper.eq(DisNestNest00000100::getNstRef, req.getNstRef());
        }
        if (StringUtils.isNotBlank(req.getJobRef())) {
            wrapper.eq(DisNestNest00000100::getJobRef, req.getJobRef());
        }
        if (StringUtils.isNotBlank(req.getWrkRef())) {
            wrapper.eq(DisNestNest00000100::getWrkRef, req.getWrkRef());
        }
        if (StringUtils.isNotBlank(req.getMatRef())) {
            wrapper.eq(DisNestNest00000100::getMatRef, req.getMatRef());
        }
        if (req.getSThickness() != null) {
            wrapper.eq(DisNestNest00000100::getSThickness, req.getSThickness());
        }

        // 这里如果你项目里已有 QueryOrders 排序工具，直接替换掉下面这行
        wrapper.orderByDesc(DisNestNest00000100::getRecID);

        Page<DisNestNest00000100> page = this.page(
                new Page<>(req.getCurrent(), req.getPageSize()),
                wrapper
        );
        List<DisNestNest00000100> entities = page.getRecords();
        long total = page.getTotal();

        List<DisNestNest00000100VO> views = convert.toVOList(entities);
        if (CollectionUtils.isEmpty(views)) {
            return buildPageResult(views, total, req.getCurrent(), req.getPageSize());
        }

        RelationLoadPlan loadPlan = Optional.ofNullable(req.getLoadPlan()).orElseGet(RelationLoadPlan::new);

        List<String> nestRefs = entities.stream()
                .map(DisNestNest00000100::getNstRef)
                .filter(StringUtils::isNotBlank)
                .toList();

        List<String> jobRefs = entities.stream()
                .map(DisNestNest00000100::getJobRef)
                .filter(StringUtils::isNotBlank)
                .distinct()
                .toList();

        List<Integer> recIds = entities.stream()
                .map(DisNestNest00000100::getRecID)
                .filter(Objects::nonNull)
                .toList();

        if (loadPlan.isIncludeNestMetrics()) {
            Map<String, NestingAuxiliaryProperties> metricMap = collectNestMetrics(nestRefs).stream()
                    .collect(Collectors.toMap(NestingAuxiliaryProperties::getNstRef, Function.identity(), (a, b) -> a));

            views.forEach(vo -> vo.setNestingAuxiliaryProperties(metricMap.get(vo.getNstRef())));
        }

        if (loadPlan.isIncludeNestFiles()) {
            Map<Integer, NestingDocument> docMap = collectNestFiles(recIds).stream()
                    .collect(Collectors.toMap(NestingDocument::getRecID, Function.identity(), (a, b) -> a));

            views.forEach(vo -> vo.setNestingDocument(docMap.get(vo.getRecID())));
        }

        if (loadPlan.isIncludeNestParts()) {
            List<DisNestNest00000500VO> partRows =
                    disNestNest00000500Service.findPartRowsByNestRefs(nestRefs, loadPlan);

            Map<String, List<DisNestNest00000500VO>> partMap = partRows.stream()
                    .collect(Collectors.groupingBy(DisNestNest00000500VO::getNstRef));

            views.forEach(vo -> vo.setNestParts(partMap.getOrDefault(vo.getNstRef(), Collections.emptyList())));
        }

        if (loadPlan.isIncludeNestRemnants()) {
            List<DisNestNest00000300VO> remnants =
                    disNestNest00000300Service.findRemnantsByNestRefs(nestRefs, loadPlan);

            Map<String, List<DisNestNest00000300VO>> remnantMap = remnants.stream()
                    .collect(Collectors.groupingBy(DisNestNest00000300VO::getNstRef));

            views.forEach(vo -> vo.setNestRemnant(remnantMap.getOrDefault(vo.getNstRef(), Collections.emptyList())));
        }

        if (loadPlan.isIncludeJobCard()) {
            List<DisMmnnMmoo00000200VO> jobs =
                    disMmnnMmoo00000200Service.findJobsByRefs(jobRefs, loadPlan);

            Map<String, DisMmnnMmoo00000200VO> jobMap = jobs.stream()
                    .collect(Collectors.toMap(DisMmnnMmoo00000200VO::getJobRef, Function.identity(), (a, b) -> a));

            views.forEach(vo -> vo.setJob(jobMap.get(vo.getJobRef())));
        }

        return buildPageResult(views, total, req.getCurrent(), req.getPageSize());
    }

    /**
     * 套料辅助统计
     */
    public List<NestingAuxiliaryProperties> collectNestMetrics(List<String> nestRefs) {
        if (CollectionUtils.isEmpty(nestRefs)) {
            return Collections.emptyList();
        }

        List<DisNestNest00000200> metricRows = disNestNest00000200Service.list(
                Wrappers.lambdaQuery(DisNestNest00000200.class)
                        .in(DisNestNest00000200::getNstRef, nestRefs)
        );

        Map<String, List<DisNestNest00000200>> groupMap = metricRows.stream()
                .collect(Collectors.groupingBy(DisNestNest00000200::getNstRef));

        return groupMap.entrySet().stream().map(entry -> {
            String nstRef = entry.getKey();
            List<DisNestNest00000200> rows = entry.getValue();

            NestingAuxiliaryProperties vo = new NestingAuxiliaryProperties();
            vo.setNstRef(nstRef);
            vo.setDisplacementLength(pickMetric(rows, MCode.DisNestNest00000200.DisplacementLengthCode));
            vo.setBevelessCuttingLength(pickMetric(rows, MCode.DisNestNest00000200.BevelessCuttingLengthCode));
            vo.setNumberOfPerforations(pickMetric(rows, MCode.DisNestNest00000200.NumberOfPerforationsCode));
            vo.setDisplacementDuration(pickMetric(rows, MCode.DisNestNest00000200.DisplacementDurationCode));
            vo.setNormalCuttingDuration(pickMetric(rows, MCode.DisNestNest00000200.NormalCuttingDurationCode));
            vo.setPerforationDuration(pickMetric(rows, MCode.DisNestNest00000200.PerforationDurationCode));
            vo.setAuxiliarTime(pickMetric(rows, MCode.DisNestNest00000200.AuxiliarTimeCode));
            vo.setTotalProcessingTime(pickMetric(rows, MCode.DisNestNest00000200.TotalProcessingTimeCode));
            vo.setCostOfMaterial(pickMetric(rows, MCode.DisNestNest00000200.CostOfMaterialCode));
            vo.setCostOfMachineTime(pickMetric(rows, MCode.DisNestNest00000200.CostOfMachineTimeCode));
            vo.setCostOfConsumable(pickMetric(rows, MCode.DisNestNest00000200.CostOfConsumableCode));
            vo.setCostOfPlanning(pickMetric(rows, MCode.DisNestNest00000200.CostOfPlanningCode));
            vo.setTotalCost(pickMetric(rows, MCode.DisNestNest00000200.TotalCostCode));
            vo.setSheetUploadAuxiliarTime(pickMetric(rows, MCode.DisNestNest00000200.SheetUploadAuxiliarTimeCode));
            vo.setSheetDownloadAuxiliarTime(pickMetric(rows, MCode.DisNestNest00000200.SheetDownloadAuxiliarTimeCode));
            return vo;
        }).toList();
    }

    /**
     * 套料文档
     */
    public List<NestingDocument> collectNestFiles(List<Integer> recIds) {
        if (CollectionUtils.isEmpty(recIds)) {
            return Collections.emptyList();
        }

        List<SystOwnd00000100> docs = systOwnd00000100Service.list(
                Wrappers.lambdaQuery(SystOwnd00000100.class)
                        .eq(SystOwnd00000100::getTblRef, OwndConstant.TblRef.NEST)
                        .in(SystOwnd00000100::getRecordID, recIds)
        );

        Map<Integer, List<SystOwnd00000100>> groupMap = docs.stream()
                .filter(it -> it.getRecordID() != null)
                .collect(Collectors.groupingBy(SystOwnd00000100::getRecordID));

        return groupMap.entrySet().stream().map(entry -> {
            Integer recId = entry.getKey();
            List<SystOwnd00000100> fileRows = entry.getValue();

            NestingDocument vo = new NestingDocument();
            vo.setRecID(recId);
            vo.setBMP(pickFile(fileRows, SysOwndDocDsc.BMP.name()));
            vo.setCHP(pickFile(fileRows, SysOwndDocDsc.CHP.name()));
            vo.setCNC(pickFile(fileRows, SysOwndDocDsc.CNC.name()));
            vo.setIMGB(pickFile(fileRows, SysOwndDocDsc.IMGB.name()));
            vo.setJOBRPT(pickFile(fileRows, SysOwndDocDsc.JOBRPT.name()));
            vo.setMEC(pickFile(fileRows, SysOwndDocDsc.MEC.name()));
            vo.setXML(pickFile(fileRows, SysOwndDocDsc.XML.name()));

            vo.setFullPathBMP(LantekFilePathBuilder.combine(lantekConfigProperties.getInstall(), vo.getBMP()));
            vo.setFullPathCHP(LantekFilePathBuilder.combine(lantekConfigProperties.getInstall(), vo.getCHP()));
            vo.setFullPathIMGB(LantekFilePathBuilder.combine(lantekConfigProperties.getInstall(), vo.getFullPathIMGB()));
            vo.setFullPathJOBRPT(LantekFilePathBuilder.combine(lantekConfigProperties.getInstall(), vo.getFullPathJOBRPT()));
            vo.setFullPathMEC(LantekFilePathBuilder.combine(lantekConfigProperties.getInstall(), vo.getMEC()));
            vo.setFullPathXML(LantekFilePathBuilder.combine(lantekConfigProperties.getInstall(), vo.getXML()));

            return vo;
        }).toList();
    }

    private double pickMetric(List<DisNestNest00000200> rows, String code) {
        return rows.stream()
                .filter(it -> Objects.equals(it.getMCode(), code))
                .map(DisNestNest00000200::getDValue)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(0D);
    }

    private String pickFile(List<SystOwnd00000100> rows, String docType) {
        return rows.stream()
                .filter(it -> Objects.equals(it.getDocDsc(), docType))
                .map(SystOwnd00000100::getFFName)
                .filter(StringUtils::isNotBlank)
                .findFirst()
                .orElse("");
    }

    private PageResponse<DisNestNest00000100VO> buildPageResult(List<DisNestNest00000100VO> records, long total,
                                                                long current,
                                                                long size) {
        return new PageResponse<>(records,total,current,size);
    }
}
