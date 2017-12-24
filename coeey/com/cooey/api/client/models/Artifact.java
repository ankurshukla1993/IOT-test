package com.cooey.api.client.models;

import com.cooey.android.users.old.utils.CTConstants;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

public class Artifact {
    @SerializedName("accountType")
    private AccountTypeEnum accountType = null;
    @SerializedName("applicationId")
    private String applicationId = null;
    @SerializedName("changes")
    private String changes = null;
    @SerializedName("createdOn")
    private Long createdOn = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("platform")
    private PlatformEnum platform = null;
    @SerializedName("published")
    private Boolean published = Boolean.valueOf(false);
    @SerializedName("status")
    private StatusEnum status = null;
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("updatedOn")
    private Long updatedOn = null;
    @SerializedName("url")
    private String url = null;
    @SerializedName("version")
    private String version = null;

    @JsonAdapter(Adapter.class)
    public enum AccountTypeEnum {
        PATIENT(CTConstants.PATIENTTYPE),
        TENANT(CTConstants.TENANT),
        GUARDIAN("GUARDIAN"),
        ADMIN("ADMIN"),
        CARE_TAKER(CTConstants.USERTYPE),
        USER("USER");
        
        private String value;

        private AccountTypeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }

        public static AccountTypeEnum fromValue(String text) {
            for (AccountTypeEnum b : values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @JsonAdapter(Adapter.class)
    public enum PlatformEnum {
        ANDROID("ANDROID"),
        IPHONE("IPHONE"),
        WEB("WEB");
        
        private String value;

        private PlatformEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }

        public static PlatformEnum fromValue(String text) {
            for (PlatformEnum b : values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @JsonAdapter(Adapter.class)
    public enum StatusEnum {
        PENDING("PENDING"),
        CODE_CLONED("CODE_CLONED"),
        APK_GENERATED("APK_GENERATED"),
        COMPLETED("COMPLETED");
        
        private String value;

        private StatusEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }

        public static StatusEnum fromValue(String text) {
            for (StatusEnum b : values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    public Artifact createdOn(Long createdOn) {
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

    public Artifact updatedOn(Long updatedOn) {
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

    public Artifact tenantId(String tenantId) {
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

    public Artifact applicationId(String applicationId) {
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

    public Artifact id(String id) {
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

    public Artifact platform(PlatformEnum platform) {
        this.platform = platform;
        return this;
    }

    @ApiModelProperty("")
    public PlatformEnum getPlatform() {
        return this.platform;
    }

    public void setPlatform(PlatformEnum platform) {
        this.platform = platform;
    }

    public Artifact status(StatusEnum status) {
        this.status = status;
        return this;
    }

    @ApiModelProperty("")
    public StatusEnum getStatus() {
        return this.status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Artifact url(String url) {
        this.url = url;
        return this;
    }

    @ApiModelProperty("")
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Artifact version(String version) {
        this.version = version;
        return this;
    }

    @ApiModelProperty("")
    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Artifact changes(String changes) {
        this.changes = changes;
        return this;
    }

    @ApiModelProperty("")
    public String getChanges() {
        return this.changes;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }

    public Artifact accountType(AccountTypeEnum accountType) {
        this.accountType = accountType;
        return this;
    }

    @ApiModelProperty("")
    public AccountTypeEnum getAccountType() {
        return this.accountType;
    }

    public void setAccountType(AccountTypeEnum accountType) {
        this.accountType = accountType;
    }

    public Artifact published(Boolean published) {
        this.published = published;
        return this;
    }

    @ApiModelProperty("")
    public Boolean getPublished() {
        return this.published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Artifact artifact = (Artifact) o;
        if (Objects.equals(this.createdOn, artifact.createdOn) && Objects.equals(this.updatedOn, artifact.updatedOn) && Objects.equals(this.tenantId, artifact.tenantId) && Objects.equals(this.applicationId, artifact.applicationId) && Objects.equals(this.id, artifact.id) && Objects.equals(this.platform, artifact.platform) && Objects.equals(this.status, artifact.status) && Objects.equals(this.url, artifact.url) && Objects.equals(this.version, artifact.version) && Objects.equals(this.changes, artifact.changes) && Objects.equals(this.accountType, artifact.accountType) && Objects.equals(this.published, artifact.published)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.createdOn, this.updatedOn, this.tenantId, this.applicationId, this.id, this.platform, this.status, this.url, this.version, this.changes, this.accountType, this.published});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Artifact {\n");
        sb.append("    createdOn: ").append(toIndentedString(this.createdOn)).append("\n");
        sb.append("    updatedOn: ").append(toIndentedString(this.updatedOn)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    applicationId: ").append(toIndentedString(this.applicationId)).append("\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    platform: ").append(toIndentedString(this.platform)).append("\n");
        sb.append("    status: ").append(toIndentedString(this.status)).append("\n");
        sb.append("    url: ").append(toIndentedString(this.url)).append("\n");
        sb.append("    version: ").append(toIndentedString(this.version)).append("\n");
        sb.append("    changes: ").append(toIndentedString(this.changes)).append("\n");
        sb.append("    accountType: ").append(toIndentedString(this.accountType)).append("\n");
        sb.append("    published: ").append(toIndentedString(this.published)).append("\n");
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
