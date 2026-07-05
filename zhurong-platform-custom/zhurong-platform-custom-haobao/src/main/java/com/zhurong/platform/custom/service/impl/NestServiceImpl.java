package com.zhurong.platform.custom.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.base.api.ApiResponse;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.lantek.api.IDisNestNest00000100Api;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000100PageQuery;
import com.zhurong.platform.core.lantek.dto.RelationLoadPlan;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000100VO;
import com.zhurong.platform.custom.dto.LabelDataQueryDTO;
import com.zhurong.platform.custom.entity.DisNestNest00000100;
import com.zhurong.platform.custom.mapper.DisNestNest00000100Mapper;
import com.zhurong.platform.custom.service.NestService;
import com.zhurong.platform.custom.vo.LabelDataVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
@DS("lantek")
public class NestServiceImpl extends ServiceImpl<DisNestNest00000100Mapper, DisNestNest00000100> implements NestService {

    private final IDisNestNest00000100Api disNestNest00000100Api;

    public List<LabelDataVO> labelData(LabelDataQueryDTO labelDataQueryDTO){
        DisNestNest00000100PageQuery query = new DisNestNest00000100PageQuery();
        query.setRecIds(labelDataQueryDTO.getNestRecIds());
        query.setNestPartRecIds(labelDataQueryDTO.getNestPartRecIds());
        query.setLoadPlan(new RelationLoadPlan()
                .setIncludeNestParts(true)
                .setIncludePlanMaster(true));

        ApiResponse<PageResponse<DisNestNest00000100VO>> apiResponse =
                disNestNest00000100Api.pageNestOverview(query);

        if (apiResponse == null) {
            throw new BusinessException("套料数据查询失败");
        }

        PageResponse<DisNestNest00000100VO> data = apiResponse.data();

        if (data == null) {
            throw new BusinessException("套料数据查询为空");
        }

        if (CollectionUtils.isEmpty(data.items())) {
            return new ArrayList<>();
        }

        /*
         * null：不合并，每条套料零件生成一条标签数据
         * false：同一个套料程序内，按照“订单编码 + 零件编码”合并
         * true：跨套料程序，按照“订单编码 + 零件编码”合并
         */
        Boolean crossBoardMerger = labelDataQueryDTO.getCrossBoardMerger();

        record LabelRow(String nstRef, LabelDataVO labelData) {
        }

        record MergeKey(String nstRef, String ordRef, String prdRef) {
        }

        List<LabelRow> rows = data.items().stream()
                .flatMap(nest -> nest.getNestParts().stream()
                        .map(nestPart -> {
                            LabelDataVO vo = new LabelDataVO();

                            // 订单编码
                            vo.setOrdRef(nestPart.getWorkOrder().getOrdRef());

                            // 零件编码
                            vo.setPrdRef(nestPart.getPrdRefDst());
                            vo.setQrCodeContent(nestPart.getWorkOrder().getOrdRef());

                            // 零件名称
                            vo.setPrdName(nestPart.getWorkOrder().getPrdNameDst());

                            // 数量
                            vo.setQuantity(nestPart.getQuantity());

                            // 日期
                            vo.setDate(nestPart.getCrtDate());

                            return new LabelRow(nestPart.getNstRef(), vo);
                        }))
                .toList();

        // 选项1：不合并
        if (crossBoardMerger == null) {
            return rows.stream()
                    .map(LabelRow::labelData)
                    .toList();
        }

        Map<MergeKey, LabelDataVO> mergedMap = rows.stream()
                .collect(Collectors.toMap(
                        row -> {
                            LabelDataVO vo = row.labelData();

                            /*
                             * false：合并键包含套料编码，只在当前程序中合并
                             * true：合并键不包含套料编码，可以跨程序合并
                             */
                            String nstRef = crossBoardMerger
                                    ? null
                                    : row.nstRef();

                            return new MergeKey(
                                    nstRef,
                                    vo.getOrdRef(),
                                    vo.getPrdRef()
                            );
                        },
                        LabelRow::labelData,
                        (existing, current) -> {
                            int existingQuantity = Objects.requireNonNullElse(
                                    existing.getQuantity(),
                                    0
                            );

                            int currentQuantity = Objects.requireNonNullElse(
                                    current.getQuantity(),
                                    0
                            );

                            // 相同标签数据合并后数量累加
                            existing.setQuantity(existingQuantity + currentQuantity);

                            return existing;
                        },
                        LinkedHashMap::new
                ));
        return new ArrayList<>(mergedMap.values());
    }
}
