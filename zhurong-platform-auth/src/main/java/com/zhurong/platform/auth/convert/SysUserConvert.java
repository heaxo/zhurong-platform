package com.zhurong.platform.auth.convert;

import com.zhurong.platform.auth.dto.CreateUserRequest;
import com.zhurong.platform.auth.dto.SysUserDTO;
import com.zhurong.platform.auth.dto.SysUserPageQuery;
import com.zhurong.platform.auth.entity.SysUser;
import com.zhurong.platform.auth.vo.SysUserVO;
import com.zhurong.platform.security.model.TokenUser;
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
