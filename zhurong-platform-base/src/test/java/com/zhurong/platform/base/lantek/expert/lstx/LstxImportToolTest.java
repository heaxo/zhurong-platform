package com.zhurong.platform.base.lantek.expert.lstx;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LstxImportToolTest {

    @Test
    void buildsLstxImportCommandLikeCSharpHelper() {
        String command = LstxImportTool.getLstxImportCommand(
                Path.of("C:\\Lantek"),
                Path.of("C:\\Lantek\\CAMAssistant\\1734068206.lstx"),
                LstxImportTool.ImportType.DXF
        );

        assertEquals(
                "C:\\Lantek\\Expert\\IMPASYSW.EXE 7 \"C:\\Lantek\\CAMAssistant\\1734068206.lstx\" \"\" \"\" 1",
                command
        );
    }
}
