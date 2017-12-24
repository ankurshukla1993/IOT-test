package com.facebook.react.uimanager;

public class ReactInvalidPropertyException extends RuntimeException {
    public ReactInvalidPropertyException(String property, String value, String expectedValues) {
        super("Invalid React property `" + property + "` with value `" + value + "`, expected " + expectedValues);
    }
}
