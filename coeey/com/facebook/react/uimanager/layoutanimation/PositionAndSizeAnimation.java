package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

class PositionAndSizeAnimation extends Animation implements HandleLayout {
    private final int mDeltaHeight;
    private final int mDeltaWidth;
    private final float mDeltaX;
    private final float mDeltaY;
    private final int mStartHeight;
    private final int mStartWidth;
    private final float mStartX;
    private final float mStartY;
    private final View mView;

    public PositionAndSizeAnimation(View view, int x, int y, int width, int height) {
        this.mView = view;
        this.mStartX = view.getX() - view.getTranslationX();
        this.mStartY = view.getY() - view.getTranslationY();
        this.mStartWidth = view.getWidth();
        this.mStartHeight = view.getHeight();
        this.mDeltaX = ((float) x) - this.mStartX;
        this.mDeltaY = ((float) y) - this.mStartY;
        this.mDeltaWidth = width - this.mStartWidth;
        this.mDeltaHeight = height - this.mStartHeight;
    }

    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float newX = this.mStartX + (this.mDeltaX * interpolatedTime);
        float newY = this.mStartY + (this.mDeltaY * interpolatedTime);
        this.mView.layout(Math.round(newX), Math.round(newY), Math.round(newX + (((float) this.mStartWidth) + (((float) this.mDeltaWidth) * interpolatedTime))), Math.round(newY + (((float) this.mStartHeight) + (((float) this.mDeltaHeight) * interpolatedTime))));
    }

    public boolean willChangeBounds() {
        return true;
    }
}
