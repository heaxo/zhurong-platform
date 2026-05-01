package com.zhurong.platform.auth.controller;

import com.zhurong.platform.auth.api.ISysDeptApi;
import com.zhurong.platform.auth.convert.SysDeptConvert;
import com.zhurong.platform.auth.dto.SysDeptDTO;
import com.zhurong.platform.auth.dto.SysDeptPageQuery;
import com.zhurong.platform.auth.entity.SysDept;
import com.zhurong.platform.auth.service.ISysDeptService;
import com.zhurong.platform.auth.vo.SysDeptVO;
import com.zhurong.platform.auth.web.BaseController;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.BaseEntity;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.base.util.TreeBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
public class SysDeptController extends BaseController implements ISysDeptApi {

    private final SysDeptConvert convert;
    private final ISysDeptService service;

    @Override
    public ApiResponse
            <PageResponse
                    <SysDeptVO>> page(SysDeptPageQuery pageQuery) {

        LambdaQueryWrapper<SysDept> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));
        wrapper.orderByAsc(BaseEntity::getCreateTime);
        Page<SysDept> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <SysDeptVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <SysDeptVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @GetMapping("tree-list")
    public ApiResponse<List<SysDeptVO>> treeList() {
        List<SysDept> list = service.list();

        List<SysDeptVO> vos = list
                .stream()
                .map(convert::toVO)
                .toList();

        List<SysDeptVO> tree = TreeBuilder.buildTree(
                vos,
                SysDeptVO::getId,
                SysDeptVO::getPid,
                pid -> pid == null || "-1".equals(pid) || pid.isEmpty(),
                Comparator.comparing(SysDeptVO::getSortOrder),
                SysDeptVO::setChildren
        );
        return ApiResponse.success(tree);
    }

    @Override
    public ApiResponse
            <SysDeptVO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid SysDeptDTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid SysDeptDTO dto) {
        boolean update = service.updateFromDTO(id, dto);
        return ApiResponse.success(update);
    }

    @Override
    public ApiResponse
            <Boolean> remove(Long id) {
        boolean remove = service.removeById(id);
        return ApiResponse.success(remove);
    }

    @Override
    public ApiResponse
            <Boolean> batchRemove(List<Long> ids) {
        boolean remove = service.removeByIds(ids);
        return ApiResponse.success(remove);
    }
}
