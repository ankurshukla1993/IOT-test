package com.cooey.common.vo.diet;

import com.cooey.common.vo.diet.time.BreakFast;
import com.cooey.common.vo.diet.time.Dinner;
import com.cooey.common.vo.diet.time.Lunch;
import io.realm.DietMealPlanRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class DietMealPlan extends RealmObject implements Serializable, DietMealPlanRealmProxyInterface {
    private BreakFast breakFast;
    private String calories;
    private String carbs;
    private Dinner dinner;
    private String fat;
    private String id;
    private Lunch lunch;
    private String name;
    private String proteins;
    private String quantity;
    private String servingSize;
    private String type;

    public BreakFast realmGet$breakFast() {
        return this.breakFast;
    }

    public String realmGet$calories() {
        return this.calories;
    }

    public String realmGet$carbs() {
        return this.carbs;
    }

    public Dinner realmGet$dinner() {
        return this.dinner;
    }

    public String realmGet$fat() {
        return this.fat;
    }

    public String realmGet$id() {
        return this.id;
    }

    public Lunch realmGet$lunch() {
        return this.lunch;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$proteins() {
        return this.proteins;
    }

    public String realmGet$quantity() {
        return this.quantity;
    }

    public String realmGet$servingSize() {
        return this.servingSize;
    }

    public String realmGet$type() {
        return this.type;
    }

    public void realmSet$breakFast(BreakFast breakFast) {
        this.breakFast = breakFast;
    }

    public void realmSet$calories(String str) {
        this.calories = str;
    }

    public void realmSet$carbs(String str) {
        this.carbs = str;
    }

    public void realmSet$dinner(Dinner dinner) {
        this.dinner = dinner;
    }

    public void realmSet$fat(String str) {
        this.fat = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$lunch(Lunch lunch) {
        this.lunch = lunch;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$proteins(String str) {
        this.proteins = str;
    }

    public void realmSet$quantity(String str) {
        this.quantity = str;
    }

    public void realmSet$servingSize(String str) {
        this.servingSize = str;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public DietMealPlan() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public BreakFast getBreakFast() {
        return realmGet$breakFast();
    }

    public void setBreakFast(BreakFast breakFast) {
        realmSet$breakFast(breakFast);
    }

    public Lunch getLunch() {
        return realmGet$lunch();
    }

    public void setLunch(Lunch lunch) {
        realmSet$lunch(lunch);
    }

    public Dinner getDinner() {
        return realmGet$dinner();
    }

    public void setDinner(Dinner dinner) {
        realmSet$dinner(dinner);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String id) {
        realmSet$id(id);
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

    public String getFat() {
        return realmGet$fat();
    }

    public void setFat(String fat) {
        realmSet$fat(fat);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String name) {
        realmSet$name(name);
    }

    public String getProteins() {
        return realmGet$proteins();
    }

    public void setProteins(String proteins) {
        realmSet$proteins(proteins);
    }

    public String getQuantity() {
        return realmGet$quantity();
    }

    public void setQuantity(String quantity) {
        realmSet$quantity(quantity);
    }

    public String getServingSize() {
        return realmGet$servingSize();
    }

    public void setServingSize(String servingSize) {
        realmSet$servingSize(servingSize);
    }

    public String getType() {
        return realmGet$type();
    }

    public void setType(String type) {
        realmSet$type(type);
    }
}
