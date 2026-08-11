package com.zhurong.platform.custom.dto;

import com.zhurong.platform.base.model.BasePageQuery;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

public final class XyRequests {
    private XyRequests() {}

    @Data
    public static class Ids {
        @NotEmpty private List<Long> ids;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Feedback extends Ids {
        @NotBlank private String productionWorkshopCode;
        private boolean materialReceived = true;
    }

    @Data
    public static class JobCreate {
        @NotBlank private String jobName;
        @NotBlank private String jobPath;
    }

    @Data
    public static class SteelPlateSync {
        private String prdRef;
        private String lotNumber;

        @AssertTrue(message = "物料编号或物料批号不能为空")
        public boolean isQuerySpecified() {
            return StringUtils.hasText(prdRef) || StringUtils.hasText(lotNumber);
        }
    }

    @Data
    public static class OrderUpdate {
        private Long id;
        private String wrkRef;
        private String jobRef;
        private String jobName;
        private Boolean invalidState;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class BasePartPage extends BasePageQuery {
        private String prdRef;
        private String prdName;
        private String drawingCode;
        private String matRef;
        private Double thickness;
        private String udata1;
        private String udata2;
        private String udata3;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class SteelPlatePage extends BasePageQuery {
        private String prdRef;
        private String prdName;
        private String matRef;
        private String stockName;
        private Double thickness;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String lotNumber;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ManufacturingOrderPage extends BasePageQuery {
        private String productionOrderNumber;
        private String productionOrderErpInternalCode;
        private String productionWorkshopName;
        private String drawingCode;
        private String matRef;
        private Double thickness;
        private String prdName;
        private Double quantity;
        private String cusRef;
        private String workCenter;
        private Boolean sendState;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
    }
}
