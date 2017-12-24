package com.facebook.react.modules.storage;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;

class AsyncStorageModule$3 extends GuardedAsyncTask<Void, Void> {
    final /* synthetic */ AsyncStorageModule this$0;
    final /* synthetic */ Callback val$callback;
    final /* synthetic */ ReadableArray val$keys;

    AsyncStorageModule$3(AsyncStorageModule this$0, ReactContext reactContext, Callback callback, ReadableArray readableArray) {
        this.this$0 = this$0;
        this.val$callback = callback;
        this.val$keys = readableArray;
        super(reactContext);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void doInBackgroundGuarded(java.lang.Void... r12) {
        /*
        r11 = this;
        r10 = 1;
        r9 = 0;
        r8 = 0;
        r4 = r11.this$0;
        r4 = com.facebook.react.modules.storage.AsyncStorageModule.access$000(r4);
        if (r4 != 0) goto L_0x0019;
    L_0x000b:
        r4 = r11.val$callback;
        r5 = new java.lang.Object[r10];
        r6 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getDBError(r8);
        r5[r9] = r6;
        r4.invoke(r5);
    L_0x0018:
        return;
    L_0x0019:
        r1 = 0;
        r4 = r11.this$0;	 Catch:{ Exception -> 0x0094 }
        r4 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r4);	 Catch:{ Exception -> 0x0094 }
        r4 = r4.get();	 Catch:{ Exception -> 0x0094 }
        r4.beginTransaction();	 Catch:{ Exception -> 0x0094 }
        r3 = 0;
    L_0x0028:
        r4 = r11.val$keys;	 Catch:{ Exception -> 0x0094 }
        r4 = r4.size();	 Catch:{ Exception -> 0x0094 }
        if (r3 >= r4) goto L_0x0059;
    L_0x0030:
        r4 = r11.val$keys;	 Catch:{ Exception -> 0x0094 }
        r4 = r4.size();	 Catch:{ Exception -> 0x0094 }
        r4 = r4 - r3;
        r5 = 999; // 0x3e7 float:1.4E-42 double:4.936E-321;
        r2 = java.lang.Math.min(r4, r5);	 Catch:{ Exception -> 0x0094 }
        r4 = r11.this$0;	 Catch:{ Exception -> 0x0094 }
        r4 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r4);	 Catch:{ Exception -> 0x0094 }
        r4 = r4.get();	 Catch:{ Exception -> 0x0094 }
        r5 = "catalystLocalStorage";
        r6 = com.facebook.react.modules.storage.AsyncLocalStorageUtil.buildKeySelection(r2);	 Catch:{ Exception -> 0x0094 }
        r7 = r11.val$keys;	 Catch:{ Exception -> 0x0094 }
        r7 = com.facebook.react.modules.storage.AsyncLocalStorageUtil.buildKeySelectionArgs(r7, r3, r2);	 Catch:{ Exception -> 0x0094 }
        r4.delete(r5, r6, r7);	 Catch:{ Exception -> 0x0094 }
        r3 = r3 + 999;
        goto L_0x0028;
    L_0x0059:
        r4 = r11.this$0;	 Catch:{ Exception -> 0x0094 }
        r4 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r4);	 Catch:{ Exception -> 0x0094 }
        r4 = r4.get();	 Catch:{ Exception -> 0x0094 }
        r4.setTransactionSuccessful();	 Catch:{ Exception -> 0x0094 }
        r4 = r11.this$0;	 Catch:{ Exception -> 0x007f }
        r4 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r4);	 Catch:{ Exception -> 0x007f }
        r4 = r4.get();	 Catch:{ Exception -> 0x007f }
        r4.endTransaction();	 Catch:{ Exception -> 0x007f }
    L_0x0073:
        if (r1 == 0) goto L_0x00ee;
    L_0x0075:
        r4 = r11.val$callback;
        r5 = new java.lang.Object[r10];
        r5[r9] = r1;
        r4.invoke(r5);
        goto L_0x0018;
    L_0x007f:
        r0 = move-exception;
        r4 = "React";
        r5 = r0.getMessage();
        com.facebook.common.logging.FLog.m152w(r4, r5, r0);
        if (r1 != 0) goto L_0x0073;
    L_0x008b:
        r4 = r0.getMessage();
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r8, r4);
        goto L_0x0073;
    L_0x0094:
        r0 = move-exception;
        r4 = "React";
        r5 = r0.getMessage();	 Catch:{ all -> 0x00ca }
        com.facebook.common.logging.FLog.m152w(r4, r5, r0);	 Catch:{ all -> 0x00ca }
        r4 = 0;
        r5 = r0.getMessage();	 Catch:{ all -> 0x00ca }
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r4, r5);	 Catch:{ all -> 0x00ca }
        r4 = r11.this$0;	 Catch:{ Exception -> 0x00b5 }
        r4 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r4);	 Catch:{ Exception -> 0x00b5 }
        r4 = r4.get();	 Catch:{ Exception -> 0x00b5 }
        r4.endTransaction();	 Catch:{ Exception -> 0x00b5 }
        goto L_0x0073;
    L_0x00b5:
        r0 = move-exception;
        r4 = "React";
        r5 = r0.getMessage();
        com.facebook.common.logging.FLog.m152w(r4, r5, r0);
        if (r1 != 0) goto L_0x0073;
    L_0x00c1:
        r4 = r0.getMessage();
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r8, r4);
        goto L_0x0073;
    L_0x00ca:
        r4 = move-exception;
        r5 = r11.this$0;	 Catch:{ Exception -> 0x00d9 }
        r5 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r5);	 Catch:{ Exception -> 0x00d9 }
        r5 = r5.get();	 Catch:{ Exception -> 0x00d9 }
        r5.endTransaction();	 Catch:{ Exception -> 0x00d9 }
    L_0x00d8:
        throw r4;
    L_0x00d9:
        r0 = move-exception;
        r5 = "React";
        r6 = r0.getMessage();
        com.facebook.common.logging.FLog.m152w(r5, r6, r0);
        if (r1 != 0) goto L_0x00d8;
    L_0x00e5:
        r5 = r0.getMessage();
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r8, r5);
        goto L_0x00d8;
    L_0x00ee:
        r4 = r11.val$callback;
        r5 = new java.lang.Object[r9];
        r4.invoke(r5);
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.storage.AsyncStorageModule$3.doInBackgroundGuarded(java.lang.Void[]):void");
    }
}
