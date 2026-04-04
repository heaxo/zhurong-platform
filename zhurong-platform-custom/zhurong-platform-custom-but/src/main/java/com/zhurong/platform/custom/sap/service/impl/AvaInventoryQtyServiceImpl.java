package com.zhurong.platform.custom.sap.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.sap.entity.AvaInventoryQty;
import com.zhurong.platform.custom.sap.mapper.AvaInventoryQtyMapper;
import com.zhurong.platform.custom.sap.service.IAvaInventoryQtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
 * @Author zhurong
 * @Description ViPmOrderlServiceImpl
 * @Date 2026/3/29 15:52
 **/
@Service
@RequiredArgsConstructor
@DS("sap")
public class AvaInventoryQtyServiceImpl extends ServiceImpl<AvaInventoryQtyMapper, AvaInventoryQty> implements IAvaInventoryQtyService {

}
