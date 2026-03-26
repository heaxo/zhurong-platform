package com.zhurong.platform.core.lantek.mapper;

import com.zhurong.platform.core.lantek.entity.ZhurongPlatformJobCursor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author heao
 * @since 2026-03-05
 */
public interface ZhurongPlatformJobCursorMapper extends BaseMapper<ZhurongPlatformJobCursor> {
    // 读游标时间
    @Select("SELECT CursorTime FROM AO_PLATFORM_JOB_CURSOR WHERE JobName = #{JobName}")
    LocalDateTime getCursorTime(@Param("JobName") String JobName);

    // 初始化游标（不存在则插入）
    @Insert("""
            INSERT INTO AO_PLATFORM_JOB_CURSOR(JobName, CursorTime, UpdateTime)
            VALUES (#{JobName}, #{CursorTime}, GETDATE())
            """)
    int initCursor(@Param("JobName") String JobName, @Param("CursorTime") LocalDateTime CursorTime);

    // 更新游标
    @Update("""
            UPDATE AO_PLATFORM_JOB_CURSOR
            SET CursorTime = #{CursorTime}, UpdateTime = GETDATE()
            WHERE JobName = #{JobName}
            """)
    int updateCursorTime(@Param("JobName") String JobName, @Param("CursorTime") LocalDateTime CursorTime);

}
