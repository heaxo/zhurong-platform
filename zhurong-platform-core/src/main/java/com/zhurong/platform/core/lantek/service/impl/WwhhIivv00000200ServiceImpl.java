package com.zhurong.platform.core.lantek.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.core.lantek.convert.WwhhIivv00000200Convert;
import com.zhurong.platform.core.lantek.dto.WwhhIivv00000200DTO;
import com.zhurong.platform.core.lantek.entity.WwhhIivv00000200;
import com.zhurong.platform.core.lantek.mapper.WwhhIivv00000200Mapper;
import com.zhurong.platform.core.lantek.service.IWwhhIivv00000200Service;
import com.zhurong.platform.core.lantek.vo.WwhhIivv00000200VO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
*  服务实现类
*/
@Service
@RequiredArgsConstructor
public class WwhhIivv00000200ServiceImpl
extends ServiceImpl<WwhhIivv00000200Mapper, WwhhIivv00000200>
implements IWwhhIivv00000200Service {

private final WwhhIivv00000200Convert convert;


@Override
public WwhhIivv00000200VO getVOById(Long id) {
WwhhIivv00000200 entity = this.getById(id);
return convert.toVO(entity);
}

@Override
public Long saveFromDTO(WwhhIivv00000200DTO dto) {
WwhhIivv00000200 entity = convert.toEntity(dto);
this.save(entity);
return entity.getId();
}

@Override
public Boolean updateFromDTO(Long id, WwhhIivv00000200DTO dto) {
WwhhIivv00000200 entity = this.getById(id);
convert.updateFromDTO(dto, entity);
return this.updateById(entity);
}
}
