package com.google.common.collect;

import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

class Maps$UnmodifiableEntrySet<K, V> extends Maps$UnmodifiableEntries<K, V> implements Set<Entry<K, V>> {
    Maps$UnmodifiableEntrySet(Set<Entry<K, V>> entries) {
        super(entries);
    }

    public boolean equals(@Nullable Object object) {
        return Sets.equalsImpl(this, object);
    }

    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }
}
