package com.zhurong.platform.core.lantek.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.core.lantek.convert.DisMmnnBwsr00000200Convert;
import com.zhurong.platform.core.lantek.dto.DisMmnnBwsr00000200DTO;
import com.zhurong.platform.core.lantek.entity.DisMmnnBwsr00000200;
import com.zhurong.platform.core.lantek.mapper.DisMmnnBwsr00000200Mapper;
import com.zhurong.platform.core.lantek.service.IDisMmnnBwsr00000200Service;
import com.zhurong.platform.core.lantek.vo.DisMmnnBwsr00000200VO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
*  服务实现类
*/
@Service
@RequiredArgsConstructor
public class DisMmnnBwsr00000200ServiceImpl
extends ServiceImpl<DisMmnnBwsr00000200Mapper, DisMmnnBwsr00000200>
implements IDisMmnnBwsr00000200Service {

private final DisMmnnBwsr00000200Convert convert;


@Override
public DisMmnnBwsr00000200VO getVOById(Long id) {
DisMmnnBwsr00000200 entity = this.getById(id);
return convert.toVO(entity);
}

@Override
public Long saveFromDTO(DisMmnnBwsr00000200DTO dto) {
DisMmnnBwsr00000200 entity = convert.toEntity(dto);
this.save(entity);
return entity.getId();
}

@Override
public Boolean updateFromDTO(Long id, DisMmnnBwsr00000200DTO dto) {
DisMmnnBwsr00000200 entity = this.getById(id);
convert.updateFromDTO(dto, entity);
return this.updateById(entity);
}
}
