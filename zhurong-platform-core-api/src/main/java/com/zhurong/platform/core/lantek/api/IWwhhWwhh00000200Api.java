package com.zhurong.platform.core.lantek.api;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000200DTO;
import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000200PageQuery;
import com.zhurong.platform.core.lantek.vo.WwhhWwhh00000200VO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对外契约接口
 * <p>
 * 说明：仅定义接口契约
 */
public interface IWwhhWwhh00000200Api {

    /**
     * 分页查询
     */
    @GetMapping("/page")
    ApiResponse
            <PageResponse
                    <WwhhWwhh00000200VO>> page(@SpringQueryMap WwhhWwhh00000200PageQuery pageQuery);

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    ApiResponse
            <WwhhWwhh00000200VO> getById(@PathVariable("id") Long id);

    /**
     * 新增
     */
    @PostMapping
    ApiResponse
            <Long> save(@Valid @RequestBody WwhhWwhh00000200DTO dto);

    /**
     * 更新
     */
    @PutMapping("/{id}")
    ApiResponse
            <Boolean> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody WwhhWwhh00000200DTO dto
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