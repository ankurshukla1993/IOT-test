package com.facebook.react.uimanager;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.WritableMap;

public interface AppRegistry extends JavaScriptModule {
    void runApplication(String str, WritableMap writableMap);

    void startHeadlessTask(int i, String str, WritableMap writableMap);

    void unmountApplicationComponentAtRootTag(int i);
}
