package com.zhurong.platform.custom.erp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhurong.platform.custom.erp.entity.LkPmOrder;

import java.util.List;

public interface ILkPmOrder2Service extends IService<LkPmOrder> {
    boolean tryInserts(List<String> mnoRefs);
}
