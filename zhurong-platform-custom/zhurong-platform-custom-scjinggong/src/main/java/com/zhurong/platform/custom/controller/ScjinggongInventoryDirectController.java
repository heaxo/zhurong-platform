package com.zhurong.platform.custom.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.custom.api.IScjinggongInventoryDirectApi;
import com.zhurong.platform.custom.dto.ScjinggongInventoryDirectDTO;
import com.zhurong.platform.custom.dto.ScjinggongInventoryDirectPageQuery;
import com.zhurong.platform.custom.dto.U8InventoryBatchRequest;
import com.zhurong.platform.custom.dto.U8InventoryResponse;
import com.zhurong.platform.custom.service.IScjinggongInventoryDirectService;
import com.zhurong.platform.custom.vo.ScjinggongInventoryDirectVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 基于MES视图的库存同步接口，不读写旧钢板库存中间表。
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/scjinggongInventoryDirect")
public class ScjinggongInventoryDirectController implements IScjinggongInventoryDirectApi {

    private final IScjinggongInventoryDirectService service;

    @Override
    public ApiResponse<PageResponse<ScjinggongInventoryDirectVO>> page(
            ScjinggongInventoryDirectPageQuery pageQuery
    ) {
        try {
            return ApiResponse.success(service.page(pageQuery));
        } catch (Exception exception) {
            log.error("MES物料主数据视图分页查询失败", exception);
            return ApiResponse.fail(exception.getMessage());
        }
    }

    @Override
    public ApiResponse<List<U8InventoryResponse>> loadFromU8(U8InventoryBatchRequest request) {
        try {
            return ApiResponse.success(service.loadFromU8(request));
        } catch (Exception exception) {
            log.error("从U8批量加载库存失败", exception);
            return ApiResponse.fail(exception.getMessage());
        }
    }

    @Override
    public ApiResponse<Boolean> importToExpert(List<ScjinggongInventoryDirectDTO> inventoryList) {
        try {
            return ApiResponse.success(service.importToExpert(inventoryList));
        } catch (Exception exception) {
            log.error("新库存页面直接导入Expert失败", exception);
            return ApiResponse.fail(exception.getMessage());
        }
    }
}
