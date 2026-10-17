package com.zhurong.platform.custom.mes.service;

import com.zhurong.platform.custom.mes.entity.ScjinggongMesInventory;
import com.zhurong.platform.custom.service.BaseIService;

import java.util.List;

public interface IScjinggongMesInventoryService extends BaseIService<ScjinggongMesInventory> {

    List<ScjinggongMesInventory> listByInventoryCodes(List<String> inventoryCodes);
}
