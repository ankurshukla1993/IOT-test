package com.cooey.android.vitals;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.facebook.share.internal.ShareConstants;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\nJ\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\u0010\u0010&\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010'\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010(\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0019JP\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010*J\u0013\u0010+\u001a\u00020\u00072\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020.HÖ\u0001J\t\u0010/\u001a\u00020\u0003HÖ\u0001R\"\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u0006\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\"\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\b\u0010\u0019\"\u0004\b\u001d\u0010\u001bR\u001e\u0010\t\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\t\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0014\"\u0004\b \u0010\u0016R\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0014\"\u0004\b\"\u0010\u0016¨\u00060"}, d2 = {"Lcom/cooey/android/vitals/VitalBlueprint;", "", "id", "", "name", "type", "isGraphRequired", "", "isPrimary", "isSynced", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "fields", "", "Lcom/cooey/android/vitals/Field;", "getFields", "()Ljava/util/List;", "setFields", "(Ljava/util/List;)V", "iconURL", "getIconURL", "()Ljava/lang/String;", "setIconURL", "(Ljava/lang/String;)V", "getId", "setId", "()Ljava/lang/Boolean;", "setGraphRequired", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "setPrimary", "setSynced", "getName", "setName", "getType", "setType", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lcom/cooey/android/vitals/VitalBlueprint;", "equals", "other", "hashCode", "", "toString", "vitals_release"}, k = 1, mv = {1, 1, 7})
@Entity(tableName = "vitalBlueprints")
/* compiled from: VitalBlueprint.kt */
public final class VitalBlueprint {
    @Nullable
    private List<Field> fields;
    @Nullable
    private String iconURL;
    @PrimaryKey
    @NotNull
    private String id;
    @Nullable
    private Boolean isGraphRequired;
    @SerializedName("primary")
    @Nullable
    private Boolean isPrimary;
    @Nullable
    private Boolean isSynced;
    @NotNull
    private String name;
    @NotNull
    private String type;

    @NotNull
    public static /* bridge */ /* synthetic */ VitalBlueprint copy$default(VitalBlueprint vitalBlueprint, String str, String str2, String str3, Boolean bool, Boolean bool2, Boolean bool3, int i, Object obj) {
        return vitalBlueprint.copy((i & 1) != 0 ? vitalBlueprint.id : str, (i & 2) != 0 ? vitalBlueprint.name : str2, (i & 4) != 0 ? vitalBlueprint.type : str3, (i & 8) != 0 ? vitalBlueprint.isGraphRequired : bool, (i & 16) != 0 ? vitalBlueprint.isPrimary : bool2, (i & 32) != 0 ? vitalBlueprint.isSynced : bool3);
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

    @Nullable
    public final Boolean component4() {
        return this.isGraphRequired;
    }

    @Nullable
    public final Boolean component5() {
        return this.isPrimary;
    }

    @Nullable
    public final Boolean component6() {
        return this.isSynced;
    }

    @NotNull
    public final VitalBlueprint copy(@NotNull String id, @NotNull String name, @NotNull String type, @Nullable Boolean isGraphRequired, @Nullable Boolean isPrimary, @Nullable Boolean isSynced) {
        Intrinsics.checkParameterIsNotNull(id, ShareConstants.WEB_DIALOG_PARAM_ID);
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(type, "type");
        return new VitalBlueprint(id, name, type, isGraphRequired, isPrimary, isSynced);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
        r2 = this;
        if (r2 == r3) goto L_0x0044;
    L_0x0002:
        r0 = r3 instanceof com.cooey.android.vitals.VitalBlueprint;
        if (r0 == 0) goto L_0x0046;
    L_0x0006:
        r3 = (com.cooey.android.vitals.VitalBlueprint) r3;
        r0 = r2.id;
        r1 = r3.id;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0046;
    L_0x0012:
        r0 = r2.name;
        r1 = r3.name;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0046;
    L_0x001c:
        r0 = r2.type;
        r1 = r3.type;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0046;
    L_0x0026:
        r0 = r2.isGraphRequired;
        r1 = r3.isGraphRequired;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0046;
    L_0x0030:
        r0 = r2.isPrimary;
        r1 = r3.isPrimary;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0046;
    L_0x003a:
        r0 = r2.isSynced;
        r1 = r3.isSynced;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0046;
    L_0x0044:
        r0 = 1;
    L_0x0045:
        return r0;
    L_0x0046:
        r0 = 0;
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cooey.android.vitals.VitalBlueprint.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        String str = this.id;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        str = this.name;
        hashCode = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        str = this.type;
        hashCode = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        Boolean bool = this.isGraphRequired;
        hashCode = ((bool != null ? bool.hashCode() : 0) + hashCode) * 31;
        bool = this.isPrimary;
        int hashCode2 = ((bool != null ? bool.hashCode() : 0) + hashCode) * 31;
        Boolean bool2 = this.isSynced;
        if (bool2 != null) {
            i = bool2.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "VitalBlueprint(id=" + this.id + ", name=" + this.name + ", type=" + this.type + ", isGraphRequired=" + this.isGraphRequired + ", isPrimary=" + this.isPrimary + ", isSynced=" + this.isSynced + ")";
    }

    public VitalBlueprint(@NotNull String id, @NotNull String name, @NotNull String type, @Nullable Boolean isGraphRequired, @Nullable Boolean isPrimary, @Nullable Boolean isSynced) {
        Intrinsics.checkParameterIsNotNull(id, ShareConstants.WEB_DIALOG_PARAM_ID);
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.id = id;
        this.name = name;
        this.type = type;
        this.isGraphRequired = isGraphRequired;
        this.isPrimary = isPrimary;
        this.isSynced = isSynced;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final void setId(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.id = <set-?>;
    }

    public final void setName(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.name = <set-?>;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    @Nullable
    public final Boolean isGraphRequired() {
        return this.isGraphRequired;
    }

    public final void setGraphRequired(@Nullable Boolean <set-?>) {
        this.isGraphRequired = <set-?>;
    }

    public final void setType(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.type = <set-?>;
    }

    @Nullable
    public final Boolean isPrimary() {
        return this.isPrimary;
    }

    @Nullable
    public final Boolean isSynced() {
        return this.isSynced;
    }

    public final void setPrimary(@Nullable Boolean <set-?>) {
        this.isPrimary = <set-?>;
    }

    public final void setSynced(@Nullable Boolean <set-?>) {
        this.isSynced = <set-?>;
    }

    @Nullable
    public final String getIconURL() {
        return this.iconURL;
    }

    public final void setIconURL(@Nullable String <set-?>) {
        this.iconURL = <set-?>;
    }

    @Nullable
    public final List<Field> getFields() {
        return this.fields;
    }

    public final void setFields(@Nullable List<Field> <set-?>) {
        this.fields = <set-?>;
    }
}
