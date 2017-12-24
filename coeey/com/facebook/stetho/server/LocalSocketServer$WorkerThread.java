package com.facebook.stetho.server;

import android.net.LocalSocket;

class LocalSocketServer$WorkerThread extends Thread {
    private final LocalSocket mSocket;
    private final SocketHandler mSocketHandler;

    public LocalSocketServer$WorkerThread(LocalSocket socket, SocketHandler socketHandler) {
        this.mSocket = socket;
        this.mSocketHandler = socketHandler;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r4 = this;
        r1 = r4.mSocketHandler;	 Catch:{ IOException -> 0x000d }
        r2 = r4.mSocket;	 Catch:{ IOException -> 0x000d }
        r1.onAccepted(r2);	 Catch:{ IOException -> 0x000d }
        r1 = r4.mSocket;	 Catch:{ IOException -> 0x002a }
        r1.close();	 Catch:{ IOException -> 0x002a }
    L_0x000c:
        return;
    L_0x000d:
        r0 = move-exception;
        r1 = "I/O error: %s";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0021 }
        r3 = 0;
        r2[r3] = r0;	 Catch:{ all -> 0x0021 }
        com.facebook.stetho.common.LogUtil.m202w(r1, r2);	 Catch:{ all -> 0x0021 }
        r1 = r4.mSocket;	 Catch:{ IOException -> 0x001f }
        r1.close();	 Catch:{ IOException -> 0x001f }
        goto L_0x000c;
    L_0x001f:
        r1 = move-exception;
        goto L_0x000c;
    L_0x0021:
        r1 = move-exception;
        r2 = r4.mSocket;	 Catch:{ IOException -> 0x0028 }
        r2.close();	 Catch:{ IOException -> 0x0028 }
    L_0x0027:
        throw r1;
    L_0x0028:
        r2 = move-exception;
        goto L_0x0027;
    L_0x002a:
        r1 = move-exception;
        goto L_0x000c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.server.LocalSocketServer$WorkerThread.run():void");
    }
}
