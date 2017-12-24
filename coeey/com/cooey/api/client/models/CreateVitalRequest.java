package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class CreateVitalRequest {
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

    public CreateVitalRequest createdOn(Long createdOn) {
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

    public CreateVitalRequest updatedOn(Long updatedOn) {
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

    public CreateVitalRequest tenantId(String tenantId) {
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

    public CreateVitalRequest applicationId(String applicationId) {
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

    public CreateVitalRequest id(String id) {
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

    public CreateVitalRequest vitalType(String vitalType) {
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

    public CreateVitalRequest takenOn(Long takenOn) {
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

    public CreateVitalRequest parameters(String parameters) {
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

    public CreateVitalRequest userId(String userId) {
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

    public CreateVitalRequest takenBy(String takenBy) {
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

    public CreateVitalRequest postAction(String postAction) {
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

    public CreateVitalRequest deviceId(String deviceId) {
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

    public CreateVitalRequest deviceType(String deviceType) {
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

    public CreateVitalRequest platform(String platform) {
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

    public CreateVitalRequest deviceReading(Boolean deviceReading) {
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
        CreateVitalRequest createVitalRequest = (CreateVitalRequest) o;
        if (Objects.equals(this.createdOn, createVitalRequest.createdOn) && Objects.equals(this.updatedOn, createVitalRequest.updatedOn) && Objects.equals(this.tenantId, createVitalRequest.tenantId) && Objects.equals(this.applicationId, createVitalRequest.applicationId) && Objects.equals(this.id, createVitalRequest.id) && Objects.equals(this.vitalType, createVitalRequest.vitalType) && Objects.equals(this.takenOn, createVitalRequest.takenOn) && Objects.equals(this.parameters, createVitalRequest.parameters) && Objects.equals(this.userId, createVitalRequest.userId) && Objects.equals(this.takenBy, createVitalRequest.takenBy) && Objects.equals(this.postAction, createVitalRequest.postAction) && Objects.equals(this.deviceId, createVitalRequest.deviceId) && Objects.equals(this.deviceType, createVitalRequest.deviceType) && Objects.equals(this.platform, createVitalRequest.platform) && Objects.equals(this.deviceReading, createVitalRequest.deviceReading)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.vitalType, this.takenOn, this.parameters, this.userId, this.takenBy, this.postAction, this.deviceId, this.deviceType, this.platform, this.deviceReading});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateVitalRequest {\n");
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
