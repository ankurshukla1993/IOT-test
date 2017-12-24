package com.facebook.react.bridge;

import com.facebook.react.bridge.BaseJavaModule.ArgumentExtractor;

class BaseJavaModule$3 extends ArgumentExtractor<Float> {
    BaseJavaModule$3() {
        super(null);
    }

    public Float extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
        return Float.valueOf((float) jsArguments.getDouble(atIndex));
    }
}
