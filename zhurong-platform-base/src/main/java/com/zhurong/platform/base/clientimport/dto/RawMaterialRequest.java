package com.zhurong.platform.base.clientimport.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@Schema(description = "Raw material import payload")
public class RawMaterialRequest implements Serializable {

    @NotBlank(message = "prdRef cannot be blank")
    @Schema(description = "Sheet or remnant reference", example = "SHEET-001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String prdRef;

    @Schema(description = "Sheet or remnant name", example = "Q235 sheet")
    private String prdName;

    @Schema(description = "Material reference", example = "Q235")
    private String matRef;

    @DecimalMin(value = "0.0", inclusive = false, message = "thickness must be greater than 0")
    @Schema(description = "Thickness", example = "2.5")
    private Float thickness;

    @DecimalMin(value = "0.0", inclusive = false, message = "length must be greater than 0")
    @Schema(description = "Length", example = "3000")
    private Float length;

    @DecimalMin(value = "0.0", inclusive = false, message = "width must be greater than 0")
    @Schema(description = "Width", example = "1500")
    private Float width;

    @Min(value = 1, message = "quantity must be greater than 0")
    @Schema(description = "Quantity", example = "3")
    private Integer quantity;

    private String udata1;
    private String udata2;
    private String udata3;

    @Schema(description = "Remnant DXF path. Blank means whole sheet.", example = "\\\\SERVER\\Share\\remnant.dxf")
    private String image;

    @Schema(description = "Business extension fields")
    private Map<String, Object> extensions;
}
