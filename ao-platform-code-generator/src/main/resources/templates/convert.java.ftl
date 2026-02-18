package ${package.Parent}.convert;

import ${package.Entity}.${entity};
import ${package.Parent}.dto.${entity}DTO;
import ${package.Parent}.dto.${entity}PageQuery;
import ${package.Parent}.vo.${entity}VO;

import org.mapstruct.Mapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
* ${table.comment!} 对象转换器
*
* 说明：
* 1. Entity ↔ DTO
* 2. Entity ↔ VO
* 3. 使用 MapStruct 自动生成实现
*/
@Mapper(componentModel = "spring")
public interface ${entity}Convert {

/**
* Entity → VO
*/
${entity}VO toVO(${entity} entity);

/**
* Entity 列表 → VO 列表
*/
List
<${entity}VO> toVOList(List<${entity}> list);

    /**
    * DTO → Entity
    */
    ${entity} toEntity(${entity}DTO dto);
    ${entity} toEntity(${entity}PageQuery dto);

    /**
    * 更新时 DTO → Entity（忽略 null）
    */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(${entity}DTO dto, @MappingTarget ${entity} entity);
    }
