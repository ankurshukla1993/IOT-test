package com.cooey.common.vo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ActivityResponse {
    @SerializedName("event")
    private Activity activity;
    @SerializedName("userIds")
    private List<String> userId;

    public ActivityResponse(Activity activity, List<String> userId) {
        this.activity = activity;
        this.userId = userId;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public List<String> getUserId() {
        return this.userId;
    }

    public void setUserId(List<String> userId) {
        this.userId = userId;
    }
}
