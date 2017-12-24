package com.cooey.common.vo;

import android.databinding.Observable;
import android.databinding.Observable.OnPropertyChangedCallback;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import io.realm.GuardianRealmProxyInterface;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

public class Guardian extends RealmObject implements Parcelable, Observable, GuardianRealmProxyInterface {
    public static final Creator CREATOR = new 1();
    @SerializedName("alarmId")
    private String alarmId;
    @SerializedName("allergires")
    private RealmList<Allergy> allergires;
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
    private String patientId;
    @SerializedName("publicId")
    private Long publicId;
    @SerializedName("room")
    private String room;
    @SerializedName("tenantId")
    private String tenantId;
    @SerializedName("type")
    private String type;
    @SerializedName("userSettings")
    private UserSettings userSettings;
    @SerializedName("waistSize")
    private WaistSize waistSize;

    public String realmGet$alarmId() {
        return this.alarmId;
    }

    public RealmList realmGet$allergires() {
        return this.allergires;
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

    public String realmGet$patientId() {
        return this.patientId;
    }

    public Long realmGet$publicId() {
        return this.publicId;
    }

    public String realmGet$room() {
        return this.room;
    }

    public String realmGet$tenantId() {
        return this.tenantId;
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

    public void realmSet$alarmId(String str) {
        this.alarmId = str;
    }

    public void realmSet$allergires(RealmList realmList) {
        this.allergires = realmList;
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

    public void realmSet$patientId(String str) {
        this.patientId = str;
    }

    public void realmSet$publicId(Long l) {
        this.publicId = l;
    }

    public void realmSet$room(String str) {
        this.room = str;
    }

    public void realmSet$tenantId(String str) {
        this.tenantId = str;
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

    public static String toJson(Guardian user) {
        return new GsonBuilder().create().toJson(user);
    }

    public static Guardian fromJson(String userJson) {
        return (Guardian) new GsonBuilder().create().fromJson(userJson, Guardian.class);
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

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public String getPatientId() {
        return realmGet$patientId();
    }

    public void setPatientId(String patientId) {
        realmSet$patientId(patientId);
    }

    public static Creator getCREATOR() {
        return CREATOR;
    }

    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
    }

    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
    }

    public Guardian id(String id) {
        realmSet$id(id);
        return this;
    }

    public Guardian(Parcel in) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$id(null);
        realmSet$firstName(null);
        realmSet$lastName(null);
        realmSet$dateOfBirth(null);
        realmSet$email(null);
        realmSet$password(null);
        realmSet$country(null);
        realmSet$mobile(null);
        realmSet$gender(null);
        realmSet$tenantId(null);
        realmSet$userSettings(null);
        realmSet$publicId(null);
        realmSet$groupId(null);
        realmSet$type(null);
        realmSet$caretakerId(null);
        realmSet$alarmId(null);
        realmSet$allergires(new RealmList());
        realmSet$passwordResetEnabled(Boolean.valueOf(false));
        realmSet$oauthId(null);
        realmSet$oauthToken(null);
        realmSet$id(in.readString());
        realmSet$firstName(in.readString());
        realmSet$lastName(in.readString());
        realmSet$country(in.readString());
        realmSet$dateOfBirth(in.readString());
        realmSet$email(in.readString());
        realmSet$gender(in.readString());
        realmSet$mobile(in.readString());
    }

    public Guardian() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$id(null);
        realmSet$firstName(null);
        realmSet$lastName(null);
        realmSet$dateOfBirth(null);
        realmSet$email(null);
        realmSet$password(null);
        realmSet$country(null);
        realmSet$mobile(null);
        realmSet$gender(null);
        realmSet$tenantId(null);
        realmSet$userSettings(null);
        realmSet$publicId(null);
        realmSet$groupId(null);
        realmSet$type(null);
        realmSet$caretakerId(null);
        realmSet$alarmId(null);
        realmSet$allergires(new RealmList());
        realmSet$passwordResetEnabled(Boolean.valueOf(false));
        realmSet$oauthId(null);
        realmSet$oauthToken(null);
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

    public Guardian firstName(String firstName) {
        realmSet$firstName(firstName);
        return this;
    }

    public String getFirstName() {
        return realmGet$firstName();
    }

    public void setFirstName(String firstName) {
        realmSet$firstName(firstName);
    }

    public Guardian lastName(String lastName) {
        realmSet$lastName(lastName);
        return this;
    }

    public String getLastName() {
        return realmGet$lastName();
    }

    public void setLastName(String lastName) {
        realmSet$lastName(lastName);
    }

    public Guardian dateOfBirth(String dateOfBirth) {
        realmSet$dateOfBirth(dateOfBirth);
        return this;
    }

    public String getDateOfBirth() {
        return realmGet$dateOfBirth();
    }

    public void setDateOfBirth(String dateOfBirth) {
        realmSet$dateOfBirth(dateOfBirth);
    }

    public Guardian email(String email) {
        realmSet$email(email);
        return this;
    }

    public String getEmail() {
        return realmGet$email();
    }

    public void setEmail(String email) {
        realmSet$email(email);
    }

    public Guardian password(String password) {
        realmSet$password(password);
        return this;
    }

    public String getPassword() {
        return realmGet$password();
    }

    public void setPassword(String password) {
        realmSet$password(password);
    }

    public Guardian country(String country) {
        realmSet$country(country);
        return this;
    }

    public String getCountry() {
        return realmGet$country();
    }

    public void setCountry(String country) {
        realmSet$country(country);
    }

    public Guardian mobile(String mobile) {
        realmSet$mobile(mobile);
        return this;
    }

    public String getMobile() {
        return realmGet$mobile();
    }

    public void setMobile(String mobile) {
        realmSet$mobile(mobile);
    }

    public Guardian gender(String gender) {
        realmSet$gender(gender);
        return this;
    }

    public String getGender() {
        return realmGet$gender();
    }

    public void setGender(String gender) {
        realmSet$gender(gender);
    }

    public Guardian tenantId(String tenantId) {
        realmSet$tenantId(tenantId);
        return this;
    }

    public String getTenantId() {
        return realmGet$tenantId();
    }

    public void setTenantId(String tenantId) {
        realmSet$tenantId(tenantId);
    }

    public Guardian userSettings(UserSettings userSettings) {
        realmSet$userSettings(userSettings);
        return this;
    }

    public UserSettings getUserSettings() {
        return realmGet$userSettings();
    }

    public void setUserSettings(UserSettings userSettings) {
        realmSet$userSettings(userSettings);
    }

    public Guardian publicId(Long publicId) {
        realmSet$publicId(publicId);
        return this;
    }

    public Long getPublicId() {
        return realmGet$publicId();
    }

    public void setPublicId(Long publicId) {
        realmSet$publicId(publicId);
    }

    public Guardian groupId(String groupId) {
        realmSet$groupId(groupId);
        return this;
    }

    public String getGroupId() {
        return realmGet$groupId();
    }

    public void setGroupId(String groupId) {
        realmSet$groupId(groupId);
    }

    public Guardian type(String type) {
        realmSet$type(type);
        return this;
    }

    public String getType() {
        return realmGet$type();
    }

    public void setType(String type) {
        realmSet$type(type);
    }

    public Guardian caretakerId(String caretakerId) {
        realmSet$caretakerId(caretakerId);
        return this;
    }

    public String getCaretakerId() {
        return realmGet$caretakerId();
    }

    public void setCaretakerId(String caretakerId) {
        realmSet$caretakerId(caretakerId);
    }

    public Guardian alarmId(String alarmId) {
        realmSet$alarmId(alarmId);
        return this;
    }

    public String getAlarmId() {
        return realmGet$alarmId();
    }

    public void setAlarmId(String alarmId) {
        realmSet$alarmId(alarmId);
    }

    public Guardian allergires(RealmList<Allergy> allergires) {
        realmSet$allergires(allergires);
        return this;
    }

    public Guardian addAllergiresItem(Allergy allergiresItem) {
        realmGet$allergires().add((RealmModel) allergiresItem);
        return this;
    }

    public RealmList<Allergy> getAllergires() {
        return realmGet$allergires();
    }

    public void setAllergires(RealmList<Allergy> allergires) {
        realmSet$allergires(allergires);
    }

    public Guardian passwordResetEnabled(Boolean passwordResetEnabled) {
        realmSet$passwordResetEnabled(passwordResetEnabled);
        return this;
    }

    public Boolean getPasswordResetEnabled() {
        return realmGet$passwordResetEnabled();
    }

    public void setPasswordResetEnabled(Boolean passwordResetEnabled) {
        realmSet$passwordResetEnabled(passwordResetEnabled);
    }

    public Guardian oauthId(String oauthId) {
        realmSet$oauthId(oauthId);
        return this;
    }

    public String getOauthId() {
        return realmGet$oauthId();
    }

    public void setOauthId(String oauthId) {
        realmSet$oauthId(oauthId);
    }

    public Guardian oauthToken(String oauthToken) {
        realmSet$oauthToken(oauthToken);
        return this;
    }

    public String getOauthToken() {
        return realmGet$oauthToken();
    }

    public void setOauthToken(String oauthToken) {
        realmSet$oauthToken(oauthToken);
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
        dest.writeString(realmGet$email());
        dest.writeString(realmGet$gender());
        dest.writeString(realmGet$mobile());
    }
}
