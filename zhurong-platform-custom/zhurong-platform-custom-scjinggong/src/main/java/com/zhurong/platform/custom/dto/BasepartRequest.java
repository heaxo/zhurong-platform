package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * DTO
 */
@Data
public class BasepartRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "零件档案不能为空")
    @NotNull(message = "零件档案不能为空")
    @Valid
    @JsonProperty("PartList")
    public List<BasepartRequestDTO> data;

}


