package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportALMA extends BaseImport {

    public ImportALMA(boolean importFromExcel, String path) {
        super(124, importFromExcel, path);
    }

    public ImportALMA(boolean importFromExcel, String path, Path expertPath) {
        super(124, importFromExcel, path, expertPath);
    }
}