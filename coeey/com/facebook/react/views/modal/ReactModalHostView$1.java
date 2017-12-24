package com.facebook.react.views.modal;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;

class ReactModalHostView$1 implements OnKeyListener {
    final /* synthetic */ ReactModalHostView this$0;

    ReactModalHostView$1(ReactModalHostView this$0) {
        this.this$0 = this$0;
    }

    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (event.getAction() == 1) {
            if (keyCode == 4) {
                Assertions.assertNotNull(ReactModalHostView.access$000(this.this$0), "setOnRequestCloseListener must be called by the manager");
                ReactModalHostView.access$000(this.this$0).onRequestClose(dialog);
                return true;
            }
            Activity currentActivity = ((ReactContext) this.this$0.getContext()).getCurrentActivity();
            if (currentActivity != null) {
                return currentActivity.onKeyUp(keyCode, event);
            }
        }
        return false;
    }
}
