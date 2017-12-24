package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class zzdlw {
    @NonNull
    public static <T> T checkNotNull(@Nullable T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }
}
