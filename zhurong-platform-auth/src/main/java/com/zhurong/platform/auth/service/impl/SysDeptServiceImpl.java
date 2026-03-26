package com.zhurong.platform.auth.service.impl;

import com.zhurong.platform.auth.convert.SysDeptConvert;
import com.zhurong.platform.auth.dto.SysDeptDTO;
import com.zhurong.platform.auth.entity.SysDept;
import com.zhurong.platform.auth.mapper.SysDeptMapper;
import com.zhurong.platform.auth.service.ISysDeptService;
import com.zhurong.platform.auth.vo.SysDeptVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl
        extends ServiceImpl<SysDeptMapper, SysDept>
        implements ISysDeptService {

    private final SysDeptConvert convert;


    @Override
    public SysDeptVO getVOById(Long id) {
        SysDept entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(SysDeptDTO dto) {
        SysDept entity = convert.toEntity(dto);
        if (entity.getTenantId() == null){
            entity.setTenantId(0L);
        }
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, SysDeptDTO dto) {
        SysDept entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }
}
