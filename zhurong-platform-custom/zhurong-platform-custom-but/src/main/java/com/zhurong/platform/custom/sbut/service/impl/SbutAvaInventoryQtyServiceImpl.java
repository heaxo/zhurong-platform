package com.zhurong.platform.custom.sbut.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.sbut.entity.SbutAvaInventoryQty;
import com.zhurong.platform.custom.sbut.mapper.SbutAvaInventoryQtyMapper;
import com.zhurong.platform.custom.sbut.service.ISbutAvaInventoryQtyService;
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
@DS("sbut")
public class SbutAvaInventoryQtyServiceImpl extends ServiceImpl<SbutAvaInventoryQtyMapper, SbutAvaInventoryQty> implements ISbutAvaInventoryQtyService {

}
