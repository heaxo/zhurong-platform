package com.ao.platform.auth.api;

import com.ao.platform.auth.dto.SysTenantDTO;
import com.ao.platform.auth.dto.SysTenantPageQuery;
import com.ao.platform.auth.vo.SysTenantVO;
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
@RequestMapping("/sysTenant")
public interface ISysTenantApi {

    /**
     * 分页查询
     */
    @GetMapping("/page")
    ApiResponse
            <PageResponse
                    <SysTenantVO>> page(@SpringQueryMap SysTenantPageQuery pageQuery);

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    ApiResponse
            <SysTenantVO> getById(@PathVariable Serializable id);

    /**
     * 新增
     */
    @PostMapping
    ApiResponse
            <Long> save(@Valid @RequestBody SysTenantDTO dto);

    /**
     * 更新
     */
    @PutMapping("/{id}")
    ApiResponse
            <Boolean> update(
            @PathVariable Serializable id,
            @Valid @RequestBody SysTenantDTO dto
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
}