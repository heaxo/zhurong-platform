package com.zhurong.platform.core.lantek.api;

import com.zhurong.platform.core.lantek.dto.DisPpttTtrr00000700DTO;
import com.zhurong.platform.core.lantek.dto.DisPpttTtrr00000700PageQuery;
import com.zhurong.platform.core.lantek.vo.DisPpttTtrr00000700VO;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;

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
@RequestMapping("/disPpttTtrr00000700")
public interface IDisPpttTtrr00000700Api {

/**
* 分页查询
*/
@GetMapping("/page")
ApiResponse
<PageResponse
<DisPpttTtrr00000700VO>> page(@SpringQueryMap DisPpttTtrr00000700PageQuery pageQuery);

    /**
    * 根据ID查询
    */
    @GetMapping("/{id}")
    ApiResponse
    <DisPpttTtrr00000700VO> getById(@PathVariable Long id);

        /**
        * 新增
        */
        @PostMapping
        ApiResponse
        <Long> save(@Valid @RequestBody DisPpttTtrr00000700DTO dto);

            /**
            * 更新
            */
            @PutMapping("/{id}")
            ApiResponse
            <Boolean> update(
                @PathVariable Long id,
                @Valid @RequestBody DisPpttTtrr00000700DTO dto
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