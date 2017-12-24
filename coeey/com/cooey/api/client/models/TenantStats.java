package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class TenantStats {
    @SerializedName("caretakerCount")
    private Long caretakerCount = null;
    @SerializedName("deviceCount")
    private Long deviceCount = null;
    @SerializedName("deviceId")
    private String deviceId = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("patientCount")
    private Long patientCount = null;
    @SerializedName("zoneCount")
    private Long zoneCount = null;

    public TenantStats id(String id) {
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

    public TenantStats deviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    @ApiModelProperty("")
    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public TenantStats zoneCount(Long zoneCount) {
        this.zoneCount = zoneCount;
        return this;
    }

    @ApiModelProperty("")
    public Long getZoneCount() {
        return this.zoneCount;
    }

    public void setZoneCount(Long zoneCount) {
        this.zoneCount = zoneCount;
    }

    public TenantStats patientCount(Long patientCount) {
        this.patientCount = patientCount;
        return this;
    }

    @ApiModelProperty("")
    public Long getPatientCount() {
        return this.patientCount;
    }

    public void setPatientCount(Long patientCount) {
        this.patientCount = patientCount;
    }

    public TenantStats caretakerCount(Long caretakerCount) {
        this.caretakerCount = caretakerCount;
        return this;
    }

    @ApiModelProperty("")
    public Long getCaretakerCount() {
        return this.caretakerCount;
    }

    public void setCaretakerCount(Long caretakerCount) {
        this.caretakerCount = caretakerCount;
    }

    public TenantStats deviceCount(Long deviceCount) {
        this.deviceCount = deviceCount;
        return this;
    }

    @ApiModelProperty("")
    public Long getDeviceCount() {
        return this.deviceCount;
    }

    public void setDeviceCount(Long deviceCount) {
        this.deviceCount = deviceCount;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TenantStats tenantStats = (TenantStats) o;
        if (Objects.equals(this.id, tenantStats.id) && Objects.equals(this.deviceId, tenantStats.deviceId) && Objects.equals(this.zoneCount, tenantStats.zoneCount) && Objects.equals(this.patientCount, tenantStats.patientCount) && Objects.equals(this.caretakerCount, tenantStats.caretakerCount) && Objects.equals(this.deviceCount, tenantStats.deviceCount)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.deviceId, this.zoneCount, this.patientCount, this.caretakerCount, this.deviceCount});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TenantStats {\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    deviceId: ").append(toIndentedString(this.deviceId)).append("\n");
        sb.append("    zoneCount: ").append(toIndentedString(this.zoneCount)).append("\n");
        sb.append("    patientCount: ").append(toIndentedString(this.patientCount)).append("\n");
        sb.append("    caretakerCount: ").append(toIndentedString(this.caretakerCount)).append("\n");
        sb.append("    deviceCount: ").append(toIndentedString(this.deviceCount)).append("\n");
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
