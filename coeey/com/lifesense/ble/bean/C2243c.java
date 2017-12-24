package com.lifesense.ble.bean;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class C2243c implements Creator {
    C2243c() {
    }

    public HeightData m1824a(Parcel parcel) {
        return new HeightData(parcel);
    }

    public HeightData[] m1825a(int i) {
        return new HeightData[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m1824a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m1825a(i);
    }
}
