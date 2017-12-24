package com.google.android.flexbox;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.flexbox.FlexboxLayout.LayoutParams;

class FlexboxLayout$LayoutParams$1 implements Creator<LayoutParams> {
    FlexboxLayout$LayoutParams$1() {
    }

    public LayoutParams createFromParcel(Parcel source) {
        return new LayoutParams(source);
    }

    public LayoutParams[] newArray(int size) {
        return new LayoutParams[size];
    }
}
