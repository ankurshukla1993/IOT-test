package com.google.common.collect;

import com.google.common.annotations.VisibleForTesting;

@VisibleForTesting
class Ordering$IncomparableValueException extends ClassCastException {
    private static final long serialVersionUID = 0;
    final Object value;

    Ordering$IncomparableValueException(Object value) {
        String valueOf = String.valueOf(String.valueOf(value));
        super(new StringBuilder(valueOf.length() + 22).append("Cannot compare value: ").append(valueOf).toString());
        this.value = value;
    }
}
