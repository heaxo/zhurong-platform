package com.zhurong.platform.custom.clientimport.handler;

import com.zhurong.platform.core.clientimport.dto.RawMaterialRequest;
import com.zhurong.platform.core.clientimport.mq.ClientImportBusinessTypes;
import com.zhurong.platform.custom.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@ConditionalOnClientCommunicationEnabled
public class RawMaterialHandler implements ClientImportHandler<RawMaterialRequest> {

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
        log.info("goodmate收到原材料批量任务 taskId={}, requestId={}, count={}, recordIds={}, dataList={}",
                context.getTask().getTaskId(),
                context.getTask().getRequestId(),
                dataList.size(),
                recordIds,
                dataList);

        /*
         * TODO 固美特后续实现位置：
         * 1. 批量读取板材编码、规格、数量和extensions。
         * 2. data.image为空表示整板；不为空表示余料共享文件路径。
         * 3. 本地套料软件成功导入哪些记录，就返回对应recordId集合。
         */
        return ClientImportResult.successAll("原材料模板批量处理完成：" + dataList.size(), context);
    }
}
