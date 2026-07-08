package com.zhurong.platform.custom.clientimport.handler;

import com.zhurong.platform.core.clientimport.dto.ProductionOrderRequest;
import com.zhurong.platform.core.clientimport.mq.ClientImportBusinessTypes;
import com.zhurong.platform.custom.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@ConditionalOnClientCommunicationEnabled
public class ProductionOrderHandler implements ClientImportHandler<ProductionOrderRequest> {

    @Override
    public String businessType() {
        return ClientImportBusinessTypes.PRODUCTION_ORDER;
    }

    @Override
    public Class<ProductionOrderRequest> payloadType() {
        return ProductionOrderRequest.class;
    }

    @Override
    public ClientImportResult execute(ClientImportContext<ProductionOrderRequest> context) {
        List<ProductionOrderRequest> dataList = context.getDataList();
        List<Long> recordIds = context.getRecordIds();
        log.info("goodmate收到生产订单批量任务 taskId={}, requestId={}, count={}, recordIds={}, dataList={}",
                context.getTask().getTaskId(),
                context.getTask().getRequestId(),
                dataList.size(),
                recordIds,
                dataList);

        /*
         * TODO 固美特后续实现位置：
         * 1. 批量映射工单、订单、数量等标准字段。
         * 2. image不为空的记录可同时读取共享图纸文件并按客户规则补建图纸档案。
         * 3. 本地套料软件返回成功导入的记录后，只回传对应recordId集合。
         */
        return ClientImportResult.successAll("生产订单模板批量处理完成：" + dataList.size(), context);
    }
}
