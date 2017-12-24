package com.facebook.react.uimanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewManagerRegistry {
    private final Map<String, ViewManager> mViewManagers = new HashMap();

    public ViewManagerRegistry(List<ViewManager> viewManagerList) {
        for (ViewManager viewManager : viewManagerList) {
            this.mViewManagers.put(viewManager.getName(), viewManager);
        }
    }

    public ViewManager get(String className) {
        ViewManager viewManager = (ViewManager) this.mViewManagers.get(className);
        if (viewManager != null) {
            return viewManager;
        }
        throw new IllegalViewOperationException("No ViewManager defined for class " + className);
    }
}
