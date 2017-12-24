package com.cooey.common.config;

import com.google.gson.annotations.SerializedName;

public class VitalConfigResponse {
    @SerializedName("andriodConfigResponse")
    private AndriodConfigResponse andriodConfigResponse;
    @SerializedName("dietConfig")
    private DietConfig dietConfig;
    @SerializedName("id")
    private String id;
    @SerializedName("templateConfig")
    private TemplateConfig templateConfig;
    @SerializedName("vitalConfig")
    private VitalConfig vitalConfig;

    public void setTemplateConfig(TemplateConfig templateConfig) {
        this.templateConfig = templateConfig;
    }

    public TemplateConfig getTemplateConfig() {
        return this.templateConfig;
    }

    public void setAndriodConfigResponse(AndriodConfigResponse andriodConfigResponse) {
        this.andriodConfigResponse = andriodConfigResponse;
    }

    public AndriodConfigResponse getAndriodConfigResponse() {
        return this.andriodConfigResponse;
    }

    public void setDietConfig(DietConfig dietConfig) {
        this.dietConfig = dietConfig;
    }

    public DietConfig getDietConfig() {
        return this.dietConfig;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setVitalConfig(VitalConfig vitalConfig) {
        this.vitalConfig = vitalConfig;
    }

    public VitalConfig getVitalConfig() {
        return this.vitalConfig;
    }
}
