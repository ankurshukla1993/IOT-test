package com.cooey.common.config;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class VitalTemplatesConfigListItem {
    @SerializedName("createdOn")
    private int createdOn;
    @SerializedName("fieldConfig")
    private List<FieldConfig> fieldConfigList;
    @SerializedName("graphRequired")
    private boolean graphRequired;
    @SerializedName("iconURL")
    private Object iconURL;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("parameters")
    private String parameters;
    @SerializedName("primary")
    private boolean primary;
    @SerializedName("propertyStateName")
    private String propertyStateName;
    @SerializedName("tenantId")
    private String tenantId;
    @SerializedName("type")
    private String type;
    @SerializedName("updatedOn")
    private int updatedOn;

    public void setPropertyStateName(String propertyStateName) {
        this.propertyStateName = propertyStateName;
    }

    public String getPropertyStateName() {
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

    public void setGraphRequired(boolean graphRequired) {
        this.graphRequired = graphRequired;
    }

    public boolean isGraphRequired() {
        return this.graphRequired;
    }

    public void setUpdatedOn(int updatedOn) {
        this.updatedOn = updatedOn;
    }

    public int getUpdatedOn() {
        return this.updatedOn;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setCreatedOn(int createdOn) {
        this.createdOn = createdOn;
    }

    public int getCreatedOn() {
        return this.createdOn;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getParameters() {
        return this.parameters;
    }

    public List<FieldConfig> getFieldConfigList() {
        return this.fieldConfigList;
    }

    public void setFieldConfigList(List<FieldConfig> fieldConfigList) {
        this.fieldConfigList = fieldConfigList;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isPrimary() {
        return this.primary;
    }
}
