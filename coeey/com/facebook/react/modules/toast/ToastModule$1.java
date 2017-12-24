package com.facebook.react.modules.toast;

import android.widget.Toast;

class ToastModule$1 implements Runnable {
    final /* synthetic */ ToastModule this$0;
    final /* synthetic */ int val$duration;
    final /* synthetic */ String val$message;

    ToastModule$1(ToastModule this$0, String str, int i) {
        this.this$0 = this$0;
        this.val$message = str;
        this.val$duration = i;
    }

    public void run() {
        Toast.makeText(ToastModule.access$000(this.this$0), this.val$message, this.val$duration).show();
    }
}
