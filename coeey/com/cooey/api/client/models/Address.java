package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Address {
    @SerializedName("city")
    private String city = null;
    @SerializedName("line1")
    private String line1 = null;
    @SerializedName("line2")
    private String line2 = null;
    @SerializedName("state")
    private String state = null;

    public Address line1(String line1) {
        this.line1 = line1;
        return this;
    }

    @ApiModelProperty("")
    public String getLine1() {
        return this.line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public Address line2(String line2) {
        this.line2 = line2;
        return this;
    }

    @ApiModelProperty("")
    public String getLine2() {
        return this.line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public Address city(String city) {
        this.city = city;
        return this;
    }

    @ApiModelProperty("")
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address state(String state) {
        this.state = state;
        return this;
    }

    @ApiModelProperty("")
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        if (Objects.equals(this.line1, address.line1) && Objects.equals(this.line2, address.line2) && Objects.equals(this.city, address.city) && Objects.equals(this.state, address.state)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.line1, this.line2, this.city, this.state});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Address {\n");
        sb.append("    line1: ").append(toIndentedString(this.line1)).append("\n");
        sb.append("    line2: ").append(toIndentedString(this.line2)).append("\n");
        sb.append("    city: ").append(toIndentedString(this.city)).append("\n");
        sb.append("    state: ").append(toIndentedString(this.state)).append("\n");
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
