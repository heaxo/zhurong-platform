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
    private ${field.propertyType} ${field.propertyName};
</#list>

}
