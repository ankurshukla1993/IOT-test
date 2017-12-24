package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.util.List;

public class UIImplementationProvider {
    public UIImplementation createUIImplementation(ReactApplicationContext reactContext, List<ViewManager> viewManagers, EventDispatcher eventDispatcher) {
        return new UIImplementation(reactContext, (List) viewManagers, eventDispatcher);
    }
}
