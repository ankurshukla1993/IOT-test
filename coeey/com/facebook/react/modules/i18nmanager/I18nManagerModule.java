package com.facebook.react.modules.i18nmanager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import java.util.Locale;
import java.util.Map;

@ReactModule(name = "I18nManager")
public class I18nManagerModule extends ReactContextBaseJavaModule {
    private final I18nUtil sharedI18nUtilInstance = I18nUtil.getInstance();

    public I18nManagerModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "I18nManager";
    }

    public Map<String, Object> getConstants() {
        Locale locale = getReactApplicationContext().getBaseContext().getResources().getConfiguration().locale;
        Map<String, Object> constants = MapBuilder.newHashMap();
        constants.put("isRTL", Boolean.valueOf(this.sharedI18nUtilInstance.isRTL(getReactApplicationContext())));
        constants.put("localeIdentifier", locale.toString());
        return constants;
    }

    @ReactMethod
    public void allowRTL(boolean value) {
        this.sharedI18nUtilInstance.allowRTL(getReactApplicationContext(), value);
    }

    @ReactMethod
    public void forceRTL(boolean value) {
        this.sharedI18nUtilInstance.forceRTL(getReactApplicationContext(), value);
    }
}
