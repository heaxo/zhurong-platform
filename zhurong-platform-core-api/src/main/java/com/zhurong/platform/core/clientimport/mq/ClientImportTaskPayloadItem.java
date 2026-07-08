package com.zhurong.platform.core.clientimport.mq;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClientImportTaskPayloadItem<T> implements Serializable {

    /**
     * core业务表主键。客户端导入成功后回传该id，core据此把对应记录Imported标记为true。
     */
    private Long recordId;

    /**
     * 原始请求data数组中的下标，便于排查和保持列表顺序。
     */
    private Integer requestItemIndex;

    /**
     * 标准业务数据。若原始image不为空，core下发前已替换为客户端可访问的共享路径。
     */
    private T data;
}
