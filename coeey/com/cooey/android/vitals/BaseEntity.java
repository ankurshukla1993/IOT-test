package com.cooey.android.vitals;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0015\b\u0016\u0018\u00002\u00020\u0001BQ\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u000b\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0017\"\u0004\b\u001a\u0010\u0019R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0012\"\u0004\b\u001e\u0010\u0014¨\u0006\u001f"}, d2 = {"Lcom/cooey/android/vitals/BaseEntity;", "", "createdOn", "", "updatedOn", "tenantId", "", "applicationId", "externalId", "isActive", "", "isArchived", "(JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getApplicationId", "()Ljava/lang/String;", "setApplicationId", "(Ljava/lang/String;)V", "getCreatedOn", "()J", "setCreatedOn", "(J)V", "getExternalId", "setExternalId", "()Z", "setActive", "(Z)V", "setArchived", "getTenantId", "setTenantId", "getUpdatedOn", "setUpdatedOn", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: BaseEntity.kt */
public class BaseEntity {
    @Nullable
    private String applicationId;
    private long createdOn;
    @Nullable
    private String externalId;
    private boolean isActive;
    private boolean isArchived;
    @Nullable
    private String tenantId;
    private long updatedOn;

    public BaseEntity() {
        this(0, 0, null, null, null, false, false, 127, null);
    }

    public BaseEntity(long createdOn, long updatedOn, @Nullable String tenantId, @Nullable String applicationId, @Nullable String externalId, boolean isActive, boolean isArchived) {
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.tenantId = tenantId;
        this.applicationId = applicationId;
        this.externalId = externalId;
        this.isActive = isActive;
        this.isArchived = isArchived;
    }

    public /* synthetic */ BaseEntity(long j, long j2, String str, String str2, String str3, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        long j3;
        long j4;
        String str4;
        String str5;
        String str6;
        boolean z3;
        if ((i & 1) != 0) {
            j3 = 0;
        } else {
            j3 = j;
        }
        if ((i & 2) != 0) {
            j4 = 0;
        } else {
            j4 = j2;
        }
        if ((i & 4) != 0) {
            str4 = (String) null;
        } else {
            str4 = str;
        }
        if ((i & 8) != 0) {
            str5 = (String) null;
        } else {
            str5 = str2;
        }
        if ((i & 16) != 0) {
            str6 = (String) null;
        } else {
            str6 = str3;
        }
        if ((i & 32) != 0) {
            z3 = true;
        } else {
            z3 = z;
        }
        this(j3, j4, str4, str5, str6, z3, (i & 64) != 0 ? false : z2);
    }

    public long getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(long <set-?>) {
        this.createdOn = <set-?>;
    }

    public final long getUpdatedOn() {
        return this.updatedOn;
    }

    public final void setUpdatedOn(long <set-?>) {
        this.updatedOn = <set-?>;
    }

    @Nullable
    public final String getTenantId() {
        return this.tenantId;
    }

    public final void setTenantId(@Nullable String <set-?>) {
        this.tenantId = <set-?>;
    }

    @Nullable
    public final String getApplicationId() {
        return this.applicationId;
    }

    public final void setApplicationId(@Nullable String <set-?>) {
        this.applicationId = <set-?>;
    }

    @Nullable
    public final String getExternalId() {
        return this.externalId;
    }

    public final void setExternalId(@Nullable String <set-?>) {
        this.externalId = <set-?>;
    }

    public final boolean isActive() {
        return this.isActive;
    }

    public final void setActive(boolean <set-?>) {
        this.isActive = <set-?>;
    }

    public final boolean isArchived() {
        return this.isArchived;
    }

    public final void setArchived(boolean <set-?>) {
        this.isArchived = <set-?>;
    }
}
