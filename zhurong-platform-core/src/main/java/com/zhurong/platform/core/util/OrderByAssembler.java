package com.zhurong.platform.core.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.zhurong.platform.core.dto.SortInstructionDTO;

import java.util.*;

public final class OrderByAssembler {

    private OrderByAssembler() {
    }

    /**
     * 把前端排序规则安全应用到 QueryWrapper 上
     */
    public static <T> void applySorts(
            QueryWrapper<T> wrapper,
            Class<T> entityClass,
            List<SortInstructionDTO> sortRules,
            String defaultProperty,
            boolean defaultAsc
    ) {
        List<ResolvedSort> resolvedSorts = resolveSorts(entityClass, sortRules);

        if (resolvedSorts.isEmpty()) {
            if (defaultProperty != null && !defaultProperty.isBlank()) {
                String defaultColumn = resolveColumn(entityClass, defaultProperty);
                if (defaultColumn != null) {
                    if (defaultAsc) {
                        wrapper.orderByAsc(defaultColumn);
                    } else {
                        wrapper.orderByDesc(defaultColumn);
                    }
                }
            }
            return;
        }

        for (ResolvedSort sort : resolvedSorts) {
            if (sort.asc()) {
                wrapper.orderByAsc(sort.column());
            } else {
                wrapper.orderByDesc(sort.column());
            }
        }
    }

    /**
     * 解析排序规则
     */
    public static <T> List<ResolvedSort> resolveSorts(
            Class<T> entityClass,
            List<SortInstructionDTO> sortRules
    ) {
        if (sortRules == null || sortRules.isEmpty()) {
            return Collections.emptyList();
        }

        Map<String, String> propertyColumnMap = buildPropertyColumnMap(entityClass);
        if (propertyColumnMap.isEmpty()) {
            return Collections.emptyList();
        }

        List<ResolvedSort> result = new ArrayList<>();
        Set<String> seenColumns = new HashSet<>();

        for (SortInstructionDTO rule : sortRules) {
            if (rule == null || rule.getProperty() == null || rule.getProperty().isBlank()) {
                continue;
            }

            String normalizedProperty = normalize(rule.getProperty());
            String column = propertyColumnMap.get(normalizedProperty);
            if (column == null || column.isBlank()) {
                continue;
            }

            if (!seenColumns.add(column)) {
                continue;
            }

            boolean asc = !"desc".equalsIgnoreCase(rule.getDirection());
            result.add(new ResolvedSort(column, asc));
        }

        return result;
    }

    /**
     * 根据实体属性名解析数据库列名
     */
    public static <T> String resolveColumn(Class<T> entityClass, String property) {
        if (property == null || property.isBlank()) {
            return null;
        }
        return buildPropertyColumnMap(entityClass).get(normalize(property));
    }

    private static <T> Map<String, String> buildPropertyColumnMap(Class<T> entityClass) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        if (tableInfo == null) {
            return Collections.emptyMap();
        }

        Map<String, String> propertyColumnMap = new LinkedHashMap<>();

        if (tableInfo.getKeyProperty() != null && !tableInfo.getKeyProperty().isBlank()
                && tableInfo.getKeyColumn() != null && !tableInfo.getKeyColumn().isBlank()) {
            propertyColumnMap.put(normalize(tableInfo.getKeyProperty()), tableInfo.getKeyColumn());
        }

        if (tableInfo.getFieldList() != null && !tableInfo.getFieldList().isEmpty()) {
            for (TableFieldInfo fieldInfo : tableInfo.getFieldList()) {
                if (fieldInfo.getProperty() == null || fieldInfo.getProperty().isBlank()) {
                    continue;
                }
                propertyColumnMap.put(normalize(fieldInfo.getProperty()), fieldInfo.getColumn());
            }
        }

        return propertyColumnMap;
    }

    private static String normalize(String property) {
        return property.trim().toLowerCase(Locale.ROOT);
    }

    public record ResolvedSort(String column, boolean asc) {
    }
}