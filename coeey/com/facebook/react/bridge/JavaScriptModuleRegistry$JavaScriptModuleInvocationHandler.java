package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

class JavaScriptModuleRegistry$JavaScriptModuleInvocationHandler implements InvocationHandler {
    private final CatalystInstance mCatalystInstance;
    private final WeakReference<ExecutorToken> mExecutorToken;
    private final JavaScriptModuleRegistration mModuleRegistration;

    public JavaScriptModuleRegistry$JavaScriptModuleInvocationHandler(ExecutorToken executorToken, CatalystInstance catalystInstance, JavaScriptModuleRegistration moduleRegistration) {
        this.mExecutorToken = new WeakReference(executorToken);
        this.mCatalystInstance = catalystInstance;
        this.mModuleRegistration = moduleRegistration;
    }

    @Nullable
    public Object invoke(Object proxy, Method method, @Nullable Object[] args) throws Throwable {
        ExecutorToken executorToken = (ExecutorToken) this.mExecutorToken.get();
        if (executorToken == null) {
            FLog.w(ReactConstants.TAG, "Dropping JS call, ExecutorToken went away...");
        } else {
            this.mCatalystInstance.callFunction(executorToken, this.mModuleRegistration.getName(), method.getName(), args != null ? Arguments.fromJavaArgs(args) : new WritableNativeArray());
        }
        return null;
    }
}
