package com.google.common.reflect;

import com.google.common.reflect.TypeToken.TypeCollector;

class TypeToken$TypeCollector$ForwardingTypeCollector<K> extends TypeCollector<K> {
    private final TypeCollector<K> delegate;

    TypeToken$TypeCollector$ForwardingTypeCollector(TypeCollector<K> delegate) {
        super(null);
        this.delegate = delegate;
    }

    Class<?> getRawType(K type) {
        return this.delegate.getRawType(type);
    }

    Iterable<? extends K> getInterfaces(K type) {
        return this.delegate.getInterfaces(type);
    }

    K getSuperclass(K type) {
        return this.delegate.getSuperclass(type);
    }
}
