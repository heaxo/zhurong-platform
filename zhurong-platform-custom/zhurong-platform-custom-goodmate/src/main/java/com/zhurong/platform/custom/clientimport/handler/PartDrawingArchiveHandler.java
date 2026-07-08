package com.zhurong.platform.custom.clientimport.handler;

import com.zhurong.platform.core.clientimport.dto.PartDrawingArchiveRequest;
import com.zhurong.platform.core.clientimport.mq.ClientImportBusinessTypes;
import com.zhurong.platform.custom.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@ConditionalOnClientCommunicationEnabled
public class PartDrawingArchiveHandler implements ClientImportHandler<PartDrawingArchiveRequest> {

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
        log.info("goodmate收到钣金件图纸档案批量任务 taskId={}, requestId={}, count={}, recordIds={}, dataList={}",
                context.getTask().getTaskId(),
                context.getTask().getRequestId(),
                dataList.size(),
                recordIds,
                dataList);

        /*
         * TODO 固美特后续实现位置：
         * 1. 按dataList批量读取core下发的标准字段和extensions扩展字段。
         * 2. 每条data.image已经是共享目录路径，可直接交给本地套料软件读取。
         * 3. 哪些记录真实导入成功，就返回对应ClientImportTaskPayloadItem.recordId。
         */
        return ClientImportResult.successAll("钣金件图纸档案模板批量处理完成：" + dataList.size(), context);
    }
}
