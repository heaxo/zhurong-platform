package com.zhurong.platform.custom.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.entity.DisMmttMmtt00000100;
import com.zhurong.platform.custom.mapper.DisMmttMmtt00000100Mapper;
import com.zhurong.platform.custom.service.IDisMmttMmtt00000100Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
@DS("lantek")
public class DisMmttMmtt00000100ServiceImpl
        extends ServiceImpl<DisMmttMmtt00000100Mapper, DisMmttMmtt00000100>
        implements IDisMmttMmtt00000100Service {


}
