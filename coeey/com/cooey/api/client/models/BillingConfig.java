package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class BillingConfig {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("footer")
    private String footer = null;
    @SerializedName("headerLogo")
    private String headerLogo = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("unit")
    private String unit = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;

    public BillingConfig createdOn(Long createdOn) {
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

    public BillingConfig updatedOn(Long updatedOn) {
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

    public BillingConfig tenantId(String tenantId) {
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

    public BillingConfig applicationId(String applicationId) {
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

    public BillingConfig headerLogo(String headerLogo) {
        this.headerLogo = headerLogo;
        return this;
    }

    @ApiModelProperty("")
    public String getHeaderLogo() {
        return this.headerLogo;
    }

    public void setHeaderLogo(String headerLogo) {
        this.headerLogo = headerLogo;
    }

    public BillingConfig footer(String footer) {
        this.footer = footer;
        return this;
    }

    @ApiModelProperty("")
    public String getFooter() {
        return this.footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public BillingConfig unit(String unit) {
        this.unit = unit;
        return this;
    }

    @ApiModelProperty("")
    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BillingConfig billingConfig = (BillingConfig) o;
        if (Objects.equals(this.createdOn, billingConfig.createdOn) && Objects.equals(this.updatedOn, billingConfig.updatedOn) && Objects.equals(this.tenantId, billingConfig.tenantId) && Objects.equals(this.applicationId, billingConfig.applicationId) && Objects.equals(this.headerLogo, billingConfig.headerLogo) && Objects.equals(this.footer, billingConfig.footer) && Objects.equals(this.unit, billingConfig.unit)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.headerLogo, this.footer, this.unit});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BillingConfig {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    headerLogo: ").append(toIndentedString(this.headerLogo)).append("\n");
        sb.append("    footer: ").append(toIndentedString(this.footer)).append("\n");
        sb.append("    unit: ").append(toIndentedString(this.unit)).append("\n");
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
