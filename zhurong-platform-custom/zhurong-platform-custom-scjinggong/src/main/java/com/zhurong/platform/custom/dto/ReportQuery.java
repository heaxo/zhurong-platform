package com.zhurong.platform.custom.dto;

import com.zhurong.platform.base.model.BasePageQuery;
import lombok.Getter;
import lombok.Setter;

/**
 * 报表共用的可选查询条件。新增报表时按需增加字段，并在对应 mapper SQL 中绑定参数。
 * 前端的表单和列仍由各自的 Vue 组件定义，不从后端动态生成。
 */
@Getter
@Setter
public class ReportQuery extends BasePageQuery {
    private String jobRef;
    private String cnc;
    private String matRef;
    private String prdRef;
    private String customerPartNo;
    private String ordRef;
    private String process;
}
