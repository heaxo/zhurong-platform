package com.zhurong.platform.custom.erp.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Sets;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.base.masterlink.commands.ManufacturingCommand;
import com.zhurong.platform.base.masterlink.engine.XmlExportEngine;
import com.zhurong.platform.custom.entity.*;
import com.zhurong.platform.custom.erp.dto.ViPmOrderlDTO;
import com.zhurong.platform.custom.erp.entity.ViPmOrderl;
import com.zhurong.platform.custom.erp.mapper.ViPmOrderlMapper;
import com.zhurong.platform.custom.erp.service.ILkPmOrder2Service;
import com.zhurong.platform.custom.erp.service.ILkPmOrderService;
import com.zhurong.platform.custom.erp.service.IViPmOrderl2Service;
import com.zhurong.platform.custom.erp.service.IViPmOrderlService;
import com.zhurong.platform.custom.exception.MasterlinkImportException;
import com.zhurong.platform.custom.service.*;
import com.zhurong.platform.custom.util.MasterlinkTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/*
 * @Author zhurong
 * @Description ViPmOrderlServiceImpl
 * @Date 2026/3/29 15:52
 **/
@Service
@RequiredArgsConstructor
@Slf4j
@DS("erp")
public class ViPmOrderlServiceImpl extends ServiceImpl<ViPmOrderlMapper, ViPmOrderl> implements IViPmOrderlService {

    private final IMmnnMmoo00000300Service mmnnMmoo00000300Service;
    private final IDisMmnnMmoo00000200Service disMmnnMmoo00000200Service;
    private final IPprrPprr00000100Service pprrPprr00000100Service;
    private final IDisMmttMmtt00000100Service disMmttMmtt00000100Service;
    private final IWwccWwcc00000100Service wwccWwcc00000100Service;
    private final IViPmOrderl2Service viPmOrderl2Service;

    //已完成导入反馈service
    private final ILkPmOrderService lkPmOrderService;
    private final ILkPmOrder2Service lkPmOrder2Service;
    private final ReentrantLock exeLock = new ReentrantLock();

