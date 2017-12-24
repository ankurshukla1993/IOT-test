package com.cooey.devices.vo;

import com.lifesense.ble.bean.WeightUserInfo;

public class UserInfo extends WeightUserInfo {
    private String contextId;
    private String contextType;
    private String patientId;

    public String getContextId() {
        return this.contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public String getContextType() {
        return this.contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    public String getPatientId() {
        return this.patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
