package com.facebook.react;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CompositeReactPackage implements ReactPackage {
    private final List<ReactPackage> mChildReactPackages = new ArrayList();

    public CompositeReactPackage(ReactPackage arg1, ReactPackage arg2, ReactPackage... args) {
        this.mChildReactPackages.add(arg1);
        this.mChildReactPackages.add(arg2);
        for (ReactPackage reactPackage : args) {
            this.mChildReactPackages.add(reactPackage);
        }
    }

    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        Map<String, NativeModule> moduleMap = new HashMap();
        for (ReactPackage reactPackage : this.mChildReactPackages) {
            for (NativeModule nativeModule : reactPackage.createNativeModules(reactContext)) {
                moduleMap.put(nativeModule.getName(), nativeModule);
            }
        }
        return new ArrayList(moduleMap.values());
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        Set<Class<? extends JavaScriptModule>> moduleSet = new HashSet();
        for (ReactPackage reactPackage : this.mChildReactPackages) {
            for (Class<? extends JavaScriptModule> jsModule : reactPackage.createJSModules()) {
                moduleSet.add(jsModule);
            }
        }
        return new ArrayList(moduleSet);
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        Map<String, ViewManager> viewManagerMap = new HashMap();
        for (ReactPackage reactPackage : this.mChildReactPackages) {
            for (ViewManager viewManager : reactPackage.createViewManagers(reactContext)) {
                viewManagerMap.put(viewManager.getName(), viewManager);
            }
        }
        return new ArrayList(viewManagerMap.values());
    }
}
