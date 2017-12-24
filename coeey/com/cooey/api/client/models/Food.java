package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Food {
    @SerializedName("calories")
    private String calories = null;
    @SerializedName("carbs")
    private String carbs = null;
    @SerializedName("fat")
    private String fat = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("proteins")
    private String proteins = null;
    @SerializedName("servingSize")
    private String servingSize = null;
    @SerializedName("type")
    private String type = null;

    public Food id(String id) {
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

    public Food calories(String calories) {
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

    public Food carbs(String carbs) {
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

    public Food fat(String fat) {
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

    public Food name(String name) {
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

    public Food proteins(String proteins) {
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

    public Food servingSize(String servingSize) {
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

    public Food type(String type) {
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
        Food food = (Food) o;
        if (Objects.equals(this.id, food.id) && Objects.equals(this.calories, food.calories) && Objects.equals(this.carbs, food.carbs) && Objects.equals(this.fat, food.fat) && Objects.equals(this.name, food.name) && Objects.equals(this.proteins, food.proteins) && Objects.equals(this.servingSize, food.servingSize) && Objects.equals(this.type, food.type)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.calories, this.carbs, this.fat, this.name, this.proteins, this.servingSize, this.type});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Food {\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    calories: ").append(toIndentedString(this.calories)).append("\n");
        sb.append("    carbs: ").append(toIndentedString(this.carbs)).append("\n");
        sb.append("    fat: ").append(toIndentedString(this.fat)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    proteins: ").append(toIndentedString(this.proteins)).append("\n");
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
