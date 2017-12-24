package com.bartoszlipinski.viewpropertyobjectanimator;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

class DimensionChangeListener extends ChangeUpdateListener implements AnimatorUpdateListener {
    private IntValues mHeight;
    private final LayoutParams mParams;
    private IntValues mWidth;

    DimensionChangeListener(View view) {
        super(view);
        this.mParams = view.getLayoutParams();
        if (this.mParams == null) {
            throw new IllegalStateException("View does not have layout params yet.");
        }
    }

    private int currentWidth() {
        if (this.mParams.width > 0) {
            return this.mParams.width;
        }
        return hasView() ? ((View) this.mView.get()).getWidth() : 0;
    }

    private int currentHeight() {
        if (this.mParams.height > 0) {
            return this.mParams.height;
        }
        return hasView() ? ((View) this.mView.get()).getHeight() : 0;
    }

    public void width(int width) {
        this.mWidth = new IntValues(currentWidth(), width);
    }

    public void widthBy(int widthBy) {
        this.mWidth = new IntValues(currentWidth(), currentWidth() + widthBy);
    }

    public void height(int height) {
        this.mHeight = new IntValues(currentHeight(), height);
    }

    public void heightBy(int heightBy) {
        this.mHeight = new IntValues(currentHeight(), currentHeight() + heightBy);
    }

    public void size(int size) {
        width(size);
        height(size);
    }

    public void sizeBy(int sizeBy) {
        widthBy(sizeBy);
        heightBy(sizeBy);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (hasView()) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            if (this.mWidth != null) {
                this.mParams.width = (int) calculateAnimatedValue((float) this.mWidth.mFrom, (float) this.mWidth.mTo, animatedFraction);
            }
            if (this.mHeight != null) {
                this.mParams.height = (int) calculateAnimatedValue((float) this.mHeight.mFrom, (float) this.mHeight.mTo, animatedFraction);
            }
            ((View) this.mView.get()).requestLayout();
        }
    }
}
