package com.zhurong.platform.custom.service;

import com.zhurong.platform.base.lantek.expert.procesos.AutomationInstructionBuilder;
import com.zhurong.platform.base.lantek.expert.procesos.CreateJob;
import com.zhurong.platform.base.lantek.expert.procesos.OpenExpert;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class XyJobAutomationExecutor {
    public AutomationInstructionBuilder.ExecResult createJob(
            String lantekInstall,
            String jobRef,
            String jobName,
            String jobPath
    ) throws IOException {
        return createJobBuilder(lantekInstall, jobRef, jobName, jobPath).execute();
    }

    AutomationInstructionBuilder createJobBuilder(
            String lantekInstall,
            String jobRef,
            String jobName,
            String jobPath
    ) {
        return new AutomationInstructionBuilder(
                AutomationInstructionBuilder.AutomationVersion.V45,
                lantekInstall
        ).withPrcEncoding(AutomationInstructionBuilder.PrcEncoding.ANSI)
                .addInstruction(new OpenExpert(false))
                .addInstruction(
                        new CreateJob(jobName, false, jobRef)
                                .setJobPath(jobPath)
                                .setPromptErrorIfTheJobRefAlreadyExists(true)
                );
    }
}
