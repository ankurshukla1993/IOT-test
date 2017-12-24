package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Event {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("category")
    private String category = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("enableNotification")
    private Boolean enableNotification = Boolean.valueOf(false);
    @SerializedName("end")
    private Long end = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("notificationBefore")
    private Long notificationBefore = null;
    @SerializedName("ownerId")
    private String ownerId = null;
    @SerializedName("start")
    private Long start = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;
    @SerializedName("userId")
    private String userId = null;

    public Event createdOn(Long createdOn) {
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

    public Event updatedOn(Long updatedOn) {
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

    public Event tenantId(String tenantId) {
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

    public Event applicationId(String applicationId) {
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

    public Event id(String id) {
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

    public Event name(String name) {
        this.name = name;
        return this;
    }

    @ApiModelProperty("")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event start(Long start) {
        this.start = start;
        return this;
    }

    @ApiModelProperty("")
    public Long getStart() {
        return this.start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Event end(Long end) {
        this.end = end;
        return this;
    }

    @ApiModelProperty("")
    public Long getEnd() {
        return this.end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Event enableNotification(Boolean enableNotification) {
        this.enableNotification = enableNotification;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getEnableNotification() {
        return this.enableNotification;
    }

    public void setEnableNotification(Boolean enableNotification) {
        this.enableNotification = enableNotification;
    }

    public Event notificationBefore(Long notificationBefore) {
        this.notificationBefore = notificationBefore;
        return this;
    }

    @ApiModelProperty("")
    public Long getNotificationBefore() {
        return this.notificationBefore;
    }

    public void setNotificationBefore(Long notificationBefore) {
        this.notificationBefore = notificationBefore;
    }

    public Event userId(String userId) {
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

    public Event ownerId(String ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    @ApiModelProperty("")
    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Event category(String category) {
        this.category = category;
        return this;
    }

    @ApiModelProperty("")
    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        if (Objects.equals(this.createdOn, event.createdOn) && Objects.equals(this.updatedOn, event.updatedOn) && Objects.equals(this.tenantId, event.tenantId) && Objects.equals(this.applicationId, event.applicationId) && Objects.equals(this.id, event.id) && Objects.equals(this.name, event.name) && Objects.equals(this.start, event.start) && Objects.equals(this.end, event.end) && Objects.equals(this.enableNotification, event.enableNotification) && Objects.equals(this.notificationBefore, event.notificationBefore) && Objects.equals(this.userId, event.userId) && Objects.equals(this.ownerId, event.ownerId) && Objects.equals(this.category, event.category)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.name, this.start, this.end, this.enableNotification, this.notificationBefore, this.userId, this.ownerId, this.category});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Event {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    start: ").append(toIndentedString(this.start)).append("\n");
        sb.append("    end: ").append(toIndentedString(this.end)).append("\n");
        sb.append("    enableNotification: ").append(toIndentedString(this.enableNotification)).append("\n");
        sb.append("    notificationBefore: ").append(toIndentedString(this.notificationBefore)).append("\n");
        sb.append("    userId: ").append(toIndentedString(this.userId)).append("\n");
        sb.append("    ownerId: ").append(toIndentedString(this.ownerId)).append("\n");
        sb.append("    category: ").append(toIndentedString(this.category)).append("\n");
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
