package com.zhurong.platform.custom.erp.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.erp.entity.ViPmPrjplanLantek;
import com.zhurong.platform.custom.erp.mapper.ViPmPrjplanLantek2Mapper;
import com.zhurong.platform.custom.erp.service.IViPmPrjplanLantek2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
 * @Author zhurong
 * @Description 下料程序名位置视图
 * @Date 2026/3/29 15:52
 **/
@Service
@RequiredArgsConstructor
@Slf4j
@DS("erp2")
public class ViPmPrjplanLantek2ServiceImpl extends ServiceImpl<ViPmPrjplanLantek2Mapper, ViPmPrjplanLantek> implements IViPmPrjplanLantek2Service {

}
