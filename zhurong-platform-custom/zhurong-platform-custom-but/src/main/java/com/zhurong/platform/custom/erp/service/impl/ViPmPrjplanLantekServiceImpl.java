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
        viPmPrjplanLanteks.forEach(prjp -> {
            if (StringUtils.isBlank(prjp.getShtRef())){
                prjp.setShtRef(prjp.getUseRemnatRef());
            }
        });
        List<String> querydCncs = viPmPrjplanLanteks.stream().map(ViPmPrjplanLantek::getCnc).toList();

        List<String> cncs2 = cncs.stream().filter(cnc -> !querydCncs.contains(cnc)).toList();
        if (CollectionUtils.isNotEmpty(cncs2)){
            List<ViPmPrjplanLantek> viPmPrjplanLanteks2 = viPmPrjplanLantek2Service
                    .listByIn(Wrappers.lambdaQuery(ViPmPrjplanLantek.class), ViPmPrjplanLantek::getCnc, cncs2, 1000);
            viPmPrjplanLanteks2.forEach(prjp -> {
                if (StringUtils.isBlank(prjp.getShtRef())){
                    prjp.setShtRef(prjp.getUseRemnatRef());
                }
            });

            viPmPrjplanLanteks.addAll(viPmPrjplanLanteks2);
        }


        viPmPrjplanLanteks = viPmPrjplanLanteks.stream().filter(it -> StringUtils.isNotBlank(it.getShtRef())).toList();
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
            log.warn("需更新的供应商信息为空，无需更新");
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

                log.info("记录需更新钢板用户数据：{}",shtRef);
                pprrUpdates.add(new PprrPprr00000100()
                        .setDIS_ShtRefOrg(shtRef)
                        .setDIS_UData3_Sht(pprr.getDIS_UData3_Sht())

                        .setDIS_UData6_Prt(supplierInfo.getWhsName())
                        .setDIS_UData5_Prt(supplierInfo.getLocName())
                        .setDIS_UData7_Prt(supplierInfo.getCnc())

                        .setDIS_CamQuan(pprr.getDIS_CamQuan())
                        .setRecID(pprr.getRecID())
                );
            }

        }

        log.info("需更新钢板数量：{}",pprrUpdates.size());
        if (CollectionUtils.isNotEmpty(pprrUpdates)){
            for (int i = 0; i < pprrUpdates.size(); i++) {
                try{
                    PprrPprr00000100 pprrPprr00000100 = pprrUpdates.get(i);
                    String originShtRef = pprrPprr00000100.getDIS_ShtRefOrg();

                    String locName = pprrPprr00000100.getDIS_UData5_Prt();
                    String whsName = pprrPprr00000100.getDIS_UData6_Prt();
                    String cnc = pprrPprr00000100.getDIS_UData7_Prt();
                    String SUData3 = pprrPprr00000100.getDIS_UData3_Sht();

                    String DIS_UData3_Sht = StringUtils.isNotBlank(whsName) ? whsName : "";

                    if (StringUtils.isNotBlank(DIS_UData3_Sht)){
                        DIS_UData3_Sht = String.format("%s,%s",DIS_UData3_Sht, locName);
                    }else{
                        DIS_UData3_Sht = locName;
                    }

                    if(StringUtils.isNotBlank(SUData3)){
                        String[] split = SUData3.split(",");

                        List<String> list = Arrays.stream(split).toList();
                        if (!list.contains(whsName)){
                            list.add(whsName);
                        }
                        if (!list.contains(locName)){
                            list.add(locName);
                        }
                        DIS_UData3_Sht = String.join(",",list);
                    }



                    boolean update1 = pprrPprr00000100Service.update(Wrappers.lambdaUpdate(PprrPprr00000100.class)
                            .set(PprrPprr00000100::getDIS_UData3_Sht, DIS_UData3_Sht)
                            .eq(PprrPprr00000100::getRecID, pprrPprr00000100.getRecID()));

                    log.info("更新钢板档案用户数据：{} {} {}",originShtRef, DIS_UData3_Sht, update1);

                    boolean update2 = disNestNest00000100Service.update(Wrappers.lambdaUpdate(DisNestNest00000100.class)
                            .set(DisNestNest00000100::getUData3, DIS_UData3_Sht)
                            .eq(DisNestNest00000100::getShtRef, originShtRef));

                    log.info("更新套料程序钢板用户数据：{} {} {}",originShtRef, DIS_UData3_Sht, update2);

                    if (update1 && update2){
                        updateReadState.add(cnc);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }

            }

            List<Boolean> booleans = zhurongButSupplierinfoService.updateByIn(Wrappers.lambdaUpdate(ZhurongButSupplierinfo.class)
                            .set(BaseEntity::getIsRead, true),
                    ZhurongButSupplierinfo::getCnc, updateReadState);
            log.info("供应商信息同步状态更新：{}，{}",booleans, String.join(",", updateReadState));
        }

        return true;
    }
}
