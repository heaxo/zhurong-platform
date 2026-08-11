package com.zhurong.platform.custom.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.zhurong.platform.base.lantek.expert.procesos.AutomationInstructionBuilder;
import com.zhurong.platform.custom.feign.DisMmnnBwsr00000100FeignClient;
import com.zhurong.platform.custom.mapper.XyCodeSequenceMapper;
import com.zhurong.platform.custom.properties.XyBaoyuanProperties;
import com.zhurong.platform.core.lantek.vo.JobBrowserTreeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@DS("lantek")
@RequiredArgsConstructor
public class XyJobService {
    static final String JOB_SEQUENCE_KEY = "JOB";
    private static final long FIRST_JOB_REF = 100_000_001L;
    private static final long MAX_JOB_REF = 999_999_999L;

    private final DisMmnnBwsr00000100FeignClient browserClient;
    private final XyBaoyuanProperties properties;
    private final XyCodeSequenceMapper codeSequenceMapper;
    private final XyJobAutomationExecutor automationExecutor;

    public boolean exists(String jobName) {
        return findJobByName(jobName, jobTree()).isPresent();
    }

    public String create(String jobName, String jobPath) {
        String normalizedName = normalizeJobName(jobName);
        String normalizedPath = normalizeJobPath(jobPath);
        List<JobBrowserTreeVO> tree = jobTree();
        if (findJobByName(normalizedName, tree).isPresent()) {
            throw new IllegalArgumentException("套料软件中已存在同名作业: " + normalizedName);
        }
        if (!folderExists(normalizedPath, tree)) {
            throw new IllegalArgumentException("套料软件中不存在指定作业目录: " + normalizedPath);
        }

        String install = properties.getLantek().getInstall();
        requireText(install, "未配置Lantek安装目录");
        String jobRef = allocateJobRef();
        try {
            AutomationInstructionBuilder.ExecResult result = automationExecutor.createJob(
                    install,
                    jobRef,
                    normalizedName,
                    normalizedPath
            );
            if (!result.success()) {
                throw new IllegalStateException(errorMessage("作业创建失败", result));
            }
            return jobRef;
        } catch (Exception exception) {
            if (exception instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }
            throw new IllegalStateException("作业创建失败", exception);
        }
    }

    private String allocateJobRef() {
        Long value = codeSequenceMapper.allocateNextValue(JOB_SEQUENCE_KEY);
        if (value == null) {
            throw new IllegalStateException("JOB作业编码序列不存在");
        }
        if (value < FIRST_JOB_REF || value > MAX_JOB_REF) {
            throw new IllegalStateException("JOB作业编码超出九位数范围: " + value);
        }
        return String.format(Locale.ROOT, "%09d", value);
    }

    private List<JobBrowserTreeVO> jobTree() {
        List<JobBrowserTreeVO> nodes = browserClient.getJobBrowserTree().unwrap();
        return nodes == null ? List.of() : nodes;
    }

    private Optional<JobBrowserTreeVO> findJobByName(String jobName, List<JobBrowserTreeVO> tree) {
        if (!StringUtils.hasText(jobName)) {
            return Optional.empty();
        }
        return flatten(tree)
                .filter(node -> !Boolean.TRUE.equals(node.getIsFolder()))
                .filter(node -> jobName.equalsIgnoreCase(node.getLabel()))
                .findFirst();
    }

    private boolean folderExists(String targetPath, List<JobBrowserTreeVO> tree) {
        return folderPaths(tree, "").anyMatch(targetPath::equalsIgnoreCase);
    }

    private Stream<String> folderPaths(List<JobBrowserTreeVO> nodes, String parentPath) {
        if (nodes == null) {
            return Stream.empty();
        }
        return nodes.stream().flatMap(node -> {
            String currentPath = parentPath + "\\" + safePathSegment(node.getLabel());
            Stream<String> current = Boolean.TRUE.equals(node.getIsFolder())
                    ? Stream.of(currentPath)
                    : Stream.empty();
            return Stream.concat(current, folderPaths(node.getChildren(), currentPath));
        });
    }

    private Stream<JobBrowserTreeVO> flatten(List<JobBrowserTreeVO> nodes) {
        if (nodes == null) {
            return Stream.empty();
        }
        return nodes.stream().flatMap(node -> Stream.concat(Stream.of(node), flatten(node.getChildren())));
    }

    static String normalizeJobPath(String value) {
        requireText(value, "新建作业必须选择作业目录");
        rejectPrcControlCharacters(value, "作业目录包含不支持的字符");
        String normalized = value.trim().replace('/', '\\').replaceAll("\\\\+", "\\\\");
        if (!normalized.startsWith("\\")) {
            normalized = "\\" + normalized;
        }
        while (normalized.length() > 1 && normalized.endsWith("\\")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        if (normalized.length() > 1000) {
            throw new IllegalArgumentException("作业目录长度不能超过1000个字符");
        }
        return normalized;
    }

    private static String normalizeJobName(String value) {
        requireText(value, "作业名称不能为空");
        rejectPrcControlCharacters(value, "作业名称包含不支持的字符");
        String normalized = value.trim();
        if (normalized.length() > 255) {
            throw new IllegalArgumentException("作业名称长度不能超过255个字符");
        }
        return normalized;
    }

    private static String safePathSegment(String value) {
        return value == null ? "" : value.trim().replace("/", "\\");
    }

    private static void rejectPrcControlCharacters(String value, String message) {
        if (value.indexOf('"') >= 0 || value.indexOf('\r') >= 0 || value.indexOf('\n') >= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    private static String errorMessage(String prefix, AutomationInstructionBuilder.ExecResult result) {
        String detail = StringUtils.hasText(result.stderr()) ? result.stderr().trim() : result.stdout().trim();
        return StringUtils.hasText(detail) ? prefix + ": " + detail : prefix + "，退出码: " + result.exitCode();
    }

    private static void requireText(String value, String message) {
        if (!StringUtils.hasText(value)) {
            throw new IllegalArgumentException(message);
        }
    }
}
