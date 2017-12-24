package org.parceler.converter;

import java.util.HashMap;

public abstract class HashMapParcelConverter<K, V> extends MapParcelConverter<K, V, HashMap<K, V>> {
    public HashMap<K, V> createMap() {
        return new HashMap();
    }
}
