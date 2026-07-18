package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportMec extends BaseImport {

    public ImportMec(boolean importFromExcel, String path) {
        super(115, importFromExcel, path);
    }

    public ImportMec(boolean importFromExcel, String path, Path expertPath) {
        super(115, importFromExcel, path, expertPath);
    }
}