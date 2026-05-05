package com.zhurong.platform.custom.erp.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.erp.entity.ViPmOrderl;
import com.zhurong.platform.custom.erp.mapper.ViPmOrderl2Mapper;
import com.zhurong.platform.custom.erp.service.IViPmOrderl2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
 * @Author zhurong
 * @Description ViPmOrderlServiceImpl
 * @Date 2026/3/29 15:52
 **/
@Service
@RequiredArgsConstructor
@Slf4j
@DS("erp2")
public class ViPmOrderl2ServiceImpl extends ServiceImpl<ViPmOrderl2Mapper, ViPmOrderl> implements IViPmOrderl2Service {

}
