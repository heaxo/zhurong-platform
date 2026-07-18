package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportPdf extends BaseImport {

    public ImportPdf(boolean importFromExcel, String path) {
        super(136, importFromExcel, path);
    }

    public ImportPdf(boolean importFromExcel, String path, Path expertPath) {
        super(136, importFromExcel, path, expertPath);
    }
}