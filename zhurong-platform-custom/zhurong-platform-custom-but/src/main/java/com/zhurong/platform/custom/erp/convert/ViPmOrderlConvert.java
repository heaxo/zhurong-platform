package com.zhurong.platform.custom.erp.convert;

import com.zhurong.platform.custom.erp.dto.ViPmOrderlDTO;
import com.zhurong.platform.custom.erp.entity.ViPmOrderl;
import com.zhurong.platform.custom.erp.vo.ViPmOrderlVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ViPmOrderlConvert {

    ViPmOrderl toEntity(ViPmOrderlDTO dto);
    ViPmOrderlVO toVO(ViPmOrderl entity);
    List<ViPmOrderlVO> toVO(List<ViPmOrderl> entity);
}
