package com.crashlytics.android.core;

import java.util.concurrent.Callable;

class CrashlyticsCore$4 implements Callable<Boolean> {
    final /* synthetic */ CrashlyticsCore this$0;

    CrashlyticsCore$4(CrashlyticsCore this$0) {
        this.this$0 = this$0;
    }

    public Boolean call() throws Exception {
        return Boolean.valueOf(CrashlyticsCore.access$100(this.this$0).isPresent());
    }
}
