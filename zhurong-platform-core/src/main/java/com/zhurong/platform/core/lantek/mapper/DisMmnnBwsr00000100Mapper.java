package com.zhurong.platform.core.lantek.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhurong.platform.core.lantek.entity.DisMmnnBwsr00000100;
import com.zhurong.platform.core.lantek.vo.DisMmnnBwsr00000100VO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author me
 * @since 2026-04-04
 */
public interface DisMmnnBwsr00000100Mapper extends BaseMapper<DisMmnnBwsr00000100> {
    List<DisMmnnBwsr00000100VO> selectJobTreeList();
}
