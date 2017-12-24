package com.facebook.react.modules.permissions;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.PermissionAwareActivity;
import java.util.ArrayList;

class PermissionsModule$2 implements Callback {
    final /* synthetic */ PermissionsModule this$0;
    final /* synthetic */ WritableMap val$grantedPermissions;
    final /* synthetic */ ArrayList val$permissionsToCheck;
    final /* synthetic */ Promise val$promise;

    PermissionsModule$2(PermissionsModule this$0, ArrayList arrayList, WritableMap writableMap, Promise promise) {
        this.this$0 = this$0;
        this.val$permissionsToCheck = arrayList;
        this.val$grantedPermissions = writableMap;
        this.val$promise = promise;
    }

    public void invoke(Object... args) {
        int[] results = (int[]) args[0];
        PermissionAwareActivity activity = args[1];
        for (int j = 0; j < this.val$permissionsToCheck.size(); j++) {
            String permission = (String) this.val$permissionsToCheck.get(j);
            if (results[j] == 0) {
                this.val$grantedPermissions.putString(permission, "granted");
            } else if (activity.shouldShowRequestPermissionRationale(permission)) {
                this.val$grantedPermissions.putString(permission, "denied");
            } else {
                this.val$grantedPermissions.putString(permission, "never_ask_again");
            }
        }
        this.val$promise.resolve(this.val$grantedPermissions);
    }
}
