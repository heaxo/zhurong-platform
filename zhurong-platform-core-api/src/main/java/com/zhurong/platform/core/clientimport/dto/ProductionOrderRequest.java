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
@Schema(description = "生产订单标准业务数据")
public class ProductionOrderRequest implements Serializable {

    @Schema(description = "钣金件编码", example = "PART-001")
    private String prdRef;

    @Schema(description = "钣金件名称", example = "侧板")
    private String prdName;

    @Schema(description = "材质", example = "Q235")
    private String matRef;

    @DecimalMin(value = "0.0", inclusive = false, message = "thickness必须大于0")
    @Schema(description = "厚度，大于0", example = "2.5")
    private BigDecimal thickness;

    @Schema(description = "机床，可选", example = "LASER-01")
    private String wrkRef;

    @Schema(description = "图纸来源，可选；传入时core先归档文件，未传表示客户端套料软件中应已存在该图纸", example = "data:application/octet-stream;base64,AAAA")
    private String image;

    @NotBlank(message = "mnORef不能为空")
    @Schema(description = "工单号，必填", example = "MO-001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String mnORef;

    @Schema(description = "订单号，可选", example = "SO-001")
    private String ordRef;

    @Schema(description = "客户号，可选", example = "CUS-001")
    private String cusRef;

    @NotNull(message = "quantity不能为空")
    @DecimalMin(value = "0.0", inclusive = false, message = "quantity必须大于0")
    @Schema(description = "数量，必填且大于0", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal quantity;

    @Schema(description = "客户特殊扩展字段；core只保存JSON快照并透传，不解释客户业务含义")
    private Map<String, Object> extensions;
}