    @Override
    public int importToExpert(ViPmOrderlDTO dto) {
        List<String> belposIds = dto.getBelposIds();
        if (CollectionUtils.isEmpty(belposIds)){
            throw new BusinessException("生产订单工单号不能为空");
        }
        if (StringUtils.isBlank(dto.getJobRef())){
            throw new BusinessException("作业未指定，无法开始导入");
        }
        LambdaQueryWrapper<ViPmOrderl> wrapper = Wrappers.lambdaQuery(ViPmOrderl.class).in(ViPmOrderl::getBelposId, belposIds);
        List<ViPmOrderl> list = list(wrapper);
        Map<String, Integer> map1 = list.stream().collect(Collectors.toMap(ViPmOrderl::getBelposId, p -> 1));
        List<ViPmOrderl> list2 = viPmOrderl2Service.list(wrapper);
        Map<String, Integer> map2 = list2.stream().collect(Collectors.toMap(ViPmOrderl::getBelposId, p -> 1));
        //合并
        list.addAll(list2);
        //检查数据是否是同一家公司
        List<String> companys = list.stream().map(ViPmOrderl::getCompany).distinct().toList();

        if (companys.size() > 1){
            throw new BusinessException(String.format("不允许同时指定多家不同公司的数据，%s",String.join(",", companys)));
        }
        String company = companys.get(0);
        //检查指定的作业是否存在，并且是否已指定公司，如果已指定公司，检查是否与当前指定的公司一致，不一致直接抛异常
        DisMmnnMmoo00000200 job = disMmnnMmoo00000200Service.getOne(Wrappers.lambdaQuery(DisMmnnMmoo00000200.class)
                .eq(DisMmnnMmoo00000200::getJobRef, dto.getJobRef()));

        if (job == null){
            throw new BusinessException(String.format("作业%s不存在，请检查套料软件中是否已创建作业", dto.getJobRef()));
        }

        String targetCompany = job.getUData1();

        if (StringUtils.isNotBlank(targetCompany) && !company.equals(targetCompany)){
            throw new BusinessException(String.format("作业%s，已被指定公司：%s，无法再次指定公司：%s", job.getJobName(), targetCompany, dto.getJobRef()));
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            ViPmOrderl viPmOrderl = list.get(i);
            if (StringUtils.isBlank(viPmOrderl.getUZnr())){
                sb.append(String.format("第%s行，%s材质为空",i + 1,viPmOrderl.getItemCode()));
            }
            if (StringUtils.isBlank(viPmOrderl.getAplatzId()) && StringUtils.isBlank(dto.getWrkRef())){
                sb.append(String.format("第%s行，%s机床为空",i + 1,viPmOrderl.getItemCode()));
            }
        }
        if (StringUtils.isNotBlank(sb.toString())){
            log.warn(sb.toString());
            throw new BusinessException(sb.toString());
        }

        Set<String> prdRefs = list.stream().map(ViPmOrderl::getCcad).collect(Collectors.toSet());
        Set<String> wrkRefs = list.stream().map(ViPmOrderl::getAplatzId).filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        Set<String> matRefs = list.stream().map(ViPmOrderl::getUZnr).filter(StringUtils::isNotBlank).collect(Collectors.toSet());

        List<PprrPprr00000100> pprrs = pprrPprr00000100Service.list(Wrappers.lambdaQuery(PprrPprr00000100.class)
                .in(PprrPprr00000100::getPrdRef, prdRefs));
        Set<String> existsPrdRefs = pprrs.stream().map(PprrPprr00000100::getPrdRef)
                .map(String::toLowerCase).collect(Collectors.toSet());
        List<String> notExistPrdRefs = new ArrayList<>();
        if (existsPrdRefs.size() < prdRefs.size()){
            Sets.SetView<String> difference = Sets.difference(prdRefs, existsPrdRefs);
            notExistPrdRefs.addAll(difference);
//            String msg = String.format("零件档案图纸不存在，无法导入至作业，%s",String.join(",",difference.stream().toList()));
//            log.warn(msg);
//            throw new BusinessException(msg);
        }

        List<DisMmttMmtt00000100> masterials = disMmttMmtt00000100Service.list();
        List<WwccWwcc00000100> machines = wwccWwcc00000100Service.list();
        List<String> existsMatRefs = masterials.stream().map(DisMmttMmtt00000100::getMatRef).map(String::toLowerCase).toList();
        List<String> existsWrkRefs = machines.stream().map(WwccWwcc00000100::getWrkRef).map(String::toLowerCase).toList();
        List<String> notExistMatRefs = matRefs.stream().filter(matRef -> !existsMatRefs.contains(matRef.toLowerCase())).toList();
        List<String> notExistWrkRefs = wrkRefs.stream().filter(wrkRef -> !existsWrkRefs.contains(wrkRef.toLowerCase())).toList();
        /*if (CollectionUtils.isNotEmpty(notExistMatRefs)){
            String msg = String.format("材质不存在，请在lantek套料软件中创建相关材质，%s", String.join(",",notExistMatRefs));
            log.warn(msg);
            throw new BusinessException(msg);
        }
        if (CollectionUtils.isNotEmpty(notExistWrkRefs)){
            String msg = String.format("机床不存在，请在lantek套料软件中创建相关机床，%s", String.join(",",notExistWrkRefs));
            log.warn(msg);
            throw new BusinessException(msg);
        }*/

        boolean locked = false;
        try {
            locked = exeLock.tryLock(5, TimeUnit.MINUTES);
            if (!locked) {
                throw new BusinessException("系统繁忙，请稍后再试");
            }


            //查询是否存在已导入订单
            List<MmnnMmoo00000300> existing = mmnnMmoo00000300Service.list(Wrappers.lambdaQuery(MmnnMmoo00000300.class)
                    .in(MmnnMmoo00000300::getMnORef, belposIds));
            List<String> existingMnorefs = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(existing)){
                existingMnorefs = existing.stream().map(MmnnMmoo00000300::getMnORef).toList();
                String msg = String.format("生产订单已存在，无法重复导入，%s",String.join(",",existingMnorefs));
                log.error(msg);
                lkPmOrderService.tryInserts(existingMnorefs);
                lkPmOrder2Service.tryInserts(existingMnorefs);
                //throw new BusinessException(msg);

            }

            XmlExportEngine engine = new XmlExportEngine();
            //创建导入数据
            List<String> finalExistingMnorefs = existingMnorefs;
            List<ManufacturingCommand> imports = list.stream().map(it -> {
                String mnoRef = it.getBelposId();
                if (finalExistingMnorefs.contains(mnoRef)){
                    return null;
                }

                ManufacturingCommand manufacturing = new ManufacturingCommand();
                String wrkRef = StringUtils.isNotBlank(dto.getWrkRef()) ? dto.getWrkRef() : it.getAplatzId();
                String matRef = StringUtils.isNotBlank(dto.getMatRef()) ? dto.getMatRef() : it.getUZnr();

                //不存在图纸得跳过
                if (!existsPrdRefs.contains(it.getCcad().toLowerCase())){
                    log.warn(String.format("%s，图纸不存在，跳过", it.getCcad()));
                    return null;
                }
                //不存在得材质跳过
                if (!existsMatRefs.contains(matRef.toLowerCase())){
                    log.warn(String.format("%s，材质不存在，跳过", it.getCcad()));
                    return null;
                }
                //不存在得机床跳过
                if (!existsWrkRefs.contains(wrkRef.toLowerCase())){
                    log.warn(String.format("%s，机床不存在，跳过", it.getCcad()));
                    return null;
                }

                manufacturing.setCDate(LocalDateTime.now().toLocalDate());
                manufacturing.setDisJobRef(dto.getJobRef());
                manufacturing.setWrkRef(wrkRef);
                manufacturing.setMnORef(mnoRef);
                manufacturing.setPrdRefDst(it.getCcad());
                manufacturing.setOrdRef(it.getIcadproduct());
                manufacturing.setRq(it.getMengeVerbrauch().intValue());
                manufacturing.setCusRef(it.getVversion());
                manufacturing.setDisMatRef(matRef);
                manufacturing.setDisThickness(it.getUdf1().floatValue());
                manufacturing.setCDate(it.getAnfzeit().toLocalDate());
                manufacturing.setCusName(it.getItemCode());
                return manufacturing;
            })
            //过滤掉空的（已导入的finalExistingMnorefs）
            .filter(Objects::nonNull).toList();

            if (CollectionUtils.isEmpty(imports)){
                if (CollectionUtils.isNotEmpty(notExistPrdRefs)){
                    String msg = String.format("零件图纸未维护，先维护零件图纸后重新提交，%s",String.join(",",notExistPrdRefs));
                    log.warn(msg);
                    throw new BusinessException(msg);
                }
                if (CollectionUtils.isNotEmpty(notExistMatRefs)){
                    String msg = String.format("材质未维护，先维护材质后重新提交，%s",String.join(",",notExistMatRefs));
                    log.warn(msg);
                    throw new BusinessException(msg);
                }
                if (CollectionUtils.isNotEmpty(notExistWrkRefs)){
                    String msg = String.format("机床未维护，先维护机床后重新提交，%s",String.join(",",notExistWrkRefs));
                    log.warn(msg);
                    throw new BusinessException(msg);
                }

                String msg = "生产订单可能已完成导入，请刷新重试";
                log.warn(msg);
                throw new BusinessException(msg);
            }
            List<String> mnoRefs = imports.stream().map(ManufacturingCommand::getMnORef).toList();
            String xmlPath = engine.export(imports);
            //执行导入
            MasterlinkTool.ExecResult execResult = MasterlinkTool.executeImport(xmlPath);

            if (!execResult.success()){
                log.error(execResult.stderr());
                throw new MasterlinkImportException(execResult.stderr());
            }

            //验证是否完成导入
            List<MmnnMmoo00000300> existsing = mmnnMmoo00000300Service.list(Wrappers.lambdaQuery(MmnnMmoo00000300.class)
                    .in(MmnnMmoo00000300::getMnORef, mnoRefs));

            if (CollectionUtils.isEmpty(existsing)){
                String msg = "生产订单导入失败，请刷新重试";
                log.warn(msg);
                throw new BusinessException(msg);
            }

            List<String> beenImportedMnoRefs = existsing.stream().map(MmnnMmoo00000300::getMnORef).toList();
            List<String> beenImportedMnoRefs1 = existsing.stream().map(MmnnMmoo00000300::getMnORef).filter(map1::containsKey).toList();
            List<String> beenImportedMnoRefs2 = existsing.stream().map(MmnnMmoo00000300::getMnORef).filter(map2::containsKey).toList();
            //反馈已完成导入的工单
            boolean b1 = lkPmOrderService.tryInserts(beenImportedMnoRefs1);
            log.info(String.format("已完成导入工单状态保存结果：%s，%s",b1, String.join(",", beenImportedMnoRefs1)));
            boolean b2 = lkPmOrder2Service.tryInserts(beenImportedMnoRefs2);
            log.info(String.format("已完成导入工单状态保存结果：%s，%s",b2, String.join(",", beenImportedMnoRefs2)));
            //更新masterlink无法导入的字段数据
            Map<String, ViPmOrderl> recordMap = list.stream().collect(Collectors.toMap(ViPmOrderl::getBelposId, it -> it));

            List<ViPmOrderl> updates = beenImportedMnoRefs.stream().map(recordMap::get).toList();

            for (int i = 0; i < updates.size(); i++) {
                ViPmOrderl viPmOrderl = updates.get(i);
                pprrPprr00000100Service.update(Wrappers.lambdaUpdate(PprrPprr00000100.class)
                        .set(PprrPprr00000100::getPrdName, viPmOrderl.getItemName())
                        .eq(PprrPprr00000100::getPrdRef, viPmOrderl.getCcad()));
                boolean update = mmnnMmoo00000300Service.update(Wrappers.lambdaUpdate(MmnnMmoo00000300.class)
                        .set(MmnnMmoo00000300::getPrdNameDst, viPmOrderl.getItemName())
                        .set(MmnnMmoo00000300::getCusName, viPmOrderl.getItemCode())
                        .set(MmnnMmoo00000300::getRDate, viPmOrderl.getLieferdatum())
                        .set(MmnnMmoo00000300::getDescrip, viPmOrderl.getCompany())
                        .set(MmnnMmoo00000300::getMinQuan, viPmOrderl.getMengeVerbrauch())
                        .eq(MmnnMmoo00000300::getMnORef, viPmOrderl.getBelposId()));
                log.info(String.format("更新生产订单：%s，更新结果：%s", viPmOrderl.getBelposId(), update));
            }
            //更新公司到作业
            if (StringUtils.isBlank(targetCompany)){
                boolean update = disMmnnMmoo00000200Service.update(Wrappers.lambdaUpdate(DisMmnnMmoo00000200.class)
                        .set(DisMmnnMmoo00000200::getUData1, company)
                        .eq(DisMmnnMmoo00000200::getRecID, job.getRecID()));
                log.info(String.format("作业公司更新结果：%s，%s，%s", update, company, job.getJobName()));
            }

            return existsing.size();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new BusinessException("获取锁被中断");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException(String.format("生产订单导入异常中断：%s", e.getMessage()));
        } finally {
            if (locked) {
                exeLock.unlock();
            }
        }
    }
}
