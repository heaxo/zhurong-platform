package com.zhurong.platform.base.lantek.expert.procesos;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AutomationInstructionBuilder {

    public enum AutomationVersion {
        V42,
        V43,
        V44,
        V45
    }

    private final AutomationInstruction header;
    private final List<AutomationInstruction> instructions = new ArrayList<>();
    private Path lantekPath;
    private Path prcFolder;
    private String expertAutoTaskExePath;

    public AutomationInstructionBuilder() {
        this(AutomationVersion.V43);
    }

    public AutomationInstructionBuilder(AutomationVersion version) {
        this.header = createHead(version);
        this.instructions.add(header);
    }

    public AutomationInstructionBuilder(Path lantekPath, String prcFolder, String expertAutoTaskExePath) {
        this();
        withFileConfig(lantekPath, prcFolder, expertAutoTaskExePath);
    }

    public AutomationInstructionBuilder(AutomationVersion version, Path lantekPath, String prcFolder, String expertAutoTaskExePath) {
        this(version);
        withFileConfig(lantekPath, prcFolder, expertAutoTaskExePath);
    }

    private static AutomationInstruction createHead(AutomationVersion version) {
        if (version == AutomationVersion.V44) {
            return new V44Head();
        }
        if (version == AutomationVersion.V45) {
            return new V45Head();
        }
        return new V43Head();
    }

    public AutomationInstructionBuilder withFileConfig(Path lantekPath, String prcFolder, String expertAutoTaskExePath) {
        this.lantekPath = lantekPath;
        this.prcFolder = Path.of(prcFolder);
        this.expertAutoTaskExePath = expertAutoTaskExePath;
        return this;
    }

    public AutomationInstructionBuilder addInstruction(AutomationInstruction automationInstruction) {
        automationInstruction.setHeader(header);
        instructions.add(automationInstruction);
        return this;
    }

    public String build() {
        return instructions.stream()
                .map(AutomationInstruction::generateInstructionText)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public Path buildPrcPath() throws IOException {
        requireFileConfig();
        Path directory = resolvePrcFolder();
        Files.createDirectories(directory);
        Path prcPath = directory.resolve("AUTO-" + UUID.randomUUID() + ".prc");
        buildPrcPath(prcPath);
        return prcPath;
    }

    public void buildPrcPath(Path prcPath) throws IOException {
        if (prcPath.getParent() != null) {
            Files.createDirectories(prcPath.getParent());
        }
        Files.writeString(prcPath, build(), StandardCharsets.UTF_8);
    }

    public Path buildPrcBat() throws IOException {
        requireFileConfig();
        Path prcPath = buildPrcPath();
        String batchCmd = getAutoNestCmd(lantekPath, expertAutoTaskExePath, prcPath);
        Path batchPath = resolvePrcFolder().resolve("prc-" + UUID.randomUUID() + ".bat");
        Files.writeString(batchPath, batchCmd, StandardCharsets.UTF_8);
        return batchPath;
    }

    public static String getAutoNestCmd(Path lantekPath, String expertAutoTaskExePath, Path prcPath) {
        StringBuilder sb = new StringBuilder("@ECHO OFF\r\n");
        sb.append(":LOOP\r\n");
        sb.append("tasklist | find /i \"procesos.exe\"\r\n");
        sb.append("IF ERRORLEVEL 1 (\r\n");
        sb.append("GOTO CONTINUE\r\n");
        sb.append(") ELSE (\r\n");
        sb.append("ECHO Procesos.exe is still running\r\n");
        sb.append("Timeout /T 5 /Nobreak\r\n");
        sb.append("GOTO LOOP\r\n");
        sb.append(")\r\n");
        sb.append(":CONTINUE\r\n");
        sb.append("START /W ");
        sb.append(getAutoTaskPathWithParam(lantekPath, expertAutoTaskExePath, prcPath));
        sb.append("\r\n");
        sb.append("EXIT");
        return sb.toString();
    }

    private static String getAutoTaskPathWithParam(Path lantekPath, String expertAutoTaskExePath, Path prcPath) {
        return lantekPath.resolve(expertAutoTaskExePath).toString() + " " + quoteText(prcPath.toString());
    }

    public static String quoteText(String value) {
        return "\"" + value + "\" ";
    }

    private Path resolvePrcFolder() {
        return prcFolder.isAbsolute() ? prcFolder : lantekPath.resolve(prcFolder);
    }

    private void requireFileConfig() {
        if (lantekPath == null || prcFolder == null || expertAutoTaskExePath == null || expertAutoTaskExePath.isBlank()) {
            throw new IllegalStateException("Lantek path, PRC folder and Expert AutoTask executable path must be configured.");
        }
    }
}