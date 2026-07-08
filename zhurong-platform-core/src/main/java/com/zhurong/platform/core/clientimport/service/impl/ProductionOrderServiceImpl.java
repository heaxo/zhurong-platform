package com.zhurong.platform.core.clientimport.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.entity.ProductionOrder;
import com.zhurong.platform.core.clientimport.mapper.ProductionOrderMapper;
import com.zhurong.platform.core.clientimport.service.ProductionOrderService;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnClientCommunicationEnabled
public class ProductionOrderServiceImpl
        extends ServiceImpl<ProductionOrderMapper, ProductionOrder>
        implements ProductionOrderService {
}
