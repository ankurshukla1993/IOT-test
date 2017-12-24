package com.facebook.stetho.inspector;

public class MessageHandlingException extends Exception {
    public MessageHandlingException(Throwable cause) {
        super(cause);
    }

    public MessageHandlingException(String message) {
        super(message);
    }
}
