package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class TaskStatus {
    @SerializedName("completedTask")
    private String completedTask = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("totalTask")
    private String totalTask = null;

    public TaskStatus totalTask(String totalTask) {
        this.totalTask = totalTask;
        return this;
    }

    @ApiModelProperty("")
    public String getTotalTask() {
        return this.totalTask;
    }

    public void setTotalTask(String totalTask) {
        this.totalTask = totalTask;
    }

    public TaskStatus completedTask(String completedTask) {
        this.completedTask = completedTask;
        return this;
    }

    @ApiModelProperty("")
    public String getCompletedTask() {
        return this.completedTask;
    }

    public void setCompletedTask(String completedTask) {
        this.completedTask = completedTask;
    }

    public TaskStatus id(String id) {
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

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskStatus taskStatus = (TaskStatus) o;
        if (Objects.equals(this.totalTask, taskStatus.totalTask) && Objects.equals(this.completedTask, taskStatus.completedTask) && Objects.equals(this.id, taskStatus.id)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.totalTask, this.completedTask, this.id});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TaskStatus {\n");
        sb.append("    totalTask: ").append(toIndentedString(this.totalTask)).append("\n");
        sb.append("    completedTask: ").append(toIndentedString(this.completedTask)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
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
