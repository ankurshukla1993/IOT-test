package org.webrtc;

import org.webrtc.CameraVideoCapturer.CameraSwitchHandler;

class VideoCapturerAndroid$2 implements Runnable {
    final /* synthetic */ VideoCapturerAndroid this$0;
    final /* synthetic */ CameraSwitchHandler val$switchEventsHandler;

    VideoCapturerAndroid$2(VideoCapturerAndroid this$0, CameraSwitchHandler cameraSwitchHandler) {
        this.this$0 = this$0;
        this.val$switchEventsHandler = cameraSwitchHandler;
    }

    public void run() {
        boolean z = true;
        VideoCapturerAndroid.access$200(this.this$0);
        synchronized (VideoCapturerAndroid.access$300(this.this$0)) {
            VideoCapturerAndroid.access$402(this.this$0, false);
        }
        if (this.val$switchEventsHandler != null) {
            CameraSwitchHandler cameraSwitchHandler = this.val$switchEventsHandler;
            if (VideoCapturerAndroid.access$500(this.this$0).facing != 1) {
                z = false;
            }
            cameraSwitchHandler.onCameraSwitchDone(z);
        }
    }
}
