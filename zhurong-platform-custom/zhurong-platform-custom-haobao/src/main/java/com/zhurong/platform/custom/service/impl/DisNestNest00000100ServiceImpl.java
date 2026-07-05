package com.zhurong.platform.custom.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.entity.DisNestNest00000100;
import com.zhurong.platform.custom.mapper.DisNestNest00000100Mapper;
import com.zhurong.platform.custom.service.IDisNestNest00000100Service;
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
public class DisNestNest00000100ServiceImpl
        extends ServiceImpl<DisNestNest00000100Mapper, DisNestNest00000100>
        implements IDisNestNest00000100Service {
}
