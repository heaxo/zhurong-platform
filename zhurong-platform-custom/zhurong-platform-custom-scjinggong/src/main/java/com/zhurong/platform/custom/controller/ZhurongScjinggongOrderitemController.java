package com.zhurong.platform.custom.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.custom.api.IZhurongScjinggongOrderitemApi;
import com.zhurong.platform.custom.convert.ZhurongScjinggongOrderitemConvert;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderitemDTO;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderitemPageQuery;
import com.zhurong.platform.custom.entity.ZhurongScjinggongBasepart;
import com.zhurong.platform.custom.entity.ZhurongScjinggongOrderitem;
import com.zhurong.platform.custom.service.IZhurongScjinggongBasepartService;
import com.zhurong.platform.custom.service.IZhurongScjinggongOrderitemService;
import com.zhurong.platform.custom.vo.ZhurongScjinggongOrderitemVO;
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
@RequestMapping("/zhurongScjinggongOrderitem")
public class ZhurongScjinggongOrderitemController extends BaseController implements IZhurongScjinggongOrderitemApi {

    private final ZhurongScjinggongOrderitemConvert convert;
    private final IZhurongScjinggongOrderitemService service;
    private final IZhurongScjinggongBasepartService basepartService;

    @Override
    public ApiResponse
            <PageResponse
                    <ZhurongScjinggongOrderitemVO>> page(ZhurongScjinggongOrderitemPageQuery pageQuery) {

        LambdaQueryWrapper<ZhurongScjinggongOrderitem> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(ZhurongScjinggongOrderitem::getCreatedAt);

        Page<ZhurongScjinggongOrderitem> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <ZhurongScjinggongOrderitemVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        List<String> prdRefs = voList.stream().map(ZhurongScjinggongOrderitemVO::getPrdRef).distinct().toList();

        if (!prdRefs.isEmpty()){
            List<ZhurongScjinggongBasepart> baseparts = basepartService.list(Wrappers.lambdaQuery(ZhurongScjinggongBasepart.class)
                    .in(ZhurongScjinggongBasepart::getPrdRef, prdRefs));

            List<String> _prdRefs = baseparts.stream()
                    .map(ZhurongScjinggongBasepart::getPrdRef)
                    .map(String::toLowerCase)
                    .distinct().toList();
            voList.forEach(it -> {
                it.setPartIssued(_prdRefs.contains(it.getPrdRef().toLowerCase()));
            });

        }

        PageResponse
                <ZhurongScjinggongOrderitemVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <ZhurongScjinggongOrderitemVO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid ZhurongScjinggongOrderitemDTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid ZhurongScjinggongOrderitemDTO dto) {
        boolean update = service.updateFromDTO(id, dto);
        return ApiResponse.success(update);
    }

    @Override
    public ApiResponse
            <Boolean> importToExpert(@Valid ZhurongScjinggongOrderitemDTO dto) {
        try{
            boolean result = service.importToExpert(dto);
            return ApiResponse.success(result);
        }catch (Exception e){
            return ApiResponse.fail(e.getMessage());
        }
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
