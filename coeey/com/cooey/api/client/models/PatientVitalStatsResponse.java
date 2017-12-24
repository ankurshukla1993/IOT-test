package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class PatientVitalStatsResponse {
    @SerializedName("bpCount")
    private Integer bpCount = null;
    @SerializedName("bpVitalLimitCrossedCount")
    private Integer bpVitalLimitCrossedCount = null;
    @SerializedName("glucoseCount")
    private Integer glucoseCount = null;
    @SerializedName("glucoseVitalLimitCrossedCount")
    private Integer glucoseVitalLimitCrossedCount = null;
    @SerializedName("latestBPVital")
    private Vital latestBPVital = null;
    @SerializedName("latestBloodGlucoseVital")
    private Vital latestBloodGlucoseVital = null;
    @SerializedName("latestWeightVital")
    private Vital latestWeightVital = null;
    @SerializedName("weightCount")
    private Integer weightCount = null;
    @SerializedName("weightVitalLimitCrossedCount")
    private Integer weightVitalLimitCrossedCount = null;

    public PatientVitalStatsResponse bpCount(Integer bpCount) {
        this.bpCount = bpCount;
        return this;
    }

    @ApiModelProperty("")
    public Integer getBpCount() {
        return this.bpCount;
    }

    public void setBpCount(Integer bpCount) {
        this.bpCount = bpCount;
    }

    public PatientVitalStatsResponse glucoseCount(Integer glucoseCount) {
        this.glucoseCount = glucoseCount;
        return this;
    }

    @ApiModelProperty("")
    public Integer getGlucoseCount() {
        return this.glucoseCount;
    }

    public void setGlucoseCount(Integer glucoseCount) {
        this.glucoseCount = glucoseCount;
    }

    public PatientVitalStatsResponse weightCount(Integer weightCount) {
        this.weightCount = weightCount;
        return this;
    }

    @ApiModelProperty("")
    public Integer getWeightCount() {
        return this.weightCount;
    }

    public void setWeightCount(Integer weightCount) {
        this.weightCount = weightCount;
    }

    public PatientVitalStatsResponse bpVitalLimitCrossedCount(Integer bpVitalLimitCrossedCount) {
        this.bpVitalLimitCrossedCount = bpVitalLimitCrossedCount;
        return this;
    }

    @ApiModelProperty("")
    public Integer getBpVitalLimitCrossedCount() {
        return this.bpVitalLimitCrossedCount;
    }

    public void setBpVitalLimitCrossedCount(Integer bpVitalLimitCrossedCount) {
        this.bpVitalLimitCrossedCount = bpVitalLimitCrossedCount;
    }

    public PatientVitalStatsResponse weightVitalLimitCrossedCount(Integer weightVitalLimitCrossedCount) {
        this.weightVitalLimitCrossedCount = weightVitalLimitCrossedCount;
        return this;
    }

    @ApiModelProperty("")
    public Integer getWeightVitalLimitCrossedCount() {
        return this.weightVitalLimitCrossedCount;
    }

    public void setWeightVitalLimitCrossedCount(Integer weightVitalLimitCrossedCount) {
        this.weightVitalLimitCrossedCount = weightVitalLimitCrossedCount;
    }

    public PatientVitalStatsResponse glucoseVitalLimitCrossedCount(Integer glucoseVitalLimitCrossedCount) {
        this.glucoseVitalLimitCrossedCount = glucoseVitalLimitCrossedCount;
        return this;
    }

    @ApiModelProperty("")
    public Integer getGlucoseVitalLimitCrossedCount() {
        return this.glucoseVitalLimitCrossedCount;
    }

    public void setGlucoseVitalLimitCrossedCount(Integer glucoseVitalLimitCrossedCount) {
        this.glucoseVitalLimitCrossedCount = glucoseVitalLimitCrossedCount;
    }

    public PatientVitalStatsResponse latestBloodGlucoseVital(Vital latestBloodGlucoseVital) {
        this.latestBloodGlucoseVital = latestBloodGlucoseVital;
        return this;
    }

    @ApiModelProperty("")
    public Vital getLatestBloodGlucoseVital() {
        return this.latestBloodGlucoseVital;
    }

    public void setLatestBloodGlucoseVital(Vital latestBloodGlucoseVital) {
        this.latestBloodGlucoseVital = latestBloodGlucoseVital;
    }

    public PatientVitalStatsResponse latestBPVital(Vital latestBPVital) {
        this.latestBPVital = latestBPVital;
        return this;
    }

    @ApiModelProperty("")
    public Vital getLatestBPVital() {
        return this.latestBPVital;
    }

    public void setLatestBPVital(Vital latestBPVital) {
        this.latestBPVital = latestBPVital;
    }

    public PatientVitalStatsResponse latestWeightVital(Vital latestWeightVital) {
        this.latestWeightVital = latestWeightVital;
        return this;
    }

    @ApiModelProperty("")
    public Vital getLatestWeightVital() {
        return this.latestWeightVital;
    }

    public void setLatestWeightVital(Vital latestWeightVital) {
        this.latestWeightVital = latestWeightVital;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PatientVitalStatsResponse patientVitalStatsResponse = (PatientVitalStatsResponse) o;
        if (Objects.equals(this.bpCount, patientVitalStatsResponse.bpCount) && Objects.equals(this.glucoseCount, patientVitalStatsResponse.glucoseCount) && Objects.equals(this.weightCount, patientVitalStatsResponse.weightCount) && Objects.equals(this.bpVitalLimitCrossedCount, patientVitalStatsResponse.bpVitalLimitCrossedCount) && Objects.equals(this.weightVitalLimitCrossedCount, patientVitalStatsResponse.weightVitalLimitCrossedCount) && Objects.equals(this.glucoseVitalLimitCrossedCount, patientVitalStatsResponse.glucoseVitalLimitCrossedCount) && Objects.equals(this.latestBloodGlucoseVital, patientVitalStatsResponse.latestBloodGlucoseVital) && Objects.equals(this.latestBPVital, patientVitalStatsResponse.latestBPVital) && Objects.equals(this.latestWeightVital, patientVitalStatsResponse.latestWeightVital)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.bpCount, this.glucoseCount, this.weightCount, this.bpVitalLimitCrossedCount, this.weightVitalLimitCrossedCount, this.glucoseVitalLimitCrossedCount, this.latestBloodGlucoseVital, this.latestBPVital, this.latestWeightVital});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PatientVitalStatsResponse {\n");
        sb.append("    bpCount: ").append(toIndentedString(this.bpCount)).append("\n");
        sb.append("    glucoseCount: ").append(toIndentedString(this.glucoseCount)).append("\n");
        sb.append("    weightCount: ").append(toIndentedString(this.weightCount)).append("\n");
        sb.append("    bpVitalLimitCrossedCount: ").append(toIndentedString(this.bpVitalLimitCrossedCount)).append("\n");
        sb.append("    weightVitalLimitCrossedCount: ").append(toIndentedString(this.weightVitalLimitCrossedCount)).append("\n");
        sb.append("    glucoseVitalLimitCrossedCount: ").append(toIndentedString(this.glucoseVitalLimitCrossedCount)).append("\n");
        sb.append("    latestBloodGlucoseVital: ").append(toIndentedString(this.latestBloodGlucoseVital)).append("\n");
        sb.append("    latestBPVital: ").append(toIndentedString(this.latestBPVital)).append("\n");
        sb.append("    latestWeightVital: ").append(toIndentedString(this.latestWeightVital)).append("\n");
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
