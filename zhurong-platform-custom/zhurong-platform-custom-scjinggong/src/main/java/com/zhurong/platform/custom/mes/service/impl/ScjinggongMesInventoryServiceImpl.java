package com.zhurong.platform.custom.mes.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.mes.entity.ScjinggongMesInventory;
import com.zhurong.platform.custom.mes.mapper.ScjinggongMesInventoryMapper;
import com.zhurong.platform.custom.mes.service.IScjinggongMesInventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("mes")
public class ScjinggongMesInventoryServiceImpl
        extends ServiceImpl<ScjinggongMesInventoryMapper, ScjinggongMesInventory>
        implements IScjinggongMesInventoryService {

    @Override
    public List<ScjinggongMesInventory> listByInventoryCodes(List<String> inventoryCodes) {
        if (CollectionUtils.isEmpty(inventoryCodes)) {
            return List.of();
        }
        return listByIn(
                Wrappers.lambdaQuery(ScjinggongMesInventory.class),
                ScjinggongMesInventory::getInvCode,
                inventoryCodes
        );
    }
}
