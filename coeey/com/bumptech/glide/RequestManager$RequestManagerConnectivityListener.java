package com.bumptech.glide;

import com.bumptech.glide.manager.ConnectivityMonitor.ConnectivityListener;
import com.bumptech.glide.manager.RequestTracker;

class RequestManager$RequestManagerConnectivityListener implements ConnectivityListener {
    private final RequestTracker requestTracker;

    public RequestManager$RequestManagerConnectivityListener(RequestTracker requestTracker) {
        this.requestTracker = requestTracker;
    }

    public void onConnectivityChanged(boolean isConnected) {
        if (isConnected) {
            this.requestTracker.restartRequests();
        }
    }
}
