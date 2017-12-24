package com.cooey.common.vo;

import com.google.gson.annotations.SerializedName;

public class ActivityConfig {
    @SerializedName("activityEnabled")
    public boolean activityEnabled;

    public boolean isActivityEnabled() {
        return this.activityEnabled;
    }

    public void setActivityEnabled(boolean activityEnabled) {
        this.activityEnabled = activityEnabled;
    }
}
