package org.parceler.converter;

import android.os.Parcel;
import java.util.Map;
import java.util.Map.Entry;
import org.parceler.TypeRangeParcelConverter;

public abstract class MapParcelConverter<K, V, M extends Map<K, V>> implements TypeRangeParcelConverter<Map<K, V>, M> {
    private static final int NULL = -1;

    public abstract M createMap();

    public abstract K mapKeyFromParcel(Parcel parcel);

    public abstract void mapKeyToParcel(K k, Parcel parcel);

    public abstract V mapValueFromParcel(Parcel parcel);

    public abstract void mapValueToParcel(V v, Parcel parcel);

    public void toParcel(Map<K, V> input, Parcel parcel) {
        if (input == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(input.size());
        for (Entry<K, V> entry : input.entrySet()) {
            mapKeyToParcel(entry.getKey(), parcel);
            mapValueToParcel(entry.getValue(), parcel);
        }
    }

    public M fromParcel(Parcel parcel) {
        int size = parcel.readInt();
        if (size == -1) {
            return null;
        }
        M map = createMap();
        for (int i = 0; i < size; i++) {
            map.put(mapKeyFromParcel(parcel), mapValueFromParcel(parcel));
        }
        return map;
    }
}
