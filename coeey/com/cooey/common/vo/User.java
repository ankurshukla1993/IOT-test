package com.cooey.common.vo;

import android.databinding.Observable;
import android.databinding.Observable.OnPropertyChangedCallback;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.UserRealmProxyInterface;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

public class User extends RealmObject implements Observable, Parcelable, UserRealmProxyInterface {
    public static final Creator CREATOR = new 1();
    @SerializedName("address")
    private Address address;
    @SerializedName("alarmId")
    private String alarmId;
    @SerializedName("allergires")
    private RealmList<Allergy> allergires;
    @Ignore
    private AuthenticationProviderEnum authenticationProvide;
    @SerializedName("authenticationProvider")
    private String authenticationProviderValue;
    @SerializedName("caretakerId")
    private String caretakerId;
    @SerializedName("country")
    private String country;
    @SerializedName("dateOfBirth")
    private String dateOfBirth;
    @SerializedName("email")
    private String email;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("gender")
    private String gender;
    @SerializedName("governmentId")
    private String governmentId;
    @SerializedName("groupId")
    private String groupId;
    @SerializedName("height")
    private Height height;
    @SerializedName("hipSize")
    private HipSize hipSize;
    @SerializedName("id")
    @PrimaryKey
    private String id;
    @Ignore
    private transient boolean isSelected;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("oauthId")
    private String oauthId;
    @SerializedName("oauthToken")
    private String oauthToken;
    @SerializedName("password")
    private String password;
    @SerializedName("passwordResetEnabled")
    private Boolean passwordResetEnabled;
    @SerializedName("profilePhotoId")
    private String profilePhotoId;
    @SerializedName("profileTags")
    private RealmList<RealmString> profileTags;
    @SerializedName("publicId")
    private Long publicId;
    @SerializedName("room")
    private String room;
    @SerializedName("shiftId")
    private String shiftId;
    @SerializedName("shiftTimings")
    private String shiftTimings;
    @SerializedName("tenantId")
    private String tenantId;
    @SerializedName("timeZone")
    private String timeZone;
    @SerializedName("type")
    private String type;
    @SerializedName("userSettings")
    private UserSettings userSettings;
    @SerializedName("waistSize")
    private WaistSize waistSize;

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

    public Address realmGet$address() {
        return this.address;
    }

    public String realmGet$alarmId() {
        return this.alarmId;
    }

    public RealmList realmGet$allergires() {
        return this.allergires;
    }

    public String realmGet$authenticationProviderValue() {
        return this.authenticationProviderValue;
    }

    public String realmGet$caretakerId() {
        return this.caretakerId;
    }

    public String realmGet$country() {
        return this.country;
    }

    public String realmGet$dateOfBirth() {
        return this.dateOfBirth;
    }

    public String realmGet$email() {
        return this.email;
    }

    public String realmGet$firstName() {
        return this.firstName;
    }

    public String realmGet$gender() {
        return this.gender;
    }

    public String realmGet$governmentId() {
        return this.governmentId;
    }

    public String realmGet$groupId() {
        return this.groupId;
    }

    public Height realmGet$height() {
        return this.height;
    }

