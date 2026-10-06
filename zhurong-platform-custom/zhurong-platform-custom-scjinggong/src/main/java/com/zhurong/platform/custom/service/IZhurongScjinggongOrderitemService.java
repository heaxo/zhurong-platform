package com.zhurong.platform.custom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderitemDTO;
import com.zhurong.platform.custom.entity.ZhurongScjinggongOrderitem;
import com.zhurong.platform.custom.vo.ZhurongScjinggongOrderitemVO;

/**
 * 服务接口
 */
public interface IZhurongScjinggongOrderitemService extends IService<ZhurongScjinggongOrderitem> {
    ZhurongScjinggongOrderitemVO getVOById(Long id);

    Long saveFromDTO(ZhurongScjinggongOrderitemDTO dto);

    Boolean updateFromDTO(Long id, ZhurongScjinggongOrderitemDTO dto);

    boolean importToExpert(ZhurongScjinggongOrderitemDTO dto);
}
