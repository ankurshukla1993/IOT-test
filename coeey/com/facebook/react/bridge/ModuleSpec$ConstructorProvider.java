package com.facebook.react.bridge;

import java.lang.reflect.Constructor;
import javax.annotation.Nullable;
import javax.inject.Provider;

abstract class ModuleSpec$ConstructorProvider implements Provider<NativeModule> {
    @Nullable
    protected Constructor<? extends NativeModule> mConstructor;

    public ModuleSpec$ConstructorProvider(Class<? extends NativeModule> cls, Class[] signature) {
    }

    protected Constructor<? extends NativeModule> getConstructor(Class<? extends NativeModule> mType, Class[] signature) throws NoSuchMethodException {
        if (this.mConstructor != null) {
            return this.mConstructor;
        }
        return mType.getConstructor(signature);
    }
}
