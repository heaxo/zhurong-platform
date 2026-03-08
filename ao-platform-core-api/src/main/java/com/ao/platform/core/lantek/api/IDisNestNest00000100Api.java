package com.ao.platform.core.lantek.api;

import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;
import com.ao.platform.core.lantek.dto.DisNestNest00000100DTO;
import com.ao.platform.core.lantek.dto.DisNestNest00000100PageQuery;
import com.ao.platform.core.lantek.vo.DisNestNest00000100VO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对外契约接口
 * <p>
 * 说明：仅定义接口契约
 */
public interface IDisNestNest00000100Api {

    /**
     * 分页查询
     */
    @GetMapping("/page")
    ApiResponse
            <PageResponse
                    <DisNestNest00000100VO>> page(@SpringQueryMap DisNestNest00000100PageQuery pageQuery);

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    ApiResponse
            <DisNestNest00000100VO> getById(@PathVariable("id") Long id);

    /**
     * 新增
     */
    @PostMapping
    ApiResponse
            <Long> save(@Valid @RequestBody DisNestNest00000100DTO dto);

    /**
     * 更新
     */
    @PutMapping("/{id}")
    ApiResponse
            <Boolean> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody DisNestNest00000100DTO dto
    );

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    ApiResponse
            <Boolean> remove(@PathVariable("id") Long id);

    /**
     * 批量删除
     */
    @DeleteMapping
    ApiResponse
            <Boolean> batchRemove(@RequestBody List
            <Long> ids);

    @GetMapping("/details")
    ApiResponse<DisNestNest00000100VO> details(@SpringQueryMap DisNestNest00000100DTO query);
}