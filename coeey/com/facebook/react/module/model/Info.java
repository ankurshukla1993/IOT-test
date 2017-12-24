package com.facebook.react.module.model;

public interface Info {
    boolean canOverrideExistingModule();

    String name();

    boolean needsEagerInit();

    boolean supportsWebWorkers();
}
