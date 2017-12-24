package com.facebook.react.bridge;

import javax.annotation.Nullable;

public class SoftAssertions {
    public static void assertUnreachable(String message) {
        throw new AssertionException(message);
    }

    public static void assertCondition(boolean condition, String message) {
        if (!condition) {
            throw new AssertionException(message);
        }
    }

    public static <T> T assertNotNull(@Nullable T instance) {
        if (instance != null) {
            return instance;
        }
        throw new AssertionException("Expected object to not be null!");
    }
}
