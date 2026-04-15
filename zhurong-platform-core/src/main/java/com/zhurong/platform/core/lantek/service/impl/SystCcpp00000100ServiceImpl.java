package com.zhurong.platform.core.lantek.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.core.lantek.convert.SystCcpp00000100Convert;
import com.zhurong.platform.core.lantek.dto.SystCcpp00000100DTO;
import com.zhurong.platform.core.lantek.entity.SystCcpp00000100;
import com.zhurong.platform.core.lantek.mapper.SystCcpp00000100Mapper;
import com.zhurong.platform.core.lantek.service.ISystCcpp00000100Service;
import com.zhurong.platform.core.lantek.vo.SystCcpp00000100VO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
*  服务实现类
*/
@Service
@RequiredArgsConstructor
public class SystCcpp00000100ServiceImpl
extends ServiceImpl<SystCcpp00000100Mapper, SystCcpp00000100>
implements ISystCcpp00000100Service {

private final SystCcpp00000100Convert convert;


@Override
public SystCcpp00000100VO getVOById(Long id) {
SystCcpp00000100 entity = this.getById(id);
return convert.toVO(entity);
}

@Override
public Long saveFromDTO(SystCcpp00000100DTO dto) {
SystCcpp00000100 entity = convert.toEntity(dto);
this.save(entity);
return entity.getId();
}

@Override
public Boolean updateFromDTO(Long id, SystCcpp00000100DTO dto) {
SystCcpp00000100 entity = this.getById(id);
convert.updateFromDTO(dto, entity);
return this.updateById(entity);
}
}
