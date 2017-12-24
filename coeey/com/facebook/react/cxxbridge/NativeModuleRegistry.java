package com.facebook.react.cxxbridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.OnBatchCompleteListener;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.systrace.Systrace;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class NativeModuleRegistry {
    private final ArrayList<OnBatchCompleteListener> mBatchCompleteListenerModules;
    private final Map<Class<? extends NativeModule>, ModuleHolder> mModules;

    public NativeModuleRegistry(Map<Class<? extends NativeModule>, ModuleHolder> modules, ArrayList<OnBatchCompleteListener> batchCompleteListenerModules) {
        this.mModules = modules;
        this.mBatchCompleteListenerModules = batchCompleteListenerModules;
    }

    Collection<JavaModuleWrapper> getJavaModules(CatalystInstanceImpl catalystInstanceImpl) {
        ArrayList<JavaModuleWrapper> javaModules = new ArrayList();
        for (Entry<Class<? extends NativeModule>, ModuleHolder> entry : this.mModules.entrySet()) {
            if (!CxxModuleWrapper.class.isAssignableFrom((Class) entry.getKey())) {
                javaModules.add(new JavaModuleWrapper(catalystInstanceImpl, (ModuleHolder) entry.getValue()));
            }
        }
        return javaModules;
    }

    Collection<CxxModuleWrapper> getCxxModules() {
        ArrayList<CxxModuleWrapper> cxxModules = new ArrayList();
        for (Entry<Class<? extends NativeModule>, ModuleHolder> entry : this.mModules.entrySet()) {
            if (CxxModuleWrapper.class.isAssignableFrom((Class) entry.getKey())) {
                cxxModules.add((CxxModuleWrapper) ((ModuleHolder) entry.getValue()).getModule());
            }
        }
        return cxxModules;
    }

    void notifyCatalystInstanceDestroy() {
        UiThreadUtil.assertOnUiThread();
        Systrace.beginSection(0, "NativeModuleRegistry_notifyCatalystInstanceDestroy");
        try {
            for (ModuleHolder module : this.mModules.values()) {
                module.destroy();
            }
        } finally {
            Systrace.endSection(0);
        }
    }

    void notifyCatalystInstanceInitialized() {
        UiThreadUtil.assertOnUiThread();
        ReactMarker.logMarker(ReactMarkerConstants.NATIVE_MODULE_INITIALIZE_START);
        Systrace.beginSection(0, "NativeModuleRegistry_notifyCatalystInstanceInitialized");
        try {
            for (ModuleHolder module : this.mModules.values()) {
                module.initialize();
            }
        } finally {
            Systrace.endSection(0);
            ReactMarker.logMarker(ReactMarkerConstants.NATIVE_MODULE_INITIALIZE_END);
        }
    }

    public void onBatchComplete() {
        for (int i = 0; i < this.mBatchCompleteListenerModules.size(); i++) {
            ((OnBatchCompleteListener) this.mBatchCompleteListenerModules.get(i)).onBatchComplete();
        }
    }

    public <T extends NativeModule> boolean hasModule(Class<T> moduleInterface) {
        return this.mModules.containsKey(moduleInterface);
    }

    public <T extends NativeModule> T getModule(Class<T> moduleInterface) {
        return ((ModuleHolder) Assertions.assertNotNull(this.mModules.get(moduleInterface))).getModule();
    }

    public List<NativeModule> getAllModules() {
        List<NativeModule> modules = new ArrayList();
        for (ModuleHolder module : this.mModules.values()) {
            modules.add(module.getModule());
        }
        return modules;
    }
}
