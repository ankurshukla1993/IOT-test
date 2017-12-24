package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import java.util.concurrent.Callable;

final class CrashlyticsCore$CrashMarkerCheck implements Callable<Boolean> {
    private final CrashlyticsFileMarker crashMarker;

    public CrashlyticsCore$CrashMarkerCheck(CrashlyticsFileMarker crashMarker) {
        this.crashMarker = crashMarker;
    }

    public Boolean call() throws Exception {
        if (!this.crashMarker.isPresent()) {
            return Boolean.FALSE;
        }
        Fabric.getLogger().d(CrashlyticsCore.TAG, "Found previous crash marker.");
        this.crashMarker.remove();
        return Boolean.TRUE;
    }
}
