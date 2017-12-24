package com.facebook.stetho.server;

import android.net.LocalServerSocket;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;

public class LocalSocketServer {
    private static final int MAX_BIND_RETRIES = 2;
    private static final int TIME_BETWEEN_BIND_RETRIES_MS = 1000;
    private static final String WORKER_THREAD_NAME_PREFIX = "StethoWorker";
    private final String mAddress;
    private final String mFriendlyName;
    private Thread mListenerThread;
    private LocalServerSocket mServerSocket;
    private final SocketHandler mSocketHandler;
    private boolean mStopped;
    private final AtomicInteger mThreadId = new AtomicInteger();

    public LocalSocketServer(String friendlyName, String address, SocketHandler socketHandler) {
        this.mFriendlyName = (String) Util.throwIfNull(friendlyName);
        this.mAddress = (String) Util.throwIfNull(address);
        this.mSocketHandler = socketHandler;
    }

    public String getName() {
        return this.mFriendlyName;
    }

    public void run() throws IOException {
        synchronized (this) {
            if (this.mStopped) {
                return;
            }
            this.mListenerThread = Thread.currentThread();
            listenOnAddress(this.mAddress);
        }
    }

    private void listenOnAddress(String address) throws IOException {
        this.mServerSocket = bindToSocket(address);
        LogUtil.i("Listening on @" + address);
        while (!Thread.interrupted()) {
            try {
                Thread t = new WorkerThread(this.mServerSocket.accept(), this.mSocketHandler);
                t.setName("StethoWorker-" + this.mFriendlyName + "-" + this.mThreadId.incrementAndGet());
                t.setDaemon(true);
                t.start();
            } catch (SocketException se) {
                if (Thread.interrupted()) {
                    break;
                }
                LogUtil.w(se, "I/O error");
            } catch (InterruptedIOException e) {
            } catch (IOException e2) {
                LogUtil.w(e2, "I/O error initialising connection thread");
            }
        }
        LogUtil.i("Server shutdown on @" + address);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void stop() {
        /*
        r1 = this;
        monitor-enter(r1);
        r0 = 1;
        r1.mStopped = r0;	 Catch:{ all -> 0x001c }
        r0 = r1.mListenerThread;	 Catch:{ all -> 0x001c }
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
    L_0x0009:
        return;
    L_0x000a:
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
        r0 = r1.mListenerThread;
        r0.interrupt();
        r0 = r1.mServerSocket;	 Catch:{ IOException -> 0x001a }
        if (r0 == 0) goto L_0x0009;
    L_0x0014:
        r0 = r1.mServerSocket;	 Catch:{ IOException -> 0x001a }
        r0.close();	 Catch:{ IOException -> 0x001a }
        goto L_0x0009;
    L_0x001a:
        r0 = move-exception;
        goto L_0x0009;
    L_0x001c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001c }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.server.LocalSocketServer.stop():void");
    }

    @Nonnull
    private static LocalServerSocket bindToSocket(String address) throws IOException {
        IOException firstException = null;
        int retries = 2;
        while (true) {
            try {
                break;
            } catch (IOException be) {
                LogUtil.w(be, "Binding error, sleep 1000 ms...");
                if (firstException == null) {
                    firstException = be;
                }
                Util.sleepUninterruptibly(1000);
                retries = retries - 1;
                if (retries <= 0) {
                    throw firstException;
                }
                int retries2;
                retries = retries2;
            }
        }
        if (LogUtil.isLoggable(3)) {
            LogUtil.d("Trying to bind to @" + address);
        }
        return new LocalServerSocket(address);
    }
}
