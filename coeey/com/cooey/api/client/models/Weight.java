package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Weight {
    @SerializedName("unit")
    private String unit = null;
    @SerializedName("value")
    private Float value = null;

    public Weight value(Float value) {
        this.value = value;
        return this;
    }

    @ApiModelProperty("")
    public Float getValue() {
        return this.value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Weight unit(String unit) {
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

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Weight weight = (Weight) o;
        if (Objects.equals(this.value, weight.value) && Objects.equals(this.unit, weight.unit)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.value, this.unit});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Weight {\n");
        sb.append("    value: ").append(toIndentedString(this.value)).append("\n");
        sb.append("    unit: ").append(toIndentedString(this.unit)).append("\n");
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
