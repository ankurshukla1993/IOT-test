package com.facebook.react;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.OnBatchCompleteListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.cxxbridge.LegacyModuleInfo;
import com.facebook.react.cxxbridge.ModuleHolder;
import com.facebook.react.cxxbridge.NativeModuleRegistry;
import com.facebook.react.module.model.ReactModuleInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class NativeModuleRegistryBuilder {
    private final boolean mLazyNativeModulesEnabled;
    private final Map<Class<? extends NativeModule>, ModuleHolder> mModules = new HashMap();
    private final ReactApplicationContext mReactApplicationContext;
    private final Map<String, Class<? extends NativeModule>> namesToType = new HashMap();

    public NativeModuleRegistryBuilder(ReactApplicationContext reactApplicationContext, boolean lazyNativeModulesEnabled) {
        this.mReactApplicationContext = reactApplicationContext;
        this.mLazyNativeModulesEnabled = lazyNativeModulesEnabled;
    }

    public void processPackage(ReactPackage reactPackage) {
        NativeModule nativeModule;
        if (!this.mLazyNativeModulesEnabled) {
            FLog.m99d(ReactConstants.TAG, reactPackage.getClass().getSimpleName() + " is not a LazyReactPackage, falling back to old version.");
            for (NativeModule nativeModule2 : reactPackage.createNativeModules(this.mReactApplicationContext)) {
                addNativeModule(nativeModule2);
            }
        } else if (reactPackage instanceof LazyReactPackage) {
            LazyReactPackage lazyReactPackage = (LazyReactPackage) reactPackage;
            List<ModuleSpec> moduleSpecs = lazyReactPackage.getNativeModules(this.mReactApplicationContext);
            Map<Class, ReactModuleInfo> reactModuleInfoMap = lazyReactPackage.getReactModuleInfoProvider().getReactModuleInfos();
            for (ModuleSpec moduleSpec : moduleSpecs) {
                ModuleHolder moduleHolder;
                Class<? extends NativeModule> type = moduleSpec.getType();
                ReactModuleInfo reactModuleInfo = (ReactModuleInfo) reactModuleInfoMap.get(type);
                if (reactModuleInfo != null) {
                    moduleHolder = new ModuleHolder(reactModuleInfo, moduleSpec.getProvider());
                } else if (BaseJavaModule.class.isAssignableFrom(type)) {
                    throw new IllegalStateException("Native Java module " + type.getSimpleName() + " should be annotated with @ReactModule and added to a @ReactModuleList.");
                } else {
                    nativeModule2 = (NativeModule) moduleSpec.getProvider().get();
                    moduleHolder = new ModuleHolder(new LegacyModuleInfo(type, nativeModule2), nativeModule2);
                }
                String name = moduleHolder.getInfo().name();
                if (this.namesToType.containsKey(name)) {
                    Class<? extends NativeModule> existingNativeModule = (Class) this.namesToType.get(name);
                    if (moduleHolder.getInfo().canOverrideExistingModule()) {
                        this.mModules.remove(existingNativeModule);
                    } else {
                        throw new IllegalStateException("Native module " + type.getSimpleName() + " tried to override " + existingNativeModule.getSimpleName() + " for module name " + name + ". If this was your intention, set canOverrideExistingModule=true");
                    }
                }
                this.namesToType.put(name, type);
                this.mModules.put(type, moduleHolder);
            }
        } else {
            throw new IllegalStateException("Lazy native modules requires all ReactPackage to inherit from LazyReactPackage");
        }
    }

    public void addNativeModule(NativeModule nativeModule) {
        String name = nativeModule.getName();
        Class<? extends NativeModule> type = nativeModule.getClass();
        if (this.namesToType.containsKey(name)) {
            Class<? extends NativeModule> existingModule = (Class) this.namesToType.get(name);
            if (nativeModule.canOverrideExistingModule()) {
                this.mModules.remove(existingModule);
            } else {
                throw new IllegalStateException("Native module " + type.getSimpleName() + " tried to override " + existingModule.getSimpleName() + " for module name " + name + ". If this was your intention, set canOverrideExistingModule=true");
            }
        }
        this.namesToType.put(name, type);
        this.mModules.put(type, new ModuleHolder(new LegacyModuleInfo(type, nativeModule), nativeModule));
    }

    public NativeModuleRegistry build() {
        ArrayList<OnBatchCompleteListener> batchCompleteListenerModules = new ArrayList();
        for (Entry<Class<? extends NativeModule>, ModuleHolder> entry : this.mModules.entrySet()) {
            if (OnBatchCompleteListener.class.isAssignableFrom((Class) entry.getKey())) {
                final ModuleHolder moduleHolder = (ModuleHolder) entry.getValue();
                batchCompleteListenerModules.add(new OnBatchCompleteListener() {
                    public void onBatchComplete() {
                        ((OnBatchCompleteListener) moduleHolder.getModule()).onBatchComplete();
                    }
                });
            }
        }
        return new NativeModuleRegistry(this.mModules, batchCompleteListenerModules);
    }
}
