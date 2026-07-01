package com.zhurong.platform.custom.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.entity.DisMmnnMmoo00000200;
import com.zhurong.platform.custom.mapper.DisMmnnMmoo00000200Mapper;
import com.zhurong.platform.custom.service.IDisMmnnMmoo00000200Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
@DS("lantek")
public class DisMmnnMmoo00000200ServiceImpl
        extends ServiceImpl<DisMmnnMmoo00000200Mapper, DisMmnnMmoo00000200>
        implements IDisMmnnMmoo00000200Service {


}
