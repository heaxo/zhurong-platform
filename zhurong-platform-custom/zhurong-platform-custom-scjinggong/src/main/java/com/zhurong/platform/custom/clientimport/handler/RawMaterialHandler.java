package com.zhurong.platform.custom.clientimport.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhurong.platform.base.clientimport.dto.RawMaterialRequest;
import com.zhurong.platform.base.lantek.expert.lst.PlateAndRemnantLstTool;
import com.zhurong.platform.base.lantek.expert.procesos.AutomationInstructionBuilder;
import com.zhurong.platform.base.lantek.expert.procesos.CreateAndUpdateBoard;
import com.zhurong.platform.core.clientimport.mq.ClientImportBusinessTypes;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskPayloadItem;
import com.zhurong.platform.custom.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.custom.entity.PprrPprr00000100;
import com.zhurong.platform.custom.properties.LantekConfigProperties;
import com.zhurong.platform.custom.service.IPprrPprr00000100Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnClientCommunicationEnabled
public class RawMaterialHandler implements ClientImportHandler<RawMaterialRequest> {

    private final LantekConfigProperties lantekConfigProperties;
    private final IPprrPprr00000100Service pprrPprr00000100Service;

    @Override
    public String businessType() {
        return ClientImportBusinessTypes.RAW_MATERIAL;
    }

    @Override
    public Class<RawMaterialRequest> payloadType() {
        return RawMaterialRequest.class;
    }

    @Override
    public ClientImportResult execute(ClientImportContext<RawMaterialRequest> context) {
        List<RawMaterialRequest> dataList = context.getDataList();
        List<Long> recordIds = context.getRecordIds();
        log.info("goodmate收到钢板/余料批量导入任务，taskId={}, requestId={}, count={}, recordIds={}",
                context.getTask().getTaskId(),
                context.getTask().getRequestId(),
                dataList.size(),
                recordIds);

        String lantekInstall = lantekConfigProperties.getInstall();
        if (!StringUtils.hasText(lantekInstall)) {
            return ClientImportResult.failed("Lantek安装目录未配置，无法执行钢板/余料导入。", List.of());
        }
        if (dataList.isEmpty()) {
            return ClientImportResult.success("当前批次没有待导入钢板/余料数据。", List.of());
        }

        try {
            List<String> prdRefs = dataList.stream()
                    .map(RawMaterialRequest::getPrdRef)
                    .filter(StringUtils::hasText)
                    .distinct()
                    .toList();
            String lstPath = PlateAndRemnantLstTool.exportRawMaterials(dataList);
            AutomationInstructionBuilder builder = new AutomationInstructionBuilder(
                    AutomationInstructionBuilder.AutomationVersion.V45,
                    lantekInstall
            ).addInstruction(new CreateAndUpdateBoard(lstPath));

            Path prcPath = builder.buildPrcPath();
            AutomationInstructionBuilder.ExecResult execResult = builder.execute(prcPath);
            if (!execResult.success()) {
                log.warn("goodmate钢板/余料PRC导入失败，lstPath={}, prcPath={}, exitCode={}, stdout={}, stderr={}",
                        lstPath, prcPath, execResult.exitCode(), execResult.stdout(), execResult.stderr());
                return ClientImportResult.failed("钢板/余料PRC导入失败，exitCode=" + execResult.exitCode(), List.of());
            }

            Set<String> importedPrdRefs = findImportedPrdRefs(prdRefs);
            List<Long> importedRecordIds = recordIdsForPrdRefs(context, importedPrdRefs);
            log.info("goodmate钢板/余料PRC导入完成，lstPath={}, prcPath={}, durationMs={}",
                    lstPath, prcPath, execResult.durationMs());

            if (importedRecordIds.size() == recordIds.size()) {
                return ClientImportResult.successAll("钢板/余料导入完成：" + dataList.size(), context);
            }
            return ClientImportResult.success(
                    "钢板/余料部分导入完成：" + importedRecordIds.size() + "/" + dataList.size(),
                    importedRecordIds
            );
        } catch (Exception e) {
            log.error("goodmate钢板/余料导入异常", e);
            return ClientImportResult.failed("钢板/余料导入异常：" + e.getMessage(), List.of());
        }
    }

    private Set<String> findImportedPrdRefs(Collection<String> prdRefs) {
        if (prdRefs.isEmpty()) {
            return Set.of();
        }
        /*
         * 钢板、余料和图纸档案在Lantek中都会落到PPRR产品表。
         * 导入完成后只能按PrdRef反查产品表，确认本次请求中哪些记录已经真正写入Lantek。
         */
        return pprrPprr00000100Service.list(Wrappers.lambdaQuery(PprrPprr00000100.class)
                        .in(PprrPprr00000100::getPrdRef, prdRefs))
                .stream()
                .map(PprrPprr00000100::getPrdRef)
                .filter(StringUtils::hasText)
                .collect(Collectors.toSet());
    }

    private List<Long> recordIdsForPrdRefs(
            ClientImportContext<RawMaterialRequest> context,
            Set<String> importedPrdRefs
    ) {
        return context.getItems().stream()
                .filter(item -> importedPrdRefs.contains(item.getData().getPrdRef()))
                .map(ClientImportTaskPayloadItem::getRecordId)
                .toList();
    }
}
