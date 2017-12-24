package org.parceler.converter;

import android.os.Parcel;
import org.parceler.ParcelConverter;

public abstract class NullableParcelConverter<T> implements ParcelConverter<T> {
    private static final int NOT_NULL = 1;
    private static final int NULL = -1;

    public abstract T nullSafeFromParcel(Parcel parcel);

    public abstract void nullSafeToParcel(T t, Parcel parcel);

    public void toParcel(T input, Parcel parcel) {
        if (input == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(1);
        nullSafeToParcel(input, parcel);
    }

    public T fromParcel(Parcel parcel) {
        if (parcel.readInt() == -1) {
            return null;
        }
        return nullSafeFromParcel(parcel);
    }
}
