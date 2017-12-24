package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Guardian {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("contacts")
    private List<Contact> contacts = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("guardianId")
    private String guardianId = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;
    @SerializedName("userId")
    private String userId = null;

    public Guardian createdOn(Long createdOn) {
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

    public Guardian updatedOn(Long updatedOn) {
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

    public Guardian tenantId(String tenantId) {
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

    public Guardian applicationId(String applicationId) {
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

    public Guardian id(String id) {
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

    public Guardian userId(String userId) {
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

    public Guardian guardianId(String guardianId) {
        this.guardianId = guardianId;
        return this;
    }

    @ApiModelProperty("")
    public String getGuardianId() {
        return this.guardianId;
    }

    public void setGuardianId(String guardianId) {
        this.guardianId = guardianId;
    }

    public Guardian name(String name) {
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

    public Guardian contacts(List<Contact> contacts) {
        this.contacts = contacts;
        return this;
    }

    public Guardian addContactsItem(Contact contactsItem) {
        if (this.contacts == null) {
            this.contacts = new ArrayList();
        }
        this.contacts.add(contactsItem);
        return this;
    }

    @ApiModelProperty("")
    public List<Contact> getContacts() {
        return this.contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Guardian guardian = (Guardian) o;
        if (Objects.equals(this.createdOn, guardian.createdOn) && Objects.equals(this.updatedOn, guardian.updatedOn) && Objects.equals(this.tenantId, guardian.tenantId) && Objects.equals(this.applicationId, guardian.applicationId) && Objects.equals(this.id, guardian.id) && Objects.equals(this.userId, guardian.userId) && Objects.equals(this.guardianId, guardian.guardianId) && Objects.equals(this.name, guardian.name) && Objects.equals(this.contacts, guardian.contacts)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.userId, this.guardianId, this.name, this.contacts});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Guardian {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    userId: ").append(toIndentedString(this.userId)).append("\n");
        sb.append("    guardianId: ").append(toIndentedString(this.guardianId)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    contacts: ").append(toIndentedString(this.contacts)).append("\n");
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
