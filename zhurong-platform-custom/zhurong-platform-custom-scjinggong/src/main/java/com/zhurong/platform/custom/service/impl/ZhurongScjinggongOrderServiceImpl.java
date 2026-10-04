package com.zhurong.platform.custom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.convert.ZhurongScjinggongOrderConvert;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderDTO;
import com.zhurong.platform.custom.entity.ZhurongScjinggongOrder;
import com.zhurong.platform.custom.mapper.ZhurongScjinggongOrderMapper;
import com.zhurong.platform.custom.service.IZhurongScjinggongOrderService;
import com.zhurong.platform.custom.vo.ZhurongScjinggongOrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
*  服务实现类
*/
@Service
@RequiredArgsConstructor
public class ZhurongScjinggongOrderServiceImpl
extends ServiceImpl<ZhurongScjinggongOrderMapper, ZhurongScjinggongOrder>
implements IZhurongScjinggongOrderService {

private final ZhurongScjinggongOrderConvert convert;


@Override
public ZhurongScjinggongOrderVO getVOById(Long id) {
ZhurongScjinggongOrder entity = this.getById(id);
return convert.toVO(entity);
}

@Override
public Long saveFromDTO(ZhurongScjinggongOrderDTO dto) {
ZhurongScjinggongOrder entity = convert.toEntity(dto);
this.save(entity);
return entity.getId();
}

@Override
public Boolean updateFromDTO(Long id, ZhurongScjinggongOrderDTO dto) {
ZhurongScjinggongOrder entity = this.getById(id);
convert.updateFromDTO(dto, entity);
return this.updateById(entity);
}
}
