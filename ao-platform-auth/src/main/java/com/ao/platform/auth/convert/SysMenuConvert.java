package com.ao.platform.auth.convert;

import com.ao.platform.auth.entity.SysMenu;
import com.ao.platform.auth.dto.SysMenuDTO;
import com.ao.platform.auth.vo.SysMenuVO;

import org.mapstruct.Mapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
*  对象转换器
*
* 说明：
* 1. Entity ↔ DTO
* 2. Entity ↔ VO
* 3. 使用 MapStruct 自动生成实现
*/
@Mapper(componentModel = "spring")
public interface SysMenuConvert {

/**
* Entity → VO
*/
SysMenuVO toVO(SysMenu entity);

/**
* Entity 列表 → VO 列表
*/
List<SysMenuVO> toVOList(List<SysMenu> list);

    /**
    * DTO → Entity
    */
    SysMenu toEntity(SysMenuDTO dto);

    /**
    * 更新时 DTO → Entity（忽略 null）
    */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(SysMenuDTO dto, @MappingTarget SysMenu entity);
    }
