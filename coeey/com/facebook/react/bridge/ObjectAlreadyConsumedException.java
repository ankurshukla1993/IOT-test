package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class ObjectAlreadyConsumedException extends RuntimeException {
    @DoNotStrip
    public ObjectAlreadyConsumedException(String detailMessage) {
        super(detailMessage);
    }
}
