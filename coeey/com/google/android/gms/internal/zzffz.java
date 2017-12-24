package com.google.android.gms.internal;

import java.util.Map.Entry;

final class zzffz implements Comparable<zzffz>, Entry<K, V> {
    private V value;
    private final K zzpec;
    private /* synthetic */ zzffu zzped;

    zzffz(zzffu com_google_android_gms_internal_zzffu, K k, V v) {
        this.zzped = com_google_android_gms_internal_zzffu;
        this.zzpec = k;
        this.value = v;
    }

    zzffz(zzffu com_google_android_gms_internal_zzffu, Entry<K, V> entry) {
        this(com_google_android_gms_internal_zzffu, (Comparable) entry.getKey(), entry.getValue());
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return ((Comparable) getKey()).compareTo((Comparable) ((zzffz) obj).getKey());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        return equals(this.zzpec, entry.getKey()) && equals(this.value, entry.getValue());
    }

    public final /* synthetic */ Object getKey() {
        return this.zzpec;
    }

    public final V getValue() {
        return this.value;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = this.zzpec == null ? 0 : this.zzpec.hashCode();
        if (this.value != null) {
            i = this.value.hashCode();
        }
        return hashCode ^ i;
    }

    public final V setValue(V v) {
        this.zzped.zzcwl();
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzpec);
        String valueOf2 = String.valueOf(this.value);
        return new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(valueOf2).length()).append(valueOf).append("=").append(valueOf2).toString();
    }
}
