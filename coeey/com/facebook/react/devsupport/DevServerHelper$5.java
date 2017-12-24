package com.facebook.react.devsupport;

import java.io.File;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;

class DevServerHelper$5 implements Callback {
    final /* synthetic */ DevServerHelper this$0;
    final /* synthetic */ DevServerHelper$BundleDownloadCallback val$callback;
    final /* synthetic */ File val$outputFile;

    public void onResponse(okhttp3.Call r8, okhttp3.Response r9) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1431)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r7 = this;
        r5 = 0;
        r4 = r7.this$0;
        r4 = com.facebook.react.devsupport.DevServerHelper.access$300(r4);
        if (r4 == 0) goto L_0x0015;
    L_0x0009:
        r4 = r7.this$0;
        r4 = com.facebook.react.devsupport.DevServerHelper.access$300(r4);
        r4 = r4.isCanceled();
        if (r4 == 0) goto L_0x001b;
    L_0x0015:
        r4 = r7.this$0;
        com.facebook.react.devsupport.DevServerHelper.access$302(r4, r5);
    L_0x001a:
        return;
    L_0x001b:
        r4 = r7.this$0;
        com.facebook.react.devsupport.DevServerHelper.access$302(r4, r5);
        r4 = r9.isSuccessful();
        if (r4 != 0) goto L_0x0087;
    L_0x0026:
        r4 = r9.body();
        r0 = r4.string();
        r1 = com.facebook.react.devsupport.DebugServerException.parse(r0);
        if (r1 == 0) goto L_0x003a;
    L_0x0034:
        r4 = r7.val$callback;
        r4.onFailure(r1);
        goto L_0x001a;
    L_0x003a:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "The development server returned response error code: ";
        r4 = r3.append(r4);
        r5 = r9.code();
        r4 = r4.append(r5);
        r5 = "\n\n";
        r4 = r4.append(r5);
        r5 = "URL: ";
        r4 = r4.append(r5);
        r5 = r8.request();
        r5 = r5.url();
        r5 = r5.toString();
        r4 = r4.append(r5);
        r5 = "\n\n";
        r4 = r4.append(r5);
        r5 = "Body:\n";
        r4 = r4.append(r5);
        r4.append(r0);
        r4 = r7.val$callback;
        r5 = new com.facebook.react.devsupport.DebugServerException;
        r6 = r3.toString();
        r5.<init>(r6);
        r4.onFailure(r5);
        goto L_0x001a;
    L_0x0087:
        r2 = 0;
        r4 = r7.val$outputFile;	 Catch:{ all -> 0x00a9 }
        r2 = okio.Okio.sink(r4);	 Catch:{ all -> 0x00a9 }
        r4 = r9.body();	 Catch:{ all -> 0x00a9 }
        r4 = r4.source();	 Catch:{ all -> 0x00a9 }
        r4 = okio.Okio.buffer(r4);	 Catch:{ all -> 0x00a9 }
        r4.readAll(r2);	 Catch:{ all -> 0x00a9 }
        r4 = r7.val$callback;	 Catch:{ all -> 0x00a9 }
        r4.onSuccess();	 Catch:{ all -> 0x00a9 }
        if (r2 == 0) goto L_0x001a;
    L_0x00a4:
        r2.close();
        goto L_0x001a;
    L_0x00a9:
        r4 = move-exception;
        if (r2 == 0) goto L_0x00af;
    L_0x00ac:
        r2.close();
    L_0x00af:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.devsupport.DevServerHelper$5.onResponse(okhttp3.Call, okhttp3.Response):void");
    }

    DevServerHelper$5(DevServerHelper this$0, DevServerHelper$BundleDownloadCallback devServerHelper$BundleDownloadCallback, File file) {
        this.this$0 = this$0;
        this.val$callback = devServerHelper$BundleDownloadCallback;
        this.val$outputFile = file;
    }

    public void onFailure(Call call, IOException e) {
        if (DevServerHelper.access$300(this.this$0) == null || DevServerHelper.access$300(this.this$0).isCanceled()) {
            DevServerHelper.access$302(this.this$0, null);
            return;
        }
        DevServerHelper.access$302(this.this$0, null);
        this.val$callback.onFailure(DebugServerException.makeGeneric("Could not connect to development server.", "URL: " + call.request().url().toString(), e));
    }
}
