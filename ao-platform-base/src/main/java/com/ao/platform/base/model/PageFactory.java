package com.ao.platform.base.model;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Optional;

public final class PageFactory {

    private static final long MAX_PAGE_SIZE = 200;

    private PageFactory() {
    }

    public static <T> Page<T> build(BasePageQuery query) {

        long page = Optional.ofNullable(query.getPage()).orElse(1L);
        long size = Optional.ofNullable(query.getPageSize()).orElse(10L);

        if (page <= 0) {
            page = 1;
        }

        if (size <= 0) {
            size = 10;
        }

        if (size > MAX_PAGE_SIZE) {
            size = MAX_PAGE_SIZE;
        }

        Page<T> pageObj = new Page<>(page, size);

        // 统一排序
        if (StringUtils.isNotBlank(query.getOrderBy())) {

            boolean isAsc = "asc".equalsIgnoreCase(query.getOrderDirection());

            pageObj.addOrder(
                    isAsc ?
                            OrderItem.asc(query.getOrderBy()) :
                            OrderItem.desc(query.getOrderBy())
            );
        }

        return pageObj;
    }
}
