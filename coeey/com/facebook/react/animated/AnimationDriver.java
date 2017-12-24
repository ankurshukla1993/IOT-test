package com.facebook.react.animated;

import com.facebook.react.bridge.Callback;

abstract class AnimationDriver {
    ValueAnimatedNode mAnimatedValue;
    Callback mEndCallback;
    boolean mHasFinished = false;
    int mId;

    public abstract void runAnimationStep(long j);

    AnimationDriver() {
    }
}
