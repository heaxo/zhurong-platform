package com.ao.platform.core.lantek.convert;

import com.ao.platform.core.lantek.dto.AoPlatformJobCursorDTO;
import com.ao.platform.core.lantek.dto.AoPlatformJobCursorPageQuery;
import com.ao.platform.core.lantek.entity.AoPlatformJobCursor;
import com.ao.platform.core.lantek.vo.AoPlatformJobCursorVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-08T17:13:33+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class AoPlatformJobCursorConvertImpl implements AoPlatformJobCursorConvert {

    @Override
    public AoPlatformJobCursorVO toVO(AoPlatformJobCursor entity) {
        if ( entity == null ) {
            return null;
        }

        AoPlatformJobCursorVO aoPlatformJobCursorVO = new AoPlatformJobCursorVO();

        aoPlatformJobCursorVO.setJobName( entity.getJobName() );
        aoPlatformJobCursorVO.setCursorTime( entity.getCursorTime() );

        return aoPlatformJobCursorVO;
    }

    @Override
    public List<AoPlatformJobCursorVO> toVOList(List<AoPlatformJobCursor> list) {
        if ( list == null ) {
            return null;
        }

        List<AoPlatformJobCursorVO> list1 = new ArrayList<AoPlatformJobCursorVO>( list.size() );
        for ( AoPlatformJobCursor aoPlatformJobCursor : list ) {
            list1.add( toVO( aoPlatformJobCursor ) );
        }

        return list1;
    }

    @Override
    public AoPlatformJobCursor toEntity(AoPlatformJobCursorDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AoPlatformJobCursor aoPlatformJobCursor = new AoPlatformJobCursor();

        aoPlatformJobCursor.setJobName( dto.getJobName() );
        aoPlatformJobCursor.setCursorTime( dto.getCursorTime() );

        return aoPlatformJobCursor;
    }

    @Override
    public AoPlatformJobCursor toEntity(AoPlatformJobCursorPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        AoPlatformJobCursor aoPlatformJobCursor = new AoPlatformJobCursor();

        aoPlatformJobCursor.setJobName( dto.getJobName() );
        aoPlatformJobCursor.setCursorTime( dto.getCursorTime() );

        return aoPlatformJobCursor;
    }

    @Override
    public void updateFromDTO(AoPlatformJobCursorDTO dto, AoPlatformJobCursor entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getJobName() != null ) {
            entity.setJobName( dto.getJobName() );
        }
        if ( dto.getCursorTime() != null ) {
            entity.setCursorTime( dto.getCursorTime() );
        }
    }
}
