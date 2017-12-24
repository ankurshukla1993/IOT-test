package com.cooey.common.vo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class FeedBack {
    @SerializedName("feedBackInput")
    private List<FeedBackInputItem> feedBackInput;
    @SerializedName("title")
    private String title;

    public void setFeedBackInput(List<FeedBackInputItem> feedBackInput) {
        this.feedBackInput = feedBackInput;
    }

    public List<FeedBackInputItem> getFeedBackInput() {
        return this.feedBackInput;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
