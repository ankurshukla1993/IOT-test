package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CaretakerEvent {
    @SerializedName("event")
    private Event event = null;
    @SerializedName("userIds")
    private List<String> userIds = null;

    public CaretakerEvent event(Event event) {
        this.event = event;
        return this;
    }

    @ApiModelProperty("")
    public Event getEvent() {
        return this.event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public CaretakerEvent userIds(List<String> userIds) {
        this.userIds = userIds;
        return this;
    }

    public CaretakerEvent addUserIdsItem(String userIdsItem) {
        if (this.userIds == null) {
            this.userIds = new ArrayList();
        }
        this.userIds.add(userIdsItem);
        return this;
    }

    @ApiModelProperty("")
    public List<String> getUserIds() {
        return this.userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CaretakerEvent caretakerEvent = (CaretakerEvent) o;
        if (Objects.equals(this.event, caretakerEvent.event) && Objects.equals(this.userIds, caretakerEvent.userIds)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.event, this.userIds});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CaretakerEvent {\n");
        sb.append("    event: ").append(toIndentedString(this.event)).append("\n");
        sb.append("    userIds: ").append(toIndentedString(this.userIds)).append("\n");
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
