package com.ao.platform.core.lantek.service.impl;

import com.ao.platform.base.constant.OwndConstant;
import com.ao.platform.base.exception.BusinessException;
import com.ao.platform.core.lantek.convert.DisNestNest00000100Convert;
import com.ao.platform.core.lantek.convert.DisNestNest00000500Convert;
import com.ao.platform.core.lantek.convert.PprrPprr00000100Convert;
import com.ao.platform.core.lantek.dto.DisNestNest00000100DTO;
import com.ao.platform.core.lantek.entity.*;
import com.ao.platform.core.lantek.mapper.DisNestNest00000100Mapper;
import com.ao.platform.core.lantek.service.*;
import com.ao.platform.core.lantek.vo.DisNestNest00000100VO;
import com.ao.platform.core.lantek.vo.DisNestNest00000500VO;
import com.ao.platform.core.lantek.vo.MmnnMmoo00000300VO;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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
    private final ISystOwnd00000100Service systOwnd00000100Service;
    private final IPprrPprr00000100Service pprrPprr00000100Service;
    private final IMmnnMmoo00000300Service mmnnMmoo00000300Service;


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
        vo.setWMFPath(nestWmfOwnd.getFFName());


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
                partWmfOwnd.ifPresent(systOwnd00000100 -> it.setWMFPath(systOwnd00000100.getFFName()));
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
}
