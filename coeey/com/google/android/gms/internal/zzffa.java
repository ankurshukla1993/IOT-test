package com.google.android.gms.internal;

import java.util.Map.Entry;

final class zzffa<K> implements Entry<K, Object> {
    private Entry<K, zzfey> zzpcv;

    private zzffa(Entry<K, zzfey> entry) {
        this.zzpcv = entry;
    }

    public final K getKey() {
        return this.zzpcv.getKey();
    }

    public final Object getValue() {
        return ((zzfey) this.zzpcv.getValue()) == null ? null : zzfey.zzcwa();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzffi) {
            return ((zzfey) this.zzpcv.getValue()).zzj((zzffi) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
