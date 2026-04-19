import type { Recordable } from '@vben/types';

export interface ${entity}VO {
<#list table.fields as field>
    <#if field.propertyName != "deleted"
    && field.propertyName != "isDeleted">
        /**
        * ${field.comment!field.propertyName}
        */
        ${field.propertyName}?: ${field.propertyType?switch(
    "String", "string",
    "Integer", "number",
    "Long", "number",
    "Double", "number",
    "Float", "number",
    "BigDecimal", "number",
    "Short", "number",
    "Byte", "number",
    "Boolean", "boolean",
    "LocalDate", "string",
    "LocalDateTime", "string",
    "Date", "string",
    "Object", "Recordable<any>",
    "string"
    )};
    </#if>
</#list>
}

export interface ${entity}DTO {
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
        * ${field.comment!field.propertyName}
        */
        ${field.propertyName}?: ${field.propertyType?switch(
    "String", "string",
    "Integer", "number",
    "Long", "number",
    "Double", "number",
    "Float", "number",
    "BigDecimal", "number",
    "Short", "number",
    "Byte", "number",
    "Boolean", "boolean",
    "LocalDate", "string",
    "LocalDateTime", "string",
    "Date", "string",
    "Object", "Recordable<any>",
    "string"
    )};
    </#if>
</#list>
}

export interface ${entity}PageQuery {
current?: number;
size?: number;

<#list table.fields as field>
    <#if field.propertyName != "deleted"
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
        * ${field.comment!field.propertyName}
        */
        ${field.propertyName}?: ${field.propertyType?switch(
    "String", "string",
    "Integer", "number",
    "Long", "number",
    "Double", "number",
    "Float", "number",
    "BigDecimal", "number",
    "Short", "number",
    "Byte", "number",
    "Boolean", "boolean",
    "LocalDate", "string",
    "LocalDateTime", "string",
    "Date", "string",
    "Object", "Recordable<any>",
    "string"
    )};
    </#if>
</#list>
}