package com.facebook.common.internal;

import java.util.Arrays;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

public final class Objects {
    private Objects() {
    }

    @CheckReturnValue
    public static boolean equal(@Nullable Object a, @Nullable Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static int hashCode(@Nullable Object... objects) {
        return Arrays.hashCode(objects);
    }

    public static ToStringHelper toStringHelper(Object self) {
        return new ToStringHelper(simpleName(self.getClass()), null);
    }

    public static ToStringHelper toStringHelper(Class<?> clazz) {
        return new ToStringHelper(simpleName(clazz), null);
    }

    public static ToStringHelper toStringHelper(String className) {
        return new ToStringHelper(className, null);
    }

    private static String simpleName(Class<?> clazz) {
        String name = clazz.getName().replaceAll("\\$[0-9]+", "\\$");
        int start = name.lastIndexOf(36);
        if (start == -1) {
            start = name.lastIndexOf(46);
        }
        return name.substring(start + 1);
    }

    public static <T> T firstNonNull(@Nullable T first, @Nullable T second) {
        return first != null ? first : Preconditions.checkNotNull(second);
    }
}
