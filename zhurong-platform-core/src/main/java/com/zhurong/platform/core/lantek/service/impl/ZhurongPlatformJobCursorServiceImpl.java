package com.zhurong.platform.core.lantek.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.core.lantek.convert.ZhurongPlatformJobCursorConvert;
import com.zhurong.platform.core.lantek.dto.ZhurongPlatformJobCursorDTO;
import com.zhurong.platform.core.lantek.entity.ZhurongPlatformJobCursor;
import com.zhurong.platform.core.lantek.mapper.ZhurongPlatformJobCursorMapper;
import com.zhurong.platform.core.lantek.service.IZhurongPlatformJobCursorService;
import com.zhurong.platform.core.lantek.vo.ZhurongPlatformJobCursorVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class ZhurongPlatformJobCursorServiceImpl
        extends ServiceImpl<ZhurongPlatformJobCursorMapper, ZhurongPlatformJobCursor>
        implements IZhurongPlatformJobCursorService {

    private final ZhurongPlatformJobCursorConvert convert;


    @Override
    public ZhurongPlatformJobCursorVO getVOById(Long id) {
        ZhurongPlatformJobCursor entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(ZhurongPlatformJobCursorDTO dto) {
        return -1L;
    }

    @Override
    public Boolean updateFromDTO(Long id, ZhurongPlatformJobCursorDTO dto) {
        ZhurongPlatformJobCursor entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
