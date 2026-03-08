package ${package.Parent}.vo;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
* ${table.comment!} VO
*/
@Data
public class ${entity}VO implements Serializable {

<#list table.fields as field>
    <#if field.propertyType?contains("Long") || field.propertyType == "long">
        private String <#if useDbColumnName>${field.name};<#else>${field.propertyName};</#if>
    <#else>
        private ${field.propertyType} <#if useDbColumnName>${field.name};<#else>${field.propertyName};</#if>
    </#if>
</#list>
}
