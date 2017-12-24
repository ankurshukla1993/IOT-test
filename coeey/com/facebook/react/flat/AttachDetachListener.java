package com.facebook.react.flat;

interface AttachDetachListener {
    public static final AttachDetachListener[] EMPTY_ARRAY = new AttachDetachListener[0];

    void onAttached(FlatViewGroup$InvalidateCallback flatViewGroup$InvalidateCallback);

    void onDetached();
}
