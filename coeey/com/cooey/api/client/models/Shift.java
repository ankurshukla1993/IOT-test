package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Shift {
    @SerializedName("endTime")
    private String endTime = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("shiftName")
    private String shiftName = null;
    @SerializedName("startTime")
    private String startTime = null;
    @SerializedName("tenantId")
    private String tenantId = null;

    public Shift id(String id) {
        this.id = id;
        return this;
    }

    @ApiModelProperty("")
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Shift shiftName(String shiftName) {
        this.shiftName = shiftName;
        return this;
    }

    @ApiModelProperty("")
    public String getShiftName() {
        return this.shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public Shift startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    @ApiModelProperty("")
    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Shift endTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    @ApiModelProperty("")
    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Shift tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @ApiModelProperty("")
    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Shift shift = (Shift) o;
        if (Objects.equals(this.id, shift.id) && Objects.equals(this.shiftName, shift.shiftName) && Objects.equals(this.startTime, shift.startTime) && Objects.equals(this.endTime, shift.endTime) && Objects.equals(this.tenantId, shift.tenantId)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.shiftName, this.startTime, this.endTime, this.tenantId});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Shift {\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    shiftName: ").append(toIndentedString(this.shiftName)).append("\n");
        sb.append("    startTime: ").append(toIndentedString(this.startTime)).append("\n");
        sb.append("    endTime: ").append(toIndentedString(this.endTime)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
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
