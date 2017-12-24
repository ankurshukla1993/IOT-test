package com.biz.health.cooey_app.diet;

import com.cooey.common.vo.diet.DietMealPlan;
import java.io.Serializable;
import java.util.List;

public class Meal implements Serializable {
    private String calories;
    private String carbs;
    private String dietId;
    private String dietRecomendation;
    private String fiber;
    private List<DietMealPlan> mealPlans;
    private String name;
    private String tagId;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDietId() {
        return this.dietId;
    }

    public void setDietId(String dietId) {
        this.dietId = dietId;
    }

    public String getTagId() {
        return this.tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getDietRecomendation() {
        return this.dietRecomendation;
    }

    public void setDietRecomendation(String dietRecomendation) {
        this.dietRecomendation = dietRecomendation;
    }

    public String getCalories() {
        return this.calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCarbs() {
        return this.carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getFiber() {
        return this.fiber;
    }

    public void setFiber(String fiber) {
        this.fiber = fiber;
    }

    public List<DietMealPlan> getMealPlans() {
        return this.mealPlans;
    }

    public void setMealPlans(List<DietMealPlan> mealPlans) {
        this.mealPlans = mealPlans;
    }
}
