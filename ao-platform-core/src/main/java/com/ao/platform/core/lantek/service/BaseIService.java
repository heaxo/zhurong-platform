package com.ao.platform.core.lantek.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.List;

public interface BaseIService<T> extends IService<T> {
    Integer sqlserverMaxInCount = 2000;

    default <E> List<T> listByIn(LambdaQueryWrapper<T> queryWrapper, SFunction<T, ?> R, List<E> inList) {
        return listByIn(queryWrapper, R, inList, sqlserverMaxInCount);
    }

    default <E> List<T> listByIn(LambdaQueryWrapper<T> queryWrapper, SFunction<T, ?> R, List<E> inList, Integer maxInCount) {
        List<T> list = new ArrayList<>();
        if (inList.size() > maxInCount) {
            int count = inList.size() / maxInCount;
            int yu = inList.size() % maxInCount;
            if (yu > 0) {
                count = count + 1;
            }
            for (int i = 0; i < count; i++) {
                LambdaQueryWrapper<T> newWrapper = queryWrapper.clone();
                if (i == count - 1) {
                    List<E> ins = inList.subList(i * maxInCount, inList.size());
                    newWrapper.in(R, ins);
                } else {
                    List<E> ins = inList.subList(i * maxInCount, maxInCount * (i + 1));
                    newWrapper.in(R, ins);
                }
                list.addAll(list(newWrapper));
            }
        } else {
            list.addAll(list(queryWrapper.in(R, inList)));
        }
        return list;
    }
}
