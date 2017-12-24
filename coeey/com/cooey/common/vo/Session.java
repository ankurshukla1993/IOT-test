package com.cooey.common.vo;

import android.support.annotation.RequiresApi;
import com.cooey.android.users.old.utils.CTConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session {
    static Gson gson = new GsonBuilder().create();
    @SerializedName("accountType")
    private AccountTypeEnum accountType = null;
    @SerializedName("id")
    private String id = null;
    @SerializedName("ownerType")
    private OwnerTypeEnum ownerType = null;
    @SerializedName("permissions")
    private List<String> permissions = new ArrayList();
    @SerializedName("tenantId")
    private String tenantId = null;
    @SerializedName("userId")
    private String userId = null;

    public enum AccountTypeEnum {
        USER("USER"),
        TENANT(CTConstants.TENANT),
        PATIENT(CTConstants.PATIENTTYPE),
        CARE_TAKER(CTConstants.USERTYPE);
        
        private String value;

        private AccountTypeEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }
    }

    public enum OwnerTypeEnum {
        READ("READ"),
        WRITE("WRITE"),
        READWRITE("READWRITE");
        
        private String value;

        private OwnerTypeEnum(String value) {
            this.value = value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }
    }

    public Session id(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Session tenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Session userId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Session accountType(AccountTypeEnum accountType) {
        this.accountType = accountType;
        return this;
    }

    public AccountTypeEnum getAccountType() {
        return this.accountType;
    }

    public void setAccountType(AccountTypeEnum accountType) {
        this.accountType = accountType;
    }

    public Session ownerType(OwnerTypeEnum ownerType) {
        this.ownerType = ownerType;
        return this;
    }

    public OwnerTypeEnum getOwnerType() {
        return this.ownerType;
    }

    public void setOwnerType(OwnerTypeEnum ownerType) {
        this.ownerType = ownerType;
    }

    public Session permissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    public Session addPermissionsItem(String permissionsItem) {
        this.permissions.add(permissionsItem);
        return this;
    }

    public List<String> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Session session = (Session) o;
        return false;
    }

    @RequiresApi(api = 19)
    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.tenantId, this.userId, this.accountType, this.ownerType, this.permissions});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Session {\n");
        sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
        sb.append("    tenantId: ").append(toIndentedString(this.tenantId)).append("\n");
        sb.append("    userId: ").append(toIndentedString(this.userId)).append("\n");
        sb.append("    accountType: ").append(toIndentedString(this.accountType)).append("\n");
        sb.append("    ownerType: ").append(toIndentedString(this.ownerType)).append("\n");
        sb.append("    permissions: ").append(toIndentedString(this.permissions)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public static String toJson(Session session) {
        return gson.toJson(session);
    }

    public static Session fromJson(String sessionJson) {
        return (Session) gson.fromJson(sessionJson, Session.class);
    }
}
