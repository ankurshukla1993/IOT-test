package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class DeviceActivityPeriodSettings {
    @SerializedName("day")
    private String day = null;
    @SerializedName("night")
    private String night = null;
    @SerializedName("sleep")
    private String sleep = null;

    public DeviceActivityPeriodSettings day(String day) {
        this.day = day;
        return this;
    }

    @ApiModelProperty("")
    public String getDay() {
        return this.day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public DeviceActivityPeriodSettings night(String night) {
        this.night = night;
        return this;
    }

    @ApiModelProperty("")
    public String getNight() {
        return this.night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public DeviceActivityPeriodSettings sleep(String sleep) {
        this.sleep = sleep;
        return this;
    }

    @ApiModelProperty("")
    public String getSleep() {
        return this.sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceActivityPeriodSettings deviceActivityPeriodSettings = (DeviceActivityPeriodSettings) o;
        if (Objects.equals(this.day, deviceActivityPeriodSettings.day) && Objects.equals(this.night, deviceActivityPeriodSettings.night) && Objects.equals(this.sleep, deviceActivityPeriodSettings.sleep)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.day, this.night, this.sleep});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeviceActivityPeriodSettings {\n");
        sb.append("    day: ").append(toIndentedString(this.day)).append("\n");
        sb.append("    night: ").append(toIndentedString(this.night)).append("\n");
        sb.append("    sleep: ").append(toIndentedString(this.sleep)).append("\n");
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
