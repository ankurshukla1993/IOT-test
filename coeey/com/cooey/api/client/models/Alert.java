package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Alert {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("caretakerId")
    private String caretakerId = null;
    @SerializedName("causerId")
    private String causerId = null;
    @SerializedName("closed")
    private Boolean closed = Boolean.valueOf(false);
    @SerializedName("closedBy")
    private String closedBy = null;
    @SerializedName("closedOn")
    private Long closedOn = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("guardianId")
    private String guardianId = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("message")
    private String message = null;
    @SerializedName("messageType")
    private String messageType = null;
    @SerializedName("notes")
    private String notes = null;
    @SerializedName("notiferType")
    private String notiferType = null;
    @SerializedName("notifierId")
    private String notifierId = null;
    @SerializedName("patientId")
    private String patientId = null;
    @SerializedName("status")
    private String status = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;

    public Alert createdOn(Long createdOn) {
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

    public Alert updatedOn(Long updatedOn) {
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

    public Alert tenantId(String tenantId) {
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

    public Alert applicationId(String applicationId) {
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

    public Alert id(String id) {
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

    public Alert message(String message) {
        this.message = message;
        return this;
    }

    @ApiModelProperty("")
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Alert notifierId(String notifierId) {
        this.notifierId = notifierId;
        return this;
    }

    @ApiModelProperty("")
    public String getNotifierId() {
        return this.notifierId;
    }

    public void setNotifierId(String notifierId) {
        this.notifierId = notifierId;
    }

    public Alert causerId(String causerId) {
        this.causerId = causerId;
        return this;
    }

    @ApiModelProperty("")
    public String getCauserId() {
        return this.causerId;
    }

    public void setCauserId(String causerId) {
        this.causerId = causerId;
    }

    public Alert notiferType(String notiferType) {
        this.notiferType = notiferType;
        return this;
    }

    @ApiModelProperty("")
    public String getNotiferType() {
        return this.notiferType;
    }

    public void setNotiferType(String notiferType) {
        this.notiferType = notiferType;
    }

    public Alert messageType(String messageType) {
        this.messageType = messageType;
        return this;
    }

    @ApiModelProperty("")
    public String getMessageType() {
        return this.messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Alert status(String status) {
        this.status = status;
        return this;
    }

    @ApiModelProperty("")
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Alert notes(String notes) {
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

    public Alert closedBy(String closedBy) {
        this.closedBy = closedBy;
        return this;
    }

    @ApiModelProperty("")
    public String getClosedBy() {
        return this.closedBy;
    }

    public void setClosedBy(String closedBy) {
        this.closedBy = closedBy;
    }

    public Alert closedOn(Long closedOn) {
        this.closedOn = closedOn;
        return this;
    }

    @ApiModelProperty("")
    public Long getClosedOn() {
        return this.closedOn;
    }

    public void setClosedOn(Long closedOn) {
        this.closedOn = closedOn;
    }

    public Alert guardianId(String guardianId) {
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

    public Alert patientId(String patientId) {
        this.patientId = patientId;
        return this;
    }

    @ApiModelProperty("")
    public String getPatientId() {
        return this.patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Alert caretakerId(String caretakerId) {
        this.caretakerId = caretakerId;
        return this;
    }

    @ApiModelProperty("")
    public String getCaretakerId() {
        return this.caretakerId;
    }

    public void setCaretakerId(String caretakerId) {
        this.caretakerId = caretakerId;
    }

    public Alert closed(Boolean closed) {
        this.closed = closed;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getClosed() {
        return this.closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Alert alert = (Alert) o;
        if (Objects.equals(this.createdOn, alert.createdOn) && Objects.equals(this.updatedOn, alert.updatedOn) && Objects.equals(this.tenantId, alert.tenantId) && Objects.equals(this.applicationId, alert.applicationId) && Objects.equals(this.id, alert.id) && Objects.equals(this.message, alert.message) && Objects.equals(this.notifierId, alert.notifierId) && Objects.equals(this.causerId, alert.causerId) && Objects.equals(this.notiferType, alert.notiferType) && Objects.equals(this.messageType, alert.messageType) && Objects.equals(this.status, alert.status) && Objects.equals(this.notes, alert.notes) && Objects.equals(this.closedBy, alert.closedBy) && Objects.equals(this.closedOn, alert.closedOn) && Objects.equals(this.guardianId, alert.guardianId) && Objects.equals(this.patientId, alert.patientId) && Objects.equals(this.caretakerId, alert.caretakerId) && Objects.equals(this.closed, alert.closed)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.message, this.notifierId, this.causerId, this.notiferType, this.messageType, this.status, this.notes, this.closedBy, this.closedOn, this.guardianId, this.patientId, this.caretakerId, this.closed});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Alert {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    message: ").append(toIndentedString(this.message)).append("\n");
        sb.append("    notifierId: ").append(toIndentedString(this.notifierId)).append("\n");
        sb.append("    causerId: ").append(toIndentedString(this.causerId)).append("\n");
        sb.append("    notiferType: ").append(toIndentedString(this.notiferType)).append("\n");
        sb.append("    messageType: ").append(toIndentedString(this.messageType)).append("\n");
        sb.append("    status: ").append(toIndentedString(this.status)).append("\n");
        sb.append("    notes: ").append(toIndentedString(this.notes)).append("\n");
        sb.append("    closedBy: ").append(toIndentedString(this.closedBy)).append("\n");
        sb.append("    closedOn: ").append(toIndentedString(this.closedOn)).append("\n");
        sb.append("    guardianId: ").append(toIndentedString(this.guardianId)).append("\n");
        sb.append("    patientId: ").append(toIndentedString(this.patientId)).append("\n");
        sb.append("    caretakerId: ").append(toIndentedString(this.caretakerId)).append("\n");
        sb.append("    closed: ").append(toIndentedString(this.closed)).append("\n");
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
