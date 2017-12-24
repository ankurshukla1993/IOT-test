package com.cooey.common.vo;

import com.google.gson.annotations.SerializedName;

public class FeedBackInputItem {
    @SerializedName("caretakerAlert")
    private boolean caretakerAlert;
    @SerializedName("guardianAlert")
    private boolean guardianAlert;
    @SerializedName("label")
    private String label;
    @SerializedName("patientAlert")
    private boolean patientAlert;
    @SerializedName("type")
    private String type;
    @SerializedName("value")
    private String value;

    public void setPatientAlert(boolean patientAlert) {
        this.patientAlert = patientAlert;
    }

    public boolean isPatientAlert() {
        return this.patientAlert;
    }

    public void setGuardianAlert(boolean guardianAlert) {
        this.guardianAlert = guardianAlert;
    }

    public boolean isGuardianAlert() {
        return this.guardianAlert;
    }

    public void setCaretakerAlert(boolean caretakerAlert) {
        this.caretakerAlert = caretakerAlert;
    }

    public boolean isCaretakerAlert() {
        return this.caretakerAlert;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
