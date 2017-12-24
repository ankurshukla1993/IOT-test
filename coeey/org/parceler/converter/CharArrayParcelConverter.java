package org.parceler.converter;

import android.os.Parcel;
import org.parceler.ParcelConverter;

public class CharArrayParcelConverter implements ParcelConverter<char[]> {
    private static final int NULL = -1;

    public void toParcel(char[] array, Parcel parcel) {
        if (array == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(array.length);
        parcel.writeCharArray(array);
    }

    public char[] fromParcel(Parcel parcel) {
        int size = parcel.readInt();
        if (size == -1) {
            return null;
        }
        char[] array = new char[size];
        parcel.readCharArray(array);
        return array;
    }
}
