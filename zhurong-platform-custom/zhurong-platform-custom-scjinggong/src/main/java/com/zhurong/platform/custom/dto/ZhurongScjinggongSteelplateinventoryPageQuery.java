package com.zhurong.platform.custom.dto;

import com.zhurong.platform.base.model.BasePageQuery;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 分页查询对象
 */
@Getter
@Setter
public class ZhurongScjinggongSteelplateinventoryPageQuery extends BasePageQuery {


    /**
     *
     */
    private Boolean isDeleted;


    /**
     *
     */
    private Integer version;


    /**
     *
     */
    private Long createdBy;


    /**
     *
     */
    private LocalDateTime createdAt;


    /**
     *
     */
    private Long updatedBy;


    /**
     *
     */
    private LocalDateTime updatedAt;


    /**
     *
     */
    private Boolean isRead;


    /**
     *
     */
    private Boolean isReviewed;


    /**
     *
     */
    private Boolean invalidState;


    /**
     * 钢板编码
     */
    private String shtRef;


    /**
     * 材质
     */
    private String matRef;


    /**
     * 厚度
     */
    private Float thickness;


    /**
     * 长度
     */
    private Float length;


    /**
     * 宽度
     */
    private Float width;


    /**
     * 数量
     */
    private Integer quantity;


    /**
     *
     */
    private String udata1;


    /**
     *
     */
    private String udata2;


    /**
     *
     */
    private String udata3;


    /**
     *
     */
    private String udata4;


    /**
     *
     */
    private String udata5;


    /**
     *
     */
    private String udata6;


    /**
     *
     */
    private String udata7;


    /**
     *
     */
    private String udata8;


    /**
     *
     */
    private String udata9;


    /**
     *
     */
    private String udata10;


    /**
     *
     */
    private String udata11;


    /**
     *
     */
    private String udata12;


    /**
     *
     */
    private String udata13;


    /**
     *
     */
    private String udata14;


    /**
     *
     */
    private String udata15;


    /**
     *
     */
    private String udata16;


    /**
     *
     */
    private String udata17;


    /**
     *
     */
    private String udata18;


    /**
     *
     */
    private String udata19;


    /**
     *
     */
    private String udata20;

    /**
     * 创建时间开始
     */
    private LocalDateTime beginCreateTime;

    /**
     * 创建时间结束
     */
    private LocalDateTime endCreateTime;
}
