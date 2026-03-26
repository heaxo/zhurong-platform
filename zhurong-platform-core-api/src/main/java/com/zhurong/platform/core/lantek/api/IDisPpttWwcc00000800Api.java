package com.zhurong.platform.core.lantek.api;

import com.zhurong.platform.core.lantek.dto.DisPpttWwcc00000800DTO;
import com.zhurong.platform.core.lantek.dto.DisPpttWwcc00000800PageQuery;
import com.zhurong.platform.core.lantek.vo.DisPpttWwcc00000800VO;

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
@RequestMapping("/disPpttWwcc00000800")
public interface IDisPpttWwcc00000800Api {

/**
* 分页查询
*/
@GetMapping("/page")
ApiResponse
<PageResponse
<DisPpttWwcc00000800VO>> page(@SpringQueryMap DisPpttWwcc00000800PageQuery pageQuery);

    /**
    * 根据ID查询
    */
    @GetMapping("/{id}")
    ApiResponse
    <DisPpttWwcc00000800VO> getById(@PathVariable Long id);

        /**
        * 新增
        */
        @PostMapping
        ApiResponse
        <Long> save(@Valid @RequestBody DisPpttWwcc00000800DTO dto);

            /**
            * 更新
            */
            @PutMapping("/{id}")
            ApiResponse
            <Boolean> update(
                @PathVariable Long id,
                @Valid @RequestBody DisPpttWwcc00000800DTO dto
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