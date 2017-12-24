package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class ColorConfiguration {
    @SerializedName("backgroundColor")
    private String backgroundColor = null;
    @SerializedName("primaryColor")
    private String primaryColor = null;
    @SerializedName("primaryDarkColor")
    private String primaryDarkColor = null;
    @SerializedName("secondaryColor")
    private String secondaryColor = null;
    @SerializedName("secondaryDarkColor")
    private String secondaryDarkColor = null;
    @SerializedName("textColor")
    private String textColor = null;

    public ColorConfiguration primaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
        return this;
    }

    @ApiModelProperty("")
    public String getPrimaryColor() {
        return this.primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public ColorConfiguration primaryDarkColor(String primaryDarkColor) {
        this.primaryDarkColor = primaryDarkColor;
        return this;
    }

    @ApiModelProperty("")
    public String getPrimaryDarkColor() {
        return this.primaryDarkColor;
    }

    public void setPrimaryDarkColor(String primaryDarkColor) {
        this.primaryDarkColor = primaryDarkColor;
    }

    public ColorConfiguration secondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
        return this;
    }

    @ApiModelProperty("")
    public String getSecondaryColor() {
        return this.secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public ColorConfiguration secondaryDarkColor(String secondaryDarkColor) {
        this.secondaryDarkColor = secondaryDarkColor;
        return this;
    }

    @ApiModelProperty("")
    public String getSecondaryDarkColor() {
        return this.secondaryDarkColor;
    }

    public void setSecondaryDarkColor(String secondaryDarkColor) {
        this.secondaryDarkColor = secondaryDarkColor;
    }

    public ColorConfiguration textColor(String textColor) {
        this.textColor = textColor;
        return this;
    }

    @ApiModelProperty("")
    public String getTextColor() {
        return this.textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public ColorConfiguration backgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    @ApiModelProperty("")
    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ColorConfiguration colorConfiguration = (ColorConfiguration) o;
        if (Objects.equals(this.primaryColor, colorConfiguration.primaryColor) && Objects.equals(this.primaryDarkColor, colorConfiguration.primaryDarkColor) && Objects.equals(this.secondaryColor, colorConfiguration.secondaryColor) && Objects.equals(this.secondaryDarkColor, colorConfiguration.secondaryDarkColor) && Objects.equals(this.textColor, colorConfiguration.textColor) && Objects.equals(this.backgroundColor, colorConfiguration.backgroundColor)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.primaryColor, this.primaryDarkColor, this.secondaryColor, this.secondaryDarkColor, this.textColor, this.backgroundColor});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ColorConfiguration {\n");
        sb.append("    primaryColor: ").append(toIndentedString(this.primaryColor)).append("\n");
        sb.append("    primaryDarkColor: ").append(toIndentedString(this.primaryDarkColor)).append("\n");
        sb.append("    secondaryColor: ").append(toIndentedString(this.secondaryColor)).append("\n");
        sb.append("    secondaryDarkColor: ").append(toIndentedString(this.secondaryDarkColor)).append("\n");
        sb.append("    textColor: ").append(toIndentedString(this.textColor)).append("\n");
        sb.append("    backgroundColor: ").append(toIndentedString(this.backgroundColor)).append("\n");
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
