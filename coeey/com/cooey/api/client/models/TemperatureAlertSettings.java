package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class TemperatureAlertSettings {
    @SerializedName("day")
    private Integer day = null;
    @SerializedName("night")
    private Integer night = null;
    @SerializedName("sleep")
    private Integer sleep = null;

    public TemperatureAlertSettings day(Integer day) {
        this.day = day;
        return this;
    }

    @ApiModelProperty("")
    public Integer getDay() {
        return this.day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public TemperatureAlertSettings night(Integer night) {
        this.night = night;
        return this;
    }

    @ApiModelProperty("")
    public Integer getNight() {
        return this.night;
    }

    public void setNight(Integer night) {
        this.night = night;
    }

    public TemperatureAlertSettings sleep(Integer sleep) {
        this.sleep = sleep;
        return this;
    }

    @ApiModelProperty("")
    public Integer getSleep() {
        return this.sleep;
    }

    public void setSleep(Integer sleep) {
        this.sleep = sleep;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TemperatureAlertSettings temperatureAlertSettings = (TemperatureAlertSettings) o;
        if (Objects.equals(this.day, temperatureAlertSettings.day) && Objects.equals(this.night, temperatureAlertSettings.night) && Objects.equals(this.sleep, temperatureAlertSettings.sleep)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.day, this.night, this.sleep});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TemperatureAlertSettings {\n");
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
