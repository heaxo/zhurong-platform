package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportME10 extends BaseImport {

    public ImportME10(boolean importFromExcel, String path) {
        super(114, importFromExcel, path);
    }

    public ImportME10(boolean importFromExcel, String path, Path expertPath) {
        super(114, importFromExcel, path, expertPath);
    }
}