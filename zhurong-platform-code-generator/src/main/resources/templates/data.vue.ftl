import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { ${entity}VO } from '#/api';

export function useFormSchema(): VbenFormSchema[] {
return [
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
        {
        component: '<#if field.propertyType == "Integer"
    || field.propertyType == "Long"
    || field.propertyType == "Double"
    || field.propertyType == "Float"
    || field.propertyType == "BigDecimal"
    || field.propertyType == "Short"
    || field.propertyType == "Byte">InputNumber<#elseif field.propertyType == "LocalDateTime" || field.propertyType == "LocalDate" || field.propertyType == "Date">DatePicker<#elseif field.propertyName == "remark">Textarea<#else>Input</#if>',
        fieldName: '${field.propertyName}',
        label: '${field.propertyName}',
        <#if field.propertyName?contains("name") || field.propertyName?contains("code")>
            rules: 'required',
        </#if>
        },
    </#if>
</#list>
];
}


export function useGridFormSchema(): VbenFormSchema[] {
return [
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
        {
        component: '<#if field.propertyType == "Integer"
    || field.propertyType == "Long"
    || field.propertyType == "Double"
    || field.propertyType == "Float"
    || field.propertyType == "BigDecimal"
    || field.propertyType == "Short"
    || field.propertyType == "Byte">InputNumber<#else>Input</#if>',
        fieldName: '${field.propertyName}',
        label: '${field.propertyName}',
        },
    </#if>
</#list>
];
}

export function useColumns<T = ${entity}VO>(): VxeTableGridOptions['columns'] {
return [
{
align: 'left',
type: 'checkbox',
width: 30,
},
<#assign firstField = true>
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
        {
        field: '${field.propertyName}',
        title: '${field.propertyName}',
        width: 150<#if firstField>,
        filters: [{ data: '' }],
        filterRender: {
        name: 'TableTextFilterInput',
        }</#if>
        },
        <#assign firstField = false>
    </#if>
</#list>
];
}