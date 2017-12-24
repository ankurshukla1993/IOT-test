package com.cooey.common.vo;

import com.cooey.common.vo.diet.DietMealPlan;
import com.cooey.common.vo.diet.weekdays.Friday;
import com.cooey.common.vo.diet.weekdays.Monday;
import com.cooey.common.vo.diet.weekdays.Saturday;
import com.cooey.common.vo.diet.weekdays.Thursday;
import com.cooey.common.vo.diet.weekdays.Tuesday;
import com.cooey.common.vo.diet.weekdays.Wednesday;
import com.google.gson.annotations.SerializedName;
import io.realm.DietTemplateRealmProxyInterface;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;
import java.util.List;

public class DietTemplate extends RealmObject implements Serializable, DietTemplateRealmProxyInterface {
    @SerializedName("name")
    private String dietTitle;
    @SerializedName("friday")
    private Friday friday;
    @SerializedName("from")
    private long fromDate;
    @PrimaryKey
    private String id;
    @SerializedName("mealPlanList")
    private RealmList<MealPlan> mealPlan;
    @SerializedName("monday")
    private Monday monday;
    @SerializedName("ownerId")
    private String ownerId;
    @SerializedName("ownerType")
    private String ownerType;
    @SerializedName("saturday")
    private Saturday saturday;
    @SerializedName("thursday")
    private Thursday thursday;
    @SerializedName("to")
    private long toDate;
    @SerializedName("tuesday")
    private Tuesday tuesday;
    @SerializedName("wednesday")
    private Wednesday wednesday;

    public String realmGet$dietTitle() {
        return this.dietTitle;
    }

    public Friday realmGet$friday() {
        return this.friday;
    }

    public long realmGet$fromDate() {
        return this.fromDate;
    }

    public String realmGet$id() {
        return this.id;
    }

    public RealmList realmGet$mealPlan() {
        return this.mealPlan;
    }

    public Monday realmGet$monday() {
        return this.monday;
    }

    public String realmGet$ownerId() {
        return this.ownerId;
    }

    public String realmGet$ownerType() {
        return this.ownerType;
    }

    public Saturday realmGet$saturday() {
        return this.saturday;
    }

    public Thursday realmGet$thursday() {
        return this.thursday;
    }

    public long realmGet$toDate() {
        return this.toDate;
    }

    public Tuesday realmGet$tuesday() {
        return this.tuesday;
    }

    public Wednesday realmGet$wednesday() {
        return this.wednesday;
    }

    public void realmSet$dietTitle(String str) {
        this.dietTitle = str;
    }

    public void realmSet$friday(Friday friday) {
        this.friday = friday;
    }

    public void realmSet$fromDate(long j) {
        this.fromDate = j;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$mealPlan(RealmList realmList) {
        this.mealPlan = realmList;
    }

    public void realmSet$monday(Monday monday) {
        this.monday = monday;
    }

    public void realmSet$ownerId(String str) {
        this.ownerId = str;
    }

    public void realmSet$ownerType(String str) {
        this.ownerType = str;
    }

    public void realmSet$saturday(Saturday saturday) {
        this.saturday = saturday;
    }

    public void realmSet$thursday(Thursday thursday) {
        this.thursday = thursday;
    }

    public void realmSet$toDate(long j) {
        this.toDate = j;
    }

    public void realmSet$tuesday(Tuesday tuesday) {
        this.tuesday = tuesday;
    }

    public void realmSet$wednesday(Wednesday wednesday) {
        this.wednesday = wednesday;
    }

    public DietTemplate(String id, String ownerId, String ownerType, String dietTitle, Monday monday, Tuesday tuesday, Wednesday wednesday, Thursday thursday, Friday friday, Saturday saturday, List<DietMealPlan> list, long fromDate, long toDate) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$id(id);
        realmSet$ownerId(ownerId);
        realmSet$ownerType(ownerType);
        realmSet$dietTitle(dietTitle);
        realmSet$monday(monday);
        realmSet$tuesday(tuesday);
        realmSet$wednesday(wednesday);
        realmSet$thursday(thursday);
        realmSet$friday(friday);
        realmSet$saturday(saturday);
        realmSet$fromDate(fromDate);
        realmSet$toDate(toDate);
    }

    public DietTemplate() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String id) {
        realmSet$id(id);
    }

    public String getOwnerId() {
        return realmGet$ownerId();
    }

    public void setOwnerId(String ownerId) {
        realmSet$ownerId(ownerId);
    }

    public String getOwnerType() {
        return realmGet$ownerType();
    }

    public void setOwnerType(String ownerType) {
        realmSet$ownerType(ownerType);
    }

    public String getDietTitle() {
        return realmGet$dietTitle();
    }

    public void setDietTitle(String dietTitle) {
        realmSet$dietTitle(dietTitle);
    }

    public Monday getMonday() {
        return realmGet$monday();
    }

    public void setMonday(Monday monday) {
        realmSet$monday(monday);
    }

    public Tuesday getTuesday() {
        return realmGet$tuesday();
    }

    public void setTuesday(Tuesday tuesday) {
        realmSet$tuesday(tuesday);
    }

    public Wednesday getWednesday() {
        return realmGet$wednesday();
    }

    public void setWednesday(Wednesday wednesday) {
        realmSet$wednesday(wednesday);
    }

    public Thursday getThursday() {
        return realmGet$thursday();
    }

    public void setThursday(Thursday thursday) {
        realmSet$thursday(thursday);
    }

    public Friday getFriday() {
        return realmGet$friday();
    }

    public void setFriday(Friday friday) {
        realmSet$friday(friday);
    }

    public Saturday getSaturday() {
        return realmGet$saturday();
    }

    public void setSaturday(Saturday saturday) {
        realmSet$saturday(saturday);
    }

    public RealmList<MealPlan> getMealPlan() {
        return realmGet$mealPlan();
    }

    public void setMealPlan(RealmList<MealPlan> mealPlan) {
        realmSet$mealPlan(mealPlan);
    }

    public long getFromDate() {
        return realmGet$fromDate();
    }

    public void setFromDate(long fromDate) {
        realmSet$fromDate(fromDate);
    }

    public long getToDate() {
        return realmGet$toDate();
    }

    public void setToDate(long toDate) {
        realmSet$toDate(toDate);
    }
}
