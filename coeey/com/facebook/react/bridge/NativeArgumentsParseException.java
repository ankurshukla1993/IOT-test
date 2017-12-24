package com.facebook.react.bridge;

import javax.annotation.Nullable;

public class NativeArgumentsParseException extends JSApplicationCausedNativeException {
    public NativeArgumentsParseException(String detailMessage) {
        super(detailMessage);
    }

    public NativeArgumentsParseException(@Nullable String detailMessage, @Nullable Throwable t) {
        super(detailMessage, t);
    }
}
