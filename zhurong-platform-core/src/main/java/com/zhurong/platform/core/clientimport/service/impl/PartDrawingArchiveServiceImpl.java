package com.zhurong.platform.core.clientimport.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.core.clientimport.configuration.ConditionalOnClientCommunicationEnabled;
import com.zhurong.platform.core.clientimport.entity.PartDrawingArchive;
import com.zhurong.platform.core.clientimport.mapper.PartDrawingArchiveMapper;
import com.zhurong.platform.core.clientimport.service.PartDrawingArchiveService;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnClientCommunicationEnabled
public class PartDrawingArchiveServiceImpl
        extends ServiceImpl<PartDrawingArchiveMapper, PartDrawingArchive>
        implements PartDrawingArchiveService {
}
