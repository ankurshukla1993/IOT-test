package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.facebook.GraphResponse;
import com.google.android.gms.internal.zzavr;
import com.google.android.gms.internal.zzbem;
import com.google.android.gms.internal.zzbfl;
import java.util.List;
import java.util.Map;

public class zzp extends zzavr {
    public static final Creator<zzp> CREATOR = new zzq();
    private static final ArrayMap<String, zzbfl<?, ?>> zzebg;
    private int zzdzm;
    private List<String> zzebh;
    private List<String> zzebi;
    private List<String> zzebj;
    private List<String> zzebk;
    private List<String> zzebl;

    static {
        ArrayMap arrayMap = new ArrayMap();
        zzebg = arrayMap;
        arrayMap.put("registered", zzbfl.zzm("registered", 2));
        zzebg.put("in_progress", zzbfl.zzm("in_progress", 3));
        zzebg.put(GraphResponse.SUCCESS_KEY, zzbfl.zzm(GraphResponse.SUCCESS_KEY, 4));
        zzebg.put("failed", zzbfl.zzm("failed", 5));
        zzebg.put("escrowed", zzbfl.zzm("escrowed", 6));
    }

    public zzp() {
        this.zzdzm = 1;
    }

    zzp(int i, @Nullable List<String> list, @Nullable List<String> list2, @Nullable List<String> list3, @Nullable List<String> list4, @Nullable List<String> list5) {
        this.zzdzm = i;
        this.zzebh = list;
        this.zzebi = list2;
        this.zzebj = list3;
        this.zzebk = list4;
        this.zzebl = list5;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zzb(parcel, 2, this.zzebh, false);
        zzbem.zzb(parcel, 3, this.zzebi, false);
        zzbem.zzb(parcel, 4, this.zzebj, false);
        zzbem.zzb(parcel, 5, this.zzebk, false);
        zzbem.zzb(parcel, 6, this.zzebl, false);
        zzbem.zzai(parcel, zze);
    }

    protected final boolean zza(zzbfl com_google_android_gms_internal_zzbfl) {
        return true;
    }

    public final Map<String, zzbfl<?, ?>> zzaaj() {
        return zzebg;
    }

    protected final Object zzb(zzbfl com_google_android_gms_internal_zzbfl) {
        switch (com_google_android_gms_internal_zzbfl.zzali()) {
            case 1:
                return Integer.valueOf(this.zzdzm);
            case 2:
                return this.zzebh;
            case 3:
                return this.zzebi;
            case 4:
                return this.zzebj;
            case 5:
                return this.zzebk;
            case 6:
                return this.zzebl;
            default:
                throw new IllegalStateException("Unknown SafeParcelable id=" + com_google_android_gms_internal_zzbfl.zzali());
        }
    }
}
