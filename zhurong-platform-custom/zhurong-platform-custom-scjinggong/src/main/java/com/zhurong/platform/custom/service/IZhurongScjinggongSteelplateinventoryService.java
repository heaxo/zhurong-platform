package com.zhurong.platform.custom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhurong.platform.custom.dto.ZhurongScjinggongSteelplateinventoryDTO;
import com.zhurong.platform.custom.entity.ZhurongScjinggongSteelplateinventory;
import com.zhurong.platform.custom.vo.ZhurongScjinggongSteelplateinventoryVO;

import java.io.IOException;

/**
 * 服务接口
 */
public interface IZhurongScjinggongSteelplateinventoryService extends IService<ZhurongScjinggongSteelplateinventory> {
    ZhurongScjinggongSteelplateinventoryVO getVOById(Long id);

    Long saveFromDTO(ZhurongScjinggongSteelplateinventoryDTO dto);

    Boolean updateFromDTO(Long id, ZhurongScjinggongSteelplateinventoryDTO dto);
    Boolean importToExpert(ZhurongScjinggongSteelplateinventoryDTO dto) throws IOException;
}
