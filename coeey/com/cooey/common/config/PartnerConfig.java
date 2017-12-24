package com.cooey.common.config;

public class PartnerConfig {
    private ConfigColor configColor;
    private long createdOn;
    private DietConfig dietConfig;
    private String faviconURL;
    private String id;
    private String logoURL;
    private String tenantId;
    private String tenantName;
    private long updatedOn;
    private VitalConfig vitalConfig;

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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenantName() {
        return this.tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public ConfigColor getConfigColor() {
        return this.configColor;
    }

    public void setConfigColor(ConfigColor configColor) {
        this.configColor = configColor;
    }

    public String getLogoURL() {
        return this.logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getFaviconURL() {
        return this.faviconURL;
    }

    public void setFaviconURL(String faviconURL) {
        this.faviconURL = faviconURL;
    }

    public VitalConfig getVitalConfig() {
        return this.vitalConfig;
    }

    public void setVitalConfig(VitalConfig vitalConfig) {
        this.vitalConfig = vitalConfig;
    }

    public DietConfig getDietConfig() {
        return this.dietConfig;
    }

    public void setDietConfig(DietConfig dietConfig) {
        this.dietConfig = dietConfig;
    }
}
