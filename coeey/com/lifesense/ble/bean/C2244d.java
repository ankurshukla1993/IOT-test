package com.lifesense.ble.bean;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class C2244d implements Creator {
    C2244d() {
    }

    public LsDeviceInfo m1826a(Parcel parcel) {
        return new LsDeviceInfo(parcel);
    }

    public LsDeviceInfo[] m1827a(int i) {
        return new LsDeviceInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m1826a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m1827a(i);
    }
}
