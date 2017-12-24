package com.facebook.react.modules.storage;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;

class AsyncStorageModule$4 extends GuardedAsyncTask<Void, Void> {
    final /* synthetic */ AsyncStorageModule this$0;
    final /* synthetic */ Callback val$callback;
    final /* synthetic */ ReadableArray val$keyValueArray;

    AsyncStorageModule$4(AsyncStorageModule this$0, ReactContext reactContext, Callback callback, ReadableArray readableArray) {
        this.this$0 = this$0;
        this.val$callback = callback;
        this.val$keyValueArray = readableArray;
        super(reactContext);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void doInBackgroundGuarded(java.lang.Void... r11) {
        /*
        r10 = this;
        r9 = 1;
        r8 = 0;
        r7 = 0;
        r3 = r10.this$0;
        r3 = com.facebook.react.modules.storage.AsyncStorageModule.access$000(r3);
        if (r3 != 0) goto L_0x0019;
    L_0x000b:
        r3 = r10.val$callback;
        r4 = new java.lang.Object[r9];
        r5 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getDBError(r7);
        r4[r8] = r5;
        r3.invoke(r4);
    L_0x0018:
        return;
    L_0x0019:
        r1 = 0;
        r3 = r10.this$0;	 Catch:{ Exception -> 0x0162 }
        r3 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r3);	 Catch:{ Exception -> 0x0162 }
        r3 = r3.get();	 Catch:{ Exception -> 0x0162 }
        r3.beginTransaction();	 Catch:{ Exception -> 0x0162 }
        r2 = 0;
    L_0x0028:
        r3 = r10.val$keyValueArray;	 Catch:{ Exception -> 0x0162 }
        r3 = r3.size();	 Catch:{ Exception -> 0x0162 }
        if (r2 >= r3) goto L_0x0126;
    L_0x0030:
        r3 = r10.val$keyValueArray;	 Catch:{ Exception -> 0x0162 }
        r3 = r3.getArray(r2);	 Catch:{ Exception -> 0x0162 }
        r3 = r3.size();	 Catch:{ Exception -> 0x0162 }
        r4 = 2;
        if (r3 == r4) goto L_0x0065;
    L_0x003d:
        r3 = 0;
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getInvalidValueError(r3);	 Catch:{ Exception -> 0x0162 }
        r3 = r10.this$0;	 Catch:{ Exception -> 0x0050 }
        r3 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r3);	 Catch:{ Exception -> 0x0050 }
        r3 = r3.get();	 Catch:{ Exception -> 0x0050 }
        r3.endTransaction();	 Catch:{ Exception -> 0x0050 }
        goto L_0x0018;
    L_0x0050:
        r0 = move-exception;
        r3 = "React";
        r4 = r0.getMessage();
        com.facebook.common.logging.FLog.m152w(r3, r4, r0);
        if (r1 != 0) goto L_0x0018;
    L_0x005c:
        r3 = r0.getMessage();
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r7, r3);
        goto L_0x0018;
    L_0x0065:
        r3 = r10.val$keyValueArray;	 Catch:{ Exception -> 0x0162 }
        r3 = r3.getArray(r2);	 Catch:{ Exception -> 0x0162 }
        r4 = 0;
        r3 = r3.getString(r4);	 Catch:{ Exception -> 0x0162 }
        if (r3 != 0) goto L_0x009b;
    L_0x0072:
        r3 = 0;
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getInvalidKeyError(r3);	 Catch:{ Exception -> 0x0162 }
        r3 = r10.this$0;	 Catch:{ Exception -> 0x0085 }
        r3 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r3);	 Catch:{ Exception -> 0x0085 }
        r3 = r3.get();	 Catch:{ Exception -> 0x0085 }
        r3.endTransaction();	 Catch:{ Exception -> 0x0085 }
        goto L_0x0018;
    L_0x0085:
        r0 = move-exception;
        r3 = "React";
        r4 = r0.getMessage();
        com.facebook.common.logging.FLog.m152w(r3, r4, r0);
        if (r1 != 0) goto L_0x0018;
    L_0x0091:
        r3 = r0.getMessage();
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r7, r3);
        goto L_0x0018;
    L_0x009b:
        r3 = r10.val$keyValueArray;	 Catch:{ Exception -> 0x0162 }
        r3 = r3.getArray(r2);	 Catch:{ Exception -> 0x0162 }
        r4 = 1;
        r3 = r3.getString(r4);	 Catch:{ Exception -> 0x0162 }
        if (r3 != 0) goto L_0x00d2;
    L_0x00a8:
        r3 = 0;
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getInvalidValueError(r3);	 Catch:{ Exception -> 0x0162 }
        r3 = r10.this$0;	 Catch:{ Exception -> 0x00bc }
        r3 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r3);	 Catch:{ Exception -> 0x00bc }
        r3 = r3.get();	 Catch:{ Exception -> 0x00bc }
        r3.endTransaction();	 Catch:{ Exception -> 0x00bc }
        goto L_0x0018;
    L_0x00bc:
        r0 = move-exception;
        r3 = "React";
        r4 = r0.getMessage();
        com.facebook.common.logging.FLog.m152w(r3, r4, r0);
        if (r1 != 0) goto L_0x0018;
    L_0x00c8:
        r3 = r0.getMessage();
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r7, r3);
        goto L_0x0018;
    L_0x00d2:
        r3 = r10.this$0;	 Catch:{ Exception -> 0x0162 }
        r3 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r3);	 Catch:{ Exception -> 0x0162 }
        r3 = r3.get();	 Catch:{ Exception -> 0x0162 }
        r4 = r10.val$keyValueArray;	 Catch:{ Exception -> 0x0162 }
        r4 = r4.getArray(r2);	 Catch:{ Exception -> 0x0162 }
        r5 = 0;
        r4 = r4.getString(r5);	 Catch:{ Exception -> 0x0162 }
        r5 = r10.val$keyValueArray;	 Catch:{ Exception -> 0x0162 }
        r5 = r5.getArray(r2);	 Catch:{ Exception -> 0x0162 }
        r6 = 1;
        r5 = r5.getString(r6);	 Catch:{ Exception -> 0x0162 }
        r3 = com.facebook.react.modules.storage.AsyncLocalStorageUtil.mergeImpl(r3, r4, r5);	 Catch:{ Exception -> 0x0162 }
        if (r3 != 0) goto L_0x0122;
    L_0x00f8:
        r3 = 0;
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getDBError(r3);	 Catch:{ Exception -> 0x0162 }
        r3 = r10.this$0;	 Catch:{ Exception -> 0x010c }
        r3 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r3);	 Catch:{ Exception -> 0x010c }
        r3 = r3.get();	 Catch:{ Exception -> 0x010c }
        r3.endTransaction();	 Catch:{ Exception -> 0x010c }
        goto L_0x0018;
    L_0x010c:
        r0 = move-exception;
        r3 = "React";
        r4 = r0.getMessage();
        com.facebook.common.logging.FLog.m152w(r3, r4, r0);
        if (r1 != 0) goto L_0x0018;
    L_0x0118:
        r3 = r0.getMessage();
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r7, r3);
        goto L_0x0018;
    L_0x0122:
        r2 = r2 + 1;
        goto L_0x0028;
    L_0x0126:
        r3 = r10.this$0;	 Catch:{ Exception -> 0x0162 }
        r3 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r3);	 Catch:{ Exception -> 0x0162 }
        r3 = r3.get();	 Catch:{ Exception -> 0x0162 }
        r3.setTransactionSuccessful();	 Catch:{ Exception -> 0x0162 }
        r3 = r10.this$0;	 Catch:{ Exception -> 0x014d }
        r3 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r3);	 Catch:{ Exception -> 0x014d }
        r3 = r3.get();	 Catch:{ Exception -> 0x014d }
        r3.endTransaction();	 Catch:{ Exception -> 0x014d }
    L_0x0140:
        if (r1 == 0) goto L_0x01bc;
    L_0x0142:
        r3 = r10.val$callback;
        r4 = new java.lang.Object[r9];
        r4[r8] = r1;
        r3.invoke(r4);
        goto L_0x0018;
    L_0x014d:
        r0 = move-exception;
        r3 = "React";
        r4 = r0.getMessage();
        com.facebook.common.logging.FLog.m152w(r3, r4, r0);
        if (r1 != 0) goto L_0x0140;
    L_0x0159:
        r3 = r0.getMessage();
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r7, r3);
        goto L_0x0140;
    L_0x0162:
        r0 = move-exception;
        r3 = "React";
        r4 = r0.getMessage();	 Catch:{ all -> 0x0198 }
        com.facebook.common.logging.FLog.m152w(r3, r4, r0);	 Catch:{ all -> 0x0198 }
        r3 = 0;
        r4 = r0.getMessage();	 Catch:{ all -> 0x0198 }
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r3, r4);	 Catch:{ all -> 0x0198 }
        r3 = r10.this$0;	 Catch:{ Exception -> 0x0183 }
        r3 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r3);	 Catch:{ Exception -> 0x0183 }
        r3 = r3.get();	 Catch:{ Exception -> 0x0183 }
        r3.endTransaction();	 Catch:{ Exception -> 0x0183 }
        goto L_0x0140;
    L_0x0183:
        r0 = move-exception;
        r3 = "React";
        r4 = r0.getMessage();
        com.facebook.common.logging.FLog.m152w(r3, r4, r0);
        if (r1 != 0) goto L_0x0140;
    L_0x018f:
        r3 = r0.getMessage();
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r7, r3);
        goto L_0x0140;
    L_0x0198:
        r3 = move-exception;
        r4 = r10.this$0;	 Catch:{ Exception -> 0x01a7 }
        r4 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r4);	 Catch:{ Exception -> 0x01a7 }
        r4 = r4.get();	 Catch:{ Exception -> 0x01a7 }
        r4.endTransaction();	 Catch:{ Exception -> 0x01a7 }
    L_0x01a6:
        throw r3;
    L_0x01a7:
        r0 = move-exception;
        r4 = "React";
        r5 = r0.getMessage();
        com.facebook.common.logging.FLog.m152w(r4, r5, r0);
        if (r1 != 0) goto L_0x01a6;
    L_0x01b3:
        r4 = r0.getMessage();
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r7, r4);
        goto L_0x01a6;
    L_0x01bc:
        r3 = r10.val$callback;
        r4 = new java.lang.Object[r8];
        r3.invoke(r4);
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.storage.AsyncStorageModule$4.doInBackgroundGuarded(java.lang.Void[]):void");
    }
}
