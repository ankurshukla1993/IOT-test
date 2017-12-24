package org.parceler;

import android.os.Parcel;

public interface TypeRangeParcelConverter<L, U extends L> {
    U fromParcel(Parcel parcel);

    void toParcel(L l, Parcel parcel);
}
