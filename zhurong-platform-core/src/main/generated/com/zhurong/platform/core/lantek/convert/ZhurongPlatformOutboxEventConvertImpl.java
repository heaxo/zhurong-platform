package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.ZhurongPlatformOutboxEventDTO;
import com.zhurong.platform.core.lantek.dto.ZhurongPlatformOutboxEventPageQuery;
import com.zhurong.platform.core.lantek.entity.ZhurongPlatformOutboxEvent;
import com.zhurong.platform.core.lantek.vo.ZhurongPlatformOutboxEventVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-01T18:49:30+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class ZhurongPlatformOutboxEventConvertImpl implements ZhurongPlatformOutboxEventConvert {

    @Override
    public ZhurongPlatformOutboxEventVO toVO(ZhurongPlatformOutboxEvent entity) {
        if ( entity == null ) {
            return null;
        }

        ZhurongPlatformOutboxEventVO zhurongPlatformOutboxEventVO = new ZhurongPlatformOutboxEventVO();

        if ( entity.getId() != null ) {
            zhurongPlatformOutboxEventVO.setId( String.valueOf( entity.getId() ) );
        }
        zhurongPlatformOutboxEventVO.setEventId( entity.getEventId() );
        zhurongPlatformOutboxEventVO.setEventType( entity.getEventType() );
        zhurongPlatformOutboxEventVO.setAggregateId( entity.getAggregateId() );
        zhurongPlatformOutboxEventVO.setOldState( entity.getOldState() );
        zhurongPlatformOutboxEventVO.setNewState( entity.getNewState() );
        zhurongPlatformOutboxEventVO.setSourceLastDate( entity.getSourceLastDate() );
        zhurongPlatformOutboxEventVO.setPayload( entity.getPayload() );
        zhurongPlatformOutboxEventVO.setStatus( entity.getStatus() );
        zhurongPlatformOutboxEventVO.setRetryCount( entity.getRetryCount() );
        zhurongPlatformOutboxEventVO.setNextRetryTime( entity.getNextRetryTime() );
        zhurongPlatformOutboxEventVO.setCreateTime( entity.getCreateTime() );
        zhurongPlatformOutboxEventVO.setUpdateTime( entity.getUpdateTime() );

        return zhurongPlatformOutboxEventVO;
    }

    @Override
    public List<ZhurongPlatformOutboxEventVO> toVOList(List<ZhurongPlatformOutboxEvent> list) {
        if ( list == null ) {
            return null;
        }

        List<ZhurongPlatformOutboxEventVO> list1 = new ArrayList<ZhurongPlatformOutboxEventVO>( list.size() );
        for ( ZhurongPlatformOutboxEvent zhurongPlatformOutboxEvent : list ) {
            list1.add( toVO( zhurongPlatformOutboxEvent ) );
        }

        return list1;
    }

    @Override
    public ZhurongPlatformOutboxEvent toEntity(ZhurongPlatformOutboxEventDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongPlatformOutboxEvent zhurongPlatformOutboxEvent = new ZhurongPlatformOutboxEvent();

        zhurongPlatformOutboxEvent.setId( dto.getId() );
        zhurongPlatformOutboxEvent.setCreateTime( dto.getCreateTime() );
        zhurongPlatformOutboxEvent.setUpdateTime( dto.getUpdateTime() );
        zhurongPlatformOutboxEvent.setEventId( dto.getEventId() );
        zhurongPlatformOutboxEvent.setEventType( dto.getEventType() );
        zhurongPlatformOutboxEvent.setAggregateId( dto.getAggregateId() );
        zhurongPlatformOutboxEvent.setOldState( dto.getOldState() );
        zhurongPlatformOutboxEvent.setNewState( dto.getNewState() );
        zhurongPlatformOutboxEvent.setSourceLastDate( dto.getSourceLastDate() );
        zhurongPlatformOutboxEvent.setPayload( dto.getPayload() );
        zhurongPlatformOutboxEvent.setStatus( dto.getStatus() );
        zhurongPlatformOutboxEvent.setRetryCount( dto.getRetryCount() );
        zhurongPlatformOutboxEvent.setNextRetryTime( dto.getNextRetryTime() );

        return zhurongPlatformOutboxEvent;
    }

    @Override
    public ZhurongPlatformOutboxEvent toEntity(ZhurongPlatformOutboxEventPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongPlatformOutboxEvent zhurongPlatformOutboxEvent = new ZhurongPlatformOutboxEvent();

        zhurongPlatformOutboxEvent.setEventId( dto.getEventId() );
        zhurongPlatformOutboxEvent.setEventType( dto.getEventType() );
        zhurongPlatformOutboxEvent.setAggregateId( dto.getAggregateId() );
        zhurongPlatformOutboxEvent.setOldState( dto.getOldState() );
        zhurongPlatformOutboxEvent.setNewState( dto.getNewState() );
        zhurongPlatformOutboxEvent.setSourceLastDate( dto.getSourceLastDate() );
        zhurongPlatformOutboxEvent.setPayload( dto.getPayload() );
        zhurongPlatformOutboxEvent.setStatus( dto.getStatus() );
        zhurongPlatformOutboxEvent.setRetryCount( dto.getRetryCount() );
        zhurongPlatformOutboxEvent.setNextRetryTime( dto.getNextRetryTime() );

        return zhurongPlatformOutboxEvent;
    }

    @Override
    public void updateFromDTO(ZhurongPlatformOutboxEventDTO dto, ZhurongPlatformOutboxEvent entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getCreateTime() != null ) {
            entity.setCreateTime( dto.getCreateTime() );
        }
        if ( dto.getUpdateTime() != null ) {
            entity.setUpdateTime( dto.getUpdateTime() );
        }
        if ( dto.getEventId() != null ) {
            entity.setEventId( dto.getEventId() );
        }
        if ( dto.getEventType() != null ) {
            entity.setEventType( dto.getEventType() );
        }
        if ( dto.getAggregateId() != null ) {
            entity.setAggregateId( dto.getAggregateId() );
        }
        if ( dto.getOldState() != null ) {
            entity.setOldState( dto.getOldState() );
        }
        if ( dto.getNewState() != null ) {
            entity.setNewState( dto.getNewState() );
        }
        if ( dto.getSourceLastDate() != null ) {
            entity.setSourceLastDate( dto.getSourceLastDate() );
        }
        if ( dto.getPayload() != null ) {
            entity.setPayload( dto.getPayload() );
        }
        if ( dto.getStatus() != null ) {
            entity.setStatus( dto.getStatus() );
        }
        if ( dto.getRetryCount() != null ) {
            entity.setRetryCount( dto.getRetryCount() );
        }
        if ( dto.getNextRetryTime() != null ) {
            entity.setNextRetryTime( dto.getNextRetryTime() );
        }
    }
}
