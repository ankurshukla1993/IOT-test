package org.parceler.converter;

import android.os.Parcel;
import org.parceler.ParcelConverter;

public class BooleanArrayParcelConverter implements ParcelConverter<boolean[]> {
    private static final int NULL = -1;

    public void toParcel(boolean[] array, Parcel parcel) {
        if (array == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(array.length);
        parcel.writeBooleanArray(array);
    }

    public boolean[] fromParcel(Parcel parcel) {
        int size = parcel.readInt();
        if (size == -1) {
            return null;
        }
        boolean[] array = new boolean[size];
        parcel.readBooleanArray(array);
        return array;
    }
}
