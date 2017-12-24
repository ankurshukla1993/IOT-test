package com.cooey.common.vo;

import com.cooey.common.vo.careplan.Assessment;
import com.cooey.common.vo.careplan.Diagnosis;
import java.io.Serializable;
import java.util.List;

public class Assignment implements Serializable {
    private Assessment assessment;
    private String assignerId;
    private long beginTime;
    private CareplanBlueprint careplanBlueprint;
    private String careplanId;
    private long createdOn;
    private Diagnosis diagnosis;
    private String evaluation;
    private String goal;
    private String history;
    private List<InterventionBlueprint> interventionBlueprintList;
    private String patientId;
    private long startTime;
    private String tenantId;
    private long updatedOn;

    public long getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public long getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getPatientId() {
        return this.patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getCareplanId() {
        return this.careplanId;
    }

    public void setCareplanId(String careplanId) {
        this.careplanId = careplanId;
    }

    public Assessment getAssessment() {
        return this.assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public String getHistory() {
        return this.history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getGoal() {
        return this.goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getEvaluation() {
        return this.evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public CareplanBlueprint getCareplanBlueprint() {
        return this.careplanBlueprint;
    }

    public Diagnosis getDiagnosis() {
        return this.diagnosis;
    }

    public String getAssignerId() {
        return this.assignerId;
    }

    public void setAssignerId(String assignerId) {
        this.assignerId = assignerId;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setCareplanBlueprint(CareplanBlueprint careplanBlueprint) {
        this.careplanBlueprint = careplanBlueprint;
    }

    public List<InterventionBlueprint> getInterventionBlueprintList() {
        return this.interventionBlueprintList;
    }

    public void setInterventionBlueprintList(List<InterventionBlueprint> interventionBlueprintList) {
        this.interventionBlueprintList = interventionBlueprintList;
    }
}
