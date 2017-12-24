package com.google.android.gms.auth.api.accounttransfer;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.util.ArraySet;
import com.google.android.gms.internal.zzavr;
import com.google.android.gms.internal.zzbem;
import com.google.android.gms.internal.zzbfl;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class zzu extends zzavr {
    public static final Creator<zzu> CREATOR = new zzv();
    private static final HashMap<String, zzbfl<?, ?>> zzebb;
    private int zzbzn;
    private int zzdzm;
    private Set<Integer> zzebc;
    private String zzebn;
    private byte[] zzebo;
    private PendingIntent zzebp;
    private DeviceMetaData zzebq;

    static {
        HashMap hashMap = new HashMap();
        zzebb = hashMap;
        hashMap.put("accountType", zzbfl.zzl("accountType", 2));
        zzebb.put("status", zzbfl.zzj("status", 3));
        zzebb.put("transferBytes", zzbfl.zzn("transferBytes", 4));
    }

    public zzu() {
        this.zzebc = new ArraySet(3);
        this.zzdzm = 1;
    }

    zzu(Set<Integer> set, int i, String str, int i2, byte[] bArr, PendingIntent pendingIntent, DeviceMetaData deviceMetaData) {
        this.zzebc = set;
        this.zzdzm = i;
        this.zzebn = str;
        this.zzbzn = i2;
        this.zzebo = bArr;
        this.zzebp = pendingIntent;
        this.zzebq = deviceMetaData;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        Set set = this.zzebc;
        if (set.contains(Integer.valueOf(1))) {
            zzbem.zzc(parcel, 1, this.zzdzm);
        }
        if (set.contains(Integer.valueOf(2))) {
            zzbem.zza(parcel, 2, this.zzebn, true);
        }
        if (set.contains(Integer.valueOf(3))) {
            zzbem.zzc(parcel, 3, this.zzbzn);
        }
        if (set.contains(Integer.valueOf(4))) {
            zzbem.zza(parcel, 4, this.zzebo, true);
        }
        if (set.contains(Integer.valueOf(5))) {
            zzbem.zza(parcel, 5, this.zzebp, i, true);
        }
        if (set.contains(Integer.valueOf(6))) {
            zzbem.zza(parcel, 6, this.zzebq, i, true);
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
                return this.zzebn;
            case 3:
                return Integer.valueOf(this.zzbzn);
            case 4:
                return this.zzebo;
            default:
                throw new IllegalStateException("Unknown SafeParcelable id=" + com_google_android_gms_internal_zzbfl.zzali());
        }
    }
}
