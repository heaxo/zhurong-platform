package com.zhurong.platform.auth.api;

import com.zhurong.platform.auth.dto.SysMenuDTO;
import com.zhurong.platform.auth.dto.SysMenuPageQuery;
import com.zhurong.platform.auth.vo.SysMenuVO;
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
     *
     */
    @GetMapping("/name-exists")
    ApiResponse<Boolean> nameExists(@SpringQueryMap SysMenuDTO dto);
    /**
     *
     */
    @GetMapping("/path-exists")
    ApiResponse<Boolean> pathExists(@SpringQueryMap SysMenuDTO dto);

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    ApiResponse
            <SysMenuVO> getById(@PathVariable Long id);

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
            @PathVariable Long id,
            @Valid @RequestBody SysMenuDTO dto
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
    /**
     * 当前用户所有菜单
     */
    @GetMapping("all")
    ApiResponse<List<SysMenuVO>> all();
}