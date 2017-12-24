package com.lifesense.ble.bean;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class C2252l implements Creator {
    C2252l() {
    }

    public WeightData_A3 m1855a(Parcel parcel) {
        return new WeightData_A3(parcel);
    }

    public WeightData_A3[] m1856a(int i) {
        return new WeightData_A3[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m1855a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m1856a(i);
    }
}
