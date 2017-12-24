package com.facebook.react.modules.debug;

public interface DeveloperSettings {
    boolean isAnimationFpsDebugEnabled();

    boolean isElementInspectorEnabled();

    boolean isFpsDebugEnabled();

    boolean isJSDevModeEnabled();

    boolean isJSMinifyEnabled();

    boolean isRemoteJSDebugEnabled();

    void setRemoteJSDebugEnabled(boolean z);
}
