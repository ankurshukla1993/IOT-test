package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class zzfhh implements Cloneable {
    private Object value;
    private zzfhf<?, ?> zzphe;
    private List<zzfhm> zzphf = new ArrayList();

    zzfhh() {
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzo()];
        zza(zzfhc.zzbe(bArr));
        return bArr;
    }

    private zzfhh zzcxg() {
        zzfhh com_google_android_gms_internal_zzfhh = new zzfhh();
        try {
            com_google_android_gms_internal_zzfhh.zzphe = this.zzphe;
            if (this.zzphf == null) {
                com_google_android_gms_internal_zzfhh.zzphf = null;
            } else {
                com_google_android_gms_internal_zzfhh.zzphf.addAll(this.zzphf);
            }
            if (this.value != null) {
                if (this.value instanceof zzfhk) {
                    com_google_android_gms_internal_zzfhh.value = (zzfhk) ((zzfhk) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    com_google_android_gms_internal_zzfhh.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    r4 = new byte[bArr.length][];
                    com_google_android_gms_internal_zzfhh.value = r4;
                    for (r2 = 0; r2 < bArr.length; r2++) {
                        r4[r2] = (byte[]) bArr[r2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    com_google_android_gms_internal_zzfhh.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    com_google_android_gms_internal_zzfhh.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    com_google_android_gms_internal_zzfhh.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    com_google_android_gms_internal_zzfhh.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    com_google_android_gms_internal_zzfhh.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzfhk[]) {
                    zzfhk[] com_google_android_gms_internal_zzfhkArr = (zzfhk[]) this.value;
                    r4 = new zzfhk[com_google_android_gms_internal_zzfhkArr.length];
                    com_google_android_gms_internal_zzfhh.value = r4;
                    for (r2 = 0; r2 < com_google_android_gms_internal_zzfhkArr.length; r2++) {
                        r4[r2] = (zzfhk) com_google_android_gms_internal_zzfhkArr[r2].clone();
                    }
                }
            }
            return com_google_android_gms_internal_zzfhh;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzcxg();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfhh)) {
            return false;
        }
        zzfhh com_google_android_gms_internal_zzfhh = (zzfhh) obj;
        if (this.value != null && com_google_android_gms_internal_zzfhh.value != null) {
            return this.zzphe == com_google_android_gms_internal_zzfhh.zzphe ? !this.zzphe.zznan.isArray() ? this.value.equals(com_google_android_gms_internal_zzfhh.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) com_google_android_gms_internal_zzfhh.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) com_google_android_gms_internal_zzfhh.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) com_google_android_gms_internal_zzfhh.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) com_google_android_gms_internal_zzfhh.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) com_google_android_gms_internal_zzfhh.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) com_google_android_gms_internal_zzfhh.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) com_google_android_gms_internal_zzfhh.value) : false;
        } else {
            if (this.zzphf != null && com_google_android_gms_internal_zzfhh.zzphf != null) {
                return this.zzphf.equals(com_google_android_gms_internal_zzfhh.zzphf);
            }
            try {
                return Arrays.equals(toByteArray(), com_google_android_gms_internal_zzfhh.toByteArray());
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    final void zza(zzfhc com_google_android_gms_internal_zzfhc) throws IOException {
        if (this.value != null) {
            zzfhf com_google_android_gms_internal_zzfhf = this.zzphe;
            Object obj = this.value;
            if (com_google_android_gms_internal_zzfhf.zzpgz) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    Object obj2 = Array.get(obj, i);
                    if (obj2 != null) {
                        com_google_android_gms_internal_zzfhf.zza(obj2, com_google_android_gms_internal_zzfhc);
                    }
                }
                return;
            }
            com_google_android_gms_internal_zzfhf.zza(obj, com_google_android_gms_internal_zzfhc);
            return;
        }
        for (zzfhm com_google_android_gms_internal_zzfhm : this.zzphf) {
            com_google_android_gms_internal_zzfhc.zzlx(com_google_android_gms_internal_zzfhm.tag);
            com_google_android_gms_internal_zzfhc.zzbg(com_google_android_gms_internal_zzfhm.zzjkl);
        }
    }

    final void zza(zzfhm com_google_android_gms_internal_zzfhm) {
        this.zzphf.add(com_google_android_gms_internal_zzfhm);
    }

    final <T> T zzb(zzfhf<?, T> com_google_android_gms_internal_zzfhf___T) {
        if (this.value == null) {
            this.zzphe = com_google_android_gms_internal_zzfhf___T;
            this.value = com_google_android_gms_internal_zzfhf___T.zzbp(this.zzphf);
            this.zzphf = null;
        } else if (!this.zzphe.equals(com_google_android_gms_internal_zzfhf___T)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return this.value;
    }

    final int zzo() {
        int i = 0;
        int i2;
        if (this.value != null) {
            zzfhf com_google_android_gms_internal_zzfhf = this.zzphe;
            Object obj = this.value;
            if (!com_google_android_gms_internal_zzfhf.zzpgz) {
                return com_google_android_gms_internal_zzfhf.zzcn(obj);
            }
            int length = Array.getLength(obj);
            for (i2 = 0; i2 < length; i2++) {
                if (Array.get(obj, i2) != null) {
                    i += com_google_android_gms_internal_zzfhf.zzcn(Array.get(obj, i2));
                }
            }
            return i;
        }
        i2 = 0;
        for (zzfhm com_google_android_gms_internal_zzfhm : this.zzphf) {
            i2 = (com_google_android_gms_internal_zzfhm.zzjkl.length + (zzfhc.zzly(com_google_android_gms_internal_zzfhm.tag) + 0)) + i2;
        }
        return i2;
    }
}
