package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportIGES extends BaseImport {

    public ImportIGES(boolean importFromExcel, String path) {
        super(112, importFromExcel, path);
    }

    public ImportIGES(boolean importFromExcel, String path, Path expertPath) {
        super(112, importFromExcel, path, expertPath);
    }
}