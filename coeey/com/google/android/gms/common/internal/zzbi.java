package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;

public final class zzbi {
    private final Object zzdbh;
    private final List<String> zzfyk;

    private zzbi(Object obj) {
        this.zzdbh = zzbq.checkNotNull(obj);
        this.zzfyk = new ArrayList();
    }

    public final String toString() {
        StringBuilder append = new StringBuilder(100).append(this.zzdbh.getClass().getSimpleName()).append('{');
        int size = this.zzfyk.size();
        for (int i = 0; i < size; i++) {
            append.append((String) this.zzfyk.get(i));
            if (i < size - 1) {
                append.append(", ");
            }
        }
        return append.append('}').toString();
    }

    public final zzbi zzg(String str, Object obj) {
        List list = this.zzfyk;
        String str2 = (String) zzbq.checkNotNull(str);
        String valueOf = String.valueOf(obj);
        list.add(new StringBuilder((String.valueOf(str2).length() + 1) + String.valueOf(valueOf).length()).append(str2).append("=").append(valueOf).toString());
        return this;
    }
}
