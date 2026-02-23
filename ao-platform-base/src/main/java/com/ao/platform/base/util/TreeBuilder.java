package com.ao.platform.base.util;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class TreeBuilder {

    public static <T, K> List<T> buildTree(
            List<T> list,
            Function<T, K> idGetter,
            Function<T, K> pidGetter,
            Predicate<K> rootPredicate,
            Comparator<T> comparator,
            BiConsumer<T, List<T>> childrenSetter
    ) {

        Map<K, List<T>> parentMap = list.stream()
                .collect(Collectors.groupingBy(pidGetter));

        List<T> roots = list.stream()
                .filter(node -> rootPredicate.test(pidGetter.apply(node)))
                .sorted(comparator)
                .toList();

        attachChildren(roots, parentMap, idGetter, comparator, childrenSetter);

        return roots;
    }

    private static <T, K> void attachChildren(
            List<T> parents,
            Map<K, List<T>> parentMap,
            Function<T, K> idGetter,
            Comparator<T> comparator,
            BiConsumer<T, List<T>> childrenSetter
    ) {

        for (T parent : parents) {

            K id = idGetter.apply(parent);
            List<T> children = parentMap.get(id);

            if (children != null && !children.isEmpty()) {
                children.sort(comparator);
                childrenSetter.accept(parent, children);
                attachChildren(children, parentMap, idGetter, comparator, childrenSetter);
            }
        }
    }
}