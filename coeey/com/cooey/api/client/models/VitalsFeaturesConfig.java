package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class VitalsFeaturesConfig {
    @SerializedName("propertyName")
    private String propertyName = null;
    @SerializedName("propertyStateId")
    private String propertyStateId = null;
    @SerializedName("vitalTemplateConfigId")
    private String vitalTemplateConfigId = null;

    public VitalsFeaturesConfig propertyStateId(String propertyStateId) {
        this.propertyStateId = propertyStateId;
        return this;
    }

    @ApiModelProperty("")
    public String getPropertyStateId() {
        return this.propertyStateId;
    }

    public void setPropertyStateId(String propertyStateId) {
        this.propertyStateId = propertyStateId;
    }

    public VitalsFeaturesConfig vitalTemplateConfigId(String vitalTemplateConfigId) {
        this.vitalTemplateConfigId = vitalTemplateConfigId;
        return this;
    }

    @ApiModelProperty("")
    public String getVitalTemplateConfigId() {
        return this.vitalTemplateConfigId;
    }

    public void setVitalTemplateConfigId(String vitalTemplateConfigId) {
        this.vitalTemplateConfigId = vitalTemplateConfigId;
    }

    public VitalsFeaturesConfig propertyName(String propertyName) {
        this.propertyName = propertyName;
        return this;
    }

    @ApiModelProperty("")
    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VitalsFeaturesConfig vitalsFeaturesConfig = (VitalsFeaturesConfig) o;
        if (Objects.equals(this.propertyStateId, vitalsFeaturesConfig.propertyStateId) && Objects.equals(this.vitalTemplateConfigId, vitalsFeaturesConfig.vitalTemplateConfigId) && Objects.equals(this.propertyName, vitalsFeaturesConfig.propertyName)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.propertyStateId, this.vitalTemplateConfigId, this.propertyName});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class VitalsFeaturesConfig {\n");
        sb.append("    propertyStateId: ").append(toIndentedString(this.propertyStateId)).append("\n");
        sb.append("    vitalTemplateConfigId: ").append(toIndentedString(this.vitalTemplateConfigId)).append("\n");
        sb.append("    propertyName: ").append(toIndentedString(this.propertyName)).append("\n");
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
