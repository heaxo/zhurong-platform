package com.zhurong.platform.custom.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.entity.MmnnMmoo00000300;
import com.zhurong.platform.custom.mapper.MmnnMmoo00000300Mapper;
import com.zhurong.platform.custom.service.IMmnnMmoo00000300Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
@DS("lantek")
public class MmnnMmoo00000300ServiceImpl
        extends ServiceImpl<MmnnMmoo00000300Mapper, MmnnMmoo00000300>
        implements IMmnnMmoo00000300Service {


}
