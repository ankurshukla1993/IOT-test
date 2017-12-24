package com.facebook.stetho.server;

import android.content.Context;
import android.net.Credentials;
import android.net.LocalSocket;
import com.facebook.stetho.common.LogUtil;
import java.io.IOException;

public abstract class SecureSocketHandler implements SocketHandler {
    private final Context mContext;

    protected abstract void onSecured(LocalSocket localSocket) throws IOException;

    public SecureSocketHandler(Context context) {
        this.mContext = context;
    }

    public final void onAccepted(LocalSocket socket) throws IOException {
        try {
            enforcePermission(this.mContext, socket);
            onSecured(socket);
        } catch (PeerAuthorizationException e) {
            LogUtil.m189e("Unauthorized request: " + e.getMessage());
        }
    }

    private static void enforcePermission(Context context, LocalSocket peer) throws IOException, PeerAuthorizationException {
        Credentials credentials = peer.getPeerCredentials();
        int uid = credentials.getUid();
        int pid = credentials.getPid();
        if (LogUtil.isLoggable(2)) {
            LogUtil.m198v("Got request from uid=%d, pid=%d", Integer.valueOf(uid), Integer.valueOf(pid));
        }
        String requiredPermission = "android.permission.DUMP";
        if (context.checkPermission(requiredPermission, pid, uid) != 0) {
            throw new PeerAuthorizationException("Peer pid=" + pid + ", uid=" + uid + " does not have " + requiredPermission);
        }
    }
}
