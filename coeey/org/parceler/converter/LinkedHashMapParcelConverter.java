package org.parceler.converter;

import java.util.LinkedHashMap;

public abstract class LinkedHashMapParcelConverter<K, V> extends MapParcelConverter<K, V, LinkedHashMap<K, V>> {
    public LinkedHashMap<K, V> createMap() {
        return new LinkedHashMap();
    }
}
