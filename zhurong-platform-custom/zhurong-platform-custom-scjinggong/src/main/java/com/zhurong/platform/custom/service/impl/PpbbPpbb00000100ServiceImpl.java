package com.zhurong.platform.custom.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.entity.PpbbPpbb00000100;
import com.zhurong.platform.custom.mapper.PpbbPpbb00000100Mapper;
import com.zhurong.platform.custom.service.IPpbbPpbb00000100Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
@DS("lantek")
public class PpbbPpbb00000100ServiceImpl
        extends ServiceImpl<PpbbPpbb00000100Mapper, PpbbPpbb00000100>
        implements IPpbbPpbb00000100Service {

}
