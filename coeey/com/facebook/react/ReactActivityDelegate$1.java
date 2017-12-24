package com.facebook.react;

import com.facebook.react.bridge.Callback;

class ReactActivityDelegate$1 implements Callback {
    final /* synthetic */ ReactActivityDelegate this$0;
    final /* synthetic */ int[] val$grantResults;
    final /* synthetic */ String[] val$permissions;
    final /* synthetic */ int val$requestCode;

    ReactActivityDelegate$1(ReactActivityDelegate this$0, int i, String[] strArr, int[] iArr) {
        this.this$0 = this$0;
        this.val$requestCode = i;
        this.val$permissions = strArr;
        this.val$grantResults = iArr;
    }

    public void invoke(Object... args) {
        if (ReactActivityDelegate.access$000(this.this$0) != null && ReactActivityDelegate.access$000(this.this$0).onRequestPermissionsResult(this.val$requestCode, this.val$permissions, this.val$grantResults)) {
            ReactActivityDelegate.access$002(this.this$0, null);
        }
    }
}
