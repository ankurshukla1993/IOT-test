package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class ActivityConfig {
    @SerializedName("activityEnabled")
    private Boolean activityEnabled = Boolean.valueOf(false);

    public ActivityConfig activityEnabled(Boolean activityEnabled) {
        this.activityEnabled = activityEnabled;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getActivityEnabled() {
        return this.activityEnabled;
    }

    public void setActivityEnabled(Boolean activityEnabled) {
        this.activityEnabled = activityEnabled;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Objects.equals(this.activityEnabled, ((ActivityConfig) o).activityEnabled);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.activityEnabled});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ActivityConfig {\n");
        sb.append("    activityEnabled: ").append(toIndentedString(this.activityEnabled)).append("\n");
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
