package com.zhurong.platform.core.clientimport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zhurong.platform.base.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ClientImportRecordBase extends BaseEntity {

    @TableField("RequestId")
    private String requestId;

    @TableField("RequestItemIndex")
    private Integer requestItemIndex;

    @TableField("TaskId")
    private String taskId;

    @TableField("TargetClientId")
    private String targetClientId;

    @TableField("OriginalImage")
    private String originalImage;

    @TableField("StoredFileId")
    private String storedFileId;

    @TableField("StoredRelativePath")
    private String storedRelativePath;

    @TableField("ClientImagePath")
    private String clientImagePath;

    @TableField("ExtensionsJson")
    private String extensionsJson;

    @TableField("Imported")
    private Boolean imported;

    @TableField("DispatchStatus")
    private String dispatchStatus;

    @TableField("DispatchMessage")
    private String dispatchMessage;
}
