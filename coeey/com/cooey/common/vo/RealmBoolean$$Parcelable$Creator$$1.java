package com.cooey.common.vo;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import org.parceler.IdentityCollection;

/* compiled from: RealmBoolean$$Parcelable */
public final class RealmBoolean$$Parcelable$Creator$$1 implements Creator<RealmBoolean$$Parcelable> {
    public RealmBoolean$$Parcelable createFromParcel(Parcel parcel$$2) {
        return new RealmBoolean$$Parcelable(RealmBoolean$$Parcelable.read(parcel$$2, new IdentityCollection()));
    }

    public RealmBoolean$$Parcelable[] newArray(int size) {
        return new RealmBoolean$$Parcelable[size];
    }
}
