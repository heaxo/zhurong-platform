package com.zhurong.platform.core.clientimport.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.entity.RawMaterial;
import com.zhurong.platform.core.clientimport.mapper.RawMaterialMapper;
import com.zhurong.platform.core.clientimport.service.RawMaterialService;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnClientCommunicationEnabled
public class RawMaterialServiceImpl
        extends ServiceImpl<RawMaterialMapper, RawMaterial>
        implements RawMaterialService {
}
