package com.zhurong.platform.base.lantek.expert.lstx;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class ExpertProductXmlItem {

    private String reference = "";
    private String nBono = "0";
    private String product = "";
    private String name = "";
    private String database = "";
    private String machine = "";
    private String material = "";
    private String thickness = "";
    private String quantity = "";
    private String priority = "";
    private String file = "";
    private String userData1 = "";
    private String userData2 = "";
    private String userData3 = "";
    private String userData4 = "";
    private String userData5 = "";
    private String userData6 = "";
    private String userData7 = "";
    private String userData8 = "";
    private String param0 = "";
    private String param1 = "";
    private String param2 = "";
    private String param3 = "";
    private String param5 = "";
    private String param6 = "";
    private String param7 = "";
    private final RotationValues normalRotations = new RotationValues();
    private final RotationValues symmetricRotations = new RotationValues();
    private String fillsValue = "0";
    private String hollows = "0";
    private final Map<String, String> auxiliarData = new LinkedHashMap<>();

    public ExpertProductXmlItem() {
        auxiliarData.put("OrdRef", "");
        auxiliarData.put("CusRef", "");
        auxiliarData.put("RDate", "");
        auxiliarData.put("CusName", "");
    }

    public static ExpertProductXmlItem create() {
        return new ExpertProductXmlItem();
    }

    public ExpertProductXmlItem reference(Object value) {
        reference = text(value);
        return this;
    }

    public ExpertProductXmlItem nBono(Object value) {
        nBono = text(value);
        return this;
    }

    public ExpertProductXmlItem product(Object value) {
        product = text(value);
        return this;
    }

    public ExpertProductXmlItem name(Object value) {
        name = text(value);
        return this;
    }

    public ExpertProductXmlItem database(Object value) {
        database = text(value);
        return this;
    }

    public ExpertProductXmlItem machine(Object value) {
        machine = text(value);
        return this;
    }

    public ExpertProductXmlItem material(Object value) {
        material = text(value);
        return this;
    }

    public ExpertProductXmlItem thickness(Object value) {
        thickness = text(value);
        return this;
    }

    public ExpertProductXmlItem quantity(Object value) {
        quantity = text(value);
        return this;
    }

    public ExpertProductXmlItem priority(Object value) {
        priority = text(value);
        return this;
    }

    public ExpertProductXmlItem file(Object value) {
        file = text(value);
        return this;
    }

    public ExpertProductXmlItem userData1(Object value) {
        userData1 = text(value);
        return this;
    }

    public ExpertProductXmlItem userData2(Object value) {
        userData2 = text(value);
        return this;
    }

    public ExpertProductXmlItem userData3(Object value) {
        userData3 = text(value);
        return this;
    }

    public ExpertProductXmlItem userData4(Object value) {
        userData4 = text(value);
        return this;
    }

    public ExpertProductXmlItem userData5(Object value) {
        userData5 = text(value);
        return this;
    }

    public ExpertProductXmlItem userData6(Object value) {
        userData6 = text(value);
        return this;
    }

    public ExpertProductXmlItem userData7(Object value) {
        userData7 = text(value);
        return this;
    }

    public ExpertProductXmlItem userData8(Object value) {
        userData8 = text(value);
        return this;
    }

    public ExpertProductXmlItem param0(Object value) {
        param0 = text(value);
        return this;
    }

    public ExpertProductXmlItem param1(Object value) {
        param1 = text(value);
        return this;
    }

    public ExpertProductXmlItem param2(Object value) {
        param2 = text(value);
        return this;
    }

    public ExpertProductXmlItem param3(Object value) {
        param3 = text(value);
        return this;
    }

    public ExpertProductXmlItem param5(Object value) {
        param5 = text(value);
        return this;
    }

    public ExpertProductXmlItem param6(Object value) {
        param6 = text(value);
        return this;
    }

    public ExpertProductXmlItem param7(Object value) {
        param7 = text(value);
        return this;
    }

    public ExpertProductXmlItem fills(Object value, Object hollows) {
        this.fillsValue = text(value);
        this.hollows = text(hollows);
        return this;
    }

    public ExpertProductXmlItem normalRotation0(boolean value) {
        normalRotations.rotation0 = bit(value);
        return this;
    }

    public ExpertProductXmlItem normalRotation90(boolean value) {
        normalRotations.rotation90 = bit(value);
        return this;
    }

    public ExpertProductXmlItem normalRotation180(boolean value) {
        normalRotations.rotation180 = bit(value);
        return this;
    }

    public ExpertProductXmlItem normalRotation270(boolean value) {
        normalRotations.rotation270 = bit(value);
        return this;
    }

    public ExpertProductXmlItem normalOthers(boolean value) {
        normalRotations.others = bit(value);
        return this;
    }

    public ExpertProductXmlItem allNormalRotations(boolean value) {
        normalRotations.setAll(value);
        return this;
    }

    public ExpertProductXmlItem symmetricRotation0(boolean value) {
        symmetricRotations.rotation0 = bit(value);
        return this;
    }

    public ExpertProductXmlItem symmetricRotation90(boolean value) {
        symmetricRotations.rotation90 = bit(value);
        return this;
    }

    public ExpertProductXmlItem symmetricRotation180(boolean value) {
        symmetricRotations.rotation180 = bit(value);
        return this;
    }

    public ExpertProductXmlItem symmetricRotation270(boolean value) {
        symmetricRotations.rotation270 = bit(value);
        return this;
    }

    public ExpertProductXmlItem symmetricOthers(boolean value) {
        symmetricRotations.others = bit(value);
        return this;
    }

    public ExpertProductXmlItem allSymmetricRotations(boolean value) {
        symmetricRotations.setAll(value);
        return this;
    }

    public ExpertProductXmlItem auxiliarData(String dbField, Object value) {
        auxiliarData.put(dbField, text(value));
        return this;
    }

    public ExpertProductXmlItem ordRef(Object value) {
        return auxiliarData("OrdRef", value);
    }

    public ExpertProductXmlItem cusRef(Object value) {
        return auxiliarData("CusRef", value);
    }

    public ExpertProductXmlItem rDate(Object value) {
        return auxiliarData("RDate", value);
    }

    public ExpertProductXmlItem cusName(Object value) {
        return auxiliarData("CusName", value);
    }

    Map<String, String> productAttributes() {
        Map<String, String> attributes = new LinkedHashMap<>();
        attributes.put("Reference", reference);
        attributes.put("NBono", nBono);
        attributes.put("Product", product);
        attributes.put("Name", name);
        attributes.put("Database", database);
        attributes.put("Machine", machine);
        attributes.put("Material", material);
        attributes.put("Thickness", thickness);
        attributes.put("Quantity", quantity);
        attributes.put("Priority", priority);
        attributes.put("File", file);
        attributes.put("UserData1", userData1);
        attributes.put("UserData2", userData2);
        attributes.put("UserData3", userData3);
        attributes.put("UserData4", userData4);
        attributes.put("UserData5", userData5);
        attributes.put("UserData6", userData6);
        attributes.put("UserData7", userData7);
        attributes.put("UserData8", userData8);
        attributes.put("Param0", param0);
        attributes.put("Param1", param1);
        attributes.put("Param2", param2);
        attributes.put("Param3", param3);
        attributes.put("Param5", param5);
        attributes.put("Param6", param6);
        attributes.put("Param7", param7);
        return attributes;
    }

    RotationValues normalRotations() {
        return normalRotations;
    }

    RotationValues symmetricRotations() {
        return symmetricRotations;
    }

    String fillsValue() {
        return fillsValue;
    }

    String hollows() {
        return hollows;
    }

    Map<String, String> auxiliarData() {
        return auxiliarData;
    }

    private static String text(Object value) {
        return value == null ? "" : String.valueOf(value);
    }

    private static String bit(boolean value) {
        return value ? "1" : "0";
    }

    static class RotationValues {
        private String rotation0 = "1";
        private String rotation90 = "1";
        private String rotation180 = "1";
        private String rotation270 = "1";
        private String others = "1";

        private void setAll(boolean value) {
            String bit = bit(value);
            rotation0 = bit;
            rotation90 = bit;
            rotation180 = bit;
            rotation270 = bit;
            others = bit;
        }

        String rotation0() {
            return rotation0;
        }

        String rotation90() {
            return rotation90;
        }

        String rotation180() {
            return rotation180;
        }

        String rotation270() {
            return rotation270;
        }

        String others() {
            return others;
        }
    }
}
