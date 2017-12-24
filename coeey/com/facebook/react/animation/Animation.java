package com.facebook.react.animation;

import android.view.View;
import com.facebook.infer.annotation.Assertions;
import javax.annotation.Nullable;

public abstract class Animation {
    @Nullable
    private View mAnimatedView;
    private final int mAnimationID;
    @Nullable
    private AnimationListener mAnimationListener;
    private volatile boolean mCancelled = false;
    private volatile boolean mIsFinished = false;
    private final AnimationPropertyUpdater mPropertyUpdater;

    public abstract void run();

    public Animation(int animationID, AnimationPropertyUpdater propertyUpdater) {
        this.mAnimationID = animationID;
        this.mPropertyUpdater = propertyUpdater;
    }

    public void setAnimationListener(AnimationListener animationListener) {
        this.mAnimationListener = animationListener;
    }

    public final void start(View view) {
        this.mAnimatedView = view;
        this.mPropertyUpdater.prepare(view);
        run();
    }

    protected final boolean onUpdate(float value) {
        boolean z;
        if (this.mIsFinished) {
            z = false;
        } else {
            z = true;
        }
        Assertions.assertCondition(z, "Animation must not already be finished!");
        if (!this.mCancelled) {
            this.mPropertyUpdater.onUpdate((View) Assertions.assertNotNull(this.mAnimatedView), value);
        }
        return !this.mCancelled;
    }

    protected final void finish() {
        Assertions.assertCondition(!this.mIsFinished, "Animation must not already be finished!");
        this.mIsFinished = true;
        if (!this.mCancelled) {
            if (this.mAnimatedView != null) {
                this.mPropertyUpdater.onFinish(this.mAnimatedView);
            }
            if (this.mAnimationListener != null) {
                this.mAnimationListener.onFinished();
            }
        }
    }

    public final void cancel() {
        if (!this.mIsFinished && !this.mCancelled) {
            this.mCancelled = true;
            if (this.mAnimationListener != null) {
                this.mAnimationListener.onCancel();
            }
        }
    }

    public int getAnimationID() {
        return this.mAnimationID;
    }
}
