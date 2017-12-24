package com.facebook.react.bridge;

import javax.inject.Provider;

public class ModuleSpec {
    private static final Class[] CONTEXT_SIGNATURE = new Class[]{ReactApplicationContext.class};
    private static final Class[] EMPTY_SIGNATURE = new Class[0];
    private final Provider<? extends NativeModule> mProvider;
    private final Class<? extends NativeModule> mType;

    public static ModuleSpec simple(final Class<? extends NativeModule> type) {
        return new ModuleSpec(type, new ConstructorProvider(EMPTY_SIGNATURE, type) {
            public NativeModule get() {
                try {
                    return (NativeModule) getConstructor(type, ModuleSpec.EMPTY_SIGNATURE).newInstance(new Object[0]);
                } catch (Exception e) {
                    throw new RuntimeException("ModuleSpec with class: " + type.getName(), e);
                }
            }
        });
    }

    public static ModuleSpec simple(final Class<? extends NativeModule> type, final ReactApplicationContext context) {
        return new ModuleSpec(type, new ConstructorProvider(CONTEXT_SIGNATURE, type) {
            public NativeModule get() {
                try {
                    return (NativeModule) getConstructor(type, ModuleSpec.CONTEXT_SIGNATURE).newInstance(new Object[]{context});
                } catch (Exception e) {
                    throw new RuntimeException("ModuleSpec with class: " + type.getName(), e);
                }
            }
        });
    }

    public ModuleSpec(Class<? extends NativeModule> type, Provider<? extends NativeModule> provider) {
        this.mType = type;
        this.mProvider = provider;
    }

    public Class<? extends NativeModule> getType() {
        return this.mType;
    }

    public Provider<? extends NativeModule> getProvider() {
        return this.mProvider;
    }
}
