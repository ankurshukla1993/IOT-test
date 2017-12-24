package com.facebook.react.modules.storage;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;

class AsyncStorageModule$2 extends GuardedAsyncTask<Void, Void> {
    final /* synthetic */ AsyncStorageModule this$0;
    final /* synthetic */ Callback val$callback;
    final /* synthetic */ ReadableArray val$keyValueArray;

    AsyncStorageModule$2(AsyncStorageModule this$0, ReactContext reactContext, Callback callback, ReadableArray readableArray) {
        this.this$0 = this$0;
        this.val$callback = callback;
        this.val$keyValueArray = readableArray;
        super(reactContext);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void doInBackgroundGuarded(java.lang.Void... r13) {
        /*
        r12 = this;
        r11 = 2;
        r10 = 1;
        r9 = 0;
        r8 = 0;
        r5 = r12.this$0;
        r5 = com.facebook.react.modules.storage.AsyncStorageModule.access$000(r5);
        if (r5 != 0) goto L_0x001a;
    L_0x000c:
        r5 = r12.val$callback;
        r6 = new java.lang.Object[r10];
        r7 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getDBError(r8);
        r6[r9] = r7;
        r5.invoke(r6);
    L_0x0019:
        return;
    L_0x001a:
        r3 = "INSERT OR REPLACE INTO catalystLocalStorage VALUES (?, ?);";
        r5 = r12.this$0;
        r5 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r5);
        r5 = r5.get();
        r4 = r5.compileStatement(r3);
        r1 = 0;
        r5 = r12.this$0;	 Catch:{ Exception -> 0x0146 }
        r5 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r5);	 Catch:{ Exception -> 0x0146 }
        r5 = r5.get();	 Catch:{ Exception -> 0x0146 }
        r5.beginTransaction();	 Catch:{ Exception -> 0x0146 }
        r2 = 0;
    L_0x0039:
        r5 = r12.val$keyValueArray;	 Catch:{ Exception -> 0x0146 }
        r5 = r5.size();	 Catch:{ Exception -> 0x0146 }
        if (r2 >= r5) goto L_0x010a;
    L_0x0041:
        r5 = r12.val$keyValueArray;	 Catch:{ Exception -> 0x0146 }
        r5 = r5.getArray(r2);	 Catch:{ Exception -> 0x0146 }
        r5 = r5.size();	 Catch:{ Exception -> 0x0146 }
        if (r5 == r11) goto L_0x0075;
    L_0x004d:
        r5 = 0;
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getInvalidValueError(r5);	 Catch:{ Exception -> 0x0146 }
        r5 = r12.this$0;	 Catch:{ Exception -> 0x0060 }
        r5 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r5);	 Catch:{ Exception -> 0x0060 }
        r5 = r5.get();	 Catch:{ Exception -> 0x0060 }
        r5.endTransaction();	 Catch:{ Exception -> 0x0060 }
        goto L_0x0019;
    L_0x0060:
        r0 = move-exception;
        r5 = "React";
        r6 = r0.getMessage();
        com.facebook.common.logging.FLog.m152w(r5, r6, r0);
        if (r1 != 0) goto L_0x0019;
    L_0x006c:
        r5 = r0.getMessage();
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r8, r5);
        goto L_0x0019;
    L_0x0075:
        r5 = r12.val$keyValueArray;	 Catch:{ Exception -> 0x0146 }
        r5 = r5.getArray(r2);	 Catch:{ Exception -> 0x0146 }
        r6 = 0;
        r5 = r5.getString(r6);	 Catch:{ Exception -> 0x0146 }
        if (r5 != 0) goto L_0x00ab;
    L_0x0082:
        r5 = 0;
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getInvalidKeyError(r5);	 Catch:{ Exception -> 0x0146 }
        r5 = r12.this$0;	 Catch:{ Exception -> 0x0095 }
        r5 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r5);	 Catch:{ Exception -> 0x0095 }
        r5 = r5.get();	 Catch:{ Exception -> 0x0095 }
        r5.endTransaction();	 Catch:{ Exception -> 0x0095 }
        goto L_0x0019;
    L_0x0095:
        r0 = move-exception;
        r5 = "React";
        r6 = r0.getMessage();
        com.facebook.common.logging.FLog.m152w(r5, r6, r0);
        if (r1 != 0) goto L_0x0019;
    L_0x00a1:
        r5 = r0.getMessage();
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r8, r5);
        goto L_0x0019;
    L_0x00ab:
        r5 = r12.val$keyValueArray;	 Catch:{ Exception -> 0x0146 }
        r5 = r5.getArray(r2);	 Catch:{ Exception -> 0x0146 }
        r6 = 1;
        r5 = r5.getString(r6);	 Catch:{ Exception -> 0x0146 }
        if (r5 != 0) goto L_0x00e2;
    L_0x00b8:
        r5 = 0;
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getInvalidValueError(r5);	 Catch:{ Exception -> 0x0146 }
        r5 = r12.this$0;	 Catch:{ Exception -> 0x00cc }
        r5 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r5);	 Catch:{ Exception -> 0x00cc }
        r5 = r5.get();	 Catch:{ Exception -> 0x00cc }
        r5.endTransaction();	 Catch:{ Exception -> 0x00cc }
        goto L_0x0019;
    L_0x00cc:
        r0 = move-exception;
        r5 = "React";
        r6 = r0.getMessage();
        com.facebook.common.logging.FLog.m152w(r5, r6, r0);
        if (r1 != 0) goto L_0x0019;
    L_0x00d8:
        r5 = r0.getMessage();
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r8, r5);
        goto L_0x0019;
    L_0x00e2:
        r4.clearBindings();	 Catch:{ Exception -> 0x0146 }
        r5 = 1;
        r6 = r12.val$keyValueArray;	 Catch:{ Exception -> 0x0146 }
        r6 = r6.getArray(r2);	 Catch:{ Exception -> 0x0146 }
        r7 = 0;
        r6 = r6.getString(r7);	 Catch:{ Exception -> 0x0146 }
        r4.bindString(r5, r6);	 Catch:{ Exception -> 0x0146 }
        r5 = 2;
        r6 = r12.val$keyValueArray;	 Catch:{ Exception -> 0x0146 }
        r6 = r6.getArray(r2);	 Catch:{ Exception -> 0x0146 }
        r7 = 1;
        r6 = r6.getString(r7);	 Catch:{ Exception -> 0x0146 }
        r4.bindString(r5, r6);	 Catch:{ Exception -> 0x0146 }
        r4.execute();	 Catch:{ Exception -> 0x0146 }
        r2 = r2 + 1;
        goto L_0x0039;
    L_0x010a:
        r5 = r12.this$0;	 Catch:{ Exception -> 0x0146 }
        r5 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r5);	 Catch:{ Exception -> 0x0146 }
        r5 = r5.get();	 Catch:{ Exception -> 0x0146 }
        r5.setTransactionSuccessful();	 Catch:{ Exception -> 0x0146 }
        r5 = r12.this$0;	 Catch:{ Exception -> 0x0131 }
        r5 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r5);	 Catch:{ Exception -> 0x0131 }
        r5 = r5.get();	 Catch:{ Exception -> 0x0131 }
        r5.endTransaction();	 Catch:{ Exception -> 0x0131 }
    L_0x0124:
        if (r1 == 0) goto L_0x01a0;
    L_0x0126:
        r5 = r12.val$callback;
        r6 = new java.lang.Object[r10];
        r6[r9] = r1;
        r5.invoke(r6);
        goto L_0x0019;
    L_0x0131:
        r0 = move-exception;
        r5 = "React";
        r6 = r0.getMessage();
        com.facebook.common.logging.FLog.m152w(r5, r6, r0);
        if (r1 != 0) goto L_0x0124;
    L_0x013d:
        r5 = r0.getMessage();
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r8, r5);
        goto L_0x0124;
    L_0x0146:
        r0 = move-exception;
        r5 = "React";
        r6 = r0.getMessage();	 Catch:{ all -> 0x017c }
        com.facebook.common.logging.FLog.m152w(r5, r6, r0);	 Catch:{ all -> 0x017c }
        r5 = 0;
        r6 = r0.getMessage();	 Catch:{ all -> 0x017c }
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r5, r6);	 Catch:{ all -> 0x017c }
        r5 = r12.this$0;	 Catch:{ Exception -> 0x0167 }
        r5 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r5);	 Catch:{ Exception -> 0x0167 }
        r5 = r5.get();	 Catch:{ Exception -> 0x0167 }
        r5.endTransaction();	 Catch:{ Exception -> 0x0167 }
        goto L_0x0124;
    L_0x0167:
        r0 = move-exception;
        r5 = "React";
        r6 = r0.getMessage();
        com.facebook.common.logging.FLog.m152w(r5, r6, r0);
        if (r1 != 0) goto L_0x0124;
    L_0x0173:
        r5 = r0.getMessage();
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r8, r5);
        goto L_0x0124;
    L_0x017c:
        r5 = move-exception;
        r6 = r12.this$0;	 Catch:{ Exception -> 0x018b }
        r6 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r6);	 Catch:{ Exception -> 0x018b }
        r6 = r6.get();	 Catch:{ Exception -> 0x018b }
        r6.endTransaction();	 Catch:{ Exception -> 0x018b }
    L_0x018a:
        throw r5;
    L_0x018b:
        r0 = move-exception;
        r6 = "React";
        r7 = r0.getMessage();
        com.facebook.common.logging.FLog.m152w(r6, r7, r0);
        if (r1 != 0) goto L_0x018a;
    L_0x0197:
        r6 = r0.getMessage();
        r1 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r8, r6);
        goto L_0x018a;
    L_0x01a0:
        r5 = r12.val$callback;
        r6 = new java.lang.Object[r9];
        r5.invoke(r6);
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.storage.AsyncStorageModule$2.doInBackgroundGuarded(java.lang.Void[]):void");
    }
}
