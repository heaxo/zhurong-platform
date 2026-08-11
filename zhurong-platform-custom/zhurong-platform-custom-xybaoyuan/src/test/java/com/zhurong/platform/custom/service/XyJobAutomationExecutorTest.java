package com.zhurong.platform.custom.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class XyJobAutomationExecutorTest {

    @Test
    void opensExpertVisiblyBeforeCreatingJob() {
        String separator = System.lineSeparator();
        String prc = new XyJobAutomationExecutor().createJobBuilder(
                "C:\\Lantek",
                "100000001",
                "新作业",
                "\\Root\\Folder"
        ).build();

        assertThat(prc).isEqualTo("0 FILEPROLT 8.02" + separator
                + "1 0" + separator
                + "3 1 \"100000001\" \"新作业\" \"\\Root\\Folder\" \"\" \"\" \"\" \"\" \"\" \"\" 0 1");
    }
}
