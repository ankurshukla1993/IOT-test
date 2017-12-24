package com.bartoszlipinski.viewpropertyobjectanimator;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;

class ScrollChangeListener extends ChangeUpdateListener implements AnimatorUpdateListener {
    private IntValues mScrollX;
    private IntValues mScrollY;

    ScrollChangeListener(View view) {
        super(view);
    }

    private int currentScrollX() {
        return hasView() ? ((View) this.mView.get()).getScrollX() : 0;
    }

    private int currentScrollY() {
        return hasView() ? ((View) this.mView.get()).getScrollY() : 0;
    }

    public void scrollX(int scrollX) {
        this.mScrollX = new IntValues(currentScrollX(), scrollX);
    }

    public void scrollXBy(int scrollXBy) {
        this.mScrollX = new IntValues(currentScrollX(), currentScrollX() + scrollXBy);
    }

    public void scrollY(int scrollY) {
        this.mScrollY = new IntValues(currentScrollY(), scrollY);
    }

    public void scrollYBy(int scrollYBy) {
        this.mScrollY = new IntValues(currentScrollY(), currentScrollY() + scrollYBy);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (hasView()) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            int scrollX = currentScrollX();
            int scrollY = currentScrollY();
            if (this.mScrollX != null) {
                scrollX = (int) calculateAnimatedValue((float) this.mScrollX.mFrom, (float) this.mScrollX.mTo, animatedFraction);
            }
            if (this.mScrollY != null) {
                scrollY = (int) calculateAnimatedValue((float) this.mScrollY.mFrom, (float) this.mScrollY.mTo, animatedFraction);
            }
            ((View) this.mView.get()).scrollTo(scrollX, scrollY);
        }
    }
}
