package com.facebook.react.bridge;

import com.facebook.react.bridge.BaseJavaModule.ArgumentExtractor;

class BaseJavaModule$10 extends ArgumentExtractor<Promise> {
    BaseJavaModule$10() {
        super(null);
    }

    public int getJSArgumentsNeeded() {
        return 2;
    }

    public Promise extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
        return new PromiseImpl((Callback) BaseJavaModule.access$100().extractArgument(catalystInstance, executorToken, jsArguments, atIndex), (Callback) BaseJavaModule.access$100().extractArgument(catalystInstance, executorToken, jsArguments, atIndex + 1));
    }
}
