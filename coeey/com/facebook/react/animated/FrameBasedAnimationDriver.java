package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

class FrameBasedAnimationDriver extends AnimationDriver {
    private static final double FRAME_TIME_MILLIS = 16.666666666666668d;
    private final double[] mFrames;
    private double mFromValue;
    private long mStartFrameTimeNanos = -1;
    private final double mToValue;

    FrameBasedAnimationDriver(ReadableMap config) {
        ReadableArray frames = config.getArray("frames");
        int numberOfFrames = frames.size();
        this.mFrames = new double[numberOfFrames];
        for (int i = 0; i < numberOfFrames; i++) {
            this.mFrames[i] = frames.getDouble(i);
        }
        this.mToValue = config.getDouble("toValue");
    }

    public void runAnimationStep(long frameTimeNanos) {
        if (this.mStartFrameTimeNanos < 0) {
            this.mStartFrameTimeNanos = frameTimeNanos;
            this.mFromValue = this.mAnimatedValue.mValue;
        }
        int frameIndex = (int) (((double) ((frameTimeNanos - this.mStartFrameTimeNanos) / 1000000)) / FRAME_TIME_MILLIS);
        if (frameIndex < 0) {
            throw new IllegalStateException("Calculated frame index should never be lower than 0");
        } else if (!this.mHasFinished) {
            double nextValue;
            if (frameIndex >= this.mFrames.length - 1) {
                this.mHasFinished = true;
                nextValue = this.mToValue;
            } else {
                nextValue = this.mFromValue + (this.mFrames[frameIndex] * (this.mToValue - this.mFromValue));
            }
            this.mAnimatedValue.mValue = nextValue;
        }
    }
}
