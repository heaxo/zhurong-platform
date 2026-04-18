package com.zhurong.platform.core.lantek.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.lantek.constants.LantekTableDefaultValue;
import com.zhurong.platform.core.lantek.convert.WwhhWwhh00000100Convert;
import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000100DTO;
import com.zhurong.platform.core.lantek.entity.SystCcpp00000100;
import com.zhurong.platform.core.lantek.entity.WwhhWwhh00000100;
import com.zhurong.platform.core.lantek.entity.WwhhWwhh00000200;
import com.zhurong.platform.core.lantek.mapper.WwhhWwhh00000100Mapper;
import com.zhurong.platform.core.lantek.service.ISystCcpp00000100Service;
import com.zhurong.platform.core.lantek.service.IWwhhWwhh00000100Service;
import com.zhurong.platform.core.lantek.service.IWwhhWwhh00000200Service;
import com.zhurong.platform.core.lantek.vo.WwhhWwhh00000100VO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class WwhhWwhh00000100ServiceImpl
        extends ServiceImpl<WwhhWwhh00000100Mapper, WwhhWwhh00000100>
        implements IWwhhWwhh00000100Service {

    private final WwhhWwhh00000100Convert convert;
    private final ISystCcpp00000100Service systCcpp00000100Service;
    private final IWwhhWwhh00000200Service wwhhWwhh00000200Service;


    @Override
    public WwhhWwhh00000100VO getVOById(Long id) {
        WwhhWwhh00000100 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(WwhhWwhh00000100DTO dto) {
        WwhhWwhh00000100 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, WwhhWwhh00000100DTO dto) {
        WwhhWwhh00000100 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }

    /**
     * 强制创建仓库
     * @param warehouse 仓库编码
     * @return 结果
     */
    @Override
    public Boolean forceCreationOfWarehouses(String warehouse){

        //检查是否已开启库存模块
        SystCcpp00000100 disWhmSheetManagement = systCcpp00000100Service.getOne(Wrappers.lambdaQuery(SystCcpp00000100.class)
                .eq(SystCcpp00000100::getParName, "DIS_WHM_SheetManagement"), false);

        if (disWhmSheetManagement == null){
            //开启库存模块
            SystCcpp00000100 systCcpp00000100 = new SystCcpp00000100()
                    .setEType(-1)
                    .setEName("")
                    .setParName("DIS_WHM_SheetManagement")
                    .setParValue("1")
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
            SystCcpp00000100 last = systCcpp00000100Service.getOne(Wrappers.lambdaQuery(SystCcpp00000100.class)
                            .select(SystCcpp00000100::getRecID)
                    .orderByDesc(SystCcpp00000100::getRecID), false);
            if (last == null){
                last = new SystCcpp00000100();
                last.setRecID(0);
            }
            systCcpp00000100.setRecID(last.getRecID() + 1);
            if (!systCcpp00000100Service.save(systCcpp00000100)){
                throw new BusinessException("库存管理模块【DIS_WHM_SheetManagement】开启失败，请重新提交");
            }
        }
        SystCcpp00000100 disWhmSheetWarehouse = systCcpp00000100Service.getOne(Wrappers.lambdaQuery(SystCcpp00000100.class)
                .eq(SystCcpp00000100::getParName, "DIS_WHM_SheetWarehouse")
                .eq(SystCcpp00000100::getParValue, warehouse), false);
        if (disWhmSheetWarehouse == null){
            SystCcpp00000100 systCcpp00000100 = new SystCcpp00000100()
                    .setEType(-1)
                    .setEName("")
                    .setParName("DIS_WHM_SheetWarehouse")
                    .setParValue(warehouse)
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
            SystCcpp00000100 last = systCcpp00000100Service.getOne(Wrappers.lambdaQuery(SystCcpp00000100.class)
                    .orderByDesc(SystCcpp00000100::getRecID), false);
            if (last == null){
                last = new SystCcpp00000100();
                last.setRecID(0);
            }
            systCcpp00000100.setRecID(last.getRecID() + 1);
            if (!systCcpp00000100Service.save(systCcpp00000100)){
                throw new BusinessException("库存模块【DIS_WHM_SheetWarehouse】开启失败，请重新提交");
            }
        }

        WwhhWwhh00000100 wwhhWwhh00000100 = getOne(Wrappers.lambdaQuery(WwhhWwhh00000100.class)
                .eq(WwhhWwhh00000100::getWrhRef, warehouse), false);

        if (wwhhWwhh00000100 == null){
            WwhhWwhh00000100 save = new WwhhWwhh00000100()
                    .setWrhRef(warehouse)
                    .setWrhName(warehouse)
                    .setCrtDate(LocalDateTime.now())
                    .setLastDate(LocalDateTime.now())
                    .setCrtUser(LantekTableDefaultValue.User)
                    .setLastUser(LantekTableDefaultValue.User)
                    .setOwner(LantekTableDefaultValue.User)
                    .setRecEnt(LantekTableDefaultValue.Ment)
                    .setRecOU(LantekTableDefaultValue.Ment)
                    .setCntID(-1);
            WwhhWwhh00000100 last = getOne(Wrappers.lambdaQuery(WwhhWwhh00000100.class)
                    .orderByDesc(WwhhWwhh00000100::getRecID), false);
            if (last == null){
                last = new WwhhWwhh00000100();
                last.setRecID(0);
            }
            save.setRecID(last.getRecID() + 1);
            if (!save(save)){
                throw new BusinessException("库存模块：仓库创建失败，请重新提交");
            }
        }

        return true;
    }

    /**
     * 强制创建库存位置
     * @param warehouse 仓库编码
     * @param location 库位
     * @return 结果
     */
    @Override
    public Boolean forceTheCreationOfStorageLocations(String warehouse,String location){
        //检查是否已开启库存库位模块
        SystCcpp00000100 disWhmSheetManagement = systCcpp00000100Service.getOne(Wrappers.lambdaQuery(SystCcpp00000100.class)
                .eq(SystCcpp00000100::getParName, "DIS_WHM_SheetLocation"), false);

        if (disWhmSheetManagement == null){
            //开启库存模块
            SystCcpp00000100 systCcpp00000100 = new SystCcpp00000100()
                    .setEType(-1)
                    .setEName("")
                    .setParName("DIS_WHM_SheetLocation")
                    .setParValue(location)
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
            SystCcpp00000100 last = systCcpp00000100Service.getOne(Wrappers.lambdaQuery(SystCcpp00000100.class)
                    .orderByDesc(SystCcpp00000100::getRecID), false);
            if (last == null){
                last = new SystCcpp00000100();
                last.setRecID(0);
            }
            systCcpp00000100.setRecID(last.getRecID() + 1);
            if (!systCcpp00000100Service.save(systCcpp00000100)){
                throw new BusinessException("库存库位模块【DIS_WHM_SheetLocation】开启失败，请重新提交");
            }
        }

        WwhhWwhh00000200 wwhh00000200 = wwhhWwhh00000200Service.getOne(Wrappers.lambdaQuery(WwhhWwhh00000200.class)
                .eq(WwhhWwhh00000200::getWrhRef, warehouse)
                .eq(WwhhWwhh00000200::getLocRef, location), false);

        if (wwhh00000200 == null){
            WwhhWwhh00000200 save = new WwhhWwhh00000200()
                    .setWrhRef(warehouse)
                    .setLocRef(location)
                    .setLOrder(1)
                    .setLocName("")
                    .setLocParent("")
                    .setIsEnd((byte) 1)
                    .setWidth(0D)
                    .setHeight(0D)
                    .setLength(0D)
                    .setWeight(0D)
                    .setDIS_IsWorkCenter((byte) 0)
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
            WwhhWwhh00000200 last = wwhhWwhh00000200Service.getOne(Wrappers.lambdaQuery(WwhhWwhh00000200.class)
                    .orderByDesc(WwhhWwhh00000200::getRecID), false);
            if (last == null){
                last = new WwhhWwhh00000200();
                last.setRecID(0);
            }
            save.setRecID(last.getRecID() + 1);
            if (!wwhhWwhh00000200Service.save(save)){
                throw new BusinessException("库存模块：库位创建失败，请重新提交");
            }
        }

        return true;
    }

    /**
     * 强制创建仓库和存储位置
     * @param warehouse 仓库编码
     * @param location 库位
     * @return 结果
     */
    @Override
    public Boolean forceTheCreationOfWarehousesAndStorageLocations(String warehouse,String location){
        forceCreationOfWarehouses(warehouse);
        forceTheCreationOfStorageLocations(warehouse, location);
        return true;
    }
}
