package com.ao.platform.auth.api;

import com.ao.platform.auth.dto.SysMenuDTO;
import com.ao.platform.auth.dto.SysMenuPageQuery;
import com.ao.platform.auth.vo.SysMenuVO;
import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 对外契约接口
 * <p>
 * 说明：仅定义接口契约
 */
@RequestMapping("/sysMenu")
public interface ISysMenuApi {

    /**
     * 分页查询
     */
    @GetMapping("/page")
    ApiResponse
            <PageResponse
                    <SysMenuVO>> page(@SpringQueryMap SysMenuPageQuery pageQuery);

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    ApiResponse
            <SysMenuVO> getById(@PathVariable Serializable id);

    /**
     * 新增
     */
    @PostMapping
    ApiResponse
            <Long> save(@Valid @RequestBody SysMenuDTO dto);

    /**
     * 更新
     */
    @PutMapping("/{id}")
    ApiResponse
            <Boolean> update(
            @PathVariable Serializable id,
            @Valid @RequestBody SysMenuDTO dto
    );

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    ApiResponse
            <Boolean> remove(@PathVariable Serializable id);

    /**
     * 批量删除
     */
    @DeleteMapping
    ApiResponse
            <Boolean> batchRemove(@RequestBody List
            <Serializable> ids);
    /**
     * 当前用户所有菜单
     */
    @GetMapping("all")
    ApiResponse<List<SysMenuVO>> all();
}