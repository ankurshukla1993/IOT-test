package com.cooey.common.vo;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import org.parceler.IdentityCollection;

/* compiled from: Reminder$$Parcelable */
public final class Reminder$$Parcelable$Creator$$2 implements Creator<Reminder$$Parcelable> {
    public Reminder$$Parcelable createFromParcel(Parcel parcel$$2) {
        return new Reminder$$Parcelable(Reminder$$Parcelable.read(parcel$$2, new IdentityCollection()));
    }

    public Reminder$$Parcelable[] newArray(int size) {
        return new Reminder$$Parcelable[size];
    }
}
