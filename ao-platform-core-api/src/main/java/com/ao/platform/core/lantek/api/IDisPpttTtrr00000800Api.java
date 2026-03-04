package com.ao.platform.core.lantek.api;

import com.ao.platform.core.lantek.dto.DisPpttTtrr00000800DTO;
import com.ao.platform.core.lantek.dto.DisPpttTtrr00000800PageQuery;
import com.ao.platform.core.lantek.vo.DisPpttTtrr00000800VO;

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
@RequestMapping("/disPpttTtrr00000800")
public interface IDisPpttTtrr00000800Api {

/**
* 分页查询
*/
@GetMapping("/page")
ApiResponse
<PageResponse
<DisPpttTtrr00000800VO>> page(@SpringQueryMap DisPpttTtrr00000800PageQuery pageQuery);

    /**
    * 根据ID查询
    */
    @GetMapping("/{id}")
    ApiResponse
    <DisPpttTtrr00000800VO> getById(@PathVariable Long id);

        /**
        * 新增
        */
        @PostMapping
        ApiResponse
        <Long> save(@Valid @RequestBody DisPpttTtrr00000800DTO dto);

            /**
            * 更新
            */
            @PutMapping("/{id}")
            ApiResponse
            <Boolean> update(
                @PathVariable Long id,
                @Valid @RequestBody DisPpttTtrr00000800DTO dto
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