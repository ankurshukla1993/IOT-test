package com.cooey.api.client.models;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserSettings {
    @SerializedName("defaultNotificationMode")
    private DefaultNotificationModeEnum defaultNotificationMode = null;
    @SerializedName("eventNotificationEnabled")
    private Boolean eventNotificationEnabled = Boolean.valueOf(false);
    @SerializedName("featureList")
    private Map<String, Boolean> featureList = null;
    @SerializedName("notificationEnabled")
    private Boolean notificationEnabled = Boolean.valueOf(false);
    @SerializedName("vitalLimitNotificationEnabled")
    private Boolean vitalLimitNotificationEnabled = Boolean.valueOf(false);
    @SerializedName("vitalLimits")
    private List<Limit> vitalLimits = null;
    @SerializedName("vitalNotificationEnabled")
    private Boolean vitalNotificationEnabled = Boolean.valueOf(false);

    @JsonAdapter(Adapter.class)
    public enum DefaultNotificationModeEnum {
        PUSH_NOTIFICATION("PUSH_NOTIFICATION"),
        PHONE("PHONE"),
        EMAIL("EMAIL");
        
        private String value;

        private DefaultNotificationModeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }

        public static DefaultNotificationModeEnum fromValue(String text) {
            for (DefaultNotificationModeEnum b : values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    public UserSettings vitalLimits(List<Limit> vitalLimits) {
        this.vitalLimits = vitalLimits;
        return this;
    }

    public UserSettings addVitalLimitsItem(Limit vitalLimitsItem) {
        if (this.vitalLimits == null) {
            this.vitalLimits = new ArrayList();
        }
        this.vitalLimits.add(vitalLimitsItem);
        return this;
    }

    @ApiModelProperty("")
    public List<Limit> getVitalLimits() {
        return this.vitalLimits;
    }

    public void setVitalLimits(List<Limit> vitalLimits) {
        this.vitalLimits = vitalLimits;
    }

    public UserSettings featureList(Map<String, Boolean> featureList) {
        this.featureList = featureList;
        return this;
    }

    public UserSettings putFeatureListItem(String key, Boolean featureListItem) {
        if (this.featureList == null) {
            this.featureList = new HashMap();
        }
        this.featureList.put(key, featureListItem);
        return this;
    }

    @ApiModelProperty("")
    public Map<String, Boolean> getFeatureList() {
        return this.featureList;
    }

    public void setFeatureList(Map<String, Boolean> featureList) {
        this.featureList = featureList;
    }

    public UserSettings defaultNotificationMode(DefaultNotificationModeEnum defaultNotificationMode) {
        this.defaultNotificationMode = defaultNotificationMode;
        return this;
    }

    @ApiModelProperty("")
    public DefaultNotificationModeEnum getDefaultNotificationMode() {
        return this.defaultNotificationMode;
    }

    public void setDefaultNotificationMode(DefaultNotificationModeEnum defaultNotificationMode) {
        this.defaultNotificationMode = defaultNotificationMode;
    }

    public UserSettings vitalNotificationEnabled(Boolean vitalNotificationEnabled) {
        this.vitalNotificationEnabled = vitalNotificationEnabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getVitalNotificationEnabled() {
        return this.vitalNotificationEnabled;
    }

    public void setVitalNotificationEnabled(Boolean vitalNotificationEnabled) {
        this.vitalNotificationEnabled = vitalNotificationEnabled;
    }

    public UserSettings vitalLimitNotificationEnabled(Boolean vitalLimitNotificationEnabled) {
        this.vitalLimitNotificationEnabled = vitalLimitNotificationEnabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getVitalLimitNotificationEnabled() {
        return this.vitalLimitNotificationEnabled;
    }

    public void setVitalLimitNotificationEnabled(Boolean vitalLimitNotificationEnabled) {
        this.vitalLimitNotificationEnabled = vitalLimitNotificationEnabled;
    }

    public UserSettings eventNotificationEnabled(Boolean eventNotificationEnabled) {
        this.eventNotificationEnabled = eventNotificationEnabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getEventNotificationEnabled() {
        return this.eventNotificationEnabled;
    }

    public void setEventNotificationEnabled(Boolean eventNotificationEnabled) {
        this.eventNotificationEnabled = eventNotificationEnabled;
    }

    public UserSettings notificationEnabled(Boolean notificationEnabled) {
        this.notificationEnabled = notificationEnabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getNotificationEnabled() {
        return this.notificationEnabled;
    }

    public void setNotificationEnabled(Boolean notificationEnabled) {
        this.notificationEnabled = notificationEnabled;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserSettings userSettings = (UserSettings) o;
        if (Objects.equals(this.vitalLimits, userSettings.vitalLimits) && Objects.equals(this.featureList, userSettings.featureList) && Objects.equals(this.defaultNotificationMode, userSettings.defaultNotificationMode) && Objects.equals(this.vitalNotificationEnabled, userSettings.vitalNotificationEnabled) && Objects.equals(this.vitalLimitNotificationEnabled, userSettings.vitalLimitNotificationEnabled) && Objects.equals(this.eventNotificationEnabled, userSettings.eventNotificationEnabled) && Objects.equals(this.notificationEnabled, userSettings.notificationEnabled)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.vitalLimits, this.featureList, this.defaultNotificationMode, this.vitalNotificationEnabled, this.vitalLimitNotificationEnabled, this.eventNotificationEnabled, this.notificationEnabled});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserSettings {\n");
        sb.append("    vitalLimits: ").append(toIndentedString(this.vitalLimits)).append("\n");
        sb.append("    featureList: ").append(toIndentedString(this.featureList)).append("\n");
        sb.append("    defaultNotificationMode: ").append(toIndentedString(this.defaultNotificationMode)).append("\n");
        sb.append("    vitalNotificationEnabled: ").append(toIndentedString(this.vitalNotificationEnabled)).append("\n");
        sb.append("    vitalLimitNotificationEnabled: ").append(toIndentedString(this.vitalLimitNotificationEnabled)).append("\n");
        sb.append("    eventNotificationEnabled: ").append(toIndentedString(this.eventNotificationEnabled)).append("\n");
        sb.append("    notificationEnabled: ").append(toIndentedString(this.notificationEnabled)).append("\n");
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
