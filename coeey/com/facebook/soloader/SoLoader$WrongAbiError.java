package com.facebook.soloader;

public final class SoLoader$WrongAbiError extends UnsatisfiedLinkError {
    SoLoader$WrongAbiError(Throwable cause) {
        super("APK was built for a different platform");
        initCause(cause);
    }
}
