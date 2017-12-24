package com.cooey.common.vo;

import io.realm.MealPlanRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

public class MealPlan extends RealmObject implements MealPlanRealmProxyInterface {
    private String calories;
    private String carbs;
    private String dietId;
    private String dietRecomendation;
    private String fiber;
    private String name;
    private String tagId;

    public String realmGet$calories() {
        return this.calories;
    }

    public String realmGet$carbs() {
        return this.carbs;
    }

    public String realmGet$dietId() {
        return this.dietId;
    }

    public String realmGet$dietRecomendation() {
        return this.dietRecomendation;
    }

    public String realmGet$fiber() {
        return this.fiber;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$tagId() {
        return this.tagId;
    }

    public void realmSet$calories(String str) {
        this.calories = str;
    }

    public void realmSet$carbs(String str) {
        this.carbs = str;
    }

    public void realmSet$dietId(String str) {
        this.dietId = str;
    }

    public void realmSet$dietRecomendation(String str) {
        this.dietRecomendation = str;
    }

    public void realmSet$fiber(String str) {
        this.fiber = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$tagId(String str) {
        this.tagId = str;
    }

    public MealPlan() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String name) {
        realmSet$name(name);
    }

    public String getDietId() {
        return realmGet$dietId();
    }

    public void setDietId(String dietId) {
        realmSet$dietId(dietId);
    }

    public String getTagId() {
        return realmGet$tagId();
    }

    public void setTagId(String tagId) {
        realmSet$tagId(tagId);
    }

    public String getDietRecomendation() {
        return realmGet$dietRecomendation();
    }

    public void setDietRecomendation(String dietRecomendation) {
        realmSet$dietRecomendation(dietRecomendation);
    }

    public String getCalories() {
        return realmGet$calories();
    }

    public void setCalories(String calories) {
        realmSet$calories(calories);
    }

    public String getCarbs() {
        return realmGet$carbs();
    }

    public void setCarbs(String carbs) {
        realmSet$carbs(carbs);
    }

    public String getFiber() {
        return realmGet$fiber();
    }

    public void setFiber(String fiber) {
        realmSet$fiber(fiber);
    }
}
