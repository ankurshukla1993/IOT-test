package com.bartoszlipinski.viewpropertyobjectanimator;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;

class MarginChangeListener extends ChangeUpdateListener implements AnimatorUpdateListener {
    private IntValues mBottomMargin;
    private IntValues mLeftMargin;
    private final MarginLayoutParams mParams;
    private IntValues mRightMargin;
    private IntValues mTopMargin;

    MarginChangeListener(View view) {
        super(view);
        this.mParams = (MarginLayoutParams) view.getLayoutParams();
        if (this.mParams == null) {
            throw new IllegalStateException("View does not have layout params yet.");
        }
    }

    private int currentLeftMargin() {
        return this.mParams.leftMargin;
    }

    private int currentTopMargin() {
        return this.mParams.topMargin;
    }

    private int currentRightMargin() {
        return this.mParams.rightMargin;
    }

    private int currentBottomMargin() {
        return this.mParams.bottomMargin;
    }

    public void leftMargin(int margin) {
        this.mLeftMargin = new IntValues(currentLeftMargin(), margin);
    }

    public void leftMarginBy(int marginBy) {
        this.mLeftMargin = new IntValues(currentLeftMargin(), currentLeftMargin() + marginBy);
    }

    public void topMargin(int margin) {
        this.mTopMargin = new IntValues(currentTopMargin(), margin);
    }

    public void topMarginBy(int marginBy) {
        this.mTopMargin = new IntValues(currentTopMargin(), currentLeftMargin() + marginBy);
    }

    public void bottomMargin(int margin) {
        this.mBottomMargin = new IntValues(currentBottomMargin(), margin);
    }

    public void bottomMarginBy(int marginBy) {
        this.mBottomMargin = new IntValues(currentBottomMargin(), currentLeftMargin() + marginBy);
    }

    public void rightMargin(int margin) {
        this.mRightMargin = new IntValues(currentRightMargin(), margin);
    }

    public void rightMarginBy(int marginBy) {
        this.mRightMargin = new IntValues(currentRightMargin(), currentLeftMargin() + marginBy);
    }

    public void horizontalMargin(int margin) {
        leftMargin(margin);
        rightMargin(margin);
    }

    public void horizontalMarginBy(int marginBy) {
        leftMarginBy(marginBy);
        rightMarginBy(marginBy);
    }

    public void verticalMargin(int margin) {
        topMargin(margin);
        bottomMargin(margin);
    }

    public void verticalMarginBy(int marginBy) {
        topMarginBy(marginBy);
        bottomMarginBy(marginBy);
    }

    public void margin(int margin) {
        leftMargin(margin);
        topMargin(margin);
        bottomMargin(margin);
        rightMargin(margin);
    }

    public void marginBy(int marginBy) {
        leftMarginBy(marginBy);
        topMarginBy(marginBy);
        bottomMarginBy(marginBy);
        rightMarginBy(marginBy);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (hasView()) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            if (this.mLeftMargin != null) {
                this.mParams.leftMargin = (int) calculateAnimatedValue((float) this.mLeftMargin.mFrom, (float) this.mLeftMargin.mTo, animatedFraction);
            }
            if (this.mTopMargin != null) {
                this.mParams.topMargin = (int) calculateAnimatedValue((float) this.mTopMargin.mFrom, (float) this.mTopMargin.mTo, animatedFraction);
            }
            if (this.mRightMargin != null) {
                this.mParams.rightMargin = (int) calculateAnimatedValue((float) this.mRightMargin.mFrom, (float) this.mRightMargin.mTo, animatedFraction);
            }
            if (this.mBottomMargin != null) {
                this.mParams.bottomMargin = (int) calculateAnimatedValue((float) this.mBottomMargin.mFrom, (float) this.mBottomMargin.mTo, animatedFraction);
            }
            ((View) this.mView.get()).requestLayout();
        }
    }
}
