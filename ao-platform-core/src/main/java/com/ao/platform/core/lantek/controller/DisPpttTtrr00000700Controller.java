package com.ao.platform.core.lantek.controller;

import com.ao.platform.core.lantek.service.IDisPpttTtrr00000700Service;
import com.ao.platform.core.lantek.dto.DisPpttTtrr00000700DTO;
import com.ao.platform.core.lantek.dto.DisPpttTtrr00000700PageQuery;
import com.ao.platform.core.lantek.vo.DisPpttTtrr00000700VO;
import com.ao.platform.core.lantek.entity.DisPpttTtrr00000700;
import com.ao.platform.core.lantek.api.IDisPpttTtrr00000700Api;
import com.ao.platform.core.lantek.convert.DisPpttTtrr00000700Convert;

import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;
import com.ao.platform.base.model.PageFactory;
import com.ao.platform.core.web.BaseController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
*  控制器实现
*/
@RestController
@RequiredArgsConstructor
public class DisPpttTtrr00000700Controller extends BaseController implements IDisPpttTtrr00000700Api {

private final DisPpttTtrr00000700Convert convert;
private final IDisPpttTtrr00000700Service service;

@Override
public ApiResponse
<PageResponse
<DisPpttTtrr00000700VO>> page(DisPpttTtrr00000700PageQuery pageQuery ) {

    LambdaQueryWrapper<DisPpttTtrr00000700> wrapper =
    Wrappers.lambdaQuery(convert.toEntity(pageQuery));

    wrapper.orderByAsc(DisPpttTtrr00000700::getRecID);

    Page<DisPpttTtrr00000700> page = service.page(
    PageFactory.build(pageQuery),
    wrapper
    );

    List
    <DisPpttTtrr00000700VO> voList = page.getRecords()
        .stream()
        .map(convert::toVO)
        .toList();

        PageResponse
        <DisPpttTtrr00000700VO> response = new PageResponse<>(
            voList,
            page.getTotal(),
            page.getCurrent(),
            page.getSize()
            );

            return ApiResponse.success(response);
            }

            @Override
            public ApiResponse
            <DisPpttTtrr00000700VO> getById(Long id) {
                return ApiResponse.success(service.getVOById(id));
                }

                @Override
                public ApiResponse
                <Long> save(@Valid DisPpttTtrr00000700DTO dto) {
                    Long id = service.saveFromDTO(dto);
                    return ApiResponse.success(id);
                    }

                    @Override
                    public ApiResponse
                    <Boolean> update(Long id, @Valid DisPpttTtrr00000700DTO dto) {
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
