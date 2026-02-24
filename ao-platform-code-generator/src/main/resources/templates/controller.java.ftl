package ${package.Controller};

import ${package.Service}.${table.serviceName};
import ${package.Parent}.dto.${entity}DTO;
import ${package.Parent}.dto.${entity}PageQuery;
import ${package.Parent}.vo.${entity}VO;
import ${package.Parent}.entity.${entity};
import ${package.Parent}.api.I${entity}Api;
import ${package.Parent}.convert.${entity}Convert;

import com.ao.platform.base.api.ApiResponse;
import com.ao.platform.base.api.PageResponse;
import com.ao.platform.base.model.PageFactory;
import com.ao.platform.auth.web.BaseController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

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
public class ${table.controllerName} extends BaseController implements I${entity}Api {

private final ${entity}Convert convert;
private final ${table.serviceName} service;

@Override
public ApiResponse
<PageResponse
<${entity}VO>> page(${entity}PageQuery pageQuery ) {

    LambdaQueryWrapper<${entity}> wrapper =
    Wrappers.lambdaQuery(convert.toEntity(pageQuery));

    Page<${entity}> page = service.page(
    PageFactory.build(pageQuery),
    wrapper
    );

    List
    <${entity}VO> voList = page.getRecords()
        .stream()
        .map(convert::toVO)
        .toList();

        PageResponse
        <${entity}VO> response = new PageResponse<>(
            voList,
            page.getTotal(),
            page.getCurrent(),
            page.getSize()
            );

            return ApiResponse.success(response);
            }

            @Override
            public ApiResponse
            <${entity}VO> getById(Long id) {
                return ApiResponse.success(service.getVOById(id));
                }

                @Override
                public ApiResponse
                <Long> save(@Valid ${entity}DTO dto) {
                    Long id = service.saveFromDTO(dto);
                    return ApiResponse.success(id);
                    }

                    @Override
                    public ApiResponse
                    <Boolean> update(Long id, @Valid ${entity}DTO dto) {
                        boolean update = service.updateFromDTO(id, dto);
                        return ApiResponse.success(update);
                        }

                        @Override
                        public ApiResponse
                        <Boolean> remove(Long id) {
                            boolean remove = service.removeById(id);
                            return ApiResponse.success(remove);
                            }

                            @Override
                            public ApiResponse
                            <Boolean> batchRemove(List
                                <Serializable> ids) {
                                    boolean remove = service.removeByIds(ids);
                                    return ApiResponse.success(remove);
                                    }
                                    }
