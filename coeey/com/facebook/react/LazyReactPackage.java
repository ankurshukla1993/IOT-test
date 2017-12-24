package com.facebook.react;

import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class LazyReactPackage implements ReactPackage {
    public abstract List<ModuleSpec> getNativeModules(ReactApplicationContext reactApplicationContext);

    public abstract ReactModuleInfoProvider getReactModuleInfoProvider();

    public static ReactModuleInfoProvider getReactModuleInfoProviderViaReflection(LazyReactPackage lazyReactPackage) {
        try {
            Class<?> reactModuleInfoProviderClass = Class.forName(lazyReactPackage.getClass().getCanonicalName() + "$$ReactModuleInfoProvider");
            if (reactModuleInfoProviderClass == null) {
                throw new RuntimeException("ReactModuleInfoProvider class for " + lazyReactPackage.getClass().getCanonicalName() + " not found.");
            }
            try {
                return (ReactModuleInfoProvider) reactModuleInfoProviderClass.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("Unable to instantiate ReactModuleInfoProvider for " + lazyReactPackage.getClass(), e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Unable to instantiate ReactModuleInfoProvider for " + lazyReactPackage.getClass(), e2);
            }
        } catch (ClassNotFoundException e3) {
            throw new RuntimeException(e3);
        }
    }

    public final List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList();
        for (ModuleSpec holder : getNativeModules(reactContext)) {
            SystraceMessage.beginSection(0, "createNativeModule").arg("module", holder.getType()).flush();
            try {
                modules.add(holder.getProvider().get());
                Systrace.endSection(0);
            } catch (Throwable th) {
                Systrace.endSection(0);
            }
        }
        return modules;
    }

    public List<ModuleSpec> getViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        List<ModuleSpec> viewManagerModuleSpecs = getViewManagers(reactContext);
        if (viewManagerModuleSpecs == null || viewManagerModuleSpecs.isEmpty()) {
            return Collections.emptyList();
        }
        List<ViewManager> viewManagers = new ArrayList();
        for (ModuleSpec moduleSpec : viewManagerModuleSpecs) {
            viewManagers.add((ViewManager) moduleSpec.getProvider().get());
        }
        return viewManagers;
    }
}
