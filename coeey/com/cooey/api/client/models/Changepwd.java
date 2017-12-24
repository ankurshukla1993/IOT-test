package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Changepwd {
    @SerializedName("email")
    private String email = null;
    @SerializedName("newPwd")
    private String newPwd = null;
    @SerializedName("oldPwd")
    private String oldPwd = null;

    public Changepwd email(String email) {
        this.email = email;
        return this;
    }

    @ApiModelProperty("")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Changepwd oldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
        return this;
    }

    @ApiModelProperty("")
    public String getOldPwd() {
        return this.oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public Changepwd newPwd(String newPwd) {
        this.newPwd = newPwd;
        return this;
    }

    @ApiModelProperty("")
    public String getNewPwd() {
        return this.newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Changepwd changepwd = (Changepwd) o;
        if (Objects.equals(this.email, changepwd.email) && Objects.equals(this.oldPwd, changepwd.oldPwd) && Objects.equals(this.newPwd, changepwd.newPwd)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.email, this.oldPwd, this.newPwd});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Changepwd {\n");
        sb.append("    email: ").append(toIndentedString(this.email)).append("\n");
        sb.append("    oldPwd: ").append(toIndentedString(this.oldPwd)).append("\n");
        sb.append("    newPwd: ").append(toIndentedString(this.newPwd)).append("\n");
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
