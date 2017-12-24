package com.google.common.cache;

import java.util.Map.Entry;
import javax.annotation.Nullable;

final class LocalCache$WriteThroughEntry implements Entry<K, V> {
    final K key;
    final /* synthetic */ LocalCache this$0;
    V value;

    LocalCache$WriteThroughEntry(LocalCache localCache, K key, V value) {
        this.this$0 = localCache;
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public boolean equals(@Nullable Object object) {
        if (!(object instanceof Entry)) {
            return false;
        }
        Entry<?, ?> that = (Entry) object;
        if (this.key.equals(that.getKey()) && this.value.equals(that.getValue())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.key.hashCode() ^ this.value.hashCode();
    }

    public V setValue(V v) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(getKey()));
        String valueOf2 = String.valueOf(String.valueOf(getValue()));
        return new StringBuilder((valueOf.length() + 1) + valueOf2.length()).append(valueOf).append("=").append(valueOf2).toString();
    }
}
