package com.zhurong.platform.custom.erp.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.custom.erp.convert.ViPmOrderlConvert;
import com.zhurong.platform.custom.erp.dto.ViPmOrderlDTO;
import com.zhurong.platform.custom.erp.entity.ViPmOrderl;
import com.zhurong.platform.custom.erp.service.IViPmOrderlService;
import com.zhurong.platform.custom.erp.vo.ViPmOrderlVO;
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

    @GetMapping("/ping")
    public String ping() {
        return "pong-from-custom";
    }

    @GetMapping("list")
    public ApiResponse<List<ViPmOrderlVO>> list() {
        List<ViPmOrderl> list = viPmOrderlService.list();
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
}
