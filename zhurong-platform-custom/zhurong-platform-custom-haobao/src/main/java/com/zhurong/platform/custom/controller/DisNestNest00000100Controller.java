package com.zhurong.platform.custom.controller;

import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.core.lantek.api.IDisNestNest00000100Api;
import com.zhurong.platform.custom.dto.LabelDataQueryDTO;
import com.zhurong.platform.custom.service.IDisNestNest00000100Service;
import com.zhurong.platform.custom.service.NestService;
import com.zhurong.platform.custom.service.impl.ProductLabelExcelService;
import com.zhurong.platform.custom.vo.LabelDataVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 控制器实现
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/disNestNest00000100")
public class DisNestNest00000100Controller extends com.zhurong.platform.custom.web.BaseController {

    private final ProductLabelExcelService productLabelExcelService;
    private final IDisNestNest00000100Service disNestNest00000100Service;
    private final IDisNestNest00000100Api disNestNest00000100Api;
    private final NestService nestService;

    @PostMapping("labelData")
    public ApiResponse<List<LabelDataVO>> labelData(
            @RequestBody LabelDataQueryDTO labelDataQueryDTO) {

        try{
            List<LabelDataVO> labelDataVOS = nestService.labelData(labelDataQueryDTO);
            return ApiResponse.success(labelDataVOS);
        } catch (Exception e) {
            return ApiResponse.fail(e.getMessage());
        }

    }

    @PostMapping("exportLabelExcel")
    public void exportLabelExcel(
            @RequestBody
            LabelDataQueryDTO labelDataQueryDTO,
            HttpServletResponse response
    ) throws IOException {

        /*
         * 这里返回的已经是后端处理好的最终数据。
         * Excel 服务不会再次分组或合并。
         */
        List<LabelDataVO> labelDataVOS =
                nestService.labelData(
                        labelDataQueryDTO
                );

        if (labelDataVOS == null ||
                labelDataVOS.isEmpty()) {
            throw new IllegalArgumentException(
                    "未查询到标签数据"
            );
        }

        /*
         * null：不合并，需要按数量展开。
         * false：同套料合并，不展开。
         * true：跨板合并，不展开。
         */
        boolean expandByQuantity =
                labelDataQueryDTO.getCrossBoardMerger() == null;

        ProductLabelExcelService.GeneratedLabelExcel generated =
                productLabelExcelService.generate(
                        labelDataVOS,
                        expandByQuantity
                );

        response.setContentType(
                generated.contentType()
        );

        response.setHeader(
                HttpHeaders.CONTENT_DISPOSITION,
                ContentDisposition
                        .attachment()
                        .filename(
                                generated.fileName(),
                                StandardCharsets.UTF_8
                        )
                        .build()
                        .toString()
        );

        response.setContentLength(
                generated.content().length
        );

        response.getOutputStream().write(
                generated.content()
        );

        response.getOutputStream().flush();
    }

}
