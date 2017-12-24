package com.cooey.common.converter;

import android.os.Parcel;
import io.realm.RealmList;
import io.realm.RealmObject;
import java.util.Iterator;
import org.parceler.Parcels;
import org.parceler.TypeRangeParcelConverter;

public class RealmListParcelConverter implements TypeRangeParcelConverter<RealmList<? extends RealmObject>, RealmList<? extends RealmObject>> {
    private static final int NULL = -1;

    public void toParcel(RealmList<? extends RealmObject> input, Parcel parcel) {
        parcel.writeInt(input == null ? -1 : input.size());
        if (input != null) {
            Iterator it = input.iterator();
            while (it.hasNext()) {
                parcel.writeParcelable(Parcels.wrap((RealmObject) it.next()), 0);
            }
        }
    }

    public RealmList fromParcel(Parcel parcel) {
        int size = parcel.readInt();
        RealmList list = new RealmList();
        for (int i = 0; i < size; i++) {
            list.add((RealmObject) Parcels.unwrap(parcel.readParcelable(getClass().getClassLoader())));
        }
        return list;
    }
}
