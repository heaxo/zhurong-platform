package com.zhurong.platform.core.lantek.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.lantek.constants.LantekTableDefaultValue;
import com.zhurong.platform.core.lantek.convert.WwhhIivv00000100Convert;
import com.zhurong.platform.core.lantek.dto.WwhhIivv00000100DTO;
import com.zhurong.platform.core.lantek.entity.WwhhIivv00000100;
import com.zhurong.platform.core.lantek.mapper.WwhhIivv00000100Mapper;
import com.zhurong.platform.core.lantek.service.IWwhhIivv00000100Service;
import com.zhurong.platform.core.lantek.vo.WwhhIivv00000100VO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WwhhIivv00000100ServiceImpl
        extends ServiceImpl<WwhhIivv00000100Mapper, WwhhIivv00000100>
        implements IWwhhIivv00000100Service {

    private final WwhhIivv00000100Convert convert;


    @Override
    public WwhhIivv00000100VO getVOById(Long id) {
        WwhhIivv00000100 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(WwhhIivv00000100DTO dto) {
        WwhhIivv00000100 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, WwhhIivv00000100DTO dto) {
        WwhhIivv00000100 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }

    @Override
    public Boolean forceSheetMetalBinding(WwhhIivv00000100DTO dto) {
        WwhhIivv00000100 exist = getOne(Wrappers.lambdaQuery(WwhhIivv00000100.class)
                .eq(WwhhIivv00000100::getWrhRef, dto.getWrhRef())
                .eq(WwhhIivv00000100::getPrdRef, dto.getPrdRef())
                .eq(WwhhIivv00000100::getLocDefault, dto.getLocDefault())
        );
        if (exist != null){
            boolean update = update(Wrappers.lambdaUpdate(WwhhIivv00000100.class)
                    .set(WwhhIivv00000100::getAllocatedQ, dto.getAllocatedQ())
                    .eq(WwhhIivv00000100::getRecID, exist.getRecID())
            );
            log.info("库存绑定更新：{}", update);
            return update;
        }
        WwhhIivv00000100 wwhhIivv00000100 = new WwhhIivv00000100()
                .setWrhRef(dto.getWrhRef())
                .setPrdRef(dto.getPrdRef())
                .setPrdName("")
                .setUCtName("UNT")
                .setUntName("unt")
                .setStockQ(0D)
                .setAllocatedQ(dto.getAllocatedQ())
                .setOnOrderQ(0D)
                .setPendingQ(0D)
                .setUpdMethod(3)
                .setIsDefault((byte) 1)
                .setMinStock(1D)
                .setMinOrder(0D)
                .setMaxOrder(0D)
                .setMultOrder(0D)
                .setStrategy(0)
                .setLocDefault(dto.getLocDefault())
                .setWeight(0D)
                .setRWeight(0D)
                .setGLS_Var1("")
                .setGLS_Var2("")
                .setGLS_Var3("")
                .setGLS_Var4("")
                .setGLS_Var5("")
                .setRecState(0)
                .setCrtDate(LocalDateTime.now())
                .setLastDate(LocalDateTime.now())
                .setCrtUser(LantekTableDefaultValue.User)
                .setLastUser(LantekTableDefaultValue.User)
                .setOwner(LantekTableDefaultValue.User)
                .setRecEnt(LantekTableDefaultValue.Ment)
                .setRecOU(LantekTableDefaultValue.Ment)
                .setRecSec(0)
                .setCntID(-1);
        WwhhIivv00000100 last = getOne(Wrappers.lambdaQuery(WwhhIivv00000100.class)
                .orderByDesc(WwhhIivv00000100::getRecID));
        wwhhIivv00000100.setRecID(last.getRecID() + 1);
        if (!save(wwhhIivv00000100)){
            throw new BusinessException("仓库库位绑定失败，请重新提交");
        }
        boolean save = save(wwhhIivv00000100);
        log.info("库存绑定：{}", save);
        return save;
    }
}
