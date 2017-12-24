package com.cooey.api.client.models;

import com.facebook.share.internal.ShareConstants;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class ContentTemplate {
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("template")
    private Object template = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("type")
    private TypeEnum type = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;

    @JsonAdapter(Adapter.class)
    public enum TypeEnum {
        BLOG("BLOG"),
        VIDEO(ShareConstants.VIDEO_URL);
        
        private String value;

        private TypeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }

        public static TypeEnum fromValue(String text) {
            for (TypeEnum b : values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    public ContentTemplate createdOn(Long createdOn) {
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

    public ContentTemplate updatedOn(Long updatedOn) {
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

    public ContentTemplate tenantId(String tenantId) {
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

    public ContentTemplate applicationId(String applicationId) {
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

    public ContentTemplate id(String id) {
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

    public ContentTemplate type(TypeEnum type) {
        this.type = type;
        return this;
    }

    @ApiModelProperty("")
    public TypeEnum getType() {
        return this.type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public ContentTemplate template(Object template) {
        this.template = template;
        return this;
    }

    @ApiModelProperty("")
    public Object getTemplate() {
        return this.template;
    }

    public void setTemplate(Object template) {
        this.template = template;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContentTemplate contentTemplate = (ContentTemplate) o;
        if (Objects.equals(this.createdOn, contentTemplate.createdOn) && Objects.equals(this.updatedOn, contentTemplate.updatedOn) && Objects.equals(this.tenantId, contentTemplate.tenantId) && Objects.equals(this.applicationId, contentTemplate.applicationId) && Objects.equals(this.id, contentTemplate.id) && Objects.equals(this.type, contentTemplate.type) && Objects.equals(this.template, contentTemplate.template)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.type, this.template});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ContentTemplate {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    type: ").append(toIndentedString(this.type)).append("\n");
        sb.append("    template: ").append(toIndentedString(this.template)).append("\n");
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
