package com.zhurong.platform.custom.sap.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Sets;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.base.masterlink.commands.SheetsCommand;
import com.zhurong.platform.base.masterlink.engine.XmlExportEngine;
import com.zhurong.platform.core.lantek.dto.WwhhIivv00000100DTO;
import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000100DTO;
import com.zhurong.platform.custom.entity.PpbbPpbb00000100;
import com.zhurong.platform.custom.entity.PprrPprr00000100;
import com.zhurong.platform.custom.entity.WwhhIivv00000200;
import com.zhurong.platform.custom.exception.MasterlinkImportException;
import com.zhurong.platform.custom.feign.WwhhIivv00000100FeignClient;
import com.zhurong.platform.custom.feign.WwhhWwhh00000100FeignClient;
import com.zhurong.platform.custom.sap.dto.AvaInventoryQtyDTO;
import com.zhurong.platform.custom.sap.entity.AvaInventoryQty;
import com.zhurong.platform.custom.sap.mapper.AvaInventoryQtyMapper;
import com.zhurong.platform.custom.sap.service.IAvaInventoryQtyService;
import com.zhurong.platform.custom.service.IPpbbPpbb00000100Service;
import com.zhurong.platform.custom.service.IPprrPprr00000100Service;
import com.zhurong.platform.custom.service.IWwhhIivv00000200Service;
import com.zhurong.platform.custom.util.MasterlinkTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/*
 * @Author zhurong
 * @Description ViPmOrderlServiceImpl
 * @Date 2026/3/29 15:52
 **/
@Service
@RequiredArgsConstructor
@Slf4j
@DS("sap")
public class AvaInventoryQtyServiceImpl extends ServiceImpl<AvaInventoryQtyMapper, AvaInventoryQty> implements IAvaInventoryQtyService {

    private final IPprrPprr00000100Service pprrPprr00000100Service;
    private final IPpbbPpbb00000100Service ppbbPpbb00000100Service;
    private final WwhhWwhh00000100FeignClient wwhhWwhh00000100FeignClient;
    private final WwhhIivv00000100FeignClient wwhhIivv00000100FeignClient;
    private final IWwhhIivv00000200Service wwhhIivv00000200Service;

    private final ReentrantLock exeLock = new ReentrantLock();

