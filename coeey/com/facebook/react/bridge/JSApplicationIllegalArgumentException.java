package com.facebook.react.bridge;

public class JSApplicationIllegalArgumentException extends JSApplicationCausedNativeException {
    public JSApplicationIllegalArgumentException(String detailMessage) {
        super(detailMessage);
    }

    public JSApplicationIllegalArgumentException(String detailMessage, Throwable t) {
        super(detailMessage, t);
    }
}
