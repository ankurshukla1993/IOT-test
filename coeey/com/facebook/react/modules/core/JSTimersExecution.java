package com.facebook.react.modules.core;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.SupportsWebWorkers;
import com.facebook.react.bridge.WritableArray;

@SupportsWebWorkers
public interface JSTimersExecution extends JavaScriptModule {
    void callIdleCallbacks(double d);

    void callTimers(WritableArray writableArray);

    void emitTimeDriftWarning(String str);
}
