package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Diagnosis {
    @SerializedName("healthCondition")
    private String healthCondition = null;
    @SerializedName("symoptoms")
    private String symoptoms = null;

    public Diagnosis healthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
        return this;
    }

    @ApiModelProperty("")
    public String getHealthCondition() {
        return this.healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public Diagnosis symoptoms(String symoptoms) {
        this.symoptoms = symoptoms;
        return this;
    }

    @ApiModelProperty("")
    public String getSymoptoms() {
        return this.symoptoms;
    }

    public void setSymoptoms(String symoptoms) {
        this.symoptoms = symoptoms;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Diagnosis diagnosis = (Diagnosis) o;
        if (Objects.equals(this.healthCondition, diagnosis.healthCondition) && Objects.equals(this.symoptoms, diagnosis.symoptoms)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.healthCondition, this.symoptoms});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Diagnosis {\n");
        sb.append("    healthCondition: ").append(toIndentedString(this.healthCondition)).append("\n");
        sb.append("    symoptoms: ").append(toIndentedString(this.symoptoms)).append("\n");
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
