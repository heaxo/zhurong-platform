package com.zhurong.platform.custom.erp.convert;

import com.zhurong.platform.custom.erp.entity.ViPmOrderl;
import com.zhurong.platform.custom.erp.vo.ViPmOrderlVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-21T22:19:20+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class ViPmOrderlConvertImpl implements ViPmOrderlConvert {

    @Override
    public ViPmOrderlVO toVO(ViPmOrderl entity) {
        if ( entity == null ) {
            return null;
        }

        ViPmOrderlVO viPmOrderlVO = new ViPmOrderlVO();

        viPmOrderlVO.setBelposId( entity.getBelposId() );
        viPmOrderlVO.setItemCode( entity.getItemCode() );
        viPmOrderlVO.setCoutKey( entity.getCoutKey() );
        viPmOrderlVO.setCfccad( entity.getCfccad() );
        viPmOrderlVO.setCfType( entity.getCfType() );
        viPmOrderlVO.setCcad( entity.getCcad() );
        viPmOrderlVO.setItemName( entity.getItemName() );
        viPmOrderlVO.setUZnr( entity.getUZnr() );
        viPmOrderlVO.setUdf1( entity.getUdf1() );
        viPmOrderlVO.setAplatzId( entity.getAplatzId() );
        viPmOrderlVO.setMengeVerbrauch( entity.getMengeVerbrauch() );
        viPmOrderlVO.setAnfzeit( entity.getAnfzeit() );
        viPmOrderlVO.setLieferdatum( entity.getLieferdatum() );
        viPmOrderlVO.setVversion( entity.getVversion() );
        viPmOrderlVO.setIcadproduct( entity.getIcadproduct() );

        return viPmOrderlVO;
    }

    @Override
    public List<ViPmOrderlVO> toVO(List<ViPmOrderl> entity) {
        if ( entity == null ) {
            return null;
        }

        List<ViPmOrderlVO> list = new ArrayList<ViPmOrderlVO>( entity.size() );
        for ( ViPmOrderl viPmOrderl : entity ) {
            list.add( toVO( viPmOrderl ) );
        }

        return list;
    }
}
