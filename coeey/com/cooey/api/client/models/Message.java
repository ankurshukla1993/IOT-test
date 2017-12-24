package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Message {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("fromUserId")
    private String fromUserId = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("text")
    private String text = null;
    @SerializedName("toUserId")
    private String toUserId = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;

    public Message createdOn(Long createdOn) {
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

    public Message updatedOn(Long updatedOn) {
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

    public Message tenantId(String tenantId) {
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

    public Message applicationId(String applicationId) {
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

    public Message id(String id) {
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

    public Message text(String text) {
        this.text = text;
        return this;
    }

    @ApiModelProperty("")
    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Message fromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
        return this;
    }

    @ApiModelProperty("")
    public String getFromUserId() {
        return this.fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Message toUserId(String toUserId) {
        this.toUserId = toUserId;
        return this;
    }

    @ApiModelProperty("")
    public String getToUserId() {
        return this.toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        if (Objects.equals(this.createdOn, message.createdOn) && Objects.equals(this.updatedOn, message.updatedOn) && Objects.equals(this.tenantId, message.tenantId) && Objects.equals(this.applicationId, message.applicationId) && Objects.equals(this.id, message.id) && Objects.equals(this.text, message.text) && Objects.equals(this.fromUserId, message.fromUserId) && Objects.equals(this.toUserId, message.toUserId)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.text, this.fromUserId, this.toUserId});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Message {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    text: ").append(toIndentedString(this.text)).append("\n");
        sb.append("    fromUserId: ").append(toIndentedString(this.fromUserId)).append("\n");
        sb.append("    toUserId: ").append(toIndentedString(this.toUserId)).append("\n");
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
