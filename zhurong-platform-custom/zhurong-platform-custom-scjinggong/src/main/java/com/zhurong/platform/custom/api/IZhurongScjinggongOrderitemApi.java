package com.zhurong.platform.custom.api;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderitemDTO;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderitemPageQuery;
import com.zhurong.platform.custom.vo.ZhurongScjinggongOrderitemVO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对外契约接口
 * <p>
 * 说明：仅定义接口契约
 */
public interface IZhurongScjinggongOrderitemApi {

    /**
     * 分页查询
     */
    @GetMapping("/page")
    ApiResponse
            <PageResponse
                    <ZhurongScjinggongOrderitemVO>> page(@SpringQueryMap ZhurongScjinggongOrderitemPageQuery pageQuery);

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    ApiResponse
            <ZhurongScjinggongOrderitemVO> getById(@PathVariable("id") Long id);

    /**
     * 新增
     */
    @PostMapping
    ApiResponse
            <Long> save(@Valid @RequestBody ZhurongScjinggongOrderitemDTO dto);

    /**
     * 更新
     */
    @PutMapping("/{id}")
    ApiResponse
            <Boolean> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody ZhurongScjinggongOrderitemDTO dto
    );

    /**
     * 删除
     */
    @PostMapping("/importToExpert")
    ApiResponse
            <Boolean> importToExpert(@Valid @RequestBody ZhurongScjinggongOrderitemDTO dto);

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
