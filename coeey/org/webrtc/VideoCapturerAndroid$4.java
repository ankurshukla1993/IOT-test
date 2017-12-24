package org.webrtc;

class VideoCapturerAndroid$4 implements Runnable {
    final /* synthetic */ VideoCapturerAndroid this$0;
    final /* synthetic */ int val$framerate;
    final /* synthetic */ int val$height;
    final /* synthetic */ int val$width;

    VideoCapturerAndroid$4(VideoCapturerAndroid this$0, int i, int i2, int i3) {
        this.this$0 = this$0;
        this.val$width = i;
        this.val$height = i2;
        this.val$framerate = i3;
    }

    public void run() {
        VideoCapturerAndroid.access$702(this.this$0, 0);
        VideoCapturerAndroid.access$800(this.this$0, this.val$width, this.val$height, this.val$framerate);
    }
}
