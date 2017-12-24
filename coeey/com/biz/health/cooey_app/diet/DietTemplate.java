package com.biz.health.cooey_app.diet;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DietTemplate implements Serializable {
    @SerializedName("dietTemplate")
    private DietTemplateList dietTemplateList;
    @SerializedName("tagList")
    private Map<String, String> tagList;
    @SerializedName("userIds")
    private List<String> userId;

    public DietTemplate(DietTemplateList dietTemplateList, List<String> userId) {
        this.dietTemplateList = dietTemplateList;
        this.userId = userId;
    }

    public DietTemplateList getDietTemplateList() {
        return this.dietTemplateList;
    }

    public void setDietTemplateList(DietTemplateList dietTemplateList) {
        this.dietTemplateList = dietTemplateList;
    }

    public List<String> getUserId() {
        return this.userId;
    }

    public void setUserId(List<String> userId) {
        this.userId = userId;
    }

    public Map<String, String> getTagList() {
        return this.tagList;
    }

    public void setTagList(Map<String, String> tagList) {
        this.tagList = tagList;
    }
}
