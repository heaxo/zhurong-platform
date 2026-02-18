package com.ao.platform.auth.controller;

import com.ao.platform.auth.api.ISysMenuApi;
import com.ao.platform.auth.convert.SysMenuConvert;
import com.ao.platform.auth.dto.SysMenuDTO;
import com.ao.platform.auth.dto.SysMenuPageQuery;
import com.ao.platform.auth.entity.SysMenu;
import com.ao.platform.auth.service.ISysMenuService;
import com.ao.platform.auth.vo.SysMenuVO;
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
public class SysMenuController implements ISysMenuApi {

    private final SysMenuConvert convert;
    private final ISysMenuService service;

    @Override
    public ApiResponse<PageResponse<SysMenuVO>> page(SysMenuPageQuery pageQuery) {

        Page<SysMenu> page = service.page(new Page(pageQuery.getPage(), pageQuery.getPageSize()),
                service.lambdaQuery(
                        convert.toEntity(pageQuery)
                ));

        List<SysMenuVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse<SysMenuVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse<SysMenuVO> getById(Serializable id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse<Long> save(@Valid SysMenuDTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse<Boolean> update(Serializable id, @Valid SysMenuDTO dto) {
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
