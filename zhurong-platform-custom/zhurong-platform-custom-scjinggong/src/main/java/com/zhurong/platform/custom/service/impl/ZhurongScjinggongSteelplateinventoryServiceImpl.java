package com.zhurong.platform.custom.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.base.clientimport.dto.RawMaterialRequest;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.base.lantek.expert.lst.PlateAndRemnantLstTool;
import com.zhurong.platform.base.lantek.expert.procesos.AutomationInstructionBuilder;
import com.zhurong.platform.base.lantek.expert.procesos.CreateAndUpdateBoard;
import com.zhurong.platform.custom.convert.ZhurongScjinggongSteelplateinventoryConvert;
import com.zhurong.platform.custom.dto.ZhurongScjinggongSteelplateinventoryDTO;
import com.zhurong.platform.custom.entity.ZhurongScjinggongSteelplateinventory;
import com.zhurong.platform.custom.mapper.ZhurongScjinggongSteelplateinventoryMapper;
import com.zhurong.platform.custom.model.BaseEntity;
import com.zhurong.platform.custom.properties.ScjinggongProperties;
import com.zhurong.platform.custom.service.IZhurongScjinggongSteelplateinventoryService;
import com.zhurong.platform.custom.vo.ZhurongScjinggongSteelplateinventoryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class ZhurongScjinggongSteelplateinventoryServiceImpl
        extends ServiceImpl<ZhurongScjinggongSteelplateinventoryMapper, ZhurongScjinggongSteelplateinventory>
        implements IZhurongScjinggongSteelplateinventoryService {

    private final ZhurongScjinggongSteelplateinventoryConvert convert;
    private final ScjinggongProperties properties;


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
        String install = properties.getLantek().getInstall();
        String lstPath = PlateAndRemnantLstTool.exportRawMaterials(requests);
        AutomationInstructionBuilder.ExecResult result = new AutomationInstructionBuilder(
                AutomationInstructionBuilder.AutomationVersion.V45,
                install.trim()
        ).withPrcEncoding(AutomationInstructionBuilder.PrcEncoding.ANSI)
                .addInstruction(new CreateAndUpdateBoard(lstPath))
                .execute();
        if (!result.success()) {
            throw new BusinessException("钢板导入失败");
        }
        return true;
    }
}
