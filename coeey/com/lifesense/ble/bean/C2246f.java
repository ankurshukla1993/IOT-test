package com.lifesense.ble.bean;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class C2246f implements Creator {
    C2246f() {
    }

    public ProductUserInfo m1830a(Parcel parcel) {
        return new ProductUserInfo(parcel);
    }

    public ProductUserInfo[] m1831a(int i) {
        return new ProductUserInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m1830a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m1831a(i);
    }
}
