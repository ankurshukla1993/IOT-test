package com.cooey.common.config;

public class ConfigColor {
    private String backgroundColor;
    private String primaryColor;
    private String primaryDarkColor;
    private String secondaryColor;
    private String secondaryDarkColor;
    private String textColor;

    public String getPrimaryColor() {
        return this.primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getPrimaryDarkColor() {
        return this.primaryDarkColor;
    }

    public void setPrimaryDarkColor(String primaryDarkColor) {
        this.primaryDarkColor = primaryDarkColor;
    }

    public String getSecondaryColor() {
        return this.secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getSecondaryDarkColor() {
        return this.secondaryDarkColor;
    }

    public void setSecondaryDarkColor(String secondaryDarkColor) {
        this.secondaryDarkColor = secondaryDarkColor;
    }

    public String getTextColor() {
        return this.textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
