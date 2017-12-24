package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Permission {
    @SerializedName("canRead")
    private Boolean canRead = Boolean.valueOf(false);
    @SerializedName("canWrite")
    private Boolean canWrite = Boolean.valueOf(false);
    @SerializedName("name")
    private String name = null;

    public Permission name(String name) {
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

    public Permission canRead(Boolean canRead) {
        this.canRead = canRead;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getCanRead() {
        return this.canRead;
    }

    public void setCanRead(Boolean canRead) {
        this.canRead = canRead;
    }

    public Permission canWrite(Boolean canWrite) {
        this.canWrite = canWrite;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getCanWrite() {
        return this.canWrite;
    }

    public void setCanWrite(Boolean canWrite) {
        this.canWrite = canWrite;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Permission permission = (Permission) o;
        if (Objects.equals(this.name, permission.name) && Objects.equals(this.canRead, permission.canRead) && Objects.equals(this.canWrite, permission.canWrite)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.canRead, this.canWrite});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Permission {\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    canRead: ").append(toIndentedString(this.canRead)).append("\n");
        sb.append("    canWrite: ").append(toIndentedString(this.canWrite)).append("\n");
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
