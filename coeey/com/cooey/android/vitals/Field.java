package com.cooey.android.vitals;

import android.arch.persistence.room.Entity;
import com.ihealth.communication.control.AmProfile;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010¨\u0006 "}, d2 = {"Lcom/cooey/android/vitals/Field;", "", "label", "", "unit", "key", "dataType", "Lcom/cooey/android/vitals/DataType;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/cooey/android/vitals/DataType;)V", "getDataType", "()Lcom/cooey/android/vitals/DataType;", "setDataType", "(Lcom/cooey/android/vitals/DataType;)V", "getKey", "()Ljava/lang/String;", "setKey", "(Ljava/lang/String;)V", "getLabel", "setLabel", "getUnit", "setUnit", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "vitals_release"}, k = 1, mv = {1, 1, 7})
@Entity
/* compiled from: Field.kt */
public final class Field {
    @NotNull
    private DataType dataType;
    @NotNull
    private String key;
    @NotNull
    private String label;
    @NotNull
    private String unit;

    @NotNull
    public static /* bridge */ /* synthetic */ Field copy$default(Field field, String str, String str2, String str3, DataType dataType, int i, Object obj) {
        if ((i & 1) != 0) {
            str = field.label;
        }
        if ((i & 2) != 0) {
            str2 = field.unit;
        }
        if ((i & 4) != 0) {
            str3 = field.key;
        }
        if ((i & 8) != 0) {
            dataType = field.dataType;
        }
        return field.copy(str, str2, str3, dataType);
    }

    @NotNull
    public final String component1() {
        return this.label;
    }

    @NotNull
    public final String component2() {
        return this.unit;
    }

    @NotNull
    public final String component3() {
        return this.key;
    }

    @NotNull
    public final DataType component4() {
        return this.dataType;
    }

    @NotNull
    public final Field copy(@NotNull String label, @NotNull String unit, @NotNull String key, @NotNull DataType dataType) {
        Intrinsics.checkParameterIsNotNull(label, "label");
        Intrinsics.checkParameterIsNotNull(unit, AmProfile.GET_USER_UNIT_AM);
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(dataType, "dataType");
        return new Field(label, unit, key, dataType);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
        r2 = this;
        if (r2 == r3) goto L_0x0030;
    L_0x0002:
        r0 = r3 instanceof com.cooey.android.vitals.Field;
        if (r0 == 0) goto L_0x0032;
    L_0x0006:
        r3 = (com.cooey.android.vitals.Field) r3;
        r0 = r2.label;
        r1 = r3.label;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0032;
    L_0x0012:
        r0 = r2.unit;
        r1 = r3.unit;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0032;
    L_0x001c:
        r0 = r2.key;
        r1 = r3.key;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0032;
    L_0x0026:
        r0 = r2.dataType;
        r1 = r3.dataType;
        r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1);
        if (r0 == 0) goto L_0x0032;
    L_0x0030:
        r0 = 1;
    L_0x0031:
        return r0;
    L_0x0032:
        r0 = 0;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cooey.android.vitals.Field.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        String str = this.label;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        str = this.unit;
        hashCode = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        str = this.key;
        int hashCode2 = ((str != null ? str.hashCode() : 0) + hashCode) * 31;
        DataType dataType = this.dataType;
        if (dataType != null) {
            i = dataType.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "Field(label=" + this.label + ", unit=" + this.unit + ", key=" + this.key + ", dataType=" + this.dataType + ")";
    }

    public Field(@NotNull String label, @NotNull String unit, @NotNull String key, @NotNull DataType dataType) {
        Intrinsics.checkParameterIsNotNull(label, "label");
        Intrinsics.checkParameterIsNotNull(unit, AmProfile.GET_USER_UNIT_AM);
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(dataType, "dataType");
        this.label = label;
        this.unit = unit;
        this.key = key;
        this.dataType = dataType;
    }

    @NotNull
    public final String getLabel() {
        return this.label;
    }

    public final void setLabel(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.label = <set-?>;
    }

    @NotNull
    public final String getUnit() {
        return this.unit;
    }

    public final void setUnit(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.unit = <set-?>;
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }

    public final void setKey(@NotNull String <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.key = <set-?>;
    }

    @NotNull
    public final DataType getDataType() {
        return this.dataType;
    }

    public final void setDataType(@NotNull DataType <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.dataType = <set-?>;
    }
}