    public HipSize realmGet$hipSize() {
        return this.hipSize;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$lastName() {
        return this.lastName;
    }

    public String realmGet$mobile() {
        return this.mobile;
    }

    public String realmGet$oauthId() {
        return this.oauthId;
    }

    public String realmGet$oauthToken() {
        return this.oauthToken;
    }

    public String realmGet$password() {
        return this.password;
    }

    public Boolean realmGet$passwordResetEnabled() {
        return this.passwordResetEnabled;
    }

    public String realmGet$profilePhotoId() {
        return this.profilePhotoId;
    }

    public RealmList realmGet$profileTags() {
        return this.profileTags;
    }

    public Long realmGet$publicId() {
        return this.publicId;
    }

    public String realmGet$room() {
        return this.room;
    }

    public String realmGet$shiftId() {
        return this.shiftId;
    }

    public String realmGet$shiftTimings() {
        return this.shiftTimings;
    }

    public String realmGet$tenantId() {
        return this.tenantId;
    }

    public String realmGet$timeZone() {
        return this.timeZone;
    }

    public String realmGet$type() {
        return this.type;
    }

    public UserSettings realmGet$userSettings() {
        return this.userSettings;
    }

    public WaistSize realmGet$waistSize() {
        return this.waistSize;
    }

    public void realmSet$address(Address address) {
        this.address = address;
    }

    public void realmSet$alarmId(String str) {
        this.alarmId = str;
    }

    public void realmSet$allergires(RealmList realmList) {
        this.allergires = realmList;
    }

    public void realmSet$authenticationProviderValue(String str) {
        this.authenticationProviderValue = str;
    }

    public void realmSet$caretakerId(String str) {
        this.caretakerId = str;
    }

    public void realmSet$country(String str) {
        this.country = str;
    }

    public void realmSet$dateOfBirth(String str) {
        this.dateOfBirth = str;
    }

    public void realmSet$email(String str) {
        this.email = str;
    }

    public void realmSet$firstName(String str) {
        this.firstName = str;
    }

    public void realmSet$gender(String str) {
        this.gender = str;
    }

    public void realmSet$governmentId(String str) {
        this.governmentId = str;
    }

    public void realmSet$groupId(String str) {
        this.groupId = str;
    }

    public void realmSet$height(Height height) {
        this.height = height;
    }

    public void realmSet$hipSize(HipSize hipSize) {
        this.hipSize = hipSize;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$lastName(String str) {
        this.lastName = str;
    }

    public void realmSet$mobile(String str) {
        this.mobile = str;
    }

    public void realmSet$oauthId(String str) {
        this.oauthId = str;
    }

    public void realmSet$oauthToken(String str) {
        this.oauthToken = str;
    }

    public void realmSet$password(String str) {
        this.password = str;
    }

    public void realmSet$passwordResetEnabled(Boolean bool) {
        this.passwordResetEnabled = bool;
    }

    public void realmSet$profilePhotoId(String str) {
        this.profilePhotoId = str;
    }

    public void realmSet$profileTags(RealmList realmList) {
        this.profileTags = realmList;
    }

    public void realmSet$publicId(Long l) {
        this.publicId = l;
    }

    public void realmSet$room(String str) {
        this.room = str;
    }

    public void realmSet$shiftId(String str) {
        this.shiftId = str;
    }

    public void realmSet$shiftTimings(String str) {
        this.shiftTimings = str;
    }

    public void realmSet$tenantId(String str) {
        this.tenantId = str;
    }

    public void realmSet$timeZone(String str) {
        this.timeZone = str;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public void realmSet$userSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

    public void realmSet$waistSize(WaistSize waistSize) {
        this.waistSize = waistSize;
    }

    public static String toJson(User user) {
        return new GsonBuilder().create().toJson(user);
    }

    public static User fromJson(String userJson) {
        return (User) new GsonBuilder().create().fromJson(userJson, User.class);
    }

    public String getRoom() {
        return realmGet$room();
    }

    public void setRoom(String room) {
        realmSet$room(room);
    }

    public String getGovernmentId() {
        return realmGet$governmentId();
    }

    public void setGovernmentId(String governmentId) {
        realmSet$governmentId(governmentId);
    }

    public Height getHeight() {
        return realmGet$height();
    }

    public void setHeight(Height height) {
        realmSet$height(height);
    }

    public WaistSize getWaistSize() {
        return realmGet$waistSize();
    }

    public void setWaistSize(WaistSize waistSize) {
        realmSet$waistSize(waistSize);
    }

    public HipSize getHipSize() {
        return realmGet$hipSize();
    }

    public void setHipSize(HipSize hipSize) {
        realmSet$hipSize(hipSize);
    }

    public String getProfilePhotoId() {
        return realmGet$profilePhotoId();
    }

    public void setProfilePhotoId(String profilePhotoId) {
        realmSet$profilePhotoId(profilePhotoId);
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
    }

    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
    }

    public User id(String id) {
        realmSet$id(id);
        return this;
    }

    public RealmList<RealmString> getProfileTags() {
        return realmGet$profileTags();
    }

    public void setProfileTags(RealmList<RealmString> profileTags) {
        realmSet$profileTags(profileTags);
    }

    public User(Parcel in) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$id(null);
        realmSet$firstName(null);
        realmSet$lastName(null);
        realmSet$dateOfBirth(null);
        realmSet$email(null);
        realmSet$password(null);
        this.authenticationProvide = null;
        realmSet$country(null);
        realmSet$mobile(null);
        realmSet$gender(null);
        realmSet$tenantId(null);
        realmSet$publicId(null);
        realmSet$caretakerId(null);
        realmSet$alarmId(null);
        realmSet$allergires(new RealmList());
        realmSet$oauthId(null);
        realmSet$oauthToken(null);
        realmSet$id(in.readString());
        realmSet$firstName(in.readString());
        realmSet$lastName(in.readString());
        realmSet$country(in.readString());
        realmSet$dateOfBirth(in.readString());
        realmSet$gender(in.readString());
        realmSet$mobile(in.readString());
        realmSet$email(in.readString());
        realmSet$type(in.readString());
    }

    public User() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$id(null);
        realmSet$firstName(null);
        realmSet$lastName(null);
        realmSet$dateOfBirth(null);
        realmSet$email(null);
        realmSet$password(null);
        this.authenticationProvide = null;
        realmSet$country(null);
        realmSet$mobile(null);
        realmSet$gender(null);
        realmSet$tenantId(null);
        realmSet$publicId(null);
        realmSet$caretakerId(null);
        realmSet$alarmId(null);
        realmSet$allergires(new RealmList());
        realmSet$oauthId(null);
        realmSet$oauthToken(null);
    }

