package com.cooey.common.services.requests;

import android.support.annotation.RequiresApi;
import com.cooey.common.vo.Allergy;
import com.cooey.common.vo.CaretakerSettings;
import com.cooey.common.vo.UserSettings;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateUserRequest {
    @SerializedName("alarmId")
    private String alarmId = null;
    @SerializedName("allergires")
    private List<Allergy> allergires = new ArrayList();
    @SerializedName("authId")
    private String authId = null;
    @SerializedName("authToken")
    private String authToken = null;
    @SerializedName("authenticationProvider")
    private AuthenticationProviderEnum authenticationProvider = null;
    @SerializedName("caretakerId")
    private String caretakerId = null;
    @SerializedName("caretakerSettings")
    private CaretakerSettings caretakerSettings = null;
    @SerializedName("country")
    private String country = null;
    @SerializedName("dateOfBirth")
    private String dateOfBirth = null;
    @SerializedName("email")
    private String email = null;
    @SerializedName("firstName")
    private String firstName = null;
    @SerializedName("gender")
    private String gender = null;
    @SerializedName("groupId")
    private String groupId = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("lastName")
    private String lastName = null;
    @SerializedName("mobile")
    private String mobile = null;
    @SerializedName("partnerId")
    private String partnerId = null;
    @SerializedName("password")
    private String password = null;
    @SerializedName("passwordResetEnabled")
    private Boolean passwordResetEnabled = Boolean.valueOf(false);
    @SerializedName("publicId")
    private Long publicId = null;
    @SerializedName("requestId")
    private String requestId = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("type")
    private String type = null;
    @SerializedName("userSettings")
    private UserSettings userSettings = null;

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

    public CreateUserRequest id(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CreateUserRequest firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public CreateUserRequest lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public CreateUserRequest dateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public CreateUserRequest email(String email) {
        this.email = email;
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CreateUserRequest password(String password) {
        this.password = password;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CreateUserRequest authenticationProvider(AuthenticationProviderEnum authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
        return this;
    }

    public AuthenticationProviderEnum getAuthenticationProvider() {
        return this.authenticationProvider;
    }

    public void setAuthenticationProvider(AuthenticationProviderEnum authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    public CreateUserRequest country(String country) {
        this.country = country;
        return this;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CreateUserRequest mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public CreateUserRequest gender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public CreateUserRequest tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public CreateUserRequest userSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
        return this;
    }

    public UserSettings getUserSettings() {
        return this.userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

    public CreateUserRequest publicId(Long publicId) {
        this.publicId = publicId;
        return this;
    }

    public Long getPublicId() {
        return this.publicId;
    }

    public void setPublicId(Long publicId) {
        this.publicId = publicId;
    }

    public CreateUserRequest groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public CreateUserRequest type(String type) {
        this.type = type;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CreateUserRequest caretakerSettings(CaretakerSettings caretakerSettings) {
        this.caretakerSettings = caretakerSettings;
        return this;
    }

    public CaretakerSettings getCaretakerSettings() {
        return this.caretakerSettings;
    }

    public void setCaretakerSettings(CaretakerSettings caretakerSettings) {
        this.caretakerSettings = caretakerSettings;
    }

    public CreateUserRequest caretakerId(String caretakerId) {
        this.caretakerId = caretakerId;
        return this;
    }

    public String getCaretakerId() {
        return this.caretakerId;
    }

    public void setCaretakerId(String caretakerId) {
        this.caretakerId = caretakerId;
    }

    public CreateUserRequest alarmId(String alarmId) {
        this.alarmId = alarmId;
        return this;
    }

    public String getAlarmId() {
        return this.alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public CreateUserRequest allergires(List<Allergy> allergires) {
        this.allergires = allergires;
        return this;
    }

    public CreateUserRequest addAllergiresItem(Allergy allergiresItem) {
        this.allergires.add(allergiresItem);
        return this;
    }

    public List<Allergy> getAllergires() {
        return this.allergires;
    }

    public void setAllergires(List<Allergy> allergires) {
        this.allergires = allergires;
    }

    public CreateUserRequest requestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public CreateUserRequest partnerId(String partnerId) {
        this.partnerId = partnerId;
        return this;
    }

    public String getPartnerId() {
        return this.partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public CreateUserRequest passwordResetEnabled(Boolean passwordResetEnabled) {
        this.passwordResetEnabled = passwordResetEnabled;
        return this;
    }

    public Boolean getPasswordResetEnabled() {
        return this.passwordResetEnabled;
    }

    public void setPasswordResetEnabled(Boolean passwordResetEnabled) {
        this.passwordResetEnabled = passwordResetEnabled;
    }

    public CreateUserRequest authId(String authId) {
        this.authId = authId;
        return this;
    }

    public String getauthId() {
        return this.authId;
    }

    public void setauthId(String authId) {
        this.authId = authId;
    }

    public CreateUserRequest authToken(String authToken) {
        this.authToken = authToken;
        return this;
    }

    public String getauthToken() {
        return this.authToken;
    }

    public void setauthToken(String authToken) {
        this.authToken = authToken;
    }

    @RequiresApi(api = 19)
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateUserRequest createUserRequest = (CreateUserRequest) o;
        if (Objects.equals(this.id, createUserRequest.id) && Objects.equals(this.firstName, createUserRequest.firstName) && Objects.equals(this.lastName, createUserRequest.lastName) && Objects.equals(this.dateOfBirth, createUserRequest.dateOfBirth) && Objects.equals(this.email, createUserRequest.email) && Objects.equals(this.password, createUserRequest.password) && Objects.equals(this.authenticationProvider, createUserRequest.authenticationProvider) && Objects.equals(this.country, createUserRequest.country) && Objects.equals(this.mobile, createUserRequest.mobile) && Objects.equals(this.gender, createUserRequest.gender) && Objects.equals(this.tenantId, createUserRequest.tenantId) && Objects.equals(this.userSettings, createUserRequest.userSettings) && Objects.equals(this.publicId, createUserRequest.publicId) && Objects.equals(this.groupId, createUserRequest.groupId) && Objects.equals(this.type, createUserRequest.type) && Objects.equals(this.caretakerSettings, createUserRequest.caretakerSettings) && Objects.equals(this.caretakerId, createUserRequest.caretakerId) && Objects.equals(this.alarmId, createUserRequest.alarmId) && Objects.equals(this.allergires, createUserRequest.allergires) && Objects.equals(this.requestId, createUserRequest.requestId) && Objects.equals(this.partnerId, createUserRequest.partnerId) && Objects.equals(this.passwordResetEnabled, createUserRequest.passwordResetEnabled) && Objects.equals(this.authId, createUserRequest.authId) && Objects.equals(this.authToken, createUserRequest.authToken)) {
            return true;
        }
        return false;
    }

    @RequiresApi(api = 19)
    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.firstName, this.lastName, this.dateOfBirth, this.email, this.password, this.authenticationProvider, this.country, this.mobile, this.gender, this.tenantId, this.userSettings, this.publicId, this.groupId, this.type, this.caretakerSettings, this.caretakerId, this.alarmId, this.allergires, this.requestId, this.partnerId, this.passwordResetEnabled, this.authId, this.authToken});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateUserRequest {\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    firstName: ").append(toIndentedString(this.firstName)).append("\n");
        sb.append("    lastName: ").append(toIndentedString(this.lastName)).append("\n");
        sb.append("    dateOfBirth: ").append(toIndentedString(this.dateOfBirth)).append("\n");
        sb.append("    email: ").append(toIndentedString(this.email)).append("\n");
        sb.append("    password: ").append(toIndentedString(this.password)).append("\n");
        sb.append("    authenticationProvider: ").append(toIndentedString(this.authenticationProvider)).append("\n");
        sb.append("    country: ").append(toIndentedString(this.country)).append("\n");
        sb.append("    mobile: ").append(toIndentedString(this.mobile)).append("\n");
        sb.append("    gender: ").append(toIndentedString(this.gender)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    userSettings: ").append(toIndentedString(this.userSettings)).append("\n");
        sb.append("    publicId: ").append(toIndentedString(this.publicId)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(this.groupId)).append("\n");
        sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
        sb.append("    caretakerSettings: ").append(toIndentedString(this.caretakerSettings)).append("\n");
        sb.append("    caretakerId: ").append(toIndentedString(this.caretakerId)).append("\n");
        sb.append("    alarmId: ").append(toIndentedString(this.alarmId)).append("\n");
        sb.append("    allergires: ").append(toIndentedString(this.allergires)).append("\n");
        sb.append("    requestId: ").append(toIndentedString(this.requestId)).append("\n");
        sb.append("    partnerId: ").append(toIndentedString(this.partnerId)).append("\n");
        sb.append("    passwordResetEnabled: ").append(toIndentedString(this.passwordResetEnabled)).append("\n");
        sb.append("    authId: ").append(toIndentedString(this.authId)).append("\n");
        sb.append("    authToken: ").append(toIndentedString(this.authToken)).append("\n");
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
