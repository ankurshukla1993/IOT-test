package com.cooey.api.client.models;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Allergy {
    @SerializedName("level")
    private LevelEnum level = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("symptoms")
    private List<String> symptoms = null;

    @JsonAdapter(Adapter.class)
    public enum LevelEnum {
        LOW("LOW"),
        NORMAL("NORMAL"),
        HIGH("HIGH"),
        SEVERE("SEVERE");
        
        private String value;

        private LevelEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }

        public static LevelEnum fromValue(String text) {
            for (LevelEnum b : values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    public Allergy name(String name) {
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

    public Allergy level(LevelEnum level) {
        this.level = level;
        return this;
    }

    @ApiModelProperty("")
    public LevelEnum getLevel() {
        return this.level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public Allergy symptoms(List<String> symptoms) {
        this.symptoms = symptoms;
        return this;
    }

    public Allergy addSymptomsItem(String symptomsItem) {
        if (this.symptoms == null) {
            this.symptoms = new ArrayList();
        }
        this.symptoms.add(symptomsItem);
        return this;
    }

    @ApiModelProperty("")
    public List<String> getSymptoms() {
        return this.symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Allergy allergy = (Allergy) o;
        if (Objects.equals(this.name, allergy.name) && Objects.equals(this.level, allergy.level) && Objects.equals(this.symptoms, allergy.symptoms)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.level, this.symptoms});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Allergy {\n");
        sb.append("    name: ").append(toIndentedString(this.name)).append("\n");
        sb.append("    level: ").append(toIndentedString(this.level)).append("\n");
        sb.append("    symptoms: ").append(toIndentedString(this.symptoms)).append("\n");
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
