package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible("To be supported")
enum GenericMapMaker$NullListener implements MapMaker$RemovalListener<Object, Object> {
    INSTANCE;

    public void onRemoval(MapMaker$RemovalNotification<Object, Object> mapMaker$RemovalNotification) {
    }
}
