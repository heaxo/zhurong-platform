package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.AoPlatformNest100StateSnapshotDTO;
import com.ao.platform.core.lantek.dto.AoPlatformNest100StateSnapshotPageQuery;
import com.ao.platform.core.lantek.entity.AoPlatformNest100StateSnapshot;
import com.ao.platform.core.lantek.vo.AoPlatformNest100StateSnapshotVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-08T17:13:32+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class AoPlatformNest100StateSnapshotConvertImpl implements AoPlatformNest100StateSnapshotConvert {

    @Override
    public AoPlatformNest100StateSnapshotVO toVO(AoPlatformNest100StateSnapshot entity) {
        if ( entity == null ) {
            return null;
        }

        AoPlatformNest100StateSnapshotVO aoPlatformNest100StateSnapshotVO = new AoPlatformNest100StateSnapshotVO();

        aoPlatformNest100StateSnapshotVO.setRecID( entity.getRecID() );
        aoPlatformNest100StateSnapshotVO.setMState( entity.getMState() );
        aoPlatformNest100StateSnapshotVO.setLastDate( entity.getLastDate() );
        aoPlatformNest100StateSnapshotVO.setSyncTime( entity.getSyncTime() );

        return aoPlatformNest100StateSnapshotVO;
    }

    @Override
    public List<AoPlatformNest100StateSnapshotVO> toVOList(List<AoPlatformNest100StateSnapshot> list) {
        if ( list == null ) {
            return null;
        }

        List<AoPlatformNest100StateSnapshotVO> list1 = new ArrayList<AoPlatformNest100StateSnapshotVO>( list.size() );
        for ( AoPlatformNest100StateSnapshot aoPlatformNest100StateSnapshot : list ) {
            list1.add( toVO( aoPlatformNest100StateSnapshot ) );
        }

        return list1;
    }

    @Override
    public AoPlatformNest100StateSnapshot toEntity(AoPlatformNest100StateSnapshotDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AoPlatformNest100StateSnapshot aoPlatformNest100StateSnapshot = new AoPlatformNest100StateSnapshot();

        aoPlatformNest100StateSnapshot.setRecID( dto.getRecID() );
        aoPlatformNest100StateSnapshot.setMState( dto.getMState() );
        aoPlatformNest100StateSnapshot.setLastDate( dto.getLastDate() );
        aoPlatformNest100StateSnapshot.setSyncTime( dto.getSyncTime() );

        return aoPlatformNest100StateSnapshot;
    }

    @Override
    public AoPlatformNest100StateSnapshot toEntity(AoPlatformNest100StateSnapshotPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        AoPlatformNest100StateSnapshot aoPlatformNest100StateSnapshot = new AoPlatformNest100StateSnapshot();

        aoPlatformNest100StateSnapshot.setRecID( dto.getRecID() );
        aoPlatformNest100StateSnapshot.setMState( dto.getMState() );
        aoPlatformNest100StateSnapshot.setLastDate( dto.getLastDate() );
        aoPlatformNest100StateSnapshot.setSyncTime( dto.getSyncTime() );

        return aoPlatformNest100StateSnapshot;
    }

    @Override
    public void updateFromDTO(AoPlatformNest100StateSnapshotDTO dto, AoPlatformNest100StateSnapshot entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getRecID() != null ) {
            entity.setRecID( dto.getRecID() );
        }
        if ( dto.getMState() != null ) {
            entity.setMState( dto.getMState() );
        }
        if ( dto.getLastDate() != null ) {
            entity.setLastDate( dto.getLastDate() );
        }
        if ( dto.getSyncTime() != null ) {
            entity.setSyncTime( dto.getSyncTime() );
        }
    }
}
