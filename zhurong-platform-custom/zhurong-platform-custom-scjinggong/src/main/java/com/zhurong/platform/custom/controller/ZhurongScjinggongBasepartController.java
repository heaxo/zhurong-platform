package com.zhurong.platform.custom.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.custom.api.IZhurongScjinggongBasepartApi;
import com.zhurong.platform.custom.convert.ZhurongScjinggongBasepartConvert;
import com.zhurong.platform.custom.dto.ZhurongScjinggongBasepartDTO;
import com.zhurong.platform.custom.dto.ZhurongScjinggongBasepartPageQuery;
import com.zhurong.platform.custom.entity.ZhurongScjinggongBasepart;
import com.zhurong.platform.custom.service.IZhurongScjinggongBasepartService;
import com.zhurong.platform.custom.vo.ZhurongScjinggongBasepartVO;
import com.zhurong.platform.custom.web.BaseController;
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
@RequestMapping("/zhurongScjinggongBasepart")
public class ZhurongScjinggongBasepartController extends BaseController implements IZhurongScjinggongBasepartApi {

    private final ZhurongScjinggongBasepartConvert convert;
    private final IZhurongScjinggongBasepartService service;

    @Override
    public ApiResponse
            <PageResponse
                    <ZhurongScjinggongBasepartVO>> page(ZhurongScjinggongBasepartPageQuery pageQuery) {

        LambdaQueryWrapper<ZhurongScjinggongBasepart> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(ZhurongScjinggongBasepart::getCreatedAt);

        Page<ZhurongScjinggongBasepart> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <ZhurongScjinggongBasepartVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <ZhurongScjinggongBasepartVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <ZhurongScjinggongBasepartVO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid ZhurongScjinggongBasepartDTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid ZhurongScjinggongBasepartDTO dto) {
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
