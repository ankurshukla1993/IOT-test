package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class TemplateConfig {
    @SerializedName("contentFeatureEnabled")
    private Boolean contentFeatureEnabled = Boolean.valueOf(false);
    @SerializedName("dietFeatureEnabled")
    private Boolean dietFeatureEnabled = Boolean.valueOf(false);

    public TemplateConfig contentFeatureEnabled(Boolean contentFeatureEnabled) {
        this.contentFeatureEnabled = contentFeatureEnabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getContentFeatureEnabled() {
        return this.contentFeatureEnabled;
    }

    public void setContentFeatureEnabled(Boolean contentFeatureEnabled) {
        this.contentFeatureEnabled = contentFeatureEnabled;
    }

    public TemplateConfig dietFeatureEnabled(Boolean dietFeatureEnabled) {
        this.dietFeatureEnabled = dietFeatureEnabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getDietFeatureEnabled() {
        return this.dietFeatureEnabled;
    }

    public void setDietFeatureEnabled(Boolean dietFeatureEnabled) {
        this.dietFeatureEnabled = dietFeatureEnabled;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TemplateConfig templateConfig = (TemplateConfig) o;
        if (Objects.equals(this.contentFeatureEnabled, templateConfig.contentFeatureEnabled) && Objects.equals(this.dietFeatureEnabled, templateConfig.dietFeatureEnabled)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.contentFeatureEnabled, this.dietFeatureEnabled});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TemplateConfig {\n");
        sb.append("    contentFeatureEnabled: ").append(toIndentedString(this.contentFeatureEnabled)).append("\n");
        sb.append("    dietFeatureEnabled: ").append(toIndentedString(this.dietFeatureEnabled)).append("\n");
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
