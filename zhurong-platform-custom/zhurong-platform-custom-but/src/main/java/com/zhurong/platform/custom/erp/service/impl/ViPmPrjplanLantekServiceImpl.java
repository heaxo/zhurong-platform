package com.zhurong.platform.custom.erp.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.base.constant.NestConstant;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.custom.convert.ZhurongButSupplierinfoConvert;
import com.zhurong.platform.custom.entity.DisNestNest00000100;
import com.zhurong.platform.custom.entity.PprrPprr00000100;
import com.zhurong.platform.custom.entity.ZhurongButSupplierinfo;
import com.zhurong.platform.custom.erp.entity.ViPmPrjplanLantek;
import com.zhurong.platform.custom.erp.mapper.ViPmPrjplanLantekMapper;
import com.zhurong.platform.custom.erp.service.IViPmPrjplanLantek2Service;
import com.zhurong.platform.custom.erp.service.IViPmPrjplanLantekService;
import com.zhurong.platform.custom.model.BaseEntity;
import com.zhurong.platform.custom.properties.ButConfigProperties;
import com.zhurong.platform.custom.service.IDisNestNest00000100Service;
import com.zhurong.platform.custom.service.IPprrPprr00000100Service;
import com.zhurong.platform.custom.service.IZhurongButSupplierinfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @Author zhurong
 * @Description 下料程序名位置视图
 * @Date 2026/3/29 15:52
 **/
@Service
@RequiredArgsConstructor
@Slf4j
@DS("erp")
public class ViPmPrjplanLantekServiceImpl extends ServiceImpl<ViPmPrjplanLantekMapper, ViPmPrjplanLantek> implements IViPmPrjplanLantekService {

    private final ZhurongButSupplierinfoConvert zhurongButSupplierinfoConvert;
    private final IDisNestNest00000100Service disNestNest00000100Service;
    private final IPprrPprr00000100Service pprrPprr00000100Service;
    private final IZhurongButSupplierinfoService zhurongButSupplierinfoService;
    private final IViPmPrjplanLantek2Service viPmPrjplanLantek2Service;
//    private final IWwhhIivv00000100Service wwhhIivv00000100Service;
//    private final IWwhhIivv00000200Service wwhhIivv00000200Service;
//    private final IPpbbPpbb00000100Service ppbbPpbb00000100Service;
//    private final WwhhWwhh00000100FeignClient wwhhWwhh00000100FeignClient;

    private final ButConfigProperties butConfigProperties;

    @Override
    public boolean syncSupplierInfo() {
        Integer syncNestingReportingMaximumDays = butConfigProperties.getSyncNestingReportingMaximumDays();
        if (syncNestingReportingMaximumDays == null || syncNestingReportingMaximumDays <= 0) {
            syncNestingReportingMaximumDays = 30;
        }
        LocalDateTime beginTime = LocalDateTime.now().minusDays(syncNestingReportingMaximumDays);
         //只同步未报工的
        List<DisNestNest00000100> nests = disNestNest00000100Service.list(Wrappers.lambdaQuery(DisNestNest00000100.class)
                .ne(DisNestNest00000100::getMState, NestConstant.MState.COMPLETED)
                .ge(DisNestNest00000100::getLastDate, beginTime)
        );

        if (CollectionUtils.isEmpty(nests)){
            String msg = "没有未报工的套料程序数据，无法同步供应商信息";
            log.error(msg);
            throw new BusinessException(msg);
        }

        List<String> cncs = nests.stream().map(DisNestNest00000100::getCNC).toList();

        List<ZhurongButSupplierinfo> existingSupplierInfos = zhurongButSupplierinfoService.listByIn(Wrappers.lambdaQuery(ZhurongButSupplierinfo.class), ZhurongButSupplierinfo::getCnc,
                cncs);

        List<ViPmPrjplanLantek> viPmPrjplanLanteks = listByIn(Wrappers.lambdaQuery(ViPmPrjplanLantek.class),
                ViPmPrjplanLantek::getCnc, cncs, 1000);
        List<String> querydCncs = viPmPrjplanLanteks.stream().map(ViPmPrjplanLantek::getCnc).toList();

        List<String> cncs2 = cncs.stream().filter(cnc -> !querydCncs.contains(cnc)).toList();
        if (CollectionUtils.isNotEmpty(cncs2)){
            List<ViPmPrjplanLantek> viPmPrjplanLanteks2 = viPmPrjplanLantek2Service
                    .listByIn(Wrappers.lambdaQuery(ViPmPrjplanLantek.class), ViPmPrjplanLantek::getCnc, cncs2, 1000);
            viPmPrjplanLanteks.addAll(viPmPrjplanLanteks2);
        }
        

        if (CollectionUtils.isEmpty(viPmPrjplanLanteks)){
            return false;
        }

        //过滤掉已同步的
        List<String> existingCNC = existingSupplierInfos.stream().map(ZhurongButSupplierinfo::getCnc).toList();
        List<ViPmPrjplanLantek> needInsert = viPmPrjplanLanteks.stream().filter(it -> !existingCNC.contains(it.getCnc()))
                .toList();

        if (CollectionUtils.isEmpty(needInsert)){
            log.warn("供应商信息已同步");
            return false;
        }

        List<ZhurongButSupplierinfo> saves = needInsert.stream().map(zhurongButSupplierinfoConvert::toEntity).toList();

        return zhurongButSupplierinfoService.saveBatch(saves);
    }

