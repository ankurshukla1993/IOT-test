package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.cxxbridge.JavaScriptExecutor.Factory;

public class ProxyJavaScriptExecutor$Factory implements Factory {
    private final JavaJSExecutor.Factory mJavaJSExecutorFactory;

    public ProxyJavaScriptExecutor$Factory(JavaJSExecutor.Factory javaJSExecutorFactory) {
        this.mJavaJSExecutorFactory = javaJSExecutorFactory;
    }

    public JavaScriptExecutor create() throws Exception {
        return new ProxyJavaScriptExecutor(this.mJavaJSExecutorFactory.create());
    }
}
