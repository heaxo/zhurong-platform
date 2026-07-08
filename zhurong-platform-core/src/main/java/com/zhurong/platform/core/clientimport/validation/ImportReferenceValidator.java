package com.zhurong.platform.core.clientimport.validation;

public interface ImportReferenceValidator {

    /**
     * 校验材质编码。本次只保留扩展入口，具体实现由后续业务补充。
     */
    void validateMaterial(String businessType, String matRef);

    /**
     * 校验机床编码。wrkRef为空时允许跳过，后续可以由默认配置或custom处理。
     */
    void validateMachine(String businessType, String wrkRef);
}
