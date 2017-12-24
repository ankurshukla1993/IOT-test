package com.facebook.imagepipeline.decoder;

public class DecodeException extends RuntimeException {
    public DecodeException(String message) {
        super(message);
    }

    public DecodeException(String message, Throwable t) {
        super(message, t);
    }
}
