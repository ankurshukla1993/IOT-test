package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzavr;
import com.google.android.gms.internal.zzbem;
import com.google.android.gms.internal.zzbfl;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class zzs extends zzavr {
    public static final Creator<zzs> CREATOR = new zzt();
    private static final HashMap<String, zzbfl<?, ?>> zzebb;
    private String mPackageName;
    private String zzaxu;
    private int zzdzm;
    private Set<Integer> zzebc;
    private zzu zzebm;

    static {
        HashMap hashMap = new HashMap();
        zzebb = hashMap;
        hashMap.put("authenticatorInfo", zzbfl.zza("authenticatorInfo", 2, zzu.class));
        zzebb.put("signature", zzbfl.zzl("signature", 3));
        zzebb.put("package", zzbfl.zzl("package", 4));
    }

    public zzs() {
        this.zzebc = new HashSet(3);
        this.zzdzm = 1;
    }

    zzs(Set<Integer> set, int i, zzu com_google_android_gms_auth_api_accounttransfer_zzu, String str, String str2) {
        this.zzebc = set;
        this.zzdzm = i;
        this.zzebm = com_google_android_gms_auth_api_accounttransfer_zzu;
        this.zzaxu = str;
        this.mPackageName = str2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        Set set = this.zzebc;
        if (set.contains(Integer.valueOf(1))) {
            zzbem.zzc(parcel, 1, this.zzdzm);
        }
        if (set.contains(Integer.valueOf(2))) {
            zzbem.zza(parcel, 2, this.zzebm, i, true);
        }
        if (set.contains(Integer.valueOf(3))) {
            zzbem.zza(parcel, 3, this.zzaxu, true);
        }
        if (set.contains(Integer.valueOf(4))) {
            zzbem.zza(parcel, 4, this.mPackageName, true);
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
                return this.zzebm;
            case 3:
                return this.zzaxu;
            case 4:
                return this.mPackageName;
            default:
                throw new IllegalStateException("Unknown SafeParcelable id=" + com_google_android_gms_internal_zzbfl.zzali());
        }
    }
}
