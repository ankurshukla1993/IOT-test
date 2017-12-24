package org.parceler.converter;

import android.os.Parcel;
import java.util.Collection;
import org.parceler.TypeRangeParcelConverter;

public abstract class CollectionParcelConverter<T, C extends Collection<T>> implements TypeRangeParcelConverter<Collection<T>, C> {
    private static final int NULL = -1;

    public abstract C createCollection();

    public abstract T itemFromParcel(Parcel parcel);

    public abstract void itemToParcel(T t, Parcel parcel);

    public void toParcel(Collection<T> input, Parcel parcel) {
        if (input == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(input.size());
        for (T item : input) {
            itemToParcel(item, parcel);
        }
    }

    public C fromParcel(Parcel parcel) {
        int size = parcel.readInt();
        if (size == -1) {
            return null;
        }
        C list = createCollection();
        for (int i = 0; i < size; i++) {
            list.add(itemFromParcel(parcel));
        }
        return list;
    }
}
