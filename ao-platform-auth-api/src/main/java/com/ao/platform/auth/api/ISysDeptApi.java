package com.ao.platform.auth.api;

import com.ao.platform.auth.dto.SysDeptDTO;
import com.ao.platform.auth.vo.SysDeptVO;
import com.ao.platform.auth.entity.SysDept;

import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
*  对外契约接口
*
* 说明：仅定义接口契约
*/
@RequestMapping("/api/auth/sysDept")
public interface ISysDeptApi {

/**
* 分页查询
*/
@GetMapping("/page")
ApiResponse<PageResponse<SysDeptVO>> page(
    @RequestParam(defaultValue = "1") long current,
    @RequestParam(defaultValue = "10") long size
    );

    /**
    * 根据ID查询
    */
    @GetMapping("/{id}")
    ApiResponse<SysDeptVO> getById(@PathVariable Serializable id);

    /**
    * 新增
    */
    @PostMapping
    ApiResponse<Void> save(@Valid @RequestBody SysDeptDTO dto);

    /**
    * 更新
    */
    @PutMapping("/{id}")
    ApiResponse<Void> update(
    @PathVariable Serializable id,
    @Valid @RequestBody SysDeptDTO dto
    );

    /**
    * 删除
    */
    @DeleteMapping("/{id}")
    ApiResponse<Void> remove(@PathVariable Serializable id);

    /**
    * 批量删除
    */
    @DeleteMapping
    ApiResponse<Void> batchRemove(@RequestBody List<Serializable> ids);
}