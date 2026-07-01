package com.zhurong.platform.custom.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.entity.WwhhIivv00000100;
import com.zhurong.platform.custom.mapper.WwhhIivv00000100Mapper;
import com.zhurong.platform.custom.service.IWwhhIivv00000100Service;
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
public class WwhhIivv00000100ServiceImpl
        extends ServiceImpl<WwhhIivv00000100Mapper, WwhhIivv00000100>
        implements IWwhhIivv00000100Service {

}
