package com.zhurong.platform.custom.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.custom.dto.ReportQuery;
import com.zhurong.platform.custom.vo.CuttingProgramReportRow;
import com.zhurong.platform.custom.vo.PartReportRow;
import org.apache.ibatis.annotations.Param;

/** 每新增一份报表，只需在此声明一条分页查询，并在同名 XML 中实现 SQL。 */
public interface ScjinggongReportMapper {
    Page<CuttingProgramReportRow> cuttingPrograms(Page<CuttingProgramReportRow> page, @Param("q") ReportQuery query);
    Page<PartReportRow> parts(Page<PartReportRow> page, @Param("q") ReportQuery query);
}
