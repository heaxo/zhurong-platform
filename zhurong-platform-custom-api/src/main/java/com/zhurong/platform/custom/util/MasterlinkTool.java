package com.zhurong.platform.custom.util;

import com.zhurong.platform.custom.exception.MasterlinkImportException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MasterlinkTool {

    private static final int defaultTimeout = 15;
    private static final TimeUnit defaultTimeUnit = TimeUnit.MINUTES;
    private static final Path LOCK_FILE = Paths.get("C:\\ProgramData\\ZhurongPlatform\\import.lock");

    public static String XMLImporterPath = "System\\Common\\XMLImporter.exe";

    // ===========================
    // 新增：执行方法（生产级）
    // ===========================
    public static ExecResult executeImport(String xmlPath, String... userArgs) {
        return executeImport(xmlPath, defaultTimeout, defaultTimeUnit, userArgs);
    }

    public static ExecResult executeImport(String xmlPath, int timeout, TimeUnit timeUnit) {
        return executeImport(xmlPath, timeout, timeUnit);
    }

    public static ExecResult executeImport(String xmlPath, int timeout, TimeUnit timeUnit, String... userArgs) {

        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            // 确保锁目录存在
            Files.createDirectories(LOCK_FILE.getParent());

            try (RandomAccessFile raf = new RandomAccessFile(LOCK_FILE.toFile(), "rw");
                 FileChannel channel = raf.getChannel()) {

                FileLock lock = null;

                long timeoutMs = 10_000; // 最大等待10秒
                long start = System.currentTimeMillis();

                while (lock == null) {
                    try {
                        lock = channel.tryLock();
                    } catch (Exception e) {
                        // 某些情况下会抛异常（锁被占用）
                    }

                    if (lock != null) {
                        break;
                    }

                    // 超时判断
                    if (System.currentTimeMillis() - start > timeoutMs) {
                        throw new RuntimeException("获取文件锁超时（已有任务在执行）");
                    }

                    // 休眠避免 CPU 空转
                    Thread.sleep(500);
                }

                try {
                    return runProcess(xmlPath, executor, timeout, timeUnit, userArgs);
                } finally {
                    lock.release();
                }

            }

        } catch (Exception e) {
            throw new MasterlinkImportException("Execute XMLImporter failed", e);
        }
    }

    private static String getXMLImporterCommand(String xmlPath, String... userArgs) {
        String installDir = RegistryHelper.getInstallDir();
        String command = installDir + XMLImporterPath + " -src " + StringUtils.quoteText(xmlPath);
        if (userArgs != null && userArgs.length == 2) {
            return String.format("%s -USER %s-PASS %s", command, StringUtils.quoteText(userArgs[0]), StringUtils.quoteText(userArgs[1]));
        }
        return command;
    }

    // ===========================
    // 实际执行 exe
    // ===========================
    private static ExecResult runProcess(String xmlPath,
                                         ExecutorService executor,
                                         int timeout, TimeUnit timeUnit,
                                         String... userArgs) throws Exception {

        String command = getXMLImporterCommand(xmlPath, userArgs);

        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", command);

        pb.redirectErrorStream(false);

        Instant start = Instant.now();

        Process process = pb.start();

        Future<String> stdout = executor.submit(() -> readStream(process.getInputStream()));
        Future<String> stderr = executor.submit(() -> readStream(process.getErrorStream()));

        // 超时控制
        boolean finished = process.waitFor(timeout, timeUnit);

        if (!finished) {
            process.destroyForcibly();
            throw new MasterlinkImportException("XMLImporter timeout");
        }

        int exitCode = process.exitValue();

        String out = getFuture(stdout);
        String err = getFuture(stderr);

        long duration = Duration.between(start, Instant.now()).toMillis();

        return new ExecResult(exitCode, out, err, exitCode == 0, duration);
    }

    // ===========================
    // 读取输出（防死锁）
    // ===========================

    private static String readStream(InputStream is) throws Exception {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("GBK")) // Windows
        )) {
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

    // ===========================
    // 执行结果对象
    // ===========================

    public record ExecResult(
            int exitCode,
            String stdout,
            String stderr,
            boolean success,
            long durationMs
    ) {
    }
}