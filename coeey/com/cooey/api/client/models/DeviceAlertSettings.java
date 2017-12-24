package com.cooey.api.client.models;

import com.cooey.android.users.old.utils.CTConstants;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class DeviceAlertSettings {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("deviceActivityPeriodSettings")
    private DeviceActivityPeriodSettings deviceActivityPeriodSettings = null;
    @SerializedName("deviceSettingLevel")
    private DeviceSettingLevelEnum deviceSettingLevel = null;
    @SerializedName("deviceTemperatureLevelSettings")
    private DeviceTemperatureLevelSettings deviceTemperatureLevelSettings = null;
    @SerializedName("enableGPS")
    private Boolean enableGPS = Boolean.valueOf(false);
    @SerializedName("gpsAlertSettings")
    private GPSAlertSettings gpsAlertSettings = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("patientId")
    private String patientId = null;
    @SerializedName("serverConnectionFrequency")
    private ServerConnectionFrequency serverConnectionFrequency = null;
    @SerializedName("temperatureAlertSettings")
    private TemperatureAlertSettings temperatureAlertSettings = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("timestamp")
    private Long timestamp = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;

    @JsonAdapter(Adapter.class)
    public enum DeviceSettingLevelEnum {
        TENANT(CTConstants.TENANT),
        ZONE("ZONE"),
        SUBZONE("SUBZONE"),
        PATIENT(CTConstants.PATIENTTYPE);
        
        private String value;

        private DeviceSettingLevelEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }

        public static DeviceSettingLevelEnum fromValue(String text) {
            for (DeviceSettingLevelEnum b : values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    public DeviceAlertSettings createdOn(Long createdOn) {
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

    public DeviceAlertSettings updatedOn(Long updatedOn) {
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

    public DeviceAlertSettings tenantId(String tenantId) {
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

    public DeviceAlertSettings applicationId(String applicationId) {
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

    public DeviceAlertSettings id(String id) {
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

    public DeviceAlertSettings deviceSettingLevel(DeviceSettingLevelEnum deviceSettingLevel) {
        this.deviceSettingLevel = deviceSettingLevel;
        return this;
    }

    @ApiModelProperty("")
    public DeviceSettingLevelEnum getDeviceSettingLevel() {
        return this.deviceSettingLevel;
    }

    public void setDeviceSettingLevel(DeviceSettingLevelEnum deviceSettingLevel) {
        this.deviceSettingLevel = deviceSettingLevel;
    }

    public DeviceAlertSettings patientId(String patientId) {
        this.patientId = patientId;
        return this;
    }

    @ApiModelProperty("")
    public String getPatientId() {
        return this.patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public DeviceAlertSettings gpsAlertSettings(GPSAlertSettings gpsAlertSettings) {
        this.gpsAlertSettings = gpsAlertSettings;
        return this;
    }

    @ApiModelProperty("")
    public GPSAlertSettings getGpsAlertSettings() {
        return this.gpsAlertSettings;
    }

    public void setGpsAlertSettings(GPSAlertSettings gpsAlertSettings) {
        this.gpsAlertSettings = gpsAlertSettings;
    }

    public DeviceAlertSettings temperatureAlertSettings(TemperatureAlertSettings temperatureAlertSettings) {
        this.temperatureAlertSettings = temperatureAlertSettings;
        return this;
    }

    @ApiModelProperty("")
    public TemperatureAlertSettings getTemperatureAlertSettings() {
        return this.temperatureAlertSettings;
    }

    public void setTemperatureAlertSettings(TemperatureAlertSettings temperatureAlertSettings) {
        this.temperatureAlertSettings = temperatureAlertSettings;
    }

    public DeviceAlertSettings serverConnectionFrequency(ServerConnectionFrequency serverConnectionFrequency) {
        this.serverConnectionFrequency = serverConnectionFrequency;
        return this;
    }

    @ApiModelProperty("")
    public ServerConnectionFrequency getServerConnectionFrequency() {
        return this.serverConnectionFrequency;
    }

    public void setServerConnectionFrequency(ServerConnectionFrequency serverConnectionFrequency) {
        this.serverConnectionFrequency = serverConnectionFrequency;
    }

    public DeviceAlertSettings deviceActivityPeriodSettings(DeviceActivityPeriodSettings deviceActivityPeriodSettings) {
        this.deviceActivityPeriodSettings = deviceActivityPeriodSettings;
        return this;
    }

    @ApiModelProperty("")
    public DeviceActivityPeriodSettings getDeviceActivityPeriodSettings() {
        return this.deviceActivityPeriodSettings;
    }

    public void setDeviceActivityPeriodSettings(DeviceActivityPeriodSettings deviceActivityPeriodSettings) {
        this.deviceActivityPeriodSettings = deviceActivityPeriodSettings;
    }

    public DeviceAlertSettings deviceTemperatureLevelSettings(DeviceTemperatureLevelSettings deviceTemperatureLevelSettings) {
        this.deviceTemperatureLevelSettings = deviceTemperatureLevelSettings;
        return this;
    }

    @ApiModelProperty("")
    public DeviceTemperatureLevelSettings getDeviceTemperatureLevelSettings() {
        return this.deviceTemperatureLevelSettings;
    }

    public void setDeviceTemperatureLevelSettings(DeviceTemperatureLevelSettings deviceTemperatureLevelSettings) {
        this.deviceTemperatureLevelSettings = deviceTemperatureLevelSettings;
    }

    public DeviceAlertSettings enableGPS(Boolean enableGPS) {
        this.enableGPS = enableGPS;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getEnableGPS() {
        return this.enableGPS;
    }

    public void setEnableGPS(Boolean enableGPS) {
        this.enableGPS = enableGPS;
    }

    public DeviceAlertSettings timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @ApiModelProperty("")
    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceAlertSettings deviceAlertSettings = (DeviceAlertSettings) o;
        if (Objects.equals(this.createdOn, deviceAlertSettings.createdOn) && Objects.equals(this.updatedOn, deviceAlertSettings.updatedOn) && Objects.equals(this.tenantId, deviceAlertSettings.tenantId) && Objects.equals(this.applicationId, deviceAlertSettings.applicationId) && Objects.equals(this.id, deviceAlertSettings.id) && Objects.equals(this.deviceSettingLevel, deviceAlertSettings.deviceSettingLevel) && Objects.equals(this.patientId, deviceAlertSettings.patientId) && Objects.equals(this.gpsAlertSettings, deviceAlertSettings.gpsAlertSettings) && Objects.equals(this.temperatureAlertSettings, deviceAlertSettings.temperatureAlertSettings) && Objects.equals(this.serverConnectionFrequency, deviceAlertSettings.serverConnectionFrequency) && Objects.equals(this.deviceActivityPeriodSettings, deviceAlertSettings.deviceActivityPeriodSettings) && Objects.equals(this.deviceTemperatureLevelSettings, deviceAlertSettings.deviceTemperatureLevelSettings) && Objects.equals(this.enableGPS, deviceAlertSettings.enableGPS) && Objects.equals(this.timestamp, deviceAlertSettings.timestamp)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.deviceSettingLevel, this.patientId, this.gpsAlertSettings, this.temperatureAlertSettings, this.serverConnectionFrequency, this.deviceActivityPeriodSettings, this.deviceTemperatureLevelSettings, this.enableGPS, this.timestamp});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeviceAlertSettings {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    deviceSettingLevel: ").append(toIndentedString(this.deviceSettingLevel)).append("\n");
        sb.append("    patientId: ").append(toIndentedString(this.patientId)).append("\n");
        sb.append("    gpsAlertSettings: ").append(toIndentedString(this.gpsAlertSettings)).append("\n");
        sb.append("    temperatureAlertSettings: ").append(toIndentedString(this.temperatureAlertSettings)).append("\n");
        sb.append("    serverConnectionFrequency: ").append(toIndentedString(this.serverConnectionFrequency)).append("\n");
        sb.append("    deviceActivityPeriodSettings: ").append(toIndentedString(this.deviceActivityPeriodSettings)).append("\n");
        sb.append("    deviceTemperatureLevelSettings: ").append(toIndentedString(this.deviceTemperatureLevelSettings)).append("\n");
        sb.append("    enableGPS: ").append(toIndentedString(this.enableGPS)).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(this.timestamp)).append("\n");
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
