package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class EventRescheduleRequestVO {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("eventId")
    private String eventId = null;
    @SerializedName("guardianId")
    private String guardianId = null;
    @SerializedName("modifiedStartTime")
    private Long modifiedStartTime = null;
    @SerializedName("notes")
    private String notes = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;
    @SerializedName("userId")
    private String userId = null;

    public EventRescheduleRequestVO createdOn(Long createdOn) {
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

    public EventRescheduleRequestVO updatedOn(Long updatedOn) {
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

    public EventRescheduleRequestVO tenantId(String tenantId) {
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

    public EventRescheduleRequestVO applicationId(String applicationId) {
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

    public EventRescheduleRequestVO eventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    @ApiModelProperty("")
    public String getEventId() {
        return this.eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public EventRescheduleRequestVO notes(String notes) {
        this.notes = notes;
        return this;
    }

    @ApiModelProperty("")
    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public EventRescheduleRequestVO userId(String userId) {
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

    public EventRescheduleRequestVO guardianId(String guardianId) {
        this.guardianId = guardianId;
        return this;
    }

    @ApiModelProperty("")
    public String getGuardianId() {
        return this.guardianId;
    }

    public void setGuardianId(String guardianId) {
        this.guardianId = guardianId;
    }

    public EventRescheduleRequestVO modifiedStartTime(Long modifiedStartTime) {
        this.modifiedStartTime = modifiedStartTime;
        return this;
    }

    @ApiModelProperty("")
    public Long getModifiedStartTime() {
        return this.modifiedStartTime;
    }

    public void setModifiedStartTime(Long modifiedStartTime) {
        this.modifiedStartTime = modifiedStartTime;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventRescheduleRequestVO eventRescheduleRequestVO = (EventRescheduleRequestVO) o;
        if (Objects.equals(this.createdOn, eventRescheduleRequestVO.createdOn) && Objects.equals(this.updatedOn, eventRescheduleRequestVO.updatedOn) && Objects.equals(this.tenantId, eventRescheduleRequestVO.tenantId) && Objects.equals(this.applicationId, eventRescheduleRequestVO.applicationId) && Objects.equals(this.eventId, eventRescheduleRequestVO.eventId) && Objects.equals(this.notes, eventRescheduleRequestVO.notes) && Objects.equals(this.userId, eventRescheduleRequestVO.userId) && Objects.equals(this.guardianId, eventRescheduleRequestVO.guardianId) && Objects.equals(this.modifiedStartTime, eventRescheduleRequestVO.modifiedStartTime)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.eventId, this.notes, this.userId, this.guardianId, this.modifiedStartTime});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EventRescheduleRequestVO {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    eventId: ").append(toIndentedString(this.eventId)).append("\n");
        sb.append("    notes: ").append(toIndentedString(this.notes)).append("\n");
        sb.append("    userId: ").append(toIndentedString(this.userId)).append("\n");
        sb.append("    guardianId: ").append(toIndentedString(this.guardianId)).append("\n");
        sb.append("    modifiedStartTime: ").append(toIndentedString(this.modifiedStartTime)).append("\n");
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
