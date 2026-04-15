package com.zhurong.platform.core.lantek.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.core.lantek.convert.DisNestNest00000300Convert;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000300DTO;
import com.zhurong.platform.core.lantek.dto.RelationLoadPlan;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000300;
import com.zhurong.platform.core.lantek.mapper.DisNestNest00000300Mapper;
import com.zhurong.platform.core.lantek.service.IDisNestNest00000300Service;
import com.zhurong.platform.core.lantek.service.IPprrPprr00000100Service;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000300VO;
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
public class DisNestNest00000300ServiceImpl
        extends ServiceImpl<DisNestNest00000300Mapper, DisNestNest00000300>
        implements IDisNestNest00000300Service {

    private final DisNestNest00000300Convert convert;
    private final IPprrPprr00000100Service pprrPprr00000100Service;


    @Override
    public DisNestNest00000300VO getVOById(Long id) {
        DisNestNest00000300 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(DisNestNest00000300DTO dto) {
        DisNestNest00000300 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, DisNestNest00000300DTO dto) {
        DisNestNest00000300 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }

    @Override
    public List<DisNestNest00000300VO> findRemnantsByNestRefs(List<String> nestRefs, RelationLoadPlan loadPlan) {
        if (CollectionUtils.isEmpty(nestRefs)) {
            return Collections.emptyList();
        }

        List<DisNestNest00000300> rows = this.list(
                Wrappers.lambdaQuery(DisNestNest00000300.class)
                        .in(DisNestNest00000300::getNstRef, nestRefs)
        );

        List<DisNestNest00000300VO> views = convert.toVOList(rows);

        if (loadPlan != null && loadPlan.isIncludePartMaster()) {
            List<String> prdRefs = rows.stream()
                    .map(DisNestNest00000300::getShtRef)
                    .filter(Objects::nonNull)
                    .distinct()
                    .toList();

            // 这里接你现有 Java 单项服务即可
            List<PprrPprr00000100VO> partViews =
                    pprrPprr00000100Service.fetchPartProfiles(prdRefs, loadPlan);

            Map<String, PprrPprr00000100VO> partMap = partViews.stream()
                    .collect(Collectors.toMap(PprrPprr00000100VO::getPrdRef, Function.identity(), (a, b) -> a));

            views.forEach(vo -> vo.setItem(partMap.get(vo.getShtRef())));
        }

        return views;
    }
}
