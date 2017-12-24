package com.facebook.react.modules.statusbar;

import android.app.Activity;

class StatusBarModule$3 implements Runnable {
    final /* synthetic */ StatusBarModule this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ boolean val$hidden;

    StatusBarModule$3(StatusBarModule this$0, boolean z, Activity activity) {
        this.this$0 = this$0;
        this.val$hidden = z;
        this.val$activity = activity;
    }

    public void run() {
        if (this.val$hidden) {
            this.val$activity.getWindow().addFlags(1024);
            this.val$activity.getWindow().clearFlags(2048);
            return;
        }
        this.val$activity.getWindow().addFlags(2048);
        this.val$activity.getWindow().clearFlags(1024);
    }
}
