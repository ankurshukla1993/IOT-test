package com.facebook.react.modules.storage;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;

class AsyncStorageModule$5 extends GuardedAsyncTask<Void, Void> {
    final /* synthetic */ AsyncStorageModule this$0;
    final /* synthetic */ Callback val$callback;

    AsyncStorageModule$5(AsyncStorageModule this$0, ReactContext reactContext, Callback callback) {
        this.this$0 = this$0;
        this.val$callback = callback;
        super(reactContext);
    }

    protected void doInBackgroundGuarded(Void... params) {
        if (AsyncStorageModule.access$100(this.this$0).ensureDatabase()) {
            try {
                AsyncStorageModule.access$100(this.this$0).clear();
                this.val$callback.invoke(new Object[0]);
                return;
            } catch (Throwable e) {
                FLog.m152w(ReactConstants.TAG, e.getMessage(), e);
                this.val$callback.invoke(AsyncStorageErrorUtil.getError(null, e.getMessage()));
                return;
            }
        }
        this.val$callback.invoke(AsyncStorageErrorUtil.getDBError(null));
    }
}
