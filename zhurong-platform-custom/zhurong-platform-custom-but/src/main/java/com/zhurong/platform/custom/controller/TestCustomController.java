package com.zhurong.platform.custom.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.core.lantek.constants.LantekTableDefaultValue;
import com.zhurong.platform.core.lantek.dto.WwhhIivv00000100DTO;
import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000100DTO;
import com.zhurong.platform.custom.entity.PprrPprr00000100;
import com.zhurong.platform.custom.entity.WwhhIivv00000100;
import com.zhurong.platform.custom.erp.entity.ViPmPrjplanLantek;
import com.zhurong.platform.custom.erp.service.IViPmPrjplanLantek2Service;
import com.zhurong.platform.custom.feign.WwhhIivv00000100FeignClient;
import com.zhurong.platform.custom.feign.WwhhWwhh00000100FeignClient;
import com.zhurong.platform.custom.service.IPprrPprr00000100Service;
import com.zhurong.platform.custom.service.IWwhhIivv00000100Service;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/custom")
@Hidden
@Slf4j
public class TestCustomController {
    private final IPprrPprr00000100Service pprrPprr00000100Service;
    private final WwhhWwhh00000100FeignClient wwhhWwhh00000100FeignClient;
    private final WwhhIivv00000100FeignClient wwhhIivv00000100FeignClient;
    private final IWwhhIivv00000100Service iWwhhIivv00000100Service;
    private final IViPmPrjplanLantek2Service viPmPrjplanLantek2Service;

    @GetMapping("/ping")
    public String ping() {
        return "pong-from-custom";
    }

    @GetMapping("/wait_test")
    public String waitTest() {
        try {
            TimeUnit.MINUTES.sleep(8);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "interrupted";
        }
        return "pong-from-custom";
    }

