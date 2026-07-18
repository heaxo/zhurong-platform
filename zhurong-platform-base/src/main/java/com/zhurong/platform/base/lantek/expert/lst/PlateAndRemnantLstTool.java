package com.zhurong.platform.base.lantek.expert.lst;

import com.zhurong.platform.base.clientimport.dto.RawMaterialRequest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

public final class PlateAndRemnantLstTool {

    private PlateAndRemnantLstTool() {
    }

    public static String export(Collection<PlateAndRemnantLstItem> items) throws IOException {
        return export(items, defaultOutputDirectory());
    }

    public static String export(Iterable<PlateAndRemnantLstItem> items, Path outputDirectory) throws IOException {
        Path directory = outputDirectory == null ? defaultOutputDirectory() : outputDirectory;
        Files.createDirectories(directory);
        Path lstFile = directory.resolve("board-" + UUID.randomUUID() + ".lst");
        return exportToFile(items, lstFile);
    }

    public static String exportRawMaterials(Collection<? extends RawMaterialRequest> requests) throws IOException {
        return exportRawMaterials(requests, defaultOutputDirectory());
    }

    public static String exportRawMaterials(Iterable<? extends RawMaterialRequest> requests) throws IOException {
        return exportRawMaterials(requests, defaultOutputDirectory());
    }

    public static String exportRawMaterials(
            Iterable<? extends RawMaterialRequest> requests,
            Path outputDirectory
    ) throws IOException {
        Path directory = outputDirectory == null ? defaultOutputDirectory() : outputDirectory;
        Files.createDirectories(directory);
        Path lstFile = directory.resolve("board-" + UUID.randomUUID() + ".lst");
        if (lstFile.getParent() != null) {
            Files.createDirectories(lstFile.getParent());
        }
        try (BufferedWriter writer = Files.newBufferedWriter(lstFile, StandardCharsets.UTF_8)) {
            Objects.requireNonNull(requests, "raw material requests cannot be null");
            for (RawMaterialRequest request : requests) {
                writer.write(PlateAndRemnantLstItem.fromRawMaterialRequest(request).toLstLine());
                writer.newLine();
            }
        }
        return lstFile.toAbsolutePath().toString();
    }

    public static String exportToFile(Iterable<PlateAndRemnantLstItem> items, Path lstFile) throws IOException {
        if (lstFile.getParent() != null) {
            Files.createDirectories(lstFile.getParent());
        }
        try (BufferedWriter writer = Files.newBufferedWriter(lstFile, StandardCharsets.UTF_8)) {
            Objects.requireNonNull(items, "LST items cannot be null");
            for (PlateAndRemnantLstItem item : items) {
                writer.write(item.toLstLine());
                writer.newLine();
            }
        }
        return lstFile.toAbsolutePath().toString();
    }

    public static Path defaultOutputDirectory() {
        return Path.of("").toAbsolutePath().resolve("lst");
    }
}
