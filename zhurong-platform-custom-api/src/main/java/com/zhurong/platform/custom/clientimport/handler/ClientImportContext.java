package com.zhurong.platform.custom.clientimport.handler;

import com.zhurong.platform.core.clientimport.mq.ClientImportTaskMessage;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskPayloadItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ClientImportContext<T> {

    /**
     * core下发的批次任务元数据，包含taskId、requestId、businessType、targetClientId等。
     */
    private final ClientImportTaskMessage task;

    /**
     * 带core记录ID的批量数据。后续导入成功后应把成功项的recordId放入ClientImportResult。
     */
    private final List<ClientImportTaskPayloadItem<T>> items;

    /**
     * 调用方当时传入的List数据。image有值时已经被core替换为共享文件路径。
     */
    public List<T> getDataList() {
        return items.stream().map(ClientImportTaskPayloadItem::getData).toList();
    }

    public List<Long> getRecordIds() {
        return items.stream().map(ClientImportTaskPayloadItem::getRecordId).toList();
    }
}
