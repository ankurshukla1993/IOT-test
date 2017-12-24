package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;

class OpacityAnimation extends Animation {
    private final float mDeltaOpacity;
    private final float mStartOpacity;
    private final View mView;

    static class OpacityAnimationListener implements AnimationListener {
        private boolean mLayerTypeChanged = false;
        private final View mView;

        public OpacityAnimationListener(View view) {
            this.mView = view;
        }

        public void onAnimationStart(Animation animation) {
            if (this.mView.hasOverlappingRendering() && this.mView.getLayerType() == 0) {
                this.mLayerTypeChanged = true;
                this.mView.setLayerType(2, null);
            }
        }

        public void onAnimationEnd(Animation animation) {
            if (this.mLayerTypeChanged) {
                this.mView.setLayerType(0, null);
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public OpacityAnimation(View view, float startOpacity, float endOpacity) {
        this.mView = view;
        this.mStartOpacity = startOpacity;
        this.mDeltaOpacity = endOpacity - startOpacity;
        setAnimationListener(new OpacityAnimationListener(view));
    }

    protected void applyTransformation(float interpolatedTime, Transformation t) {
        this.mView.setAlpha(this.mStartOpacity + (this.mDeltaOpacity * interpolatedTime));
    }

    public boolean willChangeBounds() {
        return false;
    }
}
