package ${package.Controller};

import ${package.Service}.${table.serviceName};
import ${package.Parent}.dto.${entity}DTO;
import ${package.Parent}.vo.${entity}VO;
import ${package.Parent}.entity.${entity};
import ${package.Parent}.api.I${entity}Api;

import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
* ${table.comment!} 控制器实现
*/
@RestController
@RequiredArgsConstructor
public class ${table.controllerName} implements I${entity}Api {

private final ${table.serviceName} service;

@Override
public ApiResponse<PageResponse<${entity}VO>> page(long current, long size) {

    Page<${entity}> page = service.page(new Page<>(current, size));

    List<${entity}VO> voList = page.getRecords()
        .stream()
        .map(service::convertToVO)
        .toList();

        PageResponse<${entity}VO> response = new PageResponse<>(
            voList,
            page.getTotal(),
            page.getCurrent(),
            page.getSize()
            );

            return ApiResponse.success(response);
            }

            @Override
            public ApiResponse<${entity}VO> getById(Serializable id) {
                return ApiResponse.success(service.getVOById(id));
                }

                @Override
                public ApiResponse<Void> save(@Valid ${entity}DTO dto) {
                    service.saveFromDTO(dto);
                    return ApiResponse.success();
                    }

                    @Override
                    public ApiResponse<Void> update(Serializable id, @Valid ${entity}DTO dto) {
                        service.updateFromDTO(id, dto);
                        return ApiResponse.success();
                        }

                        @Override
                        public ApiResponse<Void> remove(Serializable id) {
                            service.removeById(id);
                            return ApiResponse.success();
                            }

                            @Override
                            public ApiResponse<Void> batchRemove(List<Serializable> ids) {
                                    service.removeByIds(ids);
                                    return ApiResponse.success();
                                    }
                                    }
