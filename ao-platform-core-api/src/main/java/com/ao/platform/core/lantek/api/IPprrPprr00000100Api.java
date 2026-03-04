package com.ao.platform.core.lantek.api;

import com.ao.platform.core.lantek.dto.PprrPprr00000100DTO;
import com.ao.platform.core.lantek.dto.PprrPprr00000100PageQuery;
import com.ao.platform.core.lantek.vo.PprrPprr00000100VO;

import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.SpringQueryMap;

import java.lang.Long;
import java.util.List;

/**
*  对外契约接口
*
* 说明：仅定义接口契约
*/
@RequestMapping("/pprrPprr00000100")
public interface IPprrPprr00000100Api {

/**
* 分页查询
*/
@GetMapping("/page")
ApiResponse
<PageResponse
<PprrPprr00000100VO>> page(@SpringQueryMap PprrPprr00000100PageQuery pageQuery);

    /**
    * 根据ID查询
    */
    @GetMapping("/{id}")
    ApiResponse
    <PprrPprr00000100VO> getById(@PathVariable Long id);

        /**
        * 新增
        */
        @PostMapping
        ApiResponse
        <Long> save(@Valid @RequestBody PprrPprr00000100DTO dto);

            /**
            * 更新
            */
            @PutMapping("/{id}")
            ApiResponse
            <Boolean> update(
                @PathVariable Long id,
                @Valid @RequestBody PprrPprr00000100DTO dto
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