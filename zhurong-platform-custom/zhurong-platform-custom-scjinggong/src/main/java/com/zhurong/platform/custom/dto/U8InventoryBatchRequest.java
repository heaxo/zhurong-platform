package com.zhurong.platform.custom.dto;

import lombok.Data;

import java.util.List;

/**
 * 当前页物料批量加载U8库存的请求。
 */
@Data
public class U8InventoryBatchRequest {

    private List<String> inventoryCodes;
}
