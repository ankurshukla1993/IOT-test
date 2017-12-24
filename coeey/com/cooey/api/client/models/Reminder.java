package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reminder {
    @SerializedName("activeDays")
    private List<Boolean> activeDays = null;
    @SerializedName("timeOfDay")
    private String timeOfDay = null;

    public Reminder timeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
        return this;
    }

    @ApiModelProperty("")
    public String getTimeOfDay() {
        return this.timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public Reminder activeDays(List<Boolean> activeDays) {
        this.activeDays = activeDays;
        return this;
    }

    public Reminder addActiveDaysItem(Boolean activeDaysItem) {
        if (this.activeDays == null) {
            this.activeDays = new ArrayList();
        }
        this.activeDays.add(activeDaysItem);
        return this;
    }

    @ApiModelProperty("")
    public List<Boolean> getActiveDays() {
        return this.activeDays;
    }

    public void setActiveDays(List<Boolean> activeDays) {
        this.activeDays = activeDays;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reminder reminder = (Reminder) o;
        if (Objects.equals(this.timeOfDay, reminder.timeOfDay) && Objects.equals(this.activeDays, reminder.activeDays)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.timeOfDay, this.activeDays});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Reminder {\n");
        sb.append("    timeOfDay: ").append(toIndentedString(this.timeOfDay)).append("\n");
        sb.append("    activeDays: ").append(toIndentedString(this.activeDays)).append("\n");
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
