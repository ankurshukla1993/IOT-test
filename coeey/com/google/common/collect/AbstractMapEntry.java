package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import java.util.Map.Entry;
import javax.annotation.Nullable;

@GwtCompatible
abstract class AbstractMapEntry<K, V> implements Entry<K, V> {
    public abstract K getKey();

    public abstract V getValue();

    AbstractMapEntry() {
    }

    public V setValue(V v) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(@Nullable Object object) {
        if (!(object instanceof Entry)) {
            return false;
        }
        Entry<?, ?> that = (Entry) object;
        if (Objects.equal(getKey(), that.getKey()) && Objects.equal(getValue(), that.getValue())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        K k = getKey();
        V v = getValue();
        int hashCode = k == null ? 0 : k.hashCode();
        if (v != null) {
            i = v.hashCode();
        }
        return i ^ hashCode;
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(getKey()));
        String valueOf2 = String.valueOf(String.valueOf(getValue()));
        return new StringBuilder((valueOf.length() + 1) + valueOf2.length()).append(valueOf).append("=").append(valueOf2).toString();
    }
}
