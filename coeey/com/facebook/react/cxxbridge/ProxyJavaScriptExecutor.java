package com.facebook.react.cxxbridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.soloader.SoLoader;
import javax.annotation.Nullable;

@DoNotStrip
public class ProxyJavaScriptExecutor extends JavaScriptExecutor {
    @Nullable
    private JavaJSExecutor mJavaJSExecutor;

    private static native HybridData initHybrid(JavaJSExecutor javaJSExecutor);

    static {
        SoLoader.loadLibrary("reactnativejnifb");
    }

    public ProxyJavaScriptExecutor(JavaJSExecutor executor) {
        super(initHybrid(executor));
        this.mJavaJSExecutor = executor;
    }

    public void close() {
        if (this.mJavaJSExecutor != null) {
            this.mJavaJSExecutor.close();
            this.mJavaJSExecutor = null;
        }
    }
}
