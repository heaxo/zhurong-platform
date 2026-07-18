package com.zhurong.platform.base.lantek.expert.procesos;

import java.nio.file.Path;
import java.util.Locale;

public class BaseImport extends AutomationInstruction {

    private final AutomaticMode auto = AutomaticMode.Enable;
    private final boolean importFromExcel;
    private final String path;
    private boolean silent;
    private String excelCFG;

    public BaseImport(int instructionId, boolean importFromExcel, String path) {
        this(instructionId, importFromExcel, path, null);
    }

    public BaseImport(int instructionId, boolean importFromExcel, String path, Path expertPath) {
        super(instructionId);
        validatePath(importFromExcel, path);
        this.importFromExcel = importFromExcel;
        this.path = path;
        this.excelCFG = expertPath == null ? "ExcelImp.cfg" : expertPath.resolve("ExcelImp.cfg").toString();
    }

    @Override
    public String generateInstructionText() {
        if (importFromExcel) {
            return getInstructionId() + " " + (silent ? "1" : "") + "2 " + quote(path) + " " + quote(excelCFG);
        }
        return getInstructionId() + " " + auto.code() + (silent ? "1" : "") + " " + quote(path);
    }

    public BaseImport setSilent(boolean value) {
        silent = value;
        return this;
    }

    public BaseImport setExcelCFG(String value) {
        excelCFG = value;
        return this;
    }

    public boolean isSilent() {
        return silent;
    }

    public String getExcelCFG() {
        return excelCFG;
    }

    private static void validatePath(boolean importFromExcel, String path) {
        String extension = extensionOf(path);
        if (importFromExcel) {
            if (!".xls".equals(extension) && !".xlsx".equals(extension)) {
                throw new IllegalArgumentException("File must be an Excel file (.xls or .xlsx).");
            }
            return;
        }
        if (!".lst".equals(extension) && !".lstx".equals(extension)) {
            throw new IllegalArgumentException("File must be an LST file (.lst or .lstx).");
        }
    }

    private static String extensionOf(String path) {
        if (path == null) {
            return "";
        }
        int index = path.lastIndexOf('.');
        if (index < 0) {
            return "";
        }
        return path.substring(index).toLowerCase(Locale.ROOT);
    }
}