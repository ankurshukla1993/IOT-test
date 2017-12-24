package com.cooey.common.vo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class VitalConfig {
    @SerializedName("applicationId")
    private Object applicationId;
    @SerializedName("context")
    private boolean context;
    @SerializedName("createdOn")
    private int createdOn;
    @SerializedName("fieldConfig")
    private List<FieldConfigItem> fieldConfig;
    @SerializedName("graphRequired")
    private boolean graphRequired;
    @SerializedName("iconURL")
    private Object iconURL;
    @SerializedName("id")
    private String id;
    @SerializedName("mood")
    private boolean mood;
    @SerializedName("name")
    private String name;
    @SerializedName("parameters")
    private Object parameters;
    @SerializedName("primary")
    private boolean primary;
    @SerializedName("propertyStateName")
    private Object propertyStateName;
    @SerializedName("tenantId")
    private String tenantId;
    @SerializedName("type")
    private String type;
    @SerializedName("updatedOn")
    private int updatedOn;

    public void setMood(boolean mood) {
        this.mood = mood;
    }

    public boolean isMood() {
        return this.mood;
    }

    public void setUpdatedOn(int updatedOn) {
        this.updatedOn = updatedOn;
    }

    public int getUpdatedOn() {
        return this.updatedOn;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setFieldConfig(List<FieldConfigItem> fieldConfig) {
        this.fieldConfig = fieldConfig;
    }

    public List<FieldConfigItem> getFieldConfig() {
        return this.fieldConfig;
    }

    public void setCreatedOn(int createdOn) {
        this.createdOn = createdOn;
    }

    public int getCreatedOn() {
        return this.createdOn;
    }

    public void setPropertyStateName(Object propertyStateName) {
        this.propertyStateName = propertyStateName;
    }

    public Object getPropertyStateName() {
        return this.propertyStateName;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setContext(boolean context) {
        this.context = context;
    }

    public boolean isContext() {
        return this.context;
    }

    public void setGraphRequired(boolean graphRequired) {
        this.graphRequired = graphRequired;
    }

    public boolean isGraphRequired() {
        return this.graphRequired;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setIconURL(Object iconURL) {
        this.iconURL = iconURL;
    }

    public Object getIconURL() {
        return this.iconURL;
    }

    public void setApplicationId(Object applicationId) {
        this.applicationId = applicationId;
    }

    public Object getApplicationId() {
        return this.applicationId;
    }

    public void setParameters(Object parameters) {
        this.parameters = parameters;
    }

    public Object getParameters() {
        return this.parameters;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isPrimary() {
        return this.primary;
    }
}
