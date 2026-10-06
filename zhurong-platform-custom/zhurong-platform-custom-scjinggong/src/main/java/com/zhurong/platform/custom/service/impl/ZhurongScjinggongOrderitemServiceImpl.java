package com.zhurong.platform.custom.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhurong.platform.base.constant.PprrClassConstant;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.base.lantek.expert.lstx.ExpertProductXmlExporter;
import com.zhurong.platform.base.lantek.expert.lstx.ExpertProductXmlItem;
import com.zhurong.platform.base.lantek.expert.lstx.LstxImportTool;
import com.zhurong.platform.base.lantek.expert.procesos.AutomationInstructionBuilder;
import com.zhurong.platform.base.lantek.expert.procesos.ImportDwg;
import com.zhurong.platform.base.lantek.expert.procesos.OpenExpert;
import com.zhurong.platform.base.lantek.expert.procesos.OpenJob;
import com.zhurong.platform.custom.convert.ZhurongScjinggongOrderitemConvert;
import com.zhurong.platform.custom.drawing.DrawingDownloadTool;
import com.zhurong.platform.custom.dto.ZhurongScjinggongOrderitemDTO;
import com.zhurong.platform.custom.entity.MmnnMmoo00000300;
import com.zhurong.platform.custom.entity.PprrPprr00000100;
import com.zhurong.platform.custom.entity.ZhurongScjinggongBasepart;
import com.zhurong.platform.custom.entity.ZhurongScjinggongOrderitem;
import com.zhurong.platform.custom.mapper.ZhurongScjinggongOrderitemMapper;
import com.zhurong.platform.custom.model.BaseEntity;
import com.zhurong.platform.custom.properties.ScjinggongProperties;
import com.zhurong.platform.custom.service.IMmnnMmoo00000300Service;
import com.zhurong.platform.custom.service.IPprrPprr00000100Service;
import com.zhurong.platform.custom.service.IZhurongScjinggongBasepartService;
import com.zhurong.platform.custom.service.IZhurongScjinggongOrderitemService;
import com.zhurong.platform.custom.vo.ZhurongScjinggongOrderitemVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
public class ZhurongScjinggongOrderitemServiceImpl
        extends ServiceImpl<ZhurongScjinggongOrderitemMapper, ZhurongScjinggongOrderitem>
        implements IZhurongScjinggongOrderitemService {

    private final ScjinggongProperties scjinggongProperties;
    private final ZhurongScjinggongOrderitemConvert convert;
    private final IZhurongScjinggongBasepartService zhurongScjinggongBasepartService;
    private final IPprrPprr00000100Service pprrPprr00000100Service;
    private final IMmnnMmoo00000300Service mmnnMmoo00000300Service;
    private final DrawingDownloadTool drawingDownloadTool;

    private static String automationError(
            String prefix,
            AutomationInstructionBuilder.ExecResult result
    ) {

        String detail = StringUtils.hasText(result.stderr()) ? result.stderr().trim() : result.stdout().trim();
        return StringUtils.hasText(detail) ? prefix + ": " + detail : prefix + "，退出码: " + result.exitCode();
    }

    @Override
    public ZhurongScjinggongOrderitemVO getVOById(Long id) {
        ZhurongScjinggongOrderitem entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(ZhurongScjinggongOrderitemDTO dto) {
        ZhurongScjinggongOrderitem entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, ZhurongScjinggongOrderitemDTO dto) {
        ZhurongScjinggongOrderitem entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }

    /**
     * 导入MES工单到Expert
     * @param dto
     * @return
     */
    @Override
    public boolean importToExpert(ZhurongScjinggongOrderitemDTO dto) {

        List<String> ids = dto.getIds();
        String jobRef = dto.getJobRef();

        if (jobRef.isBlank()) {
            throw new BusinessException("作业不能不指定");
        }

        List<ZhurongScjinggongOrderitem> list = list(Wrappers.lambdaQuery(ZhurongScjinggongOrderitem.class)
                .eq(BaseEntity::getIsRead, false)
                .in(BaseEntity::getId, ids));

        if (list.isEmpty()) {
            throw new BusinessException("未查询到相关数据1");
        }

        List<String> cusRefs = list.stream().map(ZhurongScjinggongOrderitem::getCusRef).distinct().toList();

        int updateCount = 0;
        if ((updateCount = importedState(cusRefs)) == cusRefs.size()){
            return true;
        }

        if (updateCount > 0){
            list = list(Wrappers.lambdaQuery(ZhurongScjinggongOrderitem.class)
                    .eq(BaseEntity::getIsRead, false)
                    .in(BaseEntity::getId, ids));

            if (list.isEmpty()) {
                throw new BusinessException("未查询到相关数据2");
            }
        }

        List<String> prdRefs = list.stream().map(ZhurongScjinggongOrderitem::getPrdRef).distinct().toList();

        List<ZhurongScjinggongBasepart> baseparts = zhurongScjinggongBasepartService.list(Wrappers.lambdaQuery(ZhurongScjinggongBasepart.class)
                .in(ZhurongScjinggongBasepart::getPrdRef, prdRefs));

        Map<String, ZhurongScjinggongBasepart> basepartMap = baseparts.stream()
                .collect(Collectors.toMap(ZhurongScjinggongBasepart::getPrdRef, Function.identity()));


        List<PprrPprr00000100> pprrs = pprrPprr00000100Service.list(Wrappers.lambdaQuery(PprrPprr00000100.class)
                .eq(PprrPprr00000100::getDIS_PClass, PprrClassConstant.Parts)
                .in(PprrPprr00000100::getPrdRef, prdRefs));

        Map<String, PprrPprr00000100> pprrMap = pprrs.stream()
                .collect(Collectors.toMap(PprrPprr00000100::getPrdRef, Function.identity()));

        List<String> recordPrdRefs = new ArrayList<>();

        List<ZhurongScjinggongOrderitemDTO> imports = list.stream().map(it -> {
            ZhurongScjinggongOrderitemDTO item = convert.toDTO(it);

            String prdRef = it.getPrdRef();
            if (!basepartMap.containsKey(prdRef)) {
                throw new BusinessException(String.format("零件基础档案信息未下发，无法导入生成订单：%s", prdRef));
            }
            ZhurongScjinggongBasepart basepart = basepartMap.get(prdRef);

            //套料软件中没有零件则下载图纸并在本次导入
            if (!pprrMap.containsKey(prdRef) && !recordPrdRefs.contains(prdRef)) {
                String drawingPath = drawingDownloadTool.download(basepart.getRawDrawingPath());
                item.setDrawingPath(drawingPath);
                //首次图纸导入需要指定材质厚度，后续不指定系统默认使用套料软件数据库中零件档案的材质厚度
                item.setMatRef(basepart.getMatRef());
                item.setThickness(basepart.getThickness());
                recordPrdRefs.add(prdRef);
            }

            return item;
        }).toList();

        List<ZhurongScjinggongOrderitemDTO> dwgImports = imports.stream().filter(it ->
                        it.getDrawingPath() != null && it.getDrawingPath().toLowerCase(Locale.ROOT).endsWith(".dwg"))
                .toList();
        List<ZhurongScjinggongOrderitemDTO> dxfImports = imports.stream().filter(it ->
                        it.getDrawingPath() != null && it.getDrawingPath().toLowerCase(Locale.ROOT).endsWith(".dxf"))
                .toList();
        List<ZhurongScjinggongOrderitemDTO> dbsImports = imports.stream().filter(it ->
                        com.baomidou.mybatisplus.core.toolkit.StringUtils.isBlank(it.getDrawingPath()))
                .toList();

        String install = scjinggongProperties.getLantek().getInstall();

        try {
            AutomationInstructionBuilder builder = new AutomationInstructionBuilder(
                    AutomationInstructionBuilder.AutomationVersion.V45,
                    install
            ).withPrcEncoding(AutomationInstructionBuilder.PrcEncoding.ANSI)
                    .addInstruction(new OpenExpert(true))
                    .addInstruction(new OpenJob(jobRef));

            Path directory = LstxImportTool.defaultOutputDirectory();
            Files.createDirectories(directory);

            if (!dwgImports.isEmpty()) {
                List<ExpertProductXmlItem> products = dwgImports.stream()
                        .map(this::toProduct)
                        .toList();
                Path lstx = directory.resolve("dwg-order-" + UUID.randomUUID() + ".lstx");
                new ExpertProductXmlExporter().export(products, lstx);
                builder.addInstruction(new ImportDwg(false, lstx.toAbsolutePath().toString()));
            }

            if (!dxfImports.isEmpty()) {
                List<ExpertProductXmlItem> products = dxfImports.stream()
                        .map(this::toProduct)
                        .toList();
                Path lstx = directory.resolve("dxf-order-" + UUID.randomUUID() + ".lstx");
                new ExpertProductXmlExporter().export(products, lstx);
                builder.addInstruction(new ImportDwg(false, lstx.toAbsolutePath().toString()));
            }

            if (!dbsImports.isEmpty()) {
                List<ExpertProductXmlItem> products = dbsImports.stream()
                        .map(this::toProduct)
                        .toList();
                Path lstx = directory.resolve("db-order-" + UUID.randomUUID() + ".lstx");
                new ExpertProductXmlExporter().export(products, lstx);
                builder.addInstruction(new ImportDwg(false, lstx.toAbsolutePath().toString()));
            }

            AutomationInstructionBuilder.ExecResult result = builder.execute();
            if (!result.success()) {
                throw new IllegalStateException(automationError("LSTX自动化导入失败", result));
            }

            return importedState(cusRefs) == cusRefs.size();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 校验MES工单是否全部导入
     * 将已导入且要重复导入的数据状态更新
     *
     * @param cusRefs
     * @return
     */
    private int importedState(List<String> cusRefs) {

        List<MmnnMmoo00000300> mmnnMmoo00000300s = mmnnMmoo00000300Service.list(Wrappers.lambdaQuery(MmnnMmoo00000300.class)
                .in(MmnnMmoo00000300::getCusRef, cusRefs));


        if (mmnnMmoo00000300s.isEmpty()) {
            return 0;
        }
        List<String> existingCusRefs = mmnnMmoo00000300s.stream().map(MmnnMmoo00000300::getCusRef)
                .distinct().toList();
        if (existingCusRefs.size() == cusRefs.size()) {
            //更新所有要导入的MES工单状态
            update(Wrappers.lambdaUpdate(ZhurongScjinggongOrderitem.class)
                    .set(BaseEntity::getIsRead, true)
                    .in(ZhurongScjinggongOrderitem::getCusRef, cusRefs));

            //不管上面的状态是否更新成功，这里返回成功，阻止用户重复导入
            return existingCusRefs.size();
        }

        update(Wrappers.lambdaUpdate(ZhurongScjinggongOrderitem.class)
                .set(BaseEntity::getIsRead, true)
                .in(ZhurongScjinggongOrderitem::getCusRef, existingCusRefs));

        return existingCusRefs.size();
    }

    private ExpertProductXmlItem toProduct(ZhurongScjinggongOrderitemDTO request) {
        return ExpertProductXmlItem.create()
                .reference(request.getPrdRef())
                .material(request.getMatRef())
                .machine(request.getWrkRef())
                .thickness(request.getThickness())
                .quantity(request.getQuantity())
                .ordRef(request.getOrdRef())
                .cusRef(request.getCusRef())
                .cusName(request.getCusName())
                .file(request.getDrawingPath())
                .userData1(request.getUdata1())
                .userData2(request.getUdata2())
                .userData3(request.getUdata3());
    }
}
