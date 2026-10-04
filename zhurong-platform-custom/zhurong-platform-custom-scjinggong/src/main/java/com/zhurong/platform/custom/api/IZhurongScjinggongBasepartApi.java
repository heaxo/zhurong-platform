package com.zhurong.platform.custom.api;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.custom.dto.ZhurongScjinggongBasepartDTO;
import com.zhurong.platform.custom.dto.ZhurongScjinggongBasepartPageQuery;
import com.zhurong.platform.custom.vo.ZhurongScjinggongBasepartVO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*  对外契约接口
*
* 说明：仅定义接口契约
*/
public interface IZhurongScjinggongBasepartApi {

/**
* 分页查询
*/
@GetMapping("/page")
ApiResponse
<PageResponse
        <ZhurongScjinggongBasepartVO>> page(@SpringQueryMap ZhurongScjinggongBasepartPageQuery pageQuery);

    /**
    * 根据ID查询
    */
    @GetMapping("/{id}")
    ApiResponse
            <ZhurongScjinggongBasepartVO> getById(@PathVariable("id") Long id);

        /**
        * 新增
        */
        @PostMapping
        ApiResponse
        <Long> save(@Valid @RequestBody ZhurongScjinggongBasepartDTO dto);

            /**
            * 更新
            */
            @PutMapping("/{id}")
            ApiResponse
            <Boolean> update(
                @PathVariable("id") Long id,
                @Valid @RequestBody ZhurongScjinggongBasepartDTO dto
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