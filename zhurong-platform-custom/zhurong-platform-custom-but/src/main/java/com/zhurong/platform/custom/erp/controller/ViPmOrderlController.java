package com.zhurong.platform.custom.erp.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.constant.NestConstant;
import com.zhurong.platform.base.model.PageFactory;
import com.zhurong.platform.core.lantek.dto.MmnnMmoo00000300DTO;
import com.zhurong.platform.core.lantek.dto.MmnnMmoo00000300PageQuery;
import com.zhurong.platform.core.lantek.vo.MmnnMmoo00000300VO;
import com.zhurong.platform.custom.convert.MmnnMmoo00000300Convert;
import com.zhurong.platform.custom.entity.DisMmnnMmoo00000200;
import com.zhurong.platform.custom.entity.MmnnMmoo00000300;
import com.zhurong.platform.custom.entity.PprrPprr00000100;
import com.zhurong.platform.custom.erp.convert.ViPmOrderlConvert;
import com.zhurong.platform.custom.erp.dto.ViPmOrderlDTO;
import com.zhurong.platform.custom.erp.entity.ViPmOrderl;
import com.zhurong.platform.custom.erp.service.IViPmOrderl2Service;
import com.zhurong.platform.custom.erp.service.IViPmOrderlService;
import com.zhurong.platform.custom.erp.vo.ViPmOrderlVO;
import com.zhurong.platform.custom.service.IDisMmnnMmoo00000200Service;
import com.zhurong.platform.custom.service.IMmnnMmoo00000300Service;
import com.zhurong.platform.custom.service.IPprrPprr00000100Service;
import com.zhurong.platform.custom.web.BaseController;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/erp/vi/pm/orderl")
@Hidden
public class ViPmOrderlController extends BaseController {

    private final ViPmOrderlConvert viPmOrderlConvert;
    private final IViPmOrderlService viPmOrderlService;
    private final IViPmOrderl2Service viPmOrderl2Service;
    private final IMmnnMmoo00000300Service mmnnMmoo00000300Service;
    private final IPprrPprr00000100Service pprrPprr00000100Service;
    private final IDisMmnnMmoo00000200Service disMmnnMmoo00000200Service;
    private final MmnnMmoo00000300Convert mmnnMmoo00000300Convert;

    @GetMapping("/ping")
    public String ping() {
        return "pong-from-custom";
    }

    @GetMapping("list")
    public ApiResponse<List<ViPmOrderlVO>> list(ViPmOrderlDTO dto) {
        ViPmOrderl entity = viPmOrderlConvert.toEntity(dto);
        List<ViPmOrderl> list = viPmOrderlService.list(Wrappers.lambdaQuery(entity));
        List<ViPmOrderl> list2 = viPmOrderl2Service.list(Wrappers.lambdaQuery(entity));
        list.addAll(list2);
        List<ViPmOrderlVO> vo = viPmOrderlConvert.toVO(list);
        return ApiResponse.success(vo);
    }

