package com.cooey.common.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DietConfig {
    @SerializedName("createdOn")
    @Expose
    private int createdOn;
    @SerializedName("dietFeatureEnabled")
    @Expose
    private boolean dietFeatureEnabled;
    @SerializedName("tenantId")
    @Expose
    private Object tenantId;
    @SerializedName("updatedOn")
    @Expose
    private int updatedOn;

    public void setTenantId(Object tenantId) {
        this.tenantId = tenantId;
    }

    public Object getTenantId() {
        return this.tenantId;
    }

    public void setUpdatedOn(int updatedOn) {
        this.updatedOn = updatedOn;
    }

    public int getUpdatedOn() {
        return this.updatedOn;
    }

    public void setCreatedOn(int createdOn) {
        this.createdOn = createdOn;
    }

    public int getCreatedOn() {
        return this.createdOn;
    }

    public void setDietFeatureEnabled(boolean dietFeatureEnabled) {
        this.dietFeatureEnabled = dietFeatureEnabled;
    }

    public boolean isDietFeatureEnabled() {
        return this.dietFeatureEnabled;
    }
}
