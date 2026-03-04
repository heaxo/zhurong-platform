package com.ao.platform.auth.convert;

import com.ao.platform.auth.dto.CreateUserRequest;
import com.ao.platform.auth.dto.SysUserDTO;
import com.ao.platform.auth.dto.SysUserPageQuery;
import com.ao.platform.auth.entity.SysUser;
import com.ao.platform.auth.vo.SysUserVO;
import com.ao.platform.security.model.TokenUser;
import org.mapstruct.*;

import java.util.List;

/**
 * 对象转换器
 * <p>
 * 说明：
 * 1. Entity ↔ DTO
 * 2. Entity ↔ VO
 * 3. 使用 MapStruct 自动生成实现
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SysUserConvert {

    /**
     * Entity → VO
     */
    SysUserVO toVO(SysUser entity);

    /**
     * Entity 列表 → VO 列表
     */
    List
            <SysUserVO> toVOList(List<SysUser> list);

    /**
     * DTO → Entity
     */
    SysUser toEntity(SysUserDTO dto);

    SysUser toEntity(SysUserPageQuery dto);

    SysUserDTO toDTO(CreateUserRequest request);

    SysUserDTO toDTO(SysUser entity);

    TokenUser toTokenUser(SysUser entity);

    /**
     * 更新时 DTO → Entity（忽略 null）
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(SysUserDTO dto, @MappingTarget SysUser entity);
}
