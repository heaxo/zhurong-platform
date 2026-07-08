package com.zhurong.platform.custom.clientimport.handler;

public interface ClientImportHandler<T> {

    String businessType();

    /**
     * 返回当前业务类型对应的标准DTO类型，公共派发器会先完成JsonNode到DTO的转换。
     */
    Class<T> payloadType();

    ClientImportResult execute(ClientImportContext<T> context);
}
