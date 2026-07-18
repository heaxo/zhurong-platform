package com.zhurong.platform.base.lantek.expert.procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AutomationInstructionBuilder {

    public enum AutomationVersion {
        V42,
        V43,
        V44,
        V45
    }

    private static final int DEFAULT_TIMEOUT = 15;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MINUTES;
    private static final Charset WINDOWS_CONSOLE_CHARSET = Charset.forName("GBK");
    private static final Path DEFAULT_PRC_FOLDER = Path.of("").toAbsolutePath().resolve("prc");

    private final AutomationInstruction header;
    private final List<AutomationInstruction> instructions = new ArrayList<>();
    private Path lantekPath;
    private Path prcFolder;
    private String expertAutoTaskExePath = "Expert\\Procesos.exe";

    public AutomationInstructionBuilder() {
        this(AutomationVersion.V43);
    }

    public AutomationInstructionBuilder(AutomationVersion version) {
        this.header = createHead(version);
        this.instructions.add(header);
    }

    public AutomationInstructionBuilder(String lantekPath) {
        this(Path.of(lantekPath));
    }

    public AutomationInstructionBuilder(Path lantekPath) {
        this();
        withFileConfig(lantekPath, DEFAULT_PRC_FOLDER, expertAutoTaskExePath);
    }

    public AutomationInstructionBuilder(AutomationVersion version, String lantekPath) {
        this(version, Path.of(lantekPath));
    }

    public AutomationInstructionBuilder(AutomationVersion version, Path lantekPath) {
        this(version);
        withFileConfig(lantekPath, DEFAULT_PRC_FOLDER, expertAutoTaskExePath);
    }

    public AutomationInstructionBuilder(Path lantekPath, String prcFolder) {
        this();
        withFileConfig(lantekPath, prcFolder, expertAutoTaskExePath);
    }

    public AutomationInstructionBuilder(Path lantekPath, String prcFolder, String expertAutoTaskExePath) {
        this();
        withFileConfig(lantekPath, prcFolder, expertAutoTaskExePath);
    }

    public AutomationInstructionBuilder(AutomationVersion version, Path lantekPath, String prcFolder) {
        this(version);
        withFileConfig(lantekPath, prcFolder, expertAutoTaskExePath);
    }

    public AutomationInstructionBuilder(
            AutomationVersion version,
            Path lantekPath,
            String prcFolder,
            String expertAutoTaskExePath
    ) {
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

    public AutomationInstructionBuilder withFileConfig(Path lantekPath, Path prcFolder, String expertAutoTaskExePath) {
        this.lantekPath = lantekPath;
        this.prcFolder = prcFolder;
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

    public ExecResult execute() throws IOException {
        return execute(buildPrcPath(), DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT);
    }

    public ExecResult execute(int timeout, TimeUnit timeUnit) throws IOException {
        return execute(buildPrcPath(), timeout, timeUnit);
    }

    public ExecResult execute(Path prcPath) {
        return execute(prcPath, DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT);
    }

    public ExecResult execute(Path prcPath, int timeout, TimeUnit timeUnit) {
        requireFileConfig();
        Path exePath = lantekPath.resolve(expertAutoTaskExePath);
        return runProcess(List.of(exePath.toString(), prcPath.toString()), timeout, timeUnit);
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

    public static Path defaultPrcFolder() {
        return DEFAULT_PRC_FOLDER;
    }

    private Path resolvePrcFolder() {
        return prcFolder.isAbsolute() ? prcFolder : lantekPath.resolve(prcFolder);
    }

    private void requireFileConfig() {
        if (lantekPath == null || prcFolder == null || expertAutoTaskExePath == null || expertAutoTaskExePath.isBlank()) {
            throw new IllegalStateException("Lantek path, PRC folder and Expert AutoTask executable path must be configured.");
        }
    }

    private static ExecResult runProcess(List<String> command, int timeout, TimeUnit timeUnit) {
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(false);

            Instant start = Instant.now();
            Process process = pb.start();

            Future<String> stdout = executor.submit(() -> readStream(process.getInputStream()));
            Future<String> stderr = executor.submit(() -> readStream(process.getErrorStream()));

            boolean finished = process.waitFor(timeout, timeUnit);
            if (!finished) {
                process.destroyForcibly();
                throw new IllegalStateException("Expert PRC execution timeout.");
            }

            int exitCode = process.exitValue();
            long duration = Duration.between(start, Instant.now()).toMillis();
            return new ExecResult(exitCode, getFuture(stdout), getFuture(stderr), exitCode == 0, duration);
        } catch (Exception e) {
            throw new IllegalStateException("Execute Expert PRC failed.", e);
        }
    }

    private static String readStream(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, WINDOWS_CONSOLE_CHARSET))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    private static String getFuture(Future<String> future) {
        try {
            return future.get(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            return "";
        }
    }

    public record ExecResult(
            int exitCode,
            String stdout,
            String stderr,
            boolean success,
            long durationMs
    ) {
    }
}
