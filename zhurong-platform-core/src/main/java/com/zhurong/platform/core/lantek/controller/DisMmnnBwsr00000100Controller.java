package com.zhurong.platform.core.lantek.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.core.lantek.api.IDisMmnnBwsr00000100Api;
import com.zhurong.platform.core.lantek.convert.DisMmnnBwsr00000100Convert;
import com.zhurong.platform.core.lantek.dto.DisMmnnBwsr00000100DTO;
import com.zhurong.platform.core.lantek.dto.DisMmnnBwsr00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.DisMmnnBwsr00000100;
import com.zhurong.platform.core.lantek.service.IDisMmnnBwsr00000100Service;
import com.zhurong.platform.core.lantek.vo.DisMmnnBwsr00000100VO;
import com.zhurong.platform.core.lantek.vo.JobBrowserTreeVO;
import com.zhurong.platform.core.web.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/disMmnnBwsr00000100")
public class DisMmnnBwsr00000100Controller extends BaseController implements IDisMmnnBwsr00000100Api {

    private final DisMmnnBwsr00000100Convert convert;
    private final IDisMmnnBwsr00000100Service service;

    @Override
    public ApiResponse
            <PageResponse
                    <DisMmnnBwsr00000100VO>> page(DisMmnnBwsr00000100PageQuery pageQuery) {

        LambdaQueryWrapper<DisMmnnBwsr00000100> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(DisMmnnBwsr00000100::getRecID);

        Page<DisMmnnBwsr00000100> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <DisMmnnBwsr00000100VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <DisMmnnBwsr00000100VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <DisMmnnBwsr00000100VO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <List<JobBrowserTreeVO>> getJobBrowserTree() {
        List<JobBrowserTreeVO> jobTree = service.getJobBrowserTree();
        return ApiResponse.success(jobTree);
    }

    @Override
    public ApiResponse
            <Long> save(@Valid DisMmnnBwsr00000100DTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid DisMmnnBwsr00000100DTO dto) {
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
            <Boolean> batchRemove(List
                                          <Long> ids) {
        boolean remove = service.removeByIds(ids);
        return ApiResponse.success(remove);
    }
}
