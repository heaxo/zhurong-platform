package com.zhurong.platform.custom.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zhurong.platform.base.constant.NestConstant;
import com.zhurong.platform.base.exception.BusinessException;
import com.zhurong.platform.core.lantek.dto.DisNestNest00000100PageQuery;
import com.zhurong.platform.core.lantek.dto.RelationLoadPlan;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000100VO;
import com.zhurong.platform.core.lantek.vo.DisNestNest00000500VO;
import com.zhurong.platform.core.lantek.vo.MmnnMmoo00000300VO;
import com.zhurong.platform.core.lantek.vo.PprrPprr00000100VO;
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
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
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
    private static final Duration HTTP_CONNECT_TIMEOUT = Duration.ofSeconds(30);
    private static final Duration HTTP_READ_TIMEOUT = Duration.ofMinutes(5);
    private static final DateTimeFormatter API_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String AUTHORIZATION_API_NAME = "getAuthorization";
    private static final String NEST_FEEDBACK_API_NAME = "TbSetMaterial";
    private static final Set<String> SENSITIVE_JSON_FIELDS = Set.of("secret", "accesstoken", "token", "appid");
    private final DisNestNest00000100FeignClient nestClient;
    private final IZhurongScjinggongOrderitemService zhurongScjinggongOrderitemService;
    private final IDisMmttMmtt00000100Service disMmttMmtt00000100Service;
    private final IDisMmttMmtt00000200Service disMmttMmtt00000200Service;
    private final IWwccWwcc00000100Service wwccWwcc00000100Service;
    private final IDisNestNest00000100Service disNestNest00000100Service;
    private final IZhurongScjinggongNestPartFeedbackStateService nestPartFeedbackStateService;
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

    /*@Override
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
    }*/


    /**
     * 按生产批次回传套料零件，并以 NEST500 RecID 作为幂等粒度记录结果。
     * <p>
     * 此流程不使用一个覆盖全部远程调用的数据库事务：部分批次调用失败时，之前已经成功的
     * NEST500 状态必须保留，后续重试才能只发送尚未成功的零件。
     */
    @Override
    public synchronized boolean feedback(ZhurongScjinggongOrderDTO request) {
        String traceId = UUID.randomUUID().toString().substring(0, 8);
        log.info("套料反馈开始，traceId={}，nestRecIds={}，jobRef={}",
                traceId,
                request == null ? null : request.getNestRecIds(),
                request == null ? null : request.getJobRef());
        try {
            return executeFeedback(request, traceId);
        } catch (BusinessException exception) {
            // 可预期的参数或业务失败不打印堆栈，避免联调日志被重复异常栈淹没。
            log.warn("套料反馈未完成，traceId={}，message={}", traceId, compactErrorMessage(exception));
            throw exception;
        } catch (RuntimeException exception) {
            log.error("套料反馈异常，traceId={}，errorType={}，message={}",
                    traceId,
                    exception.getClass().getSimpleName(),
                    compactErrorMessage(exception),
                    exception);
            throw exception;
        }
    }

    private boolean executeFeedback(ZhurongScjinggongOrderDTO request, String traceId) {
        if (request == null) {
            throw new BusinessException("套料反馈请求不能为空");
        }
        List<String> nestRecIds = Optional.ofNullable(request.getNestRecIds()).orElse(List.of()).stream()
                .filter(StringUtils::hasText)
                .distinct()
                .toList();
        String jobRef = request.getJobRef();
        if (nestRecIds.isEmpty() && com.baomidou.mybatisplus.core.toolkit.StringUtils.isBlank(jobRef)) {
            throw new BusinessException("套料ID不能为空");
        }

        DisNestNest00000100PageQuery query = new DisNestNest00000100PageQuery();
        query.setJobRef(jobRef);
        if (!nestRecIds.isEmpty()) {
            try {
                query.setRecIds(nestRecIds.stream().map(Integer::parseInt).toList());
            } catch (NumberFormatException exception) {
                throw new BusinessException("套料ID必须为整数");
            }
            query.setSize((long) nestRecIds.size());
        } else {
            query.setSize(-1L);
        }

        // 一次加载 NEST500、零件档案和生产计划，避免后续逐条访问 Lantek 数据库。
        query.setLoadPlan(new RelationLoadPlan()
                .setIncludeNestParts(true)
                .setIncludePartMaster(true)
                .setIncludePlanMaster(true));
        List<DisNestNest00000100VO> nests = nestClient.pageNestOverview(query).unwrap().items();
        validateFeedbackNests(nests, query.getRecIds());

        Map<Integer, DisNestNest00000500VO> nestParts = collectNestParts(nests);
        Map<Integer, ZhurongScjinggongNestPartFeedbackState> states = loadFeedbackStates(nestParts.keySet());
        Set<Integer> successfulPartIds = states.values().stream()
                .filter(state -> Boolean.TRUE.equals(state.getSent()))
                .map(ZhurongScjinggongNestPartFeedbackState::getNestPartRecId)
                .collect(Collectors.toCollection(HashSet::new));

        // 成功状态是幂等依据；失败或从未发送的 NEST500 才参与本轮分组。
        List<NestPartContext> pendingParts = resolveNestPartContexts(nests, successfulPartIds);
        log.info("套料反馈数据准备完成，traceId={}，nestCount={}，nestPartCount={}，skippedSuccessCount={}，pendingCount={}",
                traceId, nests.size(), nestParts.size(), successfulPartIds.size(), pendingParts.size());

        List<String> failures = new ArrayList<>();
        if (!pendingParts.isEmpty()) {
            String bearerToken;
            try {
                bearerToken = requestAuthorizationToken(traceId);
            } catch (RuntimeException exception) {
                String message = compactErrorMessage(exception);
                saveFeedbackStates(pendingParts, states, false, "认证失败：" + message, traceId);
                throw new BusinessException("获取Authorization失败：" + message);
            }

            // 一个 NEST100 可能包含多个生产批次，同一批次也可能跨多个 NEST100，故按本地订单分组。
            Map<OrderGroupKey, List<NestPartContext>> groupedParts = pendingParts.stream()
                    .collect(Collectors.groupingBy(
                            context -> new OrderGroupKey(
                                    context.order().getId(),
                                    context.order().getOrderCode(),
                                    context.order().getOrderName()
                            ),
                            LinkedHashMap::new,
                            Collectors.toList()
                    ));

            for (Map.Entry<OrderGroupKey, List<NestPartContext>> entry : groupedParts.entrySet()) {
                OrderGroupKey group = entry.getKey();
                List<NestPartContext> parts = entry.getValue();
                try {
                    ScjinggongNestFeedbackRequest feedbackRequest = buildFeedbackRequest(group, parts);
                    log.info("套料反馈请求报文，traceId={}，orderCode={}，json={}",
                            traceId, group.orderCode(), maskedRequestJson(feedbackRequest));
                    ScjinggongNestFeedbackResponse response = postApi(
                            properties.getFeedback().getUrl(),
                            feedbackRequest,
                            "Bearer " + bearerToken,
                            ScjinggongNestFeedbackResponse.class
                    );
                    if (response == null
                            || !Objects.equals(response.getCode(), 0)
                            || !Boolean.TRUE.equals(response.getData())) {
                        throw new IllegalStateException(response == null
                                ? "接口未返回数据"
                                : Optional.ofNullable(response.getMessage()).orElse("接口返回失败"));
                    }
                } catch (RuntimeException exception) {
                    String message = compactErrorMessage(exception);
                    saveFeedbackStates(parts, states, false, message, traceId);
                    log.warn("套料反馈分组失败，traceId={}，orderCode={}，partCount={}，nestPartRecIds={}，message={}",
                            traceId, group.orderCode(), parts.size(), nestPartRecIds(parts), message);
                    failures.add("批次%s：%s".formatted(group.orderCode(), message));
                    continue;
                }

                saveFeedbackStates(parts, states, true, "反馈成功", traceId);
                parts.stream()
                        .map(context -> context.nestPart().getRecID())
                        .forEach(successfulPartIds::add);
                log.info("套料反馈分组成功，traceId={}，orderCode={}，partCount={}，nestPartRecIds={}",
                        traceId, group.orderCode(), parts.size(), nestPartRecIds(parts));
            }
        }

        // 只有某个 NEST100 下的全部 NEST500 都已成功，才允许把主记录推进到完成态。
        markCompletedNests(nests, successfulPartIds, traceId);
        if (!failures.isEmpty()) {
            throw new BusinessException("部分套料数据反馈失败，重试时将自动跳过已成功零件：" + String.join("；", failures));
        }
        log.info("套料反馈完成，traceId={}，nestCount={}，successfulNestPartCount={}",
                traceId, nests.size(), successfulPartIds.size());
        return true;
    }

    private void validateFeedbackNests(List<DisNestNest00000100VO> nests, List<Integer> requestedIds) {
        if (nests == null || nests.isEmpty()) {
            throw new BusinessException("未查询到套料程序");
        }

        Map<Integer, DisNestNest00000100VO> uniqueNests = new LinkedHashMap<>();
        for (DisNestNest00000100VO nest : nests) {
            if (nest.getRecID() == null) {
                throw new BusinessException("套料程序缺少RecID");
            }
            if (uniqueNests.putIfAbsent(nest.getRecID(), nest) != null) {
                throw new BusinessException("查询到重复的套料程序RecID：" + nest.getRecID());
            }
            if (Objects.equals(nest.getMState(), NestConstant.MState.COMPLETED)) {
                throw new BusinessException("套料程序已反馈成功，不允许重复反馈：" + nestDisplayName(nest));
            }
            if (nest.getNestParts() == null || nest.getNestParts().isEmpty()) {
                throw new BusinessException("套料程序不包含套料零件：" + nestDisplayName(nest));
            }
        }

        if (requestedIds != null && !requestedIds.isEmpty()) {
            List<Integer> missingIds = requestedIds.stream()
                    .filter(id -> !uniqueNests.containsKey(id))
                    .toList();
            if (!missingIds.isEmpty()) {
                throw new BusinessException("部分套料程序不存在：" + missingIds);
            }
        }
    }

    private Map<Integer, DisNestNest00000500VO> collectNestParts(List<DisNestNest00000100VO> nests) {
        Map<Integer, DisNestNest00000500VO> result = new LinkedHashMap<>();
        for (DisNestNest00000100VO nest : nests) {
            for (DisNestNest00000500VO part : nest.getNestParts()) {
                if (part.getRecID() == null) {
                    throw new BusinessException("套料程序%s中存在缺少RecID的NEST500零件".formatted(nestDisplayName(nest)));
                }
                if (result.putIfAbsent(part.getRecID(), part) != null) {
                    throw new BusinessException("查询到重复的NEST500 RecID：" + part.getRecID());
                }
            }
        }
        return result;
    }

    private Map<Integer, ZhurongScjinggongNestPartFeedbackState> loadFeedbackStates(Collection<Integer> partRecIds) {
        if (partRecIds.isEmpty()) {
            return new LinkedHashMap<>();
        }
        return nestPartFeedbackStateService.list(
                        Wrappers.lambdaQuery(ZhurongScjinggongNestPartFeedbackState.class)
                                .in(ZhurongScjinggongNestPartFeedbackState::getNestPartRecId, partRecIds)
                ).stream()
                .collect(Collectors.toMap(
                        ZhurongScjinggongNestPartFeedbackState::getNestPartRecId,
                        Function.identity(),
                        (left, right) -> {
                            throw new BusinessException("NEST500反馈状态存在重复记录：" + left.getNestPartRecId());
                        },
                        LinkedHashMap::new
                ));
    }

    /**
     * 建立 NEST500 -> 生产计划 -> MES工单 -> 生产批次的完整关联。
     */
    private List<NestPartContext> resolveNestPartContexts(
            List<DisNestNest00000100VO> nests,
            Set<Integer> successfulPartIds
    ) {
        List<NestPartCandidate> pendingParts = new ArrayList<>();
        Map<WorkOrderKey, MmnnMmoo00000300VO> workOrders = new LinkedHashMap<>();

        for (DisNestNest00000100VO nest : nests) {
            // 兼容详情接口返回 jobParts，以及分页接口把生产计划挂在 nestPart.workOrder 的两种结构。
            for (MmnnMmoo00000300VO workOrder : Optional.ofNullable(nest.getJobParts()).orElse(List.of())) {
                putWorkOrder(workOrders, workOrder);
            }
            for (DisNestNest00000500VO part : nest.getNestParts()) {
                putWorkOrder(workOrders, part.getWorkOrder());
                if (!successfulPartIds.contains(part.getRecID())) {
                    pendingParts.add(new NestPartCandidate(nest, part));
                }
            }
        }

        if (pendingParts.isEmpty()) {
            return List.of();
        }

        Map<Integer, MmnnMmoo00000300VO> partWorkOrders = new LinkedHashMap<>();
        Set<String> cusRefs = new LinkedHashSet<>();
        for (NestPartCandidate candidate : pendingParts) {
            DisNestNest00000500VO part = candidate.nestPart();
            if (!StringUtils.hasText(part.getMnORef()) || part.getOprID() == null) {
                throw new BusinessException("NEST500零件%s缺少MnORef或OprID".formatted(part.getRecID()));
            }
            WorkOrderKey key = new WorkOrderKey(part.getMnORef(), part.getOprID());
            MmnnMmoo00000300VO workOrder = workOrders.get(key);
            if (workOrder == null) {
                throw new BusinessException("NEST500零件%s未找到生产计划：%s".formatted(part.getRecID(), key.displayName()));
            }
            if (!StringUtils.hasText(workOrder.getCusRef())) {
                throw new BusinessException("生产计划%s缺少CusRef".formatted(key.displayName()));
            }
            partWorkOrders.put(part.getRecID(), workOrder);
            cusRefs.add(workOrder.getCusRef());
        }

        Map<String, ZhurongScjinggongOrderitem> orderItems = zhurongScjinggongOrderitemService.list(
                        Wrappers.lambdaQuery(ZhurongScjinggongOrderitem.class)
                                .in(ZhurongScjinggongOrderitem::getCusRef, cusRefs)
                ).stream()
                .collect(Collectors.toMap(
                        ZhurongScjinggongOrderitem::getCusRef,
                        Function.identity(),
                        (left, right) -> {
                            throw new BusinessException("MES工单号存在重复记录：" + left.getCusRef());
                        },
                        LinkedHashMap::new
                ));

        List<String> missingCusRefs = cusRefs.stream()
                .filter(cusRef -> !orderItems.containsKey(cusRef))
                .toList();
        if (!missingCusRefs.isEmpty()) {
            throw new BusinessException("未找到MES工单：" + String.join(",", missingCusRefs));
        }

        Set<Long> orderIds = orderItems.values().stream()
                .map(ZhurongScjinggongOrderitem::getOrderId)
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        List<String> orderlessCusRefs = orderItems.values().stream()
                .filter(item -> item.getOrderId() == null)
                .map(ZhurongScjinggongOrderitem::getCusRef)
                .toList();
        if (!orderlessCusRefs.isEmpty()) {
            throw new BusinessException("MES工单缺少orderId：" + String.join(",", orderlessCusRefs));
        }

        Map<Long, ZhurongScjinggongOrder> orders = listByIds(orderIds).stream()
                .collect(Collectors.toMap(ZhurongScjinggongOrder::getId, Function.identity()));
        List<Long> notFoundOrderIds = orderIds.stream().filter(id -> !orders.containsKey(id)).toList();
        if (!notFoundOrderIds.isEmpty()) {
            throw new BusinessException("未找到生产批次：" + notFoundOrderIds);
        }

        return pendingParts.stream().map(candidate -> {
            MmnnMmoo00000300VO workOrder = partWorkOrders.get(candidate.nestPart().getRecID());
            ZhurongScjinggongOrderitem orderItem = orderItems.get(workOrder.getCusRef());
            ZhurongScjinggongOrder order = orders.get(orderItem.getOrderId());
            requireText(order.getOrderCode(), "生产批次号不能为空");
            requireText(order.getOrderName(), "生产批次名不能为空");
            return new NestPartContext(candidate.nest(), candidate.nestPart(), orderItem, order);
        }).toList();
    }

    private void putWorkOrder(Map<WorkOrderKey, MmnnMmoo00000300VO> workOrders, MmnnMmoo00000300VO workOrder) {
        if (workOrder == null || workOrder.getMnORef() == null || workOrder.getOprID() == null) {
            return;
        }
        WorkOrderKey key = new WorkOrderKey(workOrder.getMnORef(), workOrder.getOprID());
        MmnnMmoo00000300VO previous = workOrders.putIfAbsent(key, workOrder);
        if (previous != null && !Objects.equals(previous.getCusRef(), workOrder.getCusRef())) {
            throw new BusinessException("生产计划关联到多个MES工单：" + key.displayName());
        }
    }

    private ScjinggongNestFeedbackRequest buildFeedbackRequest(
            OrderGroupKey group,
            List<NestPartContext> parts
    ) {
        ScjinggongProperties.Feedback config = properties.getFeedback();
        ScjinggongNestFeedbackRequest request = new ScjinggongNestFeedbackRequest();
        request.setApiName(NEST_FEEDBACK_API_NAME);
        request.setAccesstoken(config.getAccessToken());
        request.setSecret(config.getSecretKey());
        request.setModulesub(config.getModuleSub());
        request.setUser(config.getUser());
        request.setJobRef(group.orderCode());
        request.setJobName(group.orderName());
        request.setPartList(parts.stream().map(this::toFeedbackPart).toList());
        return request;
    }

    private ScjinggongNestFeedbackPartDTO toFeedbackPart(NestPartContext context) {
        DisNestNest00000100VO nest = context.nest();
        DisNestNest00000500VO nestPart = context.nestPart();
        ZhurongScjinggongOrderitem orderItem = context.orderItem();
        String cusRef = orderItem.getCusRef();
        PprrPprr00000100VO partMaster = nestPart.getItem();

        if (orderItem.getQuantity() == null) {
            throw new BusinessException("MES工单%s的工单数量为空".formatted(cusRef));
        }
        if (orderItem.getRdate() == null) {
            throw new BusinessException("MES工单%s的订单交货日期为空".formatted(cusRef));
        }
        if (orderItem.getThickness() == null) {
            throw new BusinessException("MES工单%s的加工厚度为空".formatted(cusRef));
        }
        if (nestPart.getQuantity() == null) {
            throw new BusinessException("NEST500零件%s的加工数量为空".formatted(nestPart.getRecID()));
        }
        if (partMaster == null || partMaster.getDIS_CutPerim() == null) {
            throw new BusinessException("NEST500零件%s的套料周长为空".formatted(nestPart.getRecID()));
        }

        ScjinggongNestFeedbackPartDTO part = new ScjinggongNestFeedbackPartDTO();
        part.setPrdRef(orderItem.getPrdRef());
        part.setMachine(requiredPartText(orderItem.getWrkRef(), "Machine", cusRef));
        part.setMachineCode(orderItem.getUdata1());
        part.setQuantity(orderItem.getQuantity());
        part.setOrdRef(requiredPartText(orderItem.getOrdRef(), "OrdRef", cusRef));
        part.setCusRef(requiredPartText(cusRef, "Cusref", cusRef));
        part.setMesProdOrdNo(requiredPartText(orderItem.getUdata2(), "mesProdOrdNo", cusRef));
        part.setRcvWareCd(requiredPartText(orderItem.getUdata3(), "rcvWareCd", cusRef));
        part.setRcvWareNm(requiredPartText(orderItem.getUdata4(), "rcvWareNm", cusRef));
        part.setOpLineNo(requiredPartText(orderItem.getUdata5(), "opLineNo", cusRef));
        part.setOpCd(requiredPartText(orderItem.getUdata6(), "opCd", cusRef));
        part.setOpNm(requiredPartText(orderItem.getUdata7(), "opNm", cusRef));
        part.setTmCd(requiredPartText(orderItem.getUdata8(), "tmCd", cusRef));
        part.setTmNm(requiredPartText(orderItem.getUdata9(), "tmNm", cusRef));
        part.setOrdDlvDt(orderItem.getRdate().format(API_DATE_TIME_FORMATTER));
        part.setPlanFinTm(requiredPartText(orderItem.getUdata10(), "planFinTm", cusRef));
        part.setChgDlvDt(requiredPartText(orderItem.getUdata11(), "chgDlvDt", cusRef));
        part.setWoStatus(requiredPartText(orderItem.getUdata12(), "woStatus", cusRef));
        part.setItemNum(requiredPartText(orderItem.getUdata13(), "ItemNum", cusRef));
        part.setGuId(requiredPartText(orderItem.getUdata14(), "GuID", cusRef));
        part.setPrdName(requiredPartText(orderItem.getPrdName(), "PrdName", cusRef));
        part.setMatRef(requiredPartText(orderItem.getMatRef(), "MatRef", cusRef));
        part.setThickness(numberText(orderItem.getThickness()));
        // Qty 是当前 NEST500 的实际排版数量，PartNo 是所属 NEST100 使用的板材料号。
        part.setQty(String.valueOf(nestPart.getQuantity()));
        part.setPartNo(requiredPartText(nest.getShtRef(), "PartNo", cusRef));
        part.setBomListId(requiredPartText(orderItem.getUdata15(), "BomList_id", cusRef));
        // NEST500 RecID 全局唯一，同时作为对方系统的套料唯一编号和本地幂等键。
        part.setPrdUData3(String.valueOf(nestPart.getRecID()));
        part.setPrdUData4(requiredPartText(orderItem.getUdata16(), "Prd_UData4", cusRef));
        part.setPrdUData5(requiredPartText(orderItem.getUdata17(), "Prd_UData5", cusRef));
        part.setPrdUData6(requiredPartText(orderItem.getUdata18(), "Prd_UData6", cusRef));
        part.setPrdUData7(requiredPartText(orderItem.getUdata19(), "Prd_UData7", cusRef));
        part.setPrdUData8(requiredPartText(orderItem.getUdata20(), "Prd_UData8", cusRef));
        part.setPrdPerimeter(numberText(partMaster.getDIS_CutPerim()));
        return part;
    }

    private String requestAuthorizationToken(String traceId) {
        ScjinggongProperties.Feedback config = properties.getFeedback();
        requireFeedbackConfig(config);

        ScjinggongAuthorizationRequest request = new ScjinggongAuthorizationRequest();
        request.setAccesstoken(config.getAccessToken());
        request.setSecret(config.getSecretKey());
        request.setUser(config.getUser());
        request.setApiName(AUTHORIZATION_API_NAME);
        request.setModulesub(config.getModuleSub());
        request.setAppid(config.getAppId());

        log.info("套料反馈认证请求报文，traceId={}，json={}", traceId, maskedRequestJson(request));
        ScjinggongAuthorizationResponse response = postApi(
                config.getUrl(),
                request,
                config.getAuthorization(),
                ScjinggongAuthorizationResponse.class
        );
        if (response == null
                || !Objects.equals(response.getCode(), 200)
                || !Objects.equals(response.getErrCode(), 1)
                || response.getData() == null
                || !StringUtils.hasText(response.getData().getToken())) {
            throw new BusinessException(response == null
                    ? "接口未返回数据"
                    : Optional.ofNullable(response.getMsg()).orElse("接口返回失败"));
        }
        String token = response.getData().getToken().trim();
        log.info("套料反馈认证成功，traceId={}，expirationTime={}",
                traceId, response.getData().getExpirationTime());
        return token.regionMatches(true, 0, "Bearer ", 0, 7) ? token.substring(7).trim() : token;
    }

    private void requireFeedbackConfig(ScjinggongProperties.Feedback config) {
        requireText(config.getUrl(), "套料反馈接口未配置");
        requireText(config.getAuthorization(), "认证接口Authorization请求头未配置");
        requireText(config.getAccessToken(), "套料反馈accesstoken未配置");
        requireText(config.getSecretKey(), "套料反馈secret未配置");
        requireText(config.getUser(), "套料反馈user未配置");
        requireText(config.getAppId(), "认证接口appid未配置");
        requireText(config.getModuleSub(), "套料反馈modulesub未配置");
    }

    private <T> T postApi(String url, Object payload, String authorization, Class<T> responseType) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(HTTP_CONNECT_TIMEOUT);
        requestFactory.setReadTimeout(HTTP_READ_TIMEOUT);

        try {
            String responseBody = RestClient.builder()
                    .requestFactory(requestFactory)
                    .build()
                    .post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", authorization)
                    .body(payload)
                    .retrieve()
                    .body(String.class);
            if (!StringUtils.hasText(responseBody)) {
                return null;
            }
            return objectMapper.readValue(responseBody, responseType);
        } catch (RestClientResponseException exception) {
            throw new IllegalStateException(
                    "HTTP请求失败，状态码=%s，响应=%s".formatted(
                            exception.getStatusCode(),
                            compactText(exception.getResponseBodyAsString(), 500)
                    ),
                    exception
            );
        } catch (RestClientException exception) {
            throw new IllegalStateException("HTTP请求失败：" + exception.getMessage(), exception);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("接口响应不是有效JSON", exception);
        }
    }

    /**
     * 输出可用于联调的数据结构，同时屏蔽认证凭据，避免日志形成新的密钥泄露面。
     */
    private String maskedRequestJson(Object payload) {
        try {
            JsonNode root = objectMapper.valueToTree(payload);
            maskSensitiveJsonFields(root);
            return objectMapper.writeValueAsString(root);
        } catch (IllegalArgumentException | JsonProcessingException exception) {
            throw new IllegalStateException("接口请求报文序列化失败", exception);
        }
    }

    private void maskSensitiveJsonFields(JsonNode node) {
        if (node == null) {
            return;
        }
        if (node.isArray()) {
            node.forEach(this::maskSensitiveJsonFields);
            return;
        }
        if (!node.isObject()) {
            return;
        }

        ObjectNode objectNode = (ObjectNode) node;
        List<String> fieldNames = new ArrayList<>();
        objectNode.fieldNames().forEachRemaining(fieldNames::add);
        for (String fieldName : fieldNames) {
            JsonNode value = objectNode.get(fieldName);
            if (SENSITIVE_JSON_FIELDS.contains(fieldName.toLowerCase(Locale.ROOT)) && !value.isNull()) {
                objectNode.put(fieldName, "******");
            } else {
                maskSensitiveJsonFields(value);
            }
        }
    }

    private void saveFeedbackStates(
            List<NestPartContext> parts,
            Map<Integer, ZhurongScjinggongNestPartFeedbackState> states,
            boolean sent,
            String remark,
            String traceId
    ) {
        LocalDateTime now = LocalDateTime.now();
        List<ZhurongScjinggongNestPartFeedbackState> changedStates = parts.stream().map(context -> {
            Integer partRecId = context.nestPart().getRecID();
            ZhurongScjinggongNestPartFeedbackState state = states.get(partRecId);
            if (state == null) {
                state = new ZhurongScjinggongNestPartFeedbackState();
                state.setNestPartRecId(partRecId);
            }
            state.setNestRecId(context.nest().getRecID());
            state.setNstRef(context.nest().getNstRef());
            state.setOrderId(context.order().getId());
            state.setOrderCode(context.order().getOrderCode());
            state.setOrderName(context.order().getOrderName());
            state.setSent(sent);
            state.setAttemptCount(Optional.ofNullable(state.getAttemptCount()).orElse(0) + 1);
            if (sent) {
                state.setSentAt(now);
            }
            state.setRemark(compactText(remark, 1000));
            states.put(partRecId, state);
            return state;
        }).toList();

        if (!nestPartFeedbackStateService.saveOrUpdateBatch(changedStates)) {
            throw new BusinessException("NEST500反馈状态保存失败");
        }
        if (log.isDebugEnabled()) {
            log.debug("NEST500反馈状态已保存，traceId={}，sent={}，nestPartRecIds={}",
                    traceId, sent, nestPartRecIds(parts));
        }
    }

    private void markCompletedNests(
            List<DisNestNest00000100VO> nests,
            Set<Integer> successfulPartIds,
            String traceId
    ) {
        List<Integer> completedNestIds = new ArrayList<>();
        for (DisNestNest00000100VO nest : nests) {
            boolean allSent = nest.getNestParts().stream()
                    .map(DisNestNest00000500VO::getRecID)
                    .allMatch(successfulPartIds::contains);
            if (!allSent) {
                continue;
            }
            boolean updated = disNestNest00000100Service.update(
                    Wrappers.lambdaUpdate(DisNestNest00000100.class)
                            .set(DisNestNest00000100::getMState, NestConstant.MState.COMPLETED)
                            .eq(DisNestNest00000100::getRecID, nest.getRecID())
            );
            if (!updated) {
                throw new BusinessException("套料程序完成状态更新失败：" + nestDisplayName(nest));
            }
            completedNestIds.add(nest.getRecID());
        }
        if (!completedNestIds.isEmpty()) {
            log.info("NEST100反馈完成态已更新，traceId={}，nestRecIds={}", traceId, completedNestIds);
        }
    }

    private String nestPartRecIds(List<NestPartContext> parts) {
        String value = parts.stream()
                .map(context -> String.valueOf(context.nestPart().getRecID()))
                .collect(Collectors.joining(",", "[", "]"));
        return compactText(value, 500);
    }

    private String requiredPartText(String value, String field, String cusRef) {
        if (!StringUtils.hasText(value)) {
            throw new BusinessException("MES工单%s的%s为空".formatted(cusRef, field));
        }
        return value;
    }

    private String numberText(Number value) {
        return new java.math.BigDecimal(value.toString()).stripTrailingZeros().toPlainString();
    }

    private String compactErrorMessage(Throwable throwable) {
        Throwable current = throwable;
        while (current.getCause() != null && current.getCause() != current) {
            current = current.getCause();
        }
        String message = StringUtils.hasText(current.getMessage()) ? current.getMessage() : throwable.getClass().getSimpleName();
        return compactText(message, 900);
    }

    private String compactText(String value, int maxLength) {
        if (value == null || value.length() <= maxLength) {
            return value;
        }
        return value.substring(0, maxLength);
    }

    private String nestDisplayName(DisNestNest00000100VO nest) {
        return StringUtils.hasText(nest.getNstRef()) ? nest.getNstRef() : String.valueOf(nest.getRecID());
    }

    private record WorkOrderKey(String mnORef, Integer oprId) {
        private String displayName() {
            return mnORef + "/" + oprId;
        }
    }

    private record NestPartCandidate(
            DisNestNest00000100VO nest,
            DisNestNest00000500VO nestPart
    ) {
    }

    private record NestPartContext(
            DisNestNest00000100VO nest,
            DisNestNest00000500VO nestPart,
            ZhurongScjinggongOrderitem orderItem,
            ZhurongScjinggongOrder order
    ) {
    }

    private record OrderGroupKey(Long orderId, String orderCode, String orderName) {
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
