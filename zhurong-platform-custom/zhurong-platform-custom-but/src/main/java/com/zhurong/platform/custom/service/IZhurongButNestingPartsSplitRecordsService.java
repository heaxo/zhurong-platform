package com.zhurong.platform.custom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhurong.platform.custom.dto.ZhurongButNestingPartsSplitRecordsCreateDTO;
import com.zhurong.platform.custom.entity.ZhurongButNestingPartsSplitRecords;

import java.util.List;

/**
*  服务接口
*/
public interface IZhurongButNestingPartsSplitRecordsService extends IService<ZhurongButNestingPartsSplitRecords> {
    /**
     * 拆分记录覆盖
     * 幂等
     * @return 保存结果
     */
    boolean splitRecordsOverwrite(List<ZhurongButNestingPartsSplitRecordsCreateDTO> records);
}