    @Override
    public boolean importInventory(AvaInventoryQtyDTO dto) {
        List<String> itemCodes = dto.getItemCodes();

        if (CollectionUtils.isEmpty(itemCodes)) {
            throw new BusinessException("钢板编码不能为空");
        }

        List<AvaInventoryQty> inventorys = list(Wrappers.lambdaQuery(AvaInventoryQty.class)
                .in(AvaInventoryQty::getItemCode, itemCodes));
        //合并相同钢板编码的数据（目前应该没有这种数据）
        Map<String, AvaInventoryQty> mergeMap = inventorys.stream().collect(Collectors.toMap(AvaInventoryQty::getItemCode,
                p -> p, (cur, old) -> cur.setQuantity(cur.getQuantity() + old.getQuantity()))
        );
        List<AvaInventoryQty> inserts = new ArrayList<>();
        List<AvaInventoryQty> updates = new ArrayList<>();
        List<AvaInventoryQty> list = mergeMap.entrySet().stream().map(Map.Entry::getValue).toList();

        boolean locked = false;

        try {
            locked = exeLock.tryLock(3, TimeUnit.MINUTES);
            if (!locked) {
                throw new BusinessException("系统繁忙，请稍后再试");
            }
            List<PprrPprr00000100> existsing = pprrPprr00000100Service.list(Wrappers.lambdaQuery(PprrPprr00000100.class)
                    .in(PprrPprr00000100::getPrdRef, itemCodes));

            //已存在的钢板，直接更新库存（累加）
            if (CollectionUtils.isNotEmpty(existsing)) {
                List<String> prdRefs = existsing.stream().map(PprrPprr00000100::getPrdRef).toList();
                updates.addAll(list.stream().filter(it -> prdRefs.contains(it.getItemCode())).toList());
                inserts.addAll(list.stream().filter(it -> !prdRefs.contains(it.getItemCode())).toList());
            } else {
                inserts.addAll(list);
            }
            //更新库存和导入库存
            if (CollectionUtils.isNotEmpty(updates)) {
                Map<String, Double> qtyMap = updates
                        .stream()
                        .collect(Collectors.toMap(AvaInventoryQty::getItemCode, AvaInventoryQty::getQuantity));
                existsing.forEach(it -> {
                    Double qty = qtyMap.get(it.getPrdRef());
//                    boolean update1 = pprrPprr00000100Service.update(Wrappers.lambdaUpdate(PprrPprr00000100.class)
//                            .set(PprrPprr00000100::getDIS_CamQuan, qty + it.getCurQuan())
//                            .eq(PprrPprr00000100::getPrdRef, it.getPrdRef()));
//                    log.info("库存更新，钢板编码：{}，原库存数量：{}，库存数量：{}，更新结果：{}", it.getPrdRef(), it.getCurQuan(), qty + it.getCurQuan(), update1);

                    //如果不使用覆盖值，需要查出WwhhIivv00000200现有库存数量，然后做累加，并注释掉下面的清零操作
                    boolean update2 = wwhhIivv00000200Service.update(Wrappers.lambdaUpdate(WwhhIivv00000200.class)
                            .set(WwhhIivv00000200::getStockQ, qty)
                            .eq(WwhhIivv00000200::getPrdRef, it.getPrdRef()));
                    log.info("库存模块更新，钢板编码：{}，库存数量：{}，更新结果：{}", it.getPrdRef(), qty + it.getCurQuan(), update2);
                    //如果是覆盖，需要将ppbb中的数量清零
                    boolean update3 = ppbbPpbb00000100Service.update(Wrappers.lambdaUpdate(PpbbPpbb00000100.class)
                            .set(PpbbPpbb00000100::getAllocatedQ, 0)
                            .set(PpbbPpbb00000100::getPendingQ, 0)
                            .set(PpbbPpbb00000100::getReceivedQ, 0)
                            .set(PpbbPpbb00000100::getRequiredQ, 0)
                            .set(PpbbPpbb00000100::getOnOrderQ, 0)
                            .eq(PpbbPpbb00000100::getPrdRef, it.getPrdRef())
                    );
                    log.info("已使用库存清零，钢板编码：{}，更新结果：{}", it.getPrdRef(), update3);

                });
            }

            if (CollectionUtils.isNotEmpty(inserts)) {
                List<SheetsCommand> sheets = inserts.stream().map(it -> new SheetsCommand()
                        .setPrdRef(it.getItemCode())
                        .setPrdName(it.getItemName())
                        .setDisMatRef(it.getUBeasZnr())
                        .setDisThickness(Float.parseFloat(it.getUHd()))
                        .setDisUData1Sht(it.getBatchNum())
                        .setDisCamQuan(it.getQuantity().intValue())
                        .setDisLength(Float.parseFloat(it.getLength()))
                        .setDisWidth(Float.parseFloat(it.getWidth()))
                        .setDisUData2Sht(it.getWhsName())).toList();
                XmlExportEngine engine = new XmlExportEngine();
                String xmlPath = engine.export(sheets);
                //执行导入
                MasterlinkTool.ExecResult execResult = MasterlinkTool.executeImport(xmlPath);

                if (!execResult.success()){
                    log.error(execResult.stderr());
                    throw new MasterlinkImportException(execResult.stderr());
                }

                Set<String> prdRefs = inserts.stream().map(AvaInventoryQty::getItemCode).collect(Collectors.toSet());
                //查询数据库获取结果（不从executeImport获取结果，因为套料软件提供的导入程序拿到结果需要解析日志文件）
                List<PprrPprr00000100> results = pprrPprr00000100Service.list(Wrappers.lambdaQuery(PprrPprr00000100.class)
                        .in(PprrPprr00000100::getPrdRef, prdRefs));
                //导入结果
                Set<String> exclude = results.stream().map(PprrPprr00000100::getPrdRef).collect(Collectors.toSet());
                Sets.SetView<String> difference = Sets.difference(prdRefs, exclude);

                if (!difference.isEmpty()) {
                    int successCount = exclude.size();
                    int failCount = difference.size();

                    String failedItems = String.join(",", difference);

                    throw new MasterlinkImportException(String.format(
                            "部分导入失败！成功数量: %d, 失败数量: %d, 未导入项: [%s]",
                            successCount,
                            failCount,
                            failedItems
                    ));
                }

                //创建仓库和库位
                record WarehouserGroupKey(String whsName,String locName){}
                Map<WarehouserGroupKey, List<AvaInventoryQty>> createWarehousers = list.stream()
                        .collect(Collectors.groupingBy(it -> new WarehouserGroupKey(it.getWhsName(), it.getLocName())));
                List<WarehouserGroupKey> warehouserGroupKeys = createWarehousers.keySet().stream().toList();
                warehouserGroupKeys.forEach(it -> {
                    WwhhWwhh00000100DTO wwhhWwhh00000100DTO = new WwhhWwhh00000100DTO();
                    wwhhWwhh00000100DTO.setWrhRef(it.whsName);
                    wwhhWwhh00000100DTO.setLocRef(it.locName);
                    ApiResponse<Boolean> apiResponse = wwhhWwhh00000100FeignClient.createWarehouseAndLocation(wwhhWwhh00000100DTO);
                    if (apiResponse.data() == null || !apiResponse.data()){
                        log.warn("库存库位创建失败：{}-{}，{}",it.whsName, it.locName, apiResponse.message());
                        return;
                    }
                    log.info("库存库位创建成功：{}-{}",it.whsName, it.locName);
                });
                list.forEach(it -> {
                    //钢板绑定库存
                    WwhhIivv00000100DTO wwhhIivv00000100DTO = new WwhhIivv00000100DTO();
                    wwhhIivv00000100DTO.setWrhRef(it.getWhsName());
                    wwhhIivv00000100DTO.setLocDefault(it.getLocName());
                    wwhhIivv00000100DTO.setPrdRef(it.getItemCode());
                    wwhhIivv00000100DTO.setAllocatedQ(it.getQuantity());
                    ApiResponse<Boolean> apiResponse = wwhhIivv00000100FeignClient.sheetMetalBinding(wwhhIivv00000100DTO);

                    if (apiResponse.data() == null || !apiResponse.data()){
                        log.warn("钢板库存库位绑定失败：{}-{}，{}，{}",it.getWhsName(), it.getLocName(), it.getItemCode(), apiResponse.message());
                        return;
                    }
                    log.info("钢板库存库位绑定成功：{}-{}，{}",it.getWhsName(), it.getLocName(), it.getItemCode());
                });


                return true;
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new BusinessException("获取锁被中断");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException(String.format("库存导入异常中断：%s", e.getMessage()));
        } finally {
            if (locked) {
                exeLock.unlock();
            }
        }
        return false;
    }
}
