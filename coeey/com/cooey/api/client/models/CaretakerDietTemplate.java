package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CaretakerDietTemplate {
    @SerializedName("dietTemplate")
    private DietTemplate dietTemplate = null;
    @SerializedName("tagList")
    private Map<String, String> tagList = null;
    @SerializedName("userIds")
    private List<String> userIds = null;

    public CaretakerDietTemplate dietTemplate(DietTemplate dietTemplate) {
        this.dietTemplate = dietTemplate;
        return this;
    }

    @ApiModelProperty("")
    public DietTemplate getDietTemplate() {
        return this.dietTemplate;
    }

    public void setDietTemplate(DietTemplate dietTemplate) {
        this.dietTemplate = dietTemplate;
    }

    public CaretakerDietTemplate userIds(List<String> userIds) {
        this.userIds = userIds;
        return this;
    }

    public CaretakerDietTemplate addUserIdsItem(String userIdsItem) {
        if (this.userIds == null) {
            this.userIds = new ArrayList();
        }
        this.userIds.add(userIdsItem);
        return this;
    }

    @ApiModelProperty("")
    public List<String> getUserIds() {
        return this.userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public CaretakerDietTemplate tagList(Map<String, String> tagList) {
        this.tagList = tagList;
        return this;
    }

    public CaretakerDietTemplate putTagListItem(String key, String tagListItem) {
        if (this.tagList == null) {
            this.tagList = new HashMap();
        }
        this.tagList.put(key, tagListItem);
        return this;
    }

    @ApiModelProperty("")
    public Map<String, String> getTagList() {
        return this.tagList;
    }

    public void setTagList(Map<String, String> tagList) {
        this.tagList = tagList;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CaretakerDietTemplate caretakerDietTemplate = (CaretakerDietTemplate) o;
        if (Objects.equals(this.dietTemplate, caretakerDietTemplate.dietTemplate) && Objects.equals(this.userIds, caretakerDietTemplate.userIds) && Objects.equals(this.tagList, caretakerDietTemplate.tagList)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.dietTemplate, this.userIds, this.tagList});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CaretakerDietTemplate {\n");
        sb.append("    dietTemplate: ").append(toIndentedString(this.dietTemplate)).append("\n");
        sb.append("    userIds: ").append(toIndentedString(this.userIds)).append("\n");
        sb.append("    tagList: ").append(toIndentedString(this.tagList)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
