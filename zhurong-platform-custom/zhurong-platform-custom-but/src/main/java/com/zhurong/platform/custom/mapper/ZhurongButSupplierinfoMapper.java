package com.zhurong.platform.custom.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhurong.platform.custom.entity.ZhurongButSupplierinfo;
import org.apache.ibatis.annotations.Delete;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author me
 * @since 2026-04-19
 */
@DS("lantek")
public interface ZhurongButSupplierinfoMapper extends BaseMapper<ZhurongButSupplierinfo> {

    @Delete("DELETE FROM PPRR_PPRR_00000100 WHERE DIS_IsRemnant <> 1 and DIS_PClass = 51")
    int clearPprrPprr00000100();

    @Delete("DELETE WH1 FROM WWHH_IIVV_00000100 AS WH1 WHERE NOT EXISTS (SELECT 1 FROM PPRR_PPRR_00000100 AS P1 WHERE P1.PrdRef = WH1.PrdRef AND P1.DIS_IsRemnant = 1)")
    int clearWwhhIivv00000100();

    @Delete("DELETE WH1 FROM WWHH_IIVV_00000100 AS WH1 WHERE NOT EXISTS (SELECT 1 FROM PPRR_PPRR_00000100 AS P1 WHERE P1.PrdRef = WH1.PrdRef AND P1.DIS_IsRemnant = 1)")
    int clearWwhhIivv00000200();
}
