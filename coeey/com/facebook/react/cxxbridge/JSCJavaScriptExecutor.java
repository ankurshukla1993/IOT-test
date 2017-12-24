package com.facebook.react.cxxbridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.ReadableNativeArray;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.soloader.SoLoader;

@DoNotStrip
public class JSCJavaScriptExecutor extends JavaScriptExecutor {

    public static class Factory implements com.facebook.react.cxxbridge.JavaScriptExecutor.Factory {
        private ReadableNativeArray mJSCConfig;

        public Factory(WritableNativeMap jscConfig) {
            WritableNativeArray array = new WritableNativeArray();
            array.pushMap(jscConfig);
            this.mJSCConfig = array;
        }

        public JavaScriptExecutor create() throws Exception {
            return new JSCJavaScriptExecutor(this.mJSCConfig);
        }
    }

    private static native HybridData initHybrid(ReadableNativeArray readableNativeArray);

    static {
        SoLoader.loadLibrary("reactnativejnifb");
    }

    public JSCJavaScriptExecutor(ReadableNativeArray jscConfig) {
        super(initHybrid(jscConfig));
    }
}
