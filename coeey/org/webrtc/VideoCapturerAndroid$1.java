package org.webrtc;

import android.hardware.Camera;
import android.hardware.Camera.ErrorCallback;

class VideoCapturerAndroid$1 implements ErrorCallback {
    final /* synthetic */ VideoCapturerAndroid this$0;

    VideoCapturerAndroid$1(VideoCapturerAndroid this$0) {
        this.this$0 = this$0;
    }

    public void onError(int error, Camera camera) {
        String errorMessage;
        boolean cameraRunning = VideoCapturerAndroid.access$000(this.this$0).get();
        if (error == 100) {
            errorMessage = "Camera server died!";
        } else {
            errorMessage = "Camera error: " + error;
        }
        Logging.m2188e("VideoCapturerAndroid", errorMessage + ". Camera running: " + cameraRunning);
        if (VideoCapturerAndroid.access$100(this.this$0) == null) {
            return;
        }
        if (error != 2) {
            VideoCapturerAndroid.access$100(this.this$0).onCameraError(errorMessage);
        } else if (cameraRunning) {
            VideoCapturerAndroid.access$100(this.this$0).onCameraDisconnected();
        } else {
            Logging.m2187d("VideoCapturerAndroid", "Ignore CAMERA_ERROR_EVICTED for closed camera.");
        }
    }
}
