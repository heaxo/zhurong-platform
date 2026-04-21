package com.zhurong.platform.custom.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.custom.convert.ZhurongButSupplierinfoConvert;
import com.zhurong.platform.custom.dto.ZhurongButSupplierinfoDTO;
import com.zhurong.platform.custom.entity.ZhurongButSupplierinfo;
import com.zhurong.platform.custom.mapper.ZhurongButSupplierinfoMapper;
import com.zhurong.platform.custom.service.IZhurongButSupplierinfoService;
import com.zhurong.platform.custom.vo.ZhurongButSupplierinfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
@DS("lantek")
public class ZhurongButSupplierinfoServiceImpl
        extends ServiceImpl<ZhurongButSupplierinfoMapper, ZhurongButSupplierinfo>
        implements IZhurongButSupplierinfoService {

    private final ZhurongButSupplierinfoConvert convert;


    @Override
    public ZhurongButSupplierinfoVO getVOById(Long id) {
        ZhurongButSupplierinfo entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(ZhurongButSupplierinfoDTO dto) {
        ZhurongButSupplierinfo entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, ZhurongButSupplierinfoDTO dto) {
        ZhurongButSupplierinfo entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
