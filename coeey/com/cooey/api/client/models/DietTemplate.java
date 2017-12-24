package com.cooey.api.client.models;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DietTemplate {
    @SerializedName("allergy")
    private String allergy = null;
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("carbs")
    private String carbs = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("fat")
    private String fat = null;
    @SerializedName("fiber")
    private String fiber = null;
    @SerializedName("friday")
    private MealPlan friday = null;
    @SerializedName("from")
    private Long from = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("mealPlanList")
    private List<Meal> mealPlanList = null;
    @SerializedName("monday")
    private MealPlan monday = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("ownerId")
    private String ownerId = null;
    @SerializedName("ownerType")
    private OwnerTypeEnum ownerType = null;
    @SerializedName("protien")
    private String protien = null;
    @SerializedName("saturday")
    private MealPlan saturday = null;
    @SerializedName("sunday")
    private MealPlan sunday = null;
    @SerializedName("tagList")
    private Map<String, String> tagList = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("thursday")
    private MealPlan thursday = null;
    @SerializedName("to")
    private Long to = null;
    @SerializedName("totalCalorie")
    private String totalCalorie = null;
    @SerializedName("tuesday")
    private MealPlan tuesday = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;
    @SerializedName("wednesday")
    private MealPlan wednesday = null;

    @JsonAdapter(Adapter.class)
    public enum OwnerTypeEnum {
        READ("READ"),
        WRITE("WRITE"),
        READWRITE("READWRITE");
        
        private String value;

        private OwnerTypeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }

        public static OwnerTypeEnum fromValue(String text) {
            for (OwnerTypeEnum b : values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    public DietTemplate createdOn(Long createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @ApiModelProperty("")
    public Long getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public DietTemplate updatedOn(Long updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    @ApiModelProperty("")
    public Long getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public DietTemplate tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @ApiModelProperty("")
    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public DietTemplate applicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    @ApiModelProperty("")
    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public DietTemplate id(String id) {
        this.id = id;
        return this;
    }

    @ApiModelProperty("")
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DietTemplate name(String name) {
        this.name = name;
        return this;
    }

    @ApiModelProperty("")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DietTemplate totalCalorie(String totalCalorie) {
        this.totalCalorie = totalCalorie;
        return this;
    }

    @ApiModelProperty("")
    public String getTotalCalorie() {
        return this.totalCalorie;
    }

    public void setTotalCalorie(String totalCalorie) {
        this.totalCalorie = totalCalorie;
    }

    public DietTemplate allergy(String allergy) {
        this.allergy = allergy;
        return this;
    }

    @ApiModelProperty("")
    public String getAllergy() {
        return this.allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public DietTemplate fiber(String fiber) {
        this.fiber = fiber;
        return this;
    }

    @ApiModelProperty("")
    public String getFiber() {
        return this.fiber;
    }

    public void setFiber(String fiber) {
        this.fiber = fiber;
    }

    public DietTemplate fat(String fat) {
        this.fat = fat;
        return this;
    }

    @ApiModelProperty("")
    public String getFat() {
        return this.fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public DietTemplate protien(String protien) {
        this.protien = protien;
        return this;
    }

    @ApiModelProperty("")
    public String getProtien() {
        return this.protien;
    }

    public void setProtien(String protien) {
        this.protien = protien;
    }

    public DietTemplate tagList(Map<String, String> tagList) {
        this.tagList = tagList;
        return this;
    }

    public DietTemplate putTagListItem(String key, String tagListItem) {
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

    public DietTemplate mealPlanList(List<Meal> mealPlanList) {
        this.mealPlanList = mealPlanList;
        return this;
    }

    public DietTemplate addMealPlanListItem(Meal mealPlanListItem) {
        if (this.mealPlanList == null) {
            this.mealPlanList = new ArrayList();
        }
        this.mealPlanList.add(mealPlanListItem);
        return this;
    }

    @ApiModelProperty("")
    public List<Meal> getMealPlanList() {
        return this.mealPlanList;
    }

    public void setMealPlanList(List<Meal> mealPlanList) {
        this.mealPlanList = mealPlanList;
    }

    public DietTemplate from(Long from) {
        this.from = from;
        return this;
    }

    @ApiModelProperty("")
    public Long getFrom() {
        return this.from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public DietTemplate to(Long to) {
        this.to = to;
        return this;
    }

    @ApiModelProperty("")
    public Long getTo() {
        return this.to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public DietTemplate ownerType(OwnerTypeEnum ownerType) {
        this.ownerType = ownerType;
        return this;
    }

    @ApiModelProperty("")
    public OwnerTypeEnum getOwnerType() {
        return this.ownerType;
    }

    public void setOwnerType(OwnerTypeEnum ownerType) {
        this.ownerType = ownerType;
    }

    public DietTemplate ownerId(String ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    @ApiModelProperty("")
    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public DietTemplate sunday(MealPlan sunday) {
        this.sunday = sunday;
        return this;
    }

    @ApiModelProperty("")
    public MealPlan getSunday() {
        return this.sunday;
    }

    public void setSunday(MealPlan sunday) {
        this.sunday = sunday;
    }

    public DietTemplate monday(MealPlan monday) {
        this.monday = monday;
        return this;
    }

    @ApiModelProperty("")
    public MealPlan getMonday() {
        return this.monday;
    }

    public void setMonday(MealPlan monday) {
        this.monday = monday;
    }

    public DietTemplate tuesday(MealPlan tuesday) {
        this.tuesday = tuesday;
        return this;
    }

    @ApiModelProperty("")
    public MealPlan getTuesday() {
        return this.tuesday;
    }

    public void setTuesday(MealPlan tuesday) {
        this.tuesday = tuesday;
    }

    public DietTemplate wednesday(MealPlan wednesday) {
        this.wednesday = wednesday;
        return this;
    }

    @ApiModelProperty("")
    public MealPlan getWednesday() {
        return this.wednesday;
    }

    public void setWednesday(MealPlan wednesday) {
        this.wednesday = wednesday;
    }

    public DietTemplate thursday(MealPlan thursday) {
        this.thursday = thursday;
        return this;
    }

    @ApiModelProperty("")
    public MealPlan getThursday() {
        return this.thursday;
    }

    public void setThursday(MealPlan thursday) {
        this.thursday = thursday;
    }

    public DietTemplate friday(MealPlan friday) {
        this.friday = friday;
        return this;
    }

    @ApiModelProperty("")
    public MealPlan getFriday() {
        return this.friday;
    }

    public void setFriday(MealPlan friday) {
        this.friday = friday;
    }

    public DietTemplate saturday(MealPlan saturday) {
        this.saturday = saturday;
        return this;
    }

    @ApiModelProperty("")
    public MealPlan getSaturday() {
        return this.saturday;
    }

    public void setSaturday(MealPlan saturday) {
        this.saturday = saturday;
    }

    public DietTemplate carbs(String carbs) {
        this.carbs = carbs;
        return this;
    }

    @ApiModelProperty("")
    public String getCarbs() {
        return this.carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DietTemplate dietTemplate = (DietTemplate) o;
        if (Objects.equals(this.createdOn, dietTemplate.createdOn) && Objects.equals(this.updatedOn, dietTemplate.updatedOn) && Objects.equals(this.tenantId, dietTemplate.tenantId) && Objects.equals(this.applicationId, dietTemplate.applicationId) && Objects.equals(this.id, dietTemplate.id) && Objects.equals(this.name, dietTemplate.name) && Objects.equals(this.totalCalorie, dietTemplate.totalCalorie) && Objects.equals(this.allergy, dietTemplate.allergy) && Objects.equals(this.fiber, dietTemplate.fiber) && Objects.equals(this.fat, dietTemplate.fat) && Objects.equals(this.protien, dietTemplate.protien) && Objects.equals(this.tagList, dietTemplate.tagList) && Objects.equals(this.mealPlanList, dietTemplate.mealPlanList) && Objects.equals(this.from, dietTemplate.from) && Objects.equals(this.to, dietTemplate.to) && Objects.equals(this.ownerType, dietTemplate.ownerType) && Objects.equals(this.ownerId, dietTemplate.ownerId) && Objects.equals(this.sunday, dietTemplate.sunday) && Objects.equals(this.monday, dietTemplate.monday) && Objects.equals(this.tuesday, dietTemplate.tuesday) && Objects.equals(this.wednesday, dietTemplate.wednesday) && Objects.equals(this.thursday, dietTemplate.thursday) && Objects.equals(this.friday, dietTemplate.friday) && Objects.equals(this.saturday, dietTemplate.saturday) && Objects.equals(this.carbs, dietTemplate.carbs)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.name, this.totalCalorie, this.allergy, this.fiber, this.fat, this.protien, this.tagList, this.mealPlanList, this.from, this.to, this.ownerType, this.ownerId, this.sunday, this.monday, this.tuesday, this.wednesday, this.thursday, this.friday, this.saturday, this.carbs});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DietTemplate {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    totalCalorie: ").append(toIndentedString(this.totalCalorie)).append("\n");
        sb.append("    allergy: ").append(toIndentedString(this.allergy)).append("\n");
        sb.append("    fiber: ").append(toIndentedString(this.fiber)).append("\n");
        sb.append("    fat: ").append(toIndentedString(this.fat)).append("\n");
        sb.append("    protien: ").append(toIndentedString(this.protien)).append("\n");
        sb.append("    tagList: ").append(toIndentedString(this.tagList)).append("\n");
        sb.append("    mealPlanList: ").append(toIndentedString(this.mealPlanList)).append("\n");
        sb.append("    from: ").append(toIndentedString(this.from)).append("\n");
        sb.append("    to: ").append(toIndentedString(this.to)).append("\n");
        sb.append("    ownerType: ").append(toIndentedString(this.ownerType)).append("\n");
        sb.append("    ownerId: ").append(toIndentedString(this.ownerId)).append("\n");
        sb.append("    sunday: ").append(toIndentedString(this.sunday)).append("\n");
        sb.append("    monday: ").append(toIndentedString(this.monday)).append("\n");
        sb.append("    tuesday: ").append(toIndentedString(this.tuesday)).append("\n");
        sb.append("    wednesday: ").append(toIndentedString(this.wednesday)).append("\n");
        sb.append("    thursday: ").append(toIndentedString(this.thursday)).append("\n");
        sb.append("    friday: ").append(toIndentedString(this.friday)).append("\n");
        sb.append("    saturday: ").append(toIndentedString(this.saturday)).append("\n");
        sb.append("    carbs: ").append(toIndentedString(this.carbs)).append("\n");
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
