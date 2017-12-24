package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzavr;
import com.google.android.gms.internal.zzbem;
import com.google.android.gms.internal.zzbfl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzm extends zzavr {
    public static final Creator<zzm> CREATOR = new zzn();
    private static final HashMap<String, zzbfl<?, ?>> zzebb;
    private int zzdzm;
    private Set<Integer> zzebc;
    private ArrayList<zzs> zzebd;
    private int zzebe;
    private zzp zzebf;

    static {
        HashMap hashMap = new HashMap();
        zzebb = hashMap;
        hashMap.put("authenticatorData", zzbfl.zzb("authenticatorData", 2, zzs.class));
        zzebb.put("progress", zzbfl.zza("progress", 4, zzp.class));
    }

    public zzm() {
        this.zzebc = new HashSet(1);
        this.zzdzm = 1;
    }

    zzm(Set<Integer> set, int i, ArrayList<zzs> arrayList, int i2, zzp com_google_android_gms_auth_api_accounttransfer_zzp) {
        this.zzebc = set;
        this.zzdzm = i;
        this.zzebd = arrayList;
        this.zzebe = i2;
        this.zzebf = com_google_android_gms_auth_api_accounttransfer_zzp;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        Set set = this.zzebc;
        if (set.contains(Integer.valueOf(1))) {
            zzbem.zzc(parcel, 1, this.zzdzm);
        }
        if (set.contains(Integer.valueOf(2))) {
            zzbem.zzc(parcel, 2, this.zzebd, true);
        }
        if (set.contains(Integer.valueOf(3))) {
            zzbem.zzc(parcel, 3, this.zzebe);
        }
        if (set.contains(Integer.valueOf(4))) {
            zzbem.zza(parcel, 4, this.zzebf, i, true);
        }
        zzbem.zzai(parcel, zze);
    }

    protected final boolean zza(zzbfl com_google_android_gms_internal_zzbfl) {
        return this.zzebc.contains(Integer.valueOf(com_google_android_gms_internal_zzbfl.zzali()));
    }

    public final /* synthetic */ Map zzaaj() {
        return zzebb;
    }

    protected final Object zzb(zzbfl com_google_android_gms_internal_zzbfl) {
        switch (com_google_android_gms_internal_zzbfl.zzali()) {
            case 1:
                return Integer.valueOf(this.zzdzm);
            case 2:
                return this.zzebd;
            case 4:
                return this.zzebf;
            default:
                throw new IllegalStateException("Unknown SafeParcelable id=" + com_google_android_gms_internal_zzbfl.zzali());
        }
    }
}
