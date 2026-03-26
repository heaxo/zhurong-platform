package ${package.Parent}.api;

import ${package.Parent}.dto.${entity}DTO;
import ${package.Parent}.dto.${entity}PageQuery;
import ${package.Parent}.vo.${entity}VO;

import api.com.zhurong.platform.base.ApiResponse;
import api.com.zhurong.platform.base.PageResponse;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.SpringQueryMap;

import java.lang.Long;
import java.util.List;

/**
* ${table.comment!} 对外契约接口
*
* 说明：仅定义接口契约
*/
public interface I${entity}Api {

/**
* 分页查询
*/
@GetMapping("/page")
ApiResponse
<PageResponse
<${entity}VO>> page(@SpringQueryMap ${entity}PageQuery pageQuery);

    /**
    * 根据ID查询
    */
    @GetMapping("/{id}")
    ApiResponse
    <${entity}VO> getById(@PathVariable("id") Long id);

        /**
        * 新增
        */
        @PostMapping
        ApiResponse
        <Long> save(@Valid @RequestBody ${entity}DTO dto);

            /**
            * 更新
            */
            @PutMapping("/{id}")
            ApiResponse
            <Boolean> update(
                @PathVariable("id") Long id,
                @Valid @RequestBody ${entity}DTO dto
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