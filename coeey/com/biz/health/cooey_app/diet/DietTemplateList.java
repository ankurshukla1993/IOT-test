package com.biz.health.cooey_app.diet;

import com.cooey.common.vo.diet.weekdays.Friday;
import com.cooey.common.vo.diet.weekdays.Monday;
import com.cooey.common.vo.diet.weekdays.Saturday;
import com.cooey.common.vo.diet.weekdays.Sunday;
import com.cooey.common.vo.diet.weekdays.Thursday;
import com.cooey.common.vo.diet.weekdays.Tuesday;
import com.cooey.common.vo.diet.weekdays.Wednesday;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class DietTemplateList implements Serializable {
    private String allergy;
    private String carbs;
    @SerializedName("name")
    private String dietTitle;
    private String fiber;
    @SerializedName("friday")
    private Friday friday;
    @SerializedName("from")
    private long fromDate;
    private String id;
    @SerializedName("mealPlanList")
    private List<Meal> meal;
    @SerializedName("monday")
    private Monday monday;
    @SerializedName("ownerId")
    private String ownerId;
    @SerializedName("ownerType")
    private String ownerType;
    @SerializedName("saturday")
    private Saturday saturday;
    @SerializedName("sunday")
    private Sunday sundayTemplate;
    @SerializedName("thursday")
    private Thursday thursday;
    @SerializedName("to")
    private long toDate;
    private String totalCalorie;
    @SerializedName("tuesday")
    private Tuesday tuesday;
    @SerializedName("wednesday")
    private Wednesday wednesday;

    public DietTemplateList(String id, String ownerId, String ownerType, String dietTitle, Sunday sundayTemplate, Monday monday, Tuesday tuesday, Wednesday wednesday, Thursday thursday, Friday friday, Saturday saturday, List<Meal> meal, long fromDate, long toDate) {
        this.id = id;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
        this.dietTitle = dietTitle;
        this.sundayTemplate = sundayTemplate;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.meal = meal;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerType() {
        return this.ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getDietTitle() {
        return this.dietTitle;
    }

    public void setDietTitle(String dietTitle) {
        this.dietTitle = dietTitle;
    }

    public Sunday getSundayTemplate() {
        return this.sundayTemplate;
    }

    public void setSundayTemplate(Sunday sundayTemplate) {
        this.sundayTemplate = sundayTemplate;
    }

    public Monday getMonday() {
        return this.monday;
    }

    public void setMonday(Monday monday) {
        this.monday = monday;
    }

    public Tuesday getTuesday() {
        return this.tuesday;
    }

    public void setTuesday(Tuesday tuesday) {
        this.tuesday = tuesday;
    }

    public Wednesday getWednesday() {
        return this.wednesday;
    }

    public void setWednesday(Wednesday wednesday) {
        this.wednesday = wednesday;
    }

    public Thursday getThursday() {
        return this.thursday;
    }

    public void setThursday(Thursday thursday) {
        this.thursday = thursday;
    }

    public Friday getFriday() {
        return this.friday;
    }

    public void setFriday(Friday friday) {
        this.friday = friday;
    }

    public Saturday getSaturday() {
        return this.saturday;
    }

    public void setSaturday(Saturday saturday) {
        this.saturday = saturday;
    }

    public List<Meal> getMeal() {
        return this.meal;
    }

    public void setMeal(List<Meal> meal) {
        this.meal = meal;
    }

    public String getTotalCalorie() {
        return this.totalCalorie;
    }

    public void setTotalCalorie(String totalCalorie) {
        this.totalCalorie = totalCalorie;
    }

    public String getAllergy() {
        return this.allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getFiber() {
        return this.fiber;
    }

    public void setFiber(String fiber) {
        this.fiber = fiber;
    }

    public String getCarbs() {
        return this.carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public long getFromDate() {
        return this.fromDate;
    }

    public void setFromDate(long fromDate) {
        this.fromDate = fromDate;
    }

    public long getToDate() {
        return this.toDate;
    }

    public void setToDate(long toDate) {
        this.toDate = toDate;
    }
}
