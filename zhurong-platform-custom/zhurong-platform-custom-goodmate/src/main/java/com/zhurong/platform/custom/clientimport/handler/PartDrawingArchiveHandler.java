package com.zhurong.platform.custom.clientimport.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhurong.platform.base.clientimport.dto.PartDrawingArchiveRequest;
import com.zhurong.platform.base.lantek.expert.lstx.ExpertProductXmlItem;
import com.zhurong.platform.base.lantek.expert.lstx.LstxImportTool;
import com.zhurong.platform.core.clientimport.mq.ClientImportBusinessTypes;
import com.zhurong.platform.custom.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.custom.entity.PprrPprr00000100;
import com.zhurong.platform.custom.properties.LantekConfigProperties;
import com.zhurong.platform.custom.service.IPprrPprr00000100Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnClientCommunicationEnabled
public class PartDrawingArchiveHandler implements ClientImportHandler<PartDrawingArchiveRequest> {

    private final LantekConfigProperties lantekConfigProperties;
    private final IPprrPprr00000100Service pprrPprr00000100Service;

    @Override
    public String businessType() {
        return ClientImportBusinessTypes.PART_DRAWING_ARCHIVE;
    }

    @Override
    public Class<PartDrawingArchiveRequest> payloadType() {
        return PartDrawingArchiveRequest.class;
    }

    @Override
    public ClientImportResult execute(ClientImportContext<PartDrawingArchiveRequest> context) {
        List<PartDrawingArchiveRequest> dataList = context.getDataList();
        List<Long> recordIds = context.getRecordIds();
        log.info("goodmate received part drawing archive task, taskId={}, requestId={}, count={}, recordIds={}",
                context.getTask().getTaskId(),
                context.getTask().getRequestId(),
                dataList.size(),
                recordIds);

        String lantekInstall = lantekConfigProperties.getInstall();
        if (!StringUtils.hasText(lantekInstall)) {
            return ClientImportResult.failed("Lantek install path is not configured.", List.of());
        }
        if (dataList.isEmpty()) {
            return ClientImportResult.success("Part drawing archive task has no pending data.", List.of());
        }

        try {
            LstxImportTool.ImportType importType = resolveImportType(dataList);
            List<ExpertProductXmlItem> products = LstxImportTool.toProductXmlItems(dataList);
            List<String> prdRefs = products.stream().map(ExpertProductXmlItem::getReference).distinct().toList();
            LstxImportTool.ImportExecutionResult result = LstxImportTool.exportAndExecute(
                    products,
                    Path.of(lantekInstall),
                    importType
            );
            LstxImportTool.ExecResult execResult = result.execResult();
            if (!execResult.success()) {
                log.warn("goodmate part drawing archive import failed, xmlPath={}, exitCode={}, stdout={}, stderr={}",
                        result.xmlPath(), execResult.exitCode(), execResult.stdout(), execResult.stderr());
                return ClientImportResult.failed("LSTX import failed, exitCode=" + execResult.exitCode(), List.of());
            }
            log.info("goodmate part drawing archive import success, xmlPath={}, durationMs={}",
                    result.xmlPath(), execResult.durationMs());

            List<PprrPprr00000100> importedSucceeds = pprrPprr00000100Service.list(Wrappers.lambdaQuery(PprrPprr00000100.class)
                    .in(PprrPprr00000100::getPrdRef, prdRefs));

            Set<String> importedPrdRefs = importedSucceeds.stream()
                    .map(PprrPprr00000100::getPrdRef)
                    .filter(StringUtils::hasText)
                    .collect(Collectors.toSet());
            List<Long> importedRecordIds = context.getItems().stream()
                    .filter(item -> importedPrdRefs.contains(item.getData().getPrdRef()))
                    .map(com.zhurong.platform.core.clientimport.mq.ClientImportTaskPayloadItem::getRecordId)
                    .toList();

            if (importedRecordIds.size() == recordIds.size()) {
                return ClientImportResult.successAll("Part drawing archive import completed: " + dataList.size(), context);
            }
            return ClientImportResult.success(
                    "Part drawing archive import partially completed: " + importedRecordIds.size() + "/" + dataList.size(),
                    importedRecordIds
            );
        } catch (Exception e) {
            log.error("goodmate part drawing archive import exception", e);
            return ClientImportResult.failed("LSTX import exception: " + e.getMessage(), List.of());
        }
    }

    private LstxImportTool.ImportType resolveImportType(List<PartDrawingArchiveRequest> dataList) {
        List<LstxImportTool.ImportType> importTypes = dataList.stream()
                .map(PartDrawingArchiveRequest::getImage)
                .map(LstxImportTool::importTypeFromDrawingPath)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
        if (importTypes.size() != 1) {
            throw new IllegalArgumentException("Part drawing archive batch must use exactly one drawing type: " + importTypes);
        }
        return importTypes.getFirst();
    }
}
