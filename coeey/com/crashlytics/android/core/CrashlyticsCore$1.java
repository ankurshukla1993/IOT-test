package com.crashlytics.android.core;

import io.fabric.sdk.android.services.concurrency.Priority;
import io.fabric.sdk.android.services.concurrency.PriorityCallable;

class CrashlyticsCore$1 extends PriorityCallable<Void> {
    final /* synthetic */ CrashlyticsCore this$0;

    CrashlyticsCore$1(CrashlyticsCore this$0) {
        this.this$0 = this$0;
    }

    public Void call() throws Exception {
        return this.this$0.doInBackground();
    }

    public Priority getPriority() {
        return Priority.IMMEDIATE;
    }
}
