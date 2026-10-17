package com.zhurong.platform.custom.service;

import com.zhurong.platform.custom.dto.U8InventoryResponse;
import com.zhurong.platform.custom.dto.ZhurongScjinggongSteelplateinventoryDTO;
import com.zhurong.platform.custom.entity.ZhurongScjinggongSteelplateinventory;
import com.zhurong.platform.custom.vo.ZhurongScjinggongSteelplateinventoryVO;

import java.io.IOException;
import java.util.List;

/**
 * 服务接口
 */
public interface IZhurongScjinggongSteelplateinventoryService extends BaseIService<ZhurongScjinggongSteelplateinventory> {
    ZhurongScjinggongSteelplateinventoryVO getVOById(Long id);

    Long saveFromDTO(ZhurongScjinggongSteelplateinventoryDTO dto);

    Boolean updateFromDTO(Long id, ZhurongScjinggongSteelplateinventoryDTO dto);

    Boolean syncFromU8(List<U8InventoryResponse> inventoryList);

    Boolean importToExpert(ZhurongScjinggongSteelplateinventoryDTO dto) throws IOException;
}
