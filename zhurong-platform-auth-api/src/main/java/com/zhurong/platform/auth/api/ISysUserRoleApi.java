package com.zhurong.platform.auth.api;

import com.zhurong.platform.auth.dto.SysUserRoleDTO;
import com.zhurong.platform.auth.dto.SysUserRolePageQuery;
import com.zhurong.platform.auth.vo.SysUserRoleVO;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对外契约接口
 * <p>
 * 说明：仅定义接口契约
 */
@RequestMapping("/sysUserRole")
public interface ISysUserRoleApi {

    /**
     * 分页查询
     */
    @GetMapping("/page")
    ApiResponse
            <PageResponse
                    <SysUserRoleVO>> page(@SpringQueryMap SysUserRolePageQuery pageQuery);

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    ApiResponse
            <SysUserRoleVO> getById(@PathVariable Long id);

    /**
     * 新增
     */
    @PostMapping
    ApiResponse
            <Long> save(@Valid @RequestBody SysUserRoleDTO dto);

    /**
     * 更新
     */
    @PutMapping("/{id}")
    ApiResponse
            <Boolean> update(
            @PathVariable Long id,
            @Valid @RequestBody SysUserRoleDTO dto
    );

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    ApiResponse
            <Boolean> remove(@PathVariable Long id);

    /**
     * 批量删除
     */
    @DeleteMapping
    ApiResponse
            <Boolean> batchRemove(@RequestBody List
            <Long> ids);
}