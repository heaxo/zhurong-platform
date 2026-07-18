package com.zhurong.platform.base.clientimport.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@Schema(description = "Part drawing archive import payload")
public class PartDrawingArchiveRequest implements Serializable {

    @NotBlank(message = "prdRef cannot be blank")
    @Schema(description = "Part reference", example = "PART-001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String prdRef;

    @Schema(description = "Part name", example = "Side plate")
    private String prdName;

    @NotBlank(message = "matRef cannot be blank")
    @Schema(description = "Material reference", example = "Q235", requiredMode = Schema.RequiredMode.REQUIRED)
    private String matRef;

    @NotNull(message = "thickness cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "thickness must be greater than 0")
    @Schema(description = "Thickness", example = "2.5", requiredMode = Schema.RequiredMode.REQUIRED)
    private Float thickness;

    @Schema(description = "Machine reference", example = "LASER-01")
    private String wrkRef;

    @NotBlank(message = "image cannot be blank")
    @Schema(description = "Drawing source path or URL", example = "\\\\server\\share\\drawing.dxf", requiredMode = Schema.RequiredMode.REQUIRED)
    private String image;

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
