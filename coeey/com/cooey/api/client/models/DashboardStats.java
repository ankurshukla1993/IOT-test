package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class DashboardStats {
    @SerializedName("caretakerCount")
    private Integer caretakerCount = null;
    @SerializedName("deviceCount")
    private Integer deviceCount = null;
    @SerializedName("patientCount")
    private Integer patientCount = null;
    @SerializedName("zoneCount")
    private Integer zoneCount = null;

    public DashboardStats caretakerCount(Integer caretakerCount) {
        this.caretakerCount = caretakerCount;
        return this;
    }

    @ApiModelProperty("")
    public Integer getCaretakerCount() {
        return this.caretakerCount;
    }

    public void setCaretakerCount(Integer caretakerCount) {
        this.caretakerCount = caretakerCount;
    }

    public DashboardStats patientCount(Integer patientCount) {
        this.patientCount = patientCount;
        return this;
    }

    @ApiModelProperty("")
    public Integer getPatientCount() {
        return this.patientCount;
    }

    public void setPatientCount(Integer patientCount) {
        this.patientCount = patientCount;
    }

    public DashboardStats deviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
        return this;
    }

    @ApiModelProperty("")
    public Integer getDeviceCount() {
        return this.deviceCount;
    }

    public void setDeviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
    }

    public DashboardStats zoneCount(Integer zoneCount) {
        this.zoneCount = zoneCount;
        return this;
    }

    @ApiModelProperty("")
    public Integer getZoneCount() {
        return this.zoneCount;
    }

    public void setZoneCount(Integer zoneCount) {
        this.zoneCount = zoneCount;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DashboardStats dashboardStats = (DashboardStats) o;
        if (Objects.equals(this.caretakerCount, dashboardStats.caretakerCount) && Objects.equals(this.patientCount, dashboardStats.patientCount) && Objects.equals(this.deviceCount, dashboardStats.deviceCount) && Objects.equals(this.zoneCount, dashboardStats.zoneCount)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.caretakerCount, this.patientCount, this.deviceCount, this.zoneCount});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DashboardStats {\n");
        sb.append("    caretakerCount: ").append(toIndentedString(this.caretakerCount)).append("\n");
        sb.append("    patientCount: ").append(toIndentedString(this.patientCount)).append("\n");
        sb.append("    deviceCount: ").append(toIndentedString(this.deviceCount)).append("\n");
        sb.append("    zoneCount: ").append(toIndentedString(this.zoneCount)).append("\n");
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
