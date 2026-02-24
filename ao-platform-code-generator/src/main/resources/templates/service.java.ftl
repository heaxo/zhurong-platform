package ${package.Service};

import ${package.Entity}.${entity};
import ${package.Parent}.vo.${entity}VO;
import ${package.Parent}.dto.${entity}DTO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;

/**
* ${table.comment!} 服务接口
*/
public interface ${table.serviceName} extends IService<${entity}> {
${entity}VO getVOById(Long id);

Long saveFromDTO(${entity}DTO dto);

Boolean updateFromDTO(Long id, ${entity}DTO dto);
}
