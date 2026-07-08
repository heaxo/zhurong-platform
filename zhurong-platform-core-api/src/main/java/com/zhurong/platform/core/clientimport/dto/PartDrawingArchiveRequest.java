package com.zhurong.platform.core.clientimport.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@Data
@Schema(description = "钣金件图纸档案标准业务数据")
public class PartDrawingArchiveRequest implements Serializable {

    @NotBlank(message = "prdRef不能为空")
    @Schema(description = "钣金件编码，必填", example = "PART-001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String prdRef;

    @Schema(description = "钣金件名称，可选", example = "侧板")
    private String prdName;

    @NotBlank(message = "matRef不能为空")
    @Schema(description = "材质，必填", example = "Q235", requiredMode = Schema.RequiredMode.REQUIRED)
    private String matRef;

    @NotNull(message = "thickness不能为空")
    @DecimalMin(value = "0.0", inclusive = false, message = "thickness必须大于0")
    @Schema(description = "厚度，必填且大于0", example = "2.5", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal thickness;

    @Schema(description = "机床，可选；为空时后续可以由默认配置或custom处理", example = "LASER-01")
    private String wrkRef;

    @NotBlank(message = "image不能为空")
    @Schema(description = "图纸来源，支持HTTP、HTTPS、FTP、FTPS、Windows共享路径以及Base64", example = "https://example.com/drawing.dxf", requiredMode = Schema.RequiredMode.REQUIRED)
    private String image;

    private String udata1;
    private String udata2;
    private String udata3;
    private String udata4;
    private String udata5;
    private String udata6;
    private String udata7;
    private String udata8;

    @Schema(description = "客户特殊扩展字段；core只保存JSON快照并透传，不解释客户业务含义")
    private Map<String, Object> extensions;
}
