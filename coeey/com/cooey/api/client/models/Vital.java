package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Vital {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("deviceId")
    private String deviceId = null;
    @SerializedName("deviceReading")
    private Boolean deviceReading = Boolean.valueOf(false);
    @SerializedName("deviceType")
    private String deviceType = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("parameters")
    private String parameters = null;
    @SerializedName("platform")
    private String platform = null;
    @SerializedName("postAction")
    private String postAction = null;
    @SerializedName("takenBy")
    private String takenBy = null;
    @SerializedName("takenOn")
    private Long takenOn = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;
    @SerializedName("userId")
    private String userId = null;
    @SerializedName("vitalType")
    private String vitalType = null;

    public Vital createdOn(Long createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @ApiModelProperty("")
    public Long getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public Vital updatedOn(Long updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    @ApiModelProperty("")
    public Long getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Vital tenantId(String tenantId) {
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

    public Vital applicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    @ApiModelProperty("")
    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public Vital id(String id) {
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

    public Vital vitalType(String vitalType) {
        this.vitalType = vitalType;
        return this;
    }

    @ApiModelProperty("")
    public String getVitalType() {
        return this.vitalType;
    }

    public void setVitalType(String vitalType) {
        this.vitalType = vitalType;
    }

    public Vital takenOn(Long takenOn) {
        this.takenOn = takenOn;
        return this;
    }

    @ApiModelProperty("")
    public Long getTakenOn() {
        return this.takenOn;
    }

    public void setTakenOn(Long takenOn) {
        this.takenOn = takenOn;
    }

    public Vital parameters(String parameters) {
        this.parameters = parameters;
        return this;
    }

    @ApiModelProperty("")
    public String getParameters() {
        return this.parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public Vital userId(String userId) {
        this.userId = userId;
        return this;
    }

    @ApiModelProperty("")
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Vital takenBy(String takenBy) {
        this.takenBy = takenBy;
        return this;
    }

    @ApiModelProperty("")
    public String getTakenBy() {
        return this.takenBy;
    }

    public void setTakenBy(String takenBy) {
        this.takenBy = takenBy;
    }

    public Vital postAction(String postAction) {
        this.postAction = postAction;
        return this;
    }

    @ApiModelProperty("")
    public String getPostAction() {
        return this.postAction;
    }

    public void setPostAction(String postAction) {
        this.postAction = postAction;
    }

    public Vital deviceId(String deviceId) {
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

    public Vital deviceType(String deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    @ApiModelProperty("")
    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Vital platform(String platform) {
        this.platform = platform;
        return this;
    }

    @ApiModelProperty("")
    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Vital deviceReading(Boolean deviceReading) {
        this.deviceReading = deviceReading;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getDeviceReading() {
        return this.deviceReading;
    }

    public void setDeviceReading(Boolean deviceReading) {
        this.deviceReading = deviceReading;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vital vital = (Vital) o;
        if (Objects.equals(this.createdOn, vital.createdOn) && Objects.equals(this.updatedOn, vital.updatedOn) && Objects.equals(this.tenantId, vital.tenantId) && Objects.equals(this.applicationId, vital.applicationId) && Objects.equals(this.id, vital.id) && Objects.equals(this.vitalType, vital.vitalType) && Objects.equals(this.takenOn, vital.takenOn) && Objects.equals(this.parameters, vital.parameters) && Objects.equals(this.userId, vital.userId) && Objects.equals(this.takenBy, vital.takenBy) && Objects.equals(this.postAction, vital.postAction) && Objects.equals(this.deviceId, vital.deviceId) && Objects.equals(this.deviceType, vital.deviceType) && Objects.equals(this.platform, vital.platform) && Objects.equals(this.deviceReading, vital.deviceReading)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.vitalType, this.takenOn, this.parameters, this.userId, this.takenBy, this.postAction, this.deviceId, this.deviceType, this.platform, this.deviceReading});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Vital {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    vitalType: ").append(toIndentedString(this.vitalType)).append("\n");
        sb.append("    takenOn: ").append(toIndentedString(this.takenOn)).append("\n");
        sb.append("    parameters: ").append(toIndentedString(this.parameters)).append("\n");
        sb.append("    userId: ").append(toIndentedString(this.userId)).append("\n");
        sb.append("    takenBy: ").append(toIndentedString(this.takenBy)).append("\n");
        sb.append("    postAction: ").append(toIndentedString(this.postAction)).append("\n");
        sb.append("    deviceId: ").append(toIndentedString(this.deviceId)).append("\n");
        sb.append("    deviceType: ").append(toIndentedString(this.deviceType)).append("\n");
        sb.append("    platform: ").append(toIndentedString(this.platform)).append("\n");
        sb.append("    deviceReading: ").append(toIndentedString(this.deviceReading)).append("\n");
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
