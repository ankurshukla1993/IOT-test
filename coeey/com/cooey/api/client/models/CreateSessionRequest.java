package com.cooey.api.client.models;

import com.cooey.android.users.old.utils.CTConstants;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class CreateSessionRequest {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("email")
    private String email = null;
    @SerializedName("password")
    private String password = null;
    @SerializedName("phone")
    private String phone = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("type")
    private TypeEnum type = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;

    @JsonAdapter(Adapter.class)
    public enum TypeEnum {
        PATIENT(CTConstants.PATIENTTYPE),
        TENANT(CTConstants.TENANT),
        GUARDIAN("GUARDIAN"),
        ADMIN("ADMIN"),
        CARE_TAKER(CTConstants.USERTYPE),
        USER("USER");
        
        private String value;

        private TypeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }

        public static TypeEnum fromValue(String text) {
            for (TypeEnum b : values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    public CreateSessionRequest createdOn(Long createdOn) {
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

    public CreateSessionRequest updatedOn(Long updatedOn) {
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

    public CreateSessionRequest tenantId(String tenantId) {
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

    public CreateSessionRequest applicationId(String applicationId) {
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

    public CreateSessionRequest email(String email) {
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

    public CreateSessionRequest password(String password) {
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

    public CreateSessionRequest phone(String phone) {
        this.phone = phone;
        return this;
    }

    @ApiModelProperty("")
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CreateSessionRequest type(TypeEnum type) {
        this.type = type;
        return this;
    }

    @ApiModelProperty("")
    public TypeEnum getType() {
        return this.type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateSessionRequest createSessionRequest = (CreateSessionRequest) o;
        if (Objects.equals(this.createdOn, createSessionRequest.createdOn) && Objects.equals(this.updatedOn, createSessionRequest.updatedOn) && Objects.equals(this.tenantId, createSessionRequest.tenantId) && Objects.equals(this.applicationId, createSessionRequest.applicationId) && Objects.equals(this.email, createSessionRequest.email) && Objects.equals(this.password, createSessionRequest.password) && Objects.equals(this.phone, createSessionRequest.phone) && Objects.equals(this.type, createSessionRequest.type)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.email, this.password, this.phone, this.type});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateSessionRequest {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    email: ").append(toIndentedString(this.email)).append("\n");
        sb.append("    password: ").append(toIndentedString(this.password)).append("\n");
        sb.append("    phone: ").append(toIndentedString(this.phone)).append("\n");
        sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
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
