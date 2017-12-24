package com.facebook.react.bridge;

import com.facebook.react.bridge.BaseJavaModule.ArgumentExtractor;

class BaseJavaModule$7 extends ArgumentExtractor<Dynamic> {
    BaseJavaModule$7() {
        super(null);
    }

    public Dynamic extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
        return DynamicFromArray.create(jsArguments, atIndex);
    }
}
