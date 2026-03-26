package com.zhurong.platform.core.lantek.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.core.lantek.api.IDisShprPptt00000200Api;
import com.zhurong.platform.core.lantek.convert.DisShprPptt00000200Convert;
import com.zhurong.platform.core.lantek.dto.DisShprPptt00000200DTO;
import com.zhurong.platform.core.lantek.dto.DisShprPptt00000200PageQuery;
import com.zhurong.platform.core.lantek.entity.DisShprPptt00000200;
import com.zhurong.platform.core.lantek.service.IDisShprPptt00000200Service;
import com.zhurong.platform.core.lantek.vo.DisShprPptt00000200VO;
import com.zhurong.platform.core.web.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
public class DisShprPptt00000200Controller extends BaseController implements IDisShprPptt00000200Api {

    private final DisShprPptt00000200Convert convert;
    private final IDisShprPptt00000200Service service;

    @Override
    public ApiResponse
            <PageResponse
                    <DisShprPptt00000200VO>> page(DisShprPptt00000200PageQuery pageQuery) {

        LambdaQueryWrapper<DisShprPptt00000200> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(DisShprPptt00000200::getRecID);

        Page<DisShprPptt00000200> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <DisShprPptt00000200VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <DisShprPptt00000200VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <DisShprPptt00000200VO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid DisShprPptt00000200DTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid DisShprPptt00000200DTO dto) {
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
