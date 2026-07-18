package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportDxf extends BaseImport {

    public ImportDxf(boolean importFromExcel, String path) {
        super(107, importFromExcel, path);
    }

    public ImportDxf(boolean importFromExcel, String path, Path expertPath) {
        super(107, importFromExcel, path, expertPath);
    }
}