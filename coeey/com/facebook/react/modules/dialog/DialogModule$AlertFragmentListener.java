package com.facebook.react.modules.dialog;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import com.facebook.react.bridge.Callback;

class DialogModule$AlertFragmentListener implements OnClickListener, OnDismissListener {
    private final Callback mCallback;
    private boolean mCallbackConsumed = false;
    final /* synthetic */ DialogModule this$0;

    public DialogModule$AlertFragmentListener(DialogModule this$0, Callback callback) {
        this.this$0 = this$0;
        this.mCallback = callback;
    }

    public void onClick(DialogInterface dialog, int which) {
        if (!this.mCallbackConsumed && DialogModule.access$000(this.this$0).hasActiveCatalystInstance()) {
            this.mCallback.invoke("buttonClicked", Integer.valueOf(which));
            this.mCallbackConsumed = true;
        }
    }

    public void onDismiss(DialogInterface dialog) {
        if (!this.mCallbackConsumed && DialogModule.access$100(this.this$0).hasActiveCatalystInstance()) {
            this.mCallback.invoke(UIManagerModuleConstants.ACTION_DISMISSED);
            this.mCallbackConsumed = true;
        }
    }
}
