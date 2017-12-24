package com.facebook.react.modules.toast;

import android.widget.Toast;

class ToastModule$2 implements Runnable {
    final /* synthetic */ ToastModule this$0;
    final /* synthetic */ int val$duration;
    final /* synthetic */ int val$gravity;
    final /* synthetic */ String val$message;

    ToastModule$2(ToastModule this$0, String str, int i, int i2) {
        this.this$0 = this$0;
        this.val$message = str;
        this.val$duration = i;
        this.val$gravity = i2;
    }

    public void run() {
        Toast toast = Toast.makeText(ToastModule.access$100(this.this$0), this.val$message, this.val$duration);
        toast.setGravity(this.val$gravity, 0, 0);
        toast.show();
    }
}
