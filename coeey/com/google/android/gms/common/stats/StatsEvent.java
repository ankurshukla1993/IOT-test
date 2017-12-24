package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbej;

public abstract class StatsEvent extends zzbej implements ReflectedParcelable {
    public abstract int getEventType();

    public abstract long getTimeMillis();

    public String toString() {
        long timeMillis = getTimeMillis();
        String str = "\t";
        int eventType = getEventType();
        String str2 = "\t";
        long zzalr = zzalr();
        String zzals = zzals();
        return new StringBuilder(((String.valueOf(str).length() + 51) + String.valueOf(str2).length()) + String.valueOf(zzals).length()).append(timeMillis).append(str).append(eventType).append(str2).append(zzalr).append(zzals).toString();
    }

    public abstract long zzalr();

    public abstract String zzals();
}
