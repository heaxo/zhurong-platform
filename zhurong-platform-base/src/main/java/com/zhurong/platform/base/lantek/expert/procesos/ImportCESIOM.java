package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportCESIOM extends BaseImport {

    public ImportCESIOM(boolean importFromExcel, String path) {
        super(123, importFromExcel, path);
    }

    public ImportCESIOM(boolean importFromExcel, String path, Path expertPath) {
        super(123, importFromExcel, path, expertPath);
    }
}