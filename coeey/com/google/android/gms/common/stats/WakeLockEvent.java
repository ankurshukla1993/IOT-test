package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.internal.zzbem;
import java.util.List;

public final class WakeLockEvent extends StatsEvent {
    public static final Creator<WakeLockEvent> CREATOR = new zzd();
    private final long mTimeout;
    private int zzdzm;
    private final long zzgar;
    private int zzgas;
    private final String zzgat;
    private final String zzgau;
    private final String zzgav;
    private final int zzgaw;
    private final List<String> zzgax;
    private final String zzgay;
    private final long zzgaz;
    private int zzgba;
    private final String zzgbb;
    private final float zzgbc;
    private long zzgbd;

    WakeLockEvent(int i, long j, int i2, String str, int i3, List<String> list, String str2, long j2, int i4, String str3, String str4, float f, long j3, String str5) {
        this.zzdzm = i;
        this.zzgar = j;
        this.zzgas = i2;
        this.zzgat = str;
        this.zzgau = str3;
        this.zzgav = str5;
        this.zzgaw = i3;
        this.zzgbd = -1;
        this.zzgax = list;
        this.zzgay = str2;
        this.zzgaz = j2;
        this.zzgba = i4;
        this.zzgbb = str4;
        this.zzgbc = f;
        this.mTimeout = j3;
    }

    public WakeLockEvent(long j, int i, String str, int i2, List<String> list, String str2, long j2, int i3, String str3, String str4, float f, long j3, String str5) {
        this(2, j, i, str, i2, list, str2, j2, i3, str3, str4, f, j3, str5);
    }

    public final int getEventType() {
        return this.zzgas;
    }

    public final long getTimeMillis() {
        return this.zzgar;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, getTimeMillis());
        zzbem.zza(parcel, 4, this.zzgat, false);
        zzbem.zzc(parcel, 5, this.zzgaw);
        zzbem.zzb(parcel, 6, this.zzgax, false);
        zzbem.zza(parcel, 8, this.zzgaz);
        zzbem.zza(parcel, 10, this.zzgau, false);
        zzbem.zzc(parcel, 11, getEventType());
        zzbem.zza(parcel, 12, this.zzgay, false);
        zzbem.zza(parcel, 13, this.zzgbb, false);
        zzbem.zzc(parcel, 14, this.zzgba);
        zzbem.zza(parcel, 15, this.zzgbc);
        zzbem.zza(parcel, 16, this.mTimeout);
        zzbem.zza(parcel, 17, this.zzgav, false);
        zzbem.zzai(parcel, zze);
    }

    public final long zzalr() {
        return this.zzgbd;
    }

    public final String zzals() {
        String str = "\t";
        String str2 = this.zzgat;
        String str3 = "\t";
        int i = this.zzgaw;
        String str4 = "\t";
        String join = this.zzgax == null ? "" : TextUtils.join(",", this.zzgax);
        String str5 = "\t";
        int i2 = this.zzgba;
        String str6 = "\t";
        String str7 = this.zzgau == null ? "" : this.zzgau;
        String str8 = "\t";
        String str9 = this.zzgbb == null ? "" : this.zzgbb;
        String str10 = "\t";
        float f = this.zzgbc;
        String str11 = "\t";
        String str12 = this.zzgav == null ? "" : this.zzgav;
        return new StringBuilder(((((((((((((String.valueOf(str).length() + 37) + String.valueOf(str2).length()) + String.valueOf(str3).length()) + String.valueOf(str4).length()) + String.valueOf(join).length()) + String.valueOf(str5).length()) + String.valueOf(str6).length()) + String.valueOf(str7).length()) + String.valueOf(str8).length()) + String.valueOf(str9).length()) + String.valueOf(str10).length()) + String.valueOf(str11).length()) + String.valueOf(str12).length()).append(str).append(str2).append(str3).append(i).append(str4).append(join).append(str5).append(i2).append(str6).append(str7).append(str8).append(str9).append(str10).append(f).append(str11).append(str12).toString();
    }
}
