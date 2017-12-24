package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VitalTemplatesConfig {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("context")
    private Boolean context = Boolean.valueOf(false);
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("fieldConfig")
    private List<FieldConfig> fieldConfig = null;
    @SerializedName("graphRequired")
    private Boolean graphRequired = Boolean.valueOf(false);
    @SerializedName("iconURL")
    private String iconURL = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("mood")
    private Boolean mood = Boolean.valueOf(false);
    @SerializedName("name")
    private String name = null;
    @SerializedName("parameters")
    private String parameters = null;
    @SerializedName("primary")
    private Boolean primary = Boolean.valueOf(false);
    @SerializedName("propertyStateName")
    private String propertyStateName = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("type")
    private String type = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;

    public VitalTemplatesConfig createdOn(Long createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @ApiModelProperty("")
    public Long getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public VitalTemplatesConfig updatedOn(Long updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    @ApiModelProperty("")
    public Long getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public VitalTemplatesConfig tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @ApiModelProperty("")
    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public VitalTemplatesConfig applicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    @ApiModelProperty("")
    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public VitalTemplatesConfig id(String id) {
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

    public VitalTemplatesConfig name(String name) {
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

    public VitalTemplatesConfig type(String type) {
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

    public VitalTemplatesConfig mood(Boolean mood) {
        this.mood = mood;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getMood() {
        return this.mood;
    }

    public void setMood(Boolean mood) {
        this.mood = mood;
    }

    public VitalTemplatesConfig context(Boolean context) {
        this.context = context;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getContext() {
        return this.context;
    }

    public void setContext(Boolean context) {
        this.context = context;
    }

    public VitalTemplatesConfig fieldConfig(List<FieldConfig> fieldConfig) {
        this.fieldConfig = fieldConfig;
        return this;
    }

    public VitalTemplatesConfig addFieldConfigItem(FieldConfig fieldConfigItem) {
        if (this.fieldConfig == null) {
            this.fieldConfig = new ArrayList();
        }
        this.fieldConfig.add(fieldConfigItem);
        return this;
    }

    @ApiModelProperty("")
    public List<FieldConfig> getFieldConfig() {
        return this.fieldConfig;
    }

    public void setFieldConfig(List<FieldConfig> fieldConfig) {
        this.fieldConfig = fieldConfig;
    }

    public VitalTemplatesConfig parameters(String parameters) {
        this.parameters = parameters;
        return this;
    }

    @ApiModelProperty("")
    public String getParameters() {
        return this.parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public VitalTemplatesConfig iconURL(String iconURL) {
        this.iconURL = iconURL;
        return this;
    }

    @ApiModelProperty("")
    public String getIconURL() {
        return this.iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public VitalTemplatesConfig propertyStateName(String propertyStateName) {
        this.propertyStateName = propertyStateName;
        return this;
    }

    @ApiModelProperty("")
    public String getPropertyStateName() {
        return this.propertyStateName;
    }

    public void setPropertyStateName(String propertyStateName) {
        this.propertyStateName = propertyStateName;
    }

    public VitalTemplatesConfig primary(Boolean primary) {
        this.primary = primary;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getPrimary() {
        return this.primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public VitalTemplatesConfig graphRequired(Boolean graphRequired) {
        this.graphRequired = graphRequired;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getGraphRequired() {
        return this.graphRequired;
    }

    public void setGraphRequired(Boolean graphRequired) {
        this.graphRequired = graphRequired;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VitalTemplatesConfig vitalTemplatesConfig = (VitalTemplatesConfig) o;
        if (Objects.equals(this.createdOn, vitalTemplatesConfig.createdOn) && Objects.equals(this.updatedOn, vitalTemplatesConfig.updatedOn) && Objects.equals(this.tenantId, vitalTemplatesConfig.tenantId) && Objects.equals(this.applicationId, vitalTemplatesConfig.applicationId) && Objects.equals(this.id, vitalTemplatesConfig.id) && Objects.equals(this.name, vitalTemplatesConfig.name) && Objects.equals(this.type, vitalTemplatesConfig.type) && Objects.equals(this.mood, vitalTemplatesConfig.mood) && Objects.equals(this.context, vitalTemplatesConfig.context) && Objects.equals(this.fieldConfig, vitalTemplatesConfig.fieldConfig) && Objects.equals(this.parameters, vitalTemplatesConfig.parameters) && Objects.equals(this.iconURL, vitalTemplatesConfig.iconURL) && Objects.equals(this.propertyStateName, vitalTemplatesConfig.propertyStateName) && Objects.equals(this.primary, vitalTemplatesConfig.primary) && Objects.equals(this.graphRequired, vitalTemplatesConfig.graphRequired)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.name, this.type, this.mood, this.context, this.fieldConfig, this.parameters, this.iconURL, this.propertyStateName, this.primary, this.graphRequired});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VitalTemplatesConfig {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
        sb.append("    mood: ").append(toIndentedString(this.mood)).append("\n");
        sb.append("    context: ").append(toIndentedString(this.context)).append("\n");
        sb.append("    fieldConfig: ").append(toIndentedString(this.fieldConfig)).append("\n");
        sb.append("    parameters: ").append(toIndentedString(this.parameters)).append("\n");
        sb.append("    iconURL: ").append(toIndentedString(this.iconURL)).append("\n");
        sb.append("    propertyStateName: ").append(toIndentedString(this.propertyStateName)).append("\n");
        sb.append("    primary: ").append(toIndentedString(this.primary)).append("\n");
        sb.append("    graphRequired: ").append(toIndentedString(this.graphRequired)).append("\n");
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