    @PostMapping("importToExpert")
    public ApiResponse<String> importToExpert(@RequestBody ViPmOrderlDTO dto) {
       try{
           int count = viPmOrderlService.importToExpert(dto);
           return ApiResponse.success(count > 0 ? String.format("成功导入%s条", count) : null);
       }catch (Exception e){
           return ApiResponse.fail(e.getMessage());
       }
    }
    @PostMapping("specifiedToExpertJob")
    public ApiResponse<String> specifiedToExpertJob(@RequestBody MmnnMmoo00000300DTO dto) {
       try{
           if (StringUtils.isBlank(dto.getDIS_JobRef())){
               return ApiResponse.fail("作业编码不能为空");
           }
           if (CollectionUtils.isEmpty(dto.getRecIds())){
               return ApiResponse.fail("工单id不能为空");
           }
           DisMmnnMmoo00000200 job = disMmnnMmoo00000200Service.getOne(Wrappers.lambdaQuery(DisMmnnMmoo00000200.class)
                   .eq(DisMmnnMmoo00000200::getJobRef, dto.getDIS_JobRef()));

           if (job == null){
               return ApiResponse.fail(String.format("所指定的作业%s，为空",dto.getDIS_JobRef()));
           }
           String company = job.getUData1();

//           if (StringUtils.isBlank(company)){
//               return ApiResponse.fail(String.format("所指定的作业%s，公司信息为空",dto.getDIS_JobRef()));
//           }

           List<MmnnMmoo00000300> mmnnMmoo00000300s = mmnnMmoo00000300Service.list(Wrappers.lambdaQuery(MmnnMmoo00000300.class)
                   .in(MmnnMmoo00000300::getRecID, dto.getRecIds()));

           List<String> companys = mmnnMmoo00000300s.stream()
                   .map(MmnnMmoo00000300::getDescrip)
                   .filter(StringUtils::isNotBlank)
                   .distinct().toList();

           List<String> emptyCompanyDatas = mmnnMmoo00000300s.stream()
                   .map(MmnnMmoo00000300::getDescrip)
                   .filter(StringUtils::isBlank)
                   .distinct().toList();
           if (CollectionUtils.isNotEmpty(emptyCompanyDatas)){
               return ApiResponse.fail(String.format("所指定的生产订单数据公司信息为空，请检查，%s", String.join(",", emptyCompanyDatas)));
           }

           if (CollectionUtils.isEmpty(companys)){
               return ApiResponse.fail("所指定的生产订单数据公司信息为空，请检查");
           }
           if (companys.size() > 1){
               return ApiResponse.fail(String.format("不能同时指定多家不同公司的生产订单数据%s",String.join(",", companys)));
           }

//           if (!company.equals(companys.getFirst())){
//               return ApiResponse.fail(String.format("作业所在公司【%s】，释放件所属公司【%s】，公司不一致，无法指定",company, companys.getFirst()));
//           }

           LambdaUpdateWrapper<MmnnMmoo00000300> wrapper = Wrappers.lambdaUpdate(MmnnMmoo00000300.class)
                   .set(MmnnMmoo00000300::getDIS_JobRef, dto.getDIS_JobRef())
                   .set(MmnnMmoo00000300::getMState, NestConstant.MState.PROGRAMMING)
                   .in(MmnnMmoo00000300::getRecID, dto.getRecIds());

           if (StringUtils.isBlank(company)){
               wrapper.set(MmnnMmoo00000300::getDescrip, companys.getFirst());
           }

           boolean update = mmnnMmoo00000300Service.update(wrapper);

           return ApiResponse.success(update ? String.format("成功导入%s条", dto.getRecIds()) : null);
       }catch (Exception e){
           return ApiResponse.fail(e.getMessage());
       }
    }

    /**
     * 获取释放件
     * @return
     */
    @GetMapping("getReleaseItem")
    public ApiResponse
            <PageResponse
                    <MmnnMmoo00000300VO>> getReleaseItem(MmnnMmoo00000300PageQuery pageQuery){
        LambdaQueryWrapper<MmnnMmoo00000300> wrapper =
                Wrappers.lambdaQuery(mmnnMmoo00000300Convert.toEntity(pageQuery));

        wrapper.orderByAsc(MmnnMmoo00000300::getRecID);

        if (CollectionUtils.isNotEmpty(pageQuery.getOrdRefs())) {
            wrapper.in(MmnnMmoo00000300::getOrdRef, pageQuery.getOrdRefs());
        }
        if (CollectionUtils.isNotEmpty(pageQuery.getMnoRefs())) {
            wrapper.in(MmnnMmoo00000300::getMnORef, pageQuery.getMnoRefs());
        }
        if (pageQuery.getQueryRelease() != null && pageQuery.getQueryRelease()){
            wrapper.eq(MmnnMmoo00000300::getMState, NestConstant.MState.NOT_STARTED);
        }
        Page<MmnnMmoo00000300> page = mmnnMmoo00000300Service.page(
                PageFactory.build(pageQuery),
                wrapper
        );

        List
                <MmnnMmoo00000300VO> voList = page.getRecords()
                .stream()
                .map(mmnnMmoo00000300Convert::toVO)
                .toList();

        List<String> prdRefs = voList.stream().map(MmnnMmoo00000300VO::getPrdRef).distinct().toList();

        if (CollectionUtils.isNotEmpty(prdRefs)){
            List<PprrPprr00000100> pprrs = pprrPprr00000100Service.list(Wrappers.lambdaQuery(PprrPprr00000100.class)
                    .in(PprrPprr00000100::getPrdRef, prdRefs));

            Map<String, PprrPprr00000100> pprrMap = pprrs.stream()
                    .collect(Collectors.toMap(PprrPprr00000100::getPrdRef, Function.identity()));
            voList.forEach(vo -> {
                PprrPprr00000100 pprr = pprrMap.getOrDefault(vo.getPrdRef(),
                        new PprrPprr00000100().setPrdName(vo.getPrdNameDst()));
                vo.setPrdNameDst(pprr.getPrdName());
            });
        }


        PageResponse
                <MmnnMmoo00000300VO> response = new PageResponse<>(
                voList,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );

        return ApiResponse.success(response);
    }
}
