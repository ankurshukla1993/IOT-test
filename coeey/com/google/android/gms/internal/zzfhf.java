package com.google.android.gms.internal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class zzfhf<M extends zzfhe<M>, T> {
    public final int tag;
    private int type;
    protected final Class<T> zznan;
    private zzfee<?, ?> zzpbu;
    protected final boolean zzpgz;

    private zzfhf(int i, Class<T> cls, int i2, boolean z) {
        this(11, cls, null, i2, false);
    }

    private zzfhf(int i, Class<T> cls, zzfee<?, ?> com_google_android_gms_internal_zzfee___, int i2, boolean z) {
        this.type = i;
        this.zznan = cls;
        this.tag = i2;
        this.zzpgz = false;
        this.zzpbu = null;
    }

    public static <M extends zzfhe<M>, T extends zzfhk> zzfhf<M, T> zza(int i, Class<T> cls, long j) {
        return new zzfhf(11, cls, (int) j, false);
    }

    private final Object zzan(zzfhb com_google_android_gms_internal_zzfhb) {
        String valueOf;
        Class componentType = this.zzpgz ? this.zznan.getComponentType() : this.zznan;
        try {
            zzfhk com_google_android_gms_internal_zzfhk;
            switch (this.type) {
                case 10:
                    com_google_android_gms_internal_zzfhk = (zzfhk) componentType.newInstance();
                    com_google_android_gms_internal_zzfhb.zza(com_google_android_gms_internal_zzfhk, this.tag >>> 3);
                    return com_google_android_gms_internal_zzfhk;
                case 11:
                    com_google_android_gms_internal_zzfhk = (zzfhk) componentType.newInstance();
                    com_google_android_gms_internal_zzfhb.zza(com_google_android_gms_internal_zzfhk);
                    return com_google_android_gms_internal_zzfhk;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (Throwable e) {
            valueOf = String.valueOf(componentType);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 33).append("Error creating instance of class ").append(valueOf).toString(), e);
        } catch (Throwable e2) {
            valueOf = String.valueOf(componentType);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 33).append("Error creating instance of class ").append(valueOf).toString(), e2);
        } catch (Throwable e22) {
            throw new IllegalArgumentException("Error reading extension field", e22);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfhf)) {
            return false;
        }
        zzfhf com_google_android_gms_internal_zzfhf = (zzfhf) obj;
        return this.type == com_google_android_gms_internal_zzfhf.type && this.zznan == com_google_android_gms_internal_zzfhf.zznan && this.tag == com_google_android_gms_internal_zzfhf.tag && this.zzpgz == com_google_android_gms_internal_zzfhf.zzpgz;
    }

    public final int hashCode() {
        return (this.zzpgz ? 1 : 0) + ((((((this.type + 1147) * 31) + this.zznan.hashCode()) * 31) + this.tag) * 31);
    }

    protected final void zza(Object obj, zzfhc com_google_android_gms_internal_zzfhc) {
        try {
            com_google_android_gms_internal_zzfhc.zzlx(this.tag);
            switch (this.type) {
                case 10:
                    int i = this.tag >>> 3;
                    ((zzfhk) obj).zza(com_google_android_gms_internal_zzfhc);
                    com_google_android_gms_internal_zzfhc.zzz(i, 4);
                    return;
                case 11:
                    com_google_android_gms_internal_zzfhc.zzb((zzfhk) obj);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
        throw new IllegalStateException(e);
    }

    final T zzbp(List<zzfhm> list) {
        int i = 0;
        if (list == null) {
            return null;
        }
        if (this.zzpgz) {
            int i2;
            List arrayList = new ArrayList();
            for (i2 = 0; i2 < list.size(); i2++) {
                zzfhm com_google_android_gms_internal_zzfhm = (zzfhm) list.get(i2);
                if (com_google_android_gms_internal_zzfhm.zzjkl.length != 0) {
                    arrayList.add(zzan(zzfhb.zzbd(com_google_android_gms_internal_zzfhm.zzjkl)));
                }
            }
            i2 = arrayList.size();
            if (i2 == 0) {
                return null;
            }
            T cast = this.zznan.cast(Array.newInstance(this.zznan.getComponentType(), i2));
            while (i < i2) {
                Array.set(cast, i, arrayList.get(i));
                i++;
            }
            return cast;
        } else if (list.isEmpty()) {
            return null;
        } else {
            return this.zznan.cast(zzan(zzfhb.zzbd(((zzfhm) list.get(list.size() - 1)).zzjkl)));
        }
    }

    protected final int zzcn(Object obj) {
        int i = this.tag >>> 3;
        switch (this.type) {
            case 10:
                return (zzfhc.zzkw(i) << 1) + ((zzfhk) obj).zzhl();
            case 11:
                return zzfhc.zzb(i, (zzfhk) obj);
            default:
                throw new IllegalArgumentException("Unknown type " + this.type);
        }
    }
}
