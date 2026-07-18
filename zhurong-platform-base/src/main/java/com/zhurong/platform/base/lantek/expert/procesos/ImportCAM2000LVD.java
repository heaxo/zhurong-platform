package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;

public class ImportCAM2000LVD extends BaseImport {

    public ImportCAM2000LVD(boolean importFromExcel, String path) {
        super(125, importFromExcel, path);
    }

    public ImportCAM2000LVD(boolean importFromExcel, String path, Path expertPath) {
        super(125, importFromExcel, path, expertPath);
    }
}