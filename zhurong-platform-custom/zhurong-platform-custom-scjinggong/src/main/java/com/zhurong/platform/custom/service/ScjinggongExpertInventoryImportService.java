package com.zhurong.platform.custom.service;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhurong.platform.base.clientimport.dto.RawMaterialRequest;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.base.lantek.expert.lst.PlateAndRemnantLstTool;
import com.zhurong.platform.base.lantek.expert.procesos.AutomationInstructionBuilder;
import com.zhurong.platform.base.lantek.expert.procesos.CreateAndUpdateBoard;
import com.zhurong.platform.custom.entity.PprrPprr00000100;
import com.zhurong.platform.custom.properties.ScjinggongProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * 钢板库存导入Expert的统一执行器。
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ScjinggongExpertInventoryImportService {

    private final ScjinggongProperties properties;
    private final IPprrPprr00000100Service pprrPprr00000100Service;

    public boolean importRawMaterials(List<RawMaterialRequest> requests) throws IOException {
        if (CollectionUtils.isEmpty(requests)) {
            throw new BusinessException("钢板库存数据不能为空");
        }

        String install = properties.getLantek().getInstall();
        if (!StringUtils.hasText(install)) {
            throw new BusinessException("Lantek Expert安装目录未配置");
        }

        log.info("开始导入钢板库存到Expert，数量={}", requests.size());
        String lstPath = PlateAndRemnantLstTool.exportRawMaterials(requests);
        AutomationInstructionBuilder.ExecResult result = new AutomationInstructionBuilder(
                AutomationInstructionBuilder.AutomationVersion.V45,
                install.trim()
        ).withPrcEncoding(AutomationInstructionBuilder.PrcEncoding.ANSI)
                .addInstruction(new CreateAndUpdateBoard(lstPath))
                .execute();
        if (!result.success()) {
            log.error("钢板库存导入Expert失败，数量={}", requests.size());
            throw new BusinessException("钢板导入失败");
        }
        List<String> prdRefs = requests.stream().map(RawMaterialRequest::getPrdRef).toList();
        List<PprrPprr00000100> list = pprrPprr00000100Service.list(Wrappers.lambdaQuery(PprrPprr00000100.class)
                .select(PprrPprr00000100::getPrdRef)
                .select(PprrPprr00000100::getDIS_CamQuan)
                .in(PprrPprr00000100::getPrdRef, prdRefs));
        log.info("钢板库存导入Expert完成，数量={}", list.size());
        return list.size() > 0;
    }
}
