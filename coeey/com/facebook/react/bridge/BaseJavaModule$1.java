package com.facebook.react.bridge;

import com.facebook.react.bridge.BaseJavaModule.ArgumentExtractor;

class BaseJavaModule$1 extends ArgumentExtractor<Boolean> {
    BaseJavaModule$1() {
        super(null);
    }

    public Boolean extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
        return Boolean.valueOf(jsArguments.getBoolean(atIndex));
    }
}
