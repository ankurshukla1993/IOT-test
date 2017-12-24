package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Meal {
    @SerializedName("calories")
    private String calories = null;
    @SerializedName("carbs")
    private String carbs = null;
    @SerializedName("dietId")
    private String dietId = null;
    @SerializedName("dietRecomendation")
    private String dietRecomendation = null;
    @SerializedName("fiber")
    private String fiber = null;
    @SerializedName("mealPlans")
    private List<MealPlan> mealPlans = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("tagId")
    private String tagId = null;

    public Meal name(String name) {
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

    public Meal dietId(String dietId) {
        this.dietId = dietId;
        return this;
    }

    @ApiModelProperty("")
    public String getDietId() {
        return this.dietId;
    }

    public void setDietId(String dietId) {
        this.dietId = dietId;
    }

    public Meal tagId(String tagId) {
        this.tagId = tagId;
        return this;
    }

    @ApiModelProperty("")
    public String getTagId() {
        return this.tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public Meal calories(String calories) {
        this.calories = calories;
        return this;
    }

    @ApiModelProperty("")
    public String getCalories() {
        return this.calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public Meal mealPlans(List<MealPlan> mealPlans) {
        this.mealPlans = mealPlans;
        return this;
    }

    public Meal addMealPlansItem(MealPlan mealPlansItem) {
        if (this.mealPlans == null) {
            this.mealPlans = new ArrayList();
        }
        this.mealPlans.add(mealPlansItem);
        return this;
    }

    @ApiModelProperty("")
    public List<MealPlan> getMealPlans() {
        return this.mealPlans;
    }

    public void setMealPlans(List<MealPlan> mealPlans) {
        this.mealPlans = mealPlans;
    }

    public Meal carbs(String carbs) {
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

    public Meal fiber(String fiber) {
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

    public Meal dietRecomendation(String dietRecomendation) {
        this.dietRecomendation = dietRecomendation;
        return this;
    }

    @ApiModelProperty("")
    public String getDietRecomendation() {
        return this.dietRecomendation;
    }

    public void setDietRecomendation(String dietRecomendation) {
        this.dietRecomendation = dietRecomendation;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Meal meal = (Meal) o;
        if (Objects.equals(this.name, meal.name) && Objects.equals(this.dietId, meal.dietId) && Objects.equals(this.tagId, meal.tagId) && Objects.equals(this.calories, meal.calories) && Objects.equals(this.mealPlans, meal.mealPlans) && Objects.equals(this.carbs, meal.carbs) && Objects.equals(this.fiber, meal.fiber) && Objects.equals(this.dietRecomendation, meal.dietRecomendation)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.dietId, this.tagId, this.calories, this.mealPlans, this.carbs, this.fiber, this.dietRecomendation});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Meal {\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    dietId: ").append(toIndentedString(this.dietId)).append("\n");
        sb.append("    tagId: ").append(toIndentedString(this.tagId)).append("\n");
        sb.append("    calories: ").append(toIndentedString(this.calories)).append("\n");
        sb.append("    mealPlans: ").append(toIndentedString(this.mealPlans)).append("\n");
        sb.append("    carbs: ").append(toIndentedString(this.carbs)).append("\n");
        sb.append("    fiber: ").append(toIndentedString(this.fiber)).append("\n");
        sb.append("    dietRecomendation: ").append(toIndentedString(this.dietRecomendation)).append("\n");
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
