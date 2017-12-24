package com.facebook.react.common;

import android.app.Application;
import com.facebook.infer.annotation.Assertions;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@Deprecated
public class ApplicationHolder {
    private static Application sApplication;

    public static void setApplication(Application application) {
        sApplication = application;
    }

    @DoNotStrip
    public static Application getApplication() {
        return (Application) Assertions.assertNotNull(sApplication);
    }
}
