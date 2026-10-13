package com.zhurong.platform.custom.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.base.constant.NestConstant;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000100PageQuery;
import com.zhurong.platform.core.lantek.dto.ItemsDocument;
import com.zhurong.platform.core.lantek.dto.RelationLoadPlan;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000100VO;
import com.zhurong.platform.custom.convert.ZhurongScjinggongOrderConvert;
import com.zhurong.platform.custom.convert.ZhurongScjinggongOrderitemConvert;
import com.zhurong.platform.custom.dto.*;
import com.zhurong.platform.custom.entity.*;
import com.zhurong.platform.custom.feign.DisNestNest00000100FeignClient;
import com.zhurong.platform.custom.http.JsonHttpClient;
import com.zhurong.platform.custom.mapper.ZhurongScjinggongOrderMapper;
import com.zhurong.platform.custom.properties.ScjinggongProperties;
import com.zhurong.platform.custom.service.*;
import com.zhurong.platform.custom.vo.ZhurongScjinggongOrderVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ZhurongScjinggongOrderServiceImpl
        extends ServiceImpl<ZhurongScjinggongOrderMapper, ZhurongScjinggongOrder>
        implements IZhurongScjinggongOrderService {

    private static final int FTP_TIMEOUT_MILLIS = (int) Duration.ofSeconds(30).toMillis();
    private final DisNestNest00000100FeignClient nestClient;
    private final IZhurongScjinggongOrderitemService zhurongScjinggongOrderitemService;
    private final IDisMmttMmtt00000100Service disMmttMmtt00000100Service;
    private final IDisMmttMmtt00000200Service disMmttMmtt00000200Service;
    private final IWwccWwcc00000100Service wwccWwcc00000100Service;
    private final IDisNestNest00000100Service disNestNest00000100Service;
    private final ZhurongScjinggongOrderConvert convert;
    private final ZhurongScjinggongOrderitemConvert orderitemConvert;
    private final ObjectMapper objectMapper;
    private final ScjinggongProperties properties;
    private final JsonHttpClient jsonHttpClient;

    private static String encode(String value) {
        return URLEncoder.encode(Optional.ofNullable(value).orElse(""), StandardCharsets.UTF_8).replace("+", "%20");
    }

    private static void requireText(String value, String message) {
        if (!StringUtils.hasText(value)) throw new IllegalArgumentException(message);
    }

    private String json(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (
                JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean feedback(ZhurongScjinggongOrderDTO request) {
        List<String> nestRecIds = request.getNestRecIds();
        String jobRef = request.getJobRef();
        if (nestRecIds.isEmpty() && com.baomidou.mybatisplus.core.toolkit.StringUtils.isBlank(jobRef)) {
            throw new BusinessException("套料ID不能为空");
        }

        DisNestNest00000100PageQuery query = new DisNestNest00000100PageQuery();
        query.setJobRef(jobRef);
        if (!nestRecIds.isEmpty()) {
            query.setRecIds(nestRecIds.stream().map(Integer::parseInt).toList());
            query.setSize((long) nestRecIds.size());
        } else {
            query.setSize(-1L);
        }

        query.setLoadPlan(fullLoadPlan());
        List<DisMmttMmtt00000100> materials = disMmttMmtt00000100Service.list();
        List<DisMmttMmtt00000200> materialPrices = disMmttMmtt00000200Service.list();
        List<DisNestNest00000100VO> result = nestClient.pageNestOverview(query).unwrap().items();
        NestFeedbackRequest nestFeedbackRequest = new NestFeedbackRequest();
        nestFeedbackRequest.setData(result.stream().map(nest -> {

            validateNest(nest);

            NestFeedbackRequestDTO nestFeedbackRequestDTO = new NestFeedbackRequestDTO();
            nestFeedbackRequestDTO.setJobRef(nest.getJob().getJobRef());
            nestFeedbackRequestDTO.setJobName(nest.getJob().getJobName());
            nestFeedbackRequestDTO.setUdata1(nest.getJob().getUData1());
            nestFeedbackRequestDTO.setUdata2(nest.getJob().getUData2());
            nestFeedbackRequestDTO.setUdata3(nest.getJob().getUData3());
            nestFeedbackRequestDTO.setUdata4(nest.getJob().getUData4());
            nestFeedbackRequestDTO.setUdata5(nest.getJob().getUData5());
            nestFeedbackRequestDTO.setNstRef(nest.getNstRef());
            nestFeedbackRequestDTO.setRecId(nest.getRecID().toString());
            nestFeedbackRequestDTO.setShtRef(nest.getShtRef());
            nestFeedbackRequestDTO.setMatRef(nest.getMatRef());
            nestFeedbackRequestDTO.setLength(nest.getSLength());
            nestFeedbackRequestDTO.setWidth(nest.getSWidth());
            nestFeedbackRequestDTO.setShtRefOrg(nest.getShtRefOrg());

            Optional<DisMmttMmtt00000200> priceOption = materialPrices.stream().filter(it -> it.getMatRef().equalsIgnoreCase(nest.getMatRef()) &&
                            it.getMinThickness() < nest.getSThickness() && it.getMaxThickness() > nest.getSThickness())
                    .findFirst();

            if (priceOption.isPresent()) {
                nestFeedbackRequestDTO.setShtPrice(priceOption.get().getPrice() == null ? 0 : priceOption.get().getPrice().doubleValue());
            } else {
                Optional<DisMmttMmtt00000100> material = materials.stream().filter(it -> it.getMatRef().equalsIgnoreCase(nest.getMatRef()))
                        .findFirst();

                if (material.isEmpty()) {
                    throw new BusinessException(String.format("材质当档案不存在，需检查材质：%s", nest.getMatRef()));
                }
                nestFeedbackRequestDTO.setShtPrice(material.get().getPrice());
            }
            String cncPath = nest.getNestingDocument().getCNC();
            String dxfPath = nest.getNestingDocument().getDXF();
            nestFeedbackRequestDTO.setQuantity(nest.getQuantity());
            nestFeedbackRequestDTO.setSprofit(nest.getSProfit());
            nestFeedbackRequestDTO.setSprofitS(nest.getSProfitS());
            nestFeedbackRequestDTO.setQty(nest.getQuantity());
            nestFeedbackRequestDTO.setDxfPath(ftpUrl(dxfPath, properties.getFeedback().getFtp().getDxfVirtualRoot()));
            nestFeedbackRequestDTO.setNcPath(ftpUrl(cncPath, properties.getFeedback().getFtp().getCncVirtualRoot()));
            nestFeedbackRequestDTO.setExpectDuration(nest.getNestingAuxiliaryProperties().getNormalCuttingDuration());
            nestFeedbackRequestDTO.setCuttingLength(nest.getNestingAuxiliaryProperties().getBevelessCuttingLength());
            nestFeedbackRequestDTO.setRapidDuration(nest.getNestingAuxiliaryProperties().getDisplacementDuration());
            nestFeedbackRequestDTO.setRapidLength(nest.getNestingAuxiliaryProperties().getDisplacementLength());
            nestFeedbackRequestDTO.setMarkingDuration(nest.getNestingAuxiliaryProperties().getMarkingDuration());
            nestFeedbackRequestDTO.setMarkingLength(nest.getNestingAuxiliaryProperties().getMarkingLength());
            nestFeedbackRequestDTO.setPiercingDuration(0.0D);
            nestFeedbackRequestDTO.setPiercingCount(nest.getNestingAuxiliaryProperties().getNumberOfPerforations());
            nestFeedbackRequestDTO.setAuxiliarDuration(nest.getNestingAuxiliaryProperties().getAuxiliarTime());
            nestFeedbackRequestDTO.setTotalDuration(nest.getNestingAuxiliaryProperties().getTotalProcessingTime());
            nestFeedbackRequestDTO.setWrkRef(nest.getWrkRef());
            nestFeedbackRequestDTO.setWeight(nest.getSWeight());
            nestFeedbackRequestDTO.setScrapWeight(nest.getScrapWeight());
            if (!nest.getNestRemnant().isEmpty()) {
                List<NestFeedbackRequestDTO.Surplus> remnants = nest.getNestRemnant().stream().map(it -> {
                    NestFeedbackRequestDTO.Surplus surplus = new NestFeedbackRequestDTO.Surplus();
                    surplus.setCode(it.getShtRef());
                    surplus.setLength(it.getItem().getDIS_Length());
                    surplus.setWidth(it.getItem().getDIS_Width());
                    surplus.setThick(nest.getSThickness());
                    surplus.setWeight(it.getItem().getWeight());
                    return surplus;
                }).toList();
                nestFeedbackRequestDTO.setSurplus(remnants);
            }
            List<NestFeedbackRequestDTO.Mat> mats = nest.getNestParts().stream().map(it -> {
                NestFeedbackRequestDTO.Mat mat = new NestFeedbackRequestDTO.Mat();
                ItemsDocument itemsDocument = it.getItem().getItemsDocument();
                String dxf = itemsDocument.getDXF();
                mat.setCode(it.getWorkOrder().getOrdRef());
                mat.setIOrder(it.getWorkOrder().getIOrder());
                mat.setPrdRef(it.getPrdRefDst());
                mat.setLength(it.getItem().getDIS_Length());
                mat.setWidth(it.getItem().getDIS_Width());
                mat.setThick(nest.getSThickness());
                mat.setWeight(it.getItem().getWeight());
                mat.setArea(it.getItem().getDIS_Area());
                mat.setUdata1(it.getItem().getDIS_UData1_Prt());
                mat.setUdata2(it.getItem().getDIS_UData2_Prt());
                mat.setUdata3(it.getItem().getDIS_UData3_Prt());
                mat.setUdata4(it.getItem().getDIS_UData4_Prt());
                mat.setUdata5(it.getItem().getDIS_UData5_Prt());
                mat.setUdata6(it.getItem().getDIS_UData6_Prt());
                mat.setUdata7(it.getItem().getDIS_UData7_Prt());
                mat.setUdata8(it.getItem().getDIS_UData8_Prt());
                mat.setQuantity(it.getQuantity());
                mat.setProcessDuration(String.valueOf(it.getItem().getSheetPartsAuxiliaryData().getTotalProcessingTime()));
                mat.setDxfPath(ftpUrl(dxf, properties.getFeedback().getFtp().getPartDxfVirtualRoot()));
                return mat;
            }).toList();
            nestFeedbackRequestDTO.setMats(mats);
            return nestFeedbackRequestDTO;
        }).toList());

        String jsonStr = json(nestFeedbackRequest);
        log.debug("--套料反馈--：{}", jsonStr);
        String url = properties.getFeedback().getUrl();
        if (!StringUtils.hasText(url)) {
            throw new BusinessException("套料反馈接口未配置");
        }
        NestFeedbackResponse response = jsonHttpClient.postJson(url, nestFeedbackRequest, new TypeReference<NestFeedbackResponse>() {
        });

        if (response == null || !"true".equalsIgnoreCase(response.getSuccess())) {
            throw new BusinessException(String.format("套料数据请求异常：%s", response == null ? "" : response.getMessage()));
        }

        LambdaUpdateWrapper<DisNestNest00000100> wrapper = Wrappers.lambdaUpdate(DisNestNest00000100.class)
                .set(DisNestNest00000100::getMState, NestConstant.MState.COMPLETED)
                .eq(DisNestNest00000100::getJobRef, jobRef);

        if (!nestRecIds.isEmpty()) {
            wrapper.in(DisNestNest00000100::getRecID, nestRecIds);
        }

        return disNestNest00000100Service.update(wrapper);
    }

    @Override
    public boolean withdraw(ZhurongScjinggongOrderDTO request) {
        List<String> nestRecIds = request.getNestRecIds();
        NestFeedbackRequest nestFeedbackRequest = new NestFeedbackRequest();
        nestFeedbackRequest.setData(nestRecIds.stream().map(id -> new NestFeedbackRequestDTO().setRecId(id)).toList());

        String jsonStr = json(nestFeedbackRequest);
        log.debug("--套料撤销--：{}", jsonStr);
        String url = properties.getFeedback().getWithdrawUrl();
        if (!StringUtils.hasText(url)) {
            throw new BusinessException("套料撤销接口未配置");
        }
        NestFeedbackResponse response = jsonHttpClient.postJson(url, nestFeedbackRequest, new TypeReference<NestFeedbackResponse>() {
        });

        if (response == null || !"true".equalsIgnoreCase(response.getSuccess())) {
            throw new BusinessException(String.format("套料撤销请求异常：%s", response == null ? "" : response.getMessage()));
        }

        LambdaUpdateWrapper<DisNestNest00000100> wrapper = Wrappers.lambdaUpdate(DisNestNest00000100.class)
                .set(DisNestNest00000100::getMState, NestConstant.MState.IN_WORKSHOP);
        wrapper.in(DisNestNest00000100::getRecID, nestRecIds);
        return disNestNest00000100Service.update(wrapper);
    }

    private RelationLoadPlan fullLoadPlan() {
        return new RelationLoadPlan()
                .setIncludeJobCard(true)
                .setIncludeNestPartMetrics(true)
                .setIncludePartFiles(true)
                .setIncludeNestParts(true).setIncludeNestRemnants(true)
                .setIncludeNestMetrics(true).setIncludeNestFiles(true).setIncludePartMaster(true).setIncludePlanMaster(true);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean creates(OrderRequest request) {

        List<OrderItemRequestDTO> data = request.getData();
        String orderCode = request.getOrderCode();
        String orderName = request.getOrderName();

        ZhurongScjinggongOrder existing = getOne(Wrappers.lambdaQuery(ZhurongScjinggongOrder.class)
                .eq(ZhurongScjinggongOrder::getOrderCode, orderCode));

        if (existing != null) {
            throw new BusinessException(String.format("批次号已存在，无法重复保存：%s", orderCode));
        }

        //MES工单号
        List<String> cusRefs = data.stream().map(OrderItemRequestDTO::getCusRef).distinct().toList();

        if (cusRefs.size() < data.size()) {
            throw new BusinessException("MES工单号重复，请检查");
        }

        List<ZhurongScjinggongOrderitem> existings = zhurongScjinggongOrderitemService.list(Wrappers.lambdaQuery(ZhurongScjinggongOrderitem.class)
                .in(ZhurongScjinggongOrderitem::getCusRef, cusRefs));

        if (!existings.isEmpty()) {
            List<String> keys = existings.stream().map(ZhurongScjinggongOrderitem::getCusRef).distinct().toList();
            throw new BusinessException(String.format("MES工单已存在，无法重复下发：%s", String.join(",", keys)));
        }

        List<WwccWwcc00000100> wwccs = wwccWwcc00000100Service.list();
//        List<DisMmttMmtt00000100> mmtts = disMmttMmtt00000100Service.list();

        List<String> wrkRefs = wwccs.stream().map(WwccWwcc00000100::getWrkRef).distinct().toList();
//        List<String> matRefs = mmtts.stream().map(DisMmttMmtt00000100::getMatRef).distinct().toList();

        List<String> nonWrkRefs = new ArrayList<>();
//        List<String> nonMatRefs = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            OrderItemRequestDTO d = data.get(i);
            if (!wrkRefs.contains(d.getWrkRef()) && !nonWrkRefs.contains(d.getWrkRef())) {
                nonWrkRefs.add(d.getWrkRef());
            }
        }

        if (!nonWrkRefs.isEmpty()) {
            throw new BusinessException(String.format("机床未维护，请打开套料软件维护相关机床：%s", String.join(",", nonWrkRefs)));
        }

        List<ZhurongScjinggongOrderitem> entitys = orderitemConvert.toEntitys(data);

        ZhurongScjinggongOrder zhurongScjinggongOrder = new ZhurongScjinggongOrder();
        zhurongScjinggongOrder.setOrderCode(orderCode);
        zhurongScjinggongOrder.setOrderName(orderName);

        boolean save = save(zhurongScjinggongOrder);

        if (!save) {
            return false;
        }

        entitys.forEach(it -> it.setOrderId(zhurongScjinggongOrder.getId()));

        boolean batch = zhurongScjinggongOrderitemService.saveBatch(entitys);

        if (!batch) {
            throw new BusinessException("订单保存失败，请重试");
        }
        return true;
    }

    @Override
    public ZhurongScjinggongOrderVO getVOById(Long id) {
        ZhurongScjinggongOrder entity = this.getById(id);
        return convert.toVO(entity);
    }

    @Override
    public Long saveFromDTO(ZhurongScjinggongOrderDTO dto) {
        ZhurongScjinggongOrder entity = convert.toEntity(dto);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Boolean updateFromDTO(Long id, ZhurongScjinggongOrderDTO dto) {
        ZhurongScjinggongOrder entity = this.getById(id);
        convert.updateFromDTO(dto, entity);
        return this.updateById(entity);
    }

    private void validateNest(DisNestNest00000100VO nest) {
        if (!Objects.equals(nest.getMState(), 40))
            throw new IllegalStateException(nest.getNstRef() + "未送到车间，不允许反馈");
        checkFtpFile(nest.getNestingDocument().getCNC(), properties.getFeedback().getFtp().getCncVirtualRoot());
//        String pdf = resolvePdfPath(nest);
//        checkFtpFile(pdf, properties.getFeedback().getFtp().getPdfVirtualRoot());
    }

    private void checkFtpFile(String fullPath, String virtualRoot) {
        String remote = remoteFtpPath(fullPath, virtualRoot);
        ScjinggongProperties.Ftp config = properties.getFeedback().getFtp();
        FTPClient client = ftpClient();
        try {
            client.connect(config.getHost(), config.getPort());
            if (!FTPReply.isPositiveCompletion(client.getReplyCode())) throw new IllegalStateException("FTP连接失败");
            if (!client.login(config.getUsername(), config.getPassword()))
                throw new IllegalStateException("FTP登录失败");
            client.enterLocalPassiveMode();
            client.setFileType(FTP.BINARY_FILE_TYPE);
            if (!ftpFileExists(client, remote)) throw new IllegalArgumentException("FTP文件不存在: " + remote);
        } catch (Exception exception) {
            if (exception instanceof RuntimeException runtimeException) throw runtimeException;
            throw new IllegalStateException("FTP文件检查失败: " + remote, exception);
        } finally {
            closeFtpClient(client);
        }
    }

    private String ftpUrl(String fullPath, String virtualRoot) {
        String remote = remoteFtpPath(fullPath, virtualRoot);
        ScjinggongProperties.Ftp ftp = properties.getFeedback().getFtp();
        String user = encode(ftp.getUsername());
        String password = encode(ftp.getPassword());
        String path = Arrays.stream(remote.split("/", -1)).map(ZhurongScjinggongOrderServiceImpl::encode).collect(Collectors.joining("/"));
        return "ftp://" + user + ":" + password + "@" + ftp.getHost() + ":" + ftp.getPort() + "/" + path.replaceFirst("^/+", "");
    }

    private String remoteFtpPath(String fullPath, String virtualRoot) {
        return remotePath(fullPath, virtualRoot).replace('\\', '/');
    }

    private String remotePath(String fullPath, String virtualRoot) {
        requireText(fullPath, "套料输出文件路径为空");
        requireText(virtualRoot, "FTP虚拟目录未配置");
        int index = fullPath.toLowerCase(Locale.ROOT).indexOf(virtualRoot.toLowerCase(Locale.ROOT));
        if (index < 0) throw new IllegalArgumentException("文件路径不在FTP虚拟目录中: " + fullPath);
        String remote = fullPath.substring(index);
        return remote;
    }

    private FTPClient ftpClient() {
        FTPClient client = new FTPClient();
        client.setAutodetectUTF8(false);
        client.setDefaultTimeout(FTP_TIMEOUT_MILLIS);
        client.setConnectTimeout(FTP_TIMEOUT_MILLIS);
        client.setDataTimeout(Duration.ofMillis(FTP_TIMEOUT_MILLIS));
        return client;
    }

    private boolean ftpFileExists(FTPClient client, String remote) throws IOException {
        if (ftpFileExistsByPath(client, remote)) return true;

        String utf8CommandPath = utf8Iso88591CommandPath(remote);
        if (Objects.equals(utf8CommandPath, remote)) return false;

        client.setControlEncoding(StandardCharsets.ISO_8859_1.name());
        return ftpFileExistsByPath(client, utf8CommandPath);
    }

    private boolean ftpFileExistsByPath(FTPClient client, String remote) throws IOException {
        FTPFile direct = client.mlistFile(remote);
        if (direct != null) return true;
        if (client.listFiles(remote).length > 0) return true;

        int separator = remote.lastIndexOf('/');
        if (separator < 0 || separator == remote.length() - 1) return false;

        String parent = separator == 0 ? "/" : remote.substring(0, separator);
        String fileName = remote.substring(separator + 1);
        String originalWorkingDirectory = client.printWorkingDirectory();
        try {
            if (!client.changeWorkingDirectory(parent)) return false;
            return Arrays.stream(client.listFiles()).anyMatch(file -> Objects.equals(file.getName(), fileName));
        } finally {
            if (StringUtils.hasText(originalWorkingDirectory)) {
                client.changeWorkingDirectory(originalWorkingDirectory);
            }
        }
    }

    private String utf8Iso88591CommandPath(String remote) {
        return new String(remote.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
    }

    private void closeFtpClient(FTPClient client) {
        if (!client.isConnected()) return;
        try {
            client.logout();
        } catch (Exception ignored) {
        }
        try {
            client.disconnect();
        } catch (Exception ignored) {
        }
    }

    private String resolvePdfPath(DisNestNest00000100VO nest) {
        String source = nest.getNestingDocument().getFullPathBMP();
        if (!StringUtils.hasText(source) && nest.getNestingDocument() != null) {
            source = nest.getNestingDocument().getFullPathJOBRPT();
            if (!StringUtils.hasText(source)) source = nest.getNestingDocument().getJOBRPT();
        }
        requireText(source, "套料PDF源文件路径为空");
        int extension = source.lastIndexOf('.');
        return (extension > source.lastIndexOf('\\') && extension > source.lastIndexOf('/'))
                ? source.substring(0, extension) + ".pdf" : source + ".pdf";
    }
}
