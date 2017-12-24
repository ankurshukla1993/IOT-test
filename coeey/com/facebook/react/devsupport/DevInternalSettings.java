package com.facebook.react.devsupport;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.modules.debug.DeveloperSettings;
import javax.annotation.Nullable;

@VisibleForTesting
public class DevInternalSettings implements DeveloperSettings, OnSharedPreferenceChangeListener {
    private static final String PREFS_ANIMATIONS_DEBUG_KEY = "animations_debug";
    private static final String PREFS_DEBUG_SERVER_HOST_KEY = "debug_http_host";
    private static final String PREFS_FPS_DEBUG_KEY = "fps_debug";
    private static final String PREFS_HOT_MODULE_REPLACEMENT_KEY = "hot_module_replacement";
    private static final String PREFS_INSPECTOR_DEBUG_KEY = "inspector_debug";
    private static final String PREFS_JS_DEV_MODE_DEBUG_KEY = "js_dev_mode_debug";
    private static final String PREFS_JS_MINIFY_DEBUG_KEY = "js_minify_debug";
    private static final String PREFS_RELOAD_ON_JS_CHANGE_KEY = "reload_on_js_change";
    private static final String PREFS_REMOTE_JS_DEBUG_KEY = "remote_js_debug";
    private final Listener mListener;
    private final SharedPreferences mPreferences;

    public DevInternalSettings(Context applicationContext, Listener listener) {
        this.mListener = listener;
        this.mPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        this.mPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    public boolean isFpsDebugEnabled() {
        return this.mPreferences.getBoolean(PREFS_FPS_DEBUG_KEY, false);
    }

    public void setFpsDebugEnabled(boolean enabled) {
        this.mPreferences.edit().putBoolean(PREFS_FPS_DEBUG_KEY, enabled).apply();
    }

    public boolean isAnimationFpsDebugEnabled() {
        return this.mPreferences.getBoolean(PREFS_ANIMATIONS_DEBUG_KEY, false);
    }

    public boolean isJSDevModeEnabled() {
        return this.mPreferences.getBoolean(PREFS_JS_DEV_MODE_DEBUG_KEY, true);
    }

    public boolean isJSMinifyEnabled() {
        return this.mPreferences.getBoolean(PREFS_JS_MINIFY_DEBUG_KEY, false);
    }

    @Nullable
    public String getDebugServerHost() {
        return this.mPreferences.getString(PREFS_DEBUG_SERVER_HOST_KEY, null);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (this.mListener == null) {
            return;
        }
        if (PREFS_FPS_DEBUG_KEY.equals(key) || PREFS_RELOAD_ON_JS_CHANGE_KEY.equals(key) || PREFS_JS_DEV_MODE_DEBUG_KEY.equals(key) || PREFS_JS_MINIFY_DEBUG_KEY.equals(key)) {
            this.mListener.onInternalSettingsChanged();
        }
    }

    public boolean isHotModuleReplacementEnabled() {
        return this.mPreferences.getBoolean(PREFS_HOT_MODULE_REPLACEMENT_KEY, false);
    }

    public void setHotModuleReplacementEnabled(boolean enabled) {
        this.mPreferences.edit().putBoolean(PREFS_HOT_MODULE_REPLACEMENT_KEY, enabled).apply();
    }

    public boolean isReloadOnJSChangeEnabled() {
        return this.mPreferences.getBoolean(PREFS_RELOAD_ON_JS_CHANGE_KEY, false);
    }

    public void setReloadOnJSChangeEnabled(boolean enabled) {
        this.mPreferences.edit().putBoolean(PREFS_RELOAD_ON_JS_CHANGE_KEY, enabled).apply();
    }

    public boolean isElementInspectorEnabled() {
        return this.mPreferences.getBoolean(PREFS_INSPECTOR_DEBUG_KEY, false);
    }

    public void setElementInspectorEnabled(boolean enabled) {
        this.mPreferences.edit().putBoolean(PREFS_INSPECTOR_DEBUG_KEY, enabled).apply();
    }

    public boolean isRemoteJSDebugEnabled() {
        return this.mPreferences.getBoolean(PREFS_REMOTE_JS_DEBUG_KEY, false);
    }

    public void setRemoteJSDebugEnabled(boolean remoteJSDebugEnabled) {
        this.mPreferences.edit().putBoolean(PREFS_REMOTE_JS_DEBUG_KEY, remoteJSDebugEnabled).apply();
    }
}
