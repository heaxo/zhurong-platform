package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportCuttingFileISO extends BaseImport {

    public ImportCuttingFileISO(boolean importFromExcel, String path) {
        super(113, importFromExcel, path);
    }

    public ImportCuttingFileISO(boolean importFromExcel, String path, Path expertPath) {
        super(113, importFromExcel, path, expertPath);
    }
}