package com.zhurong.platform.custom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.convert.ZhurongScjinggongOrderitemConvert;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderitemDTO;
import com.zhurong.platform.custom.entity.ZhurongScjinggongOrderitem;
import com.zhurong.platform.custom.mapper.ZhurongScjinggongOrderitemMapper;
import com.zhurong.platform.custom.service.IZhurongScjinggongOrderitemService;
import com.zhurong.platform.custom.vo.ZhurongScjinggongOrderitemVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
*  服务实现类
*/
@Service
@RequiredArgsConstructor
public class ZhurongScjinggongOrderitemServiceImpl
extends ServiceImpl<ZhurongScjinggongOrderitemMapper, ZhurongScjinggongOrderitem>
implements IZhurongScjinggongOrderitemService {

private final ZhurongScjinggongOrderitemConvert convert;


@Override
public ZhurongScjinggongOrderitemVO getVOById(Long id) {
ZhurongScjinggongOrderitem entity = this.getById(id);
return convert.toVO(entity);
}

@Override
public Long saveFromDTO(ZhurongScjinggongOrderitemDTO dto) {
ZhurongScjinggongOrderitem entity = convert.toEntity(dto);
this.save(entity);
return entity.getId();
}

@Override
public Boolean updateFromDTO(Long id, ZhurongScjinggongOrderitemDTO dto) {
ZhurongScjinggongOrderitem entity = this.getById(id);
convert.updateFromDTO(dto, entity);
return this.updateById(entity);
}
}
