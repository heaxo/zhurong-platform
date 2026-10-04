package com.zhurong.platform.custom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.convert.DisNestNest00000500Convert;
import com.zhurong.platform.custom.entity.DisNestNest00000500;
import com.zhurong.platform.custom.mapper.DisNestNest00000500Mapper;
import com.zhurong.platform.custom.service.IDisNestNest00000500Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class DisNestNest00000500ServiceImpl
        extends ServiceImpl<DisNestNest00000500Mapper, DisNestNest00000500>
        implements IDisNestNest00000500Service {

    private final DisNestNest00000500Convert convert;


}
