package com.facebook.react.bridge;

import com.facebook.react.bridge.BaseJavaModule.ArgumentExtractor;

class BaseJavaModule$5 extends ArgumentExtractor<String> {
    BaseJavaModule$5() {
        super(null);
    }

    public String extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
        return jsArguments.getString(atIndex);
    }
}
