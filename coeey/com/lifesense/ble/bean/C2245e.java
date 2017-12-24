package com.lifesense.ble.bean;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class C2245e implements Creator {
    C2245e() {
    }

    public PedometerData m1828a(Parcel parcel) {
        return new PedometerData(parcel);
    }

    public PedometerData[] m1829a(int i) {
        return new PedometerData[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m1828a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m1829a(i);
    }
}
