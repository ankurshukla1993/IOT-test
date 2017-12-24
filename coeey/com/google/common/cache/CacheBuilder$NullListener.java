package com.google.common.cache;

enum CacheBuilder$NullListener implements RemovalListener<Object, Object> {
    INSTANCE;

    public void onRemoval(RemovalNotification<Object, Object> removalNotification) {
    }
}
