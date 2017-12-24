package com.google.android.gms.internal;

import android.os.Parcel;

public final class zzbel extends RuntimeException {
    public zzbel(String str, Parcel parcel) {
        int dataPosition = parcel.dataPosition();
        super(new StringBuilder(String.valueOf(str).length() + 41).append(str).append(" Parcel: pos=").append(dataPosition).append(" size=").append(parcel.dataSize()).toString());
    }
}
