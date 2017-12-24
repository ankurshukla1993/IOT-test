package com.cooey.common.vo;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import org.parceler.IdentityCollection;

/* compiled from: Medicine$$Parcelable */
public final class Medicine$$Parcelable$Creator$$0 implements Creator<Medicine$$Parcelable> {
    public Medicine$$Parcelable createFromParcel(Parcel parcel$$2) {
        return new Medicine$$Parcelable(Medicine$$Parcelable.read(parcel$$2, new IdentityCollection()));
    }

    public Medicine$$Parcelable[] newArray(int size) {
        return new Medicine$$Parcelable[size];
    }
}
