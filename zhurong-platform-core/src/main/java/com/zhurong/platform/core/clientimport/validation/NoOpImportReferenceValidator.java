package com.zhurong.platform.core.clientimport.validation;

public class NoOpImportReferenceValidator implements ImportReferenceValidator {

    @Override
    public void validateMaterial(String businessType, String matRef) {
        // 默认不进行业务校验，后续可以注册自定义实现替换。
    }

    @Override
    public void validateMachine(String businessType, String wrkRef) {
        // 默认不进行业务校验，后续可以注册自定义实现替换。
    }
}
