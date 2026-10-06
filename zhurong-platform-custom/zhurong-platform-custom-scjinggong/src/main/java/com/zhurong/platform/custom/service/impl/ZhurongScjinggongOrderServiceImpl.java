package com.zhurong.platform.custom.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.custom.convert.ZhurongScjinggongOrderConvert;
import com.zhurong.platform.custom.convert.ZhurongScjinggongOrderitemConvert;
import com.zhurong.platform.custom.dto.OrderItemRequestDTO;
import com.zhurong.platform.custom.dto.OrderRequest;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderDTO;
import com.zhurong.platform.custom.entity.WwccWwcc00000100;
import com.zhurong.platform.custom.entity.ZhurongScjinggongOrder;
import com.zhurong.platform.custom.entity.ZhurongScjinggongOrderitem;
import com.zhurong.platform.custom.mapper.ZhurongScjinggongOrderMapper;
import com.zhurong.platform.custom.service.IDisMmttMmtt00000100Service;
import com.zhurong.platform.custom.service.IWwccWwcc00000100Service;
import com.zhurong.platform.custom.service.IZhurongScjinggongOrderService;
import com.zhurong.platform.custom.service.IZhurongScjinggongOrderitemService;
import com.zhurong.platform.custom.vo.ZhurongScjinggongOrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class ZhurongScjinggongOrderServiceImpl
        extends ServiceImpl<ZhurongScjinggongOrderMapper, ZhurongScjinggongOrder>
        implements IZhurongScjinggongOrderService {

    private final IZhurongScjinggongOrderitemService zhurongScjinggongOrderitemService;
    private final IWwccWwcc00000100Service wwccWwcc00000100Service;
    private final IDisMmttMmtt00000100Service disMmttMmtt00000100Service;
    private final ZhurongScjinggongOrderConvert convert;
    private final ZhurongScjinggongOrderitemConvert orderitemConvert;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean creates(OrderRequest request) {

        List<OrderItemRequestDTO> data = request.getData();
        String orderCode = request.getOrderCode();
        String orderName = request.getOrderName();

        ZhurongScjinggongOrder existing = getOne(Wrappers.lambdaQuery(ZhurongScjinggongOrder.class)
                .eq(ZhurongScjinggongOrder::getOrderCode, orderCode));

        if (existing != null) {
            throw new BusinessException(String.format("批次号已存在，无法重复保存：%s", orderCode));
        }

        //MES工单号
        List<String> cusRefs = data.stream().map(OrderItemRequestDTO::getCusRef).distinct().toList();

        if (cusRefs.size() < data.size()){
            throw new BusinessException(String.format("MES工单号重复，请检查"));
        }

        List<ZhurongScjinggongOrderitem> existings = zhurongScjinggongOrderitemService.list(Wrappers.lambdaQuery(ZhurongScjinggongOrderitem.class)
                .in(ZhurongScjinggongOrderitem::getCusRef, cusRefs));

        if (!existings.isEmpty()){
            List<String> keys = existings.stream().map(ZhurongScjinggongOrderitem::getCusRef).distinct().toList();
            throw new BusinessException(String.format("MES工单已存在，无法重复下发：%s", String.join(",", keys)));
        }

        List<WwccWwcc00000100> wwccs = wwccWwcc00000100Service.list();
//        List<DisMmttMmtt00000100> mmtts = disMmttMmtt00000100Service.list();

        List<String> wrkRefs = wwccs.stream().map(WwccWwcc00000100::getWrkRef).distinct().toList();
//        List<String> matRefs = mmtts.stream().map(DisMmttMmtt00000100::getMatRef).distinct().toList();

        List<String> nonWrkRefs = new ArrayList<>();
//        List<String> nonMatRefs = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            OrderItemRequestDTO d = data.get(i);
            if (!wrkRefs.contains(d.getWrkRef()) && !nonWrkRefs.contains(d.getWrkRef())){
                nonWrkRefs.add(d.getWrkRef());
            }
        }

        if (!nonWrkRefs.isEmpty()){
            throw new BusinessException(String.format("机床未维护，请打开套料软件维护相关机床：%s", String.join(",", nonWrkRefs)));
        }

        List<ZhurongScjinggongOrderitem> entitys = orderitemConvert.toEntitys(data);

        ZhurongScjinggongOrder zhurongScjinggongOrder = new ZhurongScjinggongOrder();
        zhurongScjinggongOrder.setOrderCode(orderCode);
        zhurongScjinggongOrder.setOrderName(orderName);

        boolean save = save(zhurongScjinggongOrder);

        if (!save){
            return false;
        }

        entitys.forEach(it -> it.setOrderId(zhurongScjinggongOrder.getId()));

        boolean batch = zhurongScjinggongOrderitemService.saveBatch(entitys);

        if (!batch){
            throw new BusinessException("订单保存失败，请重试");
        }
        return true;
    }


    @Override
    public ZhurongScjinggongOrderVO getVOById(Long id) {
        ZhurongScjinggongOrder entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(ZhurongScjinggongOrderDTO dto) {
        ZhurongScjinggongOrder entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, ZhurongScjinggongOrderDTO dto) {
        ZhurongScjinggongOrder entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
