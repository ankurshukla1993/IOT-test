package com.facebook.react.devsupport;

import com.facebook.react.bridge.JavaJSExecutor.Factory;

public interface ReactInstanceDevCommandsHandler {
    void onJSBundleLoadedFromServer();

    void onReloadWithJSDebugger(Factory factory);

    void toggleElementInspector();
}
