package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzfeb<FieldDescriptorType extends zzfed<FieldDescriptorType>> {
    private static final zzfeb zzpbp = new zzfeb(true);
    private boolean zzkqq;
    private final zzffu<FieldDescriptorType, Object> zzpbn = zzffu.zzlp(16);
    private boolean zzpbo = false;

    private zzfeb() {
    }

    private zzfeb(boolean z) {
        if (!this.zzkqq) {
            this.zzpbn.zzbim();
            this.zzkqq = true;
        }
    }

    static int zza(zzfgr com_google_android_gms_internal_zzfgr, int i, Object obj) {
        int i2;
        int zzkw = zzfdv.zzkw(i);
        if (com_google_android_gms_internal_zzfgr == zzfgr.GROUP) {
            zzfer.zzg((zzffi) obj);
            i2 = zzkw << 1;
        } else {
            i2 = zzkw;
        }
        switch (zzfec.zzpbr[com_google_android_gms_internal_zzfgr.ordinal()]) {
            case 1:
                zzkw = zzfdv.zzn(((Double) obj).doubleValue());
                break;
            case 2:
                zzkw = zzfdv.zzf(((Float) obj).floatValue());
                break;
            case 3:
                zzkw = zzfdv.zzcv(((Long) obj).longValue());
                break;
            case 4:
                zzkw = zzfdv.zzcw(((Long) obj).longValue());
                break;
            case 5:
                zzkw = zzfdv.zzkx(((Integer) obj).intValue());
                break;
            case 6:
                zzkw = zzfdv.zzcy(((Long) obj).longValue());
                break;
            case 7:
                zzkw = zzfdv.zzla(((Integer) obj).intValue());
                break;
            case 8:
                zzkw = zzfdv.zzda(((Boolean) obj).booleanValue());
                break;
            case 9:
                zzkw = zzfdv.zzf((zzffi) obj);
                break;
            case 10:
                if (!(obj instanceof zzfey)) {
                    zzkw = zzfdv.zze((zzffi) obj);
                    break;
                }
                zzkw = zzfdv.zza((zzfey) obj);
                break;
            case 11:
                if (!(obj instanceof zzfdh)) {
                    zzkw = zzfdv.zztd((String) obj);
                    break;
                }
                zzkw = zzfdv.zzan((zzfdh) obj);
                break;
            case 12:
                if (!(obj instanceof zzfdh)) {
                    zzkw = zzfdv.zzbc((byte[]) obj);
                    break;
                }
                zzkw = zzfdv.zzan((zzfdh) obj);
                break;
            case 13:
                zzkw = zzfdv.zzky(((Integer) obj).intValue());
                break;
            case 14:
                zzkw = zzfdv.zzlb(((Integer) obj).intValue());
                break;
            case 15:
                zzkw = zzfdv.zzcz(((Long) obj).longValue());
                break;
            case 16:
                zzkw = zzfdv.zzkz(((Integer) obj).intValue());
                break;
            case 17:
                zzkw = zzfdv.zzcx(((Long) obj).longValue());
                break;
            case 18:
                if (!(obj instanceof zzfes)) {
                    zzkw = zzfdv.zzlc(((Integer) obj).intValue());
                    break;
                }
                zzkw = zzfdv.zzlc(((zzfes) obj).zzhn());
                break;
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
        return i2 + zzkw;
    }

    public static Object zza(zzfdq com_google_android_gms_internal_zzfdq, zzfgr com_google_android_gms_internal_zzfgr, boolean z) throws IOException {
        zzfgx com_google_android_gms_internal_zzfgx = zzfgx.STRICT;
        switch (zzfgq.zzpbr[com_google_android_gms_internal_zzfgr.ordinal()]) {
            case 1:
                return Double.valueOf(com_google_android_gms_internal_zzfdq.readDouble());
            case 2:
                return Float.valueOf(com_google_android_gms_internal_zzfdq.readFloat());
            case 3:
                return Long.valueOf(com_google_android_gms_internal_zzfdq.zzctu());
            case 4:
                return Long.valueOf(com_google_android_gms_internal_zzfdq.zzctt());
            case 5:
                return Integer.valueOf(com_google_android_gms_internal_zzfdq.zzctv());
            case 6:
                return Long.valueOf(com_google_android_gms_internal_zzfdq.zzctw());
            case 7:
                return Integer.valueOf(com_google_android_gms_internal_zzfdq.zzctx());
            case 8:
                return Boolean.valueOf(com_google_android_gms_internal_zzfdq.zzcty());
            case 9:
                return com_google_android_gms_internal_zzfdq.zzcua();
            case 10:
                return Integer.valueOf(com_google_android_gms_internal_zzfdq.zzcub());
            case 11:
                return Integer.valueOf(com_google_android_gms_internal_zzfdq.zzcud());
            case 12:
                return Long.valueOf(com_google_android_gms_internal_zzfdq.zzcue());
            case 13:
                return Integer.valueOf(com_google_android_gms_internal_zzfdq.zzcuf());
            case 14:
                return Long.valueOf(com_google_android_gms_internal_zzfdq.zzcug());
            case 15:
                return com_google_android_gms_internal_zzfgx.zzb(com_google_android_gms_internal_zzfdq);
            case 16:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
            case 17:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
            case 18:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    static void zza(zzfdv com_google_android_gms_internal_zzfdv, zzfgr com_google_android_gms_internal_zzfgr, int i, Object obj) throws IOException {
        int i2 = 0;
        if (com_google_android_gms_internal_zzfgr == zzfgr.GROUP) {
            zzfer.zzg((zzffi) obj);
            zzffi com_google_android_gms_internal_zzffi = (zzffi) obj;
            com_google_android_gms_internal_zzfdv.zzz(i, 3);
            com_google_android_gms_internal_zzffi.zza(com_google_android_gms_internal_zzfdv);
            com_google_android_gms_internal_zzfdv.zzz(i, 4);
            return;
        }
        com_google_android_gms_internal_zzfdv.zzz(i, com_google_android_gms_internal_zzfgr.zzcxd());
        switch (zzfec.zzpbr[com_google_android_gms_internal_zzfgr.ordinal()]) {
            case 1:
                com_google_android_gms_internal_zzfdv.zzcu(Double.doubleToRawLongBits(((Double) obj).doubleValue()));
                return;
            case 2:
                com_google_android_gms_internal_zzfdv.zzkv(Float.floatToRawIntBits(((Float) obj).floatValue()));
                return;
            case 3:
                com_google_android_gms_internal_zzfdv.zzcs(((Long) obj).longValue());
                return;
            case 4:
                com_google_android_gms_internal_zzfdv.zzcs(((Long) obj).longValue());
                return;
            case 5:
                com_google_android_gms_internal_zzfdv.zzks(((Integer) obj).intValue());
                return;
            case 6:
                com_google_android_gms_internal_zzfdv.zzcu(((Long) obj).longValue());
                return;
            case 7:
                com_google_android_gms_internal_zzfdv.zzkv(((Integer) obj).intValue());
                return;
            case 8:
                if (((Boolean) obj).booleanValue()) {
                    i2 = 1;
                }
                com_google_android_gms_internal_zzfdv.zzb((byte) i2);
                return;
            case 9:
                ((zzffi) obj).zza(com_google_android_gms_internal_zzfdv);
                return;
            case 10:
                com_google_android_gms_internal_zzfdv.zzd((zzffi) obj);
                return;
            case 11:
                if (obj instanceof zzfdh) {
                    com_google_android_gms_internal_zzfdv.zzam((zzfdh) obj);
                    return;
                } else {
                    com_google_android_gms_internal_zzfdv.zztc((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzfdh) {
                    com_google_android_gms_internal_zzfdv.zzam((zzfdh) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                com_google_android_gms_internal_zzfdv.zzi(bArr, 0, bArr.length);
                return;
            case 13:
                com_google_android_gms_internal_zzfdv.zzkt(((Integer) obj).intValue());
                return;
            case 14:
                com_google_android_gms_internal_zzfdv.zzkv(((Integer) obj).intValue());
                return;
            case 15:
                com_google_android_gms_internal_zzfdv.zzcu(((Long) obj).longValue());
                return;
            case 16:
                com_google_android_gms_internal_zzfdv.zzku(((Integer) obj).intValue());
                return;
            case 17:
                com_google_android_gms_internal_zzfdv.zzct(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzfes) {
                    com_google_android_gms_internal_zzfdv.zzks(((zzfes) obj).zzhn());
                    return;
                } else {
                    com_google_android_gms_internal_zzfdv.zzks(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    private void zza(FieldDescriptorType fieldDescriptorType, Object obj) {
        Object obj2;
        if (!fieldDescriptorType.zzcvc()) {
            zza(fieldDescriptorType.zzcvb(), obj);
            obj2 = obj;
        } else if (obj instanceof List) {
            obj2 = new ArrayList();
            obj2.addAll((List) obj);
            ArrayList arrayList = (ArrayList) obj2;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj3 = arrayList.get(i);
                i++;
                zza(fieldDescriptorType.zzcvb(), obj3);
            }
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj2 instanceof zzfey) {
            this.zzpbo = true;
        }
        this.zzpbn.zza((Comparable) fieldDescriptorType, obj2);
    }

    private static void zza(zzfgr com_google_android_gms_internal_zzfgr, Object obj) {
        boolean z = false;
        zzfer.checkNotNull(obj);
        switch (zzfec.zzpbq[com_google_android_gms_internal_zzfgr.zzcxc().ordinal()]) {
            case 1:
                z = obj instanceof Integer;
                break;
            case 2:
                z = obj instanceof Long;
                break;
            case 3:
                z = obj instanceof Float;
                break;
            case 4:
                z = obj instanceof Double;
                break;
            case 5:
                z = obj instanceof Boolean;
                break;
            case 6:
                z = obj instanceof String;
                break;
            case 7:
                if ((obj instanceof zzfdh) || (obj instanceof byte[])) {
                    z = true;
                    break;
                }
            case 8:
                if ((obj instanceof Integer) || (obj instanceof zzfes)) {
                    z = true;
                    break;
                }
            case 9:
                if ((obj instanceof zzffi) || (obj instanceof zzfey)) {
                    z = true;
                    break;
                }
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public static <T extends zzfed<T>> zzfeb<T> zzcva() {
        return new zzfeb();
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzfeb com_google_android_gms_internal_zzfeb = new zzfeb();
        for (int i = 0; i < this.zzpbn.zzcwj(); i++) {
            Entry zzlq = this.zzpbn.zzlq(i);
            com_google_android_gms_internal_zzfeb.zza((zzfed) zzlq.getKey(), zzlq.getValue());
        }
        for (Entry entry : this.zzpbn.zzcwk()) {
            com_google_android_gms_internal_zzfeb.zza((zzfed) entry.getKey(), entry.getValue());
        }
        com_google_android_gms_internal_zzfeb.zzpbo = this.zzpbo;
        return com_google_android_gms_internal_zzfeb;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfeb)) {
            return false;
        }
        return this.zzpbn.equals(((zzfeb) obj).zzpbn);
    }

    public final int hashCode() {
        return this.zzpbn.hashCode();
    }

    public final Iterator<Entry<FieldDescriptorType, Object>> iterator() {
        return this.zzpbo ? new zzffb(this.zzpbn.entrySet().iterator()) : this.zzpbn.entrySet().iterator();
    }
}
