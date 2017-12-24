package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Tenant {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("email")
    private String email = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("mobile")
    private String mobile = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("password")
    private String password = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;

    public Tenant createdOn(Long createdOn) {
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

    public Tenant updatedOn(Long updatedOn) {
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

    public Tenant tenantId(String tenantId) {
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

    public Tenant applicationId(String applicationId) {
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

    public Tenant id(String id) {
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

    public Tenant name(String name) {
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

    public Tenant email(String email) {
        this.email = email;
        return this;
    }

    @ApiModelProperty("")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Tenant password(String password) {
        this.password = password;
        return this;
    }

    @ApiModelProperty("")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Tenant mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    @ApiModelProperty("")
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tenant tenant = (Tenant) o;
        if (Objects.equals(this.createdOn, tenant.createdOn) && Objects.equals(this.updatedOn, tenant.updatedOn) && Objects.equals(this.tenantId, tenant.tenantId) && Objects.equals(this.applicationId, tenant.applicationId) && Objects.equals(this.id, tenant.id) && Objects.equals(this.name, tenant.name) && Objects.equals(this.email, tenant.email) && Objects.equals(this.password, tenant.password) && Objects.equals(this.mobile, tenant.mobile)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.name, this.email, this.password, this.mobile});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Tenant {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    email: ").append(toIndentedString(this.email)).append("\n");
        sb.append("    password: ").append(toIndentedString(this.password)).append("\n");
        sb.append("    mobile: ").append(toIndentedString(this.mobile)).append("\n");
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
