package com.zhurong.platform.custom.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.custom.entity.XyImportTask;
import com.zhurong.platform.custom.service.XyDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/xybaoyuan/import-tasks")
public class XyImportTaskController {
    private final XyDataService dataService;

    @GetMapping("/{id}")
    public ApiResponse<XyImportTask> get(@PathVariable Long id) {
        XyImportTask task = dataService.taskById(id);
        if (task == null) throw new IllegalArgumentException("导入任务不存在: " + id);
        return ApiResponse.success(task);
    }
}
