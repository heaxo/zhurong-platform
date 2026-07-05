package com.zhurong.platform.custom.service;

import com.zhurong.platform.custom.dto.LabelDataQueryDTO;
import com.zhurong.platform.custom.entity.DisNestNest00000100;
import com.zhurong.platform.custom.vo.LabelDataVO;

import java.util.List;

/**
 * 服务接口
 */
public interface NestService extends BaseIService<DisNestNest00000100> {
    List<LabelDataVO> labelData(LabelDataQueryDTO labelDataQueryDTO);
}
