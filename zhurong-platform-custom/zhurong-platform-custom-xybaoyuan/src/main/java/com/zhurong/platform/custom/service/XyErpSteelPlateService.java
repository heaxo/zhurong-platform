package com.zhurong.platform.custom.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhurong.platform.custom.entity.XySteelPlate;
import com.zhurong.platform.custom.properties.XyBaoyuanProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;

import java.util.*;

@Service
@RequiredArgsConstructor
public class XyErpSteelPlateService {
    private final XyBaoyuanProperties properties;
    private final ObjectMapper objectMapper;
    private final XyDataService dataService;

    public int synchronize(String materialNumber, String lotNumber) {
        String url = properties.getErp().getSteelPlateUrl();
        String secret = properties.getErp().getSecretKey();
        requireText(url, "ERP钢板查询接口未配置");
        requireText(secret, "ERP钢板查询接口密钥未配置");
        if (!StringUtils.hasText(materialNumber) && !StringUtils.hasText(lotNumber)) {
            throw new IllegalArgumentException("物料编号或物料批号不能为空");
        }
        Map<String, Object> request = new LinkedHashMap<>();
        request.put("secret_key", secret);
        if (StringUtils.hasText(materialNumber)) request.put("material_number", materialNumber);
        if (StringUtils.hasText(lotNumber)) request.put("lot_number", lotNumber);
        String body = RestClient.create().post().uri(url).contentType(MediaType.APPLICATION_JSON)
                .body(request).retrieve().body(String.class);
        try {
            JsonNode response = objectMapper.readTree(body);
            if (response.path("code").asInt(-1) != 0) {
                throw new IllegalArgumentException(text(response, "message", text(response, "msg", "ERP钢板查询失败")));
            }
            List<Map<String, JsonNode>> rows = new ArrayList<>();
            response.path("data").forEach(node -> rows.add(caseInsensitive(node)));
            Map<GroupKey, Aggregate> aggregates = new LinkedHashMap<>();
            for (Map<String, JsonNode> row : rows) {
                if (StringUtils.hasText(text(row, "shtRef"))) continue;
                String[] size = text(row, "LengthWidth").split("\\*");
                if (size.length != 2) throw new IllegalArgumentException("ERP钢板长宽格式错误: " + text(row, "LengthWidth"));
                GroupKey key = new GroupKey(text(row, "FNUMBER"), size[1], size[0], text(row, "StockNumber"));
                aggregates.computeIfAbsent(key, ignored -> new Aggregate(row)).add(number(row, "FSecQty"), number(row, "FBaseQty"));
            }
            List<String> refs = aggregates.entrySet().stream().map(entry -> entry.getKey().reference()).toList();
            Map<String, XySteelPlate> existing = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            dataService.findSteelPlatesByRefs(refs).forEach(item -> existing.put(item.getPrdRef(), item));
            for (Map.Entry<GroupKey, Aggregate> entry : aggregates.entrySet()) {
                XySteelPlate plate = existing.getOrDefault(entry.getKey().reference(), new XySteelPlate());
                mapPlate(plate, entry.getKey(), entry.getValue());
                if (plate.getId() == null) dataService.insertSteelPlate(plate); else dataService.updateSteelPlateById(plate);
            }
            return aggregates.size();
        } catch (RuntimeException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new IllegalStateException("解析ERP钢板响应失败", exception);
        }
    }

    private void mapPlate(XySteelPlate plate, GroupKey key, Aggregate aggregate) {
        Map<String, JsonNode> row = aggregate.first();
        plate.setErpMaterialId(integer(row, "FMATERIALID"));
        plate.setPrdRef(key.reference()); plate.setPrdName(text(row, "FNAME"));
        plate.setSpecification(text(row, "FSpecification")); plate.setMatRef(text(row, "FMatRef"));
        plate.setStockName(text(row, "StockName")); plate.setStockNumber(key.stockNumber());
        plate.setTons(aggregate.tons()); plate.setQuantity(aggregate.quantity());
        plate.setThickness(number(row, "FHEIGHT")); plate.setWidth(Double.parseDouble(key.width()));
        plate.setLength(Double.parseDouble(key.length())); plate.setInvalidState(false);
        if (plate.getReadState() == null) plate.setReadState(false);
        if (plate.getSendState() == null) plate.setSendState(false);
    }

    private Map<String, JsonNode> caseInsensitive(JsonNode node) {
        Map<String, JsonNode> result = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        node.fields().forEachRemaining(entry -> result.put(entry.getKey(), entry.getValue()));
        return result;
    }

    private static String text(JsonNode node, String key, String fallback) {
        JsonNode value = node.get(key); return value == null || value.isNull() ? fallback : value.asText();
    }
    private static String text(Map<String, JsonNode> row, String key) {
        JsonNode value = row.get(key); return value == null || value.isNull() ? "" : value.asText();
    }
    private static double number(Map<String, JsonNode> row, String key) { return Double.parseDouble(text(row, key)); }
    private static Integer integer(Map<String, JsonNode> row, String key) {
        String value = text(row, key); return StringUtils.hasText(value) ? Integer.valueOf(value) : null;
    }
    private static void requireText(String value, String message) { if (!StringUtils.hasText(value)) throw new IllegalArgumentException(message); }

    private record GroupKey(String materialNumber, String length, String width, String stockNumber) {
        String reference() { return materialNumber + "[" + length + "-" + width + "-" + stockNumber + "]"; }
    }
    private static final class Aggregate {
        private final Map<String, JsonNode> first; private double quantity; private double tons;
        private Aggregate(Map<String, JsonNode> first) { this.first = first; }
        private void add(double quantity, double tons) { this.quantity += quantity; this.tons += tons; }
        private Map<String, JsonNode> first() { return first; }
        private double quantity() { return quantity; }
        private double tons() { return tons; }
    }
}
