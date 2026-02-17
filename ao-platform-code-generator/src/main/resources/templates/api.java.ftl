package ${package.Parent}.api;

import ${package.Parent}.dto.${entity}DTO;
import ${package.Parent}.vo.${entity}VO;
import ${package.Parent}.entity.${entity};

import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
* ${table.comment!} 对外契约接口
*
* 说明：仅定义接口契约
*/
@RequestMapping("/api/${package.ModuleName}/${table.entityPath}")
public interface I${entity}Api {

/**
* 分页查询
*/
@GetMapping("/page")
ApiResponse<PageResponse<${entity}VO>> page(
    @RequestParam(defaultValue = "1") long current,
    @RequestParam(defaultValue = "10") long size
    );

    /**
    * 根据ID查询
    */
    @GetMapping("/{id}")
    ApiResponse<${entity}VO> getById(@PathVariable Serializable id);

    /**
    * 新增
    */
    @PostMapping
    ApiResponse<Void> save(@Valid @RequestBody ${entity}DTO dto);

    /**
    * 更新
    */
    @PutMapping("/{id}")
    ApiResponse<Void> update(
    @PathVariable Serializable id,
    @Valid @RequestBody ${entity}DTO dto
    );

    /**
    * 删除
    */
    @DeleteMapping("/{id}")
    ApiResponse<Void> remove(@PathVariable Serializable id);

    /**
    * 批量删除
    */
    @DeleteMapping
    ApiResponse<Void> batchRemove(@RequestBody List<Serializable> ids);
}