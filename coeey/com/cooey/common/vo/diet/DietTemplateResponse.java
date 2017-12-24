package com.cooey.common.vo.diet;

import com.cooey.common.vo.DietTemplate;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DietTemplateResponse {
    @SerializedName("dietTemplate")
    private DietTemplate dietTemplate;
    @SerializedName("userIds")
    private List<String> userId;

    public DietTemplateResponse(DietTemplate dietTemplate, List<String> userId) {
        this.dietTemplate = dietTemplate;
        this.userId = userId;
    }

    public DietTemplate getDietTemplate() {
        return this.dietTemplate;
    }

    public void setDietTemplate(DietTemplate dietTemplate) {
        this.dietTemplate = dietTemplate;
    }

    public List<String> getUserId() {
        return this.userId;
    }

    public void setUserId(List<String> userId) {
        this.userId = userId;
    }
}
