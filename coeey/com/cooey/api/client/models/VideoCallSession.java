package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class VideoCallSession {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("from")
    private String from = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("jitsiVideoUrl")
    private String jitsiVideoUrl = null;
    @SerializedName("requestTime")
    private Long requestTime = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("to")
    private String to = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;

    public VideoCallSession createdOn(Long createdOn) {
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

    public VideoCallSession updatedOn(Long updatedOn) {
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

    public VideoCallSession tenantId(String tenantId) {
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

    public VideoCallSession applicationId(String applicationId) {
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

    public VideoCallSession id(String id) {
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

    public VideoCallSession from(String from) {
        this.from = from;
        return this;
    }

    @ApiModelProperty("")
    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public VideoCallSession to(String to) {
        this.to = to;
        return this;
    }

    @ApiModelProperty("")
    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public VideoCallSession requestTime(Long requestTime) {
        this.requestTime = requestTime;
        return this;
    }

    @ApiModelProperty("")
    public Long getRequestTime() {
        return this.requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    public VideoCallSession jitsiVideoUrl(String jitsiVideoUrl) {
        this.jitsiVideoUrl = jitsiVideoUrl;
        return this;
    }

    @ApiModelProperty("")
    public String getJitsiVideoUrl() {
        return this.jitsiVideoUrl;
    }

    public void setJitsiVideoUrl(String jitsiVideoUrl) {
        this.jitsiVideoUrl = jitsiVideoUrl;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VideoCallSession videoCallSession = (VideoCallSession) o;
        if (Objects.equals(this.createdOn, videoCallSession.createdOn) && Objects.equals(this.updatedOn, videoCallSession.updatedOn) && Objects.equals(this.tenantId, videoCallSession.tenantId) && Objects.equals(this.applicationId, videoCallSession.applicationId) && Objects.equals(this.id, videoCallSession.id) && Objects.equals(this.from, videoCallSession.from) && Objects.equals(this.to, videoCallSession.to) && Objects.equals(this.requestTime, videoCallSession.requestTime) && Objects.equals(this.jitsiVideoUrl, videoCallSession.jitsiVideoUrl)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.from, this.to, this.requestTime, this.jitsiVideoUrl});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VideoCallSession {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    from: ").append(toIndentedString(this.from)).append("\n");
        sb.append("    to: ").append(toIndentedString(this.to)).append("\n");
        sb.append("    requestTime: ").append(toIndentedString(this.requestTime)).append("\n");
        sb.append("    jitsiVideoUrl: ").append(toIndentedString(this.jitsiVideoUrl)).append("\n");
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
