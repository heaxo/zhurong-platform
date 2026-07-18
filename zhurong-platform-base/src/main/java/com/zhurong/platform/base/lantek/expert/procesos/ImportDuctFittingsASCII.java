package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportDuctFittingsASCII extends BaseImport {

    public ImportDuctFittingsASCII(boolean importFromExcel, String path) {
        super(106, importFromExcel, path);
    }

    public ImportDuctFittingsASCII(boolean importFromExcel, String path, Path expertPath) {
        super(106, importFromExcel, path, expertPath);
    }
}