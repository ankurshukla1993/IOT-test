package com.facebook.react.modules.statusbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.WindowInsets;

class StatusBarModule$2 implements Runnable {
    final /* synthetic */ StatusBarModule this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ boolean val$translucent;

    class C13221 implements OnApplyWindowInsetsListener {
        C13221() {
        }

        public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
            WindowInsets defaultInsets = v.onApplyWindowInsets(insets);
            return defaultInsets.replaceSystemWindowInsets(defaultInsets.getSystemWindowInsetLeft(), 0, defaultInsets.getSystemWindowInsetRight(), defaultInsets.getSystemWindowInsetBottom());
        }
    }

    StatusBarModule$2(StatusBarModule this$0, Activity activity, boolean z) {
        this.this$0 = this$0;
        this.val$activity = activity;
        this.val$translucent = z;
    }

    @TargetApi(21)
    public void run() {
        View decorView = this.val$activity.getWindow().getDecorView();
        if (this.val$translucent) {
            decorView.setOnApplyWindowInsetsListener(new C13221());
        } else {
            decorView.setOnApplyWindowInsetsListener(null);
        }
        ViewCompat.requestApplyInsets(decorView);
    }
}
