package com.cooey.common.config;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AndriodConfigResponse {
    @SerializedName("colorConfiguration")
    private Object colorConfiguration;
    @SerializedName("description")
    private Object description;
    @SerializedName("firebaseConfig")
    private Object firebaseConfig;
    @SerializedName("iconImageUrl")
    private Object iconImageUrl;
    @SerializedName("name")
    private Object name;
    @SerializedName("packageName")
    private Object packageName;
    @SerializedName("splashImageUrl")
    private Object splashImageUrl;
    @SerializedName("storeUrl")
    private Object storeUrl;
    @SerializedName("version")
    private int version;
    @SerializedName("versionName")
    private Object versionName;
    @SerializedName("vitalTemplatesConfigList")
    private List<VitalTemplatesConfigListItem> vitalTemplatesConfigList;
    @SerializedName("vitalsConfig")
    private Object vitalsConfig;

    public void setVitalTemplatesConfigList(List<VitalTemplatesConfigListItem> vitalTemplatesConfigList) {
        this.vitalTemplatesConfigList = vitalTemplatesConfigList;
    }

    public List<VitalTemplatesConfigListItem> getVitalTemplatesConfigList() {
        return this.vitalTemplatesConfigList;
    }

    public void setIconImageUrl(Object iconImageUrl) {
        this.iconImageUrl = iconImageUrl;
    }

    public Object getIconImageUrl() {
        return this.iconImageUrl;
    }

    public void setFirebaseConfig(Object firebaseConfig) {
        this.firebaseConfig = firebaseConfig;
    }

    public Object getFirebaseConfig() {
        return this.firebaseConfig;
    }

    public void setColorConfiguration(Object colorConfiguration) {
        this.colorConfiguration = colorConfiguration;
    }

    public Object getColorConfiguration() {
        return this.colorConfiguration;
    }

    public void setStoreUrl(Object storeUrl) {
        this.storeUrl = storeUrl;
    }

    public Object getStoreUrl() {
        return this.storeUrl;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getName() {
        return this.name;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getDescription() {
        return this.description;
    }

    public void setPackageName(Object packageName) {
        this.packageName = packageName;
    }

    public Object getPackageName() {
        return this.packageName;
    }

    public void setVersionName(Object versionName) {
        this.versionName = versionName;
    }

    public Object getVersionName() {
        return this.versionName;
    }

    public void setSplashImageUrl(Object splashImageUrl) {
        this.splashImageUrl = splashImageUrl;
    }

    public Object getSplashImageUrl() {
        return this.splashImageUrl;
    }

    public void setVitalsConfig(Object vitalsConfig) {
        this.vitalsConfig = vitalsConfig;
    }

    public Object getVitalsConfig() {
        return this.vitalsConfig;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return this.version;
    }
}
