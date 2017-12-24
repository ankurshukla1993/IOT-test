package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

final class MapMaker$NullComputingConcurrentMap<K, V> extends MapMaker$NullConcurrentMap<K, V> {
    private static final long serialVersionUID = 0;
    final Function<? super K, ? extends V> computingFunction;

    MapMaker$NullComputingConcurrentMap(MapMaker mapMaker, Function<? super K, ? extends V> computingFunction) {
        super(mapMaker);
        this.computingFunction = (Function) Preconditions.checkNotNull(computingFunction);
    }

    public V get(Object k) {
        K key = k;
        V value = compute(key);
        Preconditions.checkNotNull(value, "%s returned null for key %s.", this.computingFunction, key);
        notifyRemoval(key, value);
        return value;
    }

    private V compute(K key) {
        Preconditions.checkNotNull(key);
        try {
            return this.computingFunction.apply(key);
        } catch (ComputationException e) {
            throw e;
        } catch (Throwable t) {
            ComputationException computationException = new ComputationException(t);
        }
    }
}
