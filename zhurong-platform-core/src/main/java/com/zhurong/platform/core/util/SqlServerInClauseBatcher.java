package com.zhurong.platform.core.util;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public final class SqlServerInClauseBatcher {

    public static final int MAX_PARAMETER_COUNT = 2000;

    private SqlServerInClauseBatcher() {
    }

    public static <T, E> List<T> listByIn(IService<T> service,
                                         LambdaQueryWrapper<T> queryWrapper,
                                         SFunction<T, ?> column,
                                         Collection<E> values) {
        if (values == null || values.isEmpty()) {
            return List.of();
        }

        List<T> result = new ArrayList<>();
        forEachBatch(values, batch -> result.addAll(service.list(queryWrapper.clone().in(column, batch))));
        return result;
    }

    public static <L, R, T> List<T> listByTwoIn(BiFunction<List<L>, List<R>, List<T>> query,
                                               Collection<L> leftValues,
                                               Collection<R> rightValues) {
        if (leftValues == null || leftValues.isEmpty() || rightValues == null || rightValues.isEmpty()) {
            return List.of();
        }

        List<L> leftList = new ArrayList<>(leftValues);
        List<R> rightList = new ArrayList<>(rightValues);
        if (leftList.size() + rightList.size() <= MAX_PARAMETER_COUNT) {
            return query.apply(leftList, rightList);
        }

        int leftBatchSize;
        int rightBatchSize;
        if (leftList.size() <= MAX_PARAMETER_COUNT / 2) {
            leftBatchSize = leftList.size();
            rightBatchSize = MAX_PARAMETER_COUNT - leftBatchSize;
        } else if (rightList.size() <= MAX_PARAMETER_COUNT / 2) {
            rightBatchSize = rightList.size();
            leftBatchSize = MAX_PARAMETER_COUNT - rightBatchSize;
        } else {
            leftBatchSize = MAX_PARAMETER_COUNT / 2;
            rightBatchSize = MAX_PARAMETER_COUNT - leftBatchSize;
        }

        List<T> result = new ArrayList<>();
        for (List<L> leftBatch : partition(leftList, leftBatchSize)) {
            for (List<R> rightBatch : partition(rightList, rightBatchSize)) {
                result.addAll(query.apply(leftBatch, rightBatch));
            }
        }
        return result;
    }

    public static <E> void forEachBatch(Collection<E> values, Consumer<List<E>> batchConsumer) {
        if (values == null || values.isEmpty()) {
            return;
        }

        for (List<E> batch : partition(new ArrayList<>(values), MAX_PARAMETER_COUNT)) {
            batchConsumer.accept(batch);
        }
    }

    private static <E> List<List<E>> partition(List<E> values, int batchSize) {
        List<List<E>> batches = new ArrayList<>();
        for (int start = 0; start < values.size(); start += batchSize) {
            batches.add(values.subList(start, Math.min(start + batchSize, values.size())));
        }
        return batches;
    }
}
