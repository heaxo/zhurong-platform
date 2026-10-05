package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*
 * @Author zhurong
 * @Description OrderRequest
 * @Date 2026/10/5 21:37
 **/
@Data
public class OrderRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Valid
    @NotNull(message = "生产订单不能为空")
    @NotEmpty(message = "生产订单不能为空")
    public List<OrderItemRequestDTO> data;

    @NotBlank(message = "作业编码不能为空")
    @JsonProperty("JobRef")
    public String orderCode;
    @NotBlank(message = "作业名称不能为空")
    @JsonProperty("JobName")
    public String orderName;

}
