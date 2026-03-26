package com.zhurong.platform.core.lantek.api;

import com.zhurong.platform.core.lantek.dto.DisMmnnMmoo00000200DTO;
import com.zhurong.platform.core.lantek.dto.DisMmnnMmoo00000200PageQuery;
import com.zhurong.platform.core.lantek.vo.DisMmnnMmoo00000200VO;

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
@RequestMapping("/disMmnnMmoo00000200")
public interface IDisMmnnMmoo00000200Api {

/**
* 分页查询
*/
@GetMapping("/page")
ApiResponse
<PageResponse
<DisMmnnMmoo00000200VO>> page(@SpringQueryMap DisMmnnMmoo00000200PageQuery pageQuery);

    /**
    * 根据ID查询
    */
    @GetMapping("/{id}")
    ApiResponse
    <DisMmnnMmoo00000200VO> getById(@PathVariable Long id);

        /**
        * 新增
        */
        @PostMapping
        ApiResponse
        <Long> save(@Valid @RequestBody DisMmnnMmoo00000200DTO dto);

            /**
            * 更新
            */
            @PutMapping("/{id}")
            ApiResponse
            <Boolean> update(
                @PathVariable Long id,
                @Valid @RequestBody DisMmnnMmoo00000200DTO dto
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