package com.zhurong.platform.core.lantek.api;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.core.lantek.dto.PpbbPpbb00000100DTO;
import com.zhurong.platform.core.lantek.dto.PpbbPpbb00000100PageQuery;
import com.zhurong.platform.core.lantek.vo.PpbbPpbb00000100VO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对外契约接口
 * <p>
 * 说明：仅定义接口契约
 */
public interface IPpbbPpbb00000100Api {

    /**
     * 分页查询
     */
    @GetMapping("/page")
    ApiResponse
            <PageResponse
                    <PpbbPpbb00000100VO>> page(@SpringQueryMap PpbbPpbb00000100PageQuery pageQuery);

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    ApiResponse
            <PpbbPpbb00000100VO> getById(@PathVariable("id") Long id);

    /**
     * 新增
     */
    @PostMapping
    ApiResponse
            <Long> save(@Valid @RequestBody PpbbPpbb00000100DTO dto);

    /**
     * 更新
     */
    @PutMapping("/{id}")
    ApiResponse
            <Boolean> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody PpbbPpbb00000100DTO dto
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
}