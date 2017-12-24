package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class DeviceTemperatureLevelSettings {
    @SerializedName("high")
    private Integer high = null;
    @SerializedName("low")
    private Integer low = null;
    @SerializedName("period")
    private Integer period = null;

    public DeviceTemperatureLevelSettings high(Integer high) {
        this.high = high;
        return this;
    }

    @ApiModelProperty("")
    public Integer getHigh() {
        return this.high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public DeviceTemperatureLevelSettings low(Integer low) {
        this.low = low;
        return this;
    }

    @ApiModelProperty("")
    public Integer getLow() {
        return this.low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public DeviceTemperatureLevelSettings period(Integer period) {
        this.period = period;
        return this;
    }

    @ApiModelProperty("")
    public Integer getPeriod() {
        return this.period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceTemperatureLevelSettings deviceTemperatureLevelSettings = (DeviceTemperatureLevelSettings) o;
        if (Objects.equals(this.high, deviceTemperatureLevelSettings.high) && Objects.equals(this.low, deviceTemperatureLevelSettings.low) && Objects.equals(this.period, deviceTemperatureLevelSettings.period)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.high, this.low, this.period});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeviceTemperatureLevelSettings {\n");
        sb.append("    high: ").append(toIndentedString(this.high)).append("\n");
        sb.append("    low: ").append(toIndentedString(this.low)).append("\n");
        sb.append("    period: ").append(toIndentedString(this.period)).append("\n");
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
