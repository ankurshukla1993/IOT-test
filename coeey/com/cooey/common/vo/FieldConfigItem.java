package com.cooey.common.vo;

import com.google.gson.annotations.SerializedName;

public class FieldConfigItem {
    @SerializedName("fieldType")
    private Object fieldType;
    @SerializedName("label")
    private String label;
    @SerializedName("max")
    private int max;
    @SerializedName("min")
    private int min;
    @SerializedName("unit")
    private String unit;

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMin() {
        return this.min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return this.max;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public void setFieldType(Object fieldType) {
        this.fieldType = fieldType;
    }

    public Object getFieldType() {
        return this.fieldType;
    }
}
