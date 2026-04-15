package com.zhurong.platform.core.lantek.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.core.lantek.api.IWwhhIivv00000100Api;
import com.zhurong.platform.core.lantek.convert.WwhhIivv00000100Convert;
import com.zhurong.platform.core.lantek.dto.WwhhIivv00000100DTO;
import com.zhurong.platform.core.lantek.dto.WwhhIivv00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.WwhhIivv00000100;
import com.zhurong.platform.core.lantek.service.IWwhhIivv00000100Service;
import com.zhurong.platform.core.lantek.vo.WwhhIivv00000100VO;
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
@RequestMapping("/wwhhIivv00000100")
public class WwhhIivv00000100Controller extends BaseController implements IWwhhIivv00000100Api {

    private final WwhhIivv00000100Convert convert;
    private final IWwhhIivv00000100Service service;

    @Override
    public ApiResponse
            <PageResponse
                    <WwhhIivv00000100VO>> page(WwhhIivv00000100PageQuery pageQuery) {

        LambdaQueryWrapper<WwhhIivv00000100> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(WwhhIivv00000100::getRecID);

        Page<WwhhIivv00000100> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <WwhhIivv00000100VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <WwhhIivv00000100VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <WwhhIivv00000100VO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid WwhhIivv00000100DTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse<Boolean> sheetMetalBinding(WwhhIivv00000100DTO dto) {
        if (StringUtils.isBlank(dto.getWrhRef())){
            return ApiResponse.fail("仓库编码不能为空");
        }
        if (StringUtils.isBlank(dto.getLocDefault())){
            return ApiResponse.fail("库位编码不能为空");
        }
        if (StringUtils.isBlank(dto.getPrdRef())){
            return ApiResponse.fail("单项编码不能为空");
        }
        if (dto.getAllocatedQ() == null){
            return ApiResponse.fail("分配数量不能为空");
        }
        return ApiResponse.success(service.forceSheetMetalBinding(dto));
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid WwhhIivv00000100DTO dto) {
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
