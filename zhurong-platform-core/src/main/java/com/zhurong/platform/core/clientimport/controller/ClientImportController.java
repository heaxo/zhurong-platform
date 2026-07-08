package com.zhurong.platform.core.clientimport.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.dto.ClientImportRequest;
import com.zhurong.platform.core.clientimport.dto.PartDrawingArchiveRequest;
import com.zhurong.platform.core.clientimport.dto.ProductionOrderRequest;
import com.zhurong.platform.core.clientimport.dto.RawMaterialRequest;
import com.zhurong.platform.core.clientimport.service.ClientImportApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "客户端标准导入", description = "标准业务数据批量接收、图纸文件归档、指定客户端异步派发")
@RestController
@RequestMapping("/client-import")
@ConditionalOnClientCommunicationEnabled
@RequiredArgsConstructor
public class ClientImportController {

    private final ClientImportApplicationService clientImportApplicationService;

    @Operation(
            summary = "批量保存钣金件图纸档案并异步派发",
            description = """
                    接收data数组，core统一校验、归档image文件、保存业务表，并创建一个批次派发任务。
                    MQ下发给客户端的payload也是List，客户端处理器一次拿到本次请求的整批数据。
                    接口只返回是否成功接收，客户端导入结果通过状态消息回传并更新core业务表。
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "请求已成功接收",
                    content = @Content(examples = @ExampleObject(value = """
                            {"code":0,"message":"success","data":true}
                            """)))
    })
    @PostMapping("/part-drawing")
    public ApiResponse<Boolean> partDrawing(
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "钣金件图纸档案批量请求",
                    content = @Content(examples = @ExampleObject(value = """
                            {"targetClientId":"CLIENT-01","data":[{"prdRef":"PART-001","prdName":"侧板","matRef":"Q235","thickness":2.5,"wrkRef":"LASER-01","image":"https://example.com/drawing.dxf","udata1":"客户字段","extensions":{"source":"ERP"}}]}
                            """)))
            ClientImportRequest<List<PartDrawingArchiveRequest>> request) {
        return ApiResponse.success(clientImportApplicationService.importPartDrawing(request));
    }

    @Operation(
            summary = "批量保存生产订单并异步派发",
            description = "接收生产订单data数组，保存成功后创建一个批次任务并异步下发到目标客户端。"
    )
    @PostMapping("/production-order")
    public ApiResponse<Boolean> productionOrder(
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "生产订单批量请求",
                    content = @Content(examples = @ExampleObject(value = """
                            {"targetClientId":"CLIENT-01","data":[{"prdRef":"PART-001","prdName":"侧板","matRef":"Q235","thickness":2.5,"wrkRef":"LASER-01","mnORef":"MO-001","ordRef":"SO-001","cusRef":"CUS-001","quantity":10,"image":"data:application/octet-stream;base64,QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVo=","extensions":{"priority":"normal"}}]}
                            """)))
            ClientImportRequest<List<ProductionOrderRequest>> request) {
        return ApiResponse.success(clientImportApplicationService.importProductionOrder(request));
    }

    @Operation(
            summary = "批量保存原材料并异步派发",
            description = "接收原材料data数组，image有值时按余料处理并归档文件，image为空时按整板处理。"
    )
    @PostMapping("/raw-material")
    public ApiResponse<Boolean> rawMaterial(
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "原材料批量请求",
                    content = @Content(examples = @ExampleObject(value = """
                            {"targetClientId":"CLIENT-01","data":[{"prdRef":"SHEET-001","prdName":"Q235整板","matRef":"Q235","thickness":2.5,"length":3000,"width":1500,"quantity":3,"image":"\\\\\\\\SERVER\\\\Share\\\\remnant.dxf","extensions":{"warehouse":"WH-01"}}]}
                            """)))
            ClientImportRequest<List<RawMaterialRequest>> request) {
        return ApiResponse.success(clientImportApplicationService.importRawMaterial(request));
    }

    @Operation(summary = "重试发布客户端批次任务", description = "按taskId重新下发保存过的批次任务。")
    @PostMapping("/tasks/{taskId}/retry-publish")
    public ApiResponse<Boolean> retryPublish(@PathVariable String taskId) {
        return ApiResponse.success(clientImportApplicationService.retryPublish(taskId));
    }

    @Operation(summary = "发送客户端PING", description = "向指定客户端专属队列发送PING，客户端收到后会上报PONG。")
    @PostMapping("/clients/{clientId}/ping")
    public ApiResponse<Boolean> ping(@PathVariable String clientId) {
        return ApiResponse.success(clientImportApplicationService.ping(clientId));
    }
}
