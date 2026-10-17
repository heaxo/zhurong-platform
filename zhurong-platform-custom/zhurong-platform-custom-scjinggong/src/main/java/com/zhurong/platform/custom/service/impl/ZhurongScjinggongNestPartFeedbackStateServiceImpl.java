package com.zhurong.platform.custom.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.entity.ZhurongScjinggongNestPartFeedbackState;
import com.zhurong.platform.custom.mapper.ZhurongScjinggongNestPartFeedbackStateMapper;
import com.zhurong.platform.custom.service.IZhurongScjinggongNestPartFeedbackStateService;
import org.springframework.stereotype.Service;

/**
 * NEST500 套料零件反馈状态服务实现。
 */
@Service
@DS("lantek")
public class ZhurongScjinggongNestPartFeedbackStateServiceImpl
        extends ServiceImpl<ZhurongScjinggongNestPartFeedbackStateMapper, ZhurongScjinggongNestPartFeedbackState>
        implements IZhurongScjinggongNestPartFeedbackStateService {
}
