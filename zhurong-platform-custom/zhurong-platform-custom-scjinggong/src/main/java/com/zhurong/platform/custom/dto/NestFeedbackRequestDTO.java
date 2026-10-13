package com.zhurong.platform.custom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/*
 * @Author zhurong
 * @Description NestFeedbackRequest
 * @Date 2026/10/11 12:03
 **/
@Data
@Accessors(chain = true)
public class NestFeedbackRequestDTO {

    //作业编码 Y
    @JsonProperty("jobref")
    private String jobRef;
    //作业名称 Y
    @JsonProperty("jobname")
    private String jobName;
    //作业用户数据1 N
    @JsonProperty("userdata1")
    private String udata1;
    //作业用户数据2 N
    @JsonProperty("userdata2")
    private String udata2;
    //作业用户数据3 N
    @JsonProperty("userdata3")
    private String udata3;
    //作业用户数据4 N
    @JsonProperty("userdata4")
    private String udata4;
    //作业用户数据5 N
    @JsonProperty("userdata5")
    private String udata5;
    //套料编号 Y
    @JsonProperty("nstref")
    private String nstRef;
    //套料号 Y
    @JsonProperty("code")
    private String recId;
    //钢板物料编号 Y
    @JsonProperty("steel_code")
    private String shtRef;
    //钢板材质 Y
    @JsonProperty("steel_material")
    private String matRef;
    //钢板长度 Y
    @JsonProperty("steel_length")
    private Double length;
    //钢板宽度 Y
    @JsonProperty("steel_width")
    private Double width;
    //原始钢板物料编号 N
    @JsonProperty("old_steel_code")
    private String shtRefOrg;
    //钢板单价 Y
    @JsonProperty("steel_price")
    private Double shtPrice;
    //套料数量 Y
    @JsonProperty("steel_qty")
    private Integer quantity;
    //整板利用率（小数） Y
    @JsonProperty("avai")
    private Double sprofit;
    //截取后的利用率（小数） Y
    @JsonProperty("act_avai")
    private Double sprofitS;
    //切割次数 Y
    @JsonProperty("qty")
    private Integer qty;
    //套料图（带后缀）dxf(ftp:)（改为反馈套料图片） Y
    @JsonProperty("cutting_draw")
    private String dxfPath;
    //切割程序（带后缀）nc(ftp:) Y
    @JsonProperty("cutting_program")
    private String ncPath;
    // 预计切割时间（min）(切割移动时间) Y
    @JsonProperty("expect_duration")
    private Double expectDuration;
    // 切割长度 Y
    @JsonProperty("cutting_length")
    private Double cuttingLength;
    // 空程移动时间 Y
    @JsonProperty("rapid_duration")
    private Double rapidDuration;
    // 空程移动长度 Y
    @JsonProperty("rapid_length")
    private Double rapidLength;
    //标记使用时间 Y
    @JsonProperty("marking_duration")
    private Double markingDuration;
    //标记长度 Y
    @JsonProperty("marking_length")
    private Double markingLength;
    //穿孔使用时间 Y
    @JsonProperty("piercing_duration")
    private Double piercingDuration;
    //穿孔次数 Y
    @JsonProperty("piercing_count")
    private Double piercingCount;
    //上下料使用时间 Y
    @JsonProperty("auxiliar_duration")
    private Double auxiliarDuration;
    //总时间 Y
    @JsonProperty("total_duration")
    private Double totalDuration;
    //总时间 Y
    @JsonProperty("cutting_machine")
    private String wrkRef;
    //钢板实际重量 Y
    @JsonProperty("steel_weight")
    private Double weight;
    //废钢（kg）套料计算 Y
    @JsonProperty("scrap_weight")
    private Double scrapWeight;
    //余料
    @JsonProperty("children_surplus")
    private List<Surplus> surplus;
    //零件
    @JsonProperty("children_mat")
    private List<Mat> mats;

    @Data
    public static class Surplus {
        //余料物料编号（新编码）
        @JsonProperty("code")
        private String code;
        //余料长度（mm）Y
        @JsonProperty("length")
        private Double length;
        //余料宽度（mm）
        @JsonProperty("width")
        private Double width;
        //余料厚度（集成钢板厚度）
        @JsonProperty("thick")
        private Double thick;
        //余料重量
        @JsonProperty("weight")
        private Double weight;
    }

    @Data
    public static class Mat {
        //任务编号/工单编号 Y
        @JsonProperty("work_order_code")
        private String code;
        //零件序号（排料图上的数字） N
        @JsonProperty("i_order")
        private Integer iOrder;
        //零件物料编号 Y
        @JsonProperty("mat_code")
        private String prdRef;
        //零件长度（mm） Y
        @JsonProperty("length")
        private Double length;
        //零件宽度（mm） Y
        @JsonProperty("width")
        private Double width;
        //零件厚度（集成钢板厚度）（mm） Y
        @JsonProperty("thick")
        private Double thick;
        //零件重量 Y
        @JsonProperty("weight")
        private Double weight;
        //零件面积 Y
        @JsonProperty("area")
        private Double area;
        //用户数据1 N
        @JsonProperty("userdata1")
        private String udata1;
        //用户数据2 N
        @JsonProperty("userdata2")
        private String udata2;
        //用户数据3 N
        @JsonProperty("userdata3")
        private String udata3;
        //用户数据4 N
        @JsonProperty("userdata4")
        private String udata4;
        //用户数据5 N
        @JsonProperty("userdata5")
        private String udata5;
        //用户数据6 N
        @JsonProperty("userdata6")
        private String udata6;
        //用户数据7 N
        @JsonProperty("userdata7")
        private String udata7;
        //用户数据8 N
        @JsonProperty("userdata8")
        private String udata8;
        //数量 Y
        @JsonProperty("qty")
        private Integer quantity;
        //加工时间 Y
        @JsonProperty("process_duration")
        private String processDuration;
        //带路径带后缀零件图（零件编码命名）ftp: Y
        @JsonProperty("draw")
        private String dxfPath;
    }
}
