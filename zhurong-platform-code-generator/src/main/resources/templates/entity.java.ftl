package ${package.Entity};

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;
<#if importLantekBaseEntity>
import ${package.Entity}.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
<#else>
import model.com.zhurong.platform.base.BaseEntity;
</#if>

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
    && field.propertyName != "isDeleted"
    && field.propertyName != "version"
    && field.propertyName != "createBy"
    && field.propertyName != "createdBy"
    && field.propertyName != "createdAt"
    && field.propertyName != "createTime"
    && field.propertyName != "updateBy"
    && field.propertyName != "updatedBy"
    && field.propertyName != "updatedAt"
    && field.propertyName != "updateTime"
    && field.propertyName != "isRead"
    && field.propertyName != "isReviewed">

        /**
        * ${field.comment!}
        */
        <#if importLantekBaseEntity && field.name?lower_case == "recid">
            @TableId(value = "RecID",type = IdType.AUTO)
        <#else>
            @TableField("${field.name}")
        </#if>
        <#if useDbColumnName>
            private ${field.propertyType} ${field.name};
        <#else>
            private ${field.propertyType} ${field.propertyName};
        </#if>
    </#if>
</#list>
}
