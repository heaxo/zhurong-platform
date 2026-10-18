package com.zhurong.platform.custom.api;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.custom.dto.ScjinggongInventoryDirectDTO;
import com.zhurong.platform.custom.dto.ScjinggongInventoryDirectPageQuery;
import com.zhurong.platform.custom.dto.U8InventoryBatchRequest;
import com.zhurong.platform.custom.dto.U8InventoryResponse;
import com.zhurong.platform.custom.vo.ScjinggongInventoryDirectVO;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 新库存同步页面接口契约，不依赖钢板库存中间表。
 */
public interface IScjinggongInventoryDirectApi {

    @GetMapping("/page")
    ApiResponse<PageResponse<ScjinggongInventoryDirectVO>> page(
            @SpringQueryMap ScjinggongInventoryDirectPageQuery pageQuery
    );

    @PostMapping("/loadFromU8")
    ApiResponse<List<U8InventoryResponse>> loadFromU8(@RequestBody U8InventoryBatchRequest request);

    @PostMapping("/importToExpert")
    ApiResponse<Boolean> importToExpert(@RequestBody List<ScjinggongInventoryDirectDTO> inventoryList);
}
