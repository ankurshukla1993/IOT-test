package com.corbt.keepawake;

import android.app.Activity;

class KCKeepAwake$2 implements Runnable {
    final /* synthetic */ KCKeepAwake this$0;
    final /* synthetic */ Activity val$activity;

    KCKeepAwake$2(KCKeepAwake this$0, Activity activity) {
        this.this$0 = this$0;
        this.val$activity = activity;
    }

    public void run() {
        this.val$activity.getWindow().clearFlags(128);
    }
}
