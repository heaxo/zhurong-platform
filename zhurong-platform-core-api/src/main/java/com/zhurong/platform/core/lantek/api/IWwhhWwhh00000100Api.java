package com.zhurong.platform.core.lantek.api;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000100DTO;
import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000100PageQuery;
import com.zhurong.platform.core.lantek.vo.WwhhWwhh00000100VO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对外契约接口
 * <p>
 * 说明：仅定义接口契约
 */
public interface IWwhhWwhh00000100Api {

    /**
     * 分页查询
     */
    @GetMapping("/page")
    ApiResponse
            <PageResponse
                    <WwhhWwhh00000100VO>> page(@SpringQueryMap WwhhWwhh00000100PageQuery pageQuery);

    /**
     * 根据ID查询
     */
    @GetMapping("/{id}")
    ApiResponse
            <WwhhWwhh00000100VO> getById(@PathVariable("id") Long id);

    /**
     * 新增
     */
    @PostMapping
    ApiResponse
            <Long> save(@Valid @RequestBody WwhhWwhh00000100DTO dto);

    /**
     * 创建仓库
     * <p>
     * 相同参数重复调用时：
     * - 若资源已存在，则直接返回成功
     * - 若资源不存在，则执行创建
     * </p>
     * <p>
     * 注意：需依赖数据库唯一约束保证并发场景下的幂等性
     * </p>
     */
    @PostMapping("createWarehouse")
    ApiResponse
            <Boolean> createWarehouse(@Valid @RequestBody WwhhWwhh00000100DTO dto);
    /**
     * 创建库位
     * <p>
     * 相同参数重复调用时：
     * - 若资源已存在，则直接返回成功
     * - 若资源不存在，则执行创建
     * </p>
     * <p>
     * 注意：需依赖数据库唯一约束保证并发场景下的幂等性
     * </p>
     */
    @PostMapping("createRepositoryLocation")
    ApiResponse
            <Boolean> createRepositoryLocation(@Valid @RequestBody WwhhWwhh00000100DTO dto);
    /**
     * 创建仓库和库位
     *  <p>
     *  相同参数重复调用时：
     *  - 若资源已存在，则直接返回成功
     *  - 若资源不存在，则执行创建
     *  </p>
     *  <p>
     *  注意：需依赖数据库唯一约束保证并发场景下的幂等性
     *  </p>
     */
    @PostMapping("createWarehouseAndLocation")
    ApiResponse
            <Boolean> createWarehouseAndLocation(@Valid @RequestBody WwhhWwhh00000100DTO dto);


    /**
     * 更新
     */
    @PutMapping("/{id}")
    ApiResponse
            <Boolean> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody WwhhWwhh00000100DTO dto
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