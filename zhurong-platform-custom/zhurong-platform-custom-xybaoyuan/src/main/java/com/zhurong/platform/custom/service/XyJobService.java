package com.zhurong.platform.custom.service;

import com.zhurong.platform.base.lantek.expert.procesos.AutomationInstructionBuilder;
import com.zhurong.platform.base.lantek.expert.procesos.CreateJob;
import com.zhurong.platform.custom.feign.DisMmnnBwsr00000100FeignClient;
import com.zhurong.platform.custom.properties.XyBaoyuanProperties;
import com.zhurong.platform.core.lantek.vo.JobBrowserTreeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class XyJobService {
    private static final DateTimeFormatter JOB_REF_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    private final DisMmnnBwsr00000100FeignClient browserClient;
    private final XyBaoyuanProperties properties;

    public boolean exists(String jobName) { return find(jobName).isPresent(); }

    public String resolveOrCreate(String jobName, boolean useOldJob) {
        requireText(jobName, "作业名称不能为空");
        Optional<JobBrowserTreeVO> existing = find(jobName);
        if (useOldJob) return existing.orElseThrow(() -> new IllegalArgumentException("套料软件中不存在作业: " + jobName)).getId();
        if (existing.isPresent()) throw new IllegalArgumentException("套料软件中已存在同名作业: " + jobName);
        String install = properties.getLantek().getInstall();
        requireText(install, "未配置Lantek安装目录");
        String jobRef = "GRJOB" + LocalDateTime.now().format(JOB_REF_FORMAT);
        try {
            AutomationInstructionBuilder.ExecResult result = new AutomationInstructionBuilder(
                    AutomationInstructionBuilder.AutomationVersion.V45, install)
                    .addInstruction(new CreateJob(jobName, false, jobRef)).execute();
            if (!result.success()) throw new IllegalStateException("作业创建失败: " + result.stderr());
            return jobRef;
        } catch (Exception exception) {
            if (exception instanceof RuntimeException runtimeException) throw runtimeException;
            throw new IllegalStateException("作业创建失败", exception);
        }
    }

    private Optional<JobBrowserTreeVO> find(String jobName) {
        if (!StringUtils.hasText(jobName)) return Optional.empty();
        List<JobBrowserTreeVO> roots = browserClient.getJobBrowserTree().unwrap();
        return flatten(roots).filter(node -> !Boolean.TRUE.equals(node.getIsFolder()))
                .filter(node -> jobName.equalsIgnoreCase(node.getLabel())).findFirst();
    }

    private Stream<JobBrowserTreeVO> flatten(List<JobBrowserTreeVO> nodes) {
        if (nodes == null) return Stream.empty();
        return nodes.stream().flatMap(node -> Stream.concat(Stream.of(node), flatten(node.getChildren())));
    }

    private static void requireText(String value, String message) {
        if (!StringUtils.hasText(value)) throw new IllegalArgumentException(message);
    }
}
