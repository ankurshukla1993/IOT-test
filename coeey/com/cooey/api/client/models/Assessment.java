package com.cooey.api.client.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Assessment {
    @SerializedName("objective")
    private String objective = null;
    @SerializedName("subjective")
    private String subjective = null;

    public Assessment subjective(String subjective) {
        this.subjective = subjective;
        return this;
    }

    @ApiModelProperty("")
    public String getSubjective() {
        return this.subjective;
    }

    public void setSubjective(String subjective) {
        this.subjective = subjective;
    }

    public Assessment objective(String objective) {
        this.objective = objective;
        return this;
    }

    @ApiModelProperty("")
    public String getObjective() {
        return this.objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Assessment assessment = (Assessment) o;
        if (Objects.equals(this.subjective, assessment.subjective) && Objects.equals(this.objective, assessment.objective)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.subjective, this.objective});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Assessment {\n");
        sb.append("    subjective: ").append(toIndentedString(this.subjective)).append("\n");
        sb.append("    objective: ").append(toIndentedString(this.objective)).append("\n");
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
