package com.cooey.common.vo;

public class ActionItemPatientHeader implements ListItem {
    private String patientName;

    public ActionItemPatientHeader(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getListItemType() {
        return 1;
    }
}
