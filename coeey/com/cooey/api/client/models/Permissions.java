package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Permissions {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("permissionList")
    private List<Permission> permissionList = null;
    @SerializedName("roleId")
    private String roleId = null;
    @SerializedName("roleName")
    private String roleName = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;
    @SerializedName("userId")
    private String userId = null;

    public Permissions createdOn(Long createdOn) {
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

    public Permissions updatedOn(Long updatedOn) {
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

    public Permissions tenantId(String tenantId) {
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

    public Permissions applicationId(String applicationId) {
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

    public Permissions id(String id) {
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

    public Permissions userId(String userId) {
        this.userId = userId;
        return this;
    }

    @ApiModelProperty("")
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Permissions permissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
        return this;
    }

    public Permissions addPermissionListItem(Permission permissionListItem) {
        if (this.permissionList == null) {
            this.permissionList = new ArrayList();
        }
        this.permissionList.add(permissionListItem);
        return this;
    }

    @ApiModelProperty("")
    public List<Permission> getPermissionList() {
        return this.permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public Permissions roleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    @ApiModelProperty("")
    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Permissions roleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    @ApiModelProperty("")
    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Permissions permissions = (Permissions) o;
        if (Objects.equals(this.createdOn, permissions.createdOn) && Objects.equals(this.updatedOn, permissions.updatedOn) && Objects.equals(this.tenantId, permissions.tenantId) && Objects.equals(this.applicationId, permissions.applicationId) && Objects.equals(this.id, permissions.id) && Objects.equals(this.userId, permissions.userId) && Objects.equals(this.permissionList, permissions.permissionList) && Objects.equals(this.roleId, permissions.roleId) && Objects.equals(this.roleName, permissions.roleName)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.userId, this.permissionList, this.roleId, this.roleName});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Permissions {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    userId: ").append(toIndentedString(this.userId)).append("\n");
        sb.append("    permissionList: ").append(toIndentedString(this.permissionList)).append("\n");
        sb.append("    roleId: ").append(toIndentedString(this.roleId)).append("\n");
        sb.append("    roleName: ").append(toIndentedString(this.roleName)).append("\n");
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
