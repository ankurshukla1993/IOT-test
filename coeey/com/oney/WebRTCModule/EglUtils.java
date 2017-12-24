package com.oney.WebRTCModule;

import android.util.Log;
import org.webrtc.EglBase;
import org.webrtc.EglBase.Context;
import org.webrtc.EglBase10;
import org.webrtc.EglBase14;

public class EglUtils {
    private static EglBase rootEglBase;

    public static synchronized EglBase getRootEglBase() {
        EglBase eglBase;
        synchronized (EglUtils.class) {
            if (rootEglBase == null) {
                EglBase eglBase2;
                EglBase eglBase3 = null;
                int[] configAttributes = EglBase.CONFIG_PLAIN;
                RuntimeException cause = null;
                try {
                    if (EglBase14.isEGL14Supported()) {
                        eglBase3 = new EglBase14(null, configAttributes);
                    }
                    eglBase2 = eglBase3;
                } catch (RuntimeException ex) {
                    cause = ex;
                    eglBase2 = null;
                }
                if (eglBase2 == null) {
                    try {
                        eglBase3 = new EglBase10(null, configAttributes);
                    } catch (RuntimeException ex2) {
                        cause = ex2;
                        eglBase3 = eglBase2;
                    }
                } else {
                    eglBase3 = eglBase2;
                }
                if (cause != null) {
                    Log.e(EglUtils.class.getName(), "Failed to create EglBase", cause);
                } else {
                    rootEglBase = eglBase3;
                }
            }
            eglBase = rootEglBase;
        }
        return eglBase;
    }

    public static Context getRootEglBaseContext() {
        EglBase eglBase = getRootEglBase();
        return eglBase == null ? null : eglBase.getEglBaseContext();
    }
}
