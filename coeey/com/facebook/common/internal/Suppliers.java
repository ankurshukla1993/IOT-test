package com.facebook.common.internal;

public class Suppliers {
    public static <T> Supplier<T> of(final T instance) {
        return new Supplier<T>() {
            public T get() {
                return instance;
            }
        };
    }
}
