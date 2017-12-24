package com.google.common.collect;

import java.io.Serializable;

final class HashBiMap$InverseSerializedForm<K, V> implements Serializable {
    private final HashBiMap<K, V> bimap;

    HashBiMap$InverseSerializedForm(HashBiMap<K, V> bimap) {
        this.bimap = bimap;
    }

    Object readResolve() {
        return this.bimap.inverse();
    }
}
