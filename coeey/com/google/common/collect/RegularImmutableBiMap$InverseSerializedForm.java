package com.google.common.collect;

import java.io.Serializable;

class RegularImmutableBiMap$InverseSerializedForm<K, V> implements Serializable {
    private static final long serialVersionUID = 1;
    private final ImmutableBiMap<K, V> forward;

    RegularImmutableBiMap$InverseSerializedForm(ImmutableBiMap<K, V> forward) {
        this.forward = forward;
    }

    Object readResolve() {
        return this.forward.inverse();
    }
}
