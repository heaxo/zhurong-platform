package com.zhurong.platform.custom.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.base.api.PageResponse;
import com.zhurong.platform.base.constant.NestConstant;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000100PageQuery;
import com.zhurong.platform.core.lantek.dto.RelationLoadPlan;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000100VO;
import com.zhurong.platform.core.lantek.vo.MmnnMmoo00000300VO;
import com.zhurong.platform.core.lantek.vo.PprrPprr00000100VO;
import com.zhurong.platform.custom.dto.XyRequests;
import com.zhurong.platform.custom.entity.DisNestNest00000100;
import com.zhurong.platform.custom.entity.PprrPprr00000100;
import com.zhurong.platform.custom.entity.XyNestFeedbackState;
import com.zhurong.platform.custom.feign.DisNestNest00000100FeignClient;
import com.zhurong.platform.custom.properties.XyBaoyuanProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class XyNestFeedbackService {
    private final DisNestNest00000100FeignClient nestClient;
    private final XyDataService dataService;
    private final ObjectMapper objectMapper;
    private final XyBaoyuanProperties properties;
    private final IPprrPprr00000100Service partService;
    private final IDisNestNest00000100Service localNestService;

    public PageResponse<Map<String, Object>> page(DisNestNest00000100PageQuery query) {
//        query.setLoadPlan(fullLoadPlan());
        PageResponse<DisNestNest00000100VO> page = nestClient.pageNestOverview(query).unwrap();
        Map<Integer, XyNestFeedbackState> states = dataService.feedbackStates(page.items().stream()
                        .map(DisNestNest00000100VO::getRecID).toList()).stream()
                .collect(Collectors.toMap(XyNestFeedbackState::getNestRecId, Function.identity()));
        List<Map<String, Object>> items = page.items().stream().map(nest -> {
            Map<String, Object> item = objectMapper.convertValue(nest, new TypeReference<>() {});
            XyNestFeedbackState state = states.get(nest.getRecID());
            item.put("feedbackSent", state != null && Boolean.TRUE.equals(state.getSent()));
            item.put("feedbackTime", state == null ? null : state.getSentAt());
            item.put("feedbackRemark", state == null ? null : state.getRemark());
            return item;
        }).toList();
        return new PageResponse<>(items, page.total(), page.current(), page.size());
    }

    public void send(XyRequests.Feedback request) {
        requireFeedbackConfig();
        requireText(request.getProductionWorkshopCode(), "生产车间不能为空");
        for (DisNestNest00000100VO nest : details(request.getIds())) {
            validateNest(nest);
            Map<String, Object> payload = buildPayload(nest, request.getProductionWorkshopCode(), request.isMaterialReceived());
            post(properties.getFeedback().getUrl(), payload, "套料反馈失败");
            dataService.saveFeedbackState(nest.getRecID(), nest.getNstRef(), true, "反馈成功");
            boolean update = localNestService.update(Wrappers.lambdaUpdate(DisNestNest00000100.class)
                    .set(DisNestNest00000100::getMState, NestConstant.MState.COMPLETED)
                    .eq(DisNestNest00000100::getRecID, nest.getRecID()));
            log.debug(String.format("套料程序：%s，回传状态更新：%s",nest.getRecID() , update));
        }
    }

    public void withdraw(List<Long> ids) {
        requireText(properties.getFeedback().getWithdrawUrl(), "撤销套料反馈接口未配置");
        requireText(properties.getFeedback().getSecretKey(), "套料反馈密钥未配置");
        for (DisNestNest00000100VO nest : details(ids)) {
            post(properties.getFeedback().getWithdrawUrl(), Map.of(
                    "secret_key", properties.getFeedback().getSecretKey(),
                    "NstRef", nest.getNstRef()), "撤销套料反馈失败");
            dataService.saveFeedbackState(nest.getRecID(), nest.getNstRef(), false, "已撤销");
            boolean update = localNestService.update(Wrappers.lambdaUpdate(DisNestNest00000100.class)
                    .set(DisNestNest00000100::getMState, NestConstant.MState.IN_WORKSHOP)
                    .eq(DisNestNest00000100::getRecID, nest.getRecID()));
            log.debug(String.format("套料程序：%s，撤销状态更新：%s",nest.getRecID() , update));
        }
    }

    private List<DisNestNest00000100VO> details(List<Long> ids) {
        if (ids == null || ids.isEmpty()) throw new IllegalArgumentException("请选择套料记录");
        DisNestNest00000100PageQuery query = new DisNestNest00000100PageQuery();
        query.setRecIds(ids.stream().map(Long::intValue).toList());
        query.setSize((long) ids.size());
        query.setLoadPlan(fullLoadPlan());
        List<DisNestNest00000100VO> result = nestClient.pageNestOverview(query).unwrap().items();
        if (result.size() != ids.size()) throw new IllegalArgumentException("部分套料记录不存在");
        return result;
    }

    private Map<String, Object> buildPayload(DisNestNest00000100VO nest, String workshop, boolean materialReceived) {
        PprrPprr00000100 sheet = partService.getOne(Wrappers.lambdaQuery(PprrPprr00000100.class)
                .eq(PprrPprr00000100::getPrdRef, nest.getShtRef()));
        PprrPprr00000100 original = resolveOriginalSheet(sheet, nest.getShtRefOrg(), new HashSet<>());
        int materialId = parseInt(original == null ? null : original.getDIS_UData2_Sht(),
                original == null ? null : original.getDIS_UData1_Prt());
        boolean remnantSheet = sheet != null && Objects.equals(sheet.getDIS_IsRemnant(), (byte) 1);
        double length = sheet != null && sheet.getDIS_Length() != null ? sheet.getDIS_Length() : value(nest.getSLength());
        double width = sheet != null && sheet.getDIS_Width() != null ? sheet.getDIS_Width() : value(nest.getSWidth());

        List<Map<String, Object>> parts = Optional.ofNullable(nest.getNestParts()).orElse(List.of()).stream().map(part -> {
            MmnnMmoo00000300VO order = part.getWorkOrder();
            PprrPprr00000100VO item = part.getItem();
            if (order == null || item == null) throw new IllegalArgumentException("套料零件缺少生产订单或零件主数据");
            if (!StringUtils.hasText(item.getDIS_UData3_Prt())) throw new IllegalArgumentException("零件ERP物料内码为空: " + item.getPrdRef());
            XyImportTaskService.ErpIdentity identity = XyImportTaskService.splitErpIdentity(order.getCusRef());
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("MoNumber", order.getOrdRef());
            row.put("MoRowSeq", identity.erpInternalCode());
            row.put("MoEntryId", identity.erpInternalCode());
            row.put("PrdRef", item.getDIS_UData3_Prt());
            row.put("Weight", value(item.getWeight()));
            row.put("Quantity", Optional.ofNullable(part.getQuantity()).orElse(0));
            row.put("DValue", value(item.getDIS_CutPerim()));
            row.put("PlanNumber", identity.planNumber());
            return row;
        }).toList();
        List<Map<String, Object>> remnants = Optional.ofNullable(nest.getNestRemnant()).orElse(List.of()).stream().map(remnant -> {
            PprrPprr00000100VO item = remnant.getItem();
            String originalRef = original == null ? nest.getShtRef() : original.getPrdRef();
            String remnantRef = remnant.getShtRef();
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("ShtRef", remnantRef);
            row.put("LotNumber", lotNumber(remnantRef, originalRef));
            row.put("Quantity", 1);
            row.put("SWeight", item == null ? 0D : value(item.getWeight()));
            row.put("SWidth", item == null ? 0D : Math.round(value(item.getDIS_Width())));
            row.put("SLength", item == null ? 0D : Math.round(value(item.getDIS_Length())));
            return row;
        }).toList();

        String cncPath = nest.getNestingDocument().getCNC();
        String pdfPath = resolvePdfPath(nest);
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("secret_key", properties.getFeedback().getSecretKey());
        payload.put("NstRef", nest.getNstRef()); payload.put("NstSeq", nest.getRecID());
        payload.put("NstMachine", nest.getWrkRef()); payload.put("MatQuality", nest.getMatRef());
        payload.put("Sheight", value(nest.getSThickness())); payload.put("FMATERIALID", materialId);
        payload.put("UMatType", remnantSheet ? "A" : "B"); payload.put("Slength", Math.round(length));
        payload.put("Swidth", Math.round(width)); payload.put("Quantity", Optional.ofNullable(nest.getQuantity()).orElse(0));
        payload.put("Sweight", value(nest.getSWeight())); payload.put("SUWeight", value(nest.getSUWeight()));
        payload.put("Sprofit", value(nest.getSProfit())); payload.put("SprofitS", value(nest.getSProfitS()));
        payload.put("CutLength", nest.getNestingAuxiliaryProperties() == null ? 0D : value(nest.getNestingAuxiliaryProperties().getBevelessCuttingLength()));
        payload.put("HoleCount", nest.getNestingAuxiliaryProperties() == null ? 0 : Math.round(value(nest.getNestingAuxiliaryProperties().getNumberOfPerforations())));
        payload.put("CNCPath", ftpUrl(cncPath, properties.getFeedback().getFtp().getCncVirtualRoot()));
        payload.put("PDFPath", ftpUrl(pdfPath, properties.getFeedback().getFtp().getPdfVirtualRoot()));
        payload.put("Remnants", json(remnants)); payload.put("Parts", json(parts));
        payload.put("WorkShop", workshop); payload.put("IsPick", materialReceived ? "1" : "0");
        return payload;
    }

    private void validateNest(DisNestNest00000100VO nest) {
        if (!Objects.equals(nest.getMState(), 40)) throw new IllegalStateException(nest.getNstRef() + "未送到车间，不允许反馈");
        checkFtpFile(nest.getNestingDocument().getCNC(), properties.getFeedback().getFtp().getCncVirtualRoot());
        String pdf = resolvePdfPath(nest);
        checkFtpFile(pdf, properties.getFeedback().getFtp().getPdfVirtualRoot());
    }

    private PprrPprr00000100 resolveOriginalSheet(PprrPprr00000100 current, String sourceRef, Set<String> visited) {
        if (current == null || !Objects.equals(current.getDIS_IsRemnant(), (byte) 1)) return current;
        if (!StringUtils.hasText(sourceRef) || !visited.add(sourceRef)) return current;
        PprrPprr00000100 source = partService.getOne(Wrappers.lambdaQuery(PprrPprr00000100.class)
                .eq(PprrPprr00000100::getPrdRef, sourceRef));
        if (source == null || !Objects.equals(source.getDIS_IsRemnant(), (byte) 1)) return source;
        DisNestNest00000100 sourceNest = localNestService.getOne(Wrappers.lambdaQuery(DisNestNest00000100.class)
                .eq(DisNestNest00000100::getShtRef, sourceRef).orderByDesc(DisNestNest00000100::getRecID), false);
        return resolveOriginalSheet(source, sourceNest == null ? source.getDIS_ShtRefOrg() : sourceNest.getShtRefOrg(), visited);
    }

    private void checkFtpFile(String fullPath, String virtualRoot) {
        String remote = remotePath(fullPath, virtualRoot);
        XyBaoyuanProperties.Ftp config = properties.getFeedback().getFtp();
        FTPClient client = new FTPClient();
        try {
            client.connect(config.getHost(), config.getPort());
            if (!client.login(config.getUsername(), config.getPassword())) throw new IllegalStateException("FTP登录失败");
            client.enterLocalPassiveMode();
            if (client.listFiles(remote).length == 0) throw new IllegalArgumentException("FTP文件不存在: " + remote);
        } catch (Exception exception) {
            if (exception instanceof RuntimeException runtimeException) throw runtimeException;
            throw new IllegalStateException("FTP文件检查失败: " + remote, exception);
        } finally {
            if (client.isConnected()) try { client.disconnect(); } catch (Exception ignored) { }
        }
    }

    private String ftpUrl(String fullPath, String virtualRoot) {
        String remote = remotePath(fullPath, virtualRoot).replace('\\', '/');
        XyBaoyuanProperties.Ftp ftp = properties.getFeedback().getFtp();
        String user = encode(ftp.getUsername()); String password = encode(ftp.getPassword());
        String path = Arrays.stream(remote.split("/", -1)).map(XyNestFeedbackService::encode).collect(Collectors.joining("/"));
        return "ftp://" + user + ":" + password + "@" + ftp.getHost() + ":" + ftp.getPort() + "/" + path.replaceFirst("^/+", "");
    }

    private String remotePath(String fullPath, String virtualRoot) {
        requireText(fullPath, "套料输出文件路径为空"); requireText(virtualRoot, "FTP虚拟目录未配置");
        int index = fullPath.toLowerCase(Locale.ROOT).indexOf(virtualRoot.toLowerCase(Locale.ROOT));
        if (index < 0) throw new IllegalArgumentException("文件路径不在FTP虚拟目录中: " + fullPath);
        String remote = fullPath.substring(index);
        return remote;
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

    private void post(String url, Object payload, String errorPrefix) {
        log.debug(String.format("请求接口=%s，请求报文=%s", url, payload));

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        // 建立连接超时
        requestFactory.setConnectTimeout(Duration.ofSeconds(30));

        // 响应读取超时，设置为 5 分钟
        requestFactory.setReadTimeout(Duration.ofMinutes(5));

        RestClient restClient = RestClient.builder()
                .requestFactory(requestFactory)
                .build();

        String body = restClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(payload)
                .retrieve()
                .body(String.class);

        try {
            log.debug(String.format("======响应======，请求接口=%s，%s", url, body));

            JsonNode response = objectMapper.readTree(body);

            if (response.path("Code").asInt(-1) != 0) {
                String message = response.hasNonNull("Message")
                        ? response.get("Message").asText()
                        : response.path("Msg").asText(errorPrefix);

                throw new IllegalArgumentException(message);
            }
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException(errorPrefix + ": 响应不是有效JSON", exception);
        }
    }

    private void requireFeedbackConfig() {
        requireText(properties.getFeedback().getUrl(), "套料反馈接口未配置");
        requireText(properties.getFeedback().getSecretKey(), "套料反馈密钥未配置");
        XyBaoyuanProperties.Ftp ftp = properties.getFeedback().getFtp();
        requireText(ftp.getHost(), "FTP主机未配置"); requireText(ftp.getUsername(), "FTP用户名未配置");
        requireText(ftp.getCncVirtualRoot(), "CNC FTP虚拟目录未配置"); requireText(ftp.getPdfVirtualRoot(), "PDF FTP虚拟目录未配置");
    }

    private RelationLoadPlan fullLoadPlan() {
        return new RelationLoadPlan().setIncludeNestParts(true).setIncludeNestRemnants(true)
                .setIncludeNestMetrics(true).setIncludeNestFiles(true).setIncludePartMaster(true).setIncludePlanMaster(true);
    }

    private String json(Object value) { try { return objectMapper.writeValueAsString(value); } catch (JsonProcessingException e) { throw new IllegalStateException(e); } }
    private static int parseInt(String... candidates) { for (String value : candidates) if (StringUtils.hasText(value)) try { return Integer.parseInt(value); } catch (NumberFormatException ignored) { } return 0; }
    private static String lotNumber(String remnantRef, String originalRef) { if (!StringUtils.hasText(remnantRef)) return ""; String prefix = originalRef + "-"; return remnantRef.startsWith(prefix) ? remnantRef.substring(prefix.length()) : remnantRef; }
    private static double value(Number value) { return value == null ? 0D : value.doubleValue(); }
    private static String encode(String value) { return URLEncoder.encode(Optional.ofNullable(value).orElse(""), StandardCharsets.UTF_8).replace("+", "%20"); }
    private static void requireText(String value, String message) { if (!StringUtils.hasText(value)) throw new IllegalArgumentException(message); }
}
