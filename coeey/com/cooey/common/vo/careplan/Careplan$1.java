package com.cooey.common.vo.careplan;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class Careplan$1 implements Creator<Careplan> {
    Careplan$1() {
    }

    public Careplan createFromParcel(Parcel in) {
        return new Careplan(in);
    }

    public Careplan[] newArray(int size) {
        return new Careplan[size];
    }
}
