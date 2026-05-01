package com.zhurong.platform.auth.controller;

import com.zhurong.platform.auth.api.ISysTenantApi;
import com.zhurong.platform.auth.convert.SysTenantConvert;
import com.zhurong.platform.auth.dto.SysTenantDTO;
import com.zhurong.platform.auth.dto.SysTenantPageQuery;
import com.zhurong.platform.auth.entity.SysTenant;
import com.zhurong.platform.auth.service.ISysTenantService;
import com.zhurong.platform.auth.vo.SysTenantVO;
import com.zhurong.platform.auth.web.BaseController;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.BaseEntity;
import com.zhurong.platform.base.model.PageFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
public class SysTenantController extends BaseController implements ISysTenantApi {

    private final SysTenantConvert convert;
    private final ISysTenantService service;

    @Override
    public ApiResponse
            <PageResponse
                    <SysTenantVO>> page(SysTenantPageQuery pageQuery) {

        LambdaQueryWrapper<SysTenant> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));
        wrapper.orderByAsc(BaseEntity::getCreateTime);
        Page<SysTenant> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <SysTenantVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <SysTenantVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <SysTenantVO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid SysTenantDTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid SysTenantDTO dto) {
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
            <Boolean> batchRemove(List<Long> ids) {
        boolean remove = service.removeByIds(ids);
        return ApiResponse.success(remove);
    }
}
