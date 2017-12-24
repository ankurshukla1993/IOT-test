package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.module.model.Info;

public class LegacyModuleInfo implements Info {
    public final NativeModule mNativeModule;
    public final Class<?> mType;

    public LegacyModuleInfo(Class<?> type, NativeModule nativeModule) {
        this.mType = type;
        this.mNativeModule = nativeModule;
    }

    public String name() {
        return this.mNativeModule.getName();
    }

    public boolean canOverrideExistingModule() {
        return this.mNativeModule.canOverrideExistingModule();
    }

    public boolean supportsWebWorkers() {
        return this.mNativeModule.supportsWebWorkers();
    }

    public boolean needsEagerInit() {
        return true;
    }
}
