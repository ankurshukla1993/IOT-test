package com.facebook.react;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.cxxbridge.JSBundleLoader;
import com.facebook.react.cxxbridge.JavaScriptExecutor.Factory;

class ReactInstanceManager$ReactContextInitParams {
    private final JSBundleLoader mJsBundleLoader;
    private final Factory mJsExecutorFactory;
    final /* synthetic */ ReactInstanceManager this$0;

    public ReactInstanceManager$ReactContextInitParams(ReactInstanceManager reactInstanceManager, Factory jsExecutorFactory, JSBundleLoader jsBundleLoader) {
        this.this$0 = reactInstanceManager;
        this.mJsExecutorFactory = (Factory) Assertions.assertNotNull(jsExecutorFactory);
        this.mJsBundleLoader = (JSBundleLoader) Assertions.assertNotNull(jsBundleLoader);
    }

    public Factory getJsExecutorFactory() {
        return this.mJsExecutorFactory;
    }

    public JSBundleLoader getJsBundleLoader() {
        return this.mJsBundleLoader;
    }
}
