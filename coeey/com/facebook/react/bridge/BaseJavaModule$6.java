package com.facebook.react.bridge;

import com.facebook.react.bridge.BaseJavaModule.ArgumentExtractor;

class BaseJavaModule$6 extends ArgumentExtractor<ReadableNativeArray> {
    BaseJavaModule$6() {
        super(null);
    }

    public ReadableNativeArray extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
        return jsArguments.getArray(atIndex);
    }
}
