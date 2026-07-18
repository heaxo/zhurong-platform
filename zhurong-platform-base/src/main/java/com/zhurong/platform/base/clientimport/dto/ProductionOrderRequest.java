package com.zhurong.platform.base.clientimport.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@Schema(description = "Production order import payload")
public class ProductionOrderRequest implements Serializable {

    @Schema(description = "Part reference", example = "PART-001")
    private String prdRef;

    @Schema(description = "Part name", example = "Side plate")
    private String prdName;

    @Schema(description = "Material reference", example = "Q235")
    private String matRef;

    @DecimalMin(value = "0.0", inclusive = false, message = "thickness must be greater than 0")
    @Schema(description = "Thickness", example = "2.5")
    private Float thickness;

    @Schema(description = "Machine reference", example = "LASER-01")
    private String wrkRef;

    @Schema(description = "Drawing source path or URL", example = "\\\\server\\share\\drawing.dxf")
    private String image;

    @NotBlank(message = "mnORef cannot be blank")
    @Schema(description = "Manufacturing order reference", example = "MO-001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String mnORef;

    @Schema(description = "Sales order reference for non-LSTX import modes", example = "SO-001")
    private String ordRef;

    @Schema(description = "Customer reference", example = "CUS-001")
    private String cusRef;

    @NotNull(message = "quantity cannot be null")
    @Min(value = 1, message = "quantity must be greater than 0")
    @Schema(description = "Quantity", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quantity;

    private String udata1;
    private String udata2;
    private String udata3;
    private String udata4;
    private String udata5;
    private String udata6;
    private String udata7;
    private String udata8;

    @Schema(description = "Business extension fields")
    private Map<String, Object> extensions;
}
