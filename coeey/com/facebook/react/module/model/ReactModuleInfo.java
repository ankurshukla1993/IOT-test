package com.facebook.react.module.model;

public class ReactModuleInfo implements Info {
    private final boolean mCanOverrideExistingModule;
    private final String mName;
    private final boolean mNeedsEagerInit;
    private final boolean mSupportsWebWorkers;

    public ReactModuleInfo(String name, boolean canOverrideExistingModule, boolean supportsWebWorkers, boolean needsEagerInit) {
        this.mName = name;
        this.mCanOverrideExistingModule = canOverrideExistingModule;
        this.mSupportsWebWorkers = supportsWebWorkers;
        this.mNeedsEagerInit = needsEagerInit;
    }

    public String name() {
        return this.mName;
    }

    public boolean canOverrideExistingModule() {
        return this.mCanOverrideExistingModule;
    }

    public boolean supportsWebWorkers() {
        return this.mSupportsWebWorkers;
    }

    public boolean needsEagerInit() {
        return this.mNeedsEagerInit;
    }
}
