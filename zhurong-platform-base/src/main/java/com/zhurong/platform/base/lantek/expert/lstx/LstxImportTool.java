package com.zhurong.platform.base.lantek.expert.lstx;

import com.zhurong.platform.base.clientimport.dto.PartDrawingArchiveRequest;
import com.zhurong.platform.base.clientimport.dto.ProductionOrderRequest;

import javax.xml.stream.XMLStreamException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public final class LstxImportTool {

    public static final String LSTX_IMPORT_COMMAND_TEMPLATE = "Expert\\IMPASYSW.EXE {} ";
    public static final String LSTX_IMPORT_EXE_PATH = "Expert\\IMPASYSW.EXE";
    private static final int DEFAULT_TIMEOUT = 7;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MINUTES;
    private static final Charset WINDOWS_CONSOLE_CHARSET = Charset.forName("GBK");

    private LstxImportTool() {
    }

    public static String getLstxImportCommand(String lantekPath, String lstxFilePath, ImportType importDrawType) {
        return getLstxImportCommand(Path.of(lantekPath), Path.of(lstxFilePath), importDrawType);
    }

    public static String getLstxImportCommand(Path lantekPath, Path lstxFilePath, ImportType importDrawType) {
        return lantekPath.resolve(LSTX_IMPORT_EXE_PATH)
                + " "
                + importDrawType.code()
                + " "
                + quoteText(lstxFilePath.toString())
                + emptyQuote()
                + emptyQuote()
                + 1;
    }

    public static ExecResult executeImport(String lantekPath, String lstxFilePath, ImportType importDrawType) {
        return executeImport(Path.of(lantekPath), Path.of(lstxFilePath), importDrawType);
    }

    public static ExecResult executeImport(Path lantekPath, Path lstxFilePath, ImportType importDrawType) {
        return executeImport(lantekPath, lstxFilePath, importDrawType, DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT);
    }

    public static ExecResult executeImport(
            Path lantekPath,
            Path lstxFilePath,
            ImportType importDrawType,
            int timeout,
            TimeUnit timeUnit
    ) {
        Path importExe = lantekPath.resolve(LSTX_IMPORT_EXE_PATH);
        List<String> command = List.of(
                importExe.toString(),
                String.valueOf(importDrawType.code()),
                lstxFilePath.toString(),
                "",
                "",
                "1"
        );
        return runProcess(command, timeout, timeUnit);
    }

    public static ImportExecutionResult exportAndExecute(
            Collection<ExpertProductXmlItem> products,
            Path lantekPath,
            ImportType importDrawType
    ) throws IOException, XMLStreamException {
        return exportAndExecute(products, defaultOutputDirectory(), lantekPath, importDrawType);
    }

    public static ImportExecutionResult exportAndExecute(
            Collection<ExpertProductXmlItem> products,
            Path lantekPath,
            ImportType importDrawType,
            int timeout,
            TimeUnit timeUnit
    ) throws IOException, XMLStreamException {
        return exportAndExecute(products, defaultOutputDirectory(), lantekPath, importDrawType, timeout, timeUnit);
    }

    public static ImportExecutionResult exportAndExecute(
            Collection<ExpertProductXmlItem> products,
            Path outputDirectory,
            Path lantekPath,
            ImportType importDrawType
    ) throws IOException, XMLStreamException {
        return exportAndExecute(products, outputDirectory, lantekPath, importDrawType, DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT);
    }

    public static ImportExecutionResult exportAndExecute(
            Collection<ExpertProductXmlItem> products,
            Path outputDirectory,
            Path lantekPath,
            ImportType importDrawType,
            int timeout,
            TimeUnit timeUnit
    ) throws IOException, XMLStreamException {
        Path directory = outputDirectory == null ? defaultOutputDirectory() : outputDirectory;
        Files.createDirectories(directory);
        Path lstxFile = directory.resolve("lstx-" + UUID.randomUUID() + ".lstx");
        String xmlPath = new ExpertProductXmlExporter().export(products, lstxFile);
        ExecResult execResult = executeImport(lantekPath, Path.of(xmlPath), importDrawType, timeout, timeUnit);
        return new ImportExecutionResult(xmlPath, execResult);
    }

    public static ExpertProductXmlItem toProductXmlItem(PartDrawingArchiveRequest request) {
        return ExpertProductXmlItem.create()
                .reference(request.getPrdRef())
                .name(request.getPrdName())
                .material(request.getMatRef())
                .machine(request.getWrkRef())
                .thickness(request.getThickness())
                .file(request.getImage())
                .userData1(request.getUdata1())
                .userData2(request.getUdata2())
                .userData3(request.getUdata3())
                .userData4(request.getUdata4())
                .userData5(request.getUdata5())
                .userData6(request.getUdata6())
                .userData7(request.getUdata7())
                .userData8(request.getUdata8());
    }

    public static ExpertProductXmlItem toProductXmlItem(ProductionOrderRequest request) {
        return ExpertProductXmlItem.create()
                .reference(request.getPrdRef())
                .name(request.getPrdName())
                .material(request.getMatRef())
                .machine(request.getWrkRef())
                .thickness(request.getThickness())
                .quantity(request.getQuantity())
                .file(request.getImage())
                .ordRef(request.getMnORef())
                .cusRef(request.getCusRef())
                .userData1(request.getUdata1())
                .userData2(request.getUdata2())
                .userData3(request.getUdata3())
                .userData4(request.getUdata4())
                .userData5(request.getUdata5())
                .userData6(request.getUdata6())
                .userData7(request.getUdata7())
                .userData8(request.getUdata8());
    }

    public static List<ExpertProductXmlItem> toProductXmlItems(Collection<? extends PartDrawingArchiveRequest> requests) {
        return requests.stream()
                .map(LstxImportTool::toProductXmlItem)
                .toList();
    }

    public static List<ExpertProductXmlItem> toProductionOrderProductXmlItems(
            Collection<? extends ProductionOrderRequest> requests
    ) {
        return requests.stream()
                .map(LstxImportTool::toProductXmlItem)
                .toList();
    }

    public static ImportType importTypeFromDrawingPath(String drawingPath) {
        if (drawingPath == null || drawingPath.isBlank()) {
            return ImportType.NoDrawing;
        }
        String normalized = drawingPath.toLowerCase(Locale.ROOT);
        int queryIndex = normalized.indexOf('?');
        if (queryIndex >= 0) {
            normalized = normalized.substring(0, queryIndex);
        }
        int fragmentIndex = normalized.indexOf('#');
        if (fragmentIndex >= 0) {
            normalized = normalized.substring(0, fragmentIndex);
        }
        if (normalized.endsWith(".dxf")) {
            return ImportType.DXF;
        }
        if (normalized.endsWith(".dwg")) {
            return ImportType.DWG;
        }
        if (normalized.endsWith(".mec")) {
            return ImportType.MEC;
        }
        throw new IllegalArgumentException("Unsupported LSTX drawing type: " + drawingPath);
    }

    public static Path defaultOutputDirectory() {
        return Path.of("").toAbsolutePath().resolve("lstx");
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
                throw new IllegalStateException("LSTX import timeout.");
            }

            int exitCode = process.exitValue();
            long duration = Duration.between(start, Instant.now()).toMillis();
            return new ExecResult(exitCode, getFuture(stdout), getFuture(stderr), exitCode == 0, duration);
        } catch (Exception e) {
            throw new IllegalStateException("Execute LSTX import failed.", e);
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

    private static String quoteText(String value) {
        return "\"" + value + "\" ";
    }

    private static String emptyQuote() {
        return "\"\" ";
    }

    public enum ImportType {
        NoDrawing(1),
        DXF(7),
        DWG(19),
        MEC(15);

        private final int code;

        ImportType(int code) {
            this.code = code;
        }

        public int code() {
            return code;
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

    public record ImportExecutionResult(
            String xmlPath,
            ExecResult execResult
    ) {
    }
}
