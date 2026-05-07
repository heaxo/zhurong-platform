package com.zhurong.platform.custom.sap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhurong.platform.custom.sap.dto.AvaInventoryQtyDTO;
import com.zhurong.platform.custom.sap.entity.AvaInventoryQty;

public interface IAvaInventoryQtyService extends IService<AvaInventoryQty> {
    boolean clearAllInventory();
    int importInventory(AvaInventoryQtyDTO dto);
}
