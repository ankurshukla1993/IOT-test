package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class ObservationSymptoms {
    @SerializedName("abnormalSensation")
    private Boolean abnormalSensation = Boolean.valueOf(false);
    @SerializedName("amputationGangrene")
    private Boolean amputationGangrene = Boolean.valueOf(false);
    @SerializedName("anyBlurredVision")
    private Boolean anyBlurredVision = Boolean.valueOf(false);
    @SerializedName("excessiveThirst")
    private Boolean excessiveThirst = Boolean.valueOf(false);
    @SerializedName("fatigue")
    private Boolean fatigue = Boolean.valueOf(false);
    @SerializedName("heartDisease")
    private Boolean heartDisease = Boolean.valueOf(false);
    @SerializedName("id")
    private String id = null;
    @SerializedName("increasedMicturiton")
    private Boolean increasedMicturiton = Boolean.valueOf(false);
    @SerializedName("lossOfSensationLimbs")
    private Boolean lossOfSensationLimbs = Boolean.valueOf(false);
    @SerializedName("poorWoundHhealing")
    private Boolean poorWoundHhealing = Boolean.valueOf(false);
    @SerializedName("shortOfBreath")
    private Boolean shortOfBreath = Boolean.valueOf(false);
    @SerializedName("userId")
    private String userId = null;
    @SerializedName("weakness")
    private Boolean weakness = Boolean.valueOf(false);
    @SerializedName("weightLoss")
    private Boolean weightLoss = Boolean.valueOf(false);

    public ObservationSymptoms id(String id) {
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

    public ObservationSymptoms shortOfBreath(Boolean shortOfBreath) {
        this.shortOfBreath = shortOfBreath;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getShortOfBreath() {
        return this.shortOfBreath;
    }

    public void setShortOfBreath(Boolean shortOfBreath) {
        this.shortOfBreath = shortOfBreath;
    }

    public ObservationSymptoms excessiveThirst(Boolean excessiveThirst) {
        this.excessiveThirst = excessiveThirst;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getExcessiveThirst() {
        return this.excessiveThirst;
    }

    public void setExcessiveThirst(Boolean excessiveThirst) {
        this.excessiveThirst = excessiveThirst;
    }

    public ObservationSymptoms fatigue(Boolean fatigue) {
        this.fatigue = fatigue;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getFatigue() {
        return this.fatigue;
    }

    public void setFatigue(Boolean fatigue) {
        this.fatigue = fatigue;
    }

    public ObservationSymptoms poorWoundHhealing(Boolean poorWoundHhealing) {
        this.poorWoundHhealing = poorWoundHhealing;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getPoorWoundHhealing() {
        return this.poorWoundHhealing;
    }

    public void setPoorWoundHhealing(Boolean poorWoundHhealing) {
        this.poorWoundHhealing = poorWoundHhealing;
    }

    public ObservationSymptoms abnormalSensation(Boolean abnormalSensation) {
        this.abnormalSensation = abnormalSensation;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getAbnormalSensation() {
        return this.abnormalSensation;
    }

    public void setAbnormalSensation(Boolean abnormalSensation) {
        this.abnormalSensation = abnormalSensation;
    }

    public ObservationSymptoms increasedMicturiton(Boolean increasedMicturiton) {
        this.increasedMicturiton = increasedMicturiton;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getIncreasedMicturiton() {
        return this.increasedMicturiton;
    }

    public void setIncreasedMicturiton(Boolean increasedMicturiton) {
        this.increasedMicturiton = increasedMicturiton;
    }

    public ObservationSymptoms weightLoss(Boolean weightLoss) {
        this.weightLoss = weightLoss;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getWeightLoss() {
        return this.weightLoss;
    }

    public void setWeightLoss(Boolean weightLoss) {
        this.weightLoss = weightLoss;
    }

    public ObservationSymptoms anyBlurredVision(Boolean anyBlurredVision) {
        this.anyBlurredVision = anyBlurredVision;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getAnyBlurredVision() {
        return this.anyBlurredVision;
    }

    public void setAnyBlurredVision(Boolean anyBlurredVision) {
        this.anyBlurredVision = anyBlurredVision;
    }

    public ObservationSymptoms amputationGangrene(Boolean amputationGangrene) {
        this.amputationGangrene = amputationGangrene;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getAmputationGangrene() {
        return this.amputationGangrene;
    }

    public void setAmputationGangrene(Boolean amputationGangrene) {
        this.amputationGangrene = amputationGangrene;
    }

    public ObservationSymptoms lossOfSensationLimbs(Boolean lossOfSensationLimbs) {
        this.lossOfSensationLimbs = lossOfSensationLimbs;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getLossOfSensationLimbs() {
        return this.lossOfSensationLimbs;
    }

    public void setLossOfSensationLimbs(Boolean lossOfSensationLimbs) {
        this.lossOfSensationLimbs = lossOfSensationLimbs;
    }

    public ObservationSymptoms heartDisease(Boolean heartDisease) {
        this.heartDisease = heartDisease;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getHeartDisease() {
        return this.heartDisease;
    }

    public void setHeartDisease(Boolean heartDisease) {
        this.heartDisease = heartDisease;
    }

    public ObservationSymptoms weakness(Boolean weakness) {
        this.weakness = weakness;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getWeakness() {
        return this.weakness;
    }

    public void setWeakness(Boolean weakness) {
        this.weakness = weakness;
    }

    public ObservationSymptoms userId(String userId) {
        this.userId = userId;
        return this;
    }

    @ApiModelProperty("")
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObservationSymptoms observationSymptoms = (ObservationSymptoms) o;
        if (Objects.equals(this.id, observationSymptoms.id) && Objects.equals(this.shortOfBreath, observationSymptoms.shortOfBreath) && Objects.equals(this.excessiveThirst, observationSymptoms.excessiveThirst) && Objects.equals(this.fatigue, observationSymptoms.fatigue) && Objects.equals(this.poorWoundHhealing, observationSymptoms.poorWoundHhealing) && Objects.equals(this.abnormalSensation, observationSymptoms.abnormalSensation) && Objects.equals(this.increasedMicturiton, observationSymptoms.increasedMicturiton) && Objects.equals(this.weightLoss, observationSymptoms.weightLoss) && Objects.equals(this.anyBlurredVision, observationSymptoms.anyBlurredVision) && Objects.equals(this.amputationGangrene, observationSymptoms.amputationGangrene) && Objects.equals(this.lossOfSensationLimbs, observationSymptoms.lossOfSensationLimbs) && Objects.equals(this.heartDisease, observationSymptoms.heartDisease) && Objects.equals(this.weakness, observationSymptoms.weakness) && Objects.equals(this.userId, observationSymptoms.userId)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.shortOfBreath, this.excessiveThirst, this.fatigue, this.poorWoundHhealing, this.abnormalSensation, this.increasedMicturiton, this.weightLoss, this.anyBlurredVision, this.amputationGangrene, this.lossOfSensationLimbs, this.heartDisease, this.weakness, this.userId});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ObservationSymptoms {\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    shortOfBreath: ").append(toIndentedString(this.shortOfBreath)).append("\n");
        sb.append("    excessiveThirst: ").append(toIndentedString(this.excessiveThirst)).append("\n");
        sb.append("    fatigue: ").append(toIndentedString(this.fatigue)).append("\n");
        sb.append("    poorWoundHhealing: ").append(toIndentedString(this.poorWoundHhealing)).append("\n");
        sb.append("    abnormalSensation: ").append(toIndentedString(this.abnormalSensation)).append("\n");
        sb.append("    increasedMicturiton: ").append(toIndentedString(this.increasedMicturiton)).append("\n");
        sb.append("    weightLoss: ").append(toIndentedString(this.weightLoss)).append("\n");
        sb.append("    anyBlurredVision: ").append(toIndentedString(this.anyBlurredVision)).append("\n");
        sb.append("    amputationGangrene: ").append(toIndentedString(this.amputationGangrene)).append("\n");
        sb.append("    lossOfSensationLimbs: ").append(toIndentedString(this.lossOfSensationLimbs)).append("\n");
        sb.append("    heartDisease: ").append(toIndentedString(this.heartDisease)).append("\n");
        sb.append("    weakness: ").append(toIndentedString(this.weakness)).append("\n");
        sb.append("    userId: ").append(toIndentedString(this.userId)).append("\n");
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
