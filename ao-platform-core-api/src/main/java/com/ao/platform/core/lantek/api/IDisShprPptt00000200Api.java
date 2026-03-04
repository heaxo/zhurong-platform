package com.ao.platform.core.lantek.api;

import com.ao.platform.core.lantek.dto.DisShprPptt00000200DTO;
import com.ao.platform.core.lantek.dto.DisShprPptt00000200PageQuery;
import com.ao.platform.core.lantek.vo.DisShprPptt00000200VO;

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
@RequestMapping("/disShprPptt00000200")
public interface IDisShprPptt00000200Api {

/**
* 分页查询
*/
@GetMapping("/page")
ApiResponse
<PageResponse
<DisShprPptt00000200VO>> page(@SpringQueryMap DisShprPptt00000200PageQuery pageQuery);

    /**
    * 根据ID查询
    */
    @GetMapping("/{id}")
    ApiResponse
    <DisShprPptt00000200VO> getById(@PathVariable Long id);

        /**
        * 新增
        */
        @PostMapping
        ApiResponse
        <Long> save(@Valid @RequestBody DisShprPptt00000200DTO dto);

            /**
            * 更新
            */
            @PutMapping("/{id}")
            ApiResponse
            <Boolean> update(
                @PathVariable Long id,
                @Valid @RequestBody DisShprPptt00000200DTO dto
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