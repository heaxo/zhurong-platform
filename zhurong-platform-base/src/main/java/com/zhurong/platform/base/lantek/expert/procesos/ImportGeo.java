package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportGeo extends BaseImport {

    public ImportGeo(boolean importFromExcel, String path) {
        super(110, importFromExcel, path);
    }

    public ImportGeo(boolean importFromExcel, String path, Path expertPath) {
        super(110, importFromExcel, path, expertPath);
    }
}