package ${package.Parent}.dto;

import com.ao.platform.base.api.BasePageQuery;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
* ${table.comment!} 分页查询对象
*/
@Getter
@Setter
public class ${entity}PageQuery extends BasePageQuery {

<#-- 自动生成字段条件（建议排除审计字段） -->
<#list table.fields as field>
    <#if field.propertyName != "id"
    && field.propertyName != "createTime"
    && field.propertyName != "updateTime"
    && field.propertyName != "tenantId"
    && field.propertyName != "deleted">

        /**
        * ${field.comment!}
        */
        private ${field.propertyType} ${field.propertyName};

    </#if>
</#list>
/**
* 创建时间开始
*/
private LocalDateTime beginCreateTime;

/**
* 创建时间结束
*/
private LocalDateTime endCreateTime;
}
