package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class U8StockTransferOrderRequest {

    /**
     * 主键ID
     */
    private Integer cDefine5;

    /**
     * 调拨单号
     */
    private String cTVCode;

    /**
     * 调拨日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dTVDate;

    /**
     * 库存地点
     */
    private String cOWhCode;

    /**
     * 出库类别
     */
    private String cORdCode;

    /**
     * 入库类别
     */
    private String cIRdCode;

    /**
     * 目的库存地点
     */
    private String cIWhCode;

    /**
     * 表体
     */
    private List<TransVouch> TransVouchss;

    @Data
    public static class TransVouch {

        /**
         * 子表ID
         */
        private String cDefine22;

        /**
         * 存货编码
         */
        private String cInvCode;

        /**
         * 存货名称
         */
        private String cInvName;

        /**
         * 规格型号
         */
        private String cInvStd;

        /**
         * 主计量单位
         */
        private String cAssUnit;

        /**
         * 数量
         */
        private BigDecimal iTVQuantity;
    }
}