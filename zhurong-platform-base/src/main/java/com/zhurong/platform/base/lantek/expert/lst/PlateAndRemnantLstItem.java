package com.zhurong.platform.base.lantek.expert.lst;

import com.zhurong.platform.base.clientimport.dto.RawMaterialRequest;

public class PlateAndRemnantLstItem {

    private String reference;
    private Float length;
    private Float width;
    private Float thickness;
    private String material;
    private Integer quantity = 1;
    private Integer priority = 0;
    private String userData1 = "";
    private String userData2 = "";
    private String userData3 = "";
    private Type type = Type.SHEET;
    private String dxfPath = "";

    public static PlateAndRemnantLstItem create() {
        return new PlateAndRemnantLstItem();
    }

    public static PlateAndRemnantLstItem fromRawMaterialRequest(RawMaterialRequest request) {
        return create()
                .reference(request.getPrdRef())
                .length(request.getLength())
                .width(request.getWidth())
                .thickness(request.getThickness())
                .material(request.getMatRef())
                .quantity(request.getQuantity())
                .userData1(request.getUdata1())
                .userData2(request.getUdata2())
                .userData3(request.getUdata3())
                .type(hasText(request.getImage()) ? Type.REMNANT : Type.SHEET)
                .dxfPath(request.getImage());
    }

    public PlateAndRemnantLstItem reference(Object value) {
        reference = text(value);
        return this;
    }

    public PlateAndRemnantLstItem length(Number value) {
        length = numberValue(value);
        return this;
    }

    public PlateAndRemnantLstItem width(Number value) {
        width = numberValue(value);
        return this;
    }

    public PlateAndRemnantLstItem thickness(Number value) {
        thickness = numberValue(value);
        return this;
    }

    public PlateAndRemnantLstItem material(Object value) {
        material = text(value);
        return this;
    }

    public PlateAndRemnantLstItem quantity(Integer value) {
        quantity = value == null ? 1 : value;
        return this;
    }

    public PlateAndRemnantLstItem priority(Integer value) {
        priority = value == null ? 0 : value;
        return this;
    }

    public PlateAndRemnantLstItem userData1(Object value) {
        userData1 = text(value);
        return this;
    }

    public PlateAndRemnantLstItem userData2(Object value) {
        userData2 = text(value);
        return this;
    }

    public PlateAndRemnantLstItem userData3(Object value) {
        userData3 = text(value);
        return this;
    }

    public PlateAndRemnantLstItem type(Type value) {
        type = value == null ? Type.SHEET : value;
        return this;
    }

    public PlateAndRemnantLstItem sheet() {
        type = Type.SHEET;
        dxfPath = "";
        return this;
    }

    public PlateAndRemnantLstItem remnant(String dxfPath) {
        type = Type.REMNANT;
        this.dxfPath = text(dxfPath);
        return this;
    }

    public PlateAndRemnantLstItem dxfPath(Object value) {
        dxfPath = text(value);
        return this;
    }

    String toLstLine() {
        validate();
        return quote(reference)
                + " " + number(length)
                + " " + number(width)
                + " " + number(thickness)
                + " " + quote(material)
                + " " + quantity
                + " " + priority
                + " " + quote(userData1)
                + " " + quote(userData2)
                + " " + quote(userData3)
                + " " + type.code()
                + " " + quote(type == Type.SHEET ? "" : dxfPath);
    }

    private void validate() {
        if (!hasText(reference)) {
            throw new IllegalArgumentException("板材/余料编码不能为空。");
        }
        if (length == null || length <= 0) {
            throw new IllegalArgumentException("板材/余料长度必须大于0。");
        }
        if (width == null || width <= 0) {
            throw new IllegalArgumentException("板材/余料宽度必须大于0。");
        }
        if (thickness == null || thickness <= 0) {
            throw new IllegalArgumentException("板材/余料厚度必须大于0。");
        }
        if (!hasText(material)) {
            throw new IllegalArgumentException("板材/余料材质不能为空。");
        }
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("板材/余料数量必须大于0。");
        }
        if (type == Type.REMNANT && !hasText(dxfPath)) {
            throw new IllegalArgumentException("余料必须指定DXF路径。");
        }
    }

    private static String quote(String value) {
        return "\"" + text(value).replace("\"", "\"\"") + "\"";
    }

    private static String text(Object value) {
        return value == null ? "" : String.valueOf(value);
    }

    private static Float numberValue(Number value) {
        return value == null ? null : value.floatValue();
    }

    private static boolean hasText(String value) {
        return value != null && !value.isBlank();
    }

    private static String number(Float value) {
        if (value == null) {
            return "";
        }
        if (value % 1 == 0) {
            return Long.toString(value.longValue());
        }
        return value.toString();
    }

    public enum Type {
        SHEET(0),
        REMNANT(2);

        private final int code;

        Type(int code) {
            this.code = code;
        }

        public int code() {
            return code;
        }
    }
}
