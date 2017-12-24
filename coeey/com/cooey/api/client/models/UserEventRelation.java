package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class UserEventRelation {
    @SerializedName("end")
    private Long end = null;
    @SerializedName("eventId")
    private String eventId = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("start")
    private Long start = null;
    @SerializedName("userId")
    private String userId = null;

    public UserEventRelation id(String id) {
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

    public UserEventRelation userId(String userId) {
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

    public UserEventRelation eventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    @ApiModelProperty("")
    public String getEventId() {
        return this.eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public UserEventRelation start(Long start) {
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

    public UserEventRelation end(Long end) {
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

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserEventRelation userEventRelation = (UserEventRelation) o;
        if (Objects.equals(this.id, userEventRelation.id) && Objects.equals(this.userId, userEventRelation.userId) && Objects.equals(this.eventId, userEventRelation.eventId) && Objects.equals(this.start, userEventRelation.start) && Objects.equals(this.end, userEventRelation.end)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.userId, this.eventId, this.start, this.end});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserEventRelation {\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    userId: ").append(toIndentedString(this.userId)).append("\n");
        sb.append("    eventId: ").append(toIndentedString(this.eventId)).append("\n");
        sb.append("    start: ").append(toIndentedString(this.start)).append("\n");
        sb.append("    end: ").append(toIndentedString(this.end)).append("\n");
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
