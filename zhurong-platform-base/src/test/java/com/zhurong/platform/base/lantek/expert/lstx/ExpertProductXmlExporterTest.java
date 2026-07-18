package com.zhurong.platform.base.lantek.expert.lstx;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpertProductXmlExporterTest {

    @TempDir
    Path tempDir;

    @Test
    void exportsDefaultValues() throws Exception {
        Path xmlFile = tempDir.resolve("products.xml");

        String path = new ExpertProductXmlExporter()
                .export(List.of(ExpertProductXmlItem.create()), xmlFile);

        String xml = Files.readString(Path.of(path));
        assertEquals(xmlFile.toAbsolutePath().toString(), path);
        assertTrue(xml.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"));
        assertTrue(xml.contains("<Product Reference=\"\" NBono=\"0\" Product=\"\" Name=\"\" Database=\"\" Machine=\"\""));
        assertTrue(xml.contains("<Rotation0 value=\"1\"/>"));
        assertTrue(xml.contains("<Fills value=\"0\" hollows=\"0\"/>"));
        assertTrue(xml.contains("<Data DBField=\"OrdRef\" value=\"\"/>"));
        assertTrue(xml.contains("<Data DBField=\"CusName\" value=\"\"/>"));
    }

    @Test
    void supportsChainedProductFieldAssignment() throws Exception {
        ExpertProductXmlItem item = ExpertProductXmlItem.create()
                .reference("P-001")
                .nBono(12)
                .name("Part A")
                .material("Q235")
                .thickness("10")
                .quantity(3)
                .file("D:\\\\parts\\\\p001.dxf")
                .normalRotation90(false)
                .allSymmetricRotations(false)
                .fills(1, 2)
                .ordRef("ORD-1")
                .cusName("ACME");

        String path = new ExpertProductXmlExporter().export(List.of(item), tempDir.resolve("assigned.xml"));
        String xml = Files.readString(Path.of(path));

        assertTrue(xml.contains("Reference=\"P-001\""));
        assertTrue(xml.contains("NBono=\"12\""));
        assertTrue(xml.contains("Name=\"Part A\""));
        assertTrue(xml.contains("Material=\"Q235\""));
        assertTrue(xml.contains("Quantity=\"3\""));
        assertTrue(xml.contains("<Rotation90 value=\"0\"/>"));
        assertTrue(xml.contains("<Symmetrics>"));
        assertTrue(xml.contains("<Fills value=\"1\" hollows=\"2\"/>"));
        assertTrue(xml.contains("<Data DBField=\"OrdRef\" value=\"ORD-1\"/>"));
        assertTrue(xml.contains("<Data DBField=\"CusName\" value=\"ACME\"/>"));
    }
}
