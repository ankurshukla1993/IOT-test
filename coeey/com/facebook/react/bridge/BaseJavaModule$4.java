package com.facebook.react.bridge;

import com.facebook.react.bridge.BaseJavaModule.ArgumentExtractor;

class BaseJavaModule$4 extends ArgumentExtractor<Integer> {
    BaseJavaModule$4() {
        super(null);
    }

    public Integer extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
        return Integer.valueOf((int) jsArguments.getDouble(atIndex));
    }
}
