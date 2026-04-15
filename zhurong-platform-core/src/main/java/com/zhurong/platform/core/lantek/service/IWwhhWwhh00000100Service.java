package com.zhurong.platform.core.lantek.service;

import com.zhurong.platform.core.lantek.dto.WwhhWwhh00000100DTO;
import com.zhurong.platform.core.lantek.entity.WwhhWwhh00000100;
import com.zhurong.platform.core.lantek.vo.WwhhWwhh00000100VO;

/**
 * 服务接口
 */
public interface IWwhhWwhh00000100Service extends BaseIService<WwhhWwhh00000100> {
    WwhhWwhh00000100VO getVOById(Long id);

    Long saveFromDTO(WwhhWwhh00000100DTO dto);

    Boolean updateFromDTO(Long id, WwhhWwhh00000100DTO dto);

    //强制创建仓库
    Boolean forceCreationOfWarehouses(String warehouse);
    //强制创建库位
    Boolean forceTheCreationOfStorageLocations(String warehouse,String location);
    //强制创建仓库和库位
    Boolean forceTheCreationOfWarehousesAndStorageLocations(String warehouse,String location);
}
