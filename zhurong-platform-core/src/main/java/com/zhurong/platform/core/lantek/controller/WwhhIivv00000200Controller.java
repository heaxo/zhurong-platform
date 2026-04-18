package com.zhurong.platform.core.lantek.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.core.lantek.api.IWwhhIivv00000200Api;
import com.zhurong.platform.core.lantek.convert.WwhhIivv00000200Convert;
import com.zhurong.platform.core.lantek.dto.WwhhIivv00000200DTO;
import com.zhurong.platform.core.lantek.dto.WwhhIivv00000200PageQuery;
import com.zhurong.platform.core.lantek.entity.WwhhIivv00000200;
import com.zhurong.platform.core.lantek.service.IWwhhIivv00000200Service;
import com.zhurong.platform.core.lantek.vo.WwhhIivv00000200VO;
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
@RequestMapping("/wwhhIivv00000200")
public class WwhhIivv00000200Controller extends BaseController implements IWwhhIivv00000200Api {

    private final WwhhIivv00000200Convert convert;
    private final IWwhhIivv00000200Service service;

    @Override
    public ApiResponse
            <PageResponse
                    <WwhhIivv00000200VO>> page(WwhhIivv00000200PageQuery pageQuery) {

        LambdaQueryWrapper<WwhhIivv00000200> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(WwhhIivv00000200::getRecID);

        Page<WwhhIivv00000200> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <WwhhIivv00000200VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <WwhhIivv00000200VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <WwhhIivv00000200VO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid WwhhIivv00000200DTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid WwhhIivv00000200DTO dto) {
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
