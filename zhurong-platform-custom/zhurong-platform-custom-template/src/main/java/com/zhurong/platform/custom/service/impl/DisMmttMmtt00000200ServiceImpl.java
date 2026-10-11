package com.zhurong.platform.custom.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.entity.DisMmttMmtt00000200;
import com.zhurong.platform.custom.mapper.DisMmttMmtt00000200Mapper;
import com.zhurong.platform.custom.service.IDisMmttMmtt00000200Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
@DS("lantek")
public class DisMmttMmtt00000200ServiceImpl
        extends ServiceImpl<DisMmttMmtt00000200Mapper, DisMmttMmtt00000200>
        implements IDisMmttMmtt00000200Service {

}
