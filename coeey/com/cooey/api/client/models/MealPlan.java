package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class MealPlan {
    @SerializedName("breakfast")
    private Meal breakfast = null;
    @SerializedName("calories")
    private String calories = null;
    @SerializedName("carbs")
    private String carbs = null;
    @SerializedName("dinner")
    private Meal dinner = null;
    @SerializedName("fat")
    private String fat = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("lunch")
    private Meal lunch = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("proteins")
    private String proteins = null;
    @SerializedName("quantity")
    private Integer quantity = null;
    @SerializedName("servingSize")
    private String servingSize = null;
    @SerializedName("type")
    private String type = null;

    public MealPlan breakfast(Meal breakfast) {
        this.breakfast = breakfast;
        return this;
    }

    @ApiModelProperty("")
    public Meal getBreakfast() {
        return this.breakfast;
    }

    public void setBreakfast(Meal breakfast) {
        this.breakfast = breakfast;
    }

    public MealPlan lunch(Meal lunch) {
        this.lunch = lunch;
        return this;
    }

    @ApiModelProperty("")
    public Meal getLunch() {
        return this.lunch;
    }

    public void setLunch(Meal lunch) {
        this.lunch = lunch;
    }

    public MealPlan dinner(Meal dinner) {
        this.dinner = dinner;
        return this;
    }

    @ApiModelProperty("")
    public Meal getDinner() {
        return this.dinner;
    }

    public void setDinner(Meal dinner) {
        this.dinner = dinner;
    }

    public MealPlan id(String id) {
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

    public MealPlan calories(String calories) {
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

    public MealPlan carbs(String carbs) {
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

    public MealPlan fat(String fat) {
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

    public MealPlan name(String name) {
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

    public MealPlan proteins(String proteins) {
        this.proteins = proteins;
        return this;
    }

    @ApiModelProperty("")
    public String getProteins() {
        return this.proteins;
    }

    public void setProteins(String proteins) {
        this.proteins = proteins;
    }

    public MealPlan quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @ApiModelProperty("")
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MealPlan servingSize(String servingSize) {
        this.servingSize = servingSize;
        return this;
    }

    @ApiModelProperty("")
    public String getServingSize() {
        return this.servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public MealPlan type(String type) {
        this.type = type;
        return this;
    }

    @ApiModelProperty("")
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MealPlan mealPlan = (MealPlan) o;
        if (Objects.equals(this.breakfast, mealPlan.breakfast) && Objects.equals(this.lunch, mealPlan.lunch) && Objects.equals(this.dinner, mealPlan.dinner) && Objects.equals(this.id, mealPlan.id) && Objects.equals(this.calories, mealPlan.calories) && Objects.equals(this.carbs, mealPlan.carbs) && Objects.equals(this.fat, mealPlan.fat) && Objects.equals(this.name, mealPlan.name) && Objects.equals(this.proteins, mealPlan.proteins) && Objects.equals(this.quantity, mealPlan.quantity) && Objects.equals(this.servingSize, mealPlan.servingSize) && Objects.equals(this.type, mealPlan.type)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.breakfast, this.lunch, this.dinner, this.id, this.calories, this.carbs, this.fat, this.name, this.proteins, this.quantity, this.servingSize, this.type});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MealPlan {\n");
        sb.append("    breakfast: ").append(toIndentedString(this.breakfast)).append("\n");
        sb.append("    lunch: ").append(toIndentedString(this.lunch)).append("\n");
        sb.append("    dinner: ").append(toIndentedString(this.dinner)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    calories: ").append(toIndentedString(this.calories)).append("\n");
        sb.append("    carbs: ").append(toIndentedString(this.carbs)).append("\n");
        sb.append("    fat: ").append(toIndentedString(this.fat)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    proteins: ").append(toIndentedString(this.proteins)).append("\n");
        sb.append("    quantity: ").append(toIndentedString(this.quantity)).append("\n");
        sb.append("    servingSize: ").append(toIndentedString(this.servingSize)).append("\n");
        sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
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
