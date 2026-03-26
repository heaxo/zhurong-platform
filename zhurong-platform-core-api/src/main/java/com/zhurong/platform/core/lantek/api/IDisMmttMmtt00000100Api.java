package com.zhurong.platform.core.lantek.api;

import com.zhurong.platform.core.lantek.dto.DisMmttMmtt00000100DTO;
import com.zhurong.platform.core.lantek.dto.DisMmttMmtt00000100PageQuery;
import com.zhurong.platform.core.lantek.vo.DisMmttMmtt00000100VO;

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
@RequestMapping("/disMmttMmtt00000100")
public interface IDisMmttMmtt00000100Api {

/**
* 分页查询
*/
@GetMapping("/page")
ApiResponse
<PageResponse
<DisMmttMmtt00000100VO>> page(@SpringQueryMap DisMmttMmtt00000100PageQuery pageQuery);

    /**
    * 根据ID查询
    */
    @GetMapping("/{id}")
    ApiResponse
    <DisMmttMmtt00000100VO> getById(@PathVariable Long id);

        /**
        * 新增
        */
        @PostMapping
        ApiResponse
        <Long> save(@Valid @RequestBody DisMmttMmtt00000100DTO dto);

            /**
            * 更新
            */
            @PutMapping("/{id}")
            ApiResponse
            <Boolean> update(
                @PathVariable Long id,
                @Valid @RequestBody DisMmttMmtt00000100DTO dto
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