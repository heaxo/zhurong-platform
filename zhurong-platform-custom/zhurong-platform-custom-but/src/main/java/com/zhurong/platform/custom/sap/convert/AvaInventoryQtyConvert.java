package com.zhurong.platform.custom.sap.convert;

import com.zhurong.platform.custom.sap.entity.AvaInventoryQty;
import com.zhurong.platform.custom.sap.vo.AvaInventoryQtyVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AvaInventoryQtyConvert {

    AvaInventoryQtyVO toVO(AvaInventoryQty entity);
    List<AvaInventoryQtyVO> toVO(List<AvaInventoryQty> entity);
}
