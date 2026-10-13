package com.zhurong.platform.custom.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.custom.api.IZhurongScjinggongSteelplateinventoryApi;
import com.zhurong.platform.custom.convert.ZhurongScjinggongSteelplateinventoryConvert;
import com.zhurong.platform.custom.dto.U8InventoryRequest;
import com.zhurong.platform.custom.dto.U8InventoryResponse;
import com.zhurong.platform.custom.dto.ZhurongScjinggongSteelplateinventoryDTO;
import com.zhurong.platform.custom.dto.ZhurongScjinggongSteelplateinventoryPageQuery;
import com.zhurong.platform.custom.entity.ZhurongScjinggongSteelplateinventory;
import com.zhurong.platform.custom.service.IZhurongScjinggongSteelplateinventoryService;
import com.zhurong.platform.custom.service.impl.U8InventoryService;
import com.zhurong.platform.custom.vo.ZhurongScjinggongSteelplateinventoryVO;
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
@RequestMapping("/zhurongScjinggongSteelplateinventory")
public class ZhurongScjinggongSteelplateinventoryController extends BaseController implements IZhurongScjinggongSteelplateinventoryApi {

    private final ZhurongScjinggongSteelplateinventoryConvert convert;
    private final IZhurongScjinggongSteelplateinventoryService service;
    private final U8InventoryService u8InventoryService;

    @Override
    public ApiResponse
            <PageResponse
                    <ZhurongScjinggongSteelplateinventoryVO>> page(ZhurongScjinggongSteelplateinventoryPageQuery pageQuery) {

        LambdaQueryWrapper<ZhurongScjinggongSteelplateinventory> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(ZhurongScjinggongSteelplateinventory::getCreatedAt);

        Page<ZhurongScjinggongSteelplateinventory> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <ZhurongScjinggongSteelplateinventoryVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <ZhurongScjinggongSteelplateinventoryVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    @PostMapping("sync")
    public ApiResponse<Boolean> sync(@RequestBody U8InventoryRequest request){
        try{
            List<U8InventoryResponse> query = u8InventoryService.query(request);
            if (query.isEmpty()){
                return ApiResponse.success(false);
            }
            //TODO 将query结果保存到数据库中，存在则更新，不存在则新增
            return ApiResponse.success(true);
        }catch (Exception e){
            return ApiResponse.fail(e.getMessage());
        }
    }
    @PostMapping("importToExpert")
    public ApiResponse<Boolean> importToExpert(@RequestBody ZhurongScjinggongSteelplateinventoryDTO request){
        try{
            Boolean importToExpert = service.importToExpert(request);
            return ApiResponse.success(importToExpert);
        }catch (Exception e){
            return ApiResponse.fail(e.getMessage());
        }
    }

    @Override
    public ApiResponse
            <ZhurongScjinggongSteelplateinventoryVO> getById(Long id) {
        return ApiResponse.success(service.getVOById(id));
    }

    @Override
    public ApiResponse
            <Long> save(@Valid ZhurongScjinggongSteelplateinventoryDTO dto) {
        Long id = service.saveFromDTO(dto);
        return ApiResponse.success(id);
    }

    @Override
    public ApiResponse
            <Boolean> update(Long id, @Valid ZhurongScjinggongSteelplateinventoryDTO dto) {
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
