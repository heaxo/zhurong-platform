package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportDstv extends BaseImport {

    public ImportDstv(boolean importFromExcel, String path) {
        super(118, importFromExcel, path);
    }

    public ImportDstv(boolean importFromExcel, String path, Path expertPath) {
        super(118, importFromExcel, path, expertPath);
    }
}