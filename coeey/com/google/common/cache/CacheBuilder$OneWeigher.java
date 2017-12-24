package com.google.common.cache;

enum CacheBuilder$OneWeigher implements Weigher<Object, Object> {
    INSTANCE;

    public int weigh(Object key, Object value) {
        return 1;
    }
}
