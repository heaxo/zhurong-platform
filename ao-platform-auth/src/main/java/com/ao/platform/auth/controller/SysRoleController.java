package com.ao.platform.auth.controller;

import com.ao.platform.auth.api.ISysRoleApi;
import com.ao.platform.auth.convert.SysRoleConvert;
import com.ao.platform.auth.dto.SysRoleDTO;
import com.ao.platform.auth.dto.SysRolePageQuery;
import com.ao.platform.auth.entity.SysRole;
import com.ao.platform.auth.service.ISysRoleService;
import com.ao.platform.auth.vo.SysRoleVO;
import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
public class SysRoleController implements ISysRoleApi {

    private final SysRoleConvert convert;
    private final ISysRoleService service;

    @Override
    public ApiResponse<PageResponse<SysRoleVO>> page(SysRolePageQuery pageQuery) {

        Page<SysRole> page = service.page(new Page(pageQuery.getPage(), pageQuery.getPageSize()),
                service.lambdaQuery(
                        convert.toEntity(pageQuery)
                ));

        List<SysRoleVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse<SysRoleVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse<SysRoleVO> getById(Serializable id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse<Long> save(@Valid SysRoleDTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse<Boolean> update(Serializable id, @Valid SysRoleDTO dto) {
        boolean update = service.updateFromDTO(id, dto);
        return ApiResponse.success(update);
    }

    @Override
    public ApiResponse<Boolean> remove(Serializable id) {
        boolean remove = service.removeById(id);
        return ApiResponse.success(remove);
    }

    @Override
    public ApiResponse<Boolean> batchRemove(List<Serializable> ids) {
        boolean remove = service.removeByIds(ids);
        return ApiResponse.success(remove);
    }
}
