package com.zhurong.platform.custom.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.entity.WwhhIivv00000200;
import com.zhurong.platform.custom.mapper.WwhhIivv00000200Mapper;
import com.zhurong.platform.custom.service.IWwhhIivv00000200Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
@DS("lantek")
public class WwhhIivv00000200ServiceImpl
        extends ServiceImpl<WwhhIivv00000200Mapper, WwhhIivv00000200>
        implements IWwhhIivv00000200Service {

}
