package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportDwg extends BaseImport {

    public ImportDwg(boolean importFromExcel, String path) {
        super(119, importFromExcel, path);
    }

    public ImportDwg(boolean importFromExcel, String path, Path expertPath) {
        super(119, importFromExcel, path, expertPath);
    }
}