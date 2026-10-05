package com.zhurong.platform.custom.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.custom.api.IZhurongScjinggongOrderApi;
import com.zhurong.platform.custom.convert.ZhurongScjinggongOrderConvert;
import com.zhurong.platform.custom.dto.OrderRequest;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderDTO;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderPageQuery;
import com.zhurong.platform.custom.entity.ZhurongScjinggongOrder;
import com.zhurong.platform.custom.service.IZhurongScjinggongOrderService;
import com.zhurong.platform.custom.vo.ZhurongScjinggongOrderVO;
import com.zhurong.platform.custom.web.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/zhurongScjinggongOrder")
public class ZhurongScjinggongOrderController extends BaseController implements IZhurongScjinggongOrderApi {

    private final ZhurongScjinggongOrderConvert convert;
    private final IZhurongScjinggongOrderService service;


    @PostMapping("creates")
    public ApiResponse<Boolean> creates(@Valid @RequestBody OrderRequest request) {
        try {
            boolean creates = service.creates(request);
            return ApiResponse.success(creates);
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }
    }

    @Override
    public ApiResponse
            <PageResponse
                    <ZhurongScjinggongOrderVO>> page(ZhurongScjinggongOrderPageQuery pageQuery) {

        LambdaQueryWrapper<ZhurongScjinggongOrder> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(ZhurongScjinggongOrder::getCreatedAt);

        Page<ZhurongScjinggongOrder> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <ZhurongScjinggongOrderVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <ZhurongScjinggongOrderVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <ZhurongScjinggongOrderVO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid ZhurongScjinggongOrderDTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid ZhurongScjinggongOrderDTO dto) {
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
