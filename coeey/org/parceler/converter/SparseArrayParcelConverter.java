package org.parceler.converter;

import android.os.Parcel;
import android.util.SparseArray;
import org.parceler.ParcelConverter;

public abstract class SparseArrayParcelConverter<T> implements ParcelConverter<SparseArray<T>> {
    public abstract T itemFromParcel(Parcel parcel);

    public abstract void itemToParcel(T t, Parcel parcel);

    public void toParcel(SparseArray<T> input, Parcel parcel) {
        if (input == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(input.size());
        for (int i = 0; i < input.size(); i++) {
            parcel.writeInt(input.keyAt(i));
            itemToParcel(input.valueAt(i), parcel);
        }
    }

    public SparseArray<T> fromParcel(Parcel parcel) {
        int size = parcel.readInt();
        if (size < 0) {
            return null;
        }
        SparseArray<T> array = new SparseArray(size);
        for (int i = 0; i < size; i++) {
            array.append(parcel.readInt(), itemFromParcel(parcel));
        }
        return array;
    }
}
