package com.cooey.common.config;

import com.google.gson.annotations.SerializedName;

public class TemplateConfig {
    @SerializedName("contentFeatureEnabled")
    private boolean contentFeatureEnabled;
    @SerializedName("dietFeatureEnabled")
    private boolean dietFeatureEnabled;

    public void setContentFeatureEnabled(boolean contentFeatureEnabled) {
        this.contentFeatureEnabled = contentFeatureEnabled;
    }

    public boolean isContentFeatureEnabled() {
        return this.contentFeatureEnabled;
    }

    public void setDietFeatureEnabled(boolean dietFeatureEnabled) {
        this.dietFeatureEnabled = dietFeatureEnabled;
    }

    public boolean isDietFeatureEnabled() {
        return this.dietFeatureEnabled;
    }
}
