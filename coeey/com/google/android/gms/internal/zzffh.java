package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class zzffh<K, V> extends LinkedHashMap<K, V> {
    private static final zzffh zzpdd;
    private boolean zzpah = true;

    static {
        zzffh com_google_android_gms_internal_zzffh = new zzffh();
        zzpdd = com_google_android_gms_internal_zzffh;
        com_google_android_gms_internal_zzffh.zzpah = false;
    }

    private zzffh() {
    }

    private zzffh(Map<K, V> map) {
        super(map);
    }

    private static int zzcm(Object obj) {
        if (obj instanceof byte[]) {
            return zzfer.hashCode((byte[]) obj);
        }
        if (!(obj instanceof zzfes)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }

    public static <K, V> zzffh<K, V> zzcwc() {
        return zzpdd;
    }

    private final void zzcwe() {
        if (!this.zzpah) {
            throw new UnsupportedOperationException();
        }
    }

    public final void clear() {
        zzcwe();
        super.clear();
    }

    public final Set<Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Map) {
            Object obj2;
            obj = (Map) obj;
            if (this != obj) {
                if (size() == obj.size()) {
                    for (Entry entry : entrySet()) {
                        if (!obj.containsKey(entry.getKey())) {
                            obj2 = null;
                            break;
                        }
                        boolean equals;
                        Object value = entry.getValue();
                        Object obj3 = obj.get(entry.getKey());
                        if ((value instanceof byte[]) && (obj3 instanceof byte[])) {
                            equals = Arrays.equals((byte[]) value, (byte[]) obj3);
                            continue;
                        } else {
                            equals = value.equals(obj3);
                            continue;
                        }
                        if (!equals) {
                            obj2 = null;
                            break;
                        }
                    }
                }
                obj2 = null;
                if (obj2 != null) {
                    return true;
                }
            }
            int i = 1;
            if (obj2 != null) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        for (Entry entry : entrySet()) {
            i = (zzcm(entry.getValue()) ^ zzcm(entry.getKey())) + i;
        }
        return i;
    }

    public final boolean isMutable() {
        return this.zzpah;
    }

    public final V put(K k, V v) {
        zzcwe();
        zzfer.checkNotNull(k);
        zzfer.checkNotNull(v);
        return super.put(k, v);
    }

    public final void putAll(Map<? extends K, ? extends V> map) {
        zzcwe();
        for (Object next : map.keySet()) {
            zzfer.checkNotNull(next);
            zzfer.checkNotNull(map.get(next));
        }
        super.putAll(map);
    }

    public final V remove(Object obj) {
        zzcwe();
        return super.remove(obj);
    }

    public final void zza(zzffh<K, V> com_google_android_gms_internal_zzffh_K__V) {
        zzcwe();
        if (!com_google_android_gms_internal_zzffh_K__V.isEmpty()) {
            putAll(com_google_android_gms_internal_zzffh_K__V);
        }
    }

    public final void zzbim() {
        this.zzpah = false;
    }

    public final zzffh<K, V> zzcwd() {
        return isEmpty() ? new zzffh() : new zzffh(this);
    }
}
