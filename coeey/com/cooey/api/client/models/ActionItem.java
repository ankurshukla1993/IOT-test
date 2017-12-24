package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class ActionItem {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("assignerId")
    private String assignerId = null;
    @SerializedName("completed")
    private Boolean completed = Boolean.valueOf(false);
    @SerializedName("completedBy")
    private String completedBy = null;
    @SerializedName("completedOn")
    private Long completedOn = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("interventionId")
    private String interventionId = null;
    @SerializedName("latitude")
    private Double latitude = null;
    @SerializedName("longitude")
    private Double longitude = null;
    @SerializedName("notes")
    private String notes = null;
    @SerializedName("parameters")
    private String parameters = null;
    @SerializedName("patientId")
    private String patientId = null;
    @SerializedName("patientName")
    private String patientName = null;
    @SerializedName("permissions")
    private String permissions = null;
    @SerializedName("postAction")
    private String postAction = null;
    @SerializedName("resolutionId")
    private String resolutionId = null;
    @SerializedName("scheduledOn")
    private Long scheduledOn = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("title")
    private String title = null;
    @SerializedName("type")
    private String type = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;

    public ActionItem createdOn(Long createdOn) {
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

    public ActionItem updatedOn(Long updatedOn) {
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

    public ActionItem tenantId(String tenantId) {
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

    public ActionItem applicationId(String applicationId) {
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

    public ActionItem id(String id) {
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

    public ActionItem title(String title) {
        this.title = title;
        return this;
    }

    @ApiModelProperty("")
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ActionItem type(String type) {
        this.type = type;
        return this;
    }

    @ApiModelProperty("")
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ActionItem parameters(String parameters) {
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

    public ActionItem completedBy(String completedBy) {
        this.completedBy = completedBy;
        return this;
    }

    @ApiModelProperty("")
    public String getCompletedBy() {
        return this.completedBy;
    }

    public void setCompletedBy(String completedBy) {
        this.completedBy = completedBy;
    }

    public ActionItem postAction(String postAction) {
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

    public ActionItem patientId(String patientId) {
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

    public ActionItem patientName(String patientName) {
        this.patientName = patientName;
        return this;
    }

    @ApiModelProperty("")
    public String getPatientName() {
        return this.patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public ActionItem resolutionId(String resolutionId) {
        this.resolutionId = resolutionId;
        return this;
    }

    @ApiModelProperty("")
    public String getResolutionId() {
        return this.resolutionId;
    }

    public void setResolutionId(String resolutionId) {
        this.resolutionId = resolutionId;
    }

    public ActionItem completedOn(Long completedOn) {
        this.completedOn = completedOn;
        return this;
    }

    @ApiModelProperty("")
    public Long getCompletedOn() {
        return this.completedOn;
    }

    public void setCompletedOn(Long completedOn) {
        this.completedOn = completedOn;
    }

    public ActionItem scheduledOn(Long scheduledOn) {
        this.scheduledOn = scheduledOn;
        return this;
    }

    @ApiModelProperty("")
    public Long getScheduledOn() {
        return this.scheduledOn;
    }

    public void setScheduledOn(Long scheduledOn) {
        this.scheduledOn = scheduledOn;
    }

    public ActionItem assignerId(String assignerId) {
        this.assignerId = assignerId;
        return this;
    }

    @ApiModelProperty("")
    public String getAssignerId() {
        return this.assignerId;
    }

    public void setAssignerId(String assignerId) {
        this.assignerId = assignerId;
    }

    public ActionItem notes(String notes) {
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

    public ActionItem interventionId(String interventionId) {
        this.interventionId = interventionId;
        return this;
    }

    @ApiModelProperty("")
    public String getInterventionId() {
        return this.interventionId;
    }

    public void setInterventionId(String interventionId) {
        this.interventionId = interventionId;
    }

    public ActionItem latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    @ApiModelProperty("")
    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public ActionItem longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    @ApiModelProperty("")
    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public ActionItem permissions(String permissions) {
        this.permissions = permissions;
        return this;
    }

    @ApiModelProperty("")
    public String getPermissions() {
        return this.permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public ActionItem completed(Boolean completed) {
        this.completed = completed;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ActionItem actionItem = (ActionItem) o;
        if (Objects.equals(this.createdOn, actionItem.createdOn) && Objects.equals(this.updatedOn, actionItem.updatedOn) && Objects.equals(this.tenantId, actionItem.tenantId) && Objects.equals(this.applicationId, actionItem.applicationId) && Objects.equals(this.id, actionItem.id) && Objects.equals(this.title, actionItem.title) && Objects.equals(this.type, actionItem.type) && Objects.equals(this.parameters, actionItem.parameters) && Objects.equals(this.completedBy, actionItem.completedBy) && Objects.equals(this.postAction, actionItem.postAction) && Objects.equals(this.patientId, actionItem.patientId) && Objects.equals(this.patientName, actionItem.patientName) && Objects.equals(this.resolutionId, actionItem.resolutionId) && Objects.equals(this.completedOn, actionItem.completedOn) && Objects.equals(this.scheduledOn, actionItem.scheduledOn) && Objects.equals(this.assignerId, actionItem.assignerId) && Objects.equals(this.notes, actionItem.notes) && Objects.equals(this.interventionId, actionItem.interventionId) && Objects.equals(this.latitude, actionItem.latitude) && Objects.equals(this.longitude, actionItem.longitude) && Objects.equals(this.permissions, actionItem.permissions) && Objects.equals(this.completed, actionItem.completed)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.title, this.type, this.parameters, this.completedBy, this.postAction, this.patientId, this.patientName, this.resolutionId, this.completedOn, this.scheduledOn, this.assignerId, this.notes, this.interventionId, this.latitude, this.longitude, this.permissions, this.completed});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ActionItem {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    title: ").append(toIndentedString(this.title)).append("\n");
        sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
        sb.append("    parameters: ").append(toIndentedString(this.parameters)).append("\n");
        sb.append("    completedBy: ").append(toIndentedString(this.completedBy)).append("\n");
        sb.append("    postAction: ").append(toIndentedString(this.postAction)).append("\n");
        sb.append("    patientId: ").append(toIndentedString(this.patientId)).append("\n");
        sb.append("    patientName: ").append(toIndentedString(this.patientName)).append("\n");
        sb.append("    resolutionId: ").append(toIndentedString(this.resolutionId)).append("\n");
        sb.append("    completedOn: ").append(toIndentedString(this.completedOn)).append("\n");
        sb.append("    scheduledOn: ").append(toIndentedString(this.scheduledOn)).append("\n");
        sb.append("    assignerId: ").append(toIndentedString(this.assignerId)).append("\n");
        sb.append("    notes: ").append(toIndentedString(this.notes)).append("\n");
        sb.append("    interventionId: ").append(toIndentedString(this.interventionId)).append("\n");
        sb.append("    latitude: ").append(toIndentedString(this.latitude)).append("\n");
        sb.append("    longitude: ").append(toIndentedString(this.longitude)).append("\n");
        sb.append("    permissions: ").append(toIndentedString(this.permissions)).append("\n");
        sb.append("    completed: ").append(toIndentedString(this.completed)).append("\n");
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
