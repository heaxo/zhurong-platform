package com.zhurong.platform.custom.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.custom.dto.XyRequests;
import com.zhurong.platform.custom.dto.XyInboundRequests;
import com.zhurong.platform.custom.entity.XyBasePart;
import com.zhurong.platform.custom.service.XyDataService;
import com.zhurong.platform.custom.service.XyInboundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/xybaoyuan/base-parts")
public class XyBasePartController {
    private final ObjectMapper objectMapper;
    private final XyDataService dataService;
    private final XyInboundService inboundService;

    @GetMapping
    public ApiResponse<PageResponse<XyBasePart>> page(@ModelAttribute XyRequests.BasePartPage query) {
        return ApiResponse.success(dataService.pageBaseParts(query));
    }

    @PostMapping
    public ApiResponse<XyBasePart> create(@RequestBody XyBasePart item) { return ApiResponse.success(dataService.createBasePart(item)); }

    @PostMapping("/creates")
    public com.zhurong.platform.custom.api.ApiResponse<Boolean> creates(@Valid @RequestBody XyInboundRequests.BaseParts request) {
        logDebugJson("零件档案请求: {}", request);
        return com.zhurong.platform.custom.api.ApiResponse.success(inboundService.receiveBaseParts(request));
    }

    @DeleteMapping
    public ApiResponse<Void> delete(@Valid @RequestBody XyRequests.Ids request) {
        dataService.deleteBaseParts(request.getIds()); return ApiResponse.success();
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> export(@ModelAttribute XyRequests.BasePartPage query) {
        query.setSize(100_000L);
        StringBuilder csv = new StringBuilder("\uFEFF零件编号,图号,材质,厚度,客户参考,客户名称\r\n");
        dataService.pageBaseParts(query).items().forEach(item -> csv.append(row(item.getPrdRef(), item.getPrdName(),
                item.getMatRef(), item.getThickness(), item.getCusRef(), item.getCusName())));
        return csv("xybaoyuan-base-parts.csv", csv);
    }

    static ResponseEntity<byte[]> csv(String fileName, StringBuilder content) {
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(new MediaType("text", "csv", StandardCharsets.UTF_8)).body(content.toString().getBytes(StandardCharsets.UTF_8));
    }

    static String row(Object... values) {
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) row.append(',');
            String value = values[i] == null ? "" : String.valueOf(values[i]);
            row.append('"').append(value.replace("\"", "\"\"")).append('"');
        }
        return row.append("\r\n").toString();
    }
    private void logDebugJson(String message, Object request) {
        if (!log.isDebugEnabled()) return;
        try {
            log.debug(message, objectMapper.writeValueAsString(request));
        } catch (JsonProcessingException exception) {
            log.debug(message, request);
        }
    }
}
