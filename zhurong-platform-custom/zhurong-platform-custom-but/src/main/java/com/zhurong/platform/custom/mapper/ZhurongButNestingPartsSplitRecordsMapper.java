package com.zhurong.platform.custom.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.core.lantek.dto.MmnnMmoo00000300PageQuery;
import com.zhurong.platform.custom.entity.MmnnMmoo00000300;
import com.zhurong.platform.custom.entity.ZhurongButNestingPartsSplitRecords;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author me
 * @since 2026-04-22
 */
@DS("lantek")
public interface ZhurongButNestingPartsSplitRecordsMapper extends BaseMapper<ZhurongButNestingPartsSplitRecords> {

    Page<MmnnMmoo00000300> selectCustomPage(
            Page<MmnnMmoo00000300> page,
            @Param("req") MmnnMmoo00000300PageQuery req,
            @Param("detachableOrder") Boolean detachableOrder,
            @Param("notStartedMState") Object notStartedMState
    );
}
