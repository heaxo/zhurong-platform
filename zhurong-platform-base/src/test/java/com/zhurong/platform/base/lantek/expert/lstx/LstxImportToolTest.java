package com.zhurong.platform.base.lantek.expert.lstx;

import com.zhurong.platform.base.clientimport.dto.PartDrawingArchiveRequest;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void mapsPartDrawingArchiveRequestToProductXmlItem() {
        PartDrawingArchiveRequest request = new PartDrawingArchiveRequest();
        request.setPrdRef("P-001");
        request.setPrdName("Part A");
        request.setMatRef("Q235");
        request.setWrkRef("LASER-01");
        request.setThickness(2.5F);
        request.setImage("D:\\parts\\p001.dxf");
        request.setUdata1("U1");

        ExpertProductXmlItem item = LstxImportTool.toProductXmlItem(request);

        assertEquals("P-001", item.productAttributes().get("Reference"));
        assertEquals("Part A", item.productAttributes().get("Name"));
        assertEquals("Q235", item.productAttributes().get("Material"));
        assertEquals("LASER-01", item.productAttributes().get("Machine"));
        assertEquals("2.5", item.productAttributes().get("Thickness"));
        assertEquals("D:\\parts\\p001.dxf", item.productAttributes().get("File"));
        assertEquals("U1", item.productAttributes().get("UserData1"));
    }

    @Test
    void resolvesImportTypeFromDrawingPathAndDefaultOutputDirectory() {
        assertEquals(LstxImportTool.ImportType.DXF, LstxImportTool.importTypeFromDrawingPath("a.DXF"));
        assertEquals(LstxImportTool.ImportType.DWG, LstxImportTool.importTypeFromDrawingPath("http://host/a.dwg?x=1"));
        assertEquals(LstxImportTool.ImportType.MEC, LstxImportTool.importTypeFromDrawingPath("a.mec"));
        assertEquals(LstxImportTool.ImportType.NoDrawing, LstxImportTool.importTypeFromDrawingPath(""));
        assertTrue(LstxImportTool.defaultOutputDirectory().endsWith("lstx"));
    }
}
