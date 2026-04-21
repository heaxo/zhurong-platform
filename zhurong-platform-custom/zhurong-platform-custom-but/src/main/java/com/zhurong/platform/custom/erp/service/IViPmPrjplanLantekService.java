package com.zhurong.platform.custom.erp.service;

import com.zhurong.platform.custom.erp.entity.ViPmPrjplanLantek;
import com.zhurong.platform.custom.service.BaseIService;

public interface IViPmPrjplanLantekService extends BaseIService<ViPmPrjplanLantek> {

    /**
     * 同步供应商信息
     * @return 同步结果
     */
    boolean syncSupplierInfo();

    /**
     * 更新库存引用
     * @return 更新结果
     */
    boolean updateInventoryReferences();

}
