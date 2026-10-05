package com.zhurong.platform.custom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhurong.platform.custom.dto.BasepartRequest;
import com.zhurong.platform.custom.dto.ZhurongScjinggongBasepartDTO;
import com.zhurong.platform.custom.entity.ZhurongScjinggongBasepart;
import com.zhurong.platform.custom.vo.ZhurongScjinggongBasepartVO;

/**
 * 服务接口
 */
public interface IZhurongScjinggongBasepartService extends IService<ZhurongScjinggongBasepart> {

    boolean creates(BasepartRequest request);

    ZhurongScjinggongBasepartVO getVOById(Long id);

    Long saveFromDTO(ZhurongScjinggongBasepartDTO dto);

    Boolean updateFromDTO(Long id, ZhurongScjinggongBasepartDTO dto);
}
