package com.zhurong.platform.custom.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.convert.PprrPprr00000100Convert;
import com.zhurong.platform.custom.entity.PprrPprr00000100;
import com.zhurong.platform.custom.mapper.PprrPprr00000100Mapper;
import com.zhurong.platform.custom.service.IPprrPprr00000100Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
@DS("lantek")
public class PprrPprr00000100ServiceImpl
        extends ServiceImpl<PprrPprr00000100Mapper, PprrPprr00000100>
        implements IPprrPprr00000100Service {

    private final PprrPprr00000100Convert convert;

}
