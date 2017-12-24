package com.ihealth.communication.cloud;

public class ApiData {
    private String f473a;
    private String f474b;
    private String f475c;

    public String getAPIDescription() {
        return this.f475c;
    }

    public String getAPIName() {
        return this.f473a;
    }

    public String getAPIShowName() {
        return this.f474b;
    }

    public void setAPIDescription(String aPIDescription) {
        this.f475c = aPIDescription;
    }

    public void setAPIName(String aPIName) {
        this.f473a = aPIName;
    }

    public void setAPIShowName(String aPIShowName) {
        this.f474b = aPIShowName;
    }
}
