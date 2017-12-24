package com.facebook.react.modules.statusbar;

import android.annotation.TargetApi;
import android.app.Activity;

class StatusBarModule$4 implements Runnable {
    final /* synthetic */ StatusBarModule this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ String val$style;

    StatusBarModule$4(StatusBarModule this$0, Activity activity, String str) {
        this.this$0 = this$0;
        this.val$activity = activity;
        this.val$style = str;
    }

    @TargetApi(23)
    public void run() {
        this.val$activity.getWindow().getDecorView().setSystemUiVisibility(this.val$style.equals("dark-content") ? 8192 : 0);
    }
}
