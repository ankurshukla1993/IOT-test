package com.cooey.android.vitals.services.responses;

import com.facebook.share.internal.ShareConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0007HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003JQ\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007HÆ\u0001J\u0013\u0010#\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\t\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0012\"\u0004\b\u0015\u0010\u0014R\u001a\u0010\n\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\r\"\u0004\b\u001a\u0010\u000f¨\u0006("}, d2 = {"Lcom/cooey/android/vitals/services/responses/VitalBlueprintResponse;", "", "id", "", "name", "type", "isGraphRequired", "", "iconURL", "isPrimary", "isSynced", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;ZZ)V", "getIconURL", "()Ljava/lang/String;", "setIconURL", "(Ljava/lang/String;)V", "getId", "setId", "()Z", "setGraphRequired", "(Z)V", "setPrimary", "setSynced", "getName", "setName", "getType", "setType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalBlueprintResponse.kt */
public final class VitalBlueprintResponse {
    @Nullable
    private String iconURL;
    @NotNull
    private String id;
    private boolean isGraphRequired;
    private boolean isPrimary;
    private boolean isSynced;
    @NotNull
    private String name;
    @NotNull
    private String type;

    @NotNull
    public static /* bridge */ /* synthetic */ VitalBlueprintResponse copy$default(VitalBlueprintResponse vitalBlueprintResponse, String str, String str2, String str3, boolean z, String str4, boolean z2, boolean z3, int i, Object obj) {
        return vitalBlueprintResponse.copy((i & 1) != 0 ? vitalBlueprintResponse.id : str, (i & 2) != 0 ? vitalBlueprintResponse.name : str2, (i & 4) != 0 ? vitalBlueprintResponse.type : str3, (i & 8) != 0 ? vitalBlueprintResponse.isGraphRequired : z, (i & 16) != 0 ? vitalBlueprintResponse.iconURL : str4, (i & 32) != 0 ? vitalBlueprintResponse.isPrimary : z2, (i & 64) != 0 ? vitalBlueprintResponse.isSynced : z3);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final String component3() {
        return this.type;
    }

    public final boolean component4() {
        return this.isGraphRequired;
    }

    @Nullable
    public final String component5() {
        return this.iconURL;
    }

    public final boolean component6() {
        return this.isPrimary;
    }

    public final boolean component7() {
        return this.isSynced;
    }

    @NotNull
    public final VitalBlueprintResponse copy(@NotNull String id, @NotNull String name, @NotNull String type, boolean isGraphRequired, @Nullable String iconURL, boolean isPrimary, boolean isSynced) {
        Intrinsics.checkParameterIsNotNull(id, ShareConstants.WEB_DIALOG_PARAM_ID);
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(type, "type");
        return new VitalBlueprintResponse(id, name, type, isGraphRequired, iconURL, isPrimary, isSynced);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (!(obj instanceof VitalBlueprintResponse)) {
                return false;
            }
            VitalBlueprintResponse vitalBlueprintResponse = (VitalBlueprintResponse) obj;
            if (!Intrinsics.areEqual(this.id, vitalBlueprintResponse.id) || !Intrinsics.areEqual(this.name, vitalBlueprintResponse.name) || !Intrinsics.areEqual(this.type, vitalBlueprintResponse.type)) {
                return false;
            }
            if (!(this.isGraphRequired == vitalBlueprintResponse.isGraphRequired) || !Intrinsics.areEqual(this.iconURL, vitalBlueprintResponse.iconURL)) {
                return false;
            }
            if (!(this.isPrimary == vitalBlueprintResponse.isPrimary)) {
                return false;
            }
            if (!(this.isSynced == vitalBlueprintResponse.isSynced)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 1;
        int i2 = 0;
        String str = this.id;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        str = this.name;
        hashCode = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        str = this.type;
        hashCode = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        int i3 = this.isGraphRequired;
        if (i3 != 0) {
            i3 = 1;
        }
        i3 = (i3 + hashCode) * 31;
        String str2 = this.iconURL;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        i2 = (i3 + i2) * 31;
        i3 = this.isPrimary;
        if (i3 != 0) {
            i3 = 1;
        }
        i2 = (i3 + i2) * 31;
        boolean z = this.isSynced;
        if (!z) {
            boolean z2 = z;
        }
        return i2 + i;
    }

    public String toString() {
        return "VitalBlueprintResponse(id=" + this.id + ", name=" + this.name + ", type=" + this.type + ", isGraphRequired=" + this.isGraphRequired + ", iconURL=" + this.iconURL + ", isPrimary=" + this.isPrimary + ", isSynced=" + this.isSynced + ")";
    }

    public VitalBlueprintResponse(@NotNull String id, @NotNull String name, @NotNull String type, boolean isGraphRequired, @Nullable String iconURL, boolean isPrimary, boolean isSynced) {
        Intrinsics.checkParameterIsNotNull(id, ShareConstants.WEB_DIALOG_PARAM_ID);
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.id = id;
        this.name = name;
        this.type = type;
        this.isGraphRequired = isGraphRequired;
        this.iconURL = iconURL;
        this.isPrimary = isPrimary;
        this.isSynced = isSynced;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public final void setId(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.id = <set-?>;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final void setName(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.name = <set-?>;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    public final void setType(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.type = <set-?>;
    }

    public final boolean isGraphRequired() {
        return this.isGraphRequired;
    }

    public final void setGraphRequired(boolean <set-?>) {
        this.isGraphRequired = <set-?>;
    }

    @Nullable
    public final String getIconURL() {
        return this.iconURL;
    }

    public final void setIconURL(@Nullable String <set-?>) {
        this.iconURL = <set-?>;
    }

    public final boolean isPrimary() {
        return this.isPrimary;
    }

    public final void setPrimary(boolean <set-?>) {
        this.isPrimary = <set-?>;
    }

    public final boolean isSynced() {
        return this.isSynced;
    }

    public final void setSynced(boolean <set-?>) {
        this.isSynced = <set-?>;
    }
}
