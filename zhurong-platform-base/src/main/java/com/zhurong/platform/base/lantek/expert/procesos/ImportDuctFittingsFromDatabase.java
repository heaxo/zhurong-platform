package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportDuctFittingsFromDatabase extends BaseImport {

    public ImportDuctFittingsFromDatabase(boolean importFromExcel, String path) {
        super(102, importFromExcel, path);
    }

    public ImportDuctFittingsFromDatabase(boolean importFromExcel, String path, Path expertPath) {
        super(102, importFromExcel, path, expertPath);
    }
}