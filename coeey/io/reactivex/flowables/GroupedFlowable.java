package io.reactivex.flowables;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;

public abstract class GroupedFlowable<K, T> extends Flowable<T> {
    final K key;

    protected GroupedFlowable(@Nullable K key) {
        this.key = key;
    }

    @Nullable
    public K getKey() {
        return this.key;
    }
}