    @GetMapping("/importRemnantTemp")
    public Integer importRemnantTemp() {
        Integer count = 0;

        List<ViPmPrjplanLantek> viPmPrjplanLanteks =
                viPmPrjplanLantek2Service.list(
                        new QueryWrapper<ViPmPrjplanLantek>()
                                .isNotNull("\"使用余料编码\"")
                );

//        Map<String, ViPmPrjplanLantek> getMap = viPmPrjplanLanteks.stream().collect(Collectors.toMap(ViPmPrjplanLantek::getRemShtRef, Function.identity()));

        List<PprrPprr00000100> remnants = pprrPprr00000100Service.list(Wrappers.lambdaQuery(PprrPprr00000100.class)
                .eq(PprrPprr00000100::getDIS_IsRemnant, true)
                .select(PprrPprr00000100::getPrdRef, PprrPprr00000100::getDIS_CamQuan));

        Map<String, PprrPprr00000100> pprrMap = remnants.stream().collect(Collectors.toMap(PprrPprr00000100::getPrdRef, Function.identity()));

        List<WwhhIivv00000100> list = iWwhhIivv00000100Service.list(Wrappers.lambdaQuery(WwhhIivv00000100.class)
                .select(WwhhIivv00000100::getPrdRef));

        Map<String, Integer> existing = list.stream().collect(Collectors.toMap(WwhhIivv00000100::getPrdRef, p -> 1, (cur, old) -> cur));

        List<WwhhIivv00000100> inserts = new ArrayList<>();


        WwhhIivv00000100 last = iWwhhIivv00000100Service.getOne(Wrappers.lambdaQuery(WwhhIivv00000100.class)
                .orderByDesc(WwhhIivv00000100::getRecID), false);
        Integer maxRecID = last.getRecID();
        maxRecID = maxRecID + 1;
        log.info("查询到余料条数：{}",viPmPrjplanLanteks.size());
        for (int i = 0; i < viPmPrjplanLanteks.size(); i++) {
            ViPmPrjplanLantek viPmPrjplanLantek = viPmPrjplanLanteks.get(i);
            PprrPprr00000100 pprrPprr00000100 = pprrMap.get(viPmPrjplanLantek.getRemShtRef());
//            if (!getMap.containsKey(pprrPprr00000100.getPrdRef())){
//                continue;
//            }
            if (existing.containsKey(pprrPprr00000100.getPrdRef())){
                log.info("已存在：{}",pprrPprr00000100.getPrdRef());
                continue;
            }
            WwhhIivv00000100 wwhhIivv00000100 = new WwhhIivv00000100()
                    .setWrhRef(viPmPrjplanLantek.getWhsName())
                    .setPrdRef(viPmPrjplanLantek.getRemShtRef())
                    .setPrdName("")
                    .setUCtName("UNT")
                    .setUntName("unt")
                    .setStockQ(0D)
                    .setAllocatedQ(pprrPprr00000100.getDIS_CamQuan().doubleValue())
                    .setOnOrderQ(0D)
                    .setPendingQ(0D)
                    .setUpdMethod(3)
                    .setIsDefault((byte) 1)
                    .setMinStock(1D)
                    .setMinOrder(0D)
                    .setMaxOrder(0D)
                    .setMultOrder(0D)
                    .setStrategy(0)
                    .setLocDefault(viPmPrjplanLantek.getLocName())
                    .setWeight(0D)
                    .setRWeight(0D)
                    .setGLS_Var1("")
                    .setGLS_Var2("")
                    .setGLS_Var3("")
                    .setGLS_Var4("")
                    .setGLS_Var5("")
                    .setRecState(0)
                    .setCrtDate(LocalDateTime.now())
                    .setLastDate(LocalDateTime.now())
                    .setCrtUser(LantekTableDefaultValue.User)
                    .setLastUser(LantekTableDefaultValue.User)
                    .setOwner(LantekTableDefaultValue.User)
                    .setRecEnt(LantekTableDefaultValue.Ment)
                    .setRecOU(LantekTableDefaultValue.Ment)
                    .setRecSec(0)
                    .setCntID(-1)
                    .setRecID(maxRecID);
            inserts.add(wwhhIivv00000100);
            maxRecID++;
        }

        for (int i = 0; i < viPmPrjplanLanteks.size(); i++) {
            ViPmPrjplanLantek viPmPrjplanLantek = viPmPrjplanLanteks.get(i);
            boolean update = pprrPprr00000100Service.update(Wrappers.lambdaUpdate(PprrPprr00000100.class)
                    .set(PprrPprr00000100::getDIS_UData1_Sht, viPmPrjplanLantek.getBatchNumber())
                    .eq(PprrPprr00000100::getPrdRef, viPmPrjplanLantek.getRemShtRef()));
            log.info("余料批次号更新：{}，{}",update,viPmPrjplanLantek.getRemShtRef());
        }

        boolean b = iWwhhIivv00000100Service.saveBatch(inserts);

        log.info("创建结果：{}",b);

        return inserts.size();
    }
    @GetMapping("/importRemnantTemp2")
    public Integer importRemnantTemp2() {
        AtomicReference<Integer> count = new AtomicReference<>(0);

        List<ViPmPrjplanLantek> viPmPrjplanLanteks =
                viPmPrjplanLantek2Service.list(
                        new QueryWrapper<ViPmPrjplanLantek>()
                                .isNotNull("\"使用余料编码\"")
                );

        //创建仓库和库位
        record WarehouserGroupKey(String whsName,String locName){}
        Map<WarehouserGroupKey, List<ViPmPrjplanLantek>> createWarehousers = viPmPrjplanLanteks.stream()
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
            count.getAndSet(count.get() + 1);
            log.info("库存库位创建成功：{}-{}",it.whsName, it.locName);
        });
        return count.get();
    }

    @GetMapping("/bind_warehouse")
    public String bindWarehouse(@RequestParam String whsName,@RequestParam String locName,@RequestParam String prdRef) {
        WwhhWwhh00000100DTO wwhhWwhh00000100DTO = new WwhhWwhh00000100DTO();
        wwhhWwhh00000100DTO.setWrhRef(whsName);
        wwhhWwhh00000100DTO.setLocRef(locName);
        ApiResponse<Boolean> apiResponse1 = wwhhWwhh00000100FeignClient.createWarehouseAndLocation(wwhhWwhh00000100DTO);

        if (apiResponse1.data() == null || !apiResponse1.data()){
            return apiResponse1.message();
        }
        //钢板绑定库存
        WwhhIivv00000100DTO wwhhIivv00000100DTO = new WwhhIivv00000100DTO();
        wwhhIivv00000100DTO.setWrhRef(whsName);
        wwhhIivv00000100DTO.setLocDefault(locName);
        wwhhIivv00000100DTO.setPrdRef(prdRef);
        wwhhIivv00000100DTO.setAllocatedQ(66D);
        ApiResponse<Boolean> apiResponse2 = wwhhIivv00000100FeignClient.sheetMetalBinding(wwhhIivv00000100DTO);

        if (apiResponse2.data() == null || !apiResponse2.data()){
            return apiResponse2.message();
        }
        return "TRUE";
    }

    @GetMapping("/update_bind_warehouse_location")
    public String updateBindWarehouseLocation(@RequestParam String whsName,@RequestParam String locName,@RequestParam String prdRef) {
        WwhhWwhh00000100DTO wwhhWwhh00000100DTO = new WwhhWwhh00000100DTO();
        wwhhWwhh00000100DTO.setWrhRef(whsName);
        wwhhWwhh00000100DTO.setLocRef(locName);
        ApiResponse<Boolean> apiResponse1 = wwhhWwhh00000100FeignClient.createWarehouseAndLocation(wwhhWwhh00000100DTO);

        if (apiResponse1.data() == null || !apiResponse1.data()){
            return apiResponse1.message();
        }
        WwhhIivv00000100DTO wwhhIivv00000100DTO = new WwhhIivv00000100DTO();
        wwhhIivv00000100DTO.setWrhRef(whsName);
        wwhhIivv00000100DTO.setLocDefault(locName);
        wwhhIivv00000100DTO.setPrdRef(prdRef);
        ApiResponse<Boolean> apiResponse2 = wwhhIivv00000100FeignClient.sheetMetalBindingUpdate(wwhhIivv00000100DTO);

        if (apiResponse2.data() == null || !apiResponse2.data()){
            return apiResponse2.message();
        }
        return "TRUE";
    }
}
