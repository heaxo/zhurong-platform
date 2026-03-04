package ${package.Entity};

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.ao.platform.base.model.BaseEntity;

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
public class ${entity} extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;

<#list table.fields as field>
    <#if field.propertyName != "id"
    && field.propertyName != "deleted"
    && field.propertyName != "version"
    && field.propertyName != "createBy"
    && field.propertyName != "createTime"
    && field.propertyName != "updateBy"
    && field.propertyName != "updateTime">

        /**
        * ${field.comment!}
        */
        @TableField("${field.name}")
        <#if useDbColumnName>
            private ${field.propertyType} ${field.name};
        <#else>
            private ${field.propertyType} ${field.propertyName};
        </#if>
    </#if>
</#list>
}
