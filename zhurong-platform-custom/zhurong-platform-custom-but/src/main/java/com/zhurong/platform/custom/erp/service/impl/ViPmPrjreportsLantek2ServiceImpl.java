package com.zhurong.platform.custom.erp.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.base.constant.NestConstant;
import com.zhurong.platform.custom.entity.DisNestNest00000100;
import com.zhurong.platform.custom.erp.entity.ViPmPrjreportsLantek;
import com.zhurong.platform.custom.erp.mapper.ViPmPrjreportsLantek2Mapper;
import com.zhurong.platform.custom.erp.service.IViPmPrjreportsLantek2Service;
import com.zhurong.platform.custom.properties.ButConfigProperties;
import com.zhurong.platform.custom.service.IDisNestNest00000100Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/*
 * @Author zhurong
 * @Description 下料报工钢板视图
 * @Date 2026/3/29 15:52
 **/
@Service
@RequiredArgsConstructor
@DS("erp")
@Slf4j
public class ViPmPrjreportsLantek2ServiceImpl extends ServiceImpl<ViPmPrjreportsLantek2Mapper, ViPmPrjreportsLantek> implements IViPmPrjreportsLantek2Service {

    private final IDisNestNest00000100Service disNestNest00000100Service;

    private final ButConfigProperties butConfigProperties;

    /**
     * 通过lantek套料表同步报工状态
     * @return 同步结果
     */
    @Override
    public boolean syncReportNestingProgram() {

        Integer syncNestingReportingMaximumDays = butConfigProperties.getSyncNestingReportingMaximumDays();
        if (syncNestingReportingMaximumDays == null || syncNestingReportingMaximumDays <= 0) {
            syncNestingReportingMaximumDays = 30;
        }
        LocalDateTime beginTime = LocalDateTime.now().minusDays(syncNestingReportingMaximumDays);
        List<DisNestNest00000100> ncrs = disNestNest00000100Service.list(Wrappers.lambdaQuery(DisNestNest00000100.class)
                /*.and(w -> w.isNull(DisNestNest00000100::getDescrip)
                        .or()
                        .eq(DisNestNest00000100::getDescrip, ""))*/
                .ne(DisNestNest00000100::getMState, NestConstant.MState.COMPLETED)
                .ge(DisNestNest00000100::getLastDate, beginTime)
                .select(DisNestNest00000100::getCNC));

        //所有未报工的cnc
        List<String> cncs = ncrs.stream().map(DisNestNest00000100::getCNC).toList();

        //为空，不需要同步报工状态
        if (CollectionUtils.isEmpty(cncs)){
            log.warn("没有需要同步报工状态的套料程序");
            return false;
        }

        //已报工的程序钢板信息
        List<ViPmPrjreportsLantek> viPmPrjreportsLanteks =
                listByIn(Wrappers.lambdaQuery(ViPmPrjreportsLantek.class), ViPmPrjreportsLantek::getCNC, cncs, 1000);

        if (CollectionUtils.isEmpty(viPmPrjreportsLanteks)){
            log.warn("没有报工信息，套料程序：{}",String.join(",",cncs));
            return false;
        }

        //更新已报工状态到套料程序
        List<String> reportCncs = viPmPrjreportsLanteks.stream().map(ViPmPrjreportsLantek::getCNC).distinct().toList();

        log.info("正在更新套料程序报工状态：{}",String.join(",", reportCncs));

        return disNestNest00000100Service.update(Wrappers.lambdaUpdate(DisNestNest00000100.class)
//                        .set(DisNestNest00000100::getDescrip,"已报工")
                        .set(DisNestNest00000100::getMState,NestConstant.MState.COMPLETED)
                .in(DisNestNest00000100::getCNC,reportCncs));
    }
}
