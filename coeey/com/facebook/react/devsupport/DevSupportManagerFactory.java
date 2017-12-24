package com.facebook.react.devsupport;

import android.content.Context;
import javax.annotation.Nullable;

public class DevSupportManagerFactory {
    private static final String DEVSUPPORT_IMPL_CLASS = "DevSupportManagerImpl";
    private static final String DEVSUPPORT_IMPL_PACKAGE = "com.facebook.react.devsupport";

    public static DevSupportManager create(Context applicationContext, ReactInstanceDevCommandsHandler reactInstanceCommandsHandler, @Nullable String packagerPathForJSBundleName, boolean enableOnCreate) {
        return create(applicationContext, reactInstanceCommandsHandler, packagerPathForJSBundleName, enableOnCreate, null);
    }

    public static DevSupportManager create(Context applicationContext, ReactInstanceDevCommandsHandler reactInstanceCommandsHandler, @Nullable String packagerPathForJSBundleName, boolean enableOnCreate, @Nullable RedBoxHandler redBoxHandler) {
        if (!enableOnCreate) {
            return new DisabledDevSupportManager();
        }
        try {
            return (DevSupportManager) Class.forName(new StringBuilder(DEVSUPPORT_IMPL_PACKAGE).append(".").append(DEVSUPPORT_IMPL_CLASS).toString()).getConstructor(new Class[]{Context.class, ReactInstanceDevCommandsHandler.class, String.class, Boolean.TYPE, RedBoxHandler.class}).newInstance(new Object[]{applicationContext, reactInstanceCommandsHandler, packagerPathForJSBundleName, Boolean.valueOf(true), redBoxHandler});
        } catch (Exception e) {
            throw new RuntimeException("Requested enabled DevSupportManager, but DevSupportManagerImpl class was not found or could not be created", e);
        }
    }
}
