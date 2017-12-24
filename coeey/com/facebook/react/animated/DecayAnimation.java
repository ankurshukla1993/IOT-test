package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;

public class DecayAnimation extends AnimationDriver {
    private final double mDeceleration;
    private double mFromValue;
    private double mLastValue;
    private long mStartFrameTimeMillis = -1;
    private final double mVelocity;

    public DecayAnimation(ReadableMap config) {
        this.mVelocity = config.getDouble("velocity");
        this.mDeceleration = config.getDouble("deceleration");
    }

    public void runAnimationStep(long frameTimeNanos) {
        long frameTimeMillis = frameTimeNanos / 1000000;
        if (this.mStartFrameTimeMillis == -1) {
            this.mStartFrameTimeMillis = frameTimeMillis - 16;
            this.mFromValue = this.mAnimatedValue.mValue;
            this.mLastValue = this.mAnimatedValue.mValue;
        }
        double value = this.mFromValue + ((this.mVelocity / (1.0d - this.mDeceleration)) * (1.0d - Math.exp((-(1.0d - this.mDeceleration)) * ((double) (frameTimeMillis - this.mStartFrameTimeMillis)))));
        if (Math.abs(this.mLastValue - value) < 0.1d) {
            this.mHasFinished = true;
            return;
        }
        this.mLastValue = value;
        this.mAnimatedValue.mValue = value;
    }
}
