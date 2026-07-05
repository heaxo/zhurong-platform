package com.zhurong.platform.core.lantek.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.core.lantek.api.IDisNestNest00000100Api;
import com.zhurong.platform.core.lantek.convert.DisNestNest00000100Convert;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000100DTO;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.DisNestNest00000100;
import com.zhurong.platform.core.lantek.service.IDisNestNest00000100Service;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000100VO;
import com.zhurong.platform.core.web.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/disNestNest00000100")
public class DisNestNest00000100Controller extends BaseController implements IDisNestNest00000100Api {

    private final DisNestNest00000100Convert convert;
    private final IDisNestNest00000100Service service;

    @Override
    public ApiResponse
            <PageResponse
                    <DisNestNest00000100VO>> page(DisNestNest00000100PageQuery pageQuery) {

        LambdaQueryWrapper<DisNestNest00000100> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(DisNestNest00000100::getRecID);

        Page<DisNestNest00000100> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <DisNestNest00000100VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <DisNestNest00000100VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <DisNestNest00000100VO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse<PageResponse<DisNestNest00000100VO>> pageNestOverview(@RequestBody DisNestNest00000100PageQuery req) {
        return ApiResponse.success(service.pageNestOverview(req));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid DisNestNest00000100DTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid DisNestNest00000100DTO dto) {
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

    @Override
    public ApiResponse<DisNestNest00000100VO> details(DisNestNest00000100DTO query) {
        Integer recID = query.getRecID();

        if (recID == null) {
            return ApiResponse.fail("套料ID不能为空");
        }

        DisNestNest00000100VO details = service.details(recID);
        return ApiResponse.success(details);
    }
}
