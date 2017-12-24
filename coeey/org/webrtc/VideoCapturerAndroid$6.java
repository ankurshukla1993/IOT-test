package org.webrtc;

import java.util.concurrent.CountDownLatch;

class VideoCapturerAndroid$6 implements Runnable {
    final /* synthetic */ VideoCapturerAndroid this$0;
    final /* synthetic */ CountDownLatch val$barrier;

    VideoCapturerAndroid$6(VideoCapturerAndroid this$0, CountDownLatch countDownLatch) {
        this.this$0 = this$0;
        this.val$barrier = countDownLatch;
    }

    public void run() {
        VideoCapturerAndroid.access$900(this.this$0, true);
        this.val$barrier.countDown();
    }
}
