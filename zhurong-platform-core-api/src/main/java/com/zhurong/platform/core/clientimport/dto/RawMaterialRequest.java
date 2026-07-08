package com.zhurong.platform.core.clientimport.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@Data
@Schema(description = "原材料标准业务数据")
public class RawMaterialRequest implements Serializable {

    @NotBlank(message = "prdRef不能为空")
    @Schema(description = "板材编码，必填", example = "SHEET-001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String prdRef;

    @Schema(description = "板材名称，可选", example = "Q235整板")
    private String prdName;

    @Schema(description = "材质", example = "Q235")
    private String matRef;

    @DecimalMin(value = "0.0", inclusive = false, message = "thickness必须大于0")
    @Schema(description = "厚度，大于0", example = "2.5")
    private BigDecimal thickness;

    @DecimalMin(value = "0.0", inclusive = false, message = "length必须大于0")
    @Schema(description = "长度，大于0", example = "3000")
    private BigDecimal length;

    @DecimalMin(value = "0.0", inclusive = false, message = "width必须大于0")
    @Schema(description = "宽度，大于0", example = "1500")
    private BigDecimal width;

    @Min(value = 1, message = "quantity必须大于0")
    @Schema(description = "数量，正整数", example = "3")
    private Integer quantity;

    private String udata1;
    private String udata2;
    private String udata3;

    @Schema(description = "文件来源，可选；有值时认为是余料，空值时认为是整板", example = "\\\\SERVER\\Share\\remnant.dxf")
    private String image;

    @Schema(description = "客户特殊扩展字段；core只保存JSON快照并透传，不解释客户业务含义")
    private Map<String, Object> extensions;
}
