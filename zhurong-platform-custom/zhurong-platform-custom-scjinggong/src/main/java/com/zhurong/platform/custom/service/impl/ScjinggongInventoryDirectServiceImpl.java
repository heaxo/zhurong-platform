package com.zhurong.platform.custom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.clientimport.dto.RawMaterialRequest;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.custom.dto.ScjinggongInventoryDirectDTO;
import com.zhurong.platform.custom.dto.ScjinggongInventoryDirectPageQuery;
import com.zhurong.platform.custom.dto.U8InventoryBatchRequest;
import com.zhurong.platform.custom.dto.U8InventoryRequest;
import com.zhurong.platform.custom.dto.U8InventoryResponse;
import com.zhurong.platform.custom.mes.entity.ScjinggongMesInventory;
import com.zhurong.platform.custom.mes.service.IScjinggongMesInventoryService;
import com.zhurong.platform.custom.service.IScjinggongInventoryDirectService;
import com.zhurong.platform.custom.service.ScjinggongExpertInventoryImportService;
import com.zhurong.platform.custom.service.ScjinggongInventoryWarehousePolicy;
import com.zhurong.platform.custom.vo.ScjinggongInventoryDirectVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * 新库存同步页面服务实现。
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ScjinggongInventoryDirectServiceImpl implements IScjinggongInventoryDirectService {

    private static final int MAX_BATCH_SIZE = 200;

    private final IScjinggongMesInventoryService mesInventoryService;
    private final U8InventoryService u8InventoryService;
    private final ScjinggongExpertInventoryImportService expertInventoryImportService;

    @Override
    public PageResponse<ScjinggongInventoryDirectVO> page(ScjinggongInventoryDirectPageQuery pageQuery) {
        validateRanges(pageQuery);

        LambdaQueryWrapper<ScjinggongMesInventory> wrapper = Wrappers.lambdaQuery();
        // 库存同步只允许查询钢板分类，且该条件不受前端可选查询条件影响。
        wrapper.and(condition -> condition
                        .eq(ScjinggongMesInventory::getInvClassCode, "A01")
                        .or()
                        .eq(ScjinggongMesInventory::getInvClassCode, "A06"))
                .like(StringUtils.hasText(pageQuery.getInvCode()),
                        ScjinggongMesInventory::getInvCode, trim(pageQuery.getInvCode()))
                .like(StringUtils.hasText(pageQuery.getInvName()),
                        ScjinggongMesInventory::getInvName, trim(pageQuery.getInvName()))
                .like(StringUtils.hasText(pageQuery.getInvSize()),
                        ScjinggongMesInventory::getInvSize, trim(pageQuery.getInvSize()))
                .like(StringUtils.hasText(pageQuery.getInvMaterialCode()),
                        ScjinggongMesInventory::getInvMaterialCode, trim(pageQuery.getInvMaterialCode()))
                .ge(pageQuery.getInvLongMin() != null,
                        ScjinggongMesInventory::getInvLong, pageQuery.getInvLongMin())
                .le(pageQuery.getInvLongMax() != null,
                        ScjinggongMesInventory::getInvLong, pageQuery.getInvLongMax())
                .ge(pageQuery.getInvWideMin() != null,
                        ScjinggongMesInventory::getInvWide, pageQuery.getInvWideMin())
                .le(pageQuery.getInvWideMax() != null,
                        ScjinggongMesInventory::getInvWide, pageQuery.getInvWideMax())
                .ge(pageQuery.getInvThickMin() != null,
                        ScjinggongMesInventory::getInvThick, pageQuery.getInvThickMin())
                .le(pageQuery.getInvThickMax() != null,
                        ScjinggongMesInventory::getInvThick, pageQuery.getInvThickMax())
                .orderByAsc(ScjinggongMesInventory::getInvCode);

        Page<ScjinggongMesInventory> page = mesInventoryService.page(PageFactory.build(pageQuery), wrapper);
        List<ScjinggongInventoryDirectVO> items = page.getRecords().stream()
                .map(this::toVO)
                .toList();
        return new PageResponse<>(items, page.getTotal(), page.getCurrent(), page.getSize());
    }

    @Override
    public List<U8InventoryResponse> loadFromU8(U8InventoryBatchRequest request) {
        List<String> inventoryCodes = normalizeInventoryCodes(request);
        log.info("开始从U8加载当前页库存，物料数量={}", inventoryCodes.size());

        List<U8InventoryResponse> result = new ArrayList<>();
        for (String inventoryCode : inventoryCodes) {
            U8InventoryRequest queryRequest = new U8InventoryRequest();
            queryRequest.setInvcode(inventoryCode);
            List<U8InventoryResponse> queryResult = u8InventoryService.query(queryRequest);

            U8InventoryResponse selected = null;
            for (U8InventoryResponse inventory : queryResult) {
                if (inventory == null || !StringUtils.hasText(inventory.getCInvCode())) {
                    log.warn("U8库存查询返回了缺少物料编码的数据，查询物料={}", inventoryCode);
                    continue;
                }
                if (!normalizeInventoryCode(inventory.getCInvCode()).equals(normalizeInventoryCode(inventoryCode))) {
                    log.warn("U8库存查询返回了非目标物料，查询物料={}，返回物料={}",
                            inventoryCode, inventory.getCInvCode());
                    continue;
                }
                if (selected != null) {
                    log.warn("U8库存查询返回了重复物料编码，使用最后一条数据：{}", inventoryCode);
                }
                selected = inventory;
            }
            if (selected != null) {
                result.add(selected);
            }
        }

        log.info("U8当前页库存加载完成，请求物料数量={}，匹配库存数量={}",
                inventoryCodes.size(), result.size());
        return result;
    }

    @Override
    public boolean importToExpert(List<ScjinggongInventoryDirectDTO> inventoryList) throws IOException {
        if (CollectionUtils.isEmpty(inventoryList)) {
            throw new BusinessException("请选择需要导入的钢板库存");
        }
        if (inventoryList.size() > MAX_BATCH_SIZE) {
            throw new BusinessException("单次最多导入%s条钢板库存".formatted(MAX_BATCH_SIZE));
        }

        List<String> invalidWarehouseItems = inventoryList.stream()
                .filter(item -> item == null || !ScjinggongInventoryWarehousePolicy.isAllowed(item.getCWhCode()))
                .map(item -> item == null
                        ? "空数据"
                        : "%s(%s)".formatted(displayInventoryCode(item), displayWarehouseCode(item)))
                .toList();
        if (CollectionUtils.isNotEmpty(invalidWarehouseItems)) {
            log.warn("拒绝导入不在规定仓库范围内的钢板，明细={}", invalidWarehouseItems);
            throw new BusinessException(
                    "板材数据不在规定的仓库范围内：%s；允许仓库：%s"
                            .formatted(String.join(",", invalidWarehouseItems),
                                    String.join(",", ScjinggongInventoryWarehousePolicy.ALLOWED_CODES))
            );
        }

        List<RawMaterialRequest> requests = inventoryList.stream()
                .map(this::toRawMaterialRequest)
                .toList();
        log.info("新库存页面提交Expert导入，数量={}，物料={}", requests.size(),
                inventoryList.stream().map(this::displayInventoryCode).toList());
        return expertInventoryImportService.importRawMaterials(requests);
    }

    private List<String> normalizeInventoryCodes(U8InventoryBatchRequest request) {
        if (request == null || CollectionUtils.isEmpty(request.getInventoryCodes())) {
            throw new BusinessException("当前页物料编码不能为空");
        }

        Set<String> inventoryCodes = new LinkedHashSet<>();
        for (String inventoryCode : request.getInventoryCodes()) {
            if (!StringUtils.hasText(inventoryCode)) {
                throw new BusinessException("当前页存在空物料编码，无法加载U8库存");
            }
            inventoryCodes.add(inventoryCode.trim());
        }
        if (inventoryCodes.size() > MAX_BATCH_SIZE) {
            throw new BusinessException("单次最多从U8加载%s个物料".formatted(MAX_BATCH_SIZE));
        }
        return List.copyOf(inventoryCodes);
    }

    private RawMaterialRequest toRawMaterialRequest(ScjinggongInventoryDirectDTO inventory) {
        List<String> missingFields = new ArrayList<>();
        if (!StringUtils.hasText(inventory.getInvCode())) {
            missingFields.add("物料编码");
        }
        if (!StringUtils.hasText(inventory.getInvMaterialCode())) {
            missingFields.add("材质");
        }
        if (inventory.getInvThick() == null) {
            missingFields.add("厚度");
        }
        if (inventory.getInvLong() == null) {
            missingFields.add("长度");
        }
        if (inventory.getInvWide() == null) {
            missingFields.add("宽度");
        }
        if (inventory.getIQuantity() == null) {
            missingFields.add("库存数量");
        }
        if (CollectionUtils.isNotEmpty(missingFields)) {
            throw new BusinessException(
                    "物料%s缺少导入必填字段：%s"
                            .formatted(displayInventoryCode(inventory), String.join(",", missingFields))
            );
        }
        if (StringUtils.hasText(inventory.getCInvCode())
                && !normalizeInventoryCode(inventory.getInvCode())
                .equals(normalizeInventoryCode(inventory.getCInvCode()))) {
            throw new BusinessException(
                    "MES物料编码%s与U8物料编码%s不一致"
                            .formatted(inventory.getInvCode(), inventory.getCInvCode())
            );
        }

        return new RawMaterialRequest()
                .setPrdRef(inventory.getInvCode().trim())
                .setMatRef(inventory.getInvMaterialCode().trim())
                .setThickness(inventory.getInvThick().floatValue())
                .setLength(inventory.getInvLong().floatValue())
                .setWidth(inventory.getInvWide().floatValue())
                .setQuantity(toQuantity(inventory))
                .setUdata1(ScjinggongInventoryWarehousePolicy.normalize(inventory.getCWhCode()))
                .setUdata2("")
                .setUdata3("");
    }

    private int toQuantity(ScjinggongInventoryDirectDTO inventory) {
        try {
            return inventory.getIQuantity().intValueExact();
        } catch (ArithmeticException exception) {
            throw new BusinessException(
                    "物料%s的库存数量无法转换为整数：%s"
                            .formatted(displayInventoryCode(inventory), inventory.getIQuantity())
            );
        }
    }

    private void validateRanges(ScjinggongInventoryDirectPageQuery query) {
        validateRange("长度", query.getInvLongMin(), query.getInvLongMax());
        validateRange("宽度", query.getInvWideMin(), query.getInvWideMax());
        validateRange("厚度", query.getInvThickMin(), query.getInvThickMax());
    }

    private void validateRange(String fieldName, BigDecimal min, BigDecimal max) {
        if (min != null && max != null && min.compareTo(max) > 0) {
            throw new BusinessException(fieldName + "范围的最小值不能大于最大值");
        }
    }

    private ScjinggongInventoryDirectVO toVO(ScjinggongMesInventory entity) {
        ScjinggongInventoryDirectVO vo = new ScjinggongInventoryDirectVO();
        vo.setInvClassCode(entity.getInvClassCode());
        vo.setInvClassName(entity.getInvClassName());
        vo.setInvCode(entity.getInvCode());
        vo.setInvName(entity.getInvName());
        vo.setInvSize(entity.getInvSize());
        vo.setInvMaterialCode(entity.getInvMaterialCode());
        vo.setInvDensity(entity.getInvDensity());
        vo.setInvLong(entity.getInvLong());
        vo.setInvThick(entity.getInvThick());
        vo.setInvWide(entity.getInvWide());
        return vo;
    }

    private String displayInventoryCode(ScjinggongInventoryDirectDTO inventory) {
        return StringUtils.hasText(inventory.getInvCode()) ? inventory.getInvCode().trim() : "<空物料编码>";
    }

    private String displayWarehouseCode(ScjinggongInventoryDirectDTO inventory) {
        return StringUtils.hasText(inventory.getCWhCode()) ? inventory.getCWhCode().trim() : "未加载仓库";
    }

    private String normalizeInventoryCode(String inventoryCode) {
        return inventoryCode.trim().toUpperCase(Locale.ROOT);
    }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }
}
