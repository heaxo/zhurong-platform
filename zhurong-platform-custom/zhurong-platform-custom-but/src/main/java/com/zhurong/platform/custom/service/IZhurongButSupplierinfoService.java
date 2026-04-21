package com.zhurong.platform.custom.service;

import com.zhurong.platform.custom.dto.ZhurongButSupplierinfoDTO;
import com.zhurong.platform.custom.entity.ZhurongButSupplierinfo;
import com.zhurong.platform.custom.vo.ZhurongButSupplierinfoVO;

/**
 * 服务接口
 */
public interface IZhurongButSupplierinfoService extends BaseIService<ZhurongButSupplierinfo> {
    ZhurongButSupplierinfoVO getVOById(Long id);

    Long saveFromDTO(ZhurongButSupplierinfoDTO dto);

    Boolean updateFromDTO(Long id, ZhurongButSupplierinfoDTO dto);
}
