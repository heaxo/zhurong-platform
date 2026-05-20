package com.zhurong.platform.custom.erp.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhurong.platform.custom.erp.entity.ViPmPrjplanLantek;

import java.util.List;

@DS("erp2")
public interface ViPmPrjplanLantek2Mapper extends BaseMapper<ViPmPrjplanLantek> {
    List<ViPmPrjplanLantek> getSupplierInfo();
}
