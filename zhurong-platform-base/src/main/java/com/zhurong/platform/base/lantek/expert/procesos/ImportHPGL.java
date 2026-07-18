package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportHPGL extends BaseImport {

    public ImportHPGL(boolean importFromExcel, String path) {
        super(111, importFromExcel, path);
    }

    public ImportHPGL(boolean importFromExcel, String path, Path expertPath) {
        super(111, importFromExcel, path, expertPath);
    }
}