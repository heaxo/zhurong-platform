package com.zhurong.platform.custom.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
*  DTO
*/
@Data
public class ZhurongButNestingPartsSplitRecordsCreateDTO implements Serializable {

    @NotBlank(message = "套料编码不能为空")
    private String nstRef;
    @NotBlank(message = "工单号不能为空")
    private String mnoRef;
    @NotNull(message = "工序ID不能为空")
    private Integer oprId;
    @NotNull(message = "数量不能为空")
    private Integer quantity;
    private String remark;
    @NotBlank(message = "订单号不能为空")
    private String ordRef;
    @NotNull(message = "套料零件ID不能为空")
    private Integer recId;

}
