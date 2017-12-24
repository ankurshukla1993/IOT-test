package com.bartoszlipinski.viewpropertyobjectanimator;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;

class PaddingChangeListener extends ChangeUpdateListener implements AnimatorUpdateListener {
    private IntValues mBottomPadding;
    private IntValues mLeftPadding;
    private IntValues mRightPadding;
    private IntValues mTopPadding;

    PaddingChangeListener(View view) {
        super(view);
    }

    private int currentLeftPadding() {
        return hasView() ? ((View) this.mView.get()).getPaddingLeft() : 0;
    }

    private int currentTopPadding() {
        return hasView() ? ((View) this.mView.get()).getPaddingTop() : 0;
    }

    private int currentRightPadding() {
        return hasView() ? ((View) this.mView.get()).getPaddingRight() : 0;
    }

    private int currentBottomPadding() {
        return hasView() ? ((View) this.mView.get()).getPaddingBottom() : 0;
    }

    public void leftPadding(int padding) {
        this.mLeftPadding = new IntValues(currentLeftPadding(), padding);
    }

    public void leftPaddingBy(int paddingBy) {
        this.mLeftPadding = new IntValues(currentLeftPadding(), currentLeftPadding() + paddingBy);
    }

    public void topPadding(int padding) {
        this.mTopPadding = new IntValues(currentTopPadding(), padding);
    }

    public void topPaddingBy(int paddingBy) {
        this.mTopPadding = new IntValues(currentTopPadding(), currentTopPadding() + paddingBy);
    }

    public void bottomPadding(int padding) {
        this.mBottomPadding = new IntValues(currentBottomPadding(), padding);
    }

    public void bottomPaddingBy(int paddingBy) {
        this.mBottomPadding = new IntValues(currentBottomPadding(), currentBottomPadding() + paddingBy);
    }

    public void rightPadding(int padding) {
        this.mRightPadding = new IntValues(currentRightPadding(), padding);
    }

    public void rightPaddingBy(int paddingBy) {
        this.mRightPadding = new IntValues(currentRightPadding(), currentRightPadding() + paddingBy);
    }

    public void horizontalPadding(int padding) {
        leftPadding(padding);
        rightPadding(padding);
    }

    public void horizontalPaddingBy(int paddingBy) {
        leftPaddingBy(paddingBy);
        rightPaddingBy(paddingBy);
    }

    public void verticalPadding(int padding) {
        topPadding(padding);
        bottomPadding(padding);
    }

    public void verticalPaddingBy(int paddingBy) {
        topPaddingBy(paddingBy);
        bottomPaddingBy(paddingBy);
    }

    public void padding(int padding) {
        leftPadding(padding);
        topPadding(padding);
        bottomPadding(padding);
        rightPadding(padding);
    }

    public void paddingBy(int paddingBy) {
        leftPaddingBy(paddingBy);
        topPaddingBy(paddingBy);
        bottomPaddingBy(paddingBy);
        rightPaddingBy(paddingBy);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (hasView()) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            int leftPadding = currentLeftPadding();
            int topPadding = currentTopPadding();
            int rightPadding = currentRightPadding();
            int bottomPadding = currentBottomPadding();
            if (this.mLeftPadding != null) {
                leftPadding = (int) calculateAnimatedValue((float) this.mLeftPadding.mFrom, (float) this.mLeftPadding.mTo, animatedFraction);
            }
            if (this.mTopPadding != null) {
                topPadding = (int) calculateAnimatedValue((float) this.mTopPadding.mFrom, (float) this.mTopPadding.mTo, animatedFraction);
            }
            if (this.mRightPadding != null) {
                rightPadding = (int) calculateAnimatedValue((float) this.mRightPadding.mFrom, (float) this.mRightPadding.mTo, animatedFraction);
            }
            if (this.mBottomPadding != null) {
                bottomPadding = (int) calculateAnimatedValue((float) this.mBottomPadding.mFrom, (float) this.mBottomPadding.mTo, animatedFraction);
            }
            ((View) this.mView.get()).setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
        }
    }
}
