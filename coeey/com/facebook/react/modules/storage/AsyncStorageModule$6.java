package com.facebook.react.modules.storage;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactContext;

class AsyncStorageModule$6 extends GuardedAsyncTask<Void, Void> {
    final /* synthetic */ AsyncStorageModule this$0;
    final /* synthetic */ Callback val$callback;

    AsyncStorageModule$6(AsyncStorageModule this$0, ReactContext reactContext, Callback callback) {
        this.this$0 = this$0;
        this.val$callback = callback;
        super(reactContext);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void doInBackgroundGuarded(java.lang.Void... r15) {
        /*
        r14 = this;
        r13 = 2;
        r12 = 1;
        r11 = 0;
        r3 = 0;
        r0 = r14.this$0;
        r0 = com.facebook.react.modules.storage.AsyncStorageModule.access$000(r0);
        if (r0 != 0) goto L_0x001c;
    L_0x000c:
        r0 = r14.val$callback;
        r1 = new java.lang.Object[r13];
        r4 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getDBError(r3);
        r1[r11] = r4;
        r1[r12] = r3;
        r0.invoke(r1);
    L_0x001b:
        return;
    L_0x001c:
        r9 = com.facebook.react.bridge.Arguments.createArray();
        r2 = new java.lang.String[r12];
        r0 = "key";
        r2[r11] = r0;
        r0 = r14.this$0;
        r0 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r0);
        r0 = r0.get();
        r1 = "catalystLocalStorage";
        r4 = r3;
        r5 = r3;
        r6 = r3;
        r7 = r3;
        r8 = r0.query(r1, r2, r3, r4, r5, r6, r7);
        r0 = r8.moveToFirst();	 Catch:{ Exception -> 0x005d }
        if (r0 == 0) goto L_0x004e;
    L_0x0040:
        r0 = 0;
        r0 = r8.getString(r0);	 Catch:{ Exception -> 0x005d }
        r9.pushString(r0);	 Catch:{ Exception -> 0x005d }
        r0 = r8.moveToNext();	 Catch:{ Exception -> 0x005d }
        if (r0 != 0) goto L_0x0040;
    L_0x004e:
        r8.close();
        r0 = r14.val$callback;
        r1 = new java.lang.Object[r13];
        r1[r11] = r3;
        r1[r12] = r9;
        r0.invoke(r1);
        goto L_0x001b;
    L_0x005d:
        r10 = move-exception;
        r0 = "React";
        r1 = r10.getMessage();	 Catch:{ all -> 0x0083 }
        com.facebook.common.logging.FLog.m152w(r0, r1, r10);	 Catch:{ all -> 0x0083 }
        r0 = r14.val$callback;	 Catch:{ all -> 0x0083 }
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0083 }
        r3 = 0;
        r4 = 0;
        r5 = r10.getMessage();	 Catch:{ all -> 0x0083 }
        r4 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r4, r5);	 Catch:{ all -> 0x0083 }
        r1[r3] = r4;	 Catch:{ all -> 0x0083 }
        r3 = 1;
        r4 = 0;
        r1[r3] = r4;	 Catch:{ all -> 0x0083 }
        r0.invoke(r1);	 Catch:{ all -> 0x0083 }
        r8.close();
        goto L_0x001b;
    L_0x0083:
        r0 = move-exception;
        r8.close();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.storage.AsyncStorageModule$6.doInBackgroundGuarded(java.lang.Void[]):void");
    }
}
