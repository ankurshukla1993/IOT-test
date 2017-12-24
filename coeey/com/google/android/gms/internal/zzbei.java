package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;

public final class zzbei extends LruCache<Object, Drawable> {
    public zzbei() {
        super(10);
    }
}
