package com.facebook.react.modules.debug;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "SourceCode")
public class SourceCodeModule extends BaseJavaModule {
    public static final String NAME = "SourceCode";
    private final ReactContext mReactContext;

    public SourceCodeModule(ReactContext reactContext) {
        this.mReactContext = reactContext;
    }

    public String getName() {
        return NAME;
    }

    @Nullable
    public Map<String, Object> getConstants() {
        HashMap<String, Object> constants = new HashMap();
        constants.put("scriptURL", (String) Assertions.assertNotNull(this.mReactContext.getCatalystInstance().getSourceURL(), "No source URL loaded, have you initialised the instance?"));
        return constants;
    }
}
