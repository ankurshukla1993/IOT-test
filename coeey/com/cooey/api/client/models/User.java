package com.cooey.api.client.models;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    @SerializedName("address")
    private Address address = null;
    @SerializedName("age")
    private Integer age = null;
    @SerializedName("alarmId")
    private String alarmId = null;
    @SerializedName("allergires")
    private List<Allergy> allergires = null;
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("authenticationProvider")
    private AuthenticationProviderEnum authenticationProvider = null;
    @SerializedName("bloodGroup")
    private String bloodGroup = null;
    @SerializedName("careTaker")
    private String careTaker = null;
    @SerializedName("category")
    private String category = null;
    @SerializedName("country")
    private String country = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("dateOfBirth")
    private Long dateOfBirth = null;
    @SerializedName("education")
    private String education = null;
    @SerializedName("email")
    private String email = null;
    @SerializedName("emergencyContact")
    private String emergencyContact = null;
    @SerializedName("firstName")
    private String firstName = null;
    @SerializedName("gender")
    private String gender = null;
    @SerializedName("governmentId")
    private String governmentId = null;
    @SerializedName("groupId")
    private String groupId = null;
    @SerializedName("height")
    private Height height = null;
    @SerializedName("hipSize")
    private HipSize hipSize = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("internalId")
    private String internalId = null;
    @SerializedName("lastName")
    private String lastName = null;
    @SerializedName("maratialstatus")
    private String maratialstatus = null;
    @SerializedName("mobile")
    private String mobile = null;
    @SerializedName("oauthId")
    private String oauthId = null;
    @SerializedName("oauthToken")
    private String oauthToken = null;
    @SerializedName("occupation")
    private String occupation = null;
    @SerializedName("parameters")
    private String parameters = null;
    @SerializedName("password")
    private String password = null;
    @SerializedName("passwordResetEnabled")
    private Boolean passwordResetEnabled = Boolean.valueOf(false);
    @SerializedName("profilePhotoId")
    private String profilePhotoId = null;
    @SerializedName("publicId")
    private Long publicId = null;
    @SerializedName("pulse")
    private String pulse = null;
    @SerializedName("room")
    private String room = null;
    @SerializedName("shiftId")
    private String shiftId = null;
    @SerializedName("shiftTimings")
    private String shiftTimings = null;
    @SerializedName("status")
    private String status = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("timeZone")
    private String timeZone = null;
    @SerializedName("type")
    private String type = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;
    @SerializedName("userSettings")
    private UserSettings userSettings = null;
    @SerializedName("waistSize")
    private WaistSize waistSize = null;
    @SerializedName("weight")
    private Weight weight = null;

    @JsonAdapter(Adapter.class)
    public enum AuthenticationProviderEnum {
        COOEY("COOEY"),
        FACEBOOK("FACEBOOK"),
        GOOGLE("GOOGLE"),
        PHONE("PHONE");
        
        private String value;

        private AuthenticationProviderEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }

        public static AuthenticationProviderEnum fromValue(String text) {
            for (AuthenticationProviderEnum b : values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    public User createdOn(Long createdOn) {
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

    public User updatedOn(Long updatedOn) {
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

    public User tenantId(String tenantId) {
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

    public User applicationId(String applicationId) {
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

    public User id(String id) {
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

    public User firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @ApiModelProperty("")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @ApiModelProperty("")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User dateOfBirth(Long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    @ApiModelProperty("")
    public Long getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public User email(String email) {
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

    public User password(String password) {
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

    public User authenticationProvider(AuthenticationProviderEnum authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
        return this;
    }

    @ApiModelProperty("")
    public AuthenticationProviderEnum getAuthenticationProvider() {
        return this.authenticationProvider;
    }

    public void setAuthenticationProvider(AuthenticationProviderEnum authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    public User country(String country) {
        this.country = country;
        return this;
    }

    @ApiModelProperty("")
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User mobile(String mobile) {
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

    public User gender(String gender) {
        this.gender = gender;
        return this;
    }

    @ApiModelProperty("")
    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User userSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
        return this;
    }

    @ApiModelProperty("")
    public UserSettings getUserSettings() {
        return this.userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

    public User publicId(Long publicId) {
        this.publicId = publicId;
        return this;
    }

    @ApiModelProperty("")
    public Long getPublicId() {
        return this.publicId;
    }

    public void setPublicId(Long publicId) {
        this.publicId = publicId;
    }

    public User groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    @ApiModelProperty("")
    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public User type(String type) {
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

    public User careTaker(String careTaker) {
        this.careTaker = careTaker;
        return this;
    }

    @ApiModelProperty("")
    public String getCareTaker() {
        return this.careTaker;
    }

    public void setCareTaker(String careTaker) {
        this.careTaker = careTaker;
    }

    public User alarmId(String alarmId) {
        this.alarmId = alarmId;
        return this;
    }

    @ApiModelProperty("")
    public String getAlarmId() {
        return this.alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public User allergires(List<Allergy> allergires) {
        this.allergires = allergires;
        return this;
    }

    public User addAllergiresItem(Allergy allergiresItem) {
        if (this.allergires == null) {
            this.allergires = new ArrayList();
        }
        this.allergires.add(allergiresItem);
        return this;
    }

    @ApiModelProperty("")
    public List<Allergy> getAllergires() {
        return this.allergires;
    }

    public void setAllergires(List<Allergy> allergires) {
        this.allergires = allergires;
    }

    public User room(String room) {
        this.room = room;
        return this;
    }

    @ApiModelProperty("")
    public String getRoom() {
        return this.room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public User governmentId(String governmentId) {
        this.governmentId = governmentId;
        return this;
    }

    @ApiModelProperty("")
    public String getGovernmentId() {
        return this.governmentId;
    }

    public void setGovernmentId(String governmentId) {
        this.governmentId = governmentId;
    }

    public User internalId(String internalId) {
        this.internalId = internalId;
        return this;
    }

    @ApiModelProperty("")
    public String getInternalId() {
        return this.internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public User status(String status) {
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

    public User age(Integer age) {
        this.age = age;
        return this;
    }

    @ApiModelProperty("")
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User weight(Weight weight) {
        this.weight = weight;
        return this;
    }

    @ApiModelProperty("")
    public Weight getWeight() {
        return this.weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public User pulse(String pulse) {
        this.pulse = pulse;
        return this;
    }

    @ApiModelProperty("")
    public String getPulse() {
        return this.pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public User height(Height height) {
        this.height = height;
        return this;
    }

    @ApiModelProperty("")
    public Height getHeight() {
        return this.height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public User hipSize(HipSize hipSize) {
        this.hipSize = hipSize;
        return this;
    }

    @ApiModelProperty("")
    public HipSize getHipSize() {
        return this.hipSize;
    }

    public void setHipSize(HipSize hipSize) {
        this.hipSize = hipSize;
    }

    public User waistSize(WaistSize waistSize) {
        this.waistSize = waistSize;
        return this;
    }

    @ApiModelProperty("")
    public WaistSize getWaistSize() {
        return this.waistSize;
    }

    public void setWaistSize(WaistSize waistSize) {
        this.waistSize = waistSize;
    }

    public User category(String category) {
        this.category = category;
        return this;
    }

    @ApiModelProperty("")
    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User occupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    @ApiModelProperty("")
    public String getOccupation() {
        return this.occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public User education(String education) {
        this.education = education;
        return this;
    }

    @ApiModelProperty("")
    public String getEducation() {
        return this.education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public User maratialstatus(String maratialstatus) {
        this.maratialstatus = maratialstatus;
        return this;
    }

    @ApiModelProperty("")
    public String getMaratialstatus() {
        return this.maratialstatus;
    }

    public void setMaratialstatus(String maratialstatus) {
        this.maratialstatus = maratialstatus;
    }

    public User parameters(String parameters) {
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

    public User bloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    @ApiModelProperty("")
    public String getBloodGroup() {
        return this.bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public User profilePhotoId(String profilePhotoId) {
        this.profilePhotoId = profilePhotoId;
        return this;
    }

    @ApiModelProperty("")
    public String getProfilePhotoId() {
        return this.profilePhotoId;
    }

    public void setProfilePhotoId(String profilePhotoId) {
        this.profilePhotoId = profilePhotoId;
    }

    public User emergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
        return this;
    }

    @ApiModelProperty("")
    public String getEmergencyContact() {
        return this.emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public User shiftId(String shiftId) {
        this.shiftId = shiftId;
        return this;
    }

    @ApiModelProperty("")
    public String getShiftId() {
        return this.shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public User shiftTimings(String shiftTimings) {
        this.shiftTimings = shiftTimings;
        return this;
    }

    @ApiModelProperty("")
    public String getShiftTimings() {
        return this.shiftTimings;
    }

    public void setShiftTimings(String shiftTimings) {
        this.shiftTimings = shiftTimings;
    }

    public User timeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    @ApiModelProperty("")
    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public User passwordResetEnabled(Boolean passwordResetEnabled) {
        this.passwordResetEnabled = passwordResetEnabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getPasswordResetEnabled() {
        return this.passwordResetEnabled;
    }

    public void setPasswordResetEnabled(Boolean passwordResetEnabled) {
        this.passwordResetEnabled = passwordResetEnabled;
    }

    public User address(Address address) {
        this.address = address;
        return this;
    }

    @ApiModelProperty("")
    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User oauthId(String oauthId) {
        this.oauthId = oauthId;
        return this;
    }

    @ApiModelProperty("")
    public String getOauthId() {
        return this.oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    public User oauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
        return this;
    }

    @ApiModelProperty("")
    public String getOauthToken() {
        return this.oauthToken;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        if (Objects.equals(this.createdOn, user.createdOn) && Objects.equals(this.updatedOn, user.updatedOn) && Objects.equals(this.tenantId, user.tenantId) && Objects.equals(this.applicationId, user.applicationId) && Objects.equals(this.id, user.id) && Objects.equals(this.firstName, user.firstName) && Objects.equals(this.lastName, user.lastName) && Objects.equals(this.dateOfBirth, user.dateOfBirth) && Objects.equals(this.email, user.email) && Objects.equals(this.password, user.password) && Objects.equals(this.authenticationProvider, user.authenticationProvider) && Objects.equals(this.country, user.country) && Objects.equals(this.mobile, user.mobile) && Objects.equals(this.gender, user.gender) && Objects.equals(this.userSettings, user.userSettings) && Objects.equals(this.publicId, user.publicId) && Objects.equals(this.groupId, user.groupId) && Objects.equals(this.type, user.type) && Objects.equals(this.careTaker, user.careTaker) && Objects.equals(this.alarmId, user.alarmId) && Objects.equals(this.allergires, user.allergires) && Objects.equals(this.room, user.room) && Objects.equals(this.governmentId, user.governmentId) && Objects.equals(this.internalId, user.internalId) && Objects.equals(this.status, user.status) && Objects.equals(this.age, user.age) && Objects.equals(this.weight, user.weight) && Objects.equals(this.pulse, user.pulse) && Objects.equals(this.height, user.height) && Objects.equals(this.hipSize, user.hipSize) && Objects.equals(this.waistSize, user.waistSize) && Objects.equals(this.category, user.category) && Objects.equals(this.occupation, user.occupation) && Objects.equals(this.education, user.education) && Objects.equals(this.maratialstatus, user.maratialstatus) && Objects.equals(this.parameters, user.parameters) && Objects.equals(this.bloodGroup, user.bloodGroup) && Objects.equals(this.profilePhotoId, user.profilePhotoId) && Objects.equals(this.emergencyContact, user.emergencyContact) && Objects.equals(this.shiftId, user.shiftId) && Objects.equals(this.shiftTimings, user.shiftTimings) && Objects.equals(this.timeZone, user.timeZone) && Objects.equals(this.passwordResetEnabled, user.passwordResetEnabled) && Objects.equals(this.address, user.address) && Objects.equals(this.oauthId, user.oauthId) && Objects.equals(this.oauthToken, user.oauthToken)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.firstName, this.lastName, this.dateOfBirth, this.email, this.password, this.authenticationProvider, this.country, this.mobile, this.gender, this.userSettings, this.publicId, this.groupId, this.type, this.careTaker, this.alarmId, this.allergires, this.room, this.governmentId, this.internalId, this.status, this.age, this.weight, this.pulse, this.height, this.hipSize, this.waistSize, this.category, this.occupation, this.education, this.maratialstatus, this.parameters, this.bloodGroup, this.profilePhotoId, this.emergencyContact, this.shiftId, this.shiftTimings, this.timeZone, this.passwordResetEnabled, this.address, this.oauthId, this.oauthToken});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class User {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
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
        sb.append("    userSettings: ").append(toIndentedString(this.userSettings)).append("\n");
        sb.append("    publicId: ").append(toIndentedString(this.publicId)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(this.groupId)).append("\n");
        sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
        sb.append("    careTaker: ").append(toIndentedString(this.careTaker)).append("\n");
        sb.append("    alarmId: ").append(toIndentedString(this.alarmId)).append("\n");
        sb.append("    allergires: ").append(toIndentedString(this.allergires)).append("\n");
        sb.append("    room: ").append(toIndentedString(this.room)).append("\n");
        sb.append("    governmentId: ").append(toIndentedString(this.governmentId)).append("\n");
        sb.append("    internalId: ").append(toIndentedString(this.internalId)).append("\n");
        sb.append("    status: ").append(toIndentedString(this.status)).append("\n");
        sb.append("    age: ").append(toIndentedString(this.age)).append("\n");
        sb.append("    weight: ").append(toIndentedString(this.weight)).append("\n");
        sb.append("    pulse: ").append(toIndentedString(this.pulse)).append("\n");
        sb.append("    height: ").append(toIndentedString(this.height)).append("\n");
        sb.append("    hipSize: ").append(toIndentedString(this.hipSize)).append("\n");
        sb.append("    waistSize: ").append(toIndentedString(this.waistSize)).append("\n");
        sb.append("    category: ").append(toIndentedString(this.category)).append("\n");
        sb.append("    occupation: ").append(toIndentedString(this.occupation)).append("\n");
        sb.append("    education: ").append(toIndentedString(this.education)).append("\n");
        sb.append("    maratialstatus: ").append(toIndentedString(this.maratialstatus)).append("\n");
        sb.append("    parameters: ").append(toIndentedString(this.parameters)).append("\n");
        sb.append("    bloodGroup: ").append(toIndentedString(this.bloodGroup)).append("\n");
        sb.append("    profilePhotoId: ").append(toIndentedString(this.profilePhotoId)).append("\n");
        sb.append("    emergencyContact: ").append(toIndentedString(this.emergencyContact)).append("\n");
        sb.append("    shiftId: ").append(toIndentedString(this.shiftId)).append("\n");
        sb.append("    shiftTimings: ").append(toIndentedString(this.shiftTimings)).append("\n");
        sb.append("    timeZone: ").append(toIndentedString(this.timeZone)).append("\n");
        sb.append("    passwordResetEnabled: ").append(toIndentedString(this.passwordResetEnabled)).append("\n");
        sb.append("    address: ").append(toIndentedString(this.address)).append("\n");
        sb.append("    oauthId: ").append(toIndentedString(this.oauthId)).append("\n");
        sb.append("    oauthToken: ").append(toIndentedString(this.oauthToken)).append("\n");
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
