package com.zhurong.platform.custom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.convert.ZhurongScjinggongBasepartConvert;
import com.zhurong.platform.custom.dto.ZhurongScjinggongBasepartDTO;
import com.zhurong.platform.custom.entity.ZhurongScjinggongBasepart;
import com.zhurong.platform.custom.mapper.ZhurongScjinggongBasepartMapper;
import com.zhurong.platform.custom.service.IZhurongScjinggongBasepartService;
import com.zhurong.platform.custom.vo.ZhurongScjinggongBasepartVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
*  服务实现类
*/
@Service
@RequiredArgsConstructor
public class ZhurongScjinggongBasepartServiceImpl
extends ServiceImpl<ZhurongScjinggongBasepartMapper, ZhurongScjinggongBasepart>
implements IZhurongScjinggongBasepartService {

private final ZhurongScjinggongBasepartConvert convert;


@Override
public ZhurongScjinggongBasepartVO getVOById(Long id) {
ZhurongScjinggongBasepart entity = this.getById(id);
return convert.toVO(entity);
}

@Override
public Long saveFromDTO(ZhurongScjinggongBasepartDTO dto) {
ZhurongScjinggongBasepart entity = convert.toEntity(dto);
this.save(entity);
return entity.getId();
}

@Override
public Boolean updateFromDTO(Long id, ZhurongScjinggongBasepartDTO dto) {
ZhurongScjinggongBasepart entity = this.getById(id);
convert.updateFromDTO(dto, entity);
return this.updateById(entity);
}
}
