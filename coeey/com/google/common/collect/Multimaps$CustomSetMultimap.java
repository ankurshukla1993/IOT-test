package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

class Multimaps$CustomSetMultimap<K, V> extends AbstractSetMultimap<K, V> {
    @GwtIncompatible("not needed in emulated source")
    private static final long serialVersionUID = 0;
    transient Supplier<? extends Set<V>> factory;

    Multimaps$CustomSetMultimap(Map<K, Collection<V>> map, Supplier<? extends Set<V>> factory) {
        super(map);
        this.factory = (Supplier) Preconditions.checkNotNull(factory);
    }

    protected Set<V> createCollection() {
        return (Set) this.factory.get();
    }

    @GwtIncompatible("java.io.ObjectOutputStream")
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(this.factory);
        stream.writeObject(backingMap());
    }

    @GwtIncompatible("java.io.ObjectInputStream")
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.factory = (Supplier) stream.readObject();
        setMap((Map) stream.readObject());
    }
}
