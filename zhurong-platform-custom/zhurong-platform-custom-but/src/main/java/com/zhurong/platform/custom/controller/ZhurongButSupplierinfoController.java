package com.zhurong.platform.custom.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.custom.convert.ZhurongButSupplierinfoConvert;
import com.zhurong.platform.custom.dto.ZhurongButSupplierinfoDTO;
import com.zhurong.platform.custom.dto.ZhurongButSupplierinfoPageQuery;
import com.zhurong.platform.custom.entity.PprrPprr00000100;
import com.zhurong.platform.custom.entity.ZhurongButSupplierinfo;
import com.zhurong.platform.custom.erp.service.IViPmPrjplanLantekService;
import com.zhurong.platform.custom.service.IPprrPprr00000100Service;
import com.zhurong.platform.custom.service.IZhurongButSupplierinfoService;
import com.zhurong.platform.custom.vo.ZhurongButSupplierinfoVO;
import com.zhurong.platform.custom.web.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/zhurongButSupplierinfo")
public class ZhurongButSupplierinfoController extends BaseController {

    private final ZhurongButSupplierinfoConvert convert;
    private final IZhurongButSupplierinfoService service;
    private final IViPmPrjplanLantekService viPmPrjplanLantekService;
    private final IPprrPprr00000100Service pprrPprr00000100Service;

    @GetMapping("/page")
    public ApiResponse<PageResponse<ZhurongButSupplierinfoVO>> page(ZhurongButSupplierinfoPageQuery pageQuery) {

        LambdaQueryWrapper<ZhurongButSupplierinfo> wrapper =
                Wrappers.lambdaQuery(convert.toEntity(pageQuery));

        wrapper.orderByAsc(ZhurongButSupplierinfo::getCreatedAt);

        Page<ZhurongButSupplierinfo> page = service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <ZhurongButSupplierinfoVO> voList = page.getRecords()
                .stream()
                .map(convert::toVO)
                .toList();

        PageResponse
                <ZhurongButSupplierinfoVO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }

    /*
     * 同步供应商信息
     */
    @PostMapping("/sync")
    public ApiResponse<Boolean> syncSupplierInfo(ZhurongButSupplierinfoDTO dto) {
        boolean update1 = viPmPrjplanLantekService.syncSupplierInfo();
        return ApiResponse.success(update1);
    }
    /*
     *
     */
    @PostMapping("/updateUdata")
    public ApiResponse<Integer> updateUdata(@RequestBody ZhurongButSupplierinfoDTO dto) {
        int update2 = viPmPrjplanLantekService.updateInventoryReferences(dto.getIds());
        return ApiResponse.success(update2);
    }
    /*
     *
     */
    @PutMapping("/clearExistingInventory")
    public ApiResponse<Boolean> clearExistingInventory() {
        boolean update = pprrPprr00000100Service.update(Wrappers.lambdaUpdate(PprrPprr00000100.class)
                .set(PprrPprr00000100::getDIS_CamQuan, 0)
                .eq(PprrPprr00000100::getDIS_PClass, 51)
                .ne(PprrPprr00000100::getDIS_IsRemnant, 1)
        );
        return ApiResponse.success(update);
    }
}
