package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AssignCareplanRequest {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("assessment")
    private Assessment assessment = null;
    @SerializedName("assignerId")
    private String assignerId = null;
    @SerializedName("beginTime")
    private Long beginTime = null;
    @SerializedName("careplanBlueprint")
    private CareplanBlueprint careplanBlueprint = null;
    @SerializedName("careplanId")
    private String careplanId = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("diagnosis")
    private Diagnosis diagnosis = null;
    @SerializedName("evaluation")
    private String evaluation = null;
    @SerializedName("goal")
    private String goal = null;
    @SerializedName("history")
    private String history = null;
    @SerializedName("interventionBlueprintList")
    private List<InterventionBlueprint> interventionBlueprintList = null;
    @SerializedName("levelOfAssistance")
    private String levelOfAssistance = null;
    @SerializedName("patientId")
    private String patientId = null;
    @SerializedName("startTime")
    private Long startTime = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;

    public AssignCareplanRequest createdOn(Long createdOn) {
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

    public AssignCareplanRequest updatedOn(Long updatedOn) {
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

    public AssignCareplanRequest tenantId(String tenantId) {
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

    public AssignCareplanRequest applicationId(String applicationId) {
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

    public AssignCareplanRequest patientId(String patientId) {
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

    public AssignCareplanRequest careplanId(String careplanId) {
        this.careplanId = careplanId;
        return this;
    }

    @ApiModelProperty("")
    public String getCareplanId() {
        return this.careplanId;
    }

    public void setCareplanId(String careplanId) {
        this.careplanId = careplanId;
    }

    public AssignCareplanRequest assessment(Assessment assessment) {
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

    public AssignCareplanRequest diagnosis(Diagnosis diagnosis) {
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

    public AssignCareplanRequest history(String history) {
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

    public AssignCareplanRequest goal(String goal) {
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

    public AssignCareplanRequest evaluation(String evaluation) {
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

    public AssignCareplanRequest startTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    @ApiModelProperty("")
    public Long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public AssignCareplanRequest assignerId(String assignerId) {
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

    public AssignCareplanRequest levelOfAssistance(String levelOfAssistance) {
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

    public AssignCareplanRequest beginTime(Long beginTime) {
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

    public AssignCareplanRequest careplanBlueprint(CareplanBlueprint careplanBlueprint) {
        this.careplanBlueprint = careplanBlueprint;
        return this;
    }

    @ApiModelProperty("")
    public CareplanBlueprint getCareplanBlueprint() {
        return this.careplanBlueprint;
    }

    public void setCareplanBlueprint(CareplanBlueprint careplanBlueprint) {
        this.careplanBlueprint = careplanBlueprint;
    }

    public AssignCareplanRequest interventionBlueprintList(List<InterventionBlueprint> interventionBlueprintList) {
        this.interventionBlueprintList = interventionBlueprintList;
        return this;
    }

    public AssignCareplanRequest addInterventionBlueprintListItem(InterventionBlueprint interventionBlueprintListItem) {
        if (this.interventionBlueprintList == null) {
            this.interventionBlueprintList = new ArrayList();
        }
        this.interventionBlueprintList.add(interventionBlueprintListItem);
        return this;
    }

    @ApiModelProperty("")
    public List<InterventionBlueprint> getInterventionBlueprintList() {
        return this.interventionBlueprintList;
    }

    public void setInterventionBlueprintList(List<InterventionBlueprint> interventionBlueprintList) {
        this.interventionBlueprintList = interventionBlueprintList;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AssignCareplanRequest assignCareplanRequest = (AssignCareplanRequest) o;
        if (Objects.equals(this.createdOn, assignCareplanRequest.createdOn) && Objects.equals(this.updatedOn, assignCareplanRequest.updatedOn) && Objects.equals(this.tenantId, assignCareplanRequest.tenantId) && Objects.equals(this.applicationId, assignCareplanRequest.applicationId) && Objects.equals(this.patientId, assignCareplanRequest.patientId) && Objects.equals(this.careplanId, assignCareplanRequest.careplanId) && Objects.equals(this.assessment, assignCareplanRequest.assessment) && Objects.equals(this.diagnosis, assignCareplanRequest.diagnosis) && Objects.equals(this.history, assignCareplanRequest.history) && Objects.equals(this.goal, assignCareplanRequest.goal) && Objects.equals(this.evaluation, assignCareplanRequest.evaluation) && Objects.equals(this.startTime, assignCareplanRequest.startTime) && Objects.equals(this.assignerId, assignCareplanRequest.assignerId) && Objects.equals(this.levelOfAssistance, assignCareplanRequest.levelOfAssistance) && Objects.equals(this.beginTime, assignCareplanRequest.beginTime) && Objects.equals(this.careplanBlueprint, assignCareplanRequest.careplanBlueprint) && Objects.equals(this.interventionBlueprintList, assignCareplanRequest.interventionBlueprintList)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.patientId, this.careplanId, this.assessment, this.diagnosis, this.history, this.goal, this.evaluation, this.startTime, this.assignerId, this.levelOfAssistance, this.beginTime, this.careplanBlueprint, this.interventionBlueprintList});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AssignCareplanRequest {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    patientId: ").append(toIndentedString(this.patientId)).append("\n");
        sb.append("    careplanId: ").append(toIndentedString(this.careplanId)).append("\n");
        sb.append("    assessment: ").append(toIndentedString(this.assessment)).append("\n");
        sb.append("    diagnosis: ").append(toIndentedString(this.diagnosis)).append("\n");
        sb.append("    history: ").append(toIndentedString(this.history)).append("\n");
        sb.append("    goal: ").append(toIndentedString(this.goal)).append("\n");
        sb.append("    evaluation: ").append(toIndentedString(this.evaluation)).append("\n");
        sb.append("    startTime: ").append(toIndentedString(this.startTime)).append("\n");
        sb.append("    assignerId: ").append(toIndentedString(this.assignerId)).append("\n");
        sb.append("    levelOfAssistance: ").append(toIndentedString(this.levelOfAssistance)).append("\n");
        sb.append("    beginTime: ").append(toIndentedString(this.beginTime)).append("\n");
        sb.append("    careplanBlueprint: ").append(toIndentedString(this.careplanBlueprint)).append("\n");
        sb.append("    interventionBlueprintList: ").append(toIndentedString(this.interventionBlueprintList)).append("\n");
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
