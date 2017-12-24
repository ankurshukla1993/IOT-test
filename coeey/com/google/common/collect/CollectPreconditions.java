package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
final class CollectPreconditions {
    CollectPreconditions() {
    }

    static void checkEntryNotNull(Object key, Object value) {
        String valueOf;
        if (key == null) {
            valueOf = String.valueOf(String.valueOf(value));
            throw new NullPointerException(new StringBuilder(valueOf.length() + 24).append("null key in entry: null=").append(valueOf).toString());
        } else if (value == null) {
            valueOf = String.valueOf(String.valueOf(key));
            throw new NullPointerException(new StringBuilder(valueOf.length() + 26).append("null value in entry: ").append(valueOf).append("=null").toString());
        }
    }

    static int checkNonnegative(int value, String name) {
        if (value >= 0) {
            return value;
        }
        String valueOf = String.valueOf(String.valueOf(name));
        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 40).append(valueOf).append(" cannot be negative but was: ").append(value).toString());
    }

    static void checkRemove(boolean canRemove) {
        Preconditions.checkState(canRemove, "no calls to next() since the last call to remove()");
    }
}
