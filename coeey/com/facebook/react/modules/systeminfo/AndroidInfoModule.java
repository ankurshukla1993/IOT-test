package com.facebook.react.modules.systeminfo;

import android.os.Build.VERSION;
import com.facebook.internal.ServerProtocol;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "AndroidConstants")
public class AndroidInfoModule extends BaseJavaModule {
    private static final String IS_TESTING = "IS_TESTING";

    public String getName() {
        return "AndroidConstants";
    }

    @Nullable
    public Map<String, Object> getConstants() {
        HashMap<String, Object> constants = new HashMap();
        constants.put("Version", Integer.valueOf(VERSION.SDK_INT));
        constants.put("ServerHost", AndroidInfoHelpers.getServerHost());
        constants.put("isTesting", Boolean.valueOf(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(System.getProperty(IS_TESTING))));
        return constants;
    }
}
