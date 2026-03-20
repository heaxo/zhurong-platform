package com.ao.platform.core.lantek.controller;

import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;
import com.ao.platform.base.model.PageFactory;
import com.ao.platform.core.lantek.api.IMmnnMmoo00000300Api;
import com.ao.platform.core.lantek.convert.MmnnMmoo00000300Convert;
import com.ao.platform.core.lantek.dto.MmnnMmoo00000300DTO;
import com.ao.platform.core.lantek.dto.MmnnMmoo00000300PageQuery;
import com.ao.platform.core.lantek.entity.MmnnMmoo00000300;
import com.ao.platform.core.lantek.service.IMmnnMmoo00000300Service;
import com.ao.platform.core.lantek.vo.MmnnMmoo00000300VO;
import com.ao.platform.core.web.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/mmnnMmoo00000300")
public class MmnnMmoo00000300Controller extends BaseController implements IMmnnMmoo00000300Api {

    private final MmnnMmoo00000300Convert convert;
    private final IMmnnMmoo00000300Service service;

    @Override
    public ApiResponse
            <PageResponse
                    <MmnnMmoo00000300VO>> page(MmnnMmoo00000300PageQuery pageQuery) {

        LambdaQueryWrapper<MmnnMmoo00000300> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(MmnnMmoo00000300::getRecID);

        if (CollectionUtils.isNotEmpty(pageQuery.getOrdRefs())) {
            wrapper.in(MmnnMmoo00000300::getOrdRef, pageQuery.getOrdRefs());
        }
        if (CollectionUtils.isNotEmpty(pageQuery.getMnoRefs())) {
            wrapper.in(MmnnMmoo00000300::getMnORef, pageQuery.getMnoRefs());
        }

        Page<MmnnMmoo00000300> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <MmnnMmoo00000300VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <MmnnMmoo00000300VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <MmnnMmoo00000300VO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid MmnnMmoo00000300DTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid MmnnMmoo00000300DTO dto) {
        boolean update = service.updateFromDTO(id, dto);
        return ApiResponse.success(update);
    }

    @Override
    public ApiResponse
            <Boolean> remove(Long id) {
        boolean remove = service.removeById(id);
        return ApiResponse.success(remove);
    }

    @Override
    public ApiResponse
            <Boolean> batchRemove(List
                                          <Long> ids) {
        boolean remove = service.removeByIds(ids);
        return ApiResponse.success(remove);
    }
}
