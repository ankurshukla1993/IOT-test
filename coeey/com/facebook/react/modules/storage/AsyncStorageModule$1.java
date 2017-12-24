package com.facebook.react.modules.storage;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;

class AsyncStorageModule$1 extends GuardedAsyncTask<Void, Void> {
    final /* synthetic */ AsyncStorageModule this$0;
    final /* synthetic */ Callback val$callback;
    final /* synthetic */ ReadableArray val$keys;

    AsyncStorageModule$1(AsyncStorageModule this$0, ReactContext reactContext, Callback callback, ReadableArray readableArray) {
        this.this$0 = this$0;
        this.val$callback = callback;
        this.val$keys = readableArray;
        super(reactContext);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void doInBackgroundGuarded(java.lang.Void... r19) {
        /*
        r18 = this;
        r0 = r18;
        r1 = r0.this$0;
        r1 = com.facebook.react.modules.storage.AsyncStorageModule.access$000(r1);
        if (r1 != 0) goto L_0x0021;
    L_0x000a:
        r0 = r18;
        r1 = r0.val$callback;
        r2 = 2;
        r2 = new java.lang.Object[r2];
        r4 = 0;
        r5 = 0;
        r5 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getDBError(r5);
        r2[r4] = r5;
        r4 = 1;
        r5 = 0;
        r2[r4] = r5;
        r1.invoke(r2);
    L_0x0020:
        return;
    L_0x0021:
        r1 = 2;
        r3 = new java.lang.String[r1];
        r1 = 0;
        r2 = "key";
        r3[r1] = r2;
        r1 = 1;
        r2 = "value";
        r3[r1] = r2;
        r16 = com.facebook.react.common.SetBuilder.newHashSet();
        r10 = com.facebook.react.bridge.Arguments.createArray();
        r15 = 0;
    L_0x0037:
        r0 = r18;
        r1 = r0.val$keys;
        r1 = r1.size();
        if (r15 >= r1) goto L_0x0124;
    L_0x0041:
        r0 = r18;
        r1 = r0.val$keys;
        r1 = r1.size();
        r1 = r1 - r15;
        r2 = 999; // 0x3e7 float:1.4E-42 double:4.936E-321;
        r13 = java.lang.Math.min(r1, r2);
        r0 = r18;
        r1 = r0.this$0;
        r1 = com.facebook.react.modules.storage.AsyncStorageModule.access$100(r1);
        r1 = r1.get();
        r2 = "catalystLocalStorage";
        r4 = com.facebook.react.modules.storage.AsyncLocalStorageUtil.buildKeySelection(r13);
        r0 = r18;
        r5 = r0.val$keys;
        r5 = com.facebook.react.modules.storage.AsyncLocalStorageUtil.buildKeySelectionArgs(r5, r15, r13);
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = r1.query(r2, r3, r4, r5, r6, r7, r8);
        r16.clear();
        r1 = r9.getCount();	 Catch:{ Exception -> 0x00ef }
        r0 = r18;
        r2 = r0.val$keys;	 Catch:{ Exception -> 0x00ef }
        r2 = r2.size();	 Catch:{ Exception -> 0x00ef }
        if (r1 == r2) goto L_0x0097;
    L_0x0082:
        r14 = r15;
    L_0x0083:
        r1 = r15 + r13;
        if (r14 >= r1) goto L_0x0097;
    L_0x0087:
        r0 = r18;
        r1 = r0.val$keys;	 Catch:{ Exception -> 0x00ef }
        r1 = r1.getString(r14);	 Catch:{ Exception -> 0x00ef }
        r0 = r16;
        r0.add(r1);	 Catch:{ Exception -> 0x00ef }
        r14 = r14 + 1;
        goto L_0x0083;
    L_0x0097:
        r1 = r9.moveToFirst();	 Catch:{ Exception -> 0x00ef }
        if (r1 == 0) goto L_0x00ca;
    L_0x009d:
        r17 = com.facebook.react.bridge.Arguments.createArray();	 Catch:{ Exception -> 0x00ef }
        r1 = 0;
        r1 = r9.getString(r1);	 Catch:{ Exception -> 0x00ef }
        r0 = r17;
        r0.pushString(r1);	 Catch:{ Exception -> 0x00ef }
        r1 = 1;
        r1 = r9.getString(r1);	 Catch:{ Exception -> 0x00ef }
        r0 = r17;
        r0.pushString(r1);	 Catch:{ Exception -> 0x00ef }
        r0 = r17;
        r10.pushArray(r0);	 Catch:{ Exception -> 0x00ef }
        r1 = 0;
        r1 = r9.getString(r1);	 Catch:{ Exception -> 0x00ef }
        r0 = r16;
        r0.remove(r1);	 Catch:{ Exception -> 0x00ef }
        r1 = r9.moveToNext();	 Catch:{ Exception -> 0x00ef }
        if (r1 != 0) goto L_0x009d;
    L_0x00ca:
        r9.close();
        r1 = r16.iterator();
    L_0x00d1:
        r2 = r1.hasNext();
        if (r2 == 0) goto L_0x011d;
    L_0x00d7:
        r12 = r1.next();
        r12 = (java.lang.String) r12;
        r17 = com.facebook.react.bridge.Arguments.createArray();
        r0 = r17;
        r0.pushString(r12);
        r17.pushNull();
        r0 = r17;
        r10.pushArray(r0);
        goto L_0x00d1;
    L_0x00ef:
        r11 = move-exception;
        r1 = "React";
        r2 = r11.getMessage();	 Catch:{ all -> 0x0118 }
        com.facebook.common.logging.FLog.m152w(r1, r2, r11);	 Catch:{ all -> 0x0118 }
        r0 = r18;
        r1 = r0.val$callback;	 Catch:{ all -> 0x0118 }
        r2 = 2;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0118 }
        r4 = 0;
        r5 = 0;
        r6 = r11.getMessage();	 Catch:{ all -> 0x0118 }
        r5 = com.facebook.react.modules.storage.AsyncStorageErrorUtil.getError(r5, r6);	 Catch:{ all -> 0x0118 }
        r2[r4] = r5;	 Catch:{ all -> 0x0118 }
        r4 = 1;
        r5 = 0;
        r2[r4] = r5;	 Catch:{ all -> 0x0118 }
        r1.invoke(r2);	 Catch:{ all -> 0x0118 }
        r9.close();
        goto L_0x0020;
    L_0x0118:
        r1 = move-exception;
        r9.close();
        throw r1;
    L_0x011d:
        r16.clear();
        r15 = r15 + 999;
        goto L_0x0037;
    L_0x0124:
        r0 = r18;
        r1 = r0.val$callback;
        r2 = 2;
        r2 = new java.lang.Object[r2];
        r4 = 0;
        r5 = 0;
        r2[r4] = r5;
        r4 = 1;
        r2[r4] = r10;
        r1.invoke(r2);
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.storage.AsyncStorageModule$1.doInBackgroundGuarded(java.lang.Void[]):void");
    }
}
