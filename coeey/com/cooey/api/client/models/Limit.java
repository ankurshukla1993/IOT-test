package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Limit {
    @SerializedName("higherLimit")
    private Float higherLimit = null;
    @SerializedName("limitType")
    private String limitType = null;
    @SerializedName("lowerLimit")
    private Float lowerLimit = null;

    public Limit lowerLimit(Float lowerLimit) {
        this.lowerLimit = lowerLimit;
        return this;
    }

    @ApiModelProperty("")
    public Float getLowerLimit() {
        return this.lowerLimit;
    }

    public void setLowerLimit(Float lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Limit higherLimit(Float higherLimit) {
        this.higherLimit = higherLimit;
        return this;
    }

    @ApiModelProperty("")
    public Float getHigherLimit() {
        return this.higherLimit;
    }

    public void setHigherLimit(Float higherLimit) {
        this.higherLimit = higherLimit;
    }

    public Limit limitType(String limitType) {
        this.limitType = limitType;
        return this;
    }

    @ApiModelProperty("")
    public String getLimitType() {
        return this.limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Limit limit = (Limit) o;
        if (Objects.equals(this.lowerLimit, limit.lowerLimit) && Objects.equals(this.higherLimit, limit.higherLimit) && Objects.equals(this.limitType, limit.limitType)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.lowerLimit, this.higherLimit, this.limitType});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Limit {\n");
        sb.append("    lowerLimit: ").append(toIndentedString(this.lowerLimit)).append("\n");
        sb.append("    higherLimit: ").append(toIndentedString(this.higherLimit)).append("\n");
        sb.append("    limitType: ").append(toIndentedString(this.limitType)).append("\n");
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
