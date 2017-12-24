package com.lifesense.ble.bean;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class C2251k implements Creator {
    C2251k() {
    }

    public WeightData_A2 m1853a(Parcel parcel) {
        return new WeightData_A2(parcel);
    }

    public WeightData_A2[] m1854a(int i) {
        return new WeightData_A2[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m1853a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m1854a(i);
    }
}
