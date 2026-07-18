package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportKitFromDatabase extends BaseImport {

    public ImportKitFromDatabase(boolean importFromExcel, String path) {
        super(103, importFromExcel, path);
    }

    public ImportKitFromDatabase(boolean importFromExcel, String path, Path expertPath) {
        super(103, importFromExcel, path, expertPath);
    }
}