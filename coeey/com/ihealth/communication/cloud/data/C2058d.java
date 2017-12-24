package com.ihealth.communication.cloud.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class C2058d implements Creator {
    C2058d() {
    }

    public Data_TB_Swim m455a(Parcel parcel) {
        return new Data_TB_Swim(parcel);
    }

    public Data_TB_Swim[] m456a(int i) {
        return new Data_TB_Swim[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m455a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m456a(i);
    }
}
