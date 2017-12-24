package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Careplan {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("assessment")
    private Assessment assessment = null;
    @SerializedName("assignedOn")
    private Long assignedOn = null;
    @SerializedName("assignerId")
    private String assignerId = null;
    @SerializedName("beginTime")
    private Long beginTime = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("description")
    private String description = null;
    @SerializedName("diagnosis")
    private Diagnosis diagnosis = null;
    @SerializedName("enabled")
    private Boolean enabled = Boolean.valueOf(false);
    @SerializedName("endTime")
    private Long endTime = null;
    @SerializedName("evaluation")
    private String evaluation = null;
    @SerializedName("goal")
    private String goal = null;
    @SerializedName("history")
    private String history = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("levelOfAssistance")
    private String levelOfAssistance = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("numOfDays")
    private Integer numOfDays = null;
    @SerializedName("parentId")
    private String parentId = null;
    @SerializedName("patientId")
    private String patientId = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;

    public Careplan createdOn(Long createdOn) {
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

    public Careplan updatedOn(Long updatedOn) {
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

    public Careplan tenantId(String tenantId) {
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

    public Careplan applicationId(String applicationId) {
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

    public Careplan id(String id) {
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

    public Careplan name(String name) {
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

    public Careplan description(String description) {
        this.description = description;
        return this;
    }

    @ApiModelProperty("")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Careplan parentId(String parentId) {
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

    public Careplan numOfDays(Integer numOfDays) {
        this.numOfDays = numOfDays;
        return this;
    }

    @ApiModelProperty("")
    public Integer getNumOfDays() {
        return this.numOfDays;
    }

    public void setNumOfDays(Integer numOfDays) {
        this.numOfDays = numOfDays;
    }

    public Careplan assessment(Assessment assessment) {
        this.assessment = assessment;
        return this;
    }

    @ApiModelProperty("")
    public Assessment getAssessment() {
        return this.assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public Careplan diagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
        return this;
    }

    @ApiModelProperty("")
    public Diagnosis getDiagnosis() {
        return this.diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Careplan history(String history) {
        this.history = history;
        return this;
    }

    @ApiModelProperty("")
    public String getHistory() {
        return this.history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Careplan goal(String goal) {
        this.goal = goal;
        return this;
    }

    @ApiModelProperty("")
    public String getGoal() {
        return this.goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Careplan evaluation(String evaluation) {
        this.evaluation = evaluation;
        return this;
    }

    @ApiModelProperty("")
    public String getEvaluation() {
        return this.evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public Careplan assignerId(String assignerId) {
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

    public Careplan assignedOn(Long assignedOn) {
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

    public Careplan patientId(String patientId) {
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

    public Careplan levelOfAssistance(String levelOfAssistance) {
        this.levelOfAssistance = levelOfAssistance;
        return this;
    }

    @ApiModelProperty("")
    public String getLevelOfAssistance() {
        return this.levelOfAssistance;
    }

    public void setLevelOfAssistance(String levelOfAssistance) {
        this.levelOfAssistance = levelOfAssistance;
    }

    public Careplan beginTime(Long beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    @ApiModelProperty("")
    public Long getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Careplan endTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }

    @ApiModelProperty("")
    public Long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Careplan enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Careplan careplan = (Careplan) o;
        if (Objects.equals(this.createdOn, careplan.createdOn) && Objects.equals(this.updatedOn, careplan.updatedOn) && Objects.equals(this.tenantId, careplan.tenantId) && Objects.equals(this.applicationId, careplan.applicationId) && Objects.equals(this.id, careplan.id) && Objects.equals(this.name, careplan.name) && Objects.equals(this.description, careplan.description) && Objects.equals(this.parentId, careplan.parentId) && Objects.equals(this.numOfDays, careplan.numOfDays) && Objects.equals(this.assessment, careplan.assessment) && Objects.equals(this.diagnosis, careplan.diagnosis) && Objects.equals(this.history, careplan.history) && Objects.equals(this.goal, careplan.goal) && Objects.equals(this.evaluation, careplan.evaluation) && Objects.equals(this.assignerId, careplan.assignerId) && Objects.equals(this.assignedOn, careplan.assignedOn) && Objects.equals(this.patientId, careplan.patientId) && Objects.equals(this.levelOfAssistance, careplan.levelOfAssistance) && Objects.equals(this.beginTime, careplan.beginTime) && Objects.equals(this.endTime, careplan.endTime) && Objects.equals(this.enabled, careplan.enabled)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.name, this.description, this.parentId, this.numOfDays, this.assessment, this.diagnosis, this.history, this.goal, this.evaluation, this.assignerId, this.assignedOn, this.patientId, this.levelOfAssistance, this.beginTime, this.endTime, this.enabled});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Careplan {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    description: ").append(toIndentedString(this.description)).append("\n");
        sb.append("    parentId: ").append(toIndentedString(this.parentId)).append("\n");
        sb.append("    numOfDays: ").append(toIndentedString(this.numOfDays)).append("\n");
        sb.append("    assessment: ").append(toIndentedString(this.assessment)).append("\n");
        sb.append("    diagnosis: ").append(toIndentedString(this.diagnosis)).append("\n");
        sb.append("    history: ").append(toIndentedString(this.history)).append("\n");
        sb.append("    goal: ").append(toIndentedString(this.goal)).append("\n");
        sb.append("    evaluation: ").append(toIndentedString(this.evaluation)).append("\n");
        sb.append("    assignerId: ").append(toIndentedString(this.assignerId)).append("\n");
        sb.append("    assignedOn: ").append(toIndentedString(this.assignedOn)).append("\n");
        sb.append("    patientId: ").append(toIndentedString(this.patientId)).append("\n");
        sb.append("    levelOfAssistance: ").append(toIndentedString(this.levelOfAssistance)).append("\n");
        sb.append("    beginTime: ").append(toIndentedString(this.beginTime)).append("\n");
        sb.append("    endTime: ").append(toIndentedString(this.endTime)).append("\n");
        sb.append("    enabled: ").append(toIndentedString(this.enabled)).append("\n");
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
