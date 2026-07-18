package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportESSI extends BaseImport {

    public ImportESSI(boolean importFromExcel, String path) {
        super(108, importFromExcel, path);
    }

    public ImportESSI(boolean importFromExcel, String path, Path expertPath) {
        super(108, importFromExcel, path, expertPath);
    }
}