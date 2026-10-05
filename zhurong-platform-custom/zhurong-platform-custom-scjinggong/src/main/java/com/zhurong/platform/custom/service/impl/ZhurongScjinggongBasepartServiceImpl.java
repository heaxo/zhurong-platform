package com.zhurong.platform.custom.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.custom.convert.ZhurongScjinggongBasepartConvert;
import com.zhurong.platform.custom.drawing.DrawingDownloadTool;
import com.zhurong.platform.custom.dto.BasepartRequest;
import com.zhurong.platform.custom.dto.BasepartRequestDTO;
import com.zhurong.platform.custom.dto.ZhurongScjinggongBasepartDTO;
import com.zhurong.platform.custom.entity.DisMmttMmtt00000100;
import com.zhurong.platform.custom.entity.ZhurongScjinggongBasepart;
import com.zhurong.platform.custom.mapper.ZhurongScjinggongBasepartMapper;
import com.zhurong.platform.custom.service.IDisMmttMmtt00000100Service;
import com.zhurong.platform.custom.service.IZhurongScjinggongBasepartService;
import com.zhurong.platform.custom.vo.ZhurongScjinggongBasepartVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class ZhurongScjinggongBasepartServiceImpl
        extends ServiceImpl<ZhurongScjinggongBasepartMapper, ZhurongScjinggongBasepart>
        implements IZhurongScjinggongBasepartService {

    private final IDisMmttMmtt00000100Service disMmttMmtt00000100Service;
    private final ZhurongScjinggongBasepartConvert convert;
    private final DrawingDownloadTool drawingDownloadTool;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean creates(BasepartRequest request) {
        List<BasepartRequestDTO> data = request.getData();
        List<String> prdRefs = data.stream().map(BasepartRequestDTO::getPrdRef).distinct().toList();
        List<String> matRefs = new ArrayList<>(data.stream().map(BasepartRequestDTO::getMatRef).distinct().toList());

        if (prdRefs.size() < data.size()) {
            List<String> _prdRefs = data
                    .stream()
                    .collect(Collectors.groupingBy(BasepartRequestDTO::getPrdRef))
                    .entrySet()
                    .stream()
                    .filter(it -> it.getValue().size() > 1)
                    .map(Map.Entry::getKey)
                    .distinct().toList();
            throw new BusinessException(String.format("零件编码重复：%s", String.join(",", _prdRefs)));
        }

        //重复校验
        List<ZhurongScjinggongBasepart> existingData = list(Wrappers.lambdaQuery(ZhurongScjinggongBasepart.class)
                .in(ZhurongScjinggongBasepart::getPrdRef, prdRefs)
        );

        if (!existingData.isEmpty()){
            List<String> _prdRefs = existingData.stream().map(ZhurongScjinggongBasepart::getPrdRef).distinct().toList();
            throw new BusinessException(String.format("零件档案已存在：%s", String.join(",", _prdRefs)));
        }

        List<DisMmttMmtt00000100> materials = disMmttMmtt00000100Service.list();
        List<String> _matRefs = materials.stream().map(DisMmttMmtt00000100::getMatRef).toList();
        matRefs.removeAll(_matRefs);

        if (!matRefs.isEmpty()){
            throw new BusinessException(String.format("材质不存在，请打开套料软件【管理-材料-新增】维护：%s", String.join(",", matRefs)));
        }

        List<ZhurongScjinggongBasepart> saves = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            BasepartRequestDTO dto = data.get(i);
            String rawDrawingPath = dto.getRawDrawingPath();
            String drawingPath = drawingDownloadTool.download(rawDrawingPath);
            ZhurongScjinggongBasepart entity = convert.toEntity(dto);
            entity.setRawDrawingPath(rawDrawingPath);
            entity.setDrawingPath(drawingPath);
            saves.add(entity);
        }

        return saveBatch(saves);
    }

    @Override
    public ZhurongScjinggongBasepartVO getVOById(Long id) {
        ZhurongScjinggongBasepart entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(ZhurongScjinggongBasepartDTO dto) {
        ZhurongScjinggongBasepart entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, ZhurongScjinggongBasepartDTO dto) {
        ZhurongScjinggongBasepart entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
