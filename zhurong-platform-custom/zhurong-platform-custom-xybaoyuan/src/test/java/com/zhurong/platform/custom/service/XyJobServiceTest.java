package com.zhurong.platform.custom.service;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.lantek.expert.procesos.AutomationInstructionBuilder;
import com.zhurong.platform.custom.feign.DisMmnnBwsr00000100FeignClient;
import com.zhurong.platform.custom.mapper.XyCodeSequenceMapper;
import com.zhurong.platform.custom.properties.XyBaoyuanProperties;
import com.zhurong.platform.core.lantek.vo.JobBrowserTreeVO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class XyJobServiceTest {
    private final DisMmnnBwsr00000100FeignClient browserClient = mock(DisMmnnBwsr00000100FeignClient.class);
    private final XyCodeSequenceMapper sequenceMapper = mock(XyCodeSequenceMapper.class);
    private final XyJobAutomationExecutor automationExecutor = mock(XyJobAutomationExecutor.class);
    private final XyBaoyuanProperties properties = new XyBaoyuanProperties();

    @Test
    void createsFirstNineDigitJobRefInSelectedFolder() throws Exception {
        properties.getLantek().setInstall("C:\\Lantek");
        when(browserClient.getJobBrowserTree()).thenReturn(ApiResponse.success(List.of(folder(
                "1",
                "CAMAssistantTest",
                folder("2", "Level1", folder("3", "Level2"))
        ))));
        when(sequenceMapper.allocateNextValue(XyJobService.JOB_SEQUENCE_KEY)).thenReturn(100_000_001L);
        when(automationExecutor.createJob(
                "C:\\Lantek",
                "100000001",
                "新作业",
                "\\CAMAssistantTest\\Level1\\Level2"
        )).thenReturn(new AutomationInstructionBuilder.ExecResult(0, "", "", true, 10));

        String jobRef = service().create(" 新作业 ", "/CAMAssistantTest/Level1/Level2/");

        assertThat(jobRef).isEqualTo("100000001");
        verify(automationExecutor).createJob(
                "C:\\Lantek",
                "100000001",
                "新作业",
                "\\CAMAssistantTest\\Level1\\Level2"
        );
    }

    @Test
    void rejectsCreationWhenSelectedPathIsNotAnExistingFolder() {
        when(browserClient.getJobBrowserTree()).thenReturn(ApiResponse.success(List.of(folder(
                "1",
                "Root",
                folder("2", "Actual")
        ))));

        assertThatThrownBy(() -> service().create("新作业", "\\Root\\Missing"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("不存在指定作业目录");
    }

    @Test
    void rejectsPrcControlCharacters() {
        assertThatThrownBy(() -> XyJobService.normalizeJobPath("\\Root\n3 1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("不支持的字符");
    }

    private XyJobService service() {
        return new XyJobService(browserClient, properties, sequenceMapper, automationExecutor);
    }

    private static JobBrowserTreeVO folder(String id, String label, JobBrowserTreeVO... children) {
        JobBrowserTreeVO node = new JobBrowserTreeVO();
        node.setId(id);
        node.setLabel(label);
        node.setIsFolder(true);
        node.setChildren(List.of(children));
        return node;
    }
}
