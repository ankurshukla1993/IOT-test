package com.google.common.util.concurrent;

import com.google.common.base.Function;
import java.lang.reflect.Constructor;
import java.util.Arrays;

class Futures$7 implements Function<Constructor<?>, Boolean> {
    Futures$7() {
    }

    public Boolean apply(Constructor<?> input) {
        return Boolean.valueOf(Arrays.asList(input.getParameterTypes()).contains(String.class));
    }
}
