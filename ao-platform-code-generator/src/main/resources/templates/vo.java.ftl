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
        private String ${field.propertyName};
    <#else>
        private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
}
