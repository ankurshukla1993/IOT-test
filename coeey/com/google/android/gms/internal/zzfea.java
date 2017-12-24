package com.google.android.gms.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzfea {
    private static volatile boolean zzpbj = false;
    private static final Class<?> zzpbk = zzcuy();
    static final zzfea zzpbl = new zzfea(true);
    private final Map<Object, Object> zzpbm;

    zzfea() {
        this.zzpbm = new HashMap();
    }

    private zzfea(boolean z) {
        this.zzpbm = Collections.emptyMap();
    }

    private static Class<?> zzcuy() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static zzfea zzcuz() {
        return zzfdz.zzcux();
    }
}
