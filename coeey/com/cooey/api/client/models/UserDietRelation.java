package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class UserDietRelation {
    @SerializedName("dietTemplateId")
    private String dietTemplateId = null;
    @SerializedName("end")
    private Long end = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("ownerId")
    private String ownerId = null;
    @SerializedName("start")
    private Long start = null;

    public UserDietRelation id(String id) {
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

    public UserDietRelation ownerId(String ownerId) {
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

    public UserDietRelation dietTemplateId(String dietTemplateId) {
        this.dietTemplateId = dietTemplateId;
        return this;
    }

    @ApiModelProperty("")
    public String getDietTemplateId() {
        return this.dietTemplateId;
    }

    public void setDietTemplateId(String dietTemplateId) {
        this.dietTemplateId = dietTemplateId;
    }

    public UserDietRelation start(Long start) {
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

    public UserDietRelation end(Long end) {
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
        UserDietRelation userDietRelation = (UserDietRelation) o;
        if (Objects.equals(this.id, userDietRelation.id) && Objects.equals(this.ownerId, userDietRelation.ownerId) && Objects.equals(this.dietTemplateId, userDietRelation.dietTemplateId) && Objects.equals(this.start, userDietRelation.start) && Objects.equals(this.end, userDietRelation.end)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.ownerId, this.dietTemplateId, this.start, this.end});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserDietRelation {\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    ownerId: ").append(toIndentedString(this.ownerId)).append("\n");
        sb.append("    dietTemplateId: ").append(toIndentedString(this.dietTemplateId)).append("\n");
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
