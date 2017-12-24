package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class VitalConfig {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("bloodGlucoseCheckEnabled")
    private Boolean bloodGlucoseCheckEnabled = Boolean.valueOf(false);
    @SerializedName("bloodPressureCheckEnabled")
    private Boolean bloodPressureCheckEnabled = Boolean.valueOf(false);
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;
    @SerializedName("weightCheckEnabled")
    private Boolean weightCheckEnabled = Boolean.valueOf(false);

    public VitalConfig createdOn(Long createdOn) {
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

    public VitalConfig updatedOn(Long updatedOn) {
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

    public VitalConfig tenantId(String tenantId) {
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

    public VitalConfig applicationId(String applicationId) {
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

    public VitalConfig bloodGlucoseCheckEnabled(Boolean bloodGlucoseCheckEnabled) {
        this.bloodGlucoseCheckEnabled = bloodGlucoseCheckEnabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getBloodGlucoseCheckEnabled() {
        return this.bloodGlucoseCheckEnabled;
    }

    public void setBloodGlucoseCheckEnabled(Boolean bloodGlucoseCheckEnabled) {
        this.bloodGlucoseCheckEnabled = bloodGlucoseCheckEnabled;
    }

    public VitalConfig weightCheckEnabled(Boolean weightCheckEnabled) {
        this.weightCheckEnabled = weightCheckEnabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getWeightCheckEnabled() {
        return this.weightCheckEnabled;
    }

    public void setWeightCheckEnabled(Boolean weightCheckEnabled) {
        this.weightCheckEnabled = weightCheckEnabled;
    }

    public VitalConfig bloodPressureCheckEnabled(Boolean bloodPressureCheckEnabled) {
        this.bloodPressureCheckEnabled = bloodPressureCheckEnabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getBloodPressureCheckEnabled() {
        return this.bloodPressureCheckEnabled;
    }

    public void setBloodPressureCheckEnabled(Boolean bloodPressureCheckEnabled) {
        this.bloodPressureCheckEnabled = bloodPressureCheckEnabled;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VitalConfig vitalConfig = (VitalConfig) o;
        if (Objects.equals(this.createdOn, vitalConfig.createdOn) && Objects.equals(this.updatedOn, vitalConfig.updatedOn) && Objects.equals(this.tenantId, vitalConfig.tenantId) && Objects.equals(this.applicationId, vitalConfig.applicationId) && Objects.equals(this.bloodGlucoseCheckEnabled, vitalConfig.bloodGlucoseCheckEnabled) && Objects.equals(this.weightCheckEnabled, vitalConfig.weightCheckEnabled) && Objects.equals(this.bloodPressureCheckEnabled, vitalConfig.bloodPressureCheckEnabled)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.bloodGlucoseCheckEnabled, this.weightCheckEnabled, this.bloodPressureCheckEnabled});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VitalConfig {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    bloodGlucoseCheckEnabled: ").append(toIndentedString(this.bloodGlucoseCheckEnabled)).append("\n");
        sb.append("    weightCheckEnabled: ").append(toIndentedString(this.weightCheckEnabled)).append("\n");
        sb.append("    bloodPressureCheckEnabled: ").append(toIndentedString(this.bloodPressureCheckEnabled)).append("\n");
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
