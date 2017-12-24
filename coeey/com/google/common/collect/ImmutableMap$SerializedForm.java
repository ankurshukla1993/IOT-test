package com.google.common.collect;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map.Entry;

class ImmutableMap$SerializedForm implements Serializable {
    private static final long serialVersionUID = 0;
    private final Object[] keys;
    private final Object[] values;

    ImmutableMap$SerializedForm(ImmutableMap<?, ?> map) {
        this.keys = new Object[map.size()];
        this.values = new Object[map.size()];
        int i = 0;
        Iterator i$ = map.entrySet().iterator();
        while (i$.hasNext()) {
            Entry<?, ?> entry = (Entry) i$.next();
            this.keys[i] = entry.getKey();
            this.values[i] = entry.getValue();
            i++;
        }
    }

    Object readResolve() {
        return createMap(new ImmutableMap$Builder());
    }

    Object createMap(ImmutableMap$Builder<Object, Object> builder) {
        for (int i = 0; i < this.keys.length; i++) {
            builder.put(this.keys[i], this.values[i]);
        }
        return builder.build();
    }
}
