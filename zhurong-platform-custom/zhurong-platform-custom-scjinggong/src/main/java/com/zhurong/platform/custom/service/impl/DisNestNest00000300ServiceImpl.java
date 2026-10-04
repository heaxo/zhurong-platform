package com.zhurong.platform.custom.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.entity.DisNestNest00000300;
import com.zhurong.platform.custom.mapper.DisNestNest00000300Mapper;
import com.zhurong.platform.custom.service.IDisNestNest00000300Service;
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
public class DisNestNest00000300ServiceImpl
        extends ServiceImpl<DisNestNest00000300Mapper, DisNestNest00000300>
        implements IDisNestNest00000300Service {

}
