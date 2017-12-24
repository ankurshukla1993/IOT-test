package com.cooey.common.vo;

public abstract class UserSize {
    private String unit;
    private String value;

    public UserSize(String value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
