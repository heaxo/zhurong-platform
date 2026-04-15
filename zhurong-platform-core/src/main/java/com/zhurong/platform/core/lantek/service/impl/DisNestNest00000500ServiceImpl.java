package com.zhurong.platform.core.lantek.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.core.lantek.convert.DisNestNest00000500Convert;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000500DTO;
import com.zhurong.platform.core.lantek.dto.RelationLoadPlan;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000500;
import com.zhurong.platform.core.lantek.mapper.DisNestNest00000500Mapper;
import com.zhurong.platform.core.lantek.service.IDisNestNest00000500Service;
import com.zhurong.platform.core.lantek.service.IPprrPprr00000100Service;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000500VO;
import com.zhurong.platform.core.lantek.vo.PprrPprr00000100VO;
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
public class DisNestNest00000500ServiceImpl
        extends ServiceImpl<DisNestNest00000500Mapper, DisNestNest00000500>
        implements IDisNestNest00000500Service {

    private final DisNestNest00000500Convert convert;
    private final IPprrPprr00000100Service pprrPprr00000100Service;


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

        List<DisNestNest00000500> rows = this.list(
                Wrappers.lambdaQuery(DisNestNest00000500.class)
                        .in(DisNestNest00000500::getNstRef, nestRefs)
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

        return views;
    }
}
