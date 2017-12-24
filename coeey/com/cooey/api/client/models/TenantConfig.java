package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class TenantConfig {
    @SerializedName("activityConfig")
    private ActivityConfig activityConfig = null;
    @SerializedName("androidCaretakerAppConfig")
    private AndroidConfig androidCaretakerAppConfig = null;
    @SerializedName("androidGuardianAppConfig")
    private AndroidConfig androidGuardianAppConfig = null;
    @SerializedName("androidPatientAppConfig")
    private AndroidConfig androidPatientAppConfig = null;
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("billingConfig")
    private BillingConfig billingConfig = null;
    @SerializedName("careplanEnabled")
    private Boolean careplanEnabled = Boolean.valueOf(false);
    @SerializedName("configColor")
    private ColorConfiguration configColor = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("defaultMedicineSource")
    private String defaultMedicineSource = null;
    @SerializedName("dietConfig")
    private DietConfig dietConfig = null;
    @SerializedName("faviconURL")
    private String faviconURL = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("iosCaretakerConfig")
    private IOSConfig iosCaretakerConfig = null;
    @SerializedName("iosGuardianAppConfig")
    private IOSConfig iosGuardianAppConfig = null;
    @SerializedName("iosPatientAppConfig")
    private IOSConfig iosPatientAppConfig = null;
    @SerializedName("logoURL")
    private String logoURL = null;
    @SerializedName("templateConfig")
    private TemplateConfig templateConfig = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("tenantName")
    private String tenantName = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;
    @SerializedName("vitalConfig")
    private VitalConfig vitalConfig = null;

    public TenantConfig createdOn(Long createdOn) {
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

    public TenantConfig updatedOn(Long updatedOn) {
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

    public TenantConfig tenantId(String tenantId) {
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

    public TenantConfig applicationId(String applicationId) {
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

    public TenantConfig id(String id) {
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

    public TenantConfig tenantName(String tenantName) {
        this.tenantName = tenantName;
        return this;
    }

    @ApiModelProperty("")
    public String getTenantName() {
        return this.tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public TenantConfig configColor(ColorConfiguration configColor) {
        this.configColor = configColor;
        return this;
    }

    @ApiModelProperty("")
    public ColorConfiguration getConfigColor() {
        return this.configColor;
    }

    public void setConfigColor(ColorConfiguration configColor) {
        this.configColor = configColor;
    }

    public TenantConfig logoURL(String logoURL) {
        this.logoURL = logoURL;
        return this;
    }

    @ApiModelProperty("")
    public String getLogoURL() {
        return this.logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public TenantConfig faviconURL(String faviconURL) {
        this.faviconURL = faviconURL;
        return this;
    }

    @ApiModelProperty("")
    public String getFaviconURL() {
        return this.faviconURL;
    }

    public void setFaviconURL(String faviconURL) {
        this.faviconURL = faviconURL;
    }

    public TenantConfig androidCaretakerAppConfig(AndroidConfig androidCaretakerAppConfig) {
        this.androidCaretakerAppConfig = androidCaretakerAppConfig;
        return this;
    }

    @ApiModelProperty("")
    public AndroidConfig getAndroidCaretakerAppConfig() {
        return this.androidCaretakerAppConfig;
    }

    public void setAndroidCaretakerAppConfig(AndroidConfig androidCaretakerAppConfig) {
        this.androidCaretakerAppConfig = androidCaretakerAppConfig;
    }

    public TenantConfig androidGuardianAppConfig(AndroidConfig androidGuardianAppConfig) {
        this.androidGuardianAppConfig = androidGuardianAppConfig;
        return this;
    }

    @ApiModelProperty("")
    public AndroidConfig getAndroidGuardianAppConfig() {
        return this.androidGuardianAppConfig;
    }

    public void setAndroidGuardianAppConfig(AndroidConfig androidGuardianAppConfig) {
        this.androidGuardianAppConfig = androidGuardianAppConfig;
    }

    public TenantConfig androidPatientAppConfig(AndroidConfig androidPatientAppConfig) {
        this.androidPatientAppConfig = androidPatientAppConfig;
        return this;
    }

    @ApiModelProperty("")
    public AndroidConfig getAndroidPatientAppConfig() {
        return this.androidPatientAppConfig;
    }

    public void setAndroidPatientAppConfig(AndroidConfig androidPatientAppConfig) {
        this.androidPatientAppConfig = androidPatientAppConfig;
    }

    public TenantConfig iosCaretakerConfig(IOSConfig iosCaretakerConfig) {
        this.iosCaretakerConfig = iosCaretakerConfig;
        return this;
    }

    @ApiModelProperty("")
    public IOSConfig getIosCaretakerConfig() {
        return this.iosCaretakerConfig;
    }

    public void setIosCaretakerConfig(IOSConfig iosCaretakerConfig) {
        this.iosCaretakerConfig = iosCaretakerConfig;
    }

    public TenantConfig iosGuardianAppConfig(IOSConfig iosGuardianAppConfig) {
        this.iosGuardianAppConfig = iosGuardianAppConfig;
        return this;
    }

    @ApiModelProperty("")
    public IOSConfig getIosGuardianAppConfig() {
        return this.iosGuardianAppConfig;
    }

    public void setIosGuardianAppConfig(IOSConfig iosGuardianAppConfig) {
        this.iosGuardianAppConfig = iosGuardianAppConfig;
    }

    public TenantConfig iosPatientAppConfig(IOSConfig iosPatientAppConfig) {
        this.iosPatientAppConfig = iosPatientAppConfig;
        return this;
    }

    @ApiModelProperty("")
    public IOSConfig getIosPatientAppConfig() {
        return this.iosPatientAppConfig;
    }

    public void setIosPatientAppConfig(IOSConfig iosPatientAppConfig) {
        this.iosPatientAppConfig = iosPatientAppConfig;
    }

    public TenantConfig vitalConfig(VitalConfig vitalConfig) {
        this.vitalConfig = vitalConfig;
        return this;
    }

    @ApiModelProperty("")
    public VitalConfig getVitalConfig() {
        return this.vitalConfig;
    }

    public void setVitalConfig(VitalConfig vitalConfig) {
        this.vitalConfig = vitalConfig;
    }

    public TenantConfig dietConfig(DietConfig dietConfig) {
        this.dietConfig = dietConfig;
        return this;
    }

    @ApiModelProperty("")
    public DietConfig getDietConfig() {
        return this.dietConfig;
    }

    public void setDietConfig(DietConfig dietConfig) {
        this.dietConfig = dietConfig;
    }

    public TenantConfig templateConfig(TemplateConfig templateConfig) {
        this.templateConfig = templateConfig;
        return this;
    }

    @ApiModelProperty("")
    public TemplateConfig getTemplateConfig() {
        return this.templateConfig;
    }

    public void setTemplateConfig(TemplateConfig templateConfig) {
        this.templateConfig = templateConfig;
    }

    public TenantConfig defaultMedicineSource(String defaultMedicineSource) {
        this.defaultMedicineSource = defaultMedicineSource;
        return this;
    }

    @ApiModelProperty("")
    public String getDefaultMedicineSource() {
        return this.defaultMedicineSource;
    }

    public void setDefaultMedicineSource(String defaultMedicineSource) {
        this.defaultMedicineSource = defaultMedicineSource;
    }

    public TenantConfig billingConfig(BillingConfig billingConfig) {
        this.billingConfig = billingConfig;
        return this;
    }

    @ApiModelProperty("")
    public BillingConfig getBillingConfig() {
        return this.billingConfig;
    }

    public void setBillingConfig(BillingConfig billingConfig) {
        this.billingConfig = billingConfig;
    }

    public TenantConfig activityConfig(ActivityConfig activityConfig) {
        this.activityConfig = activityConfig;
        return this;
    }

    @ApiModelProperty("")
    public ActivityConfig getActivityConfig() {
        return this.activityConfig;
    }

    public void setActivityConfig(ActivityConfig activityConfig) {
        this.activityConfig = activityConfig;
    }

    public TenantConfig careplanEnabled(Boolean careplanEnabled) {
        this.careplanEnabled = careplanEnabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getCareplanEnabled() {
        return this.careplanEnabled;
    }

    public void setCareplanEnabled(Boolean careplanEnabled) {
        this.careplanEnabled = careplanEnabled;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TenantConfig tenantConfig = (TenantConfig) o;
        if (Objects.equals(this.createdOn, tenantConfig.createdOn) && Objects.equals(this.updatedOn, tenantConfig.updatedOn) && Objects.equals(this.tenantId, tenantConfig.tenantId) && Objects.equals(this.applicationId, tenantConfig.applicationId) && Objects.equals(this.id, tenantConfig.id) && Objects.equals(this.tenantName, tenantConfig.tenantName) && Objects.equals(this.configColor, tenantConfig.configColor) && Objects.equals(this.logoURL, tenantConfig.logoURL) && Objects.equals(this.faviconURL, tenantConfig.faviconURL) && Objects.equals(this.androidCaretakerAppConfig, tenantConfig.androidCaretakerAppConfig) && Objects.equals(this.androidGuardianAppConfig, tenantConfig.androidGuardianAppConfig) && Objects.equals(this.androidPatientAppConfig, tenantConfig.androidPatientAppConfig) && Objects.equals(this.iosCaretakerConfig, tenantConfig.iosCaretakerConfig) && Objects.equals(this.iosGuardianAppConfig, tenantConfig.iosGuardianAppConfig) && Objects.equals(this.iosPatientAppConfig, tenantConfig.iosPatientAppConfig) && Objects.equals(this.vitalConfig, tenantConfig.vitalConfig) && Objects.equals(this.dietConfig, tenantConfig.dietConfig) && Objects.equals(this.templateConfig, tenantConfig.templateConfig) && Objects.equals(this.defaultMedicineSource, tenantConfig.defaultMedicineSource) && Objects.equals(this.billingConfig, tenantConfig.billingConfig) && Objects.equals(this.activityConfig, tenantConfig.activityConfig) && Objects.equals(this.careplanEnabled, tenantConfig.careplanEnabled)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.tenantName, this.configColor, this.logoURL, this.faviconURL, this.androidCaretakerAppConfig, this.androidGuardianAppConfig, this.androidPatientAppConfig, this.iosCaretakerConfig, this.iosGuardianAppConfig, this.iosPatientAppConfig, this.vitalConfig, this.dietConfig, this.templateConfig, this.defaultMedicineSource, this.billingConfig, this.activityConfig, this.careplanEnabled});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TenantConfig {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    tenantName: ").append(toIndentedString(this.tenantName)).append("\n");
        sb.append("    configColor: ").append(toIndentedString(this.configColor)).append("\n");
        sb.append("    logoURL: ").append(toIndentedString(this.logoURL)).append("\n");
        sb.append("    faviconURL: ").append(toIndentedString(this.faviconURL)).append("\n");
        sb.append("    androidCaretakerAppConfig: ").append(toIndentedString(this.androidCaretakerAppConfig)).append("\n");
        sb.append("    androidGuardianAppConfig: ").append(toIndentedString(this.androidGuardianAppConfig)).append("\n");
        sb.append("    androidPatientAppConfig: ").append(toIndentedString(this.androidPatientAppConfig)).append("\n");
        sb.append("    iosCaretakerConfig: ").append(toIndentedString(this.iosCaretakerConfig)).append("\n");
        sb.append("    iosGuardianAppConfig: ").append(toIndentedString(this.iosGuardianAppConfig)).append("\n");
        sb.append("    iosPatientAppConfig: ").append(toIndentedString(this.iosPatientAppConfig)).append("\n");
        sb.append("    vitalConfig: ").append(toIndentedString(this.vitalConfig)).append("\n");
        sb.append("    dietConfig: ").append(toIndentedString(this.dietConfig)).append("\n");
        sb.append("    templateConfig: ").append(toIndentedString(this.templateConfig)).append("\n");
        sb.append("    defaultMedicineSource: ").append(toIndentedString(this.defaultMedicineSource)).append("\n");
        sb.append("    billingConfig: ").append(toIndentedString(this.billingConfig)).append("\n");
        sb.append("    activityConfig: ").append(toIndentedString(this.activityConfig)).append("\n");
        sb.append("    careplanEnabled: ").append(toIndentedString(this.careplanEnabled)).append("\n");
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
