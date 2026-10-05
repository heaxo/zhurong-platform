package com.zhurong.platform.custom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhurong.platform.custom.dto.OrderRequest;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderDTO;
import com.zhurong.platform.custom.entity.ZhurongScjinggongOrder;
import com.zhurong.platform.custom.vo.ZhurongScjinggongOrderVO;

/**
 * 服务接口
 */
public interface IZhurongScjinggongOrderService extends IService<ZhurongScjinggongOrder> {

    boolean creates(OrderRequest request);

    ZhurongScjinggongOrderVO getVOById(Long id);

    Long saveFromDTO(ZhurongScjinggongOrderDTO dto);

    Boolean updateFromDTO(Long id, ZhurongScjinggongOrderDTO dto);
}
