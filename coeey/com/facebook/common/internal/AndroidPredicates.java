package com.facebook.common.internal;

import com.android.internal.util.Predicate;

public class AndroidPredicates {

    static class C11211 implements Predicate<T> {
        C11211() {
        }

        public boolean apply(T t) {
            return true;
        }
    }

    static class C11222 implements Predicate<T> {
        C11222() {
        }

        public boolean apply(T t) {
            return false;
        }
    }

    private AndroidPredicates() {
    }

    public static <T> Predicate<T> True() {
        return new C11211();
    }

    public static <T> Predicate<T> False() {
        return new C11222();
    }
}
