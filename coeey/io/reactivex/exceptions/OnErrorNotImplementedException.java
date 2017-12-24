package io.reactivex.exceptions;

import io.reactivex.annotations.Beta;
import io.reactivex.annotations.NonNull;

@Beta
public final class OnErrorNotImplementedException extends RuntimeException {
    private static final long serialVersionUID = -6298857009889503852L;

    public OnErrorNotImplementedException(String message, @NonNull Throwable e) {
        if (e == null) {
            e = new NullPointerException();
        }
        super(message, e);
    }

    public OnErrorNotImplementedException(@NonNull Throwable e) {
        String message = e != null ? e.getMessage() : null;
        if (e == null) {
            e = new NullPointerException();
        }
        super(message, e);
    }
}
