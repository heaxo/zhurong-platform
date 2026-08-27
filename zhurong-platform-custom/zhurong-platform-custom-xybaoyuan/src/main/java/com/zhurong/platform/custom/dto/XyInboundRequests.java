package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

public final class XyInboundRequests {
    private XyInboundRequests() {}

    @Data
    public static class BaseParts {
        @NotEmpty(message = "零件基础信息不能为空")
        @JsonProperty("Parts")
        @JsonAlias({"parts", "PARTS", "PartList", "partList"})
        private List<@Valid BasePart> parts;
    }

    @Data
    public static class BasePart {
        @NotBlank(message = "零件编码不能为空")
        private String prdRef;
        @NotBlank(message = "零件名称不能为空")
        private String prdName;
        @NotBlank(message = "零件图号不能为空")
        private String drawingCode;
        @NotBlank(message = "材质不能为空")
        private String matRef;
        @NotNull(message = "厚度不能为空")
        @DecimalMin(value = "0", inclusive = false, message = "板厚不能为0")
        private Double thickness;
        private String udata1;
        private String udata2;
        @NotBlank(message = "ERP物料内码不能为空")
        private String udata3;
    }

    @Data
    public static class ManufacturingOrders {
        @NotEmpty(message = "生产订单信息不能为空")
        @JsonProperty("ProductionOrders")
        @JsonAlias({"productionOrders", "PRODUCTION_ORDERS"})
        private List<@Valid ManufacturingOrder> productionOrders;
    }
    @Data
    public static class ManufacturingOrderDeletes {
        @NotEmpty(message = "ERP内码不能为空")
        @JsonProperty("ProductionOrderERPInternalCodes")
        @JsonAlias({"productionOrderERPInternalCodes", "PRODUCTION_ORDER_ERP_INTERNAL_CODES"})
        private List<@Valid String> productionOrderERPInternalCodes;
    }

    @Data
    public static class ManufacturingOrder {
        @NotBlank(message = "生产订单号不能为空")
        private String productionOrderNumber;
        @JsonProperty("productionOrderLineID")
        private String productionOrderLineId;
        @JsonProperty("productionOrderERPInternalCode")
        @NotBlank(message = "生产订单ERP内码不能为空")
        private String productionOrderErpInternalCode;
        @NotBlank(message = "零件编码不能为空")
        private String prdRef;
        @NotBlank(message = "零件名称不能为空")
        private String prdName;
        @NotNull(message = "工单数量不能为空")
        @DecimalMin(value = "0", inclusive = false, message = "工单数量不能为0")
        private Double quantity;
        @NotBlank(message = "材质不能为空")
        private String matRef;
        @NotNull(message = "交付日期不能为空")
        @JsonProperty("ddate")
        private LocalDateTime deliveryDate;
        @NotNull(message = "厚度不能为空")
        @DecimalMin(value = "0", inclusive = false, message = "板厚不能为0")
        private Double thickness;
        private String rouRef;
        @NotBlank(message = "跟踪号不能为空")
        private String cusRef;
        private String cusName;
        @NotBlank(message = "工序次数不能为空")
        private String udata1;
        @NotBlank(message = "加工时长不能为空")
        private String udata2;
        private String workCenter;
        @NotBlank(message = "生产车间编码不能为空")
        private String productionWorkshopCode;
        @NotBlank(message = "生产车间名称不能为空")
        private String productionWorkshopName;
    }
}
