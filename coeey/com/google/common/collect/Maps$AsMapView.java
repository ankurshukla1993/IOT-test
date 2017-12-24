package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

class Maps$AsMapView<K, V> extends Maps$ImprovedAbstractMap<K, V> {
    final Function<? super K, V> function;
    private final Set<K> set;

    class C16731 extends Maps$EntrySet<K, V> {
        C16731() {
        }

        Map<K, V> map() {
            return Maps$AsMapView.this;
        }

        public Iterator<Entry<K, V>> iterator() {
            return Maps.asMapEntryIterator(Maps$AsMapView.this.backingSet(), Maps$AsMapView.this.function);
        }
    }

    Set<K> backingSet() {
        return this.set;
    }

    Maps$AsMapView(Set<K> set, Function<? super K, V> function) {
        this.set = (Set) Preconditions.checkNotNull(set);
        this.function = (Function) Preconditions.checkNotNull(function);
    }

    public Set<K> createKeySet() {
        return Maps.access$200(backingSet());
    }

    Collection<V> createValues() {
        return Collections2.transform(this.set, this.function);
    }

    public int size() {
        return backingSet().size();
    }

    public boolean containsKey(@Nullable Object key) {
        return backingSet().contains(key);
    }

    public V get(@Nullable Object key) {
        if (!Collections2.safeContains(backingSet(), key)) {
            return null;
        }
        return this.function.apply(key);
    }

    public V remove(@Nullable Object key) {
        if (!backingSet().remove(key)) {
            return null;
        }
        return this.function.apply(key);
    }

    public void clear() {
        backingSet().clear();
    }

    protected Set<Entry<K, V>> createEntrySet() {
        return new C16731();
    }
}
