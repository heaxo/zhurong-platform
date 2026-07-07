package com.zhurong.platform.custom.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.entity.WwccWwcc00000100;
import com.zhurong.platform.custom.mapper.WwccWwcc00000100Mapper;
import com.zhurong.platform.custom.service.IWwccWwcc00000100Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
@DS("lantek")
public class WwccWwcc00000100ServiceImpl
        extends ServiceImpl<WwccWwcc00000100Mapper, WwccWwcc00000100>
        implements IWwccWwcc00000100Service {


}