    @Override
    public boolean updateInventoryReferences() {

        List<ZhurongButSupplierinfo> supplierInfos = zhurongButSupplierinfoService.list(Wrappers.lambdaQuery(ZhurongButSupplierinfo.class)
                .eq(BaseEntity::getIsRead, false));

        if (CollectionUtils.isEmpty(supplierInfos)){
            log.warn("需更新引用的供应商信息为空，无需更新");
            return false;
        }

        List<String> updateReadState = new ArrayList<>();
        List<PprrPprr00000100> pprrUpdates = new ArrayList<>();

        //更新钢板引用和套料钢板引用
        for (int i = 0; i < supplierInfos.size(); i++) {
            ZhurongButSupplierinfo supplierInfo = supplierInfos.get(i);
            String shtRef = supplierInfo.getShtRef();
            PprrPprr00000100 pprr = pprrPprr00000100Service.getOne(Wrappers.lambdaQuery(PprrPprr00000100.class)
                    .eq(PprrPprr00000100::getPrdRef, shtRef));

            if (pprr != null) {

                String combinationPrdRef = String.format("%s-%s*%s",supplierInfo.getWhsName(), pprr.getDIS_Length(), pprr.getDIS_Width());

                //查询数据库中已有多少个用供应商+尺寸组合的编码
                long count = pprrPprr00000100Service.count(Wrappers.lambdaQuery(PprrPprr00000100.class)
                        .eq(PprrPprr00000100::getDIS_UData8_Prt, combinationPrdRef));

                //防止重复,加上序号
                String newPrdRef = String.format("%s-%s", combinationPrdRef, count + 1);

                log.info("记录需更新钢板引用：{}",newPrdRef);
                pprrUpdates.add(new PprrPprr00000100()
                        .setPrdRef(newPrdRef)
                        .setDIS_ShtRefOrg(shtRef)
                        .setDIS_UData8_Prt(combinationPrdRef)

                        .setDIS_UData7_Prt(supplierInfo.getCnc())
                        .setDIS_UData6_Prt(supplierInfo.getWhsName())
                        .setDIS_UData5_Prt(supplierInfo.getLocName())

                        .setDIS_UData3_Sht(pprr.getDIS_UData3_Sht())
                        .setDIS_CamQuan(pprr.getDIS_CamQuan())
                        .setRecID(pprr.getRecID())
                );
            }

        }

        /*record WarehouserGroupKey(String whsName,String locName){}
        Map<WarehouserGroupKey, List<ZhurongButSupplierinfo>> createWarehousers = supplierInfos.stream()
                .collect(Collectors.groupingBy(it -> new WarehouserGroupKey(it.getWhsName(), it.getLocName())));
        List<WarehouserGroupKey> warehouserGroupKeys = createWarehousers.keySet().stream().toList();
        warehouserGroupKeys.forEach(it -> {
            WwhhWwhh00000100DTO wwhhWwhh00000100DTO = new WwhhWwhh00000100DTO();
            wwhhWwhh00000100DTO.setWrhRef(it.whsName);
            wwhhWwhh00000100DTO.setLocRef(it.locName);
            ApiResponse<Boolean> apiResponse = wwhhWwhh00000100FeignClient.createWarehouseAndLocation(wwhhWwhh00000100DTO);
            if (apiResponse.data() == null || !apiResponse.data()){
                log.warn("库存库位创建失败：{}-{}，{}",it.whsName, it.locName, apiResponse.message());
                return;
            }
            log.info("库存库位创建成功：{}-{}",it.whsName, it.locName);
        });*/

        log.info("需更新钢板引用数量：{}",pprrUpdates.size());
        if (CollectionUtils.isNotEmpty(pprrUpdates)){
            for (int i = 0; i < pprrUpdates.size(); i++) {
                PprrPprr00000100 pprrPprr00000100 = pprrUpdates.get(i);
                String shtRef = pprrPprr00000100.getPrdRef();
                String originShtRef = pprrPprr00000100.getDIS_ShtRefOrg();

                String cnc = pprrPprr00000100.getDIS_UData7_Prt();
                String whsName = pprrPprr00000100.getDIS_UData6_Prt();
                String locName = pprrPprr00000100.getDIS_UData5_Prt();
                String SUData3 = pprrPprr00000100.getDIS_UData3_Sht();

                String DIS_UData3_Sht = whsName;
                if(StringUtils.isNotBlank(SUData3)){
                    String[] split = SUData3.split(",");

                    List<String> list = Arrays.stream(split).toList();
                    if (!list.contains(whsName)){
                        list.add(whsName);
                    }
                    DIS_UData3_Sht = String.join(",",list);
                }



                boolean update1 = pprrPprr00000100Service.update(Wrappers.lambdaUpdate(PprrPprr00000100.class)
                        .set(PprrPprr00000100::getPrdRef, shtRef)
                        .set(PprrPprr00000100::getDIS_UData3_Sht, DIS_UData3_Sht)
                        .set(PprrPprr00000100::getDIS_UData8_Prt, pprrPprr00000100.getDIS_UData8_Prt())
                        .eq(PprrPprr00000100::getRecID, pprrPprr00000100.getRecID()));

                log.info("更新钢板档案编码：{} -> {} {}",originShtRef, shtRef, update1);

                boolean update2 = disNestNest00000100Service.update(Wrappers.lambdaUpdate(DisNestNest00000100.class)
                        .set(DisNestNest00000100::getShtRef, shtRef)
                        .eq(DisNestNest00000100::getShtRef, originShtRef));

                log.info("更新套料程序钢板引用：{} -> {} {}",originShtRef, shtRef, update2);

                if (update1 && update2){
                    updateReadState.add(cnc);
                }
                /*if (update1){
                    *//** 更新库存模块相关信息
                     * update WWHH_IIVV_00000100 set WrhRef = N'仓库7',PrdRef  = 'HFB_002' where WrhRef = N'仓库6' and PrdRef  = 'HFB_001';
                     * update WWHH_IIVV_00000200 set WrhRef = N'仓库7',LocRef = N'库位7',PrdRef  = 'HFB_002' where WrhRef = N'仓库6' AND LocRef = N'库位5' and PrdRef  = 'HFB_001';
                     * update PPBB_PPBB_00000100 set WrhRef = N'仓库7',LocRef = N'库位7' where WrhRef = N'仓库6' AND LocRef = N'库位5' and PrdRef  = 'HFB_001';
                     * update PPBB_PPBB_00000100 set Reference = N'仓库7' where Type = 5 and PrdRef  = 'HFB_001';
                     *
                     * UPDATE PPRR_PPRR_00000100 SET PrdRef = 'HFB_002' WHERE PrdRef = 'HFB_001';
                     * UPDATE DIS_NEST_NEST_00000100 SET ShtRef = 'HFB_002' WHERE ShtRef = 'HFB_001';
                     * UPDATE PPBB_PPBB_00000100 SET PrdRef= 'HFB_002' WHERE PrdRef = 'HFB_001';
                     *//*
                    boolean update3 = wwhhIivv00000100Service.update(Wrappers.lambdaUpdate(WwhhIivv00000100.class)
                            .set(WwhhIivv00000100::getWrhRef, whsName)
                            .set(WwhhIivv00000100::getPrdRef, shtRef)
                            .eq(WwhhIivv00000100::getPrdRef, originShtRef));
                    log.info("WWHH_IIVV_00000100，仓库信息更新：{}、{}、{}、{}",whsName,shtRef,originShtRef,update3);
                    boolean update4 = wwhhIivv00000200Service.update(Wrappers.lambdaUpdate(WwhhIivv00000200.class)
                            .set(WwhhIivv00000200::getWrhRef, whsName)
                            .set(WwhhIivv00000200::getLocRef, locName)
                            .set(WwhhIivv00000200::getPrdRef, shtRef)
                            .eq(WwhhIivv00000200::getPrdRef, originShtRef));
                    log.info("WWHH_IIVV_00000200，仓库库位信息更新：{}、{}、{}、{}、{}",whsName,locName,shtRef,originShtRef,update4);
                    boolean update5 = ppbbPpbb00000100Service.update(Wrappers.lambdaUpdate(PpbbPpbb00000100.class)
                            .set(PpbbPpbb00000100::getWrhRef, whsName)
                            .set(PpbbPpbb00000100::getLocRef, locName)
                            .eq(PpbbPpbb00000100::getPrdRef, originShtRef));
                    log.info("PPBB_PPBB_00000100，库存仓库库位信息更新：{}、{}、{}、{}",whsName,locName,originShtRef,update5);
                    boolean update6 = ppbbPpbb00000100Service.update(Wrappers.lambdaUpdate(PpbbPpbb00000100.class)
                            .set(PpbbPpbb00000100::getReference, whsName)
                            .eq(PpbbPpbb00000100::getType, 5)
                            .eq(PpbbPpbb00000100::getPrdRef, originShtRef));
                    log.info("PPBB_PPBB_00000100，库存仓库引用更新：{}、{}、{}",whsName,originShtRef,update6);
                    boolean update7 = ppbbPpbb00000100Service.update(Wrappers.lambdaUpdate(PpbbPpbb00000100.class)
                            .set(PpbbPpbb00000100::getPrdRef, shtRef)
                            .eq(PpbbPpbb00000100::getPrdRef, originShtRef));
                    log.info("PPBB_PPBB_00000100，库存钢板引用更新：{} -> {} {}",originShtRef,shtRef,update7);
                }*/
            }

            boolean update = zhurongButSupplierinfoService.update(Wrappers.lambdaUpdate(ZhurongButSupplierinfo.class)
                    .set(BaseEntity::getIsRead, true)
                    .in(ZhurongButSupplierinfo::getCnc, updateReadState));
            log.info("供应商信息同步状态更新：{}，{}",update, String.join(",", updateReadState));
        }

        return true;
    }
}
