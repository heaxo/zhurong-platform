package ${package.Entity};

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
* ${table.comment!}
*
* @author ${author}
* @since ${date}
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("${table.name}")
public class ${entity} implements Serializable {

private static final long serialVersionUID = 1L;

<#list table.fields as field>
    /**
    * ${field.comment!}
    */
    private ${field.propertyType} ${field.propertyName};

</#list>
}
