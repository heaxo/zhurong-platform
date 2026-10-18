package com.zhurong.platform.custom.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.base.clientimport.dto.RawMaterialRequest;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.custom.convert.ZhurongScjinggongSteelplateinventoryConvert;
import com.zhurong.platform.custom.dto.U8InventoryResponse;
import com.zhurong.platform.custom.dto.ZhurongScjinggongSteelplateinventoryDTO;
import com.zhurong.platform.custom.entity.ZhurongScjinggongSteelplateinventory;
import com.zhurong.platform.custom.mapper.ZhurongScjinggongSteelplateinventoryMapper;
import com.zhurong.platform.custom.mes.entity.ScjinggongMesInventory;
import com.zhurong.platform.custom.mes.service.IScjinggongMesInventoryService;
import com.zhurong.platform.custom.model.BaseEntity;
import com.zhurong.platform.custom.service.IZhurongScjinggongSteelplateinventoryService;
import com.zhurong.platform.custom.service.ScjinggongExpertInventoryImportService;
import com.zhurong.platform.custom.service.ScjinggongInventoryWarehousePolicy;
import com.zhurong.platform.custom.vo.ZhurongScjinggongSteelplateinventoryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
@DS("lantek")
public class ZhurongScjinggongSteelplateinventoryServiceImpl
        extends ServiceImpl<ZhurongScjinggongSteelplateinventoryMapper, ZhurongScjinggongSteelplateinventory>
        implements IZhurongScjinggongSteelplateinventoryService {

    private final ZhurongScjinggongSteelplateinventoryConvert convert;
    private final IScjinggongMesInventoryService mesInventoryService;
    private final ScjinggongExpertInventoryImportService expertInventoryImportService;


    @Override
    public ZhurongScjinggongSteelplateinventoryVO getVOById(Long id) {
        ZhurongScjinggongSteelplateinventory entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(ZhurongScjinggongSteelplateinventoryDTO dto) {
        ZhurongScjinggongSteelplateinventory entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, ZhurongScjinggongSteelplateinventoryDTO dto) {
        ZhurongScjinggongSteelplateinventory entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }

    @Override
    public Boolean syncFromU8(List<U8InventoryResponse> inventoryList) {
        if (CollectionUtils.isEmpty(inventoryList)) {
            return false;
        }

        Map<String, U8InventoryResponse> u8InventoryMap = new LinkedHashMap<>();
        for (U8InventoryResponse inventory : inventoryList.stream()
                .filter(it -> it != null && ScjinggongInventoryWarehousePolicy.isAllowed(it.getCWhCode()))
                .toList()) {
            if (inventory == null || !StringUtils.hasText(inventory.getCInvCode())) {
                throw new BusinessException("U8库存数据中的物料编码不能为空");
            }
            String inventoryCode = normalizeInventoryCode(inventory.getCInvCode());
            U8InventoryResponse previous = u8InventoryMap.put(inventoryCode, inventory);
            if (previous != null) {
                log.warn("U8库存查询返回了重复物料编码，使用最后一条数据：{}", inventory.getCInvCode());
            }
        }

        List<String> inventoryCodes = u8InventoryMap.values().stream()
                .map(U8InventoryResponse::getCInvCode)
                .map(String::trim)
                .toList();
        Map<String, ScjinggongMesInventory> mesInventoryMap = toMesInventoryMap(
                mesInventoryService.listByInventoryCodes(inventoryCodes)
        );

        List<String> missingInventoryCodes = u8InventoryMap.keySet().stream()
                .filter(inventoryCode -> !mesInventoryMap.containsKey(inventoryCode))
                .map(inventoryCode -> u8InventoryMap.get(inventoryCode).getCInvCode())
                .toList();
        if (CollectionUtils.isNotEmpty(missingInventoryCodes)) {
            throw new BusinessException(
                    "MES物料视图V_BD_Inventory中未找到物料：" + String.join(",", missingInventoryCodes)
            );
        }

        List<ZhurongScjinggongSteelplateinventory> existingInventoryList = listByIn(
                Wrappers.lambdaQuery(ZhurongScjinggongSteelplateinventory.class),
                ZhurongScjinggongSteelplateinventory::getShtRef,
                inventoryCodes
        );
        Map<String, ZhurongScjinggongSteelplateinventory> existingInventoryMap = toExistingInventoryMap(
                existingInventoryList
        );

        List<ZhurongScjinggongSteelplateinventory> entities = u8InventoryMap.entrySet().stream()
                .map(entry -> toSteelPlateInventory(
                        entry.getValue(),
                        mesInventoryMap.get(entry.getKey()),
                        existingInventoryMap.get(entry.getKey())
                ))
                .toList();
        boolean saved = saveOrUpdateBatch(entities);
        if (!saved) {
            throw new BusinessException("钢板库存保存失败");
        }
        return true;
    }

    private Map<String, ScjinggongMesInventory> toMesInventoryMap(List<ScjinggongMesInventory> inventoryList) {
        Map<String, ScjinggongMesInventory> result = new LinkedHashMap<>();
        for (ScjinggongMesInventory inventory : inventoryList) {
            if (!StringUtils.hasText(inventory.getInvCode())) {
                continue;
            }
            String inventoryCode = normalizeInventoryCode(inventory.getInvCode());
            if (result.putIfAbsent(inventoryCode, inventory) != null) {
                throw new BusinessException(
                        "MES物料视图V_BD_Inventory中存在重复物料编码：" + inventory.getInvCode()
                );
            }
        }
        return result;
    }

    private Map<String, ZhurongScjinggongSteelplateinventory> toExistingInventoryMap(
            List<ZhurongScjinggongSteelplateinventory> inventoryList
    ) {
        Map<String, ZhurongScjinggongSteelplateinventory> result = new LinkedHashMap<>();
        for (ZhurongScjinggongSteelplateinventory inventory : inventoryList) {
            String inventoryCode = normalizeInventoryCode(inventory.getShtRef());
            if (result.putIfAbsent(inventoryCode, inventory) != null) {
                throw new BusinessException("钢板库存表中存在重复物料编码：" + inventory.getShtRef());
            }
        }
        return result;
    }

    private ZhurongScjinggongSteelplateinventory toSteelPlateInventory(
            U8InventoryResponse u8Inventory,
            ScjinggongMesInventory mesInventory,
            ZhurongScjinggongSteelplateinventory existingInventory
    ) {
        validateMesInventory(mesInventory);

        ZhurongScjinggongSteelplateinventory entity = existingInventory;
        if (entity == null) {
            entity = new ZhurongScjinggongSteelplateinventory();
            entity.setInvalidState(false);
        }

        entity.setShtRef(u8Inventory.getCInvCode().trim());
        entity.setMatRef(mesInventory.getInvMaterialCode().trim());
        entity.setThickness(mesInventory.getInvThick().floatValue());
        entity.setLength(mesInventory.getInvLong().floatValue());
        entity.setWidth(mesInventory.getInvWide().floatValue());
        entity.setQuantity(toQuantity(u8Inventory));
        entity.setUdata1(u8Inventory.getCWhCode());
        entity.setUdata4(Objects.toString(u8Inventory.getItemid(), null));
        entity.setUdata5(Objects.toString(u8Inventory.getAutoId(), null));
        entity.setUdata6(Objects.toString(u8Inventory.getISoType(), null));
        entity.setUdata7(u8Inventory.getIsodid());
        entity.setUdata8(u8Inventory.getCsocode());
        entity.setUdata9(u8Inventory.getEnumname());
        entity.setUdata10(u8Inventory.getCdemandmemo());
        entity.setUdata11(Objects.toString(u8Inventory.getIsoseq(), null));
        entity.setUdata12(mesInventory.getInvName());
        entity.setUdata13(mesInventory.getInvClassName());
        entity.setUdata14(mesInventory.getInvClassCode());
        entity.setUdata15(mesInventory.getInvSize());
        entity.setUdata16(toPlainString(mesInventory.getInvDensity()));
        return entity;
    }

    private void validateMesInventory(ScjinggongMesInventory inventory) {
        List<String> missingFields = new ArrayList<>();
        if (!StringUtils.hasText(inventory.getInvMaterialCode())) {
            missingFields.add("Inv_MaterialCode");
        }
        if (inventory.getInvLong() == null) {
            missingFields.add("INV_LONG");
        }
        if (inventory.getInvThick() == null) {
            missingFields.add("INV_THICK");
        }
        if (inventory.getInvWide() == null) {
            missingFields.add("INV_WIDE");
        }
        if (CollectionUtils.isNotEmpty(missingFields)) {
            throw new BusinessException(
                    "MES物料视图中物料%s缺少必填字段：%s"
                            .formatted(inventory.getInvCode(), String.join(",", missingFields))
            );
        }
    }

    private Integer toQuantity(U8InventoryResponse inventory) {
        if (inventory.getIQuantity() == null) {
            throw new BusinessException("U8库存数据中物料%s的在库数量为空".formatted(inventory.getCInvCode()));
        }
        try {
            return inventory.getIQuantity().intValueExact();
        } catch (ArithmeticException exception) {
            throw new BusinessException(
                    "U8库存数据中物料%s的在库数量无法转换为整数：%s"
                            .formatted(inventory.getCInvCode(), inventory.getIQuantity())
            );
        }
    }

    private String normalizeInventoryCode(String inventoryCode) {
        return inventoryCode.trim().toUpperCase(Locale.ROOT);
    }

    private String toPlainString(BigDecimal value) {
        return value == null ? null : value.stripTrailingZeros().toPlainString();
    }

    @Override
    public Boolean importToExpert(ZhurongScjinggongSteelplateinventoryDTO dto) throws IOException {
        if (CollectionUtils.isEmpty(dto.getIds())){
            throw new BusinessException("钢板ID不能为空");
        }
        List<ZhurongScjinggongSteelplateinventory> list = list(Wrappers.lambdaQuery(ZhurongScjinggongSteelplateinventory.class)
                .in(BaseEntity::getId, dto.getIds()));
        List<RawMaterialRequest> requests = list.stream().map(it -> new RawMaterialRequest()
                .setPrdRef(it.getShtRef())
                .setMatRef(it.getMatRef())
                .setThickness(it.getThickness())
                .setLength(it.getLength())
                .setWidth(it.getWidth())
                .setQuantity(it.getQuantity())
                .setUdata1(it.getUdata1())
                .setUdata2(it.getUdata2())
                .setUdata3(it.getUdata3())
        ).toList();
        return expertInventoryImportService.importRawMaterials(requests);
    }
}
