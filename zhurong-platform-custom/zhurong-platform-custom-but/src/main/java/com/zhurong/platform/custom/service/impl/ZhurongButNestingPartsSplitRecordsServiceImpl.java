package com.zhurong.platform.custom.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.custom.convert.ZhurongButNestingPartsSplitRecordsConvert;
import com.zhurong.platform.custom.dto.ZhurongButNestingPartsSplitRecordsCreateDTO;
import com.zhurong.platform.custom.entity.DisNestNest00000500;
import com.zhurong.platform.custom.entity.ZhurongButNestingPartsSplitRecords;
import com.zhurong.platform.custom.mapper.ZhurongButNestingPartsSplitRecordsMapper;
import com.zhurong.platform.custom.model.BaseEntity;
import com.zhurong.platform.custom.service.IDisNestNest00000500Service;
import com.zhurong.platform.custom.service.IZhurongButNestingPartsSplitRecordsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
@DS("lantek")
@Slf4j
public class ZhurongButNestingPartsSplitRecordsServiceImpl
        extends ServiceImpl<ZhurongButNestingPartsSplitRecordsMapper, ZhurongButNestingPartsSplitRecords>
        implements IZhurongButNestingPartsSplitRecordsService {

    private final ZhurongButNestingPartsSplitRecordsConvert convert;
    private final IDisNestNest00000500Service disNestNest00000500Service;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean splitRecordsOverwrite(List<ZhurongButNestingPartsSplitRecordsCreateDTO> records) {
        List<String> nstRefs = records.stream().map(ZhurongButNestingPartsSplitRecordsCreateDTO::getNstRef).distinct().toList();

        if (nstRefs.size() > 1){
            String msg = String.format("不能同时拆分多个套料程序零件：%s", String.join(",", nstRefs));
            log.warn(msg);
            throw new BusinessException(msg);
        }
        String nstRef = nstRefs.get(0);

        Map<Integer, Integer> recordMap = records.stream()
                .collect(Collectors.groupingBy(ZhurongButNestingPartsSplitRecordsCreateDTO::getRecId,
                        Collectors.summingInt(it -> Optional.ofNullable(it.getQuantity())
                                .orElse(0))));

        List<Integer> recIds = recordMap.keySet().stream().toList();

        List<DisNestNest00000500> nestParts = disNestNest00000500Service.list(Wrappers.lambdaQuery(DisNestNest00000500.class)
                .in(DisNestNest00000500::getRecID, recIds));

        Map<String, String> prdRefMap = new HashMap<>();

        //校验数量
        for (int i = 0; i < nestParts.size(); i++) {
            DisNestNest00000500 disNestNest00000500 = nestParts.get(i);
            Integer quantity = recordMap.get(disNestNest00000500.getRecID());
            prdRefMap.put(disNestNest00000500.getRecID().toString(), disNestNest00000500.getPrdRefDst());
            if (quantity > disNestNest00000500.getQuantity()){
                String msg = String.format("套料程序：%s，零件：%s，拆分数量总和：%s，超出原套料数量：%s",
                        disNestNest00000500.getNstRef(),
                        disNestNest00000500.getPrdRefDst(),
                        quantity,
                        disNestNest00000500.getQuantity()
                );
                log.warn(msg);
                throw new BusinessException(msg);
            }
        }

        //先删除记录
        List<ZhurongButNestingPartsSplitRecords> existings = list(Wrappers.lambdaQuery(ZhurongButNestingPartsSplitRecords.class)
                .eq(ZhurongButNestingPartsSplitRecords::getNstRef, nstRef));

        if (!existings.isEmpty()){
            boolean removeSucceed = removeBatchByIds(existings.stream().map(BaseEntity::getId).toList());
            if (!removeSucceed){
                String msg = String.format("%s，拆分记录删除失败",nstRef);
                log.warn(msg);
                throw new BusinessException(msg);
            }
        }

        List<ZhurongButNestingPartsSplitRecords> saves = convert.toEntity(records);
        saves.forEach(it -> {
            it.setPrdRef(prdRefMap.get(it.getRecId().toString()));
        });
        boolean succeed = saveBatch(saves);
        if (!succeed){
            throw new BusinessException("拆分记录保存失败");
        }
        return true;
    }
}
