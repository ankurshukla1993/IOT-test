package com.facebook.react.bridge;

import javax.annotation.Nullable;

public class JSApplicationCausedNativeException extends RuntimeException {
    public JSApplicationCausedNativeException(String detailMessage) {
        super(detailMessage);
    }

    public JSApplicationCausedNativeException(@Nullable String detailMessage, @Nullable Throwable throwable) {
        super(detailMessage, throwable);
    }
}
