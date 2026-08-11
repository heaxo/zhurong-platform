package com.zhurong.platform.base.lantek.expert;

import com.zhurong.platform.base.lantek.expert.procesos.*;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AutomationInstructionBuilderTest {

    @Test
    void buildsInstructionsWithDefaultV43Header() {
        String result = new AutomationInstructionBuilder()
                .addInstruction(new OpenExpert(true))
                .addInstruction(new CreateJob("JOB-001", false, "J001").setJobPath("\\folder"))
                .addInstruction(new CloseExpert())
                .build();

        String separator = System.lineSeparator();
        assertEquals("0 FILEPROLT 8.02" + separator
                + "1 1" + separator
                + "3 1 \"J001\" \"JOB-001\" \"\\folder\" \"\" \"\" \"\" \"\" \"\" \"\" 0 0" + separator
                + "2", result);
    }

    @Test
    void openNestIncludesNestReferenceOnlyForV45Header() {
        String v43 = new AutomationInstructionBuilder()
                .addInstruction(new OpenNest(false, "NST-001"))
                .build();
        String v45 = new AutomationInstructionBuilder(AutomationInstructionBuilder.AutomationVersion.V45)
                .addInstruction(new OpenNest(false, "NST-001"))
                .build();

        assertTrue(v43.endsWith("23 0"));
        assertTrue(v45.endsWith("23 0 \"NST-001\""));
    }

    @Test
    void allNestingSupportsSheetListParameters() {
        AllNesting instruction = new AllNesting()
                .useAutomaticProcessingSheet()
                .useSheetList("boards.lst")
                .setHowUseNewSheet(UseSheetListParams.HowUseNewSheet.Delete)
                .reNestingAllUseSameSheetList()
                .build();

        assertEquals("24 1 0 \"boards.lst\" 4 1", instruction.generateInstructionText());
    }

    @Test
    void importInstructionValidatesFileExtensionAndPreservesSilentFormat() {
        assertEquals("101 2 \"parts.xlsx\" \"ExcelImp.cfg\"",
                new ImportPartsFromDatabase(true, "parts.xlsx").generateInstructionText());
        assertEquals("101 11 \"parts.lst\"",
                new ImportPartsFromDatabase(false, "parts.lst").setSilent(true).generateInstructionText());
        assertThrows(IllegalArgumentException.class, () -> new ImportDxf(true, "parts.lst"));
    }

    @Test
    void removeDatabaseElementsRequiresPathInAutomaticMode() {
        assertThrows(IllegalArgumentException.class,
                () -> new RemoveDatabaseElements(AutomaticMode.Enable, RemoveDatabaseElements.Argument.Job));

        RemoveDatabaseElements instruction = new RemoveDatabaseElements(
                AutomaticMode.Enable, RemoveDatabaseElements.Argument.Part, "delete.lst")
                .setDeleteRelatedElements(true)
                .setOnlyParameterizedElementsAreRemoved(true);

        assertEquals("44 1 3 1 1 \"delete.lst\"", instruction.generateInstructionText());
    }

    @Test
    void saveAllAsSheetsSupportsBatchAndPredefinedTargets() {
        assertEquals("46 4 \"B001\"", new SaveAllAsSheets(true, "B001").generateInstructionText());
        assertEquals("46 2 \"\"", new SaveAllAsSheets(SaveAllAsSheets.Params.Workshop).generateInstructionText());
        assertThrows(IllegalArgumentException.class, () -> new SaveAllAsSheets(true));
    }

    @Test
    void buildsPrcUnderDefaultWorkingDirectoryFolder() throws Exception {
        Path prcPath = new AutomationInstructionBuilder(
                AutomationInstructionBuilder.AutomationVersion.V45,
                Path.of("D:\\Lantek")
        ).addInstruction(new CreateAndUpdateBoard("boards.lst")).buildPrcPath();

        try {
            assertTrue(prcPath.startsWith(AutomationInstructionBuilder.defaultPrcFolder()));
            assertTrue(Files.exists(prcPath));
            assertTrue(Files.readString(prcPath, StandardCharsets.UTF_8).contains("39 1 \"boards.lst\""));
        } finally {
            Files.deleteIfExists(prcPath);
        }
    }

    @Test
    void writesPrcAsAnsiByDefaultAndAllowsBusinessToChooseUtf8() throws Exception {
        Path ansiPath = Files.createTempFile("prc-ansi-", ".prc");
        Path utf8Path = Files.createTempFile("prc-utf8-", ".prc");
        try {
            AutomationInstructionBuilder ansiBuilder = new AutomationInstructionBuilder()
                    .addInstruction(new CreateJob("中文作业", false, "100000001"));
            ansiBuilder.buildPrcPath(ansiPath);

            AutomationInstructionBuilder utf8Builder = new AutomationInstructionBuilder()
                    .withPrcEncoding(AutomationInstructionBuilder.PrcEncoding.UTF8)
                    .addInstruction(new CreateJob("中文作业", false, "100000001"));
            utf8Builder.buildPrcPath(utf8Path);

            assertArrayEquals(
                    ansiBuilder.build().getBytes(Charset.forName("GBK")),
                    Files.readAllBytes(ansiPath)
            );
            assertArrayEquals(
                    utf8Builder.build().getBytes(StandardCharsets.UTF_8),
                    Files.readAllBytes(utf8Path)
            );
        } finally {
            Files.deleteIfExists(ansiPath);
            Files.deleteIfExists(utf8Path);
        }
    }
}
