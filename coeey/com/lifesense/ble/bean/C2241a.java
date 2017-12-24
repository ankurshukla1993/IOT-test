package com.lifesense.ble.bean;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class C2241a implements Creator {
    C2241a() {
    }

    public BloodPressureData m1822a(Parcel parcel) {
        return new BloodPressureData(parcel);
    }

    public BloodPressureData[] m1823a(int i) {
        return new BloodPressureData[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m1822a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m1823a(i);
    }
}
