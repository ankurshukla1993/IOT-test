package com.google.common.primitives;

import com.google.common.base.Converter;
import java.io.Serializable;

final class Doubles$DoubleConverter extends Converter<String, Double> implements Serializable {
    static final Doubles$DoubleConverter INSTANCE = new Doubles$DoubleConverter();
    private static final long serialVersionUID = 1;

    private Doubles$DoubleConverter() {
    }

    protected Double doForward(String value) {
        return Double.valueOf(value);
    }

    protected String doBackward(Double value) {
        return value.toString();
    }

    public String toString() {
        return "Doubles.stringConverter()";
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
