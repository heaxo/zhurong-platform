package com.zhurong.platform.core.lantek.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.core.lantek.convert.PpbbPpbb00000100Convert;
import com.zhurong.platform.core.lantek.dto.PpbbPpbb00000100DTO;
import com.zhurong.platform.core.lantek.entity.PpbbPpbb00000100;
import com.zhurong.platform.core.lantek.mapper.PpbbPpbb00000100Mapper;
import com.zhurong.platform.core.lantek.service.IPpbbPpbb00000100Service;
import com.zhurong.platform.core.lantek.vo.PpbbPpbb00000100VO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
*  服务实现类
*/
@Service
@RequiredArgsConstructor
public class PpbbPpbb00000100ServiceImpl
extends ServiceImpl<PpbbPpbb00000100Mapper, PpbbPpbb00000100>
implements IPpbbPpbb00000100Service {

private final PpbbPpbb00000100Convert convert;


@Override
public PpbbPpbb00000100VO getVOById(Long id) {
PpbbPpbb00000100 entity = this.getById(id);
return convert.toVO(entity);
}

@Override
public Long saveFromDTO(PpbbPpbb00000100DTO dto) {
PpbbPpbb00000100 entity = convert.toEntity(dto);
this.save(entity);
return entity.getId();
}

@Override
public Boolean updateFromDTO(Long id, PpbbPpbb00000100DTO dto) {
PpbbPpbb00000100 entity = this.getById(id);
convert.updateFromDTO(dto, entity);
return this.updateById(entity);
}
}