    public Address getAddress() {
        return realmGet$address();
    }

    public void setAddress(Address address) {
        realmSet$address(address);
    }

    public int describeContents() {
        return 0;
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String id) {
        realmSet$id(id);
    }

    public User firstName(String firstName) {
        realmSet$firstName(firstName);
        return this;
    }

    public String getFirstName() {
        return realmGet$firstName();
    }

    public void setFirstName(String firstName) {
        realmSet$firstName(firstName);
    }

    public User lastName(String lastName) {
        realmSet$lastName(lastName);
        return this;
    }

    public String getLastName() {
        return realmGet$lastName();
    }

    public void setLastName(String lastName) {
        realmSet$lastName(lastName);
    }

    public User dateOfBirth(String dateOfBirth) {
        realmSet$dateOfBirth(dateOfBirth);
        return this;
    }

    public String getShiftId() {
        return realmGet$shiftId();
    }

    public void setShiftId(String shiftId) {
        realmSet$shiftId(shiftId);
    }

    public String getShiftTimings() {
        return realmGet$shiftTimings();
    }

    public void setShiftTimings(String shiftTimings) {
        realmSet$shiftTimings(shiftTimings);
    }

    public String getTimeZone() {
        return realmGet$timeZone();
    }

    public void setTimeZone(String timeZone) {
        realmSet$timeZone(timeZone);
    }

    public String getDateOfBirth() {
        return realmGet$dateOfBirth();
    }

    public void setDateOfBirth(String dateOfBirth) {
        realmSet$dateOfBirth(dateOfBirth);
    }

    public User email(String email) {
        realmSet$email(email);
        return this;
    }

    public String getEmail() {
        return realmGet$email();
    }

    public void setEmail(String email) {
        realmSet$email(email);
    }

    public User password(String password) {
        realmSet$password(password);
        return this;
    }

    public String getPassword() {
        return realmGet$password();
    }

    public void setPassword(String password) {
        realmSet$password(password);
    }

    public User authenticationProvider(AuthenticationProviderEnum authenticationProvider) {
        this.authenticationProvide = authenticationProvider;
        return this;
    }

    public AuthenticationProviderEnum getAuthenticationProvide() {
        return AuthenticationProviderEnum.valueOf(getAuthenticationProviderValue());
    }

    public void setAuthenticationProvide(AuthenticationProviderEnum authenticationProvide) {
        setAuthenticationProviderValue(authenticationProvide.toString());
    }

    public String getAuthenticationProviderValue() {
        return realmGet$authenticationProviderValue();
    }

    public void setAuthenticationProviderValue(String authenticationProvider) {
        realmSet$authenticationProviderValue(authenticationProvider);
    }

    public User country(String country) {
        realmSet$country(country);
        return this;
    }

    public String getCountry() {
        return realmGet$country();
    }

    public void setCountry(String country) {
        realmSet$country(country);
    }

    public User mobile(String mobile) {
        realmSet$mobile(mobile);
        return this;
    }

    public String getMobile() {
        return realmGet$mobile();
    }

    public void setMobile(String mobile) {
        realmSet$mobile(mobile);
    }

    public User gender(String gender) {
        realmSet$gender(gender);
        return this;
    }

    public String getGender() {
        return realmGet$gender();
    }

    public void setGender(String gender) {
        realmSet$gender(gender);
    }

    public User tenantId(String tenantId) {
        realmSet$tenantId(tenantId);
        return this;
    }

    public String getTenantId() {
        return realmGet$tenantId();
    }

    public void setTenantId(String tenantId) {
        realmSet$tenantId(tenantId);
    }

    public User userSettings(UserSettings userSettings) {
        realmSet$userSettings(userSettings);
        return this;
    }

    public UserSettings getUserSettings() {
        return realmGet$userSettings();
    }

    public void setUserSettings(UserSettings userSettings) {
        realmSet$userSettings(userSettings);
    }

    public User publicId(Long publicId) {
        realmSet$publicId(publicId);
        return this;
    }

    public Long getPublicId() {
        return realmGet$publicId();
    }

    public void setPublicId(Long publicId) {
        realmSet$publicId(publicId);
    }

    public User groupId(String groupId) {
        realmSet$groupId(groupId);
        return this;
    }

    public String getGroupId() {
        return realmGet$groupId();
    }

