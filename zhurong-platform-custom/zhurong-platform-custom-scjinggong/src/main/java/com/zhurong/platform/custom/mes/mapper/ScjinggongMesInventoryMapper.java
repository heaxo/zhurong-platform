package com.zhurong.platform.custom.mes.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhurong.platform.custom.mes.entity.ScjinggongMesInventory;

/**
 * 亚威MES只读视图Mapper。
 */
@DS("mes")
public interface ScjinggongMesInventoryMapper extends BaseMapper<ScjinggongMesInventory> {
}
