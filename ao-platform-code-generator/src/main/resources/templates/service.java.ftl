package ${package.Service};

import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.extension.service.IService;

/**
* ${table.comment!} 服务接口
*/
public interface ${table.serviceName} extends IService<${entity}> {
    ${entity}VO getVOById(Serializable id);

    void saveFromDTO(${entity}DTO dto);

    void updateFromDTO(Serializable id, ${entity}DTO dto);
}
