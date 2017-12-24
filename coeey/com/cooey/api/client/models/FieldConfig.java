package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class FieldConfig {
    @SerializedName("fieldType")
    private String fieldType = null;
    @SerializedName("label")
    private String label = null;
    @SerializedName("max")
    private Float max = null;
    @SerializedName("min")
    private Float min = null;
    @SerializedName("unit")
    private String unit = null;

    public FieldConfig label(String label) {
        this.label = label;
        return this;
    }

    @ApiModelProperty("")
    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public FieldConfig unit(String unit) {
        this.unit = unit;
        return this;
    }

    @ApiModelProperty("")
    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public FieldConfig fieldType(String fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    @ApiModelProperty("")
    public String getFieldType() {
        return this.fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public FieldConfig min(Float min) {
        this.min = min;
        return this;
    }

    @ApiModelProperty("")
    public Float getMin() {
        return this.min;
    }

    public void setMin(Float min) {
        this.min = min;
    }

    public FieldConfig max(Float max) {
        this.max = max;
        return this;
    }

    @ApiModelProperty("")
    public Float getMax() {
        return this.max;
    }

    public void setMax(Float max) {
        this.max = max;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldConfig fieldConfig = (FieldConfig) o;
        if (Objects.equals(this.label, fieldConfig.label) && Objects.equals(this.unit, fieldConfig.unit) && Objects.equals(this.fieldType, fieldConfig.fieldType) && Objects.equals(this.min, fieldConfig.min) && Objects.equals(this.max, fieldConfig.max)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.label, this.unit, this.fieldType, this.min, this.max});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FieldConfig {\n");
        sb.append("    label: ").append(toIndentedString(this.label)).append("\n");
        sb.append("    unit: ").append(toIndentedString(this.unit)).append("\n");
        sb.append("    fieldType: ").append(toIndentedString(this.fieldType)).append("\n");
        sb.append("    min: ").append(toIndentedString(this.min)).append("\n");
        sb.append("    max: ").append(toIndentedString(this.max)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
