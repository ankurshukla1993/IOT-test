package com.google.common.cache;

final class LocalCache$WeightedStrongValueReference<K, V> extends LocalCache$StrongValueReference<K, V> {
    final int weight;

    LocalCache$WeightedStrongValueReference(V referent, int weight) {
        super(referent);
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }
}
