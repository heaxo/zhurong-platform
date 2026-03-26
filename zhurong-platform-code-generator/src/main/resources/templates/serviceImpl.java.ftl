package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${package.Parent}.convert.${entity}Convert;
import ${package.Parent}.dto.${entity}DTO;
import ${package.Parent}.vo.${entity}VO;

import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
* ${table.comment!} 服务实现类
*/
@Service
@RequiredArgsConstructor
public class ${table.serviceImplName}
extends ServiceImpl<${table.mapperName}, ${entity}>
implements ${table.serviceName} {

private final ${entity}Convert convert;


@Override
public ${entity}VO getVOById(Long id) {
${entity} entity = this.getById(id);
return convert.toVO(entity);
}

@Override
public Long saveFromDTO(${entity}DTO dto) {
${entity} entity = convert.toEntity(dto);
this.save(entity);
return entity.getId();
}

@Override
public Boolean updateFromDTO(Long id, ${entity}DTO dto) {
${entity} entity = this.getById(id);
convert.updateFromDTO(dto, entity);
return this.updateById(entity);
}
}
