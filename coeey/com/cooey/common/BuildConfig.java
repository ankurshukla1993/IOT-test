package com.cooey.common;

import com.facebook.internal.ServerProtocol;

public final class BuildConfig {
    public static final String APPLICATION_ID = "com.cooey.common";
    public static final String BUILD_TYPE = "release";
    public static final boolean DEBUG = Boolean.parseBoolean(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
    public static final String FLAVOR = "";
    public static final int VERSION_CODE = 1;
    public static final String VERSION_NAME = "1.0";
}
