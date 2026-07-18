package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportEPS extends BaseImport {

    public ImportEPS(boolean importFromExcel, String path) {
        super(122, importFromExcel, path);
    }

    public ImportEPS(boolean importFromExcel, String path, Path expertPath) {
        super(122, importFromExcel, path, expertPath);
    }
}