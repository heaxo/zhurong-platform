package com.zhurong.platform.core.lantek.service.impl;

import com.zhurong.platform.base.constant.FfllConstant;
import com.zhurong.platform.core.lantek.convert.SystFfll00000200Convert;
import com.zhurong.platform.core.lantek.dto.SystFfll00000200DTO;
import com.zhurong.platform.core.lantek.entity.SystFfll00000200;
import com.zhurong.platform.core.lantek.mapper.SystFfll00000200Mapper;
import com.zhurong.platform.core.lantek.service.ISystFfll00000200Service;
import com.zhurong.platform.core.lantek.vo.SystFfll00000200VO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SystFfll00000200ServiceImpl
        extends ServiceImpl<SystFfll00000200Mapper, SystFfll00000200>
        implements ISystFfll00000200Service {

    private final SystFfll00000200Convert convert;


    @Override
    public SystFfll00000200VO getVOById(Long id) {
        SystFfll00000200 entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(SystFfll00000200DTO dto) {
        SystFfll00000200 entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, SystFfll00000200DTO dto) {
        SystFfll00000200 entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }

    @Override
    public String getSystemVaultPath() {
        SystFfll00000200 one = getOne(Wrappers.lambdaQuery(SystFfll00000200.class)
                .eq(SystFfll00000200::getVltName, FfllConstant.VltName.SYS_VAULT));
        if (one == null){
            log.warn("未找到系统库路径，取默认值：{}",FfllConstant.DEFAULT_SYS_VAULT);
            return FfllConstant.DEFAULT_SYS_VAULT;
        }
        return one.getVltFld();
    }
}