    public void setGroupId(String groupId) {
        realmSet$groupId(groupId);
    }

    public User type(String type) {
        realmSet$type(type);
        return this;
    }

    public String getType() {
        return realmGet$type();
    }

    public void setType(String type) {
        realmSet$type(type);
    }

    public User caretakerId(String caretakerId) {
        realmSet$caretakerId(caretakerId);
        return this;
    }

    public String getCaretakerId() {
        return realmGet$caretakerId();
    }

    public void setCaretakerId(String caretakerId) {
        realmSet$caretakerId(caretakerId);
    }

    public User alarmId(String alarmId) {
        realmSet$alarmId(alarmId);
        return this;
    }

    public String getAlarmId() {
        return realmGet$alarmId();
    }

    public void setAlarmId(String alarmId) {
        realmSet$alarmId(alarmId);
    }

    public User allergires(RealmList<Allergy> allergires) {
        realmSet$allergires(allergires);
        return this;
    }

    public User addAllergiresItem(Allergy allergiresItem) {
        realmGet$allergires().add((RealmModel) allergiresItem);
        return this;
    }

    public RealmList<Allergy> getAllergires() {
        return realmGet$allergires();
    }

    public void setAllergires(RealmList<Allergy> allergires) {
        realmSet$allergires(allergires);
    }

    public User passwordResetEnabled(Boolean passwordResetEnabled) {
        realmSet$passwordResetEnabled(passwordResetEnabled);
        return this;
    }

    public Boolean getPasswordResetEnabled() {
        return realmGet$passwordResetEnabled();
    }

    public void setPasswordResetEnabled(Boolean passwordResetEnabled) {
        realmSet$passwordResetEnabled(passwordResetEnabled);
    }

    public User oauthId(String oauthId) {
        realmSet$oauthId(oauthId);
        return this;
    }

    public String getOauthId() {
        return realmGet$oauthId();
    }

    public void setOauthId(String oauthId) {
        realmSet$oauthId(oauthId);
    }

    public User oauthToken(String oauthToken) {
        realmSet$oauthToken(oauthToken);
        return this;
    }

    public String getOauthToken() {
        return realmGet$oauthToken();
    }

    public void setOauthToken(String oauthToken) {
        realmSet$oauthToken(oauthToken);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class User {\n");
        sb.append("    id: ").append(toIndentedString(realmGet$id())).append("\n");
        sb.append("    firstName: ").append(toIndentedString(realmGet$firstName())).append("\n");
        sb.append("    lastName: ").append(toIndentedString(realmGet$lastName())).append("\n");
        sb.append("    dateOfBirth: ").append(toIndentedString(realmGet$dateOfBirth())).append("\n");
        sb.append("    email: ").append(toIndentedString(realmGet$email())).append("\n");
        sb.append("    password: ").append(toIndentedString(realmGet$password())).append("\n");
        sb.append("    authenticationProvide: ").append(toIndentedString(realmGet$authenticationProviderValue())).append("\n");
        sb.append("    country: ").append(toIndentedString(realmGet$country())).append("\n");
        sb.append("    mobile: ").append(toIndentedString(realmGet$mobile())).append("\n");
        sb.append("    gender: ").append(toIndentedString(realmGet$gender())).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(realmGet$tenantId())).append("\n");
        sb.append("    userSettings: ").append(toIndentedString(realmGet$userSettings())).append("\n");
        sb.append("    publicId: ").append(toIndentedString(realmGet$publicId())).append("\n");
        sb.append("    groupId: ").append(toIndentedString(realmGet$groupId())).append("\n");
        sb.append("    type: ").append(toIndentedString(realmGet$type())).append("\n");
        sb.append("    profilePhotoId: ").append(toIndentedString(realmGet$profilePhotoId())).append("\n");
        sb.append("    caretakerId: ").append(toIndentedString(realmGet$caretakerId())).append("\n");
        sb.append("    alarmId: ").append(toIndentedString(realmGet$alarmId())).append("\n");
        sb.append("    allergires: ").append(toIndentedString(realmGet$allergires())).append("\n");
        sb.append("    passwordResetEnabled: ").append(toIndentedString(realmGet$passwordResetEnabled())).append("\n");
        sb.append("    oauthId: ").append(toIndentedString(realmGet$oauthId())).append("\n");
        sb.append("    oauthToken: ").append(toIndentedString(realmGet$oauthToken())).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(realmGet$id());
        dest.writeString(realmGet$firstName());
        dest.writeString(realmGet$lastName());
        dest.writeString(realmGet$country());
        dest.writeString(realmGet$dateOfBirth());
        dest.writeString(realmGet$gender());
        dest.writeString(realmGet$mobile());
        dest.writeString(realmGet$email());
        dest.writeString(realmGet$type());
    }
}
