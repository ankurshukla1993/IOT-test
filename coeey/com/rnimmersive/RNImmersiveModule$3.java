package com.rnimmersive;

import android.annotation.TargetApi;
import android.app.Activity;
import android.view.View.OnSystemUiVisibilityChangeListener;

class RNImmersiveModule$3 implements Runnable {
    final /* synthetic */ RNImmersiveModule this$0;
    final /* synthetic */ Activity val$activity;

    class C23081 implements OnSystemUiVisibilityChangeListener {
        C23081() {
        }

        public void onSystemUiVisibilityChange(int visibility) {
            if (((visibility & 5894) != 0) != RNImmersiveModule.access$000(RNImmersiveModule$3.this.this$0)) {
                RNImmersiveModule$3.this.this$0.emitImmersiveStateChangeEvent();
            }
        }
    }

    RNImmersiveModule$3(RNImmersiveModule this$0, Activity activity) {
        this.this$0 = this$0;
        this.val$activity = activity;
    }

    @TargetApi(19)
    public void run() {
        this.val$activity.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new C23081());
    }
}
