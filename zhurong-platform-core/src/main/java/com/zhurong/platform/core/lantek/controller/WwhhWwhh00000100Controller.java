package com.zhurong.platform.core.lantek.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.core.lantek.api.IWwhhWwhh00000100Api;
import com.zhurong.platform.core.lantek.convert.WwhhWwhh00000100Convert;
import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000100DTO;
import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000100PageQuery;
import com.zhurong.platform.core.lantek.entity.WwhhWwhh00000100;
import com.zhurong.platform.core.lantek.service.IWwhhWwhh00000100Service;
import com.zhurong.platform.core.lantek.vo.WwhhWwhh00000100VO;
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
@RequestMapping("/wwhhWwhh00000100")
public class WwhhWwhh00000100Controller extends BaseController implements IWwhhWwhh00000100Api {

    private final WwhhWwhh00000100Convert convert;
    private final IWwhhWwhh00000100Service service;

    @Override
    public ApiResponse
            <PageResponse
                    <WwhhWwhh00000100VO>> page(WwhhWwhh00000100PageQuery pageQuery) {

        LambdaQueryWrapper<WwhhWwhh00000100> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(WwhhWwhh00000100::getRecID);

        Page<WwhhWwhh00000100> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <WwhhWwhh00000100VO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <WwhhWwhh00000100VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @Override
    public ApiResponse
            <WwhhWwhh00000100VO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid WwhhWwhh00000100DTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse<Boolean> createWarehouse(WwhhWwhh00000100DTO dto) {
        if (StringUtils.isBlank(dto.getWrhRef())){
            return ApiResponse.fail("仓库编码不能为空");
        }
        Boolean result = service.forceCreationOfWarehouses(dto.getWrhRef());
        return ApiResponse.success(result);
    }

    @Override
    public ApiResponse<Boolean> createRepositoryLocation(WwhhWwhh00000100DTO dto) {
        if (StringUtils.isBlank(dto.getWrhRef())){
            return ApiResponse.fail("仓库编码不能为空");
        }
        if (StringUtils.isBlank(dto.getLocRef())){
            return ApiResponse.fail("库位编码不能为空");
        }
        Boolean result = service.forceTheCreationOfStorageLocations(dto.getWrhRef(), dto.getLocRef());
        return ApiResponse.success(result);
    }

    @Override
    public ApiResponse<Boolean> createWarehouseAndLocation(WwhhWwhh00000100DTO dto) {
        if (StringUtils.isBlank(dto.getWrhRef())){
            return ApiResponse.fail("仓库编码不能为空");
        }
        if (StringUtils.isBlank(dto.getLocRef())){
            return ApiResponse.fail("库位编码不能为空");
        }
        Boolean result = service.forceTheCreationOfWarehousesAndStorageLocations(dto.getWrhRef(), dto.getLocRef());
        return ApiResponse.success(result);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid WwhhWwhh00000100DTO dto) {
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
