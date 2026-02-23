package com.ao.platform.base.api;

import java.io.Serializable;
import java.util.List;

public record PageResponse<T>(
        List<T> items,
        long total,
        long current,
        long size
) implements Serializable {
}
