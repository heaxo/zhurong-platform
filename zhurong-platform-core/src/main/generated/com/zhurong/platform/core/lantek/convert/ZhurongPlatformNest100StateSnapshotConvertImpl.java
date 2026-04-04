package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.ZhurongPlatformNest100StateSnapshotDTO;
import com.zhurong.platform.core.lantek.dto.ZhurongPlatformNest100StateSnapshotPageQuery;
import com.zhurong.platform.core.lantek.entity.ZhurongPlatformNest100StateSnapshot;
import com.zhurong.platform.core.lantek.vo.ZhurongPlatformNest100StateSnapshotVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-04T11:40:48+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class ZhurongPlatformNest100StateSnapshotConvertImpl implements ZhurongPlatformNest100StateSnapshotConvert {

    @Override
    public ZhurongPlatformNest100StateSnapshotVO toVO(ZhurongPlatformNest100StateSnapshot entity) {
        if ( entity == null ) {
            return null;
        }

        ZhurongPlatformNest100StateSnapshotVO zhurongPlatformNest100StateSnapshotVO = new ZhurongPlatformNest100StateSnapshotVO();

        zhurongPlatformNest100StateSnapshotVO.setRecID( entity.getRecID() );
        zhurongPlatformNest100StateSnapshotVO.setMState( entity.getMState() );
        zhurongPlatformNest100StateSnapshotVO.setLastDate( entity.getLastDate() );
        zhurongPlatformNest100StateSnapshotVO.setSyncTime( entity.getSyncTime() );

        return zhurongPlatformNest100StateSnapshotVO;
    }

    @Override
    public List<ZhurongPlatformNest100StateSnapshotVO> toVOList(List<ZhurongPlatformNest100StateSnapshot> list) {
        if ( list == null ) {
            return null;
        }

        List<ZhurongPlatformNest100StateSnapshotVO> list1 = new ArrayList<ZhurongPlatformNest100StateSnapshotVO>( list.size() );
        for ( ZhurongPlatformNest100StateSnapshot zhurongPlatformNest100StateSnapshot : list ) {
            list1.add( toVO( zhurongPlatformNest100StateSnapshot ) );
        }

        return list1;
    }

    @Override
    public ZhurongPlatformNest100StateSnapshot toEntity(ZhurongPlatformNest100StateSnapshotDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongPlatformNest100StateSnapshot zhurongPlatformNest100StateSnapshot = new ZhurongPlatformNest100StateSnapshot();

        zhurongPlatformNest100StateSnapshot.setRecID( dto.getRecID() );
        zhurongPlatformNest100StateSnapshot.setMState( dto.getMState() );
        zhurongPlatformNest100StateSnapshot.setLastDate( dto.getLastDate() );
        zhurongPlatformNest100StateSnapshot.setSyncTime( dto.getSyncTime() );

        return zhurongPlatformNest100StateSnapshot;
    }

    @Override
    public ZhurongPlatformNest100StateSnapshot toEntity(ZhurongPlatformNest100StateSnapshotPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongPlatformNest100StateSnapshot zhurongPlatformNest100StateSnapshot = new ZhurongPlatformNest100StateSnapshot();

        zhurongPlatformNest100StateSnapshot.setRecID( dto.getRecID() );
        zhurongPlatformNest100StateSnapshot.setMState( dto.getMState() );
        zhurongPlatformNest100StateSnapshot.setLastDate( dto.getLastDate() );
        zhurongPlatformNest100StateSnapshot.setSyncTime( dto.getSyncTime() );

        return zhurongPlatformNest100StateSnapshot;
    }

    @Override
    public void updateFromDTO(ZhurongPlatformNest100StateSnapshotDTO dto, ZhurongPlatformNest100StateSnapshot entity) {
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
