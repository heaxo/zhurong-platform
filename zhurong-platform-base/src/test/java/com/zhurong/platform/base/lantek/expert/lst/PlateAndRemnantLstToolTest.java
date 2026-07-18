package com.zhurong.platform.base.lantek.expert.lst;

import com.zhurong.platform.base.clientimport.dto.RawMaterialRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlateAndRemnantLstToolTest {

    @TempDir
    Path tempDir;

    @Test
    void exportsSheetLstLinesWithStandardFormat() throws Exception {
        Path lstFile = tempDir.resolve("boards.lst");

        PlateAndRemnantLstTool.exportToFile(List.of(
                PlateAndRemnantLstItem.create()
                        .reference("JZL-JJQY10001")
                        .length(5850)
                        .width(1500)
                        .thickness(10)
                        .material("Q355D")
                        .quantity(1)
                        .priority(0)
                        .userData1("1")
                        .userData2("2")
                        .userData3("3")
                        .sheet(),
                PlateAndRemnantLstItem.create()
                        .reference("JZL-HL12001")
                        .length(12100)
                        .width(2920)
                        .thickness(12)
                        .material("Q355D")
                        .quantity(4)
                        .priority(0)
                        .userData1("2")
                        .userData2("3")
                        .userData3("4")
                        .sheet(),
                PlateAndRemnantLstItem.create()
                        .reference("JZL-JJ12001")
                        .length(12020)
                        .width(2200)
                        .thickness(12)
                        .material("Q355D")
                        .quantity(17)
                        .priority(0)
                        .userData1("3")
                        .userData2("4")
                        .userData3("5")
                        .sheet()
        ), lstFile);

        assertEquals(List.of(
                "\"JZL-JJQY10001\" 5850 1500 10 \"Q355D\" 1 0 \"1\" \"2\" \"3\" 0 \"\"",
                "\"JZL-HL12001\" 12100 2920 12 \"Q355D\" 4 0 \"2\" \"3\" \"4\" 0 \"\"",
                "\"JZL-JJ12001\" 12020 2200 12 \"Q355D\" 17 0 \"3\" \"4\" \"5\" 0 \"\""
        ), Files.readAllLines(lstFile, StandardCharsets.UTF_8));
    }

    @Test
    void mapsRawMaterialImageToRemnantDxfPath() throws Exception {
        RawMaterialRequest request = new RawMaterialRequest();
        request.setPrdRef("JZL-HL12001");
        request.setLength(12100F);
        request.setWidth(2920F);
        request.setThickness(12F);
        request.setMatRef("Q355D");
        request.setQuantity(4);
        request.setUdata1("2");
        request.setUdata2("3");
        request.setUdata3("4");
        request.setImage("D:\\remnant\\JZL-HL12001.dxf");

        String lstPath = PlateAndRemnantLstTool.exportRawMaterials(List.of(request), tempDir);

        assertTrue(lstPath.endsWith(".lst"));
        assertEquals(List.of(
                "\"JZL-HL12001\" 12100 2920 12 \"Q355D\" 4 0 \"2\" \"3\" \"4\" 2 \"D:\\remnant\\JZL-HL12001.dxf\""
        ), Files.readAllLines(Path.of(lstPath), StandardCharsets.UTF_8));
    }
}
