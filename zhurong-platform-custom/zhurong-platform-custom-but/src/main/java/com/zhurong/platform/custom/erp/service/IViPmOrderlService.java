package com.zhurong.platform.custom.erp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhurong.platform.custom.erp.dto.ViPmOrderlDTO;
import com.zhurong.platform.custom.erp.entity.ViPmOrderl;

public interface IViPmOrderlService extends IService<ViPmOrderl> {
    //导入至套料软件中
    boolean importToExpert(ViPmOrderlDTO dto);
}
