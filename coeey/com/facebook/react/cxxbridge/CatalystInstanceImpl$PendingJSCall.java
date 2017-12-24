package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.ExecutorToken;
import com.facebook.react.bridge.NativeArray;

class CatalystInstanceImpl$PendingJSCall {
    public NativeArray mArguments;
    public ExecutorToken mExecutorToken;
    public String mMethod;
    public String mModule;

    public CatalystInstanceImpl$PendingJSCall(ExecutorToken executorToken, String module, String method, NativeArray arguments) {
        this.mExecutorToken = executorToken;
        this.mModule = module;
        this.mMethod = method;
        this.mArguments = arguments;
    }
}
