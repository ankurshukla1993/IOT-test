package com.facebook.stetho.server;

import com.facebook.stetho.common.LogUtil;
import java.io.IOException;

public class ServerManager {
    private static final String THREAD_PREFIX = "StethoListener";
    private final LocalSocketServer mServer;
    private volatile boolean mStarted;

    public ServerManager(LocalSocketServer server) {
        this.mServer = server;
    }

    public void start() {
        if (this.mStarted) {
            throw new IllegalStateException("Already started");
        }
        this.mStarted = true;
        startServer(this.mServer);
    }

    private void startServer(final LocalSocketServer server) {
        new Thread("StethoListener-" + server.getName()) {
            public void run() {
                try {
                    server.run();
                } catch (IOException e) {
                    LogUtil.m192e(e, "Could not start Stetho server: %s", server.getName());
                }
            }
        }.start();
    }
}
