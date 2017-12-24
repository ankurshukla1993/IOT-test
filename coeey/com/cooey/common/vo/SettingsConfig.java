package com.cooey.common.vo;

import com.cooey.common.config.ConfigColor;
import com.cooey.common.config.DietConfig;
import com.cooey.common.config.TemplateConfig;
import com.cooey.common.config.VitalConfig;

public class SettingsConfig {
    private ActivityConfig activityConfig;
    private ConfigColor configColor;
    private long createdOn;
    private DietConfig dietConfig;
    private String faviconURL;
    private String id;
    private Boolean iscareplanEnabled;
    private String logoURL;
    private TemplateConfig templateConfig;
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

    public TemplateConfig getTemplateConfig() {
        return this.templateConfig;
    }

    public void setTemplateConfig(TemplateConfig templateConfig) {
        this.templateConfig = templateConfig;
    }

    public Boolean getIscareplanEnabled() {
        return this.iscareplanEnabled;
    }

    public void setIscareplanEnabled(Boolean iscareplanEnabled) {
        this.iscareplanEnabled = iscareplanEnabled;
    }

    public ActivityConfig getActivityConfig() {
        return this.activityConfig;
    }

    public void setActivityConfig(ActivityConfig activityConfig) {
        this.activityConfig = activityConfig;
    }
}
