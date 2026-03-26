package com.zhurong.platform.core.lantek.convert;

import com.zhurong.platform.core.lantek.dto.ZhurongPlatformJobCursorDTO;
import com.zhurong.platform.core.lantek.dto.ZhurongPlatformJobCursorPageQuery;
import com.zhurong.platform.core.lantek.entity.ZhurongPlatformJobCursor;
import com.zhurong.platform.core.lantek.vo.ZhurongPlatformJobCursorVO;
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
public class ZhurongPlatformJobCursorConvertImpl implements ZhurongPlatformJobCursorConvert {

    @Override
    public ZhurongPlatformJobCursorVO toVO(ZhurongPlatformJobCursor entity) {
        if ( entity == null ) {
            return null;
        }

        ZhurongPlatformJobCursorVO zhurongPlatformJobCursorVO = new ZhurongPlatformJobCursorVO();

        zhurongPlatformJobCursorVO.setJobName( entity.getJobName() );
        zhurongPlatformJobCursorVO.setCursorTime( entity.getCursorTime() );

        return zhurongPlatformJobCursorVO;
    }

    @Override
    public List<ZhurongPlatformJobCursorVO> toVOList(List<ZhurongPlatformJobCursor> list) {
        if ( list == null ) {
            return null;
        }

        List<ZhurongPlatformJobCursorVO> list1 = new ArrayList<ZhurongPlatformJobCursorVO>( list.size() );
        for ( ZhurongPlatformJobCursor zhurongPlatformJobCursor : list ) {
            list1.add( toVO(zhurongPlatformJobCursor) );
        }

        return list1;
    }

    @Override
    public ZhurongPlatformJobCursor toEntity(ZhurongPlatformJobCursorDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongPlatformJobCursor zhurongPlatformJobCursor = new ZhurongPlatformJobCursor();

        zhurongPlatformJobCursor.setJobName( dto.getJobName() );
        zhurongPlatformJobCursor.setCursorTime( dto.getCursorTime() );

        return zhurongPlatformJobCursor;
    }

    @Override
    public ZhurongPlatformJobCursor toEntity(ZhurongPlatformJobCursorPageQuery dto) {
        if ( dto == null ) {
            return null;
        }

        ZhurongPlatformJobCursor zhurongPlatformJobCursor = new ZhurongPlatformJobCursor();

        zhurongPlatformJobCursor.setJobName( dto.getJobName() );
        zhurongPlatformJobCursor.setCursorTime( dto.getCursorTime() );

        return zhurongPlatformJobCursor;
    }

    @Override
    public void updateFromDTO(ZhurongPlatformJobCursorDTO dto, ZhurongPlatformJobCursor entity) {
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
