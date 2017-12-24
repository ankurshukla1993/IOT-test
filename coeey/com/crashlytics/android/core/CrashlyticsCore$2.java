package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import java.util.concurrent.Callable;

class CrashlyticsCore$2 implements Callable<Void> {
    final /* synthetic */ CrashlyticsCore this$0;

    CrashlyticsCore$2(CrashlyticsCore this$0) {
        this.this$0 = this$0;
    }

    public Void call() throws Exception {
        CrashlyticsCore.access$100(this.this$0).create();
        Fabric.getLogger().d(CrashlyticsCore.TAG, "Initialization marker file created.");
        return null;
    }
}
