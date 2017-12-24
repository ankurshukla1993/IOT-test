package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Group {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("description")
    private String description = null;
    @SerializedName("dietTemplate")
    private DietTemplate dietTemplate = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("parameters")
    private String parameters = null;
    @SerializedName("parentId")
    private String parentId = null;
    @SerializedName("status")
    private String status = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("type")
    private String type = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;

    public Group createdOn(Long createdOn) {
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

    public Group updatedOn(Long updatedOn) {
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

    public Group tenantId(String tenantId) {
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

    public Group applicationId(String applicationId) {
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

    public Group id(String id) {
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

    public Group name(String name) {
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

    public Group description(String description) {
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

    public Group type(String type) {
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

    public Group parameters(String parameters) {
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

    public Group parentId(String parentId) {
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

    public Group dietTemplate(DietTemplate dietTemplate) {
        this.dietTemplate = dietTemplate;
        return this;
    }

    @ApiModelProperty("")
    public DietTemplate getDietTemplate() {
        return this.dietTemplate;
    }

    public void setDietTemplate(DietTemplate dietTemplate) {
        this.dietTemplate = dietTemplate;
    }

    public Group status(String status) {
        this.status = status;
        return this;
    }

    @ApiModelProperty("")
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Group group = (Group) o;
        if (Objects.equals(this.createdOn, group.createdOn) && Objects.equals(this.updatedOn, group.updatedOn) && Objects.equals(this.tenantId, group.tenantId) && Objects.equals(this.applicationId, group.applicationId) && Objects.equals(this.id, group.id) && Objects.equals(this.name, group.name) && Objects.equals(this.description, group.description) && Objects.equals(this.type, group.type) && Objects.equals(this.parameters, group.parameters) && Objects.equals(this.parentId, group.parentId) && Objects.equals(this.dietTemplate, group.dietTemplate) && Objects.equals(this.status, group.status)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.name, this.description, this.type, this.parameters, this.parentId, this.dietTemplate, this.status});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Group {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    description: ").append(toIndentedString(this.description)).append("\n");
        sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
        sb.append("    parameters: ").append(toIndentedString(this.parameters)).append("\n");
        sb.append("    parentId: ").append(toIndentedString(this.parentId)).append("\n");
        sb.append("    dietTemplate: ").append(toIndentedString(this.dietTemplate)).append("\n");
        sb.append("    status: ").append(toIndentedString(this.status)).append("\n");
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
