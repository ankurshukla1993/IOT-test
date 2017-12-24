package com.facebook.react.modules.i18nmanager;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.support.v4.text.TextUtilsCompat;
import java.util.Locale;

public class I18nUtil {
    private static final String KEY_FOR_PREFS_ALLOWRTL = "RCTI18nUtil_allowRTL";
    private static final String KEY_FOR_PREFS_FORCERTL = "RCTI18nUtil_forceRTL";
    private static final String SHARED_PREFS_NAME = "com.facebook.react.modules.i18nmanager.I18nUtil";
    private static I18nUtil sharedI18nUtilInstance = null;

    private I18nUtil() {
    }

    public static I18nUtil getInstance() {
        if (sharedI18nUtilInstance == null) {
            sharedI18nUtilInstance = new I18nUtil();
        }
        return sharedI18nUtilInstance;
    }

    public boolean isRTL(Context context) {
        if (isRTLForced(context)) {
            return true;
        }
        if (isRTLAllowed(context) && isDevicePreferredLanguageRTL()) {
            return true;
        }
        return false;
    }

    private boolean isRTLAllowed(Context context) {
        return isPrefSet(context, KEY_FOR_PREFS_ALLOWRTL, true);
    }

    public void allowRTL(Context context, boolean allowRTL) {
        setPref(context, KEY_FOR_PREFS_ALLOWRTL, allowRTL);
    }

    private boolean isRTLForced(Context context) {
        return isPrefSet(context, KEY_FOR_PREFS_FORCERTL, false);
    }

    public void forceRTL(Context context, boolean forceRTL) {
        setPref(context, KEY_FOR_PREFS_FORCERTL, forceRTL);
    }

    private boolean isDevicePreferredLanguageRTL() {
        if (TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
            return true;
        }
        return false;
    }

    private boolean isPrefSet(Context context, String key, boolean defaultValue) {
        return context.getSharedPreferences(SHARED_PREFS_NAME, 0).getBoolean(key, defaultValue);
    }

    private void setPref(Context context, String key, boolean value) {
        Editor editor = context.getSharedPreferences(SHARED_PREFS_NAME, 0).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
}
