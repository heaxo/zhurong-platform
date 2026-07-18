package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportStampingASCII extends BaseImport {

    public ImportStampingASCII(boolean importFromExcel, String path) {
        super(127, importFromExcel, path);
    }

    public ImportStampingASCII(boolean importFromExcel, String path, Path expertPath) {
        super(127, importFromExcel, path, expertPath);
    }
}