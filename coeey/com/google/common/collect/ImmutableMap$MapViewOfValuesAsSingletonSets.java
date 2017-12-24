package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

final class ImmutableMap$MapViewOfValuesAsSingletonSets<K, V> extends ImmutableMap<K, ImmutableSet<V>> {
    private final ImmutableMap<K, V> delegate;

    class C16211 extends ImmutableMapEntrySet<K, ImmutableSet<V>> {
        C16211() {
        }

        ImmutableMap<K, ImmutableSet<V>> map() {
            return ImmutableMap$MapViewOfValuesAsSingletonSets.this;
        }

        public UnmodifiableIterator<Entry<K, ImmutableSet<V>>> iterator() {
            final Iterator<Entry<K, V>> backingIterator = ImmutableMap$MapViewOfValuesAsSingletonSets.this.delegate.entrySet().iterator();
            return new UnmodifiableIterator<Entry<K, ImmutableSet<V>>>() {
                public boolean hasNext() {
                    return backingIterator.hasNext();
                }

                public Entry<K, ImmutableSet<V>> next() {
                    final Entry<K, V> backingEntry = (Entry) backingIterator.next();
                    return new AbstractMapEntry<K, ImmutableSet<V>>() {
                        public K getKey() {
                            return backingEntry.getKey();
                        }

                        public ImmutableSet<V> getValue() {
                            return ImmutableSet.of(backingEntry.getValue());
                        }
                    };
                }
            };
        }
    }

    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    public /* bridge */ /* synthetic */ Set keySet() {
        return super.keySet();
    }

    public /* bridge */ /* synthetic */ Collection values() {
        return super.values();
    }

    ImmutableMap$MapViewOfValuesAsSingletonSets(ImmutableMap<K, V> delegate) {
        this.delegate = (ImmutableMap) Preconditions.checkNotNull(delegate);
    }

    public int size() {
        return this.delegate.size();
    }

    public boolean containsKey(@Nullable Object key) {
        return this.delegate.containsKey(key);
    }

    public ImmutableSet<V> get(@Nullable Object key) {
        V outerValue = this.delegate.get(key);
        return outerValue == null ? null : ImmutableSet.of(outerValue);
    }

    boolean isPartialView() {
        return false;
    }

    ImmutableSet<Entry<K, ImmutableSet<V>>> createEntrySet() {
        return new C16211();
    }
}
