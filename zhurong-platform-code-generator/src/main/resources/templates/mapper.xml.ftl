<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

    <#if enableCache>
        <!-- 开启二级缓存 -->
        <cache type="${cacheClassName}"/>

    </#if>

    <#if baseResultMap>
        <!-- 通用查询映射结果 -->
        <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
            <#list table.fields as field>
                <#if field.keyFlag>
                    <id column="${field.name}" property="${field.name}"/>
                </#if>
            </#list>
            <#list table.commonFields as field>
                <result column="${field.name}" property="${field.name}"/>
            </#list>
            <#list table.fields as field>
                <#if !field.keyFlag>
                    <result column="${field.name}" property="${field.name}"/>
                </#if>
            </#list>
        </resultMap>

    </#if>

    <#assign allFields = table.commonFields + table.fields>

    <!-- 通用查询结果列：使用 include property 传入 alias -->
    <sql id="BaseColumns">
        <#list allFields as field>
            ${r'${alias}'}.[${field.columnName}]<#sep>,
        </#list>
    </sql>

    <!-- 通用动态查询条件：SQL Server 版 -->
    <sql id="BaseWhere">
        <#list allFields as field>
            <#assign jdbcType = "NVARCHAR">
            <#if field.propertyType == "String">
                <#assign jdbcType = "NVARCHAR">
            <#elseif field.propertyType == "Long" || field.propertyType == "long">
                <#assign jdbcType = "BIGINT">
            <#elseif field.propertyType == "Integer" || field.propertyType == "int">
                <#assign jdbcType = "INTEGER">
            <#elseif field.propertyType == "Boolean" || field.propertyType == "boolean">
                <#assign jdbcType = "BIT">
            <#elseif field.propertyType == "Date" || field.propertyType == "LocalDateTime">
                <#assign jdbcType = "TIMESTAMP">
            <#elseif field.propertyType == "LocalDate">
                <#assign jdbcType = "DATE">
            <#elseif field.propertyType == "LocalTime">
                <#assign jdbcType = "TIME">
            <#elseif field.propertyType == "BigDecimal">
                <#assign jdbcType = "DECIMAL">
            <#elseif field.propertyType == "Double" || field.propertyType == "double">
                <#assign jdbcType = "DOUBLE">
            <#elseif field.propertyType == "Float" || field.propertyType == "float">
                <#assign jdbcType = "REAL">
            </#if>

            <if test="${r'${paramAlias}'}.${field.columnName} != null">
                <#if field_index == 0>
                    ${r'${alias}'}.[${field.columnName}] = ${r'#{${paramAlias}.'}${field.columnName},jdbcType=${jdbcType}}
                <#else>
                    AND ${r'${alias}'}.[${field.columnName}] = ${r'#{${paramAlias}.'}${field.columnName},jdbcType=${jdbcType}}
                </#if>
            </if>
        </#list>
    </sql>

    <sql id="BasePage">
        OFFSET ((${r'#{'}current,jdbcType=INTEGER} - 1) * ${r'#{'}pageSize,jdbcType=INTEGER}) ROWS
        FETCH NEXT ${r'#{'}pageSize,jdbcType=INTEGER} ROWS ONLY
    </sql>
</mapper>