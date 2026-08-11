package com.zhurong.platform.custom.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.custom.dto.XyRequests;
import com.zhurong.platform.custom.dto.XyInboundRequests;
import com.zhurong.platform.custom.entity.XyBasePart;
import com.zhurong.platform.custom.service.XyDataService;
import com.zhurong.platform.custom.service.XyInboundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/xybaoyuan/base-parts")
public class XyBasePartController {
    private final XyDataService dataService;
    private final XyInboundService inboundService;

    @GetMapping
    public ApiResponse<PageResponse<XyBasePart>> page(@ModelAttribute XyRequests.BasePartPage query) {
        return ApiResponse.success(dataService.pageBaseParts(query));
    }

    @PostMapping
    public ApiResponse<XyBasePart> create(@RequestBody XyBasePart item) { return ApiResponse.success(dataService.createBasePart(item)); }

    @PostMapping("/creates")
    public ApiResponse<Boolean> creates(@Valid @RequestBody XyInboundRequests.BaseParts request) {
        return ApiResponse.success(inboundService.receiveBaseParts(request));
    }

    @DeleteMapping
    public ApiResponse<Void> delete(@Valid @RequestBody XyRequests.Ids request) {
        dataService.deleteBaseParts(request.getIds()); return ApiResponse.success();
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> export(@ModelAttribute XyRequests.BasePartPage query) {
        query.setSize(100_000L);
        StringBuilder csv = new StringBuilder("\uFEFF零件编号,零件名称,图号,材质,厚度,客户参考,客户名称\r\n");
        dataService.pageBaseParts(query).items().forEach(item -> csv.append(row(item.getPrdRef(), item.getPrdName(),
                item.getDrawingCode(), item.getMatRef(), item.getThickness(), item.getCusRef(), item.getCusName())));
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
}
