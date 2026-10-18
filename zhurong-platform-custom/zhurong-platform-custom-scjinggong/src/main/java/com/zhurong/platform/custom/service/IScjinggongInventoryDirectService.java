package com.zhurong.platform.custom.service;

import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.custom.dto.ScjinggongInventoryDirectDTO;
import com.zhurong.platform.custom.dto.ScjinggongInventoryDirectPageQuery;
import com.zhurong.platform.custom.dto.U8InventoryBatchRequest;
import com.zhurong.platform.custom.dto.U8InventoryResponse;
import com.zhurong.platform.custom.vo.ScjinggongInventoryDirectVO;

import java.io.IOException;
import java.util.List;

/**
 * 不经过中间表的库存查询、U8加载和Expert导入服务。
 */
public interface IScjinggongInventoryDirectService {

    PageResponse<ScjinggongInventoryDirectVO> page(ScjinggongInventoryDirectPageQuery pageQuery);

    List<U8InventoryResponse> loadFromU8(U8InventoryBatchRequest request);

    boolean importToExpert(List<ScjinggongInventoryDirectDTO> inventoryList) throws IOException;
}
