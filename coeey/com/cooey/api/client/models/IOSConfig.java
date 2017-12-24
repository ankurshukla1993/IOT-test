package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IOSConfig {
    @SerializedName("bundleId")
    private String bundleId = null;
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
    @SerializedName("splashImageUrl")
    private String splashImageUrl = null;
    @SerializedName("storeUrl")
    private String storeUrl = null;
    @SerializedName("vitalFeatures")
    private List<VitalsFeaturesConfig> vitalFeatures = null;

    public IOSConfig name(String name) {
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

    public IOSConfig firebaseConfig(String firebaseConfig) {
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

    public IOSConfig iconImageUrl(String iconImageUrl) {
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

    public IOSConfig description(String description) {
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

    public IOSConfig colorConfiguration(ColorConfiguration colorConfiguration) {
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

    public IOSConfig storeUrl(String storeUrl) {
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

    public IOSConfig splashImageUrl(String splashImageUrl) {
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

    public IOSConfig vitalFeatures(List<VitalsFeaturesConfig> vitalFeatures) {
        this.vitalFeatures = vitalFeatures;
        return this;
    }

    public IOSConfig addVitalFeaturesItem(VitalsFeaturesConfig vitalFeaturesItem) {
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

    public IOSConfig bundleId(String bundleId) {
        this.bundleId = bundleId;
        return this;
    }

    @ApiModelProperty("")
    public String getBundleId() {
        return this.bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IOSConfig ioSConfig = (IOSConfig) o;
        if (Objects.equals(this.name, ioSConfig.name) && Objects.equals(this.firebaseConfig, ioSConfig.firebaseConfig) && Objects.equals(this.iconImageUrl, ioSConfig.iconImageUrl) && Objects.equals(this.description, ioSConfig.description) && Objects.equals(this.colorConfiguration, ioSConfig.colorConfiguration) && Objects.equals(this.storeUrl, ioSConfig.storeUrl) && Objects.equals(this.splashImageUrl, ioSConfig.splashImageUrl) && Objects.equals(this.vitalFeatures, ioSConfig.vitalFeatures) && Objects.equals(this.bundleId, ioSConfig.bundleId)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.firebaseConfig, this.iconImageUrl, this.description, this.colorConfiguration, this.storeUrl, this.splashImageUrl, this.vitalFeatures, this.bundleId});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class IOSConfig {\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    firebaseConfig: ").append(toIndentedString(this.firebaseConfig)).append("\n");
        sb.append("    iconImageUrl: ").append(toIndentedString(this.iconImageUrl)).append("\n");
        sb.append("    description: ").append(toIndentedString(this.description)).append("\n");
        sb.append("    colorConfiguration: ").append(toIndentedString(this.colorConfiguration)).append("\n");
        sb.append("    storeUrl: ").append(toIndentedString(this.storeUrl)).append("\n");
        sb.append("    splashImageUrl: ").append(toIndentedString(this.splashImageUrl)).append("\n");
        sb.append("    vitalFeatures: ").append(toIndentedString(this.vitalFeatures)).append("\n");
        sb.append("    bundleId: ").append(toIndentedString(this.bundleId)).append("\n");
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
