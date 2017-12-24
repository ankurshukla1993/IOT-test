package com.facebook.react.bridge;

import com.facebook.react.bridge.BaseJavaModule.ArgumentExtractor;

class BaseJavaModule$2 extends ArgumentExtractor<Double> {
    BaseJavaModule$2() {
        super(null);
    }

    public Double extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
        return Double.valueOf(jsArguments.getDouble(atIndex));
    }
}
