package com.google.common.base;

import java.io.Serializable;

final class Converter$IdentityConverter<T> extends Converter<T, T> implements Serializable {
    static final Converter$IdentityConverter INSTANCE = new Converter$IdentityConverter();
    private static final long serialVersionUID = 0;

    private Converter$IdentityConverter() {
    }

    protected T doForward(T t) {
        return t;
    }

    protected T doBackward(T t) {
        return t;
    }

    public Converter$IdentityConverter<T> reverse() {
        return this;
    }

    <S> Converter<T, S> doAndThen(Converter<T, S> otherConverter) {
        return (Converter) Preconditions.checkNotNull(otherConverter, "otherConverter");
    }

    public String toString() {
        return "Converter.identity()";
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
