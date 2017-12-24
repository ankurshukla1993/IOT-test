package com.facebook.react.bridge;

public class DefaultNativeModuleCallExceptionHandler implements NativeModuleCallExceptionHandler {
    public void handleException(Exception e) {
        if (e instanceof RuntimeException) {
            throw ((RuntimeException) e);
        }
        throw new RuntimeException(e);
    }
}
