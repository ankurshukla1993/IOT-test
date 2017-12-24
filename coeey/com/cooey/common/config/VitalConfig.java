package com.cooey.common.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VitalConfig {
    @SerializedName("bloodGlucoseCheckEnabled")
    @Expose
    private boolean bloodGlucoseCheckEnabled;
    @SerializedName("bloodPressureCheckEnabled")
    @Expose
    private boolean bloodPressureCheckEnabled;
    @SerializedName("createdOn")
    @Expose
    private int createdOn;
    @SerializedName("tenantId")
    @Expose
    private Object tenantId;
    @SerializedName("updatedOn")
    @Expose
    private int updatedOn;
    @SerializedName("weightCheckEnabled")
    @Expose
    private boolean weightCheckEnabled;

    public void setWeightCheckEnabled(boolean weightCheckEnabled) {
        this.weightCheckEnabled = weightCheckEnabled;
    }

    public boolean isWeightCheckEnabled() {
        return this.weightCheckEnabled;
    }

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

    public void setBloodPressureCheckEnabled(boolean bloodPressureCheckEnabled) {
        this.bloodPressureCheckEnabled = bloodPressureCheckEnabled;
    }

    public boolean isBloodPressureCheckEnabled() {
        return this.bloodPressureCheckEnabled;
    }

    public void setBloodGlucoseCheckEnabled(boolean bloodGlucoseCheckEnabled) {
        this.bloodGlucoseCheckEnabled = bloodGlucoseCheckEnabled;
    }

    public boolean isBloodGlucoseCheckEnabled() {
        return this.bloodGlucoseCheckEnabled;
    }

    public void setCreatedOn(int createdOn) {
        this.createdOn = createdOn;
    }

    public int getCreatedOn() {
        return this.createdOn;
    }
}
