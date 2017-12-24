package com.oney.WebRTCModule;

import android.util.Log;

class CameraEventsHandler implements org.webrtc.CameraVideoCapturer.CameraEventsHandler {
    private static final String TAG = WebRTCModule.TAG;

    CameraEventsHandler() {
    }

    public void onCameraError(String errorDescription) {
        Log.d(TAG, String.format("CameraEventsHandler.onCameraError: errorDescription=%s", new Object[]{errorDescription}));
    }

    public void onCameraDisconnected() {
        Log.d(TAG, "CameraEventsHandler.onCameraDisconnected");
    }

    public void onCameraFreezed(String errorDescription) {
        Log.d(TAG, String.format("CameraEventsHandler.onCameraFreezed: errorDescription=%s", new Object[]{errorDescription}));
    }

    public void onCameraOpening(String cameraName) {
        Log.d(TAG, String.format("CameraEventsHandler.onCameraOpening: cameraName=%s", new Object[]{cameraName}));
    }

    public void onFirstFrameAvailable() {
        Log.d(TAG, "CameraEventsHandler.onFirstFrameAvailable");
    }

    public void onCameraClosed() {
        Log.d(TAG, "CameraEventsHandler.onFirstFrameAvailable");
    }
}
