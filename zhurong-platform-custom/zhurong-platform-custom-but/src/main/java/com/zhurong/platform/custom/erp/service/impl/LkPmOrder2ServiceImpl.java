package com.zhurong.platform.custom.erp.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.erp.entity.LkPmOrder;
import com.zhurong.platform.custom.erp.mapper.LkPmOrder2Mapper;
import com.zhurong.platform.custom.erp.service.ILkPmOrder2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Author zhurong
 * @Description ViPmOrderlServiceImpl
 * @Date 2026/3/29 15:52
 **/
@Service
@RequiredArgsConstructor
@DS("erp2")
public class LkPmOrder2ServiceImpl extends ServiceImpl<LkPmOrder2Mapper, LkPmOrder> implements ILkPmOrder2Service {

    @Override
    public boolean tryInserts(List<String> mnoRefs) {
        if (CollectionUtils.isEmpty(mnoRefs)){
            return false;
        }
        List<LkPmOrder> existing = list(Wrappers.lambdaQuery(LkPmOrder.class).in(LkPmOrder::getBelposId, mnoRefs));
        List<String> belposIds = existing.stream().map(LkPmOrder::getBelposId).toList();
        List<LkPmOrder> list = mnoRefs.stream()
                .filter(mnoRef -> CollectionUtils.isEmpty(existing) || !belposIds.contains(mnoRef))
                .map(mnoRef -> new LkPmOrder().setBelposId(mnoRef))
                .toList();
        if (CollectionUtils.isNotEmpty(list)) {
            return saveBatch(list);
        }
        return false;
    }
}
