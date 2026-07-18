package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportPartsFromDatabase extends BaseImport {

    public ImportPartsFromDatabase(boolean importFromExcel, String path) {
        super(101, importFromExcel, path);
    }

    public ImportPartsFromDatabase(boolean importFromExcel, String path, Path expertPath) {
        super(101, importFromExcel, path, expertPath);
    }
}