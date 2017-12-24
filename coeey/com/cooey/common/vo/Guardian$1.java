package com.cooey.common.vo;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class Guardian$1 implements Creator {
    Guardian$1() {
    }

    public Guardian createFromParcel(Parcel in) {
        return new Guardian(in);
    }

    public Guardian[] newArray(int size) {
        return new Guardian[size];
    }
}
