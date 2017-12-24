package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AndroidConfig {
    @SerializedName("colorConfiguration")
    private ColorConfiguration colorConfiguration = null;
    @SerializedName("description")
    private String description = null;
    @SerializedName("firebaseConfig")
    private String firebaseConfig = null;
    @SerializedName("iconImageUrl")
    private String iconImageUrl = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("packageName")
    private String packageName = null;
    @SerializedName("splashImageUrl")
    private String splashImageUrl = null;
    @SerializedName("storeUrl")
    private String storeUrl = null;
    @SerializedName("version")
    private Integer version = null;
    @SerializedName("versionName")
    private String versionName = null;
    @SerializedName("vitalFeatures")
    private List<VitalsFeaturesConfig> vitalFeatures = null;

    public AndroidConfig name(String name) {
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

    public AndroidConfig firebaseConfig(String firebaseConfig) {
        this.firebaseConfig = firebaseConfig;
        return this;
    }

    @ApiModelProperty("")
    public String getFirebaseConfig() {
        return this.firebaseConfig;
    }

    public void setFirebaseConfig(String firebaseConfig) {
        this.firebaseConfig = firebaseConfig;
    }

    public AndroidConfig iconImageUrl(String iconImageUrl) {
        this.iconImageUrl = iconImageUrl;
        return this;
    }

    @ApiModelProperty("")
    public String getIconImageUrl() {
        return this.iconImageUrl;
    }

    public void setIconImageUrl(String iconImageUrl) {
        this.iconImageUrl = iconImageUrl;
    }

    public AndroidConfig description(String description) {
        this.description = description;
        return this;
    }

    @ApiModelProperty("")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AndroidConfig colorConfiguration(ColorConfiguration colorConfiguration) {
        this.colorConfiguration = colorConfiguration;
        return this;
    }

    @ApiModelProperty("")
    public ColorConfiguration getColorConfiguration() {
        return this.colorConfiguration;
    }

    public void setColorConfiguration(ColorConfiguration colorConfiguration) {
        this.colorConfiguration = colorConfiguration;
    }

    public AndroidConfig storeUrl(String storeUrl) {
        this.storeUrl = storeUrl;
        return this;
    }

    @ApiModelProperty("")
    public String getStoreUrl() {
        return this.storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public AndroidConfig splashImageUrl(String splashImageUrl) {
        this.splashImageUrl = splashImageUrl;
        return this;
    }

    @ApiModelProperty("")
    public String getSplashImageUrl() {
        return this.splashImageUrl;
    }

    public void setSplashImageUrl(String splashImageUrl) {
        this.splashImageUrl = splashImageUrl;
    }

    public AndroidConfig vitalFeatures(List<VitalsFeaturesConfig> vitalFeatures) {
        this.vitalFeatures = vitalFeatures;
        return this;
    }

    public AndroidConfig addVitalFeaturesItem(VitalsFeaturesConfig vitalFeaturesItem) {
        if (this.vitalFeatures == null) {
            this.vitalFeatures = new ArrayList();
        }
        this.vitalFeatures.add(vitalFeaturesItem);
        return this;
    }

    @ApiModelProperty("")
    public List<VitalsFeaturesConfig> getVitalFeatures() {
        return this.vitalFeatures;
    }

    public void setVitalFeatures(List<VitalsFeaturesConfig> vitalFeatures) {
        this.vitalFeatures = vitalFeatures;
    }

    public AndroidConfig packageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    @ApiModelProperty("")
    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public AndroidConfig versionName(String versionName) {
        this.versionName = versionName;
        return this;
    }

    @ApiModelProperty("")
    public String getVersionName() {
        return this.versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public AndroidConfig version(Integer version) {
        this.version = version;
        return this;
    }

    @ApiModelProperty("")
    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AndroidConfig androidConfig = (AndroidConfig) o;
        if (Objects.equals(this.name, androidConfig.name) && Objects.equals(this.firebaseConfig, androidConfig.firebaseConfig) && Objects.equals(this.iconImageUrl, androidConfig.iconImageUrl) && Objects.equals(this.description, androidConfig.description) && Objects.equals(this.colorConfiguration, androidConfig.colorConfiguration) && Objects.equals(this.storeUrl, androidConfig.storeUrl) && Objects.equals(this.splashImageUrl, androidConfig.splashImageUrl) && Objects.equals(this.vitalFeatures, androidConfig.vitalFeatures) && Objects.equals(this.packageName, androidConfig.packageName) && Objects.equals(this.versionName, androidConfig.versionName) && Objects.equals(this.version, androidConfig.version)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.firebaseConfig, this.iconImageUrl, this.description, this.colorConfiguration, this.storeUrl, this.splashImageUrl, this.vitalFeatures, this.packageName, this.versionName, this.version});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AndroidConfig {\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    firebaseConfig: ").append(toIndentedString(this.firebaseConfig)).append("\n");
        sb.append("    iconImageUrl: ").append(toIndentedString(this.iconImageUrl)).append("\n");
        sb.append("    description: ").append(toIndentedString(this.description)).append("\n");
        sb.append("    colorConfiguration: ").append(toIndentedString(this.colorConfiguration)).append("\n");
        sb.append("    storeUrl: ").append(toIndentedString(this.storeUrl)).append("\n");
        sb.append("    splashImageUrl: ").append(toIndentedString(this.splashImageUrl)).append("\n");
        sb.append("    vitalFeatures: ").append(toIndentedString(this.vitalFeatures)).append("\n");
        sb.append("    packageName: ").append(toIndentedString(this.packageName)).append("\n");
        sb.append("    versionName: ").append(toIndentedString(this.versionName)).append("\n");
        sb.append("    version: ").append(toIndentedString(this.version)).append("\n");
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
