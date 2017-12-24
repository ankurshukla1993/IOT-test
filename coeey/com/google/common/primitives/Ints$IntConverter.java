package com.google.common.primitives;

import com.google.common.base.Converter;
import java.io.Serializable;

final class Ints$IntConverter extends Converter<String, Integer> implements Serializable {
    static final Ints$IntConverter INSTANCE = new Ints$IntConverter();
    private static final long serialVersionUID = 1;

    private Ints$IntConverter() {
    }

    protected Integer doForward(String value) {
        return Integer.decode(value);
    }

    protected String doBackward(Integer value) {
        return value.toString();
    }

    public String toString() {
        return "Ints.stringConverter()";
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
