package ${package.Parent}.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
* ${table.comment!} DTO
*/
@Data
public class ${entity}DTO implements Serializable {

<#list table.fields as field>
    private ${field.propertyType} ${field.propertyName};
</#list>

}
