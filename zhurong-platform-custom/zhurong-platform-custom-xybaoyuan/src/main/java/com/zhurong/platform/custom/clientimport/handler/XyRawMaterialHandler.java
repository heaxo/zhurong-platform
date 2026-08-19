package com.zhurong.platform.custom.clientimport.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhurong.platform.base.clientimport.dto.RawMaterialRequest;
import com.zhurong.platform.base.lantek.expert.lst.PlateAndRemnantLstTool;
import com.zhurong.platform.base.lantek.expert.procesos.AutomationInstructionBuilder;
import com.zhurong.platform.base.lantek.expert.procesos.CreateAndUpdateBoard;
import com.zhurong.platform.core.clientimport.mq.ClientImportBusinessTypes;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskPayloadItem;
import com.zhurong.platform.custom.entity.PprrPprr00000100;
import com.zhurong.platform.custom.properties.XyBaoyuanProperties;
import com.zhurong.platform.custom.service.IPprrPprr00000100Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/** 在目标 Windows 客户端执行象屿宝元钢板 PRC 导入。 */
@Slf4j
@Component
@RequiredArgsConstructor
public class XyRawMaterialHandler implements ClientImportHandler<RawMaterialRequest> {

    private final XyBaoyuanProperties properties;
    private final IPprrPprr00000100Service partService;
    private final XyLantekExecutionLock executionLock;

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
        try {
            return executionLock.execute(() -> executeLocked(context));
        } catch (Exception ex) {
            log.error("象屿宝元钢板客户端导入异常, taskId={}", context.getTask().getTaskId(), ex);
            return ClientImportResult.failed("钢板客户端导入失败: " + safeMessage(ex), List.of());
        }
    }

    private ClientImportResult executeLocked(ClientImportContext<RawMaterialRequest> context) throws Exception {
        List<RawMaterialRequest> data = context.getDataList();
        if (data.isEmpty()) {
            return ClientImportResult.success("当前批次没有待导入钢板", List.of());
        }
        String install = properties.getLantek().getInstall();
        if (!StringUtils.hasText(install)) {
            throw new IllegalArgumentException("未配置Lantek安装目录");
        }

        Set<String> importedBefore = findImportedRefs(data);
        List<RawMaterialRequest> pending = data.stream()
                .filter(item -> !importedBefore.contains(item.getPrdRef()))
                .toList();
        if (!pending.isEmpty()) {
            String lstPath = PlateAndRemnantLstTool.exportRawMaterials(pending);
            AutomationInstructionBuilder.ExecResult result = new AutomationInstructionBuilder(
                    AutomationInstructionBuilder.AutomationVersion.V45,
                    install.trim()
            ).withPrcEncoding(AutomationInstructionBuilder.PrcEncoding.ANSI)
                    .addInstruction(new CreateAndUpdateBoard(lstPath))
                    .execute();
            if (!result.success()) {
                throw new IllegalStateException(automationError("钢板自动化导入失败", result));
            }
        }

        Map<String, PprrPprr00000100> importedParts = findImportedParts(data);
        for (RawMaterialRequest request : data) {
            PprrPprr00000100 material = importedParts.get(request.getPrdRef());
            if (material == null) {
                continue;
            }
            // LST 支持字段有限，客户回传所需库存/物料内码/批号在导入成功后写入板材用户字段。
            material.setDIS_UData1_Sht(request.getUdata1());
            material.setDIS_UData2_Sht(request.getUdata2());
            material.setDIS_UData3_Sht(request.getUdata3());
            partService.updateById(material);
        }

        List<Long> importedIds = context.getItems().stream()
                .filter(item -> importedParts.containsKey(item.getData().getPrdRef()))
                .map(ClientImportTaskPayloadItem::getRecordId)
                .toList();
        if (importedIds.size() != context.getItems().size()) {
            return ClientImportResult.failed(
                    "钢板部分导入失败: " + importedIds.size() + "/" + context.getItems().size(),
                    importedIds
            );
        }
        return ClientImportResult.successAll("钢板导入完成: " + data.size(), context);
    }

    private Set<String> findImportedRefs(Collection<RawMaterialRequest> data) {
        return findImportedParts(data).keySet();
    }

    private Map<String, PprrPprr00000100> findImportedParts(Collection<RawMaterialRequest> data) {
        List<String> refs = data.stream()
                .map(RawMaterialRequest::getPrdRef)
                .filter(StringUtils::hasText)
                .distinct()
                .toList();
        if (refs.isEmpty()) {
            return Map.of();
        }
        return partService.list(Wrappers.lambdaQuery(PprrPprr00000100.class)
                        .in(PprrPprr00000100::getPrdRef, refs))
                .stream()
                .collect(Collectors.toMap(PprrPprr00000100::getPrdRef, Function.identity(), (left, right) -> left));
    }

    private static String automationError(
            String prefix,
            AutomationInstructionBuilder.ExecResult result
    ) {
        String detail = StringUtils.hasText(result.stderr()) ? result.stderr().trim() : result.stdout().trim();
        return StringUtils.hasText(detail) ? prefix + ": " + detail : prefix + "，退出码: " + result.exitCode();
    }

    private static String safeMessage(Exception ex) {
        return StringUtils.hasText(ex.getMessage()) ? ex.getMessage() : ex.getClass().getSimpleName();
    }
}
