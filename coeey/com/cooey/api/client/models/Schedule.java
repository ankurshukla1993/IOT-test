package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Schedule {
    @SerializedName("beginTime")
    private Long beginTime = null;
    @SerializedName("endTime")
    private Long endTime = null;
    @SerializedName("numOfDays")
    private Integer numOfDays = null;
    @SerializedName("timings")
    private Map<String, String> timings = null;

    public Schedule beginTime(Long beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    @ApiModelProperty("")
    public Long getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Schedule endTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }

    @ApiModelProperty("")
    public Long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Schedule timings(Map<String, String> timings) {
        this.timings = timings;
        return this;
    }

    public Schedule putTimingsItem(String key, String timingsItem) {
        if (this.timings == null) {
            this.timings = new HashMap();
        }
        this.timings.put(key, timingsItem);
        return this;
    }

    @ApiModelProperty("")
    public Map<String, String> getTimings() {
        return this.timings;
    }

    public void setTimings(Map<String, String> timings) {
        this.timings = timings;
    }

    public Schedule numOfDays(Integer numOfDays) {
        this.numOfDays = numOfDays;
        return this;
    }

    @ApiModelProperty("")
    public Integer getNumOfDays() {
        return this.numOfDays;
    }

    public void setNumOfDays(Integer numOfDays) {
        this.numOfDays = numOfDays;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Schedule schedule = (Schedule) o;
        if (Objects.equals(this.beginTime, schedule.beginTime) && Objects.equals(this.endTime, schedule.endTime) && Objects.equals(this.timings, schedule.timings) && Objects.equals(this.numOfDays, schedule.numOfDays)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.beginTime, this.endTime, this.timings, this.numOfDays});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Schedule {\n");
        sb.append("    beginTime: ").append(toIndentedString(this.beginTime)).append("\n");
        sb.append("    endTime: ").append(toIndentedString(this.endTime)).append("\n");
        sb.append("    timings: ").append(toIndentedString(this.timings)).append("\n");
        sb.append("    numOfDays: ").append(toIndentedString(this.numOfDays)).append("\n");
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
