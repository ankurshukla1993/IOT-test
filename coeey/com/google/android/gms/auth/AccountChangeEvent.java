package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import java.util.Arrays;

public class AccountChangeEvent extends zzbej {
    public static final Creator<AccountChangeEvent> CREATOR = new zza();
    private int mVersion;
    private long zzdyw;
    private String zzdyx;
    private int zzdyy;
    private int zzdyz;
    private String zzdza;

    AccountChangeEvent(int i, long j, String str, int i2, int i3, String str2) {
        this.mVersion = i;
        this.zzdyw = j;
        this.zzdyx = (String) zzbq.checkNotNull(str);
        this.zzdyy = i2;
        this.zzdyz = i3;
        this.zzdza = str2;
    }

    public AccountChangeEvent(long j, String str, int i, int i2, String str2) {
        this.mVersion = 1;
        this.zzdyw = j;
        this.zzdyx = (String) zzbq.checkNotNull(str);
        this.zzdyy = i;
        this.zzdyz = i2;
        this.zzdza = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AccountChangeEvent)) {
            return false;
        }
        AccountChangeEvent accountChangeEvent = (AccountChangeEvent) obj;
        return this.mVersion == accountChangeEvent.mVersion && this.zzdyw == accountChangeEvent.zzdyw && zzbg.equal(this.zzdyx, accountChangeEvent.zzdyx) && this.zzdyy == accountChangeEvent.zzdyy && this.zzdyz == accountChangeEvent.zzdyz && zzbg.equal(this.zzdza, accountChangeEvent.zzdza);
    }

    public String getAccountName() {
        return this.zzdyx;
    }

    public String getChangeData() {
        return this.zzdza;
    }

    public int getChangeType() {
        return this.zzdyy;
    }

    public int getEventIndex() {
        return this.zzdyz;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mVersion), Long.valueOf(this.zzdyw), this.zzdyx, Integer.valueOf(this.zzdyy), Integer.valueOf(this.zzdyz), this.zzdza});
    }

    public String toString() {
        String str = "UNKNOWN";
        switch (this.zzdyy) {
            case 1:
                str = "ADDED";
                break;
            case 2:
                str = "REMOVED";
                break;
            case 3:
                str = "RENAMED_FROM";
                break;
            case 4:
                str = "RENAMED_TO";
                break;
        }
        String str2 = this.zzdyx;
        String str3 = this.zzdza;
        return new StringBuilder(((String.valueOf(str2).length() + 91) + String.valueOf(str).length()) + String.valueOf(str3).length()).append("AccountChangeEvent {accountName = ").append(str2).append(", changeType = ").append(str).append(", changeData = ").append(str3).append(", eventIndex = ").append(this.zzdyz).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.mVersion);
        zzbem.zza(parcel, 2, this.zzdyw);
        zzbem.zza(parcel, 3, this.zzdyx, false);
        zzbem.zzc(parcel, 4, this.zzdyy);
        zzbem.zzc(parcel, 5, this.zzdyz);
        zzbem.zza(parcel, 6, this.zzdza, false);
        zzbem.zzai(parcel, zze);
    }
}
