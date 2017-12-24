package com.google.common.primitives;

import com.google.common.base.Converter;
import java.io.Serializable;

final class Floats$FloatConverter extends Converter<String, Float> implements Serializable {
    static final Floats$FloatConverter INSTANCE = new Floats$FloatConverter();
    private static final long serialVersionUID = 1;

    private Floats$FloatConverter() {
    }

    protected Float doForward(String value) {
        return Float.valueOf(value);
    }

    protected String doBackward(Float value) {
        return value.toString();
    }

    public String toString() {
        return "Floats.stringConverter()";
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
