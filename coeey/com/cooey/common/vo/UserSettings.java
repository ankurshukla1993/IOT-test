package com.cooey.common.vo;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.UserSettingsRealmProxyInterface;
import io.realm.annotations.Ignore;
import io.realm.internal.RealmObjectProxy;

public class UserSettings extends RealmObject implements UserSettingsRealmProxyInterface {
    @Ignore
    private transient DefaultNotificationModeEnum defaultNotificationModeEnum;
    @SerializedName("defaultNotificationMode")
    private String defaultNotificationModeValue;
    private boolean eventNotificationEnabled;
    @SerializedName("featureList")
    private transient RealmList<UserFeature> featureList;
    @SerializedName("notificationEnabled")
    private Boolean notificationEnabled;
    private boolean vitalLimitNotificationEnabled;
    @SerializedName("vitalLimits")
    private RealmList<Limit> vitalLimits;
    private boolean vitalNotificationEnabled;

    public enum DefaultNotificationModeEnum {
        PUSH_NOTIFICATION("PUSH_NOTIFICATION"),
        PHONE("PHONE"),
        EMAIL("EMAIL");
        
        private String value;

        private DefaultNotificationModeEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }
    }

    public String realmGet$defaultNotificationModeValue() {
        return this.defaultNotificationModeValue;
    }

    public boolean realmGet$eventNotificationEnabled() {
        return this.eventNotificationEnabled;
    }

    public Boolean realmGet$notificationEnabled() {
        return this.notificationEnabled;
    }

    public boolean realmGet$vitalLimitNotificationEnabled() {
        return this.vitalLimitNotificationEnabled;
    }

    public RealmList realmGet$vitalLimits() {
        return this.vitalLimits;
    }

    public boolean realmGet$vitalNotificationEnabled() {
        return this.vitalNotificationEnabled;
    }

    public void realmSet$defaultNotificationModeValue(String str) {
        this.defaultNotificationModeValue = str;
    }

    public void realmSet$eventNotificationEnabled(boolean z) {
        this.eventNotificationEnabled = z;
    }

    public void realmSet$notificationEnabled(Boolean bool) {
        this.notificationEnabled = bool;
    }

    public void realmSet$vitalLimitNotificationEnabled(boolean z) {
        this.vitalLimitNotificationEnabled = z;
    }

    public void realmSet$vitalLimits(RealmList realmList) {
        this.vitalLimits = realmList;
    }

    public void realmSet$vitalNotificationEnabled(boolean z) {
        this.vitalNotificationEnabled = z;
    }

    public UserSettings() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$vitalLimits(new RealmList());
        this.featureList = new RealmList();
        realmSet$notificationEnabled(Boolean.valueOf(false));
    }

    public UserSettings vitalLimits(RealmList<Limit> vitalLimits) {
        realmSet$vitalLimits(vitalLimits);
        return this;
    }

    public UserSettings addVitalLimitsItem(Limit vitalLimitsItem) {
        realmGet$vitalLimits().add((RealmModel) vitalLimitsItem);
        return this;
    }

    public RealmList<Limit> getVitalLimits() {
        return realmGet$vitalLimits();
    }

    public void setVitalLimits(RealmList<Limit> vitalLimits) {
        realmSet$vitalLimits(vitalLimits);
    }

    public RealmList<UserFeature> getFeatureList() {
        return this.featureList;
    }

    public void setFeatureList(RealmList<UserFeature> featureList) {
        this.featureList = featureList;
    }

    public UserSettings defaultNotificationMode(DefaultNotificationModeEnum defaultNotificationMode) {
        this.defaultNotificationModeEnum = defaultNotificationMode;
        return this;
    }

    public DefaultNotificationModeEnum getDefaultNotificationModeEnum() {
        return DefaultNotificationModeEnum.valueOf(getDefaultNotificationModeValue());
    }

    public void setDefaultNotificationModeEnum(DefaultNotificationModeEnum defaultNotificationModeEnum) {
        setDefaultNotificationModeValue(defaultNotificationModeEnum.value);
    }

    public String getDefaultNotificationModeValue() {
        return realmGet$defaultNotificationModeValue();
    }

    public void setDefaultNotificationModeValue(String defaultNotificationModeValue) {
        realmSet$defaultNotificationModeValue(defaultNotificationModeValue);
    }

    public UserSettings notificationEnabled(Boolean notificationEnabled) {
        realmSet$notificationEnabled(notificationEnabled);
        return this;
    }

    public Boolean getNotificationEnabled() {
        return realmGet$notificationEnabled();
    }

    public void setNotificationEnabled(Boolean notificationEnabled) {
        realmSet$notificationEnabled(notificationEnabled);
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserSettings {\n");
        sb.append("    vitalLimits: ").append(toIndentedString(realmGet$vitalLimits())).append("\n");
        sb.append("    defaultNotificationMode: ").append(toIndentedString(this.defaultNotificationModeEnum)).append("\n");
        sb.append("    notificationEnabled: ").append(toIndentedString(realmGet$notificationEnabled())).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public boolean isVitalNotificationEnabled() {
        return realmGet$vitalNotificationEnabled();
    }

    public void setVitalNotificationEnabled(boolean vitalNotificationEnabled) {
        realmSet$vitalNotificationEnabled(vitalNotificationEnabled);
    }

    public boolean isVitalLimitNotificationEnabled() {
        return realmGet$vitalLimitNotificationEnabled();
    }

    public void setVitalLimitNotificationEnabled(boolean vitalLimitNotificationEnabled) {
        realmSet$vitalLimitNotificationEnabled(vitalLimitNotificationEnabled);
    }

    public boolean isEventNotificationEnabled() {
        return realmGet$eventNotificationEnabled();
    }

    public void setEventNotificationEnabled(boolean eventNotificationEnabled) {
        realmSet$eventNotificationEnabled(eventNotificationEnabled);
    }
}
