package com.zhurong.platform.custom.clientimport.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientImportResult {

    private boolean success;

    private String message;

    /**
     * 本次实际导入成功的core业务表主键集合。允许部分成功，core会只把这些id的Imported标记为true。
     */
    private List<Long> importedRecordIds;

    public static ClientImportResult success(String message, List<Long> importedRecordIds) {
        return new ClientImportResult(true, message, importedRecordIds);
    }

    public static ClientImportResult successAll(String message, ClientImportContext<?> context) {
        return success(message, context.getRecordIds());
    }

    public static ClientImportResult failed(String message, List<Long> importedRecordIds) {
        return new ClientImportResult(false, message, importedRecordIds);
    }
}
