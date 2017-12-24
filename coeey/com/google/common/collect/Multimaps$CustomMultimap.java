package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map;

class Multimaps$CustomMultimap<K, V> extends AbstractMapBasedMultimap<K, V> {
    @GwtIncompatible("java serialization not supported")
    private static final long serialVersionUID = 0;
    transient Supplier<? extends Collection<V>> factory;

    Multimaps$CustomMultimap(Map<K, Collection<V>> map, Supplier<? extends Collection<V>> factory) {
        super(map);
        this.factory = (Supplier) Preconditions.checkNotNull(factory);
    }

    protected Collection<V> createCollection() {
        return (Collection) this.factory.get();
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
