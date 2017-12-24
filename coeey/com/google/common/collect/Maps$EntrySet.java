package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

abstract class Maps$EntrySet<K, V> extends ImprovedAbstractSet<Entry<K, V>> {
    abstract Map<K, V> map();

    Maps$EntrySet() {
    }

    public int size() {
        return map().size();
    }

    public void clear() {
        map().clear();
    }

    public boolean contains(Object o) {
        if (!(o instanceof Entry)) {
            return false;
        }
        Entry<?, ?> entry = (Entry) o;
        Object key = entry.getKey();
        V value = Maps.safeGet(map(), key);
        if (!Objects.equal(value, entry.getValue())) {
            return false;
        }
        if (value != null || map().containsKey(key)) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return map().isEmpty();
    }

    public boolean remove(Object o) {
        if (!contains(o)) {
            return false;
        }
        return map().keySet().remove(((Entry) o).getKey());
    }

    public boolean removeAll(Collection<?> c) {
        try {
            return super.removeAll((Collection) Preconditions.checkNotNull(c));
        } catch (UnsupportedOperationException e) {
            return Sets.removeAllImpl((Set) this, c.iterator());
        }
    }

    public boolean retainAll(Collection<?> c) {
        try {
            return super.retainAll((Collection) Preconditions.checkNotNull(c));
        } catch (UnsupportedOperationException e) {
            Set<Object> keys = Sets.newHashSetWithExpectedSize(c.size());
            for (Entry<?, ?> o : c) {
                if (contains(o)) {
                    keys.add(o.getKey());
                }
            }
            return map().keySet().retainAll(keys);
        }
    }
}
