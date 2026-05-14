package com.zhurong.platform.custom.erp.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

        List<ZhurongButSupplierinfo> existingSupplierInfos = zhurongButSupplierinfoService
                .listByIn(Wrappers.lambdaQuery(ZhurongButSupplierinfo.class), ZhurongButSupplierinfo::getCnc,
                cncs);

        List<String> existingCncs = existingSupplierInfos.stream().filter(it -> it.getIsRead() != null && it.getIsRead())
                .map(ZhurongButSupplierinfo::getCnc)
                .toList();

        List<String> qcncs = cncs.stream().filter(cnc -> !existingCncs.contains(cnc)).toList();
        if (qcncs.isEmpty()){
            String msg = "没有可同步的数据";
            log.error(msg);
            throw new BusinessException(msg);
        }
        List<String> records = new ArrayList<>();
        List<ZhurongButSupplierinfo> news = new ArrayList<>();
        List<ViPmPrjplanLantek> viPmPrjplanLanteks = listByIn(Wrappers.lambdaQuery(ViPmPrjplanLantek.class),
                ViPmPrjplanLantek::getCnc, qcncs, 1000);
        viPmPrjplanLanteks.forEach(prjp -> {
            prjp.setShtRef(prjp.getGenRemShtRef());
            /*if (StringUtils.isBlank(prjp.getUseRemnatRef()) && StringUtils.isNotBlank(prjp.getGenRemShtRef())){
                //保留整板的
                if (StringUtils.isNotBlank(prjp.getShtRef()) && !prjp.getGenRemShtRef().equals(prjp.getShtRef()) && !records.contains(prjp.getShtRef())){
                    ZhurongButSupplierinfo entity = zhurongButSupplierinfoConvert.toEntity(prjp);
                    news.add(entity);
                    records.add(entity.getShtRef());
                }
                //添加生成余料的（未使用的，没有CNC）
                prjp.setShtRef(prjp.getGenRemShtRef());
                prjp.setCnc(prjp.getGenRemShtRef());
            }

            if (StringUtils.isNotBlank(prjp.getUseRemnatRef()) && StringUtils.isNotBlank(prjp.getGenRemShtRef())){
                if (!prjp.getUseRemnatRef().equals(prjp.getGenRemShtRef())){
                    if (!records.contains(prjp.getUseRemnatRef())){
                        ZhurongButSupplierinfo entity = zhurongButSupplierinfoConvert.toEntity(prjp);
                        entity.setShtRef(prjp.getUseRemnatRef());
                        news.add(entity);
                        records.add(entity.getShtRef());
                    }
                    if (!records.contains(prjp.getGenRemShtRef())){
                        ZhurongButSupplierinfo entity = zhurongButSupplierinfoConvert.toEntity(prjp);
                        entity.setShtRef(prjp.getGenRemShtRef());
                        news.add(entity);
                        records.add(entity.getShtRef());
                    }
                }
            }

            if (StringUtils.isBlank(prjp.getShtRef())){
                prjp.setShtRef(prjp.getUseRemnatRef());
            }*/
        });
        List<String> querydCncs = viPmPrjplanLanteks.stream().map(ViPmPrjplanLantek::getCnc).toList();
        List<String> cncs2 = cncs.stream().filter(cnc -> !querydCncs.contains(cnc)).toList();
        if (CollectionUtils.isNotEmpty(cncs2)){
            List<ViPmPrjplanLantek> viPmPrjplanLanteks2 = viPmPrjplanLantek2Service
                    .listByIn(Wrappers.lambdaQuery(ViPmPrjplanLantek.class), ViPmPrjplanLantek::getCnc, cncs2, 1000);
            viPmPrjplanLanteks2.forEach(prjp -> {
                prjp.setShtRef(prjp.getGenRemShtRef());
                /*if (StringUtils.isBlank(prjp.getUseRemnatRef()) && StringUtils.isNotBlank(prjp.getGenRemShtRef())){
                    //保留整板的
                    if (StringUtils.isNotBlank(prjp.getShtRef()) && !prjp.getGenRemShtRef().equals(prjp.getShtRef()) && !records.contains(prjp.getShtRef())){
                        ZhurongButSupplierinfo entity = zhurongButSupplierinfoConvert.toEntity(prjp);
                        news.add(entity);
                        records.add(entity.getShtRef());
                    }
                    //添加生成余料的（未使用的，没有CNC）
                    prjp.setShtRef(prjp.getGenRemShtRef());
                    prjp.setCnc(prjp.getGenRemShtRef());
                }

                if (StringUtils.isNotBlank(prjp.getUseRemnatRef()) && StringUtils.isNotBlank(prjp.getGenRemShtRef())){
                    if (!prjp.getUseRemnatRef().equals(prjp.getGenRemShtRef())){
                        if (!records.contains(prjp.getUseRemnatRef())){
                            ZhurongButSupplierinfo entity = zhurongButSupplierinfoConvert.toEntity(prjp);
                            entity.setShtRef(prjp.getUseRemnatRef());
                            news.add(entity);
                            records.add(entity.getShtRef());
                        }
                        if (!records.contains(prjp.getGenRemShtRef())){
                            ZhurongButSupplierinfo entity = zhurongButSupplierinfoConvert.toEntity(prjp);
                            entity.setShtRef(prjp.getGenRemShtRef());
                            news.add(entity);
                            records.add(entity.getShtRef());
                        }
                    }
                }
                if (StringUtils.isBlank(prjp.getShtRef())){
                    prjp.setShtRef(prjp.getUseRemnatRef());
                }*/
            });

            viPmPrjplanLanteks.addAll(viPmPrjplanLanteks2);
        }


        viPmPrjplanLanteks = viPmPrjplanLanteks.stream().filter(it -> StringUtils.isNotBlank(it.getShtRef())).toList();
        if (CollectionUtils.isEmpty(viPmPrjplanLanteks)){
            return false;
        }

        //过滤掉已同步的
        List<String> existings = existingSupplierInfos.stream().map(ZhurongButSupplierinfo::getShtRef).toList();
        List<ViPmPrjplanLantek> needInsert = viPmPrjplanLanteks.stream().filter(it -> !existings.contains(it.getShtRef()))
                .toList();

        if (CollectionUtils.isEmpty(needInsert)){
            log.warn("供应商信息已同步");
            return false;
        }

        List<ZhurongButSupplierinfo> saves = needInsert.stream().map(zhurongButSupplierinfoConvert::toEntity).toList();
        news.addAll(saves);
        return zhurongButSupplierinfoService.saveBatch(news);
    }

    @Override
    public boolean updateInventoryReferences(List<String> ids) {

        LambdaQueryWrapper<ZhurongButSupplierinfo> wrapper = Wrappers.lambdaQuery(ZhurongButSupplierinfo.class);
        if (ids != null){
            wrapper.in(BaseEntity::getId, ids);
        }
        List<ZhurongButSupplierinfo> supplierInfos = zhurongButSupplierinfoService.list(wrapper
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

                        .setDIS_IsRemnant(pprr.getDIS_IsRemnant())
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
                    Byte DIS_IsRemnant = pprrPprr00000100.getDIS_IsRemnant();

                    log.info("余料标识：{}，{}",originShtRef,DIS_IsRemnant);

                    boolean isRemnant = DIS_IsRemnant != null && "1".equals(DIS_IsRemnant.toString());

                    if (isRemnant){
                        ViPmPrjplanLantek one = getOne(Wrappers.lambdaQuery(ViPmPrjplanLantek.class).eq(ViPmPrjplanLantek::getGenRemShtRef, originShtRef), false);

                        if (one == null){
                            log.info("查第二数据库：{}", originShtRef);
                            one = viPmPrjplanLantek2Service.getOne(Wrappers.lambdaQuery(ViPmPrjplanLantek.class).eq(ViPmPrjplanLantek::getGenRemShtRef, originShtRef), false);
                        }

                        if (one != null){
                            whsName = one.getWhsName();
                            locName = one.getLocName();
                            log.info("查询生成余料编码数据：{},{},{},{},{}",originShtRef,one.getShtRef(),one.getRemShtRef(),whsName,locName);
                        }
                    }


                    String DIS_UData3_Sht = StringUtils.isNotBlank(whsName) ? whsName : "";

                    log.info("originShtRef = {} DIS_UData3_Sht={}", originShtRef,DIS_UData3_Sht);

                    if (StringUtils.isNotBlank(DIS_UData3_Sht)){
                        DIS_UData3_Sht = String.format("%s,%s",DIS_UData3_Sht, locName);
                    }else{
                        DIS_UData3_Sht = locName;
                    }

                    if (StringUtils.isNotBlank(SUData3)) {
                        String[] split = SUData3.split(",");
                        log.info("whsName={}", whsName);

                        List<String> list = new ArrayList<>(Arrays.asList(split));

                        if (StringUtils.isNotBlank(whsName) && !list.contains(whsName)) {
                            list.add(whsName);
                        }

                        if (StringUtils.isNotBlank(locName) && !list.contains(locName)) {
                            list.add(locName);
                        }

                        DIS_UData3_Sht = String.join(",", list);
                    }



                    boolean update1 = pprrPprr00000100Service.update(Wrappers.lambdaUpdate(PprrPprr00000100.class)
                            .set(PprrPprr00000100::getDIS_UData3_Sht, DIS_UData3_Sht)
                            .eq(PprrPprr00000100::getRecID, pprrPprr00000100.getRecID()));

                    log.info("更新钢板档案用户数据：{} {} {}",originShtRef, DIS_UData3_Sht, update1);

                    boolean update2 = disNestNest00000100Service.update(Wrappers.lambdaUpdate(DisNestNest00000100.class)
                            .set(DisNestNest00000100::getUData3, DIS_UData3_Sht)
                            .eq(DisNestNest00000100::getShtRef, originShtRef));

                    log.info("更新套料程序钢板用户数据：{} {} {}",originShtRef, DIS_UData3_Sht, update2);

                    if (update1){
                        updateReadState.add(originShtRef);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }

            }

            List<Boolean> booleans = zhurongButSupplierinfoService.updateByIn(Wrappers.lambdaUpdate(ZhurongButSupplierinfo.class)
                            .set(BaseEntity::getIsRead, true),
                    ZhurongButSupplierinfo::getShtRef, updateReadState);
            log.info("供应商信息同步状态更新：{}，{}",booleans, String.join(",", updateReadState));
        }

        return true;
    }
}
