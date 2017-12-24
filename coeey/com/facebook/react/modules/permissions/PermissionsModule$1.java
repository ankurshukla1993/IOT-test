package com.facebook.react.modules.permissions;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;

class PermissionsModule$1 implements Callback {
    final /* synthetic */ PermissionsModule this$0;
    final /* synthetic */ String val$permission;
    final /* synthetic */ Promise val$promise;

    PermissionsModule$1(PermissionsModule this$0, Promise promise, String str) {
        this.this$0 = this$0;
        this.val$promise = promise;
        this.val$permission = str;
    }

    public void invoke(Object... args) {
        if (((int[]) args[0])[0] == 0) {
            this.val$promise.resolve("granted");
        } else if (args[1].shouldShowRequestPermissionRationale(this.val$permission)) {
            this.val$promise.resolve("denied");
        } else {
            this.val$promise.resolve("never_ask_again");
        }
    }
}
