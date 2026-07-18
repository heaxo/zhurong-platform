package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportImageVectorization extends BaseImport {

    public ImportImageVectorization(boolean importFromExcel, String path) {
        super(133, importFromExcel, path);
    }

    public ImportImageVectorization(boolean importFromExcel, String path, Path expertPath) {
        super(133, importFromExcel, path, expertPath);
    }
}