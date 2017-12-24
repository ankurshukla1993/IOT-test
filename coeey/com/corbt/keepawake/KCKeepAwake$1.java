package com.corbt.keepawake;

import android.app.Activity;

class KCKeepAwake$1 implements Runnable {
    final /* synthetic */ KCKeepAwake this$0;
    final /* synthetic */ Activity val$activity;

    KCKeepAwake$1(KCKeepAwake this$0, Activity activity) {
        this.this$0 = this$0;
        this.val$activity = activity;
    }

    public void run() {
        this.val$activity.getWindow().addFlags(128);
    }
}
