package com.zhurong.platform.core.lantek.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.core.lantek.convert.WwhhWwhh00000200Convert;
import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000200DTO;
import com.zhurong.platform.core.lantek.entity.WwhhWwhh00000200;
import com.zhurong.platform.core.lantek.mapper.WwhhWwhh00000200Mapper;
import com.zhurong.platform.core.lantek.service.IWwhhWwhh00000200Service;
import com.zhurong.platform.core.lantek.vo.WwhhWwhh00000200VO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
*  服务实现类
*/
@Service
@RequiredArgsConstructor
public class WwhhWwhh00000200ServiceImpl
extends ServiceImpl<WwhhWwhh00000200Mapper, WwhhWwhh00000200>
implements IWwhhWwhh00000200Service {

private final WwhhWwhh00000200Convert convert;


@Override
public WwhhWwhh00000200VO getVOById(Long id) {
WwhhWwhh00000200 entity = this.getById(id);
return convert.toVO(entity);
}

@Override
public Long saveFromDTO(WwhhWwhh00000200DTO dto) {
WwhhWwhh00000200 entity = convert.toEntity(dto);
this.save(entity);
return entity.getId();
}

@Override
public Boolean updateFromDTO(Long id, WwhhWwhh00000200DTO dto) {
WwhhWwhh00000200 entity = this.getById(id);
convert.updateFromDTO(dto, entity);
return this.updateById(entity);
}
}
