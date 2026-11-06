package com.zhurong.platform.custom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhurong.platform.custom.entity.RoneindCodeSequence;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface RoneindCodeSequenceMapper extends BaseMapper<RoneindCodeSequence> {
    @Select(value = """
            UPDATE dbo.Zhurong_Roneind_CodeSequence WITH (UPDLOCK, ROWLOCK)
            SET current_value = current_value + increment_by,
                last_allocated_at = SYSDATETIME(),
                updated_at = SYSDATETIME(),
                version = version + 1
            OUTPUT inserted.current_value
            WHERE sequence_key = #{sequenceKey}
              AND is_deleted = 0
            """, affectData = true)
    Long allocateNextValue(@Param("sequenceKey") String sequenceKey);
}
