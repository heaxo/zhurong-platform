package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportStampingFileISO extends BaseImport {

    public ImportStampingFileISO(boolean importFromExcel, String path) {
        super(109, importFromExcel, path);
    }

    public ImportStampingFileISO(boolean importFromExcel, String path, Path expertPath) {
        super(109, importFromExcel, path, expertPath);
    }
}