package com.facebook.react.bridge;

import com.facebook.react.bridge.BaseJavaModule.ArgumentExtractor;

class BaseJavaModule$8 extends ArgumentExtractor<ReadableMap> {
    BaseJavaModule$8() {
        super(null);
    }

    public ReadableMap extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
        return jsArguments.getMap(atIndex);
    }
}
