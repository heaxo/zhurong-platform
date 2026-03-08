package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.AoPlatformOutboxEventDTO;
import com.ao.platform.core.lantek.dto.AoPlatformOutboxEventPageQuery;
import com.ao.platform.core.lantek.entity.AoPlatformOutboxEvent;
import com.ao.platform.core.lantek.vo.AoPlatformOutboxEventVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-06T15:33:06+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class AoPlatformOutboxEventConvertImpl implements AoPlatformOutboxEventConvert {

    @Override
    public AoPlatformOutboxEventVO toVO(AoPlatformOutboxEvent entity) {
        if ( entity == null ) {
            return null;
        }

        AoPlatformOutboxEventVO aoPlatformOutboxEventVO = new AoPlatformOutboxEventVO();

        if ( entity.getId() != null ) {
            aoPlatformOutboxEventVO.setId( String.valueOf( entity.getId() ) );
        }
        aoPlatformOutboxEventVO.setEventId( entity.getEventId() );
        aoPlatformOutboxEventVO.setEventType( entity.getEventType() );
        aoPlatformOutboxEventVO.setAggregateId( entity.getAggregateId() );
        aoPlatformOutboxEventVO.setOldState( entity.getOldState() );
        aoPlatformOutboxEventVO.setNewState( entity.getNewState() );
        aoPlatformOutboxEventVO.setSourceLastDate( entity.getSourceLastDate() );
        aoPlatformOutboxEventVO.setPayload( entity.getPayload() );
        aoPlatformOutboxEventVO.setStatus( entity.getStatus() );
        aoPlatformOutboxEventVO.setRetryCount( entity.getRetryCount() );
        aoPlatformOutboxEventVO.setNextRetryTime( entity.getNextRetryTime() );

        return aoPlatformOutboxEventVO;
    }

    @Override
    public List<AoPlatformOutboxEventVO> toVOList(List<AoPlatformOutboxEvent> list) {
        if ( list == null ) {
            return null;
        }

        List<AoPlatformOutboxEventVO> list1 = new ArrayList<AoPlatformOutboxEventVO>( list.size() );
        for ( AoPlatformOutboxEvent aoPlatformOutboxEvent : list ) {
            list1.add( toVO( aoPlatformOutboxEvent ) );
        }

        return list1;
    }

    @Override
    public AoPlatformOutboxEvent toEntity(AoPlatformOutboxEventDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AoPlatformOutboxEvent aoPlatformOutboxEvent = new AoPlatformOutboxEvent();

        aoPlatformOutboxEvent.setEventId( dto.getEventId() );
        aoPlatformOutboxEvent.setEventType( dto.getEventType() );
        aoPlatformOutboxEvent.setAggregateId( dto.getAggregateId() );
        aoPlatformOutboxEvent.setOldState( dto.getOldState() );
        aoPlatformOutboxEvent.setNewState( dto.getNewState() );
        aoPlatformOutboxEvent.setSourceLastDate( dto.getSourceLastDate() );
        aoPlatformOutboxEvent.setPayload( dto.getPayload() );
        aoPlatformOutboxEvent.setStatus( dto.getStatus() );
        aoPlatformOutboxEvent.setRetryCount( dto.getRetryCount() );
        aoPlatformOutboxEvent.setNextRetryTime( dto.getNextRetryTime() );

        return aoPlatformOutboxEvent;
    }

    @Override
    public AoPlatformOutboxEvent toEntity(AoPlatformOutboxEventPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        AoPlatformOutboxEvent aoPlatformOutboxEvent = new AoPlatformOutboxEvent();

        aoPlatformOutboxEvent.setEventId( dto.getEventId() );
        aoPlatformOutboxEvent.setEventType( dto.getEventType() );
        aoPlatformOutboxEvent.setAggregateId( dto.getAggregateId() );
        aoPlatformOutboxEvent.setOldState( dto.getOldState() );
        aoPlatformOutboxEvent.setNewState( dto.getNewState() );
        aoPlatformOutboxEvent.setSourceLastDate( dto.getSourceLastDate() );
        aoPlatformOutboxEvent.setPayload( dto.getPayload() );
        aoPlatformOutboxEvent.setStatus( dto.getStatus() );
        aoPlatformOutboxEvent.setRetryCount( dto.getRetryCount() );
        aoPlatformOutboxEvent.setNextRetryTime( dto.getNextRetryTime() );

        return aoPlatformOutboxEvent;
    }

    @Override
    public void updateFromDTO(AoPlatformOutboxEventDTO dto, AoPlatformOutboxEvent entity) {
        if ( dto == null ) {
            return;
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
