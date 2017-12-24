package com.cooey.common.services.requests;

import com.cooey.android.users.old.utils.CTConstants;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;

public class CreateSessionRequest {
    @SerializedName("active")
    private boolean active;
    @SerializedName("applicationId")
    private String applicationId;
    @SerializedName("archived")
    private boolean archived;
    @SerializedName("authId")
    private String authId;
    @SerializedName("authToken")
    private String authToken;
    @SerializedName("authenticationProvider")
    private AuthenticationProviderEnum authenticationProvider = null;
    @SerializedName("createdOn")
    private long createdOn;
    @SerializedName("email")
    private String email = null;
    @SerializedName("externalId")
    private String externalId;
    @SerializedName("password")
    private String password = null;
    @SerializedName("phone")
    private String phone;
    @SerializedName("tenantId")
    private String tenantId;
    @SerializedName("type")
    private TypeEnum type = null;
    @SerializedName("updatedOn")
    private long updatedOn;

    public enum AuthenticationProviderEnum {
        COOEY("COOEY"),
        FACEBOOK("FACEBOOK"),
        GOOGLE("GOOGLE"),
        PHONE("PHONE");
        
        private String value;

        private AuthenticationProviderEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }
    }

    public enum TypeEnum {
        USER("USER"),
        TENANT(CTConstants.TENANT),
        CARE_TAKER(CTConstants.USERTYPE),
        GUARDIAN("GUARDIAN"),
        PATIENT(CTConstants.PATIENTTYPE);
        
        private String value;

        private TypeEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }
    }

    public CreateSessionRequest email(String email) {
        this.email = email;
        return this;
    }

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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CreateSessionRequest type(TypeEnum type) {
        this.type = type;
        return this;
    }

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
        if (Objects.equals(this.email, createSessionRequest.email) && Objects.equals(this.password, createSessionRequest.password) && Objects.equals(this.type, createSessionRequest.type)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.email, this.password, this.type});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateSessionRequest {\n");
        sb.append("    email: ").append(toIndentedString(this.email)).append("\n");
        sb.append("    password: ").append(toIndentedString(this.password)).append("\n");
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

    public long getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public long getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public boolean isArchived() {
        return this.archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public String getExternalId() {
        return this.externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AuthenticationProviderEnum getAuthenticationProvider() {
        return this.authenticationProvider;
    }

    public void setAuthenticationProvider(AuthenticationProviderEnum authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    public String getAuthId() {
        return this.authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
