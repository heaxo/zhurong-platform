package com.zhurong.platform.custom.erp.service;

import com.zhurong.platform.custom.erp.entity.ViPmPrjreportsLantek;
import com.zhurong.platform.custom.service.BaseIService;

public interface IViPmPrjreportsLantek2Service extends BaseIService<ViPmPrjreportsLantek> {

    //同步报工套料程序
    boolean syncReportNestingProgram();

}
