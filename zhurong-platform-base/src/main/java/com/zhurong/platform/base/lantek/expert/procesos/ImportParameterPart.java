package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportParameterPart extends BaseImport {

    public ImportParameterPart(boolean importFromExcel, String path) {
        super(116, importFromExcel, path);
    }

    public ImportParameterPart(boolean importFromExcel, String path, Path expertPath) {
        super(116, importFromExcel, path, expertPath);
    }
}