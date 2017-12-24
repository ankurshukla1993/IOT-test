package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class CareplanBlueprint {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("description")
    private String description = null;
    @SerializedName("enabled")
    private Boolean enabled = Boolean.valueOf(false);
    @SerializedName("id")
    private String id = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("numOfDays")
    private Integer numOfDays = null;
    @SerializedName("parentId")
    private String parentId = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;

    public CareplanBlueprint createdOn(Long createdOn) {
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

    public CareplanBlueprint updatedOn(Long updatedOn) {
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

    public CareplanBlueprint tenantId(String tenantId) {
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

    public CareplanBlueprint applicationId(String applicationId) {
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

    public CareplanBlueprint id(String id) {
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

    public CareplanBlueprint name(String name) {
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

    public CareplanBlueprint description(String description) {
        this.description = description;
        return this;
    }

    @ApiModelProperty("")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CareplanBlueprint parentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    @ApiModelProperty("")
    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public CareplanBlueprint numOfDays(Integer numOfDays) {
        this.numOfDays = numOfDays;
        return this;
    }

    @ApiModelProperty("")
    public Integer getNumOfDays() {
        return this.numOfDays;
    }

    public void setNumOfDays(Integer numOfDays) {
        this.numOfDays = numOfDays;
    }

    public CareplanBlueprint enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CareplanBlueprint careplanBlueprint = (CareplanBlueprint) o;
        if (Objects.equals(this.createdOn, careplanBlueprint.createdOn) && Objects.equals(this.updatedOn, careplanBlueprint.updatedOn) && Objects.equals(this.tenantId, careplanBlueprint.tenantId) && Objects.equals(this.applicationId, careplanBlueprint.applicationId) && Objects.equals(this.id, careplanBlueprint.id) && Objects.equals(this.name, careplanBlueprint.name) && Objects.equals(this.description, careplanBlueprint.description) && Objects.equals(this.parentId, careplanBlueprint.parentId) && Objects.equals(this.numOfDays, careplanBlueprint.numOfDays) && Objects.equals(this.enabled, careplanBlueprint.enabled)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.name, this.description, this.parentId, this.numOfDays, this.enabled});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CareplanBlueprint {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    description: ").append(toIndentedString(this.description)).append("\n");
        sb.append("    parentId: ").append(toIndentedString(this.parentId)).append("\n");
        sb.append("    numOfDays: ").append(toIndentedString(this.numOfDays)).append("\n");
        sb.append("    enabled: ").append(toIndentedString(this.enabled)).append("\n");
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
