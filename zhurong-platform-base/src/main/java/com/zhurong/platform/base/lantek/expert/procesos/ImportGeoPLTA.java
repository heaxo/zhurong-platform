package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportGeoPLTA extends BaseImport {

    public ImportGeoPLTA(boolean importFromExcel, String path) {
        super(137, importFromExcel, path);
    }

    public ImportGeoPLTA(boolean importFromExcel, String path, Path expertPath) {
        super(137, importFromExcel, path, expertPath);
    }
}