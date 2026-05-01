package com.zhurong.platform.custom.sap.convert;

import com.zhurong.platform.custom.sap.dto.AvaInventoryQtyDTO;
import com.zhurong.platform.custom.sap.entity.AvaInventoryQty;
import com.zhurong.platform.custom.sap.vo.AvaInventoryQtyVO;
import com.zhurong.platform.custom.sbut.entity.SbutAvaInventoryQty;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AvaInventoryQtyConvert {

    AvaInventoryQty toEntity(AvaInventoryQtyDTO dto);
    SbutAvaInventoryQty toEntity(AvaInventoryQty entity);
    AvaInventoryQtyVO toVO(AvaInventoryQty entity);
    List<AvaInventoryQtyVO> toVO(List<AvaInventoryQty> entity);
    List<AvaInventoryQtyVO> toVOFromSbut(List<SbutAvaInventoryQty> entity);
}
