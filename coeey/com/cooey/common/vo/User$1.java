package com.cooey.common.vo;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class User$1 implements Creator {
    User$1() {
    }

    public User createFromParcel(Parcel in) {
        return new User(in);
    }

    public User[] newArray(int size) {
        return new User[size];
    }
}
