package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class SummaryResponse {
    @SerializedName("itemType")
    private String itemType = null;
    @SerializedName("message")
    private String message = null;
    @SerializedName("rateIndicator")
    private Integer rateIndicator = null;

    public SummaryResponse message(String message) {
        this.message = message;
        return this;
    }

    @ApiModelProperty("")
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SummaryResponse rateIndicator(Integer rateIndicator) {
        this.rateIndicator = rateIndicator;
        return this;
    }

    @ApiModelProperty("")
    public Integer getRateIndicator() {
        return this.rateIndicator;
    }

    public void setRateIndicator(Integer rateIndicator) {
        this.rateIndicator = rateIndicator;
    }

    public SummaryResponse itemType(String itemType) {
        this.itemType = itemType;
        return this;
    }

    @ApiModelProperty("")
    public String getItemType() {
        return this.itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SummaryResponse summaryResponse = (SummaryResponse) o;
        if (Objects.equals(this.message, summaryResponse.message) && Objects.equals(this.rateIndicator, summaryResponse.rateIndicator) && Objects.equals(this.itemType, summaryResponse.itemType)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.message, this.rateIndicator, this.itemType});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SummaryResponse {\n");
        sb.append("    message: ").append(toIndentedString(this.message)).append("\n");
        sb.append("    rateIndicator: ").append(toIndentedString(this.rateIndicator)).append("\n");
        sb.append("    itemType: ").append(toIndentedString(this.itemType)).append("\n");
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
