package com.zhurong.platform.custom.erp.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.core.lantek.dto.MmnnMmoo00000300DTO;
import com.zhurong.platform.custom.entity.DisMmnnMmoo00000200;
import com.zhurong.platform.custom.entity.MmnnMmoo00000300;
import com.zhurong.platform.custom.erp.convert.ViPmOrderlConvert;
import com.zhurong.platform.custom.erp.dto.ViPmOrderlDTO;
import com.zhurong.platform.custom.erp.entity.ViPmOrderl;
import com.zhurong.platform.custom.erp.service.IViPmOrderlService;
import com.zhurong.platform.custom.erp.vo.ViPmOrderlVO;
import com.zhurong.platform.custom.service.IDisMmnnMmoo00000200Service;
import com.zhurong.platform.custom.service.IMmnnMmoo00000300Service;
import com.zhurong.platform.custom.web.BaseController;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/erp/vi/pm/orderl")
@Hidden
public class ViPmOrderlController extends BaseController {

    private final ViPmOrderlConvert viPmOrderlConvert;
    private final IViPmOrderlService viPmOrderlService;
    private final IMmnnMmoo00000300Service mmnnMmoo00000300Service;
    private final IDisMmnnMmoo00000200Service disMmnnMmoo00000200Service;

    @GetMapping("/ping")
    public String ping() {
        return "pong-from-custom";
    }

    @GetMapping("list")
    public ApiResponse<List<ViPmOrderlVO>> list(ViPmOrderlDTO dto) {
        ViPmOrderl entity = viPmOrderlConvert.toEntity(dto);
        List<ViPmOrderl> list = viPmOrderlService.list(Wrappers.lambdaQuery(entity));
        List<ViPmOrderlVO> vo = viPmOrderlConvert.toVO(list);
        return ApiResponse.success(vo);
    }

    @PostMapping("importToExpert")
    public ApiResponse<Boolean> importToExpert(@RequestBody ViPmOrderlDTO dto) {
       try{
           boolean b = viPmOrderlService.importToExpert(dto);
           return ApiResponse.success(b);
       }catch (Exception e){
           return ApiResponse.fail(e.getMessage());
       }
    }
    @PostMapping("specifiedToExpertJob")
    public ApiResponse<Boolean> specifiedToExpertJob(@RequestBody MmnnMmoo00000300DTO dto) {
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

           if (StringUtils.isBlank(company)){
               return ApiResponse.fail(String.format("所指定的作业%s，公司信息为空",dto.getDIS_JobRef()));
           }

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

           if (!company.equals(companys.getFirst())){
               return ApiResponse.fail(String.format("作业所在公司【%s】，释放件所属公司【%s】，公司不一致，无法指定",company, companys.getFirst()));
           }

           boolean update = mmnnMmoo00000300Service.update(Wrappers.lambdaUpdate(MmnnMmoo00000300.class)
                   .set(MmnnMmoo00000300::getDIS_JobRef, dto.getDIS_JobRef())
                   .in(MmnnMmoo00000300::getRecID, dto.getRecIds()));

           return ApiResponse.success(update);
       }catch (Exception e){
           return ApiResponse.fail(e.getMessage());
       }
    }
}
