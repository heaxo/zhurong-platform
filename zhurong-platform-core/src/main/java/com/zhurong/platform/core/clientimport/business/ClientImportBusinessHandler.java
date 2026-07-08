package com.zhurong.platform.core.clientimport.business;

import com.zhurong.platform.core.clientimport.dto.ClientImportRequest;
import com.zhurong.platform.core.clientimport.entity.ClientImportRecordBase;
import com.zhurong.platform.core.clientimport.enums.DispatchStatus;
import com.zhurong.platform.core.clientimport.file.StoredImportFile;
import com.zhurong.platform.core.clientimport.mq.ClientImportTaskPayloadItem;

import java.util.List;
import java.util.Set;

/*
 * core端业务类型扩展点。
 * 新增标准导入业务时，新建一个实现类集中放置查重、保存、状态更新和payload转换逻辑。
 */
public interface ClientImportBusinessHandler<T, R extends ClientImportRecordBase> {

    String businessType();

    String businessKeyName();

    String businessKey(T data);

    default String materialRef(T data) {
        return null;
    }

    default String machineRef(T data) {
        return null;
    }

    default String image(T data) {
        return null;
    }

    List<R> findExistingByBusinessKeys(Set<String> businessKeys);

    String recordBusinessKey(R record);

    void save(
            String requestId,
            ClientImportRequest<List<T>> request,
            T data,
            int requestItemIndex,
            String taskId,
            String targetClientId,
            StoredImportFile storedFile);

    void updateNotImportedRows(String requestId, DispatchStatus status, String message);

    void markImportedRows(String requestId, List<Long> importedRecordIds, String message);

    long countNotImported(String requestId);

    List<ClientImportTaskPayloadItem<T>> listPendingPayload(String requestId);
}
