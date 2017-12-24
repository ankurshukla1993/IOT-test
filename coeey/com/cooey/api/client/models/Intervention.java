package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Intervention {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("assignedOn")
    private Long assignedOn = null;
    @SerializedName("caretakerNotificationEnabled")
    private Boolean caretakerNotificationEnabled = Boolean.valueOf(false);
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("guaridanNotificationEnabled")
    private Boolean guaridanNotificationEnabled = Boolean.valueOf(false);
    @SerializedName("id")
    private String id = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("ownerId")
    private String ownerId = null;
    @SerializedName("parameters")
    private String parameters = null;
    @SerializedName("parentId")
    private String parentId = null;
    @SerializedName("patientId")
    private String patientId = null;
    @SerializedName("patientNotificationEnabled")
    private Boolean patientNotificationEnabled = Boolean.valueOf(false);
    @SerializedName("permissions")
    private String permissions = null;
    @SerializedName("schedule")
    private Schedule schedule = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("type")
    private String type = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;

    public Intervention createdOn(Long createdOn) {
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

    public Intervention updatedOn(Long updatedOn) {
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

    public Intervention tenantId(String tenantId) {
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

    public Intervention applicationId(String applicationId) {
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

    public Intervention id(String id) {
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

    public Intervention name(String name) {
        this.name = name;
        return this;
    }

    @ApiModelProperty("")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Intervention type(String type) {
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

    public Intervention parameters(String parameters) {
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

    public Intervention parentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    @ApiModelProperty("")
    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Intervention ownerId(String ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    @ApiModelProperty("")
    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Intervention schedule(Schedule schedule) {
        this.schedule = schedule;
        return this;
    }

    @ApiModelProperty("")
    public Schedule getSchedule() {
        return this.schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Intervention permissions(String permissions) {
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

    public Intervention patientId(String patientId) {
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

    public Intervention assignedOn(Long assignedOn) {
        this.assignedOn = assignedOn;
        return this;
    }

    @ApiModelProperty("")
    public Long getAssignedOn() {
        return this.assignedOn;
    }

    public void setAssignedOn(Long assignedOn) {
        this.assignedOn = assignedOn;
    }

    public Intervention caretakerNotificationEnabled(Boolean caretakerNotificationEnabled) {
        this.caretakerNotificationEnabled = caretakerNotificationEnabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getCaretakerNotificationEnabled() {
        return this.caretakerNotificationEnabled;
    }

    public void setCaretakerNotificationEnabled(Boolean caretakerNotificationEnabled) {
        this.caretakerNotificationEnabled = caretakerNotificationEnabled;
    }

    public Intervention guaridanNotificationEnabled(Boolean guaridanNotificationEnabled) {
        this.guaridanNotificationEnabled = guaridanNotificationEnabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getGuaridanNotificationEnabled() {
        return this.guaridanNotificationEnabled;
    }

    public void setGuaridanNotificationEnabled(Boolean guaridanNotificationEnabled) {
        this.guaridanNotificationEnabled = guaridanNotificationEnabled;
    }

    public Intervention patientNotificationEnabled(Boolean patientNotificationEnabled) {
        this.patientNotificationEnabled = patientNotificationEnabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getPatientNotificationEnabled() {
        return this.patientNotificationEnabled;
    }

    public void setPatientNotificationEnabled(Boolean patientNotificationEnabled) {
        this.patientNotificationEnabled = patientNotificationEnabled;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Intervention intervention = (Intervention) o;
        if (Objects.equals(this.createdOn, intervention.createdOn) && Objects.equals(this.updatedOn, intervention.updatedOn) && Objects.equals(this.tenantId, intervention.tenantId) && Objects.equals(this.applicationId, intervention.applicationId) && Objects.equals(this.id, intervention.id) && Objects.equals(this.name, intervention.name) && Objects.equals(this.type, intervention.type) && Objects.equals(this.parameters, intervention.parameters) && Objects.equals(this.parentId, intervention.parentId) && Objects.equals(this.ownerId, intervention.ownerId) && Objects.equals(this.schedule, intervention.schedule) && Objects.equals(this.permissions, intervention.permissions) && Objects.equals(this.patientId, intervention.patientId) && Objects.equals(this.assignedOn, intervention.assignedOn) && Objects.equals(this.caretakerNotificationEnabled, intervention.caretakerNotificationEnabled) && Objects.equals(this.guaridanNotificationEnabled, intervention.guaridanNotificationEnabled) && Objects.equals(this.patientNotificationEnabled, intervention.patientNotificationEnabled)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.name, this.type, this.parameters, this.parentId, this.ownerId, this.schedule, this.permissions, this.patientId, this.assignedOn, this.caretakerNotificationEnabled, this.guaridanNotificationEnabled, this.patientNotificationEnabled});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Intervention {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
        sb.append("    parameters: ").append(toIndentedString(this.parameters)).append("\n");
        sb.append("    parentId: ").append(toIndentedString(this.parentId)).append("\n");
        sb.append("    ownerId: ").append(toIndentedString(this.ownerId)).append("\n");
        sb.append("    schedule: ").append(toIndentedString(this.schedule)).append("\n");
        sb.append("    permissions: ").append(toIndentedString(this.permissions)).append("\n");
        sb.append("    patientId: ").append(toIndentedString(this.patientId)).append("\n");
        sb.append("    assignedOn: ").append(toIndentedString(this.assignedOn)).append("\n");
        sb.append("    caretakerNotificationEnabled: ").append(toIndentedString(this.caretakerNotificationEnabled)).append("\n");
        sb.append("    guaridanNotificationEnabled: ").append(toIndentedString(this.guaridanNotificationEnabled)).append("\n");
        sb.append("    patientNotificationEnabled: ").append(toIndentedString(this.patientNotificationEnabled)).append("\n");
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
