package com.facebook.react.flat;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.UIImplementationProvider;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.util.List;

public final class FlatUIImplementationProvider extends UIImplementationProvider {
    public FlatUIImplementation createUIImplementation(ReactApplicationContext reactContext, List<ViewManager> viewManagers, EventDispatcher eventDispatcher) {
        return FlatUIImplementation.createInstance(reactContext, viewManagers, eventDispatcher);
    }
}
